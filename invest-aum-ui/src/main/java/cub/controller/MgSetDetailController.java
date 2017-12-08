package cub.controller;

import cub.entities.MgSetDetail;
import cub.controller.util.JsfUtil;
import cub.controller.util.JsfUtil.PersistAction;
import cub.controller.util.Utils;
import cub.entities.AcctFundHoldingRenew;
import cub.entities.EricMgFeeMonth;
import cub.entities.EricMgFeeMonthDetail;
import cub.entities.MgCustActList;
import cub.entities.MgSetDetailChlCfg;
import cub.entities.MgSetDetailRngCfg;
import cub.entities.MgSetMaster;
import cub.enums.MgSetMasterStatus;
import cub.facade.AcctFundHoldingRenewFacade;
import cub.facade.EricMgFeeMonthDetailFacade;
import cub.facade.EricMgFeeMonthFacade;
import cub.facade.MgCustActListFacade;
import cub.facade.MgSetDetailChlCfgFacade;
import cub.facade.MgSetDetailFacade;
import cub.facade.MgSetDetailRngCfgFacade;
import cub.facade.MgSetMasterFacade;
import cub.sso.UserSession;
import cub.vo.MgDetailVO;
import cub.vo.ProductVO;
import java.io.IOException;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import org.apache.commons.io.IOUtils;
import org.joda.time.DateTime;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean(name = "mgSetDetailController")
@ViewScoped
public class MgSetDetailController implements Serializable {
    
    @ManagedProperty("#{userSession}")
    private UserSession userSession;
    
    @EJB
    private cub.facade.MgSetDetailFacade mgSetDetailFacade;
    
    @EJB
    private MgSetMasterFacade mgSetMasterFacade;
    
    @EJB
    private MgSetDetailRngCfgFacade mgSetDetailRngCfgFacade;
    
    @EJB
    private MgSetDetailChlCfgFacade mgSetDetailChlCfgFacade;
    
    private List<MgSetMaster> mgSetMasterList;
    
    private List<MgSetDetail> items = null;
    //TODO 暫時
    //private List<String> productClassList;
    private List<String> productTypeList;
    private List<String> productSeriesList;
    
    private List<MgSetDetailRngCfg> rangeList;
    private MgSetDetailRngCfg selectedRange;
    private MgSetDetail selected;
    private MgSetDetail mgSetDetail;
    private Date mgSetActDetailStartDate;
    private Date mgSetActDetailEndDate;
    private List<String> selectedChannel;
    private boolean skip;
    //VO
    private MgDetailVO mgDetailVO;
    private ProductVO productVO;
    
    @EJB
    private Utils utils;
    
    private String queryMonCustId;
    private Date queryMonStartDate;
    private Date queryMonEndDate;
    private List<EricMgFeeMonth> emfList;
    @EJB
    private EricMgFeeMonthFacade ericMgMonthFacade;
    
    private String queryMonDCustId;
    private Date queryMonDStartDate;
    private Date queryMonDEndDate;
    private List<EricMgFeeMonthDetail> emfDlist;
    @EJB
    private EricMgFeeMonthDetailFacade ericMgMonthDetailFacade;
    
    private String queryHoldingCustId;
    private Date queryHoldingStartDate;
    private Date queryHoldingEndDate;
    private List<AcctFundHoldingRenew> holdingList;
    @EJB
    private AcctFundHoldingRenewFacade acctFundHoldingRenewFacade;
    
    private List<MgCustActList> idnList = null;
    
    private String queryIdnCustId;
    private List<MgCustActList> queryIdnList;
    @EJB
    private MgCustActListFacade mgCustActListFacade;
    
    
    
    @PostConstruct
    public void init() {
        items = mgSetDetailFacade.findByStatusNotInMgDetail(MgSetMasterStatus.DELETE);
        emfList = new ArrayList<EricMgFeeMonth>();
        emfDlist = new ArrayList<EricMgFeeMonthDetail>();
        holdingList = new ArrayList<AcctFundHoldingRenew>();
        idnList = new ArrayList<MgCustActList>();
        
        selected = new MgSetDetail();
        mgDetailVO = new MgDetailVO();
        mgSetDetail = new MgSetDetail();
        
        mgSetMasterList = mgSetMasterFacade.findByStatusNotInMgMaster(MgSetMasterStatus.DELETE);
        productVO = new ProductVO();
        productTypeList = new ArrayList<String>();
        productSeriesList = new ArrayList<String>();
    }
    
