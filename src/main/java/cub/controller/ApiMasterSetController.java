package cub.controller;

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

import cub.entities.ApiMaster;
import cub.entities.RdDataClass;
import cub.entities.RdOptionItem;
import cub.entities.TrMaster;
import cub.facade.ApiMasterFacade;
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
    /*
     * ApiMaster列表
     */
    private List<ApiMaster> master;
    /*
     * 新增/修改
     */
    private ApiMaster item;
    /*
     * 待修改ApiMaster
     */
    private ApiMaster currentItem;
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

    @PostConstruct
    public void init() {
        this.checkSession(userSession);
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
        this.item = new ApiMaster();
        this.editDialogLabel = "新增";
    }

    /*
     * 確認新增
     */
    public void save(ActionEvent event) {
        this.item.setLogDttm(new Date());
        this.item.setLogUserId(this.userSession.getUser().getEmpId());
        ejbApiMasterFacade.save(this.item);
        addMessage("新增成功", "新增成功");
        this.currentItem = this.master.get(this.currentIndex);
        init();
        create();
    }

    /*
     * 點擊修改
     */
    public void edit() {
        this.editDialogLabel = "修改";
        this.item = this.currentItem;

    }

    /*
     * 點擊刪除
     */
    public void delete() {
        ejbApiMasterFacade.remove(this.currentItem);
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

    private void genSystemCodeMenu() {
        this.systemCodeMenu = new ArrayList<>();
        List<RdOptionItem> allRoi = ejbRdOptionItemFacade.findAllSortByItemCode();
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
            this.trCodeMenu.add(new SelectItem(tr.getTrCode(), tr.getTrDesc()));
        }
    }

    private void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
