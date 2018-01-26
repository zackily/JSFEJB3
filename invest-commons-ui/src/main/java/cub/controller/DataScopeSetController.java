package cub.controller;

import cub.entities.DataScopeDetail;
import cub.entities.DataScopeDetailPK;
import cub.entities.DataScopeMaster;
import cub.entities.RdDataClass;
import cub.entities.RdDataColumn;
import cub.entities.RdOptionItem;
import cub.enums.SeqTypeEnum;
import cub.vo.QueryUdColumnScopeDetailVO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

@ManagedBean(name = "dataScopeSetController")
@ViewScoped
public class DataScopeSetController implements Serializable {

    @EJB
    private cub.facade.WorkSeqFacade ejbWorkSeqFacade;
    @EJB
    private cub.facade.DataScopeMasterFacade ejbDataScopeMasterFacade;
    @EJB
    private cub.facade.DataScopeDetailFacade ejbDataScopeDetailFacade;
    @EJB
    private cub.facade.RdDataClassFacade ejbRdDataClassFacade;
    @EJB
    private cub.facade.RdDataColumnFacade ejbRdDataColumnFacade;
    @EJB
    private cub.facade.RdOptionItemFacade ejbRdOptionItemFacade;
    /*
    自定義欄位範圍列表
     */
    private List<DataScopeMaster> master;
    /*
    資料範圍設定區
     */
    private List<DataScopeDetail> details;
    /*
    新增/編輯
     */
    private DataScopeMaster item;
    /*
    待編輯資料範圍設定
     */
    private DataScopeMaster currentItem;
    /*
    新增/編輯資料條件設定區
     */
    private List<DataScopeDetail> itemDetails;
    /*
    新增/編輯暫存資料條件設定區
     */
    private List<DataScopeDetail> tempItemDetails;
    /*
    新增/編輯暫存邏輯
     */
    private String tempLogic;
    /*
    新增/編輯暫存關係
     */
    private String tempOpCode;
    /*
    新增/編輯暫存資料欄位
     */
    private String tempColumn;
    /*
    新增/編輯暫存左括弧
     */
    private String tempLeftBracket;
    /*
    新增/編輯暫存右括弧
     */
    private String tempRightBracket;
    /*
    新增/編輯暫存資料值
     */
    private String tempOpValue;
    /*
    新增/編輯暫存邏輯
     */
    private List<String> tempLogicList = new ArrayList<String>();
    /*
    新增/編輯暫存關係
     */
    private List<String> tempOpCodeList = new ArrayList<String>();
    /*
    新增/編輯暫存資料
     */
    private List<String> tempOpValueList = new ArrayList<String>();
    /*
    新增/編輯暫存左括弧
     */
    private List<String> tempLeftBracketList = new ArrayList<String>();
    /*
    新增/編輯暫存右括弧
     */
    private List<String> tempRightBracketList = new ArrayList<String>();
    /*
    新增/編輯資料欄位
     */
    private List<String> tempColumnList = new ArrayList<String>();
    /*
    資料類別下拉選單
     */
    private List<SelectItem> dataTypeMenu;
    /*
    關係下拉式選單
     */
    private List<SelectItem> operatorMenu;
    /*
    資料欄位下拉式選單
     */
    private List<SelectItem> columnMenu;
    /*
    自定義欄位範圍索引
     */
    private int currentIndex;
    /*
    新增/編輯Dialog CommandButton value
     */
    private String editDialogLabel = "新增";

