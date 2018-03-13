package cub.controller;

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

import cub.entities.DataScopeDetail;
import cub.entities.DataScopeDetailPK;
import cub.entities.DataScopeMaster;
import cub.entities.RdDataClass;
import cub.entities.RdDataColumn;
import cub.enums.SeqTypeEnum;
import cub.facade.DataScopeDetailFacade;
import cub.facade.DataScopeMasterFacade;
import cub.facade.RdDataClassFacade;
import cub.facade.RdDataColumnFacade;
import cub.facade.RdDataColumnOptionFacade;
import cub.facade.WorkSeqFacade;
import cub.vo.QueryUdColumnScopeDetailVO;

/**
 * @author F123669 資料範圍設定作業(RCMM01)
 *
 */
@ManagedBean(name = "dataScopeSetController")
@ViewScoped
public class DataScopeSetController implements Serializable {

    @EJB
    private WorkSeqFacade ejbWorkSeqFacade;
    @EJB
    private DataScopeMasterFacade ejbDataScopeMasterFacade;
    @EJB
    private DataScopeDetailFacade ejbDataScopeDetailFacade;
    @EJB
    private RdDataClassFacade ejbRdDataClassFacade;
    @EJB
    private RdDataColumnFacade ejbRdDataColumnFacade;
    @EJB
    private RdDataColumnOptionFacade ejbRdDataColumnOptionFacade;
    /*
     * 自定義欄位範圍列表
     */
    private List<DataScopeMaster> master;
    /*
     * 資料範圍設定區
     */
    private List<DataScopeDetail> details;
    /*
     * 新增/編輯
     */
    private DataScopeMaster item;
    /*
     * 待編輯資料範圍設定
     */
    private DataScopeMaster currentItem;
    /*
     * 新增/編輯資料條件設定區
     */
    private List<DataScopeDetail> itemDetails;
    /*
     * 新增/編輯暫存資料條件設定區
     */
    private List<DataScopeDetail> tempItemDetails;
    /*
     * 新增/編輯資料範圍下拉選單
     */
    private List<SelectItem> rdDataColumnOptionMenu;
    /*
     * 自定義欄位範圍索引
     */
    private int currentIndex;
    /*
     * 新增/編輯Dialog CommandButton value
     */
    private String editDialogLabel = "新增";

    @PostConstruct
    public void init() {
        this.master = new ArrayList<DataScopeMaster>();
        getRenewMaster();
        this.item = new DataScopeMaster();
        this.currentItem = new DataScopeMaster();
        if (this.master.isEmpty()) {
            this.master.add(this.item);
        } else {
            this.currentItem = this.master.get(0);
            setItemDetail();
        }
        // 頁面載入資料範圍欄位this.master的index
        currentIndex = 0;
        this.tempItemDetails = new ArrayList<DataScopeDetail>();
    }

    /*
     * 自定義欄位範圍代碼autocomplete
     */
    public List<String> searchDataScopeMaster(String code) {
        return ejbDataScopeMasterFacade.findByCode(StringUtils.upperCase(code));
    }

    /*
     * 自定義欄位範圍代碼autocomplete select
     */
    public void onItemSelect(SelectEvent event) {
        this.currentItem = ejbDataScopeMasterFacade.find(event.getObject().toString());
        this.details = ejbDataScopeDetailFacade.findByMasterCode(this.currentItem.getScopeCode());
    }

    /*
     * 新增資料條件(＋)
     */
    public void addDataScopeDetailList(ActionEvent event) {
        DataScopeDetail de = new DataScopeDetail();
        if (StringUtils.isBlank(this.item.getScopeCode())) {// 新增
            changeColumnMenuByClassCode(de, String.valueOf(this.item.getClassCode()));
            this.tempItemDetails.add(de);
        } else if (null != this.item) {// 編輯
            changeColumnMenuByClassCode(de, String.valueOf(this.item.getClassCode()));
            List<SelectItem> rdDataColumnOptionMenu = new ArrayList<SelectItem>();
            List<Object[]> optionList = ejbRdDataColumnOptionFacade.findByColumn(this.item.getClassCode(),
                de.getTableName(),
                de.getColumnName());
            for (Object[] op : optionList) {
                rdDataColumnOptionMenu.add(new SelectItem(op[0], op[1].toString()));
            }
            de.setRdDataColumnOptionMenu(rdDataColumnOptionMenu);
            this.tempItemDetails.add(de);
        } else {
            addMessage("請先選定資料類別!", "請先選定資料類別!");
        }
    }

