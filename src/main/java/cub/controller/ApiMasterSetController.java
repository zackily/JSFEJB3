package cub.controller;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import cub.entities.ApiParameterInfo;
import cub.entities.ApiParameterInfoPK;
import cub.entities.RdDataClass;
import cub.entities.RdOptionItem;
import cub.entities.TrMaster;
import cub.facade.ApiMasterFacade;
import cub.facade.ApiParameterInfoFacade;
import cub.facade.RdDataClassFacade;
import cub.facade.RdOptionItemFacade;
import cub.facade.TrMasterFacade;
import cub.sso.UserSession;

/**
 * @author F123669 API基本資料設定作業(RCMM05)
 */
@ManagedBean(name = "apiMasterSetController")
@ViewScoped
public class ApiMasterSetController extends AbstractController implements Serializable {
    @ManagedProperty("#{userSession}")
    private UserSession userSession;
    @EJB
    private ApiMasterFacade ejbApiMasterFacade;
    @EJB
    private RdDataClassFacade ejbRdDataClassFacade;
    @EJB
    private RdOptionItemFacade ejbRdOptionItemFacade;
    @EJB
    private TrMasterFacade ejbTrMasterFacade;
    @EJB
    private ApiParameterInfoFacade ejbApiParameterInfoFacade;

    /*
     * ApiMaster列表
     */
    private List<ApiMaster> master;
    /*
     * 回傳參數資料設定區
     */
    private List<ApiParameterInfo> detail;
    /*
     * 新增/修改
     */
    private ApiMaster item;
    /*
     * 待修改ApiMaster
     */
    private ApiMaster currentItem;
    /*
     * 待修改ApiParameterInfo
     */
    private List<ApiParameterInfo> itemDetail;
    /*
     * 新增/修改Dialog CommandButton value
     */
    private String editDialogLabel = "新增";
    /*
     * API基本資料索引
     */
    private int currentIndex;
    /*
     * 回傳電文代碼下拉選單
     */
    private List<SelectItem> trCodeMenu;
    /*
     * 資料類別下拉選單
     */
    private List<SelectItem> classCodeMenu;
    /*
     * 系統代碼下拉選單
     */
    private List<SelectItem> systemCodeMenu;

    private int tempVar;

    private Set<String> tempParaName;

    private List<ApiParameterInfo> tempDetail;

