package cub.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.apache.commons.lang.StringUtils;
import org.primefaces.event.SelectEvent;

import cub.entities.DataScopeMaster;
import cub.entities.RdOptionItem;
import cub.entities.RuleChannel;
import cub.entities.RuleChannelPK;
import cub.entities.RuleChecktime;
import cub.entities.RuleChecktimePK;
import cub.entities.RuleDividend;
import cub.entities.RuleDividendPK;
import cub.entities.RuleDivisor;
import cub.entities.RuleDivisorPK;
import cub.entities.RuleList;
import cub.entities.RuleOrderColumn;
import cub.entities.RuleOrderColumnPK;
import cub.entities.RuleOrderPage;
import cub.entities.RuleOrderPagePK;
import cub.entities.RuleProduct;
import cub.entities.RuleProductPK;
import cub.entities.RuleTradeType;
import cub.entities.RuleTradeTypePK;
import cub.entities.UdDataScopeMaster;
import cub.enums.SeqTypeEnum;
import cub.facade.DataScopeMasterFacade;
import cub.facade.RdOptionItemFacade;
import cub.facade.RuleChannelFacade;
import cub.facade.RuleChecktimeFacade;
import cub.facade.RuleDividendFacade;
import cub.facade.RuleDivisorFacade;
import cub.facade.RuleListFacade;
import cub.facade.RuleOrderColumnFacade;
import cub.facade.RuleOrderPageFacade;
import cub.facade.RuleProductFacade;
import cub.facade.RuleTradeTypeFacade;
import cub.facade.UdDataScopeMasterFacade;
import cub.facade.WorkSeqFacade;
import cub.sso.UserSession;

/**
 * @author F123669 投資限制條文 設定作業(RCMM03)
 *
 */
@ManagedBean(name = "investRuleSetController")
@ViewScoped
public class InvestRuleSetController extends AbstractController implements Serializable {
    @ManagedProperty("#{userSession}")
    private UserSession userSession;
    @EJB
    private WorkSeqFacade ejbWorkSeqFacade;
    @EJB
    private RuleListFacade ejbRuleListFacade;
    @EJB
    private RuleProductFacade ejbRuleProductFacade;
    @EJB
    private RuleTradeTypeFacade ejbRuleTradeTypeFacade;
    @EJB
    private RuleChannelFacade ejbRuleChannelFacade;
    @EJB
    private RuleChecktimeFacade ejbRuleChecktimeFacade;
    @EJB
    private RuleOrderPageFacade ejbRuleOrderPageFacade;
    @EJB
    private RuleOrderColumnFacade ejbRuleOrderColumnFacade;
    @EJB
    private RdOptionItemFacade ejbRdOptionItemFacade;
    @EJB
    private RuleDividendFacade ejbRuleDividendFacade;
    @EJB
    private RuleDivisorFacade ejbRuleDivisorFacade;
    @EJB
    private DataScopeMasterFacade ejbDataScopeMasterFacade;
    @EJB
    private UdDataScopeMasterFacade ejbUdDataScopeMasterFacade;
    /*
     * 自定義欄位範圍列表
     */
    private List<RuleList> master;
    /*
     * 新增/修改
     */
    private RuleList item;
    /*
     * 待修改資料範圍設定
     */
    private RuleList currentItem;
    /*
     * 自定義欄位範圍索引
     */
    private int currentIndex;
    /*
     * 新增/修改Dialog CommandButton value
     */
    private String editDialogLabel = "新增";
    /*
     * 代碼分類
     */
    private List<SelectItem> ruleClassList;
    /*
     * 檢核商品
     */
    private List<SelectItem> ruleProductList;
    /*
     * 交易類型
     */
    private List<SelectItem> ruleTradeTypeList;
    /*
     * 檢核通路
     */
    private List<SelectItem> ruleChannelList;
    /*
     * 檢核時點
     */
    private List<SelectItem> ruleChecktimeList;
    /*
     * 檢核頁面
     */
    private List<SelectItem> ruleOrderPageList;
    /*
     * 檢核欄位
     */
    private List<SelectItem> ruleOrderColumnList;
    /*
     * 客戶統計條件
     */
    private List<SelectItem> clientAggregateList;
    /*
     * 檢核資料欄位
     */
    private List<SelectItem> checkColumnList;
    /*
     * 分子標的合計方式
     */
    private List<SelectItem> dividendAggregateList;
    /*
     * 分母數值抓取抓取方式
     */
    private List<SelectItem> dividsorSourceList;
    /*
     * 超限條件
     */
    private List<SelectItem> limitConditionList;
    /*
     * 超限處理
     */
    private List<SelectItem> limitReactionList;
    /*
     * 檢核商品
     */
    private Object[] tempRuleProduct;
    /*
     * 交易類別
     */
    private Object[] tempRuleTradeType;
    /*
     * 檢核通路
     */
    private Object[] tempRuleChannel;
    /*
     * 檢核時點
     */
    private Object[] tempRuleCheckTime;
    /*
     * 檢核頁面
     */
    private Object[] tempRuleOrderPage;
    /*
     * 檢核欄位
     */
    private Object[] tempRuleOrderColumn;
    /*
     * 檢核商品
     */
    private String strTempRuleProduct;
    /*
     * 交易類別
     */
    private String strTempRuleTradeType;
    /*
     * 檢核通路
     */
    private String strTempRuleChannel;
    /*
     * 檢核時點
     */
    private String strTempRuleCheckTime;
    /*
     * 檢核頁面
     */
    private String strTempRuleOrderPage;
    /*
     * 檢核欄位
     */
    private String strTempRuleOrderColumn;
    /*
     * 分子標的
     */
    private List<RuleDividend> ruleDividendList;
    /*
     * 分母標的
     */
    private List<RuleDivisor> ruleDivisorList;