    @PostConstruct
    public void init() {
        this.master = new ArrayList<DataScopeMaster>();
        this.master = ejbDataScopeMasterFacade.findAllSort();
        this.item = new DataScopeMaster();
        this.currentItem = new DataScopeMaster();
        if (this.master.isEmpty()) {
            this.master.add(this.item);
        } else {
            this.currentItem = this.master.get(0);
            setItemDetail();
        }
        //頁面載入資料範圍欄位this.master的index
        currentIndex = 0;
        //initial資料類別下拉選單
        this.dataTypeMenu = new ArrayList<SelectItem>();
        List<RdDataClass> rdcList = ejbRdDataClassFacade.findAllSort();
        for (RdDataClass rdc : rdcList) {
            this.dataTypeMenu.add(new SelectItem(rdc.getClassCode(), rdc.getClassName()));
        }
        //initial關係下拉選單
        this.operatorMenu = new ArrayList<SelectItem>();
        List<RdOptionItem> rdiList = ejbRdOptionItemFacade.findAllSort();
        for (RdOptionItem r : rdiList) {
            this.operatorMenu.add(new SelectItem(r.getRdOptionItemPK().getItemCode(), r.getItemName()));
        }
        this.tempItemDetails = new ArrayList<DataScopeDetail>();
    }

    /*
    自定義欄位範圍代碼autocomplete
     */
    public List<String> searchDataScopeMaster(String code) {
        List<String> result = ejbDataScopeMasterFacade.findByCode(StringUtils.upperCase(code));
        return result;
    }

    /*
    自定義欄位範圍代碼autocomplete select
     */
    public void onItemSelect(SelectEvent event) {
        this.currentItem = ejbDataScopeMasterFacade.find(event.getObject().toString());
        this.details = ejbDataScopeDetailFacade.findByMasterCode(this.currentItem.getScopeCode());
    }

    /*
    新增資料條件(＋)
     */
    public void addDataScopeDetailList(ActionEvent event) {
        if (StringUtils.isBlank(this.item.getScopeCode())) {//新增
            this.tempItemDetails.add(new DataScopeDetail());
            if (this.item.getClassCode() != 0) {
                changeColumnMenuByClassCode(String.valueOf(this.item.getClassCode()));
            }
        } else if (null != this.item) {//編輯
            this.tempItemDetails.add(new DataScopeDetail());
            changeColumnMenuByClassCode(String.valueOf(this.item.getClassCode()));
        } else {
            addMessage("System Error", "請先選定資料類別!");
        }
        this.tempLogicList.add("");
        this.tempLeftBracketList.add("");
        this.tempColumnList.add(null == this.columnMenu ? "" : this.columnMenu.get(0).getValue().toString());
        this.tempOpCodeList.add("");
        this.tempOpValueList.add("");
        this.tempRightBracketList.add("");
    }

    /*
    移除資料條件(－)
     */
    public void removeDataScopeDetailList(ActionEvent event) {
        this.tempItemDetails.remove(this.tempItemDetails.size() - 1);
    }

    /*
    遍歷Master
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
    點擊新增
     */
    public void create() {
        this.item = new DataScopeMaster();
        this.editDialogLabel = "新增";
    }

