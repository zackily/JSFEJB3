package cub.webservice;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wordnik.swagger.annotations.Api;

import cub.entities.CheckResultDetail;
import cub.entities.CheckResultDetailPK;
import cub.entities.OrderInfo;
import cub.entities.RuleList;
import cub.facade.CheckResultDetailFacade;
import cub.facade.OrderInfoFacade;
import cub.facade.RuleDividendFacade;
import cub.facade.RuleDivisorFacade;
import cub.facade.RuleListFacade;
import cub.log.LogAspect;
import cub.vo.RcmmRequestObject;
import cub.vo.RcmmResponseObject;

@Stateless
@LocalBean
@Path("/rcmmService")
@Api(value = "/rcmmService", description = "Welcome to Rcmm Service!")
@Interceptors(LogAspect.class)
public class RcmmWebService {

    private static Logger logger = LoggerFactory.getLogger(RcmmWebService.class);

    @EJB
    private OrderInfoFacade orderInfoFacade;
    @EJB
    private RuleListFacade ruleListFacade;
    @EJB
    private CheckResultDetailFacade checkResultDetailFacade;
    @EJB
    private static RuleDividendFacade ruleDividendFacade;
    @EJB
    private static RuleDivisorFacade ruleDivisorFacade;
    private static BigDecimal ratio = null;
    private static Short resultCode = null;
    private static BigDecimal div = null;
    private static BigDecimal dis = null;
    private static String rtnMessage = null;

    @POST
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public RcmmResponseObject getResponsePost(RcmmRequestObject request) {
        BigDecimal channelCode = orderInfoFacade.getChannelCode(request.getApid());
        OrderInfo orderInfo = genOrderInfo(request, channelCode);
        // 3.GEN_APPLIED_RULE 產生下單適用法規檔
        List<RuleList> ruleList_T = ruleListFacade.joinRuleList(request, channelCode);
        // RTN_CHECK_RESULT
        String returnCode = rtnCheckResult(ruleList_T, orderInfo);
        String resReturnCode = "";
        if (StringUtils.isNotBlank(returnCode)) {
            switch (returnCode) {
                case "0":
                    resReturnCode = "0000";
                    rtnMessage = "檢核通過";
                    break;
                case "1":
                    resReturnCode = "1111";
                    rtnMessage = "檢核不通過,不可下單";
                    break;
                case "2":
                    resReturnCode = "2222";
                    rtnMessage = "檢核不通過,示警告但可下單";
                    break;
                case "9":
                    resReturnCode = "9999";
                    break;
                default:
                    resReturnCode = "9999";
                    break;
            }
        }

        RcmmResponseObject rcmmResponseObject = new RcmmResponseObject();
        rcmmResponseObject.setReturnMessage(rtnMessage);
        rcmmResponseObject.setReturnCode(resReturnCode);
        rcmmResponseObject.setOrderNumber(orderInfo.getOrderNo());
        rcmmResponseObject.setUpdateTime(LocalDateTime.now());
        return rcmmResponseObject;
    }

    /*
     * 2.建立下單資料檔
     */
    private OrderInfo genOrderInfo(RcmmRequestObject request, BigDecimal channelCode) {
        OrderInfo orderInfo = new OrderInfo();
        String orderInfoSeq = orderInfoFacade.getOrderInfoSeq();
        Calendar cal = Calendar.getInstance();
        cal.setTime(request.getOrderDttm());
        String orderNo = "TR" + cal.get(Calendar.YEAR) + orderInfoSeq.substring(1, orderInfoSeq.length());
        orderInfo.setOrderNo(orderNo);
        orderInfo.setChannelCode(channelCode);
        orderInfo.setCheckTiming(request.getCheckTiming());
        orderInfo.setClientId(request.getClientId());
        orderInfo.setClientIdSwitchIn(request.getClientIdSwitchIn());
        orderInfo.setOrderAmt(request.getOrderAmt());
        orderInfo.setOrderCur(request.getOrderCurrency());
        orderInfo.setOrderDttm(request.getOrderDttm());
        orderInfo.setOrderPrice(request.getOrderPrice());
        orderInfo.setOrderQty(request.getOrderQty());
        orderInfo.setPrdCode(request.getPrdCode());
        orderInfo.setSecCode(request.getSecCode());
        orderInfo.setSecCodeSwitchIn(request.getSecCodeSwitchIn());
        orderInfo.setTradeType(request.getTradeType());
        orderInfoFacade.create(orderInfo);
        return orderInfo;
    }