    private List<SelectItem> scopeCodeMenu;

    @PostConstruct
    public void init() {
        try {
            this.checkSession(userSession);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.master = new ArrayList<RuleList>();
        getRenewMaster();
        this.item = new RuleList();
        this.currentItem = new RuleList();
        this.ruleDividendList = new ArrayList<RuleDividend>();
        this.ruleDivisorList = new ArrayList<RuleDivisor>();
        if (this.master.isEmpty()) {
            this.master.add(this.item);
        } else {
            this.currentItem = this.master.get(0);
            genTempList();
        }
        // 頁面載入資料範圍欄位this.master的index
        currentIndex = 0;
        List<RdOptionItem> allItem = ejbRdOptionItemFacade.findAllSortByItemCode();
        this.ruleClassList = new ArrayList<SelectItem>();
        this.ruleProductList = new ArrayList<SelectItem>();
        this.ruleTradeTypeList = new ArrayList<SelectItem>();
        this.ruleChannelList = new ArrayList<SelectItem>();
        this.ruleChecktimeList = new ArrayList<SelectItem>();
        this.ruleOrderPageList = new ArrayList<SelectItem>();
        this.ruleOrderColumnList = new ArrayList<SelectItem>();
        this.clientAggregateList = new ArrayList<SelectItem>();
        this.checkColumnList = new ArrayList<SelectItem>();
        this.dividendAggregateList = new ArrayList<SelectItem>();
        this.dividsorSourceList = new ArrayList<SelectItem>();
        this.limitConditionList = new ArrayList<SelectItem>();
        this.limitReactionList = new ArrayList<SelectItem>();
        this.scopeCodeMenu = new ArrayList<SelectItem>();
        genAllItemList(allItem);
        List<DataScopeMaster> dsm = ejbDataScopeMasterFacade.findAllSort();
        List<UdDataScopeMaster> udsm = ejbUdDataScopeMasterFacade.findAllSort();
        for (DataScopeMaster d : dsm) {
            this.scopeCodeMenu.add(new SelectItem(d.getScopeCode(), d.getScopeCode() + " " + d.getScopeName()));
        }
        for (UdDataScopeMaster d : udsm) {
            this.scopeCodeMenu.add(new SelectItem(d.getScopeCode(), d.getScopeCode() + " " + d.getScopeName()));
        }
    }

    /*
     * 自定義欄位範圍代碼autocomplete
     */
    public List<String> searchRuleListMaster(String ruleNo) {
        return ejbRuleListFacade.findByRuleNo(ruleNo);
    }

    /*
     * 自定義欄位範圍代碼autocomplete select
     */
    public void onItemSelect(SelectEvent event) {
        this.currentItem = ejbRuleListFacade.find(event.getObject().toString());
    }

    /*
     * 新增分子條件(＋)
     */
    public void addDividendList(ActionEvent event) {
        this.ruleDividendList.add(new RuleDividend());
    }

    /*
     * 移除分子條件(－)
     */
    public void removeDividendList(RuleDividend d) {
        this.ruleDividendList.remove(d);
    }

    /*
     * 新增分母條件(＋)
     */
    public void addDivisorList(ActionEvent event) {
        this.ruleDivisorList.add(new RuleDivisor());
    }

    /*
     * 移除分母條件(－)
     */
    public void removeDivisorList(RuleDivisor d) {
        this.ruleDivisorList.remove(d);
    }

    /*
     * 遍歷Master
     */
    public void lookupMaster(int target) {
        int nextIndex = currentIndex + target;
        switch (target) {
            case 0:
                this.currentItem = this.master.get(0);
                currentIndex = 0;
                break;
            case -1:
                if (nextIndex <= 0) {
                    this.currentItem = this.master.get(0);
                    currentIndex = 0;
                } else {
                    this.currentItem = this.master.get(nextIndex);
                    currentIndex = nextIndex;
                }
                break;
            case 1:
                if (nextIndex >= this.master.size()) {
                    this.currentItem = this.master.get(this.master.size() - 1);
                    currentIndex = this.master.size() - 1;
                } else {
                    this.currentItem = this.master.get(nextIndex);
                    currentIndex = nextIndex;
                }
                break;
            default:
                nextIndex = this.master.size() - 1;
                this.currentItem = this.master.get(nextIndex);
                currentIndex = nextIndex;
        }
        genTempList();
    }

    /*
     * 點擊新增
     */
    public void create() {
        this.item = new RuleList();
        this.editDialogLabel = "新增";
        ruleDividendList.clear();
        ruleDivisorList.clear();
    }

    /*
     * 確認新增
     */
    public void save(ActionEvent event) {
        if (this.ruleDividendList.isEmpty()) {
            addMessage("請新增最少一筆分子條件", "請新增最少一筆分子條件");
        } else if (this.item.getDivisorSource().intValue() == 2 && this.ruleDivisorList.isEmpty()) {
            addMessage("請新增最少一筆分母條件", "請新增最少一筆分母條件");
        } else {
            if (StringUtils.isBlank(this.item.getRuleNo())) {// 新增
                String ruleNo = getWorkSeq(SeqTypeEnum.FUND_CODE.toString());
                this.item.setRuleNo(ruleNo);
                ejbWorkSeqFacade.updateWorkSeq(SeqTypeEnum.FUND_CODE.toString());
                addMessage("新增成功", "新增成功");
                currentIndex = this.master.size() - 1;
            } else {// 修改
                addMessage("更新成功", "更新成功");
            }
            ejbRuleListFacade.removeCascade(this.item.getRuleNo(), false);
            updateRuleRelateTable(this.item.getRuleNo());
            this.item.setLogUserId(this.userSession.getUser().getEmpId());
            this.item.setLogDttm(new Date());
            this.item.setIsLock((short) (this.item.isLock() ? 1 : 0));
            saveDivide();
            ejbRuleListFacade.save(this.item);
            getRenewMaster();
            this.currentItem = this.master.get(currentIndex);
            closeDialog();
        }
        genTempList();
    }

    public void selectAllRuleProduct(ActionEvent event) {
        tempRuleProduct = new Object[ruleProductList.size()];
        for (int i = 0; i < ruleProductList.size(); i++) {
            tempRuleProduct[i] = ruleProductList.get(i).getValue();
        }
    }

    public void selectAllRuleTradeType(ActionEvent event) {
        tempRuleTradeType = new Object[ruleTradeTypeList.size()];
        for (int i = 0; i < ruleTradeTypeList.size(); i++) {
            tempRuleTradeType[i] = ruleTradeTypeList.get(i).getValue();
        }

    }

    public void selectAllRuleChannel(ActionEvent event) {
        tempRuleChannel = new Object[ruleChannelList.size()];
        for (int i = 0; i < ruleChannelList.size(); i++) {
            tempRuleChannel[i] = ruleChannelList.get(i).getValue();
        }

    }
    //
    // public void selectAllRuleChecktime(ActionEvent event) {
    // tempRuleCheckTime = new Object[ruleChecktimeList.size()];
    // for (int i = 0; i < ruleChecktimeList.size(); i++) {
    // tempRuleCheckTime[i] = ruleChecktimeList.get(i).getValue();
    // }
    //
    // }

    /*
     * 點擊修改
     */
    public void edit() {
        this.editDialogLabel = "修改";
        this.item = this.currentItem;
        genTempList();
        genRuleSelectItem();
    }

    /*
     * 點擊刪除
     */
    public void delete() {
        ejbRuleListFacade.removeCascade(this.currentItem.getRuleNo(), true);
        addMessage("刪除成功", "刪除成功");
        this.strTempRuleChannel = "";
        this.strTempRuleCheckTime = "";
        this.strTempRuleOrderPage = "";
        this.strTempRuleOrderColumn = "";
        this.strTempRuleProduct = "";
        this.strTempRuleTradeType = "";
        this.init();
    }

    public void clearList(ActionEvent event) {
        this.item = new RuleList();
        tempRuleProduct = new Object[0];
        tempRuleTradeType = new Object[0];
        tempRuleChannel = new Object[0];
        tempRuleCheckTime = new Object[0];
        tempRuleOrderPage = new Object[0];
        tempRuleOrderColumn = new Object[0];
        this.init();
    }

    public UserSession getUserSession() {
        return userSession;
    }

    public void setUserSession(UserSession userSession) {
        this.userSession = userSession;
    }

    public List<RuleList> getMaster() {
        return master;
    }

    public void setMaster(List<RuleList> master) {
        this.master = master;
    }

    public RuleList getItem() {
        return item;
    }

    public void setItem(RuleList item) {
        this.item = item;
    }

    public RuleList getCurrentItem() {
        return currentItem;
    }

    public void setCurrentItem(RuleList currentItem) {
        this.currentItem = currentItem;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    public String getEditDialogLabel() {
        return editDialogLabel;
    }

    public void setEditDialogLabel(String editDialogLabel) {
        this.editDialogLabel = editDialogLabel;
    }

    public List<SelectItem> getRuleClassList() {
        return ruleClassList;
    }

    public void setRuleClassList(List<SelectItem> ruleClassList) {
        this.ruleClassList = ruleClassList;
    }

    public List<SelectItem> getRuleProductList() {
        return ruleProductList;
    }

    public void setRuleProductList(List<SelectItem> ruleProductList) {
        this.ruleProductList = ruleProductList;
    }

    public List<SelectItem> getRuleTradeTypeList() {
        return ruleTradeTypeList;
    }

    public void setRuleTradeTypeList(List<SelectItem> ruleTradeTypeList) {
        this.ruleTradeTypeList = ruleTradeTypeList;
    }

    public List<SelectItem> getRuleChannelList() {
        return ruleChannelList;
    }

    public void setRuleChannelList(List<SelectItem> ruleChannelList) {
        this.ruleChannelList = ruleChannelList;
    }

    public List<SelectItem> getRuleChecktimeList() {
        return ruleChecktimeList;
    }

    public void setRuleChecktimeList(List<SelectItem> ruleChecktimeList) {
        this.ruleChecktimeList = ruleChecktimeList;
    }

    public List<SelectItem> getRuleOrderPageList() {
        return ruleOrderPageList;
    }

    public void setRuleOrderPageList(List<SelectItem> ruleOrderPageList) {
        this.ruleOrderPageList = ruleOrderPageList;
    }

    public List<SelectItem> getRuleOrderColumnList() {
        return ruleOrderColumnList;
    }

    public void setRuleOrderColumnList(List<SelectItem> ruleOrderColumnList) {
        this.ruleOrderColumnList = ruleOrderColumnList;
    }

    public List<SelectItem> getClientAggregateList() {
        return clientAggregateList;
    }

    public void setClientAggregateList(List<SelectItem> clientAggregateList) {
        this.clientAggregateList = clientAggregateList;
    }

    public List<SelectItem> getCheckColumnList() {
        return checkColumnList;
    }

    public void setCheckColumnList(List<SelectItem> checkColumnList) {
        this.checkColumnList = checkColumnList;
    }

    public List<SelectItem> getDividendAggregateList() {
        return dividendAggregateList;
    }

    public void setDividendAggregateList(List<SelectItem> dividendAggregateList) {
        this.dividendAggregateList = dividendAggregateList;
    }

    public List<SelectItem> getDividsorSourceList() {
        return dividsorSourceList;
    }

    public void setDividsorSourceList(List<SelectItem> dividsorSourceList) {
        this.dividsorSourceList = dividsorSourceList;
    }

    public List<SelectItem> getLimitConditionList() {
        return limitConditionList;
    }

    public void setLimitConditionList(List<SelectItem> limitConditionList) {
        this.limitConditionList = limitConditionList;
    }

    public List<SelectItem> getLimitReactionList() {
        return limitReactionList;
    }

    public void setLimitReactionList(List<SelectItem> limitReactionList) {
        this.limitReactionList = limitReactionList;
    }

    public Object[] getTempRuleProduct() {
        return tempRuleProduct;
    }

    public void setTempRuleProduct(Object[] tempRuleProduct) {
        this.tempRuleProduct = tempRuleProduct;
    }

    public Object[] getTempRuleTradeType() {
        return tempRuleTradeType;
    }

    public void setTempRuleTradeType(Object[] tempRuleTradeType) {
        this.tempRuleTradeType = tempRuleTradeType;
    }

    public Object[] getTempRuleChannel() {
        return tempRuleChannel;
    }

    public void setTempRuleChannel(Object[] tempRuleChannel) {
        this.tempRuleChannel = tempRuleChannel;
    }

    public Object[] getTempRuleCheckTime() {
        return tempRuleCheckTime;
    }

    public void setTempRuleCheckTime(Object[] tempRuleCheckTime) {
        this.tempRuleCheckTime = tempRuleCheckTime;
    }

    public Object[] getTempRuleOrderPage() {
        return tempRuleOrderPage;
    }

    public void setTempRuleOrderPage(Object[] tempRuleOrderPage) {
        this.tempRuleOrderPage = tempRuleOrderPage;
    }

    public Object[] getTempRuleOrderColumn() {
        return tempRuleOrderColumn;
    }

    public void setTempRuleOrderColumn(Object[] tempRuleOrderColumn) {
        this.tempRuleOrderColumn = tempRuleOrderColumn;
    }

    public String getStrTempRuleProduct() {
        return strTempRuleProduct;
    }

    public void setStrTempRuleProduct(String strTempRuleProduct) {
        this.strTempRuleProduct = strTempRuleProduct;
    }

    public String getStrTempRuleTradeType() {
        return strTempRuleTradeType;
    }

    public void setStrTempRuleTradeType(String strTempRuleTradeType) {
        this.strTempRuleTradeType = strTempRuleTradeType;
    }

    public String getStrTempRuleChannel() {
        return strTempRuleChannel;
    }

    public void setStrTempRuleChannel(String strTempRuleChannel) {
        this.strTempRuleChannel = strTempRuleChannel;
    }

    public String getStrTempRuleCheckTime() {
        return strTempRuleCheckTime;
    }

    public void setStrTempRuleCheckTime(String strTempRuleCheckTime) {
        this.strTempRuleCheckTime = strTempRuleCheckTime;
    }

    public String getStrTempRuleOrderPage() {
        return strTempRuleOrderPage;
    }

    public void setStrTempRuleOrderPage(String strTempRuleOrderPage) {
        this.strTempRuleOrderPage = strTempRuleOrderPage;
    }

    public String getStrTempRuleOrderColumn() {
        return strTempRuleOrderColumn;
    }

    public void setStrTempRuleOrderColumn(String strTempRuleOrderColumn) {
        this.strTempRuleOrderColumn = strTempRuleOrderColumn;
    }

    public List<RuleDividend> getRuleDividendList() {
        return ruleDividendList;
    }

    public void setRuleDividendList(List<RuleDividend> ruleDividendList) {
        this.ruleDividendList = ruleDividendList;
    }

    public List<RuleDivisor> getRuleDivisorList() {
        return ruleDivisorList;
    }

    public void setRuleDivisorList(List<RuleDivisor> ruleDivisorList) {
        this.ruleDivisorList = ruleDivisorList;
    }

    public List<SelectItem> getScopeCodeMenu() {
        return scopeCodeMenu;
    }

    public void setScopeCodeMenu(List<SelectItem> scopeCodeMenu) {
        this.scopeCodeMenu = scopeCodeMenu;
    }

    private void saveDivide() {
        if (!this.ruleDividendList.isEmpty()) {
            ejbRuleDividendFacade.removeByRuleNo(this.item.getRuleNo());
            for (int i = 0; i < ruleDividendList.size(); i++) {
                RuleDividendPK pk = new RuleDividendPK(this.item.getRuleNo(), (short) (i + 1));
                RuleDividend entity = ruleDividendList.get(i);
                entity.setRuleDividendPK(pk);
                entity.setLogUserId(this.userSession.getUser().getEmpId());
                entity.setLogDttm(new Date());
                ejbRuleDividendFacade.save(entity);
            }
        }
        ejbRuleDivisorFacade.removeByRuleNo(this.item.getRuleNo());
        for (int i = 0; i < ruleDivisorList.size(); i++) {
            RuleDivisorPK pk = new RuleDivisorPK(this.item.getRuleNo(), (short) (i + 1));
            RuleDivisor entity = ruleDivisorList.get(i);
            entity.setRuleDivisorPK(pk);
            entity.setLogUserId(this.userSession.getUser().getEmpId());
            entity.setLogDttm(new Date());
            ejbRuleDivisorFacade.save(entity);
        }
    }

    private void genTempList() {
        List<String> ruleProductList = ejbRuleProductFacade.findItemNameByRuleNo(this.currentItem.getRuleNo());
        this.strTempRuleProduct = String.join(" ；　", ruleProductList);
        List<String> ruleTradeTypeList = ejbRuleTradeTypeFacade.findItemNameByRuleNo(this.currentItem.getRuleNo());
        this.strTempRuleTradeType = String.join(" ；　", ruleTradeTypeList);
        List<String> ruleChannelList = ejbRuleChannelFacade.findItemNameByRuleNo(this.currentItem.getRuleNo());
        this.strTempRuleChannel = String.join(" ；　", ruleChannelList);
        List<String> ruleChecktimeList = ejbRuleChecktimeFacade.findItemNameByRuleNo(this.currentItem.getRuleNo());
        this.strTempRuleCheckTime = String.join(" ；　", ruleChecktimeList);
        List<String> ruleOrderPageList = ejbRuleOrderPageFacade.findItemNameByRuleNo(this.currentItem.getRuleNo());
        this.strTempRuleOrderPage = String.join(" ；　", ruleOrderPageList);
        List<String> ruleOrderColumnList = ejbRuleOrderColumnFacade.findItemNameByRuleNo(this.currentItem.getRuleNo());
        this.strTempRuleOrderColumn = String.join(" ；　", ruleOrderColumnList);
        this.currentItem.setLock(this.currentItem.getIsLock() == 1 ? true : false);
        this.ruleDividendList = ejbRuleDividendFacade.findByRuleNo(this.currentItem.getRuleNo());
        this.ruleDivisorList = ejbRuleDivisorFacade.findByRuleNo(this.currentItem.getRuleNo());
    }

    private void genRuleSelectItem() {
        List<Short> prdCodeList = ejbRuleProductFacade.findByRuleNo(this.item.getRuleNo());
        List<Short> tradeTypeList = ejbRuleTradeTypeFacade.findByRuleNo(this.item.getRuleNo());
        List<Short> channelCodeList = ejbRuleChannelFacade.findByRuleNo(this.item.getRuleNo());
        List<Short> checktimeList = ejbRuleChecktimeFacade.findByRuleNo(this.item.getRuleNo());
        List<Short> orderPageList = ejbRuleOrderPageFacade.findByRuleNo(this.item.getRuleNo());
        List<Short> orderColumnList = ejbRuleOrderColumnFacade.findByRuleNo(this.item.getRuleNo());
        tempRuleProduct = new Object[prdCodeList.size()];
        for (int i = 0; i < prdCodeList.size(); i++) {
            tempRuleProduct[i] = prdCodeList.get(i);
        }
        tempRuleTradeType = new Object[tradeTypeList.size()];
        for (int i = 0; i < tradeTypeList.size(); i++) {
            tempRuleTradeType[i] = tradeTypeList.get(i);
        }
        tempRuleChannel = new Object[channelCodeList.size()];
        for (int i = 0; i < channelCodeList.size(); i++) {
            tempRuleChannel[i] = channelCodeList.get(i);
        }
        tempRuleCheckTime = new Object[checktimeList.size()];
        for (int i = 0; i < checktimeList.size(); i++) {
            tempRuleCheckTime[i] = checktimeList.get(i);
        }
        tempRuleOrderPage = new Object[orderPageList.size()];
        for (int i = 0; i < orderPageList.size(); i++) {
            tempRuleOrderPage[i] = orderPageList.get(i);
        }
        tempRuleOrderColumn = new Object[orderColumnList.size()];
        for (int i = 0; i < orderColumnList.size(); i++) {
            tempRuleOrderColumn[i] = orderColumnList.get(i);
        }
    }

    private void getRenewMaster() {
        this.master = ejbRuleListFacade.findAllSort();
    }

    private void updateRuleRelateTable(String ruleNo) {
        for (Object o : tempRuleProduct) {
            RuleProductPK pk = new RuleProductPK(ruleNo, Short.valueOf(o.toString()));
            RuleProduct rp = new RuleProduct();
            rp.setRuleProductPK(pk);
            rp.setLogDttm(new Date());
            rp.setLogUserId(this.userSession.getUser().getEmpId());
            ejbRuleProductFacade.create(rp);
        }
        for (Object o : tempRuleTradeType) {
            RuleTradeTypePK pk = new RuleTradeTypePK(ruleNo, Short.valueOf(o.toString()));
            RuleTradeType rt = new RuleTradeType();
            rt.setRuleTradeTypePK(pk);
            rt.setLogDttm(new Date());
            rt.setLogUserId(this.userSession.getUser().getEmpId());
            ejbRuleTradeTypeFacade.create(rt);
        }
        for (Object o : tempRuleChannel) {
            RuleChannelPK pk = new RuleChannelPK(ruleNo, Short.valueOf(o.toString()));
            RuleChannel rc = new RuleChannel();
            rc.setRuleChannelPK(pk);
            rc.setLogDttm(new Date());
            rc.setLogUserId(this.userSession.getUser().getEmpId());
            ejbRuleChannelFacade.create(rc);
        }
        for (Object o : tempRuleCheckTime) {
            RuleChecktimePK pk = new RuleChecktimePK(ruleNo, Short.valueOf(o.toString()));
            RuleChecktime rc = new RuleChecktime();
            rc.setRuleChecktimePK(pk);
            rc.setLogDttm(new Date());
            rc.setLogUserId(this.userSession.getUser().getEmpId());
            ejbRuleChecktimeFacade.create(rc);
        }
        for (Object o : tempRuleOrderPage) {
            RuleOrderPagePK pk = new RuleOrderPagePK(ruleNo, Long.valueOf(o.toString()));
            RuleOrderPage rc = new RuleOrderPage();
            rc.setId(pk);
            rc.setLogDttm(new Date());
            rc.setLogUserId(this.userSession.getUser().getEmpId());
            ejbRuleOrderPageFacade.create(rc);
        }
        for (Object o : tempRuleOrderColumn) {
            RuleOrderColumnPK pk = new RuleOrderColumnPK(ruleNo, Short.valueOf(o.toString()));
            RuleOrderColumn rc = new RuleOrderColumn();
            rc.setId(pk);
            rc.setLogDttm(new Date());
            rc.setLogUserId(this.userSession.getUser().getEmpId());
            ejbRuleOrderColumnFacade.create(rc);
        }
    }

    private void genAllItemList(List<RdOptionItem> allItem) {
        for (RdOptionItem rd : allItem) {
            switch (rd.getRdOptionItemPK().getClassCode()) {
                case 11:
                    this.ruleClassList.add(new SelectItem(rd.getRdOptionItemPK().getItemCode(), rd.getItemName()));
                    break;
                case 1:
                    this.ruleProductList.add(new SelectItem(rd.getRdOptionItemPK().getItemCode(), rd.getItemName()));
                    break;
                case 2:
                    this.ruleTradeTypeList.add(new SelectItem(rd.getRdOptionItemPK().getItemCode(), rd.getItemName()));
                    break;
                case 3:
                    this.ruleChannelList.add(new SelectItem(rd.getRdOptionItemPK().getItemCode(), rd.getItemName()));
                    break;
                case 4:
                    this.ruleChecktimeList.add(new SelectItem(rd.getRdOptionItemPK().getItemCode(), rd.getItemName()));
                    break;
                case 14:
                    this.ruleOrderPageList.add(new SelectItem(rd.getRdOptionItemPK().getItemCode(), rd.getItemName()));
                    break;
                case 15:
                    this.ruleOrderColumnList
                        .add(new SelectItem(rd.getRdOptionItemPK().getItemCode(), rd.getItemName()));
                    break;
                case 5:
                    this.clientAggregateList
                        .add(new SelectItem(rd.getRdOptionItemPK().getItemCode(), rd.getItemName()));
                    break;
                case 6:
                    this.checkColumnList.add(new SelectItem(rd.getRdOptionItemPK().getItemCode(), rd.getItemName()));
                    break;
                case 7:
                    this.dividendAggregateList
                        .add(new SelectItem(rd.getRdOptionItemPK().getItemCode(), rd.getItemName()));
                    break;
                case 8:
                    this.dividsorSourceList
                        .add(new SelectItem(rd.getRdOptionItemPK().getItemCode(), rd.getItemName()));
                    break;
                case 9:
                    this.limitConditionList
                        .add(new SelectItem(rd.getRdOptionItemPK().getItemCode(), rd.getItemName()));
                    break;
                case 10:
                    this.limitReactionList.add(new SelectItem(rd.getRdOptionItemPK().getItemCode(), rd.getItemName()));
                    break;
                default:
                    break;
            }
        }
    }

    /*
     * 取得自定義欄位範圍代碼
     */
    private String getWorkSeq(String code) {
        String seqType = SeqTypeEnum.valueOf(code).getCode();
        Short no = ejbWorkSeqFacade.getWorkSeqNo(code);
        return seqType + StringUtils.leftPad(String.valueOf(no + 1), 4, "0");
    }

    private void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