    /*
    確認新增
     */
    public void save(ActionEvent event) {
        if (StringUtils.isBlank(this.item.getScopeCode())) {//新增
            String scopeCode = getWorkSeq(SeqTypeEnum.DATA_CODE.toString());
            this.item.setScopeCode(scopeCode);
            this.item.setLogUserId("Gilbert");
            this.item.setLogDttm(new Date());
            if (null != tempItemDetails) {
                for (int i = 0; i < tempItemDetails.size(); i++) {
                    DataScopeDetail dd = new DataScopeDetail();
                    DataScopeDetailPK pk = new DataScopeDetailPK();
                    pk.setScopeCode(scopeCode);
                    int no = i + 1;//Seq_No  start from 1;
                    pk.setSeqNo((short) no);
                    dd.setDataScopeDetailPK(pk);
                    dd.setLogic(tempLogicList.get(i));
                    dd.setLeftBracket(tempLeftBracketList.get(i));
                    String[] s = StringUtils.split(this.tempColumnList.get(i), "+");
                    dd.setTableName(s[0]);
                    dd.setColumnName(s[1]);
                    dd.setOpCode(tempOpCodeList.get(i));
                    dd.setOpValue(tempOpValueList.get(i));
                    dd.setRightBracket(tempRightBracketList.get(i));
                    ejbDataScopeDetailFacade.create(dd);
                }
            }
            ejbDataScopeMasterFacade.create(this.item);
            ejbWorkSeqFacade.updateWorkSeq(SeqTypeEnum.DATA_CODE.toString());
        } else {//編輯
            ejbDataScopeDetailFacade.removeByMaster(this.item.getScopeCode());
            for (int i = 0; i < this.tempItemDetails.size(); i++) {
                DataScopeDetail dd = new DataScopeDetail();
                DataScopeDetailPK pk = new DataScopeDetailPK();
                pk.setScopeCode(this.item.getScopeCode());
                int no = i + 1;//Seq_No  start from 1;
                pk.setSeqNo((short) no);
                dd.setDataScopeDetailPK(pk);
                dd.setLogic(tempLogicList.get(i));
                dd.setLeftBracket(tempLeftBracketList.get(i));
                String[] s = StringUtils.split(this.tempColumnList.get(i), "+");
                dd.setTableName(s[0]);
                dd.setColumnName(s[1]);
                dd.setOpCode(tempOpCodeList.get(i));
                dd.setOpValue(tempOpValueList.get(i));
                dd.setRightBracket(tempRightBracketList.get(i));
                ejbDataScopeDetailFacade.create(dd);
            }
            this.item.setLogDttm(new Date());
            ejbDataScopeMasterFacade.edit(this.item);
        }
        this.tempItemDetails.clear();
        this.init();
        clearTempList();
        this.currentItem = this.master.get(this.master.size() - 1);
        setItemDetail();
    }

    /*
    點擊修改
     */
    public void edit() {
        this.editDialogLabel = "編輯";
        this.item = this.currentItem;
        this.tempItemDetails = this.details;
        for (DataScopeDetail ds : this.details) {
            this.tempLogicList.add(ds.getLogic());
            this.tempLeftBracketList.add(ds.getLeftBracket());
            this.tempColumnList.add(ds.getTableName() + "+" + ds.getColumnName());
            this.tempOpCodeList.add(ds.getOpCode());
            this.tempOpValueList.add(ds.getOpValue());
            this.tempRightBracketList.add(ds.getRightBracket());
        }
        changeColumnMenuByClassCode(String.valueOf(this.item.getClassCode()));
    }

    /*
    點擊刪除
     */
    public void delete() {
        if (ejbDataScopeMasterFacade.checkRuleNoExistByScopeCode(this.currentItem.getScopeCode())) {
            addMessage("System Error", "此資料範圍已經被引用,請移除該引用才可進行刪除!");
        } else {
            ejbDataScopeMasterFacade.remove(this.currentItem);
            ejbDataScopeDetailFacade.removeByMaster(tempOpCode);
        }
        this.init();
    }

    public void classCodeChange() {
        changeColumnMenuByClassCode(String.valueOf(this.item.getClassCode()));
    }

    public void logicChange(int i) {
        this.tempLogicList.set(i, this.tempLogic);
    }

    public void logicChangeForEdit(ValueChangeEvent e) {
        if (null == e.getOldValue()) {
            this.tempLogicList.add(0, e.getNewValue().toString());
        } else {
            int idx = this.tempLogicList.indexOf(e.getOldValue().toString());
            this.tempLogicList.set(idx, e.getNewValue().toString());
        }
    }

    public void columnChange(int i) {
        this.tempColumnList.set(i, this.tempColumn);
    }

    public void columnChangeForEdit(ValueChangeEvent e) {
        if (null == e.getOldValue()) {
            this.tempColumnList.add(0, e.getNewValue().toString());
        } else {
            int idx = this.tempColumnList.indexOf(e.getOldValue().toString());
            this.tempColumnList.set(idx, e.getNewValue().toString());
        }
    }

    public void opCodeChange(int i) {
        this.tempOpCodeList.set(i, this.tempOpCode);
    }