    /*
     * 4. 下單法規檢核
     */
    private String rtnCheckResult(List<RuleList> ruleList_T, final OrderInfo orderInfo) {
        ExecutorService executorService = Executors.newFixedThreadPool(30);
        for (final RuleList rl : ruleList_T) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    // GEN_RULE_RESULT
                    genRuleResult(orderInfo, rl);
                }
            });
        }
        executorService.shutdown();
        List<CheckResultDetail> crdList = checkResultDetailFacade.findByOrderNo(orderInfo.getOrderNo());
        if (ruleList_T.size() == crdList.size()) {
            boolean rule_0 = false;// 檢核通過
            boolean rule_1 = false;// 檢核不通過,不可下單
            boolean rule_2 = false;// 檢核不通過,示警告但可下單
            boolean rule_9 = false;// 檢核失敗
            StringBuilder mess = new StringBuilder(100);
            for (CheckResultDetail crd : crdList) {
                if (StringUtils.isNotBlank(crd.getRtnMessage())) {
                    mess.append(crd.getRtnMessage()).append("; ");
                }
                switch (crd.getResultCode().intValue()) {
                    case 0:
                        rule_0 = true;
                        break;
                    case 1:
                        rule_1 = true;
                        break;
                    case 2:
                        rule_2 = true;
                        break;
                    case 9:
                        rule_9 = true;
                        break;
                    default:
                        break;
                }
            }
            if (rule_9 == true) {
                return "9";
            } else if (rule_1 == true) {
                return "1";
            } else if (rule_2 == true) {
                return "2";
            } else if (rule_0 == true) {
                return "0";
            } else {
                return "9";
            }
        } else {
            // 失敗
            return "9";
        }
    }

    /*
     * 4.1
     */
    private void genRuleResult(final OrderInfo orderInfo, final RuleList rl) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        PrimerTask task = new PrimerTask(orderInfo, rl);
        Future<Boolean> future = executorService.submit(task);
        executorService.shutdown();
        cancelTask(future, 5_000);
        // insert Check_RESULT_DETAIL
        CheckResultDetail entity = new CheckResultDetail();
        CheckResultDetailPK pk = new CheckResultDetailPK(orderInfo.getOrderNo(), rl.getRuleNo());
        entity.setId(pk);
        entity.setDividend(div);
        entity.setDivisor(dis);
        entity.setLogDttm(new Date());
        entity.setRtnMessage(rtnMessage);
        entity.setRatio(ratio);
        entity.setResultCode(BigDecimal.valueOf(resultCode));
        checkResultDetailFacade.create(entity);
    }

    private static Short checkLimitCondition(final RuleList rl, BigDecimal ratio, Short resultCode) {
        switch (rl.getLimitCondition()) {
            case 1:
                if (ratio.compareTo(rl.getLimitRate()) == -1) {
                    resultCode = rl.getLimitReaction();
                    rtnMessage = rl.getRtnMessage();
                } else {
                    resultCode = 0;
                }
                break;
            case 2:
                if (ratio.compareTo(rl.getLimitRate()) <= 0) {
                    resultCode = rl.getLimitReaction();
                    rtnMessage = rl.getRtnMessage();
                } else {
                    resultCode = 0;
                }
                break;
            case 3:
                if (ratio.compareTo(rl.getLimitRate()) == 0) {
                    resultCode = rl.getLimitReaction();
                    rtnMessage = rl.getRtnMessage();
                } else {
                    resultCode = 0;
                }
                break;
            case 4:
                if (ratio.compareTo(rl.getLimitRate()) == 1) {
                    resultCode = rl.getLimitReaction();
                    rtnMessage = rl.getRtnMessage();
                } else {
                    resultCode = 0;
                }
                break;
            case 5:
                if (ratio.compareTo(rl.getLimitRate()) >= 0) {
                    resultCode = rl.getLimitReaction();
                    rtnMessage = rl.getRtnMessage();
                } else {
                    resultCode = 0;
                }
                break;
            case 6:
                if (ratio.compareTo(rl.getLimitRate()) != 0) {
                    resultCode = rl.getLimitReaction();
                    rtnMessage = rl.getRtnMessage();
                } else {
                    resultCode = 0;
                }
                break;

            default:
                break;
        }
        return resultCode;
    }

    /*
     * 4.2 RTN_FACTOR_VALUE
     */
    private static BigDecimal rtnFactorValue(OrderInfo orderInfo, RuleList rl, int checkFactor) {
        BigDecimal scopeValue = new BigDecimal("0");
        if (checkFactor == 2 && rl.getDivisorSource().intValue() == 1) {
            scopeValue = rl.getDivisorValue();
        } else {
            List<String[]> dividendList = ruleDividendFacade.findCodeByRuleNo(rl.getRuleNo());
            List<String[]> divisorList = ruleDivisorFacade.findCodeByRuleNo(rl.getRuleNo());
            List<String[]> newList = new ArrayList<>();
            newList.addAll(dividendList);
            newList.addAll(divisorList);
            // sa[0]:op_code;sa[1]:scope_code
            for (String[] sa : newList) {
                BigDecimal ss;
                ss = rtnScopeValue(sa[1], orderInfo, rl);
                String opCode = sa[0];
                if (opCode.equals("+") || opCode.equals("")) {
                    scopeValue = scopeValue.add(ss);
                } else {
                    scopeValue = scopeValue.subtract(ss);
                }
            }
            BigDecimal orderQty;
            String column = rl.getCheckColumn().toString();
            if (!column.equals("3")) {
                if (orderInfo.getPrdCode().toString().equals("3") && column.equals("1")
                        && orderInfo.getOrderCur().equals("USD")) {
                    orderQty = orderInfo.getOrderQty().multiply(new BigDecimal("31.1"));
                } else {
                    orderQty = orderInfo.getOrderQty();
                }
                if (column.equals("1")) {
                    scopeValue = scopeValue.add(orderQty);
                } else {
                    scopeValue = scopeValue.add(orderInfo.getOrderAmt());
                }
            }
        }

        return scopeValue;
    }

    /*
     * 4.3 RTN_SCOPE_VALUE
     */
    private static BigDecimal rtnScopeValue(String scopeCode, OrderInfo orderInfo, RuleList rl) {
        if (scopeCode.startsWith("DF")) {

        } else {

        }
        return null;
    }

    private static final class PrimerTask implements Callable<Boolean> {

        private final OrderInfo orderInfo;
        private final RuleList rl;

        public PrimerTask(OrderInfo orderInfo, RuleList rl) {
            this.orderInfo = orderInfo;
            this.rl = rl;
        }

        @Override
        public Boolean call() throws Exception {
            div = rtnFactorValue(orderInfo, rl, 1);
            dis = rtnFactorValue(orderInfo, rl, 2);
            if (null == dis || dis.compareTo(BigDecimal.ZERO) == 0) {
                resultCode = Short.valueOf("9");
                rtnMessage = "分母計算有誤";
                return false;
            } else if (Thread.currentThread().isInterrupted()) {
                resultCode = Short.valueOf("9");
                rtnMessage = "檢核時間過長";
                return false;
            }
            ratio = div.divide(dis, 2, RoundingMode.HALF_UP);
            resultCode = checkLimitCondition(rl, ratio, resultCode);
            return true;
        }

    }

    private static void cancelTask(final Future<?> future, final int delay) {

        Runnable cancellation = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(delay);
                    future.cancel(true);
                } catch (InterruptedException ex) {
                    ex.printStackTrace(System.err);
                }
            }
        };

        new Thread(cancellation).start();
    }
}
