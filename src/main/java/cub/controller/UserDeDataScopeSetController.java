package cub.controller;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.apache.commons.lang.StringUtils;
import org.primefaces.event.SelectEvent;

import cub.entities.ApiMaster;
import cub.entities.RdDataClass;
import cub.entities.TrMaster;
import cub.entities.TrOptionItem;
import cub.entities.TrOptionItemPK;
import cub.entities.TrParameterInfo;
import cub.entities.UdDataScopeDetail;
import cub.entities.UdDataScopeDetailPK;
import cub.entities.UdDataScopeMaster;
import cub.enums.SeqTypeEnum;
import cub.facade.ApiMasterFacade;
import cub.facade.RdDataClassFacade;
import cub.facade.RdOptionItemFacade;
import cub.facade.TrMasterFacade;
import cub.facade.TrOptionItemFacade;
import cub.facade.TrParameterInfoFacade;
import cub.facade.UdDataScopeDetailFacade;
import cub.facade.UdDataScopeMasterFacade;
import cub.facade.WorkSeqFacade;
import cub.sso.UserSession;

/**
 * @author F123669 自定義資料範圍設定作業(RCMM02)
 *
 */
@ManagedBean(name = "userDeDataScopeSetController")
@ViewScoped
public class UserDeDataScopeSetController extends AbstractController implements Serializable {
    @ManagedProperty("#{userSession}")
    private UserSession userSession;
    @EJB
    private WorkSeqFacade ejbWorkSeqFacade;
    @EJB
    private UdDataScopeMasterFacade ejbUdDataScopeMasterFacade;
    @EJB
    private UdDataScopeDetailFacade ejbUdDataScopeDetailFacade;
    @EJB
    private RdDataClassFacade ejbRdDataClassFacade;
    @EJB
    private TrMasterFacade ejbTrMasterFacade;
    @EJB
    private ApiMasterFacade ejbApiMasterFacade;
    @EJB
    private TrParameterInfoFacade ejbTrParameterInfoFacade;
    @EJB
    private RdOptionItemFacade ejbRdOptionItemFacade;
    @EJB
    private TrOptionItemFacade ejbTrOptionItemFacade;
    /*
     * 自定義資料範圍列表
     */
    private List<UdDataScopeMaster> master;
    /*
     * 資料條件設定區
     */
    private List<UdDataScopeDetail> detail;
    /*
     * 新增/修改
     */
    private UdDataScopeMaster item;
    /*
     * 新增/修改
     */
    private List<UdDataScopeDetail> itemDetail;
    /*
     * 新增/修改資料類別下拉選單
     */
    private List<SelectItem> itemDataTypeMenu;
    /*
     * 新增/修改API名稱下拉選單
     */
    private List<SelectItem> itemApiMasterMenu;
    /*
     * 參數名稱下拉選單
     */
    private List<SelectItem> itemParaMenu;
    /*
     * 關係下拉選單
     */
    private List<SelectItem> itemOpCodeMenu;
    /*
     * 待修改自定義欄位範圍
     */
    private UdDataScopeMaster currentItem;
    /*
     * API型態
     */
    private BigDecimal rtnType;
    /*
     * 自定義欄位範圍索引
     */
    private int currentIndex;
    /*
     * 新增/修改Dialog CommandButton value
     */
    private String editDialogLabel = "新增";

    @PostConstruct
    public void init() {
        this.checkSession(userSession);
        this.master = new ArrayList<UdDataScopeMaster>();
        this.master = ejbUdDataScopeMasterFacade.findAllSort();
        this.currentItem = new UdDataScopeMaster();
        this.item = new UdDataScopeMaster();
        if (this.master.isEmpty()) {
            this.master.add(this.item);
        } else {
            this.currentItem = this.master.get(0);
            refreshTrCode(this.currentItem.getApiCode());
        }
        // 頁面載入自定義欄位this.master的index
        currentIndex = 0;
        // initial新增/修改時資料類別下拉選單
        genDataTypeMenu();
        // initial新增/修改時API下拉選單
        genAPIMasterMenu();

        // initial新增/修改時關係下拉選單
        genOpCodeMenu();
        // 載入this.currentItem內容
        setItemDetail();
    }