    @PostConstruct
    public void init() {
        try {
            this.checkSession(userSession);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.master = new ArrayList<>();
        this.master = ejbApiMasterFacade.findAll();
        this.item = new ApiMaster();
        this.currentItem = new ApiMaster();
        if (this.master.isEmpty()) {
            this.master.add(this.item);
        } else {
            this.currentItem = this.master.get(0);
        }
        // 頁面載入ApiMaster List this.master的index
        currentIndex = 0;
        genTrCodeMenu();
        genClassCodeMenu();
        genSystemCodeMenu();
        setItemDetail();
    }

    /*
     * ApiMaster代碼autocomplete
     */
    public List<String> searchApiMaster(String code) {
        return ejbApiMasterFacade.findByCode(StringUtils.upperCase(code));
    }

    /*
     * ApiMaster代碼autocomplete select
     */
    public void onItemSelect(SelectEvent event) {
        this.currentItem = ejbApiMasterFacade.find(event.getObject().toString());
    }

    public void rtnTypeChange(ValueChangeEvent event) {
        if (event.getNewValue().toString().equals("2")) {
            this.item.setOutputTrCode("");
        }
    }

    /*
     * 新增資料範圍(＋)
     */
    public void addDetail(ActionEvent event) {
        ApiParameterInfo tempDetail = new ApiParameterInfo();
        this.itemDetail.add(tempDetail);
    }

    /*
     * 移除資料範圍(－)
     */
    public void removeDetail(ApiParameterInfo d) {
        this.itemDetail.remove(d);
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
        this.item = new ApiMaster();
        this.itemDetail = new ArrayList<>();
        this.editDialogLabel = "新增";
    }

    /*
     * 確認新增
     */
    public void save(ActionEvent event) {
        this.tempParaName = new HashSet<>();
        this.tempDetail = new ArrayList<>();
        if (this.item.getRtnType().toString().equals("1") && null != this.item.getOutputTrCode()) {
            this.item.setLogDttm(new Date());
            this.item.setLogUserId(this.userSession.getUser().getEmpId());
            if (this.currentItem.getRtnType().compareTo(BigDecimal.ONE) == 0) {
                for (int i = 0; i < this.itemDetail.size(); i++) {
                    ApiParameterInfo tri = this.itemDetail.get(i);
                    tri.setLogDttm(new Date());
                    tri.setLogUserId(this.userSession.getUser().getEmpId());
                    ApiParameterInfoPK id = new ApiParameterInfoPK(this.item.getApiCode(), i + 1);
                    tri.setId(id);
                    this.tempParaName.add(tri.getParameterName());
                    this.tempDetail.add(tri);
                }
            }
            if (this.tempDetail.size() == this.tempParaName.size()) {
                ejbApiParameterInfoFacade.removeByApiCode(this.item.getApiCode());
                for (ApiParameterInfo tri : tempDetail) {
                    ejbApiParameterInfoFacade.save(tri);
                }
                ejbApiMasterFacade.save(this.item);
                addMessage("新增成功", "新增成功");
                this.master = ejbApiMasterFacade.findAll();
                this.currentItem = this.master.get(this.currentIndex);
                closeDialog();
                create();
            } else {
                addMessage("欄位名稱重覆,請重新輸入!", "欄位名稱重覆,請重新輸入!");
                setItemDetail();
            }
        }
    }

    /*
     * 點擊修改
     */
    public void edit() {
        this.editDialogLabel = "修改";
        this.item = this.currentItem;
        this.itemDetail = this.detail;
    }

    /*
     * 點擊刪除
     */
    public void delete() {
        ejbApiMasterFacade.remove(this.currentItem);
        for (ApiParameterInfo tri : this.detail) {
            ejbApiParameterInfoFacade.remove(tri);
        }
        this.init();
    }

    public void onParaNameChangeByVar(int i) {
        // for inputText box binding value
    }

    public void onParaDescChangeByVar(int i) {
        // for inputText box binding value
    }

    public void onParaDataChangeByVar(int i) {
        // for inputText box binding value
    }

    public void onParaTypeChangeByVar(int i) {
        // for inputText box binding value
    }

    public void onParaMemoChangeByVar(int i) {
        // for inputText box binding value
    }

    public void onDataTypeChangeByVar(int i) {
        this.tempVar = i;
    }

    public void onDataTypeChange(ValueChangeEvent e) {
        String newValue = null == e.getNewValue() ? "" : e.getNewValue().toString();
        ApiParameterInfo info = this.itemDetail.get(this.tempVar);
        if (newValue.equals("X")) {
            info.setParameterDataDecDigit(0);
            this.itemDetail.set(this.tempVar, info);
        }
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

    public List<ApiMaster> getMaster() {
        return master;
    }

    public void setMaster(List<ApiMaster> master) {
        this.master = master;
    }

    public ApiMaster getItem() {
        return item;
    }

    public void setItem(ApiMaster item) {
        this.item = item;
    }

    public ApiMaster getCurrentItem() {
        return currentItem;
    }

    public void setCurrentItem(ApiMaster currentItem) {
        this.currentItem = currentItem;
    }

    public List<SelectItem> getTrCodeMenu() {
        return trCodeMenu;
    }

    public void setTrCodeMenu(List<SelectItem> trCodeMenu) {
        this.trCodeMenu = trCodeMenu;
    }

    public List<SelectItem> getClassCodeMenu() {
        return classCodeMenu;
    }

    public void setClassCodeMenu(List<SelectItem> classCodeMenu) {
        this.classCodeMenu = classCodeMenu;
    }

    public List<SelectItem> getSystemCodeMenu() {
        return systemCodeMenu;
    }

    public void setSystemCodeMenu(List<SelectItem> systemCodeMenu) {
        this.systemCodeMenu = systemCodeMenu;
    }

    public UserSession getUserSession() {
        return userSession;
    }

    public void setUserSession(UserSession userSession) {
        this.userSession = userSession;
    }

    public List<ApiParameterInfo> getDetail() {
        return detail;
    }

    public void setDetail(List<ApiParameterInfo> detail) {
        this.detail = detail;
    }

    public List<ApiParameterInfo> getItemDetail() {
        return itemDetail;
    }

    public void setItemDetail(List<ApiParameterInfo> itemDetail) {
        this.itemDetail = itemDetail;
    }

    public int getTempVar() {
        return tempVar;
    }

    public void setTempVar(int tempVar) {
        this.tempVar = tempVar;
    }

    private void genSystemCodeMenu() {
        this.systemCodeMenu = new ArrayList<>();
        List<RdOptionItem> allRoi = ejbRdOptionItemFacade.findAllSystemCode();
        for (RdOptionItem roi : allRoi) {
            this.systemCodeMenu.add(new SelectItem(roi.getRdOptionItemPK().getItemCode(), roi.getItemName()));
        }
    }

    private void genClassCodeMenu() {
        this.classCodeMenu = new ArrayList<>();
        List<RdDataClass> allRdc = ejbRdDataClassFacade.findAllSort();
        for (RdDataClass rdc : allRdc) {
            this.classCodeMenu.add(new SelectItem(rdc.getClassCode(), rdc.getClassName()));
        }
    }

    private void genTrCodeMenu() {
        this.trCodeMenu = new ArrayList<>();
        List<TrMaster> allTr = ejbTrMasterFacade.findAllSort();
        for (TrMaster tr : allTr) {
            this.trCodeMenu.add(new SelectItem(tr.getTrCode(), tr.getTrCode() + "_" + tr.getTrDesc()));
        }
    }

    private void setItemDetail() {
        if (this.currentItem.getRtnType().compareTo(BigDecimal.ONE) == 0) {
            this.detail = ejbApiParameterInfoFacade.findByApiCode(this.currentItem.getApiCode());
        }
    }

    private void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
