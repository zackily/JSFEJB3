package cub.controller;

import cub.entities.MgSetMaster;
import cub.controller.util.JsfUtil;
import cub.controller.util.JsfUtil.PersistAction;
import cub.controller.util.Utils;
import cub.dev.themes.ThemeSwitcherView;
import cub.entities.MgSetDetail;
import cub.entities.MgSetDetailCustAct;
import cub.entities.MgSetDetailSecCfg;
import cub.enums.MgSetMasterStatus;
import cub.facade.FundFacade;
import cub.facade.MgSetDetailCustActFacade;
import cub.facade.MgSetDetailSecCfgFacade;
import cub.facade.MgSetMasterFacade;
import cub.invest.aum.Fund;
import cub.invest.aum.InvCorp;
import cub.sso.UserSession;
import cub.vo.AumFundVO;
import cub.vo.MgMasterVO;
import java.io.IOException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import oracle.net.aso.m;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean(name = "mgSetMasterController")
@ViewScoped
public class MgSetMasterController implements Serializable {

    @EJB
    private cub.facade.MgSetMasterFacade mgSetMasterFacade;

    @EJB
    private cub.facade.MgSetDetailFacade mgSetDetailFacade;

    @EJB
    private cub.facade.InvCorpFacade invCorpFacade;

    @EJB
    private FundFacade fundFacade;

    @EJB
    private MgSetDetailSecCfgFacade mgSetDetailSecCfgFacade;

    @EJB
    private MgSetDetailCustActFacade mgSetDetailCustActFacade;

    private List<MgSetMaster> items = null;
    private MgSetMaster selected;

    private MgSetDetail mgSetDetail;
    private List<MgSetDetail> detailItems = null;

    private List<String> idnList = null;

    private Date start_date;
    private Date end_date;
    private Date last_settle_date;

    //Query VO
    private AumFundVO queryVO;
    private MgMasterVO masterVO;

    //for wizard
    private boolean skip;

    //for multiple checkbox
    private List<InvCorp> invCorp;
    private Map<String, String> invCorpFundMapping;
    private List<Fund> fundId;

    //for selected
    private List<String> selectedInvCorp;
    private List<String> selectedFundId;

    @EJB
    private Utils utils;    

    @ManagedProperty("#{userSession}")
    private UserSession userSession;

    @PostConstruct
    public void init() {
        
        findByStatusNotInMaster();
        queryVO = new AumFundVO();
        masterVO = new MgMasterVO();
        invCorp = invCorpFacade.findAll();
        invCorpFundMapping = new HashMap<String, String>();
        fundId = new ArrayList<Fund>();
        mgSetDetail = new MgSetDetail();

        idnList = new ArrayList<String>();
        selectedInvCorp = new ArrayList<String>();
        selectedFundId = new ArrayList<String>();
    }

    public void reset() {
        System.out.println("test");
        RequestContext.getCurrentInstance().reset(":MgSetMasterCreateForm");
    }

    public List<InvCorp> getInvCorp() {
        return invCorp;
    }

    public void setInvCorp(List<InvCorp> invCorp) {
        this.invCorp = invCorp;
    }

    public List<Fund> getFundId() {
        return fundId;
    }

    public void setFundId(List<Fund> fundId) {
        this.fundId = fundId;
    }

    public MgSetMaster getSelected() {
        return selected;
    }

