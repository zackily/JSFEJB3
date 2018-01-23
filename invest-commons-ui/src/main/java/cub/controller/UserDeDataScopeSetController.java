package cub.controller;

import cub.entities.RdDataColumn;
import cub.entities.RdDataColumnOption;
import cub.entities.RdDataColumnOptionPK;
import cub.entities.RdDataColumnPK;
import cub.entities.UdColumnScopeDetail;
import cub.entities.UdColumnScopeDetailPK;
import cub.entities.UdColumnScopeMaster;
import cub.enums.SeqTypeEnum;
import cub.vo.QueryUdColumnScopeDetailVO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import oracle.net.aso.i;
import org.apache.commons.lang.StringUtils;
import org.primefaces.event.SelectEvent;

@ManagedBean(name = "userDeDataScopeSetController")
@ViewScoped
public class UserDeDataScopeSetController implements Serializable {

    @EJB
    private cub.facade.WorkSeqFacade ejbWorkSeqFacade;
    @EJB
    private cub.facade.UdColumnScopeMasterFacade ejbUdColumnScopeMasterFacade;
    @EJB
    private cub.facade.UdColumnScopeDetailFacade ejbUdColumnScopeDetailFacade;
    @EJB
    private cub.facade.RdDataColumnFacade ejbRdDataColumnFacade;
    @EJB
    private cub.facade.RdDataColumnOptionFacade ejbRdDataColumnOptionFacade;
    /*
    自定義欄位範圍列表
     */
    private List<UdColumnScopeMaster> master;
    /*
    資料範圍設定區
     */
    private List<RdDataColumnOption> detail;
    /*
    新增/編輯
     */
    private UdColumnScopeMaster item;
    /*
    新增/編輯
     */
    private UdColumnScopeDetail itemDetail;
    /*
    新增/編輯資料範圍
     */
    private RdDataColumnOptionPK itemColumnOption;
    /*
    新增/編輯資料範圍列表,內容組成(RdDataColumnOptionPK.getOptionCode())
     */
    private Map<Integer, String> tempList = new HashMap<Integer, String>();
    /*
    新增/編輯資料範圍列表,內容組成(RdDataColumnOptionPK.getOptionCode())
     */
    private List<String> itemColumnOptionList = new ArrayList<String>();
    /*
    新增/編輯欄位中文名稱下拉選單
     */
    private List<SelectItem> itemFieldCNNameMenu;
    /*
    新增/編輯欄位中文名稱,內容組成(RdDataColumnPK.getClassCode() + "+" + RdDataColumnPK.getTableName() + "+" + RdDataColumnPK.getColumnName())
     */
    private String itemDataColumn;
    /*
    待編輯自定義欄位範圍
     */
    private UdColumnScopeMaster currentItem;
    /*
    資料範圍設定區HeaderText
     */
    private String detailHeaderTextCode;
    /*
    資料範圍設定區HeaderText
     */
    private String detailHeaderTextName;
    /*
    自定義欄位範圍索引
     */
    private int currentIndex;
    /*
    欄位中文名稱
     */
    private String fieldCNName;
    /*
    新增/編輯資料範圍下拉選單
     */
    private List<SelectItem> rdDataColumnOptionMenu;
    /*
    all RD_DATA_COLUMN_OPTION list
     */
    private List<RdDataColumnOption> allOptions;
    /*
    新增/編輯資料範圍暫存選項
     */
    private String tempOptionCode = "";
    /*
    新增/編輯Dialog CommandButton value
     */
    private String editDialogLabel = "新增";

    @PostConstruct
    public void init() {
        this.master = new ArrayList<UdColumnScopeMaster>();
        this.master = ejbUdColumnScopeMasterFacade.findAllSort();
        this.item = new UdColumnScopeMaster();
//        this.item.setUdColumnCode(getWorkSeq(SeqTypeEnum.UDFIELD_CODE.toString()));
        this.currentItem = new UdColumnScopeMaster();
        if (this.master.isEmpty()) {
            this.master.add(this.item);
        } else {
            this.currentItem = this.master.get(0);
        }
        //頁面載入自定義欄位this.master的index
        currentIndex = 0;
        //intial新增/編輯時欄位中文名稱下拉選單
        this.itemFieldCNNameMenu = new ArrayList<SelectItem>();
        List<RdDataColumn> allRdDataColumn = ejbRdDataColumnFacade.findAll();
        for (RdDataColumn rd : allRdDataColumn) {
            RdDataColumnPK pk = rd.getRdDataColumnPK();
            itemFieldCNNameMenu.add(new SelectItem(pk.getClassCode() + "+" + pk.getTableName() + "+" + pk.getColumnName(), rd.getColumnChnName()));
        }
        //intial新增/編輯時資料範圍下拉選單
        this.rdDataColumnOptionMenu = new ArrayList<SelectItem>();
        this.allOptions = ejbRdDataColumnOptionFacade.findAll();
        for (RdDataColumnOption op : allOptions) {
            RdDataColumnOptionPK pk = op.getRdDataColumnOptionPK();
            rdDataColumnOptionMenu.add(new SelectItem(pk.getOptionCode(), op.getOptionName()));
        }
        //載入this.currentItem內容
        setItemDetail();
    }