    /*
     * API代碼名稱改變時連動資料條件設定區
     */
    public void onAPIMenuChange(ValueChangeEvent e) {
        String apiCode = e.getNewValue().toString();
        refreshTrCode(apiCode);
    }

    /*
     * 參數名稱變更時連動參數說明
     */
    public void onParaMenuChange(ValueChangeEvent e) {
        String newId = e.getNewValue().toString();
        String oldId = null == e.getOldValue() ? "" : e.getOldValue().toString();
        for (int i = 0; i < this.itemDetail.size(); i++) {
            UdDataScopeDetail de = this.itemDetail.get(i);
            if (StringUtils.isBlank(de.getParameterName())
                    || de.getParameterName().equals(StringUtils.split(oldId, "+")[0])) {
                de.setParameterName(StringUtils.split(newId, "+")[0]);
                String desc = ejbTrParameterInfoFacade.findDescByParaName(StringUtils.split(newId, "+")[1]);

                genOpValueMenu(de, newId);

                de.setParameterDesc(desc);
                this.itemDetail.set(i, de);
            }
        }
    }

    private void genOpValueMenu(UdDataScopeDetail de, String newId) {
        String[] id = StringUtils.split(newId, "+");
        List<TrOptionItem> allItem = ejbTrOptionItemFacade.findByCodeName(id[0], id[1]);
        List<SelectItem> selItem = new ArrayList<SelectItem>();
        for (TrOptionItem tr : allItem) {
            selItem.add(new SelectItem(tr.getId().getItemCode(), tr.getItemName()));
        }
        de.setOpValueMenu(selItem);
    }

    public void onOpValueChange(ValueChangeEvent e) {
        String newValue = e.getNewValue().toString();
        String oldValue = null == e.getOldValue() ? "" : e.getOldValue().toString();
        for (int i = 0; i < this.itemDetail.size(); i++) {
            UdDataScopeDetail de = this.itemDetail.get(i);
            if (StringUtils.isBlank(de.getOpValue())
                    || de.getOpValue().equals(oldValue)) {
                if (StringUtils.isNotBlank(de.getParaId())) {
                    String[] split = StringUtils.split(de.getParaId(), "+");
                    TrOptionItemPK id = new TrOptionItemPK(split[0], split[1], newValue);
                    TrOptionItem desc = ejbTrOptionItemFacade.find(id);
                    de.setOpValueDesc(desc.getItemName());
                    this.itemDetail.set(i, de);
                } else {
                    addMessage("請先輸入API代碼/名稱或參數名稱", "請先輸入API代碼/名稱或參數名稱");
                }
            }
        }
    }

    /*
     * 自定義欄位範圍代碼autocomplete
     */
    public List<String> searchColumnScopeMaster(String code) {
        return ejbUdDataScopeMasterFacade.findByCode(StringUtils.upperCase(code));
    }

    /*
     * 自定義欄位範圍代碼autocomplete select
     */
    public void onItemSelect(SelectEvent event) {
        UdDataScopeMaster udm = ejbUdDataScopeMasterFacade.find(event.getObject().toString());
        this.currentItem = udm;
        setItemDetail();

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
        setItemDetail();
    }

    /*
     * 點擊新增
     */
    public void create() {
        this.item = new UdDataScopeMaster();
        this.itemDetail = new ArrayList<UdDataScopeDetail>();
        this.editDialogLabel = "新增";
    }

    /*
     * 確認新增
     */
    public void save(ActionEvent event) {
        if ((null != this.rtnType) && (this.rtnType.compareTo(new BigDecimal("1")) == 0)) {
            if (this.itemDetail.isEmpty()) {
                addMessage("TR回傳參數至少需要一筆", "TR回傳參數至少需要一筆");
            } else {
                saveMethod();
            }
        } else if ((null != this.rtnType) && (this.rtnType.compareTo(new BigDecimal("1")) != 0)) {
            saveMethod();
        }
    }