    public void setSelected(MgSetMaster selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private MgSetMasterFacade getFacade() {
        return mgSetMasterFacade;
    }

    public void search() {
        items.clear();
        items = mgSetMasterFacade.findByMgMasterVO(masterVO);
        masterVO = new MgMasterVO();
    }

    public void searchAll() {
        items.clear();
        items = mgSetMasterFacade.findAll();
    }

    public void handleFileUpload(FileUploadEvent event) {
        if (!idnList.isEmpty()) {
            idnList.clear();
        }
        try {
            System.out.println(event.getFile().getFileName());
            UploadedFile uploadedFile = event.getFile();
            idnList = IOUtils.readLines(uploadedFile.getInputstream());
        } catch (IOException ex) {
            Logger.getLogger(MgSetMasterController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public MgSetMaster prepareCreate() {
        this.selected = null;
        this.selected = new MgSetMaster();
        mgSetDetail = new MgSetDetail();
        selected.setMgActMType("1");
        selected.setMgActMRateSet("1");
        selected.setMgActMSaleChnl("2");
        selected.setMgActMChargeObj("C");
//        mgSetDetail.setMgActDSecType("1");
        start_date = new Date();
        end_date = new Date();
        last_settle_date = new Date();
        initializeEmbeddableKey();
        return selected;
    }
    
    public void prepareUpdate(MgSetMaster item) {
        this.selected = item;       
    }

    public void findByMasterId(MgSetMaster m) {
        this.detailItems = (List) m.getMgSetDetailCollection();
    }
    public void update(String status){
        updateMaster(selected,status);
         FacesMessage msg = new FacesMessage(MgSetMasterStatus.valueOf(status).getStatus()+"主活動編號為" + selected.getMgActMCode());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    public void confirm(String status){
        confirmMaster(selected,status);
        FacesMessage msg = new FacesMessage(MgSetMasterStatus.valueOf(status).getStatus()+"主活動編號為" + selected.getMgActMCode());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void updateMaster(MgSetMaster item, String status) {
        item.setCrtDate(new Date());
        item.setCrtEmpId(userSession.getUser().getEmpId());
        item.setCrtEmpName(userSession.getUser().getEmpname());
        if (StringUtils.isNotEmpty(status)) {
            item.setStatus(MgSetMasterStatus.valueOf(status));
        }
        mgSetMasterFacade.save(item);
        findByStatusNotInMaster();
    }

    public void confirmMaster(MgSetMaster item, String status) {
        item.setCfmDate(new Date());
        item.setCfmEmpId(userSession.getUser().getEmpId());
        item.setCfmEmpName(userSession.getUser().getEmpname());
        item.setStatus(MgSetMasterStatus.valueOf(status));
        mgSetMasterFacade.save(item);
        findByStatusNotInMaster();
    }

    private void findByStatusNotInMaster() {
        items = mgSetMasterFacade.findByStatusNotInMgMaster(MgSetMasterStatus.DELETE);
    }

    public void addDetail(MgSetMaster m) {
        this.selected = m;
        mgSetDetail = new MgSetDetail();
        mgSetDetail.setMgActDSecType("3");
        mgSetDetail.setMgActSetType("F");
        mgSetDetail.setMgActDSaleChnl("2");

        start_date = new Date();
        end_date = new Date();
        last_settle_date = new Date();
        System.out.println(m.getId());
    }

    public void importDetail(MgSetMaster m) {
        this.selected = m;
        detailItems = new ArrayList<MgSetDetail>();
    }

    public void create() {
        this.selected.setMgActLastSettleDate(toStringDate(last_settle_date, "yyyyMMdd"));
        this.selected.setMgActMCode(this.showMgSetMasterCode());
        this.selected.setCrtDate(new Date());
        this.selected.setCrtEmpId(getUserSession().getUser().getEmpId());
        this.selected.setCrtEmpName(getUserSession().getUser().getEmpname());
        this.selected.setStatus(MgSetMasterStatus.SEND);

        persist(PersistAction.CREATE, "管理費主活動案設定完成");
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void onRowEdit(RowEditEvent event) {
        MgSetMaster m = (MgSetMaster) event.getObject();
        mgSetMasterFacade.edit(m);
        FacesMessage msg = new FacesMessage("已修改管理費主活動案編號為" + m.getMgActMCode());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("取消修改");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void createDetail() {
        mgSetDetail.setMgActDCode(selected.getMgActMCode() + this.showMgSetDetailCode());
        mgSetDetail.setMgActDSeq(this.showMgSetDetailCode());
        mgSetDetail.setMgActDStartDate(toStringDate(start_date, "yyyyMMdd"));
        mgSetDetail.setMgActDEndDate(toStringDate(end_date, "yyyyMMdd"));
        mgSetDetail.setMgSetMasterId(selected);
        if (selected.getMgSetDetailCollection() == null || selected.getMgSetDetailCollection().isEmpty()) {
            detailItems = new ArrayList<MgSetDetail>();
            detailItems.add(mgSetDetail);
            selected.setMgSetDetailCollection(detailItems);
        } else {
            selected.getMgSetDetailCollection().add(mgSetDetail);
        }

//       mgSetDetail.setMgSetMasterId(selected);
//        update();
//TODO 以下是不對的寫法，暫時用
//基金及公司新增
        getFacade().edit(selected);
        for (String fid : selectedFundId) {
            System.out.println(fid);
            Fund f = fundFacade.findByFundId(fid);
            MgSetDetailSecCfg msdsc = new MgSetDetailSecCfg();
            msdsc.setMgActCode(mgSetDetail.getMgActDCode());
            msdsc.setMgActSubCode(mgSetDetail.getMgActDSeq());
            msdsc.setMgActCounterparty(f.getFundID().substring(0, 4));
            msdsc.setMgActFundNo(f.getFundID());
            mgSetDetailSecCfgFacade.create(msdsc);
        }
//客戶IDN
        for (String idn : idnList) {
            MgSetDetailCustAct cust = new MgSetDetailCustAct();
            cust.setMgActCode(mgSetDetail.getMgActDCode());
            cust.setMgActSubCode(mgSetDetail.getMgActDSeq());
            cust.setCustomerId(idn);
            cust.setStatus("1");
            mgSetDetailCustActFacade.create(cust);
        }

        FacesMessage msg = new FacesMessage("儲存成功");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("MgSetMasterUpdated"));
    }

    public void updateFundList() {

        fundId.clear();
        for (String invcorp : selectedInvCorp) {
//            List<Fund> funds_result = fundFacade.findByInvCorpId("0", invcorp.getCorpID());
            fundId.addAll((List) fundFacade.findByInvCorpId("0", invcorp));

        }
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("MgSetMasterDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<MgSetMaster> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public MgSetMaster getMgSetMaster(Long id) {
        return getFacade().find(id);
    }

    public List<MgSetMaster> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<MgSetMaster> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = MgSetMaster.class)
    public static class MgSetMasterControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MgSetMasterController controller = (MgSetMasterController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "mgSetMasterController");
            return controller.getMgSetMaster(getKey(value));
        }

        Long getKey(String value) {
            Long key;
            key = new Long(value);
            return key;
        }

        String getStringKey(Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof MgSetMaster) {
                MgSetMaster o = (MgSetMaster) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), MgSetMaster.class.getName()});
                return null;
            }
        }

    }

    public String toMgMType(String str) {
        if (str.equalsIgnoreCase("1")) {
            return "AUM";
        } else if (str.equalsIgnoreCase("2")) {
            return "成本";
        } else {
            return "error";
        }
    }

    public String toMgActMRateSet(String str) {
        if (str.equalsIgnoreCase("1")) {
            return "Low";
        } else if (str.equalsIgnoreCase("2")) {
            return "High";
        } else {
            return "error";
        }
    }

    public int toCollectionSize(Collection selected) {

        if (selected == null || selected.isEmpty()) {
            return 0;
        } else {
            return selected.size();
        }
    }

    public MgSetDetail getMgSetDetail() {
        return mgSetDetail;
    }

    public void setMgSetDetail(MgSetDetail mgSetDetail) {
        this.mgSetDetail = mgSetDetail;
    }

    public List<MgSetDetail> getDetailItems() {
        return detailItems;
    }

    public void setDetailItems(List<MgSetDetail> detailItems) {
        this.detailItems = detailItems;
    }

    public String toStringDate(Date d, String pattern) {
        if (d != null) {
            DateTime s = new DateTime(d);
            return s.toString(pattern);
        } else {
            return "N/A";
        }
    }

    public Date toDate(String yyyyMMdd) {
        return new DateTime(yyyyMMdd).toDate();
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public Date getLast_settle_date() {
        return last_settle_date;
    }

    public void setLast_settle_date(Date last_settle_date) {
        this.last_settle_date = last_settle_date;
    }

    public AumFundVO getQueryVO() {
        return queryVO;
    }

    public void setQueryVO(AumFundVO queryVO) {
        this.queryVO = queryVO;
    }

    public MgMasterVO getMasterVO() {
        return masterVO;
    }

    public void setMasterVO(MgMasterVO masterVO) {
        this.masterVO = masterVO;
    }

    public String onFlowProcess(FlowEvent event) {
        if (skip) {
            skip = false;   //reset in case user goes back
            return "confirm";
        } else {
            return event.getNewStep();
        }
    }

    public String showMgSetMasterCode() {
        MgSetMaster lastObj = mgSetMasterFacade.findByLastIdMgMaster("");
        int seq = 0;
        if (lastObj != null) {
            seq = lastObj.getId().intValue();
        }
        return utils.toPlusOneString(seq, 5);
    }

    public String showMgSetDetailCode() {
        MgSetDetail lastObj = mgSetDetailFacade.findByLastSeqMgSetDetail(selected);
        int seq = 0;
        if (lastObj != null) {
            seq = lastObj.getId().intValue();
        }
        return utils.toPlusOneString(seq, 2);
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public List<String> getSelectedInvCorp() {
        return selectedInvCorp;
    }

    public void setSelectedInvCorp(List<String> selectedInvCorp) {
        this.selectedInvCorp = selectedInvCorp;
    }

    public List<String> getSelectedFundId() {
        return selectedFundId;
    }

    public void setSelectedFundId(List<String> selectedFundId) {
        this.selectedFundId = selectedFundId;
    }

    public List<String> getIdnList() {
        return idnList;
    }

    public void setIdnList(List<String> idnList) {
        this.idnList = idnList;
    }

    public UserSession getUserSession() {
        return userSession;
    }

    public void setUserSession(UserSession userSession) {
        this.userSession = userSession;
    }

}
