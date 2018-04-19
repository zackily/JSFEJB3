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

import org.apache.commons.lang.StringUtils;
import org.primefaces.event.SelectEvent;

import cub.entities.ApiMaster;
import cub.entities.TrMaster;
import cub.entities.TrParameterInfo;
import cub.entities.TrParameterInfoPK;
import cub.facade.ApiMasterFacade;
import cub.facade.TrMasterFacade;
import cub.facade.TrParameterInfoFacade;
import cub.sso.UserSession;

/**
 * @author F123669 TR電文基本資料設定作業(RCMM06)
 */
@ManagedBean(name = "trMasterSetController")
@ViewScoped
public class TrMasterSetController extends AbstractController implements Serializable {
    @ManagedProperty("#{userSession}")
    private UserSession userSession;
    @EJB
    private TrMasterFacade ejbTrMasterFacade;
    @EJB
    private TrParameterInfoFacade ejbTrParameterInfoFacade;
    @EJB
    private ApiMasterFacade ejbApiMasterFacade;
    /*
     * TrMaster列表
     */
    private List<TrMaster> master;
    /*
     * 回傳參數資料設定區
     */
    private List<TrParameterInfo> detail;
    /*
     * 新增/修改
     */
    private TrMaster item;
    /*
     * 待修改TrMaster
     */
    private TrMaster currentItem;
    /*
     * 待修改TrParameterInfo
     */
    private List<TrParameterInfo> itemDetail;
    /*
     * 新增/修改Dialog CommandButton value
     */
    private String editDialogLabel = "新增";
    /*
     * TrMaster基本資料索引
     */
    private int currentIndex;

    @PostConstruct
    public void init() {
        this.checkSession(userSession);
        this.master = new ArrayList<>();
        this.master = ejbTrMasterFacade.findAll();
        this.item = new TrMaster();
        this.currentItem = new TrMaster();
        if (this.master.isEmpty()) {
            this.master.add(this.item);
        } else {
            this.currentItem = this.master.get(0);
        }
        // 頁面載入TrMaster List this.master的index
        currentIndex = 0;
        setItemDetail();
    }

    /*
     * TrMaster代碼autocomplete
     */
    public List<String> searchTrMaster(String code) {
        return ejbTrMasterFacade.findByCode(StringUtils.upperCase(code));
    }

    /*
     * TrMaster代碼autocomplete select
     */
    public void onItemSelect(SelectEvent event) {
        this.currentItem = ejbTrMasterFacade.find(event.getObject().toString());
    }

    /*
     * 新增資料範圍(＋)
     */
    public void addDetail(ActionEvent event) {
        TrParameterInfo tempDetail = new TrParameterInfo();
        this.itemDetail.add(tempDetail);
    }

    /*
     * 移除資料範圍(－)
     */
    public void removeDetail(TrParameterInfo d) {
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
        this.item = new TrMaster();
        this.itemDetail = new ArrayList<>();
        this.editDialogLabel = "新增";
    }

    /*
     * 確認新增
     */
    public void save(ActionEvent event) {
        for (int i = 0; i < this.itemDetail.size(); i++) {
            TrParameterInfo tri = this.itemDetail.get(i);
            tri.setLogDttm(new Date());
            tri.setLogUserId(this.userSession.getUser().getEmpId());
            TrParameterInfoPK id = new TrParameterInfoPK(this.item.getTrCode(), i + 1);
            tri.setId(id);
            ejbTrParameterInfoFacade.save(tri);
        }
        ejbTrMasterFacade.save(this.item);
        addMessage("新增成功", "新增成功");
        closeDialog();
        this.master = ejbTrMasterFacade.findAll();
        this.currentItem = this.master.get(this.currentIndex);
        create();
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
        List<ApiMaster> trCodeList = ejbApiMasterFacade.findByTrCode(this.currentItem.getTrCode());
        if (trCodeList.isEmpty()) {
            ejbTrMasterFacade.remove(this.currentItem);
            for (TrParameterInfo tri : this.detail) {
                ejbTrParameterInfoFacade.remove(tri);
            }
            this.init();
        } else {
            StringBuilder sb = new StringBuilder(100);
            for (ApiMaster am : trCodeList) {
                sb.append(am.getApiCode()).append(", ");
            }
            addMessage("此筆電文已被下列API引用" + sb.toString(), "此筆電文已被下列API引用" + sb.toString());
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

    public List<TrMaster> getMaster() {
        return master;
    }

    public void setMaster(List<TrMaster> master) {
        this.master = master;
    }

    public TrMaster getItem() {
        return item;
    }

    public void setItem(TrMaster item) {
        this.item = item;
    }

    public TrMaster getCurrentItem() {
        return currentItem;
    }

    public void setCurrentItem(TrMaster currentItem) {
        this.currentItem = currentItem;
    }

    public List<TrParameterInfo> getDetail() {
        return detail;
    }

    public void setDetail(List<TrParameterInfo> detail) {
        this.detail = detail;
    }

    public List<TrParameterInfo> getItemDetail() {
        return itemDetail;
    }

    public void setItemDetail(List<TrParameterInfo> itemDetail) {
        this.itemDetail = itemDetail;
    }

    public UserSession getUserSession() {
        return userSession;
    }

    public void setUserSession(UserSession userSession) {
        this.userSession = userSession;
    }

    private void setItemDetail() {
        this.detail = ejbTrParameterInfoFacade.findByTrCode(this.currentItem.getTrCode());
    }

    private void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