    /*
     * 點擊修改
     */
    public void edit() {
        this.editDialogLabel = "修改";
        this.item = this.currentItem;
        this.itemDetail = ejbUdDataScopeDetailFacade.findByScopeCode(this.item.getScopeCode());
        for (UdDataScopeDetail ud : this.itemDetail) {
            ud.setOpCodeMenu(this.itemOpCodeMenu);
            ud.setParaMenu(this.itemParaMenu);
            String name = ejbTrParameterInfoFacade.findNameByCodeDesc(ud.getParameterName(), ud.getParameterDesc());
            ud.setParaId(ud.getParameterName() + "+" + name);
        }
    }

    /*
     * 點擊刪除
     */
    public void delete() {
        ejbUdDataScopeMasterFacade.remove(this.currentItem);
        ejbUdDataScopeDetailFacade.removeByMaster(this.currentItem.getScopeCode());
        addMessage("刪除成功", "刪除成功");
        this.init();
    }

    /*
     * 新增資料範圍(＋)
     */
    public void addDetail(ActionEvent event) {
        if (null == this.itemParaMenu || this.itemParaMenu.isEmpty()) {
            addMessage("請先選定API代碼/名稱", "請先選定API代碼/名稱");
        } else {
            UdDataScopeDetail tempDetail = new UdDataScopeDetail();
            tempDetail.setOpCodeMenu(this.itemOpCodeMenu);
            tempDetail.setParaMenu(this.itemParaMenu);
            this.itemDetail.add(tempDetail);
        }
    }

    /*
     * 移除資料範圍(－)
     */
    public void removeDetail(UdDataScopeDetail d) {
        this.itemDetail.remove(d);
    }

    /*
     * 點擊搜尋
     */
    public void search(ActionEvent event) {

    }

    public List<UdDataScopeMaster> getMaster() {
        return master;
    }

    public void setMaster(List<UdDataScopeMaster> master) {
        this.master = master;
    }

    public UdDataScopeMaster getItem() {
        return item;
    }

    public void setItem(UdDataScopeMaster item) {
        this.item = item;
    }

    public UdDataScopeMaster getCurrentItem() {
        return currentItem;
    }

    public void setCurrentItem(UdDataScopeMaster currentItem) {
        this.currentItem = currentItem;
    }

    public List<UdDataScopeDetail> getDetail() {
        return detail;
    }

