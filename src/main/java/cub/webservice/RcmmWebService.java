package cub.webservice;

import java.time.LocalDateTime;
import java.util.Calendar;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import cub.entities.OrderInfo;
import cub.facade.OrderInfoFacade;
import cub.vo.RcmmRequestObject;
import cub.vo.RcmmResponseObject;

@Stateless
@LocalBean
@Path("/rcmmService")
public class RcmmWebService {

    @EJB
    private OrderInfoFacade orderInfoFacade;

    @POST
    @Produces("application/json")
    public RcmmResponseObject getResponsePost(RcmmRequestObject request) {
        OrderInfo orderInfo = new OrderInfo();
        String orderInfoSeq = orderInfoFacade.getOrderInfoSeq();
        Calendar cal = Calendar.getInstance();
        cal.setTime(request.getOrderDttm());
        String orderNo = "TR" + cal.get(Calendar.YEAR) + orderInfoSeq.substring(1, orderInfoSeq.length());
        orderInfo.setOrderNo(orderNo);
        Short channelCode = orderInfoFacade.getChannelCode(request.getAPID());
        orderInfo.setChannelCode(channelCode);
        orderInfo.setCheckTiming(request.getCheckTiming());
        orderInfo.setClientId(request.getClientId());
        orderInfo.setClientIdSwitchIn(request.getClientIdSwitchIn());
        orderInfo.setOrderAmt(request.getOrderAmt());
        orderInfo.setOrderCur(request.getOrderCur());
        orderInfo.setOrderDttm(request.getOrderDttm());
        orderInfo.setOrderPrice(request.getOrderPrice());
        orderInfo.setOrderQty(request.getOrderQty());
        orderInfo.setPrdCode(request.getPrdCode());
        orderInfo.setSecCode(request.getSecCode());
        orderInfo.setSecCodeSwitchIn(request.getSecCodeSwitchIn());
        orderInfo.setTradeType(request.getTradeType());
        orderInfoFacade.create(orderInfo);
        RcmmResponseObject rcmmResponseObject = new RcmmResponseObject();
        rcmmResponseObject.setReturnMessage("Success");
        rcmmResponseObject.setReturnCode("0000");
        rcmmResponseObject.setOrderNumber(orderNo);
        rcmmResponseObject.setUpdateTime(LocalDateTime.now());
        return rcmmResponseObject;
    }
}
