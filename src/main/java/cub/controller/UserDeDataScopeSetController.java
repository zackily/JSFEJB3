package cub.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.apache.commons.lang.StringUtils;
import org.primefaces.event.SelectEvent;

import cub.entities.RdDataClass;
import cub.entities.RdDataColumn;
import cub.entities.RdDataColumnPK;
import cub.entities.UdColumnScopeMaster;
import cub.entities.UdDataScopeDetail;
import cub.entities.UdDataScopeDetailPK;
import cub.entities.UdDataScopeMaster;
import cub.entities.UdMethodDetail;
import cub.entities.UdMethodMaster;
import cub.enums.SeqTypeEnum;
import cub.facade.DataScopeMasterFacade;
import cub.facade.RdDataClassFacade;
import cub.facade.RdDataColumnFacade;
import cub.facade.RdOptionItemFacade;
import cub.facade.UdColumnScopeMasterFacade;
import cub.facade.UdDataScopeDetailFacade;
import cub.facade.UdDataScopeMasterFacade;
import cub.facade.UdMethodDetailFacade;
import cub.facade.UdMethodMasterFacade;
import cub.facade.WorkSeqFacade;
import cub.vo.QueryUdColumnScopeDetailVO;

/**
 * @author F123669 自定義資料範圍設定作業(RCMM02)
 *
 */
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
    @EJB
    private RdOptionItemFacade ejbRdOptionItemFacade;
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
     * 對應值/對應值說明
     */
    private Map<String, String> allItemCodeMap;
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
    private String tempUserDeField;
    /*
     * 回傳欄位暫存
     */
    private String tempRTNField;

    @PostConstruct
    public void init() {
        this.master = new ArrayList<UdDataScopeMaster>();
        this.master = ejbUdDataScopeMasterFacade.findAllSort();
        this.currentItem = new UdDataScopeMaster();
        this.item = new UdDataScopeMaster();
        if (this.master.isEmpty()) {
            this.master.add(this.item);
        } else {
            this.currentItem = this.master.get(0);
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
    public void onMethodMenuChange(ValueChangeEvent e) {
        this.itemDetail.clear();
        String name = e.getNewValue().toString();
        List<UdMethodDetail> methodDetail = ejbUdMethodDetailFacade.findByMethodName(name);
        this.allItemCodeMap = new HashMap<String, String>();
        for (UdMethodDetail dt : methodDetail) {
            UdDataScopeDetail de = new UdDataScopeDetail();
            de.setParameterName(dt.getParameterName());// 參數名稱
            de.setParameterDesc(dt.getParameterDesc());// 參數說明
            genRdOptionItemMenu(de);
            this.itemDetail.add(de);
        }
        this.item.setMethodNameDesc(ejbUdMethodMasterFacade.findDescByName(name));
    }

    /*
     * 資料類別改變時連動回傳欄位
     */
    public void onDataTypeMenuChange() {

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
        if (StringUtils.isBlank(this.item.getScopeCode())) {// 新增
            String scopeCode = getWorkSeq(SeqTypeEnum.UDDATA_CODE.toString());
            this.item.setScopeCode(scopeCode);
            ejbWorkSeqFacade.updateWorkSeq(SeqTypeEnum.UDDATA_CODE.toString());
        }
        if (StringUtils.isNotBlank(this.tempRTNField)) {
            String[] split = this.tempRTNField.split("-");
            this.item.setTableName(split[1]);
            this.item.setColumnName(split[2]);
        }
        ejbUdDataScopeMasterFacade.save(this.item);
        ejbUdDataScopeDetailFacade.removeByMaster(this.item.getScopeCode());
        short i = 1;
        for (UdDataScopeDetail d : this.itemDetail) {
            UdDataScopeDetailPK pk = new UdDataScopeDetailPK(this.item.getScopeCode(), i);
            d.setUdDataScopeDetailPK(pk);
            ejbUdDataScopeDetailFacade.create(d);
            i++;
        }
        addMessage("新增成功", "新增成功");
        this.currentItem = this.master.get(this.currentIndex);
        this.init();
        setItemDetail();
        create();
    }

    /*
     * 點擊修改
     */
    public void edit() {
        this.editDialogLabel = "編輯";
        this.item = this.currentItem;
        this.itemDetail = ejbUdDataScopeDetailFacade.findByScopeCode(this.item.getScopeCode());
        genReturnFieldMenu(String.valueOf(this.item.getClassCode()));
        this.allItemCodeMap = new HashMap<String, String>();
        for (UdDataScopeDetail ud : this.itemDetail) {
            genRdOptionItemMenu(ud);
        }
    }

    /*
     * 點擊刪除
     */
    public void delete() {
        if (ejbDataScopeMasterFacade.checkRuleNoExistByScopeCode(this.currentItem.getScopeCode())) {
            addMessage("此欄位範圍已經被引用,請移除該引用才可進行刪除!", "此欄位範圍已經被引用,請移除該引用才可進行刪除!");
        } else if (this.master.size() == 1) {
            addMessage("已是最後一筆無法刪除!", "已是最後一筆無法刪除!");
        } else {
            ejbUdDataScopeMasterFacade.remove(this.currentItem);
            ejbUdDataScopeDetailFacade.removeByMaster(this.currentItem.getScopeCode());
            addMessage("刪除成功", "刪除成功");
        }
        this.init();
    }

    public void tempUserDeFieldChange(int i) {
        UdDataScopeDetail de = this.itemDetail.get(i);
        String[] split = this.tempUserDeField.split("-");
        de.setUdColumnCode(split[0]);
        de.setUdColumnName(split[1]);
        this.itemDetail.set(i, de);
    }

    public void tempValueChange(int i) {
        UdDataScopeDetail de = this.itemDetail.get(i);
        de.setValueDesc(this.allItemCodeMap.get(de.getValue()));
        this.itemDetail.set(i, de);
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

    public String getTempUserDeField() {
        return tempUserDeField;
    }

    public void setTempUserDeField(String tempUserDeField) {
        this.tempUserDeField = tempUserDeField;
    }

    public String getTempRTNField() {
        return tempRTNField;
    }

    public void setTempRTNField(String tempRTNField) {
        this.tempRTNField = tempRTNField;
    }

    public Map<String, String> getAllItemCodeMap() {
        return allItemCodeMap;
    }

    public void setAllItemCodeMap(Map<String, String> allItemCodeMap) {
        this.allItemCodeMap = allItemCodeMap;
    }

    public void genReturnFieldMenu(ValueChangeEvent e) {
        genReturnFieldMenu(e.getNewValue().toString());
    }

    private void genColumnCHNName() {
        QueryUdColumnScopeDetailVO vo = new QueryUdColumnScopeDetailVO();
        vo.setClassCode(this.currentItem.getClassCode());
        vo.setTableName(this.currentItem.getTableName());
        vo.setColumnName(this.currentItem.getColumnName());
        String columnCHNName = ejbRdDataColumnFacade.getFieldCNNameMenu(vo);
        this.currentItem.setColumnCHNName(columnCHNName);
    }

    private void genReturnFieldMenu(String classCode) {
        this.itemReturnFieldMenu = new ArrayList<SelectItem>();
        List<RdDataColumn> list = ejbRdDataColumnFacade.getColumnByClassCode(classCode);
        for (RdDataColumn rd : list) {
            RdDataColumnPK pk = rd.getRdDataColumnPK();
            this.itemReturnFieldMenu
                .add(new SelectItem(pk.getClassCode() + "-" + pk.getTableName()
                        + "-" + pk.getColumnName(), rd.getColumnChnName()));
        }
    }

    private void genUdFieldScopeMasterMenu() {
        this.userDeFieldScopeMenu = new ArrayList<SelectItem>();
        List<UdColumnScopeMaster> allUdColumnMaster = ejbUdColumnScopeMasterFacade.findAllSort();
        for (UdColumnScopeMaster m : allUdColumnMaster) {
            this.userDeFieldScopeMenu
                .add(new SelectItem(m.getUdColumnCode() + "-" + m.getUdColumnName(), m.getUdColumnCode()));
        }
    }

    private void genMethodNameMenu() {
        this.itemMethodNameMenu = new ArrayList<SelectItem>();
        List<UdMethodMaster> allMethodMaster = ejbUdMethodMasterFacade.findAllSort();
        for (UdMethodMaster m : allMethodMaster) {
            this.itemMethodNameMenu.add(new SelectItem(m.getMethodName(), m.getMethodName()));
        }
    }

    private void genDataTypeMenu() {
        this.itemDataTypeMenu = new ArrayList<SelectItem>();
        List<RdDataClass> allDataTypes = ejbRdDataClassFacade.findAllSort();
        for (RdDataClass rd : allDataTypes) {
            this.itemDataTypeMenu.add(new SelectItem(rd.getClassCode(), rd.getClassName()));
        }
    }

    private void genRdOptionItemMenu(UdDataScopeDetail de) {
        List<SelectItem> itemRdOptionItemMenu = new ArrayList<SelectItem>();
        List<Object[]> allItemCodes = ejbRdOptionItemFacade.findAllItemCodes(de.getParameterName());
        for (Object[] o : allItemCodes) {
            itemRdOptionItemMenu.add(new SelectItem(o[0].toString(), o[0].toString() + ". " + o[1].toString()));
            allItemCodeMap.put(o[0].toString(), o[1].toString());
        }
        de.setItemRdOptionItemMenu(itemRdOptionItemMenu);
    }

    /*
     * 載入detail
     */
    private void setItemDetail() {
        String className = ejbRdDataClassFacade.getClassNameByClassCode(this.currentItem.getClassCode());
        this.currentItem.setClassName(className);
        genColumnCHNName();
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