    /*
     * 移除資料條件(－)
     */
    public void removeDataScopeDetailList(DataScopeDetail d) {
        this.tempItemDetails.remove(d);
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
        this.item = new DataScopeMaster();
        this.editDialogLabel = "新增";
        // initial資料類別下拉選單
        this.init();
        List<SelectItem> dataTypeMenu = new ArrayList<SelectItem>();
        List<RdDataClass> rdcList = ejbRdDataClassFacade.findAllSort();
        for (RdDataClass rdc : rdcList) {
            dataTypeMenu.add(new SelectItem(rdc.getClassCode(), rdc.getClassName()));
        }
        this.item.setDataTypeMenu(dataTypeMenu);
    }

    /*
     * 確認新增
     */
    public void save(ActionEvent event) {
        if (StringUtils.isBlank(this.item.getScopeCode())) {// 新增
            String scopeCode = getWorkSeq(SeqTypeEnum.DATA_CODE.toString());
            this.item.setScopeCode(scopeCode);
            this.item.setLogUserId("Gilbert");
            this.item.setLogDttm(new Date());
            if (null != tempItemDetails) {
                for (int i = 0; i < tempItemDetails.size(); i++) {
                    DataScopeDetail dd = tempItemDetails.get(i);
                    DataScopeDetailPK pk = new DataScopeDetailPK();
                    pk.setScopeCode(scopeCode);
                    pk.setSeqNo((short) (i + 1));
                    if (i == 1) {
                        dd.setLogic("_");
                    }
                    dd.setDataScopeDetailPK(pk);
                    String[] s = StringUtils.split(dd.getColumnValue(), "+");
                    dd.setTableName(s[0]);
                    dd.setColumnName(s[1]);
                    ejbDataScopeDetailFacade.create(dd);
                }
            }
            ejbDataScopeMasterFacade.create(this.item);
            ejbWorkSeqFacade.updateWorkSeq(SeqTypeEnum.DATA_CODE.toString());
            addMessage("新增成功", "新增成功");
            getRenewMaster();
            currentIndex = this.master.size() - 1;
        } else {// 編輯
            ejbDataScopeDetailFacade.removeByMaster(this.item.getScopeCode());
            for (int i = 0; i < this.tempItemDetails.size(); i++) {
                DataScopeDetail dd = new DataScopeDetail();
                DataScopeDetailPK pk = new DataScopeDetailPK();
                pk.setScopeCode(this.item.getScopeCode());
                pk.setSeqNo((short) i++);
                dd.setDataScopeDetailPK(pk);
                String[] s = StringUtils.split(tempItemDetails.get(i).getColumnValue(), "+");
                dd.setTableName(s[0]);
                dd.setColumnName(s[1]);
                ejbDataScopeDetailFacade.create(dd);
            }
            this.item.setLogDttm(new Date());
            ejbDataScopeMasterFacade.edit(this.item);
            addMessage("更新成功", "更新成功");
        }
        this.tempItemDetails.clear();
        // this.init();
        this.currentItem = this.master.get(currentIndex);
        setItemDetail();
        create();
    }

    /*
     * 點擊修改
     */
    public void edit() {
        this.editDialogLabel = "編輯";
        this.item = this.currentItem;
        this.tempItemDetails = this.details;
        // initial資料類別下拉選單
        List<SelectItem> dataTypeMenu = new ArrayList<SelectItem>();
        List<RdDataClass> rdcList = ejbRdDataClassFacade.findAllSort();
        for (RdDataClass rdc : rdcList) {
            dataTypeMenu.add(new SelectItem(rdc.getClassCode(), rdc.getClassName()));
        }
        this.item.setDataTypeMenu(dataTypeMenu);
        setItemDetail();
        classCodeChange();
        columnChangeForEdit();
    }

    /*
     * 點擊刪除
     */
    public void delete() {
        if (ejbDataScopeMasterFacade.checkRuleNoExistByScopeCode(this.currentItem.getScopeCode())) {
            addMessage("此資料範圍已經被引用,請移除該引用才可進行刪除!", "此資料範圍已經被引用,請移除該引用才可進行刪除!");
        } else if (this.master.size() == 1) {
            addMessage("已是最後一筆無法刪除!", "已是最後一筆無法刪除!");
        } else {
            ejbDataScopeMasterFacade.remove(this.currentItem);
            ejbDataScopeDetailFacade.removeByMaster(this.currentItem.getScopeCode());
            addMessage("刪除成功", "刪除成功");
        }
        this.init();
    }

