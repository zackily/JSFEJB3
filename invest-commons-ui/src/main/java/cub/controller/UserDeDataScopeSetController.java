package cub.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.apache.commons.lang.StringUtils;
import org.primefaces.event.SelectEvent;

import cub.entities.RdDataClass;
import cub.entities.RdDataColumn;
import cub.entities.RdDataColumnPK;
import cub.entities.UdColumnScopeMaster;
import cub.entities.UdDataScopeDetail;
import cub.entities.UdDataScopeMaster;
import cub.entities.UdMethodDetail;
import cub.entities.UdMethodMaster;
import cub.enums.SeqTypeEnum;
import cub.facade.DataScopeMasterFacade;
import cub.facade.RdDataClassFacade;
import cub.facade.RdDataColumnFacade;
import cub.facade.UdColumnScopeMasterFacade;
import cub.facade.UdDataScopeDetailFacade;
import cub.facade.UdDataScopeMasterFacade;
import cub.facade.UdMethodDetailFacade;
import cub.facade.UdMethodMasterFacade;
import cub.facade.WorkSeqFacade;

@ManagedBean(name = "userDeDataScopeSetController")
@ViewScoped
public class UserDeDataScopeSetController implements Serializable {

    @EJB
    private WorkSeqFacade ejbWorkSeqFacade;
    @EJB
    private UdDataScopeMasterFacade ejbUdDataScopeMasterFacade;
    @EJB
    private UdDataScopeDetailFacade ejbUdDataScopeDetailFacade;
    @EJB
    private RdDataClassFacade ejbRdDataClassFacade;
    @EJB
    private UdMethodMasterFacade ejbUdMethodMasterFacade;
    @EJB
    private UdMethodDetailFacade ejbUdMethodDetailFacade;
    @EJB
    private DataScopeMasterFacade ejbDataScopeMasterFacade;
    @EJB
    private RdDataColumnFacade ejbRdDataColumnFacade;
    @EJB
    private UdColumnScopeMasterFacade ejbUdColumnScopeMasterFacade;
    /*
     * 自定義資料範圍列表
     */
    private List<UdDataScopeMaster> master;
    /*
     * 資料條件設定區
     */
    private List<UdDataScopeDetail> detail;
    /*
     * 新增/編輯
     */
    private UdDataScopeMaster item;
    /*
     * 新增/編輯
     */
    private List<UdDataScopeDetail> itemDetail;
    /*
     * 新增/編輯資料類別下拉選單
     */
    private List<SelectItem> itemDataTypeMenu;
    /*
     * 新增/編輯Method名稱下拉選單
     */
    private List<SelectItem> itemMethodNameMenu;
    /*
     * 新增/編輯回傳欄位下拉選單
     */
    private List<SelectItem> itemReturnFieldMenu;
    /*
     * 待編輯自定義欄位範圍
     */
    private UdDataScopeMaster currentItem;
    /*
     * 自定義欄位範圍索引
     */
    private int currentIndex;
    /*
     * 新增/編輯Dialog CommandButton value
     */
    private String editDialogLabel = "新增";
    /*
     * Method名稱暫存
     */
    private String tempMethodName;
    /*
     * 資料條件細節暫存
     */
    private List<String> tempDetailList;
    /*
     * 自定義欄位範圍(代碼)Menu
     */
    private List<SelectItem> userDeFieldScopeMenu;
    /*
     * 自定義欄位範圍暫存List
     */
    private List<String> tempUserDeFieldList;
    /*
     * 自定義欄位範圍暫存
     */
    private String userDeFieldTemp;

    @PostConstruct
    public void init() {
        this.master = new ArrayList<UdDataScopeMaster>();
        this.master = ejbUdDataScopeMasterFacade.findAllSort();
        this.currentItem = new UdDataScopeMaster();
        if (this.master.isEmpty()) {
            this.master.add(this.item);
        } else {
            this.currentItem = this.master.get(0);
            loadReturnFieldMenu(String.valueOf(this.currentItem.getClassCode()));
        }
        // 頁面載入自定義欄位this.master的index
        currentIndex = 0;
        // initial新增/編輯時資料類別下拉選單
        genDataTypeMenu();
        // initial新增/編輯時Method名稱下拉選單
        genMethodNameMenu();
        // initial新增/編輯時自定義欄位範圍(代碼)下拉選單
        genUdFieldScopeMasterMenu();
        // 載入this.currentItem內容
        setItemDetail();
    }