    public void setDetail(List<UdDataScopeDetail> detail) {
        this.detail = detail;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    public List<UdDataScopeDetail> getItemDetail() {
        return itemDetail;
    }

    public void setItemDetail(List<UdDataScopeDetail> itemDetail) {
        this.itemDetail = itemDetail;
    }

    public List<SelectItem> getItemApiMasterMenu() {
        return itemApiMasterMenu;
    }

    public void setItemApiMasterMenu(List<SelectItem> itemApiMasterMenu) {
        this.itemApiMasterMenu = itemApiMasterMenu;
    }

    public List<SelectItem> getItemDataTypeMenu() {
        return itemDataTypeMenu;
    }

    public void setItemDataTypeMenu(List<SelectItem> itemDataTypeMenu) {
        this.itemDataTypeMenu = itemDataTypeMenu;
    }

    public String getEditDialogLabel() {
        return editDialogLabel;
    }

    public void setEditDialogLabel(String editDialogLabel) {
        this.editDialogLabel = editDialogLabel;
    }

    public UserSession getUserSession() {
        return userSession;
    }

    public void setUserSession(UserSession userSession) {
        this.userSession = userSession;
    }

    public List<SelectItem> getItemParaMenu() {
        return itemParaMenu;
    }

    public void setItemParaMenu(List<SelectItem> itemParaMenu) {
        this.itemParaMenu = itemParaMenu;
    }

    public List<SelectItem> getItemOpCodeMenu() {
        return itemOpCodeMenu;
    }

    public void setItemOpCodeMenu(List<SelectItem> itemOpCodeMenu) {
        this.itemOpCodeMenu = itemOpCodeMenu;
    }

    public BigDecimal getRtnType() {
        return rtnType;
    }

    public void setRtnType(BigDecimal rtnType) {
        this.rtnType = rtnType;
    }

    private void refreshTrCode(String apiCode) {
        ApiMaster apiMaster = ejbApiMasterFacade.find(apiCode);
        TrMaster tm = ejbTrMasterFacade.findByTrCode(apiMaster.getOutputTrCode());
        this.item.setApiName(apiMaster.getApiDesc());
        this.item.setTrCode(tm.getTrCode());
        this.item.setTrDesc(tm.getTrDesc());
        this.currentItem.setApiName(apiMaster.getApiDesc());
        this.currentItem.setTrCode(tm.getTrCode());
        this.currentItem.setTrDesc(tm.getTrDesc());
        this.rtnType = apiMaster.getRtnType();
        // initial新增/修改時參數名稱下拉選單
        genParaNameMenu();
    }

    private void saveMethod() {
        if (StringUtils.isBlank(this.item.getScopeCode())) {// 新增
            String scopeCode = getWorkSeq(SeqTypeEnum.UDDATA_CODE.toString());
            this.item.setScopeCode(scopeCode);
            ejbWorkSeqFacade.updateWorkSeq(SeqTypeEnum.UDDATA_CODE.toString());
        }
        this.item.setLogDttm(new Date());
        this.item.setLogUserId(this.userSession.getUser().getEmpId());
        ejbUdDataScopeMasterFacade.save(this.item);
        ejbUdDataScopeDetailFacade.removeByMaster(this.item.getScopeCode());
        short i = 1;
        for (UdDataScopeDetail d : this.itemDetail) {
            UdDataScopeDetailPK pk = new UdDataScopeDetailPK(this.item.getScopeCode(), i);
            d.setId(pk);
            d.setLogDttm(new Date());
            d.setLogUserId(this.userSession.getUser().getEmpId());
            ejbUdDataScopeDetailFacade.create(d);
            i++;
        }
        addMessage("新增成功", "新增成功");
        this.currentItem = this.master.get(this.currentIndex);
        this.init();
        setItemDetail();
        create();
    }

    private void genAPIMasterMenu() {
        this.itemApiMasterMenu = new ArrayList<SelectItem>();
        List<ApiMaster> allApiMaster = ejbApiMasterFacade.findAll();
        for (ApiMaster m : allApiMaster) {
            this.itemApiMasterMenu.add(new SelectItem(m.getApiCode(), m.getApiDesc()));
        }
    }

    private void genOpCodeMenu() {
        this.itemOpCodeMenu = new ArrayList<SelectItem>();
        List<Object[]> roi = ejbRdOptionItemFacade.findByClassCode((short) 9);
        for (Object[] o : roi) {
            this.itemOpCodeMenu.add(new SelectItem(o[0], o[1].toString()));
        }
    }

    private void genParaNameMenu() {
        this.itemParaMenu = new ArrayList<SelectItem>();
        List<TrParameterInfo> allTpInfo = ejbTrParameterInfoFacade.findByTrCode(this.item.getTrCode());
        for (TrParameterInfo tp : allTpInfo) {
            this.itemParaMenu.add(
                new SelectItem(tp.getId().getTrCode() + "+" + tp.getId().getParameterName(),
                        tp.getId().getParameterName()));
        }
    }

    private void genDataTypeMenu() {
        this.itemDataTypeMenu = new ArrayList<SelectItem>();
        List<RdDataClass> allDataTypes = ejbRdDataClassFacade.findAllSort();
        for (RdDataClass rd : allDataTypes) {
            this.itemDataTypeMenu.add(new SelectItem(rd.getClassCode(), rd.getClassName()));
        }
    }

    /*
     * 載入detail
     */
    private void setItemDetail() {
        String className = ejbRdDataClassFacade.getClassNameByClassCode(this.currentItem.getClassCode());
        this.currentItem.setClassName(className);
        this.detail = ejbUdDataScopeDetailFacade.findByScopeCode(this.currentItem.getScopeCode());
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