    /*
    自定義欄位範圍代碼autocomplete
     */
    public List<String> searchColumnScopeMaster(String code) {
        List<String> result = ejbUdColumnScopeMasterFacade.findByCode(StringUtils.upperCase(code));
        return result;
    }

    /*
    自定義欄位範圍代碼autocomplete select
     */
    public void onItemSelect(SelectEvent event) {
        this.currentItem = ejbUdColumnScopeMasterFacade.find(event.getObject().toString());
        QueryUdColumnScopeDetailVO vo = new QueryUdColumnScopeDetailVO();
        vo.setClassCode(this.currentItem.getClassCode());
        vo.setTableName(this.currentItem.getTableName());
        vo.setColumnName(this.currentItem.getColumnName());
        this.detail = ejbRdDataColumnOptionFacade.findByMasterCode(this.currentItem.getUdColumnCode());
        this.fieldCNName = ejbRdDataColumnFacade.getFieldCNNameMenu(vo);
        this.detailHeaderTextCode = this.fieldCNName;
        this.detailHeaderTextName = this.fieldCNName;
    }

    public void columnOptionChange(int i) {
        if (this.tempList.size() > 0) {
            this.tempList.remove(i);
        }
        this.tempList.put(i, this.tempOptionCode);

    }

    public void columnOptionChangeForEdit(ValueChangeEvent e) {
        this.itemColumnOptionList.remove(e.getOldValue().toString());
        this.itemColumnOptionList.add(e.getNewValue().toString());
    }

    public void fieldCNNameMenuOnChange() {
        this.detailHeaderTextCode = this.fieldCNName;
        this.detailHeaderTextName = this.fieldCNName;
    }

    /*
    新增資料範圍(＋)
     */
    public void addColumnOptionList(ActionEvent event) {
        RdDataColumnOptionPK pk = this.allOptions.get(0).getRdDataColumnOptionPK();
        this.itemColumnOptionList.add("");

    }

    /*
    移除資料範圍(－)
     */
    public void removeColumnOptionList(ActionEvent event) {
        this.itemColumnOptionList.remove(this.itemColumnOptionList.size() - 1);
        this.tempList.remove(this.tempList.size() - 1);
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
        this.item = new UdColumnScopeMaster();
        this.editDialogLabel = "新增";
    }

    /*
    確認新增
     */
    public void save(ActionEvent event) {
        String[] strArray = StringUtils.split(this.itemDataColumn, "+");
        this.item.setClassCode(Short.valueOf(strArray[0]));
        this.item.setTableName(strArray[1]);
        this.item.setColumnName(strArray[2]);
        if (StringUtils.isBlank(this.item.getUdColumnCode())) {//新增
            String udColumnCode = getWorkSeq(SeqTypeEnum.UDFIELD_CODE.toString());
            this.item.setUdColumnCode(udColumnCode);
            ejbUdColumnScopeMasterFacade.create(this.item);
            if (null != tempList) {
                Set<Integer> set = tempList.keySet();
                for (Integer s : set) {
                    UdColumnScopeDetail d = new UdColumnScopeDetail();
                    UdColumnScopeDetailPK pk = new UdColumnScopeDetailPK(udColumnCode, tempList.get(s));
                    d.setUdColumnScopeDetailPK(pk);
                    ejbUdColumnScopeDetailFacade.create(d);
                }
            }
            ejbWorkSeqFacade.updateWorkSeq(SeqTypeEnum.UDFIELD_CODE.toString());
        } else {//編輯
            if (null != itemColumnOptionList) {
                ejbUdColumnScopeDetailFacade.removeByColumnCode(this.item.getUdColumnCode());
                for (String s : itemColumnOptionList) {
                    UdColumnScopeDetail d = new UdColumnScopeDetail();
                    UdColumnScopeDetailPK pk = new UdColumnScopeDetailPK(this.item.getUdColumnCode(), s);
                    d.setUdColumnScopeDetailPK(pk);
                    ejbUdColumnScopeDetailFacade.create(d);
                }
            }
            ejbUdColumnScopeMasterFacade.edit(item);
        }
        this.itemColumnOptionList.clear();
        this.tempList.clear();
        this.init();
    }

    /*
    點擊修改
     */
    public void edit() {
        this.itemColumnOptionList.clear();
        this.editDialogLabel = "編輯";
        this.item = this.currentItem;
        for (RdDataColumnOption op : this.detail) {
            this.itemColumnOptionList.add(op.getRdDataColumnOptionPK().getOptionCode());
        }
    }