    public void opCodeChangeForEdit(ValueChangeEvent e) {
        if (null == e.getOldValue()) {
            this.tempOpCodeList.add(0, e.getNewValue().toString());
        } else {
            int idx = this.tempOpCodeList.indexOf(e.getOldValue().toString());
            this.tempOpCodeList.set(idx, e.getNewValue().toString());
        }
    }

    public void opValueChange(int i) {
        this.tempOpValueList.set(i, this.tempOpValue);
    }

    public void opValueChangeForEdit(ValueChangeEvent e) {
        if (null == e.getOldValue()) {
            this.tempOpValueList.add(0, e.getNewValue().toString());
        } else {
            int idx = this.tempOpValueList.indexOf(e.getOldValue().toString());
            this.tempOpValueList.set(idx, e.getNewValue().toString());
        }
    }

    public void leftBracketChange(int i) {
        this.tempLeftBracketList.set(i, this.tempLeftBracket);
    }

    public void leftBracketChangeForEdit(ValueChangeEvent e) {
        if (null == e.getOldValue()) {
            this.tempLeftBracketList.add(0, e.getNewValue().toString());
        } else {
            int idx = this.tempLeftBracketList.indexOf(e.getOldValue().toString());
            this.tempLeftBracketList.set(idx, e.getNewValue().toString());
        }
    }

    public void rightBracketChange(int i) {
        this.tempRightBracketList.set(i, this.tempRightBracket);
    }

    public void rightBracketChangeForEdit(ValueChangeEvent e) {
        if (null == e.getOldValue()) {
            this.tempRightBracketList.add(0, e.getNewValue().toString());
        } else {
            int idx = this.tempRightBracketList.indexOf(e.getOldValue().toString());
            this.tempRightBracketList.set(idx, e.getNewValue().toString());
        }
    }