    public void classCodeChange() {
        for (DataScopeDetail dd : this.tempItemDetails) {
            changeColumnMenuByClassCode(dd, String.valueOf(this.item.getClassCode()));
        }
    }

    public void columnChangeForEdit(ValueChangeEvent e) {
        List<SelectItem> rdDataColumnOptionMenu = new ArrayList<SelectItem>();
        String newValue = e.getNewValue().toString();
        String oldValue = null != e.getOldValue() ? e.getOldValue().toString() : "";
        String[] s = StringUtils.split(newValue, "+");
        String tableName = s[0];
        String columnName = s[1];
        List<Object[]> optionList = ejbRdDataColumnOptionFacade.findByColumn(this.item.getClassCode(), tableName,
            columnName);
        for (Object[] op : optionList) {
            rdDataColumnOptionMenu.add(new SelectItem(op[0], op[1].toString()));
        }
        for (int i = 0; i < this.tempItemDetails.size(); i++) {
            DataScopeDetail d = this.tempItemDetails.get(i);
            if (StringUtils.isBlank(d.getColumnValue())) {
                d.setRdDataColumnOptionMenu(rdDataColumnOptionMenu);
                this.tempItemDetails.set(i, d);
            } else if (d.getColumnValue().equals(oldValue)) {
                d.setRdDataColumnOptionMenu(rdDataColumnOptionMenu);
                this.tempItemDetails.set(i, d);
            }
        }
    }

    public void clearList(ActionEvent event) {
        this.tempItemDetails.clear();
        this.item = new DataScopeMaster();
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

    public List<DataScopeDetail> getTempItemDetails() {
        return tempItemDetails;
    }

    public void setTempItemDetails(List<DataScopeDetail> tempItemDetails) {
        this.tempItemDetails = tempItemDetails;
    }

    public List<SelectItem> getRdDataColumnOptionMenu() {
        return rdDataColumnOptionMenu;
    }

    public void setRdDataColumnOptionMenu(List<SelectItem> rdDataColumnOptionMenu) {
        this.rdDataColumnOptionMenu = rdDataColumnOptionMenu;
    }

    private void columnChangeForEdit() {
        for (int i = 0; i < this.tempItemDetails.size(); i++) {
            List<SelectItem> rdDataColumnOptionMenu = new ArrayList<SelectItem>();
            DataScopeDetail d = this.tempItemDetails.get(i);
            List<Object[]> optionList = ejbRdDataColumnOptionFacade.findByColumn(this.item.getClassCode(),
                d.getTableName(),
                d.getColumnName());
            for (Object[] op : optionList) {
                rdDataColumnOptionMenu.add(new SelectItem(op[0], op[1].toString()));
            }
            d.setRdDataColumnOptionMenu(rdDataColumnOptionMenu);
            this.tempItemDetails.set(i, d);
        }
    }

    private void getRenewMaster() {
        this.master = ejbDataScopeMasterFacade.findAllSort();
    }

    /*
     * 載入detail
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
            dd.setColumnCHNName(
                d.getTableName() + "/" + d.getColumnName() + ejbRdDataColumnFacade.getFieldCNNameMenu(vo));
            dd.setColumnValue(d.getTableName() + "+" + d.getColumnName());
            dd.setTempOpValue(d.getOpValue() + "+" + d.getTableName() + "+" + d.getColumnName());
            this.details.add(dd);
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

    /*
     * 資料類別下拉選單改變時重新載資料欄位下拉選單
     */
    private void changeColumnMenuByClassCode(DataScopeDetail de, String classCode) {
        List<SelectItem> columnMenu = new ArrayList<SelectItem>();
        List<RdDataColumn> rdcList = ejbRdDataColumnFacade.getColumnByClassCode(classCode);
        for (RdDataColumn rdc : rdcList) {
            columnMenu.add(
                new SelectItem(rdc.getRdDataColumnPK().getTableName() + "+" + rdc.getRdDataColumnPK().getColumnName(),
                        rdc.getColumnChnName()));
        }
        de.setColumnMenu(columnMenu);
    }

}