    public void prepareDelete(MgSetDetail item) {
        this.selected = item;
        item.setStatus(MgSetMasterStatus.DELETE);
        mgSetDetailFacade.save(item);
        JsfUtil.addSuccessMessage("子活動案已刪除");
    }
    
    public boolean showConfirm(MgSetDetail item){
        System.out.println(userSession.getUser().getRole()+"=="+item.getStatus().getStatus());
        if(userSession.getUser().getRole().equalsIgnoreCase("2") ){
            System.out.println("fuxk = true");
            return true;
        }
        return false;
    }
    
    public void handleFileUpload(FileUploadEvent event) {
        if (!idnList.isEmpty()) {
            idnList.clear();
        }
        try {
//            System.out.println(event.getFile().getFileName());
            List<String> tmpList = new ArrayList<String>();
            UploadedFile uploadedFile = event.getFile();
            tmpList = IOUtils.readLines(uploadedFile.getInputstream());
            for (String idn : tmpList) {
                String[] idnary = idn.split("\\,");
                MgCustActList obj = new MgCustActList();
                obj.setCustId(idnary[0]);
                obj.setActDataDt(idnary[1]);
                obj.setActStartDt(idnary[2]);
                obj.setActEndDt(idnary[3]);
                System.out.println("fuxk" + mgSetDetail.getMgSetMasterId().getMgActMCode());
                obj.setActCode(mgSetDetail.getMgSetMasterId().getMgActMCode());
                obj.setActSubCode(showMgSetDetailCode());
                obj.setUpdateDttm(new Date());                
                obj.setStatus("Y");
                idnList.add(obj);
            }
        } catch (IOException ex) {
            Logger.getLogger(MgSetMasterController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void prepareUpdate(MgSetDetail item) {
        this.selected = item;
        this.mgSetDetail = item;
        mgSetActDetailStartDate = new DateTime(mgSetDetail.getMgActDStartDate()).toDate();
        mgSetActDetailEndDate = new DateTime(mgSetDetail.getMgActDEndDate()).toDate();
        List<MgSetDetailChlCfg> msdcc = mgSetDetailChlCfgFacade.findBySelectChannel(item.getMgActDCode(), item.getMgActDSeq());
        if (selectedChannel != null) {
            selectedChannel.clear();
        } else {
            selectedChannel = new ArrayList<String>();
        }
        for (MgSetDetailChlCfg m : msdcc) {
            selectedChannel.add(m.getMgActSaleChnlCode());
        }
        rangeList = mgSetDetailRngCfgFacade.findByRng(item.getMgActDCode(), item.getMgActDSeq());
    }
    
    public void cancel() {
        
    }
    
    public void update() {
        
    }
    
    public void confirm() {
        
    }
    
    public void export() {
        
    }
    
    public void autoFill() {
        for (String channel : selectedChannel) {
            if (channel.equalsIgnoreCase("7")) {
                mgSetDetail.setMgActDPrdCode("1");
                mgSetDetail.setMgActDSecType("1");
                mgSetDetail.setMgActDRemark("1");
                //mgSetDetail.setmgactd
            }
        }
    }
    
    public void preSetting() {
        productVO = new ProductVO();
        productTypeList = new ArrayList<String>();
        productSeriesList = new ArrayList<String>();
    }
    
    public String onFlowProcess(FlowEvent event) {
        if (skip) {
            skip = false;   //reset in case user goes back
            return "confirm";
        } else {
            return event.getNewStep();
        }
    }
    
    public void onRowEdit(RowEditEvent event) {
        
        MgSetDetail detail = (MgSetDetail) event.getObject();
        FacesMessage msg = new FacesMessage("修改資料完成");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void onRowCancel(RowEditEvent event) {
        MgSetDetail detail = (MgSetDetail) event.getObject();
        FacesMessage msg = new FacesMessage("取消");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        
        if (newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
    
    public String showMgSetDetailCode() {
        MgSetDetail lastObj = mgSetDetailFacade.findByLastSeqMgSetDetail(mgSetDetail.getMgSetMasterId());
        int seq = 0;
        if (lastObj != null) {
            seq = lastObj.getId().intValue();
        }
        return utils.toPlusOneString(seq, 2);
    }
    
    public MgSetDetail getSelected() {
        return selected;
    }
    
    public void setSelected(MgSetDetail selected) {
        this.selected = selected;
    }
    
    protected void setEmbeddableKeys() {
    }
    
    protected void initializeEmbeddableKey() {
    }
    
    private MgSetDetailFacade getFacade() {
        return mgSetDetailFacade;
    }
    
    public void prepareIdn() {
        idnList = new ArrayList<MgCustActList>();
    }
    
    public MgSetDetail prepareCreate() {
        selectedChannel = new ArrayList<String>();
        rangeList = new ArrayList<MgSetDetailRngCfg>();
        selectedRange = new MgSetDetailRngCfg();
        selected = new MgSetDetail();
        mgDetailVO = new MgDetailVO();
        mgSetDetail = new MgSetDetail();
        mgSetMasterList = mgSetMasterFacade.findByStatusNotInMgMaster(MgSetMasterStatus.DELETE);
        initializeEmbeddableKey();
        return selected;
    }
    
    public void addMSDRC() {
        rangeList.add(new MgSetDetailRngCfg());
        System.out.println(rangeList.size());
    }
    
    public void minusMSDRC(MgSetDetailRngCfg msdrc) {
        rangeList.remove(msdrc);
    }
    
    public void saveIdn() {
        for (MgCustActList obj : idnList) {
            System.out.println(obj.getActCode() + "=" + obj.getActSubCode() + "=" + obj.getStatus());
            mgCustActListFacade.save(obj);
        }
        JsfUtil.addSuccessMessage("IDN新增完成");
    }
    
    public void create() {
        
        System.out.println("Fuxk==>" + mgSetDetail.getMgSetMasterId().getId());
        mgSetDetail.setMgActDCode(mgSetDetail.getMgSetMasterId().getMgActMCode());
        mgSetDetail.setMgActDSeq(showMgSetDetailCode());
        mgSetDetail.setCrtEmpId(userSession.getUser().getEmpId());
        mgSetDetail.setCrtEmpName(userSession.getUser().getEmpname());
        mgSetDetail.setCrtDate(new Date());
        mgSetDetail.setStatus(MgSetMasterStatus.SEND);
        
        if(mgSetActDetailEndDate.before(mgSetActDetailStartDate)){
             JsfUtil.addErrorMessage("起迄日期設定有誤，請起迄日期設定");            
            return;
        }
        
        mgSetDetail.setMgActDEndDate(toStringDate(mgSetActDetailEndDate, "yyyyMMdd"));
        mgSetDetail.setMgActDStartDate(toStringDate(mgSetActDetailStartDate, "yyyyMMdd"));
        
        boolean rangeError = false;
        String errMsg = "";
        for (MgSetDetailRngCfg rng : rangeList) {
            if (rng.getMgActLowAmt().compareTo(rng.getMgActHightAmt()) == 1) {
                rangeError = true;
                errMsg = "級距設定異常，請確認級距設定。";
            }
            if(rng.getMgActBps() != null && rng.getMgActBps().compareTo(BigInteger.ZERO) == 1){
                rangeError = true;
                errMsg = "費率(bps)設定異常，請確認費率(bps)設定。";
            }
            rng.setChangedate(new Date());
            rng.setMgActCode(mgSetDetail.getMgActDCode());
            rng.setMgActSubCode(mgSetDetail.getMgActDSeq());
            mgSetDetailRngCfgFacade.save(rng);
        }
//        if (rangeError) {
//            JsfUtil.addErrorMessage("級距設定異常，請確認級距設定");            
//            return;
//        }
        
        for (String chl : selectedChannel) {
            MgSetDetailChlCfg msdcc = new MgSetDetailChlCfg();
            msdcc.setMgActCode(mgSetDetail.getMgActDCode());
            msdcc.setMgActSubCode(mgSetDetail.getMgActDSeq());
            msdcc.setMgActSaleChnlCode(chl);
            mgSetDetailChlCfgFacade.save(msdcc);
        }
        mgSetDetailFacade.save(mgSetDetail);
        search();
        JsfUtil.addSuccessMessage("子活動案新增完成");

        // FacesMessage msg = new FacesMessage("子活動案新增完成==>" + mgSetDetail.getId());
        //   FacesContext.getCurrentInstance().addMessage(null, msg);
//        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("MgSetDetailCreated"));
        if (!JsfUtil.isValidationFailed()) {
            rangeList = null;    // Invalidate list of items to trigger re-query.
        }
        RequestContext.getCurrentInstance().execute("PF('MgSetDetailCreateDialog').hide();");
        
    }

//    public void update() {
//        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("MgSetDetailUpdated"));
//    }
    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("MgSetDetailDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }
    
//    public List<MgSetDetail> getItems() {
//        if (items == null) {
//            items = getFacade().findAll();
//        }
//        return items;
//    }
    
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
    
    public MgSetDetail getMgSetDetail(Long id) {
        return getFacade().find(id);
    }
    
    public List<MgSetDetail> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }
    
    public List<MgSetDetail> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }
    
    @FacesConverter(forClass = MgSetDetail.class)
    public static class MgSetDetailControllerConverter implements Converter {
        
        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MgSetDetailController controller = (MgSetDetailController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "mgSetDetailController");
            return controller.getMgSetDetail(getKey(value));
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
            if (object instanceof MgSetDetail) {
                MgSetDetail o = (MgSetDetail) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), MgSetDetail.class.getName()});
                return null;
            }
        }
        
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
    
    public void searchIdn() {
        queryIdnList = mgCustActListFacade.findByQuery("", "", queryIdnCustId);
    }
    
    public void searchHolding() {
        holdingList = acctFundHoldingRenewFacade.findByQuery(queryHoldingCustId, queryHoldingStartDate, queryHoldingEndDate);
    }
    
    public void searchRoboMon() {
        emfList = ericMgMonthFacade.findByQuery(queryMonCustId, queryMonStartDate, queryMonEndDate);
    }
    
    public void searchRoboMonDetail() {
        emfDlist = ericMgMonthDetailFacade.findByQuery(queryMonCustId, queryMonStartDate, queryMonEndDate);
    }
    
    public void search() {
        items.clear();
        items = mgSetDetailFacade.findAll();
        mgDetailVO = new MgDetailVO();
    }
    
    public UserSession getUserSession() {
        return userSession;
    }
    
    public void setUserSession(UserSession userSession) {
        this.userSession = userSession;
    }
    
    public MgDetailVO getMgDetailVO() {
        return mgDetailVO;
    }
    
    public void setMgDetailVO(MgDetailVO mgDetailVO) {
        this.mgDetailVO = mgDetailVO;
    }
    
    public boolean isSkip() {
        return skip;
    }
    
    public void setSkip(boolean skip) {
        this.skip = skip;
    }
    
    public MgSetDetail getMgSetDetail() {
        return mgSetDetail;
    }
    
    public void setMgSetDetail(MgSetDetail mgSetDetail) {
        this.mgSetDetail = mgSetDetail;
    }
    
    public List<MgSetMaster> getMgSetMasterList() {
        return mgSetMasterList;
    }
    
    public void setMgSetMasterList(List<MgSetMaster> mgSetMasterList) {
        this.mgSetMasterList = mgSetMasterList;
    }
    
    public List<String> getSelectedChannel() {
        return selectedChannel;
    }
    
    public void setSelectedChannel(List<String> selectedChannel) {
        this.selectedChannel = selectedChannel;
    }
    
    public List<MgSetDetailRngCfg> getRangeList() {
        return rangeList;
    }
    
    public void setRangeList(List<MgSetDetailRngCfg> rangeList) {
        this.rangeList = rangeList;
    }
    
    public MgSetDetailRngCfg getSelectedRange() {
        return selectedRange;
    }
    
    public void setSelectedRange(MgSetDetailRngCfg selectedRange) {
        this.selectedRange = selectedRange;
    }
    
    public Date getMgSetActDetailStartDate() {
        return mgSetActDetailStartDate;
    }
    
    public void setMgSetActDetailStartDate(Date mgSetActDetailStartDate) {
        this.mgSetActDetailStartDate = mgSetActDetailStartDate;
    }
    
    public Date getMgSetActDetailEndDate() {
        return mgSetActDetailEndDate;
    }
    
    public void setMgSetActDetailEndDate(Date mgSetActDetailEndDate) {
        this.mgSetActDetailEndDate = mgSetActDetailEndDate;
    }
    
    public ProductVO getProductVO() {
        return productVO;
    }
    
    public void setProductVO(ProductVO productVO) {
        this.productVO = productVO;
    }
    
    public List<String> getProductTypeList() {
        return productTypeList;
    }
    
    public void setProductTypeList(List<String> productTypeList) {
        this.productTypeList = productTypeList;
    }
    
    public List<String> getProductSeriesList() {
        return productSeriesList;
    }
    
    public void setProductSeriesList(List<String> productSeriesList) {
        this.productSeriesList = productSeriesList;
    }
    
    public String getQueryMonCustId() {
        return queryMonCustId;
    }
    
    public void setQueryMonCustId(String queryMonCustId) {
        this.queryMonCustId = queryMonCustId;
    }
    
    public Date getQueryMonStartDate() {
        return queryMonStartDate;
    }
    
    public void setQueryMonStartDate(Date queryMonStartDate) {
        this.queryMonStartDate = queryMonStartDate;
    }
    
    public Date getQueryMonEndDate() {
        return queryMonEndDate;
    }
    
    public void setQueryMonEndDate(Date queryMonEndDate) {
        this.queryMonEndDate = queryMonEndDate;
    }
    
    public String getQueryMonDCustId() {
        return queryMonDCustId;
    }
    
    public void setQueryMonDCustId(String queryMonDCustId) {
        this.queryMonDCustId = queryMonDCustId;
    }
    
    public Date getQueryMonDStartDate() {
        return queryMonDStartDate;
    }
    
    public void setQueryMonDStartDate(Date queryMonDStartDate) {
        this.queryMonDStartDate = queryMonDStartDate;
    }
    
    public Date getQueryMonDEndDate() {
        return queryMonDEndDate;
    }
    
    public void setQueryMonDEndDate(Date queryMonDEndDate) {
        this.queryMonDEndDate = queryMonDEndDate;
    }
    
    public List<EricMgFeeMonth> getEmfList() {
        return emfList;
    }
    
    public void setEmfList(List<EricMgFeeMonth> emfList) {
        this.emfList = emfList;
    }
    
    public List<EricMgFeeMonthDetail> getEmfDlist() {
        return emfDlist;
    }
    
    public void setEmfDlist(List<EricMgFeeMonthDetail> emfDlist) {
        this.emfDlist = emfDlist;
    }
    
    public String getQueryHoldingCustId() {
        return queryHoldingCustId;
    }
    
    public void setQueryHoldingCustId(String queryHoldingCustId) {
        this.queryHoldingCustId = queryHoldingCustId;
    }
    
    public Date getQueryHoldingStartDate() {
        return queryHoldingStartDate;
    }
    
    public void setQueryHoldingStartDate(Date queryHoldingStartDate) {
        this.queryHoldingStartDate = queryHoldingStartDate;
    }
    
    public Date getQueryHoldingEndDate() {
        return queryHoldingEndDate;
    }
    
    public void setQueryHoldingEndDate(Date queryHoldingEndDate) {
        this.queryHoldingEndDate = queryHoldingEndDate;
    }
    
    public List<AcctFundHoldingRenew> getHoldingList() {
        return holdingList;
    }
    
    public void setHoldingList(List<AcctFundHoldingRenew> holdingList) {
        this.holdingList = holdingList;
    }
    
    public List<MgCustActList> getIdnList() {
        return idnList;
    }
    
    public void setIdnList(List<MgCustActList> idnList) {
        this.idnList = idnList;
    }
    
    public String getQueryIdnCustId() {
        return queryIdnCustId;
    }
    
    public void setQueryIdnCustId(String queryIdnCustId) {
        this.queryIdnCustId = queryIdnCustId;
    }
    
    public List<MgCustActList> getQueryIdnList() {
        return queryIdnList;
    }
    
    public void setQueryIdnList(List<MgCustActList> queryIdnList) {
        this.queryIdnList = queryIdnList;
    }

    public List<MgSetDetail> getItems() {
        return items;
    }

    public void setItems(List<MgSetDetail> items) {
        this.items = items;
    }
    
}
