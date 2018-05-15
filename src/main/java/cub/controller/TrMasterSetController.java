package cub.controller;

import java.io.IOException;
import java.io.Serializable;
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
import cub.entities.TrMaster;
import cub.entities.TrParameterInfo;
import cub.entities.TrParameterInfoPK;
import cub.entities.TrSystemUrl;
import cub.facade.ApiMasterFacade;
import cub.facade.TrMasterFacade;
import cub.facade.TrParameterInfoFacade;
import cub.facade.TrSystemUrlFacade;
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
    @EJB
    private TrSystemUrlFacade ejbTrSystemUrlFacade;
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

    private int tempVar;

    private Set<String> tempParaName;

    private List<TrParameterInfo> tempDetail;

    private List<SelectItem> selTrSystemUrl;

    @PostConstruct
    public void init() {
        try {
            this.checkSession(userSession);
        } catch (IOException e) {
            e.printStackTrace();
        }
        genTrSystemUrlMenu();
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
        setItemDetail();
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
        this.tempParaName = new HashSet<>();
        this.tempDetail = new ArrayList<>();
        for (int i = 0; i < this.itemDetail.size(); i++) {
            TrParameterInfo tri = this.itemDetail.get(i);
            tri.setLogDttm(new Date());
            tri.setLogUserId(this.userSession.getUser().getEmpId());
            TrParameterInfoPK id = new TrParameterInfoPK(this.item.getTrCode(), i + 1);
            tri.setId(id);
            this.tempParaName.add(tri.getParameterName());
            this.tempDetail.add(tri);
        }
        if (this.tempDetail.size() == this.tempParaName.size()) {
            ejbTrParameterInfoFacade.removeByTrCode(this.item.getTrCode());
            for (TrParameterInfo tri : tempDetail) {
                ejbTrParameterInfoFacade.save(tri);
            }
            ejbTrMasterFacade.save(this.item);
            addMessage("新增成功", "新增成功");
            closeDialog();
            this.master = ejbTrMasterFacade.findAll();
            this.currentItem = this.master.get(this.currentIndex);
            create();
        } else {
            addMessage("欄位名稱重覆,請重新輸入!", "欄位名稱重覆,請重新輸入!");
        }
        setItemDetail();
    }

    /*
     * 點擊修改
     */
    public void edit() {
        this.editDialogLabel = "修改";
        this.item = this.currentItem;
        this.itemDetail = this.detail;
        TrSystemUrl entity = this.ejbTrSystemUrlFacade.find(this.currentItem.getSystemCode());
        this.item.setSystemUrl(entity.getSystemUrl());
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
        TrParameterInfo info = this.itemDetail.get(this.tempVar);
        if (newValue.equals("X")) {
            info.setParameterDataDecDigit(0);
            this.itemDetail.set(this.tempVar, info);
        }
    }

    public void onTrSystemUrlChange(ValueChangeEvent e) {
        String scode = e.getNewValue().toString();
        TrSystemUrl entity = this.ejbTrSystemUrlFacade.find(scode);
        this.item.setSystemUrl(entity.getSystemUrl());
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

    public int getTempVar() {
        return tempVar;
    }

    public void setTempVar(int tempVar) {
        this.tempVar = tempVar;
    }

    public List<SelectItem> getSelTrSystemUrl() {
        return selTrSystemUrl;
    }

    public void setSelTrSystemUrl(List<SelectItem> selTrSystemUrl) {
        this.selTrSystemUrl = selTrSystemUrl;
    }

    private void genTrSystemUrlMenu() {
        this.selTrSystemUrl = new ArrayList<>();
        List<TrSystemUrl> allUrl = ejbTrSystemUrlFacade.findAll();
        for (TrSystemUrl tsu : allUrl) {
            this.selTrSystemUrl.add(new SelectItem(tsu.getSystemCode(), tsu.getSystemName()));
        }
    }

    private void setItemDetail() {
        this.detail = ejbTrParameterInfoFacade.findByTrCode(this.currentItem.getTrCode());
        TrSystemUrl entity = this.ejbTrSystemUrlFacade.find(this.currentItem.getSystemCode());
        this.currentItem.setSystemName(entity.getSystemName());
        this.currentItem.setSystemUrl(entity.getSystemUrl());
    }

    private void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