    /*
    點擊刪除
     */
    public void delete(String udColumnCode) {
        UdColumnScopeMaster master = ejbUdColumnScopeMasterFacade.find(udColumnCode);
        ejbUdColumnScopeMasterFacade.remove(master);
    }

    /*
    點擊搜尋
     */
    public void search(ActionEvent event) {

    }

    public List<UdColumnScopeMaster> getMaster() {
        return master;
    }

    public void setMaster(List<UdColumnScopeMaster> master) {
        this.master = master;
    }

    public UdColumnScopeMaster getItem() {
        return item;
    }

    public void setItem(UdColumnScopeMaster item) {
        this.item = item;
    }

    public UdColumnScopeMaster getCurrentItem() {
        return currentItem;
    }

    public void setCurrentItem(UdColumnScopeMaster currentItem) {
        this.currentItem = currentItem;
    }

    public List<RdDataColumnOption> getDetail() {
        return detail;
    }

    public void setDetail(List<RdDataColumnOption> detail) {
        this.detail = detail;
    }

    public String getDetailHeaderTextCode() {
        return detailHeaderTextCode;
    }

    public void setDetailHeaderTextCode(String detailHeaderTextCode) {
        this.detailHeaderTextCode = detailHeaderTextCode;
    }

    public String getDetailHeaderTextName() {
        return detailHeaderTextName;
    }

    public void setDetailHeaderTextName(String detailHeaderTextName) {
        this.detailHeaderTextName = detailHeaderTextName;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    public String getFieldCNName() {
        return fieldCNName;
    }

    public void setFieldCNName(String fieldCNName) {
        this.fieldCNName = fieldCNName;
    }

    public UdColumnScopeDetail getItemDetail() {
        return itemDetail;
    }

    public void setItemDetail(UdColumnScopeDetail itemDetail) {
        this.itemDetail = itemDetail;
    }

    public List<SelectItem> getRdDataColumnOptionMenu() {
        return rdDataColumnOptionMenu;
    }

    public void setRdDataColumnOptionMenu(List<SelectItem> rdDataColumnOptionMenu) {
        this.rdDataColumnOptionMenu = rdDataColumnOptionMenu;
    }

    public RdDataColumnOptionPK getItemColumnOption() {
        return itemColumnOption;
    }

    public void setItemColumnOption(RdDataColumnOptionPK itemColumnOption) {
        this.itemColumnOption = itemColumnOption;
    }

    public List<SelectItem> getItemFieldCNNameMenu() {
        return itemFieldCNNameMenu;
    }

    public void setItemFieldCNNameMenu(List<SelectItem> itemFieldCNNameMenu) {
        this.itemFieldCNNameMenu = itemFieldCNNameMenu;
    }

    public String getItemDataColumn() {
        return itemDataColumn;
    }

    public void setItemDataColumn(String itemDataColumn) {
        this.itemDataColumn = itemDataColumn;
    }

    public List<String> getItemColumnOptionList() {
        return itemColumnOptionList;
    }

    public void setItemColumnOptionList(List<String> itemColumnOptionList) {
        this.itemColumnOptionList = itemColumnOptionList;
    }

    public List<RdDataColumnOption> getAllOptions() {
        return allOptions;
    }

    public void setAllOptions(List<RdDataColumnOption> allOptions) {
        this.allOptions = allOptions;
    }

    /*
    載入detail
     */
    private void setItemDetail() {
        QueryUdColumnScopeDetailVO vo = new QueryUdColumnScopeDetailVO();
        vo.setClassCode(this.currentItem.getClassCode());
        vo.setTableName(this.currentItem.getTableName());
        vo.setColumnName(this.currentItem.getColumnName());
        this.detail = ejbRdDataColumnOptionFacade.findByMasterCode(this.currentItem.getUdColumnCode());
        this.fieldCNName = ejbRdDataColumnFacade.getFieldCNNameMenu(vo);
        this.detailHeaderTextCode = this.fieldCNName;
        this.detailHeaderTextName = this.fieldCNName;
    }

    public String getTempOptionCode() {
        return tempOptionCode;
    }

    public void setTempOptionCode(String tempOptionCode) {
        this.tempOptionCode = tempOptionCode;
    }

    public Map<Integer, String> getTempList() {
        return tempList;
    }

    public void setTempList(Map<Integer, String> tempList) {
        this.tempList = tempList;
    }

    public String getEditDialogLabel() {
        return editDialogLabel;
    }

    public void setEditDialogLabel(String editDialogLabel) {
        this.editDialogLabel = editDialogLabel;
    }

    /*
    取得自定義欄位範圍代碼
     */
    private String getWorkSeq(String code) {
        String seqType = SeqTypeEnum.valueOf(code).getCode();
        Short no = ejbWorkSeqFacade.getWorkSeqNo(code);
        return seqType + StringUtils.leftPad(String.valueOf(no + 1), 4, "0");
    }
}
