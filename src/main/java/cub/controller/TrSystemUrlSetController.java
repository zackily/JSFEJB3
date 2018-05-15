package cub.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
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

import cub.entities.TrSystemUrl;
import cub.facade.TrSystemUrlFacade;
import cub.sso.UserSession;

/**
 * @author F123669 TR System Url資料設定作業(RCMMXX)
 */
@ManagedBean(name = "trSystemUrlSetController")
@ViewScoped
public class TrSystemUrlSetController extends AbstractController implements Serializable {
    @ManagedProperty("#{userSession}")
    private UserSession userSession;
    @EJB
    private TrSystemUrlFacade ejbTrSystemUrlFacade;
    /*
     * TrMaster列表
     */
    private List<TrSystemUrl> master;
    /*
     * 新增/修改
     */
    private TrSystemUrl item;
    /*
     * 待修改TrMaster
     */
    private TrSystemUrl currentItem;
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
        try {
            this.checkSession(userSession);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.master = new ArrayList<>();
        this.master = ejbTrSystemUrlFacade.findAll();
        this.item = new TrSystemUrl();
        this.currentItem = new TrSystemUrl();
        if (this.master.isEmpty()) {
            this.master.add(this.item);
        } else {
            this.currentItem = this.master.get(0);
        }
        // 頁面載入TrMaster List this.master的index
        currentIndex = 0;
    }

    /*
     * TrMaster代碼autocomplete
     */
    public List<String> searchTrSystemUrl(String code) {
        return ejbTrSystemUrlFacade.findByCode(StringUtils.upperCase(code));
    }

    /*
     * TrMaster代碼autocomplete select
     */
    public void onItemSelect(SelectEvent event) {
        this.currentItem = ejbTrSystemUrlFacade.find(event.getObject().toString());
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
        this.item = new TrSystemUrl();
        this.editDialogLabel = "新增";
    }

    /*
     * 確認新增
     */
    public void save(ActionEvent event) {
        ejbTrSystemUrlFacade.save(this.item);
        addMessage("新增成功", "新增成功");
        this.master = ejbTrSystemUrlFacade.findAll();
        this.currentItem = this.master.get(this.currentIndex);
        closeDialog();
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
        ejbTrSystemUrlFacade.remove(this.currentItem);
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

    public List<TrSystemUrl> getMaster() {
        return master;
    }

    public void setMaster(List<TrSystemUrl> master) {
        this.master = master;
    }

    public TrSystemUrl getItem() {
        return item;
    }

    public void setItem(TrSystemUrl item) {
        this.item = item;
    }

    public TrSystemUrl getCurrentItem() {
        return currentItem;
    }

    public void setCurrentItem(TrSystemUrl currentItem) {
        this.currentItem = currentItem;
    }

    public UserSession getUserSession() {
        return userSession;
    }

    public void setUserSession(UserSession userSession) {
        this.userSession = userSession;
    }

    private void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