    /*
    清空暫存List
     */
    public void clearTempList() {
        this.tempLogicList.clear();
        this.tempLeftBracketList.clear();
        this.tempColumnList.clear();
        this.tempOpCodeList.clear();
        this.tempOpValueList.clear();
        this.tempRightBracketList.clear();
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

    public List<DataScopeMaster> getMaster() {
        return master;
    }

    public void setMaster(List<DataScopeMaster> master) {
        this.master = master;
    }

    public List<DataScopeDetail> getDetails() {
        return details;
    }

    public void setDetails(List<DataScopeDetail> details) {
        this.details = details;
    }

    public DataScopeMaster getItem() {
        return item;
    }

    public void setItem(DataScopeMaster item) {
        this.item = item;
    }

    public DataScopeMaster getCurrentItem() {
        return currentItem;
    }

    public void setCurrentItem(DataScopeMaster currentItem) {
        this.currentItem = currentItem;
    }

    public List<DataScopeDetail> getItemDetails() {
        return itemDetails;
    }

    public void setItemDetails(List<DataScopeDetail> itemDetails) {
        this.itemDetails = itemDetails;
    }

    public List<SelectItem> getDataTypeMenu() {
        return dataTypeMenu;
    }

    public void setDataTypeMenu(List<SelectItem> dataTypeMenu) {
        this.dataTypeMenu = dataTypeMenu;
    }

    public List<SelectItem> getOperatorMenu() {
        return operatorMenu;
    }

    public void setOperatorMenu(List<SelectItem> operatorMenu) {
        this.operatorMenu = operatorMenu;
    }

    public List<DataScopeDetail> getTempItemDetails() {
        return tempItemDetails;
    }

    public void setTempItemDetails(List<DataScopeDetail> tempItemDetails) {
        this.tempItemDetails = tempItemDetails;
    }

    public String getTempLogic() {
        return tempLogic;
    }

    public void setTempLogic(String tempLogic) {
        this.tempLogic = tempLogic;
    }

    public String getTempOpCode() {
        return tempOpCode;
    }

    public void setTempOpCode(String tempOpCode) {
        this.tempOpCode = tempOpCode;
    }

    public String getTempLeftBracket() {
        return tempLeftBracket;
    }

    public void setTempLeftBracket(String tempLeftBracket) {
        this.tempLeftBracket = tempLeftBracket;
    }

    public String getTempRightBracket() {
        return tempRightBracket;
    }

    public void setTempRightBracket(String tempRightBracket) {
        this.tempRightBracket = tempRightBracket;
    }

    public List<String> getTempLogicList() {
        return tempLogicList;
    }

    public void setTempLogicList(List<String> tempLogicList) {
        this.tempLogicList = tempLogicList;
    }

    public List<String> getTempOpCodeList() {
        return tempOpCodeList;
    }

    public void setTempOpCodeList(List<String> tempOpCodeList) {
        this.tempOpCodeList = tempOpCodeList;
    }

    public List<String> getTempOpValueList() {
        return tempOpValueList;
    }

    public void setTempOpValueList(List<String> tempOpValueList) {
        this.tempOpValueList = tempOpValueList;
    }

    public List<String> getTempLeftBracketList() {
        return tempLeftBracketList;
    }

    public void setTempLeftBracketList(List<String> tempLeftBracketList) {
        this.tempLeftBracketList = tempLeftBracketList;
    }

    public List<String> getTempRightBracketList() {
        return tempRightBracketList;
    }

    public void setTempRightBracketList(List<String> tempRightBracketList) {
        this.tempRightBracketList = tempRightBracketList;
    }

    public String getTempOpValue() {
        return tempOpValue;
    }

    public void setTempOpValue(String tempOpValue) {
        this.tempOpValue = tempOpValue;
    }

    public String getTempColumn() {
        return tempColumn;
    }

    public void setTempColumn(String tempColumn) {
        this.tempColumn = tempColumn;
    }

    public List<String> getTempColumnList() {
        return tempColumnList;
    }

    public void setTempColumnList(List<String> tempColumnList) {
        this.tempColumnList = tempColumnList;
    }

    public List<SelectItem> getColumnMenu() {
        return columnMenu;
    }

    public void setColumnMenu(List<SelectItem> columnMenu) {
        this.columnMenu = columnMenu;
    }

    /*
    載入detail
     */
    private void setItemDetail() {
        String className = ejbRdDataClassFacade.getClassNameByClassCode(this.currentItem.getClassCode());
        this.currentItem.setClassName(className);
        this.itemDetails = ejbDataScopeDetailFacade.findByMasterCode(this.currentItem.getScopeCode());
        this.details = new ArrayList<DataScopeDetail>();
        for (DataScopeDetail d : itemDetails) {
            DataScopeDetail dd = new DataScopeDetail();
            dd = d;
            QueryUdColumnScopeDetailVO vo = new QueryUdColumnScopeDetailVO();
            vo.setClassCode(this.currentItem.getClassCode());
            vo.setTableName(d.getTableName());
            vo.setColumnName(d.getColumnName());
            dd.setColumnCHNName(d.getTableName() + "/" + d.getColumnName() + ejbRdDataColumnFacade.getFieldCNNameMenu(vo));
            dd.setColumnValue(d.getTableName() + "+" + d.getColumnName());
            dd.setOpName(ejbRdOptionItemFacade.findItemNameByItemCode(d.getOpCode()));
            this.details.add(dd);
        }
    }

    /*
    取得自定義欄位範圍代碼
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

    /*
    資料類別下拉選單改變時重新載資料欄位下拉選單
     */
    private void changeColumnMenuByClassCode(String classCode) {
        this.columnMenu = new ArrayList<SelectItem>();
        List<RdDataColumn> rdcList = ejbRdDataColumnFacade.getColumnByClassCode(classCode);
        for (RdDataColumn rdc : rdcList) {
            this.columnMenu.add(new SelectItem(rdc.getRdDataColumnPK().getTableName() + "+" + rdc.getRdDataColumnPK().getColumnName(), rdc.getColumnChnName()));
        }
    }

}
