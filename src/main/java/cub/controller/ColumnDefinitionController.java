package cub.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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

import cub.entities.RdDataClass;
import cub.entities.RdDataColumn;
import cub.entities.RdDataColumnPK;
import cub.facade.AllCommentsFacade;
import cub.facade.RdDataClassFacade;
import cub.facade.RdDataColumnFacade;

/**
 * @author F123669 欄位定義作業(RCMM99)
 */
@ManagedBean(name = "columnDefinitionController")
@ViewScoped
public class ColumnDefinitionController implements Serializable {

    @EJB
    private RdDataColumnFacade ejbRdDataColumnFacade;
    @EJB
    private RdDataClassFacade ejbRdDataClassFacade;
    @EJB
    private AllCommentsFacade ejbAllCommentsfacade;
    /*
     * 欄位定義作業列表
     */
    private List<RdDataColumn> master;
    /*
     * 新增/編輯
     */
    private RdDataColumn item;
    /*
     * 待編輯欄位定義設定
     */
    private RdDataColumn currentItem;
    /*
     * 新增/編輯Dialog CommandButton value
     */
    private String editDialogLabel = "新增";
    /*
     * 欄位定義作業索引
     */
    private int currentIndex;
    /*
     * 資料類別代碼下拉選單
     */
    private List<SelectItem> classNameMenu;
    /*
     * Table名稱下拉選單
     */
    private List<SelectItem> tableNameMenu;
    /*
     * Column名稱下拉選單
     */
    private List<SelectItem> columnNameMenu;
    /*
     * Column名稱下拉選單
     */
    private Map<String, String> columnDescMap;
    /*
     * Db Name
     */
    private String dbName;

    private short tempClassCode;

    private String tempTableName;

    private String tempColumnName;

    @PostConstruct
    public void init() {
        this.master = new ArrayList<RdDataColumn>();
        this.master = ejbRdDataColumnFacade.findAllSort();
        this.item = new RdDataColumn();
        this.currentItem = new RdDataColumn();
        if (this.master.isEmpty()) {
            this.master.add(this.item);
        } else {
            this.currentItem = this.master.get(0);
        }
        // 頁面載入欄位定義作業this.master的index
        currentIndex = 0;
        // initial Class資料類別代碼
        List<RdDataClass> allClass = ejbRdDataClassFacade.findAllSort();
        this.classNameMenu = new ArrayList<SelectItem>();
        for (RdDataClass c : allClass) {
            this.classNameMenu.add(new SelectItem(c.getClassCode(), c.getClassName()));
        }
    }

    // initial Table名稱下拉選單, call by classMenu change
    public void genTableMenu(ValueChangeEvent event) {
        this.dbName = ejbRdDataClassFacade.getDBNameByClassCode(Short.valueOf(event.getNewValue().toString()));
        List<Object[]> allTabComments = ejbAllCommentsfacade.getAllTabComments(this.dbName);
        this.tableNameMenu = new ArrayList<SelectItem>();
        for (Object[] a : allTabComments) {
            Object[] ob = new Object[] { "", "" };
            ob = a;
            if (null != ob[0] && null != ob[1]) {
                this.tableNameMenu.add(new SelectItem(a[0].toString(), a[0].toString()));
            }
        }
    }

    // initial Column名稱下拉選單, call by tableNameMenu change
    public void genColumnMenu(ValueChangeEvent event) {
        this.columnDescMap = new HashMap<String, String>();
        List<Object[]> allColComments = ejbAllCommentsfacade.getAllColComments(this.dbName,
            event.getNewValue().toString());
        this.columnNameMenu = new ArrayList<SelectItem>();
        for (Object[] a : allColComments) {
            Object[] ob = new Object[] { "", "" };
            ob = a;
            if (null != ob[0] && null != ob[1]) {
                this.columnNameMenu.add(new SelectItem(a[0].toString(), a[0].toString()));
                this.columnDescMap.put(a[0].toString(), a[1].toString());
            }
        }
    }