    /*
     * Method名稱改變時連動資料條件設定區
     */
    public void onMethodMenuChange() {
        this.detail.clear();
        List<UdMethodDetail> methodDetail = ejbUdMethodDetailFacade.findByMethodName(this.tempMethodName);
        for (UdMethodDetail dt : methodDetail) {
            UdDataScopeDetail de = new UdDataScopeDetail();
            de.setParameterName(dt.getParameterName());//參數名稱
            de.setParameterDesc(dt.getParameterDesc());//參數說明
//            de.setValue("");//對應值
//            de.setValueDesc("");//對應值說明
//            de.setUdColumnCode("");//自定義範圍
//            de.setUdColumnName("");//範圍名稱
            this.detail.add(de);
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
        this.editDialogLabel = "新增";
    }

    /*
     * 確認新增
     */
    public void save(ActionEvent event) {
        if (StringUtils.isBlank(this.item.getScopeCode())) {// 新增
            String scopeCode = getWorkSeq(SeqTypeEnum.UDDATA_CODE.toString());
            this.item.setScopeCode(scopeCode);
            ejbWorkSeqFacade.updateWorkSeq(SeqTypeEnum.UDDATA_CODE.toString());
        }
        ejbUdDataScopeMasterFacade.save(this.item);
        ejbUdDataScopeDetailFacade.removeByMaster(this.item.getScopeCode());
        for (UdDataScopeDetail d : this.itemDetail) {
            ejbUdDataScopeDetailFacade.create(d);
        }
        this.init();
        setItemDetail();
    }

    /*
     * 點擊修改
     */
    public void edit() {
        this.editDialogLabel = "編輯";
        this.item = this.currentItem;
        this.itemDetail = ejbUdDataScopeDetailFacade.findByScopeCode(this.item.getScopeCode());
    }

    /*
     * 點擊刪除
     */
    public void delete() {
        if (ejbDataScopeMasterFacade.checkRuleNoExistByScopeCode(this.currentItem.getScopeCode())) {
            addMessage("System Error", "此欄位範圍已經被引用,請移除該引用才可進行刪除!");
        } else {
            ejbUdDataScopeMasterFacade.remove(this.currentItem);
            ejbUdDataScopeDetailFacade.removeByMaster(this.currentItem.getScopeCode());
        }
        this.init();
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

    public List<SelectItem> getItemMethodNameMenu() {
        return itemMethodNameMenu;
    }

    public void setItemMethodNameMenu(List<SelectItem> itemMethodNameMenu) {
        this.itemMethodNameMenu = itemMethodNameMenu;
    }

    public List<SelectItem> getItemReturnFieldMenu() {
        return itemReturnFieldMenu;
    }

    public void setItemReturnFieldMenu(List<SelectItem> itemReturnFieldMenu) {
        this.itemReturnFieldMenu = itemReturnFieldMenu;
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

    public String getTempMethodName() {
        return tempMethodName;
    }

    public void setTempMethodName(String tempMethodName) {
        this.tempMethodName = tempMethodName;
    }

    public List<String> getTempDetailList() {
        return tempDetailList;
    }

    public void setTempDetailList(List<String> tempDetailList) {
        this.tempDetailList = tempDetailList;
    }

    public List<SelectItem> getUserDeFieldScopeMenu() {
        return userDeFieldScopeMenu;
    }

    public void setUserDeFieldScopeMenu(List<SelectItem> userDeFieldScopeMenu) {
        this.userDeFieldScopeMenu = userDeFieldScopeMenu;
    }

    public List<String> getTempUserDeFieldList() {
        return tempUserDeFieldList;
    }

    public void setTempUserDeFieldList(List<String> tempUserDeFieldList) {
        this.tempUserDeFieldList = tempUserDeFieldList;
    }

    public String getUserDeFieldTemp() {
        return userDeFieldTemp;
    }

    public void setUserDeFieldTemp(String userDeFieldTemp) {
        this.userDeFieldTemp = userDeFieldTemp;
    }

    private void genUdFieldScopeMasterMenu() {
        this.userDeFieldScopeMenu = new ArrayList<SelectItem>();
        List<UdColumnScopeMaster> allUdColumnMaster = ejbUdColumnScopeMasterFacade.findAllSort();
        for (UdColumnScopeMaster m : allUdColumnMaster) {
            this.userDeFieldScopeMenu.add(new SelectItem(m.getUdColumnCode() + "-" + m.getUdColumnName(), m.getUdColumnCode()));
        }
    }

    private void genMethodNameMenu() {
        this.itemMethodNameMenu = new ArrayList<SelectItem>();
        List<UdMethodMaster> allMethodMaster = ejbUdMethodMasterFacade.findAllSort();
        for (UdMethodMaster m : allMethodMaster) {
            this.itemMethodNameMenu.add(new SelectItem(m.getMethodName(), m.getMethodDesc()));
        }
    }

    private void genDataTypeMenu() {
        this.itemDataTypeMenu = new ArrayList<SelectItem>();
        List<RdDataClass> allDataTypes = ejbRdDataClassFacade.findAllSort();
        for (RdDataClass rd : allDataTypes) {
            this.itemDataTypeMenu.add(new SelectItem(rd.getClassCode(), rd.getClassName()));
        }
    }

    private void loadReturnFieldMenu(String classCode) {
        // initial新增/編輯時回傳欄位下拉選單
        this.itemReturnFieldMenu = new ArrayList<SelectItem>();
        List<RdDataColumn> list = ejbRdDataColumnFacade.getColumnByClassCode(classCode);
        for (RdDataColumn rd : list) {
            RdDataColumnPK pk = rd.getRdDataColumnPK();
            this.itemReturnFieldMenu
                .add(new SelectItem(pk.getClassCode() + "+" + pk.getTableName()
                        + "+" + pk.getColumnName(), rd.getColumnChnName()));
        }
    }

    /*
     * 載入detail
     */
    private void setItemDetail() {
        String code = this.currentItem.getScopeCode();
        this.detail = ejbUdDataScopeDetailFacade.findByScopeCode(code);
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