    // call by ColumnMenu change
    public void genColumnDesc(ValueChangeEvent event) {
        String cname = this.columnDescMap.get(event.getNewValue().toString());
        this.item.setColumnChnName(cname);
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
    }

    /*
     * 點擊新增
     */
    public void create() {
        this.item = new RdDataColumn();
        this.editDialogLabel = "新增";
    }

    /*
     * 確認新增
     */
    public void save(ActionEvent event) {
        RdDataColumnPK pk = new RdDataColumnPK(this.tempClassCode, this.tempTableName, this.tempColumnName);
        if (null == ejbRdDataColumnFacade.find(pk)) {// 新增
            this.item.setRdDataColumnPK(pk);
            this.item.setLogUserId("Gilbert");
            this.item.setLogDttm(new Date());
            ejbRdDataColumnFacade.create(this.item);
            addMessage("新增成功", "新增成功");
        } else {// 編輯
            // ejbRdDataColumnFacade.edit(this.currentItem);
            addMessage("此欄位範圍已存在", "此欄位範圍已存在");
        }
//        this.init();
        this.currentItem = this.master.get(this.currentIndex);
        create();
    }
    //
    // /*
    // * 點擊修改
    // */
    // public void edit() {
    // this.editDialogLabel = "編輯";
    // this.item = this.currentItem;
    // this.tempClassCode = this.item.getRdDataColumnPK().getClassCode();
    // this.tempTableName = this.item.getRdDataColumnPK().getTableName();
    // this.tempColumnName = this.item.getRdDataColumnPK().getColumnName();
    // }

    /*
     * 點擊刪除
     */
    public void delete() {
        if (ejbRdDataColumnFacade.checkRuleNoExistByPK(this.currentItem.getRdDataColumnPK())) {
            addMessage("此欄位範圍已經被引用,請移除該引用才可進行刪除!", "此欄位範圍已經被引用,請移除該引用才可進行刪除!");
        } else if (this.master.size() == 1) {
            addMessage("已是最後一筆無法刪除!", "已是最後一筆無法刪除!");
        } else {
            ejbRdDataColumnFacade.remove(this.currentItem);
            addMessage("刪除成功", "刪除成功");
        }
        this.init();
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

    public List<RdDataColumn> getMaster() {
        return master;
    }

    public void setMaster(List<RdDataColumn> master) {
        this.master = master;
    }

    public RdDataColumn getItem() {
        return item;
    }

    public void setItem(RdDataColumn item) {
        this.item = item;
    }

    public RdDataColumn getCurrentItem() {
        return currentItem;
    }

    public void setCurrentItem(RdDataColumn currentItem) {
        this.currentItem = currentItem;
    }

    public List<SelectItem> getClassNameMenu() {
        return classNameMenu;
    }

    public void setClassNameMenu(List<SelectItem> classNameMenu) {
        this.classNameMenu = classNameMenu;
    }

    public List<SelectItem> getTableNameMenu() {
        return tableNameMenu;
    }

    public void setTableNameMenu(List<SelectItem> tableNameMenu) {
        this.tableNameMenu = tableNameMenu;
    }

    public List<SelectItem> getColumnNameMenu() {
        return columnNameMenu;
    }

    public void setColumnNameMenu(List<SelectItem> columnNameMenu) {
        this.columnNameMenu = columnNameMenu;
    }

    public Map<String, String> getColumnDescMap() {
        return columnDescMap;
    }

    public void setColumnDescMap(Map<String, String> columnDescMap) {
        this.columnDescMap = columnDescMap;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public short getTempClassCode() {
        return tempClassCode;
    }

    public void setTempClassCode(short tempClassCode) {
        this.tempClassCode = tempClassCode;
    }

    public String getTempTableName() {
        return tempTableName;
    }

    public void setTempTableName(String tempTableName) {
        this.tempTableName = tempTableName;
    }

    public String getTempColumnName() {
        return tempColumnName;
    }

    public void setTempColumnName(String tempColumnName) {
        this.tempColumnName = tempColumnName;
    }

    private void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
