package cub.controller;

import cub.entities.MgSetMaster;
import cub.controller.util.JsfUtil;
import cub.controller.util.JsfUtil.PersistAction;
import cub.controller.util.Utils;
import cub.entities.MgSetDetail;
import cub.facade.MgSetMasterFacade;
import cub.vo.AumFundVO;
import cub.vo.MgMasterVO;
import java.io.IOException;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.apache.commons.io.IOUtils;
import org.joda.time.DateTime;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FlowEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean(name = "mgSetMasterController")
@ViewScoped
public class MgSetMasterController implements Serializable {

    @EJB
    private cub.facade.MgSetMasterFacade mgSetMasterFacade;
    private List<MgSetMaster> items = null;
    private MgSetMaster selected;

    private MgSetDetail mgSetDetail;
    private List<MgSetDetail> detailItems = null;

    private Date start_date;
    private Date end_date;
    private Date last_settle_date;

    //Query VO
    private AumFundVO queryVO;
    private MgMasterVO masterVO;

    //for wizard
    private boolean skip;

    //for multiple checkbox
    private List<String> invCorp;
    private Map<String, String> invCorpFundMapping;
    private List<String> fundId;

    //for selected
    private List<String> selectedInvCorp;
    private List<String> selectedFundId;

    @EJB
    private Utils utils;

    @PostConstruct
    public void init() {
        queryVO = new AumFundVO();
        masterVO = new MgMasterVO();
        invCorp = new ArrayList<String>();
        invCorpFundMapping = new HashMap<String, String>();
        for (int i = 1; i <= 10; i++) {
            String company = "company" + i;
              invCorp.add(company);
            for (int j = 1; j <= 10; j++) {
                invCorpFundMapping.put("fund" + i + "-" + j, company);
            }
          
        }
        fundId = new ArrayList<String>();
        mgSetDetail = new MgSetDetail();
        selectedInvCorp = new ArrayList<String>();
        selectedFundId = new ArrayList<String>();
    }

    public List<String> getInvCorp() {
        return invCorp;
    }

    public void setInvCorp(List<String> invCorp) {
        this.invCorp = invCorp;
    }

    public List<String> getFundId() {
        return fundId;
    }

    public void setFundId(List<String> fundId) {
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
        if (!detailItems.isEmpty()) {
            detailItems.clear();
        }
        try {
            System.out.println(event.getFile().getFileName());
            UploadedFile uploadedFile = event.getFile();
            List<String> sub_lists = IOUtils.readLines(uploadedFile.getInputstream());
            for (String line : sub_lists) {
                System.out.println(line);
                String[] line_spilt = line.split("\\,");
                MgSetDetail d = new MgSetDetail();
                int index = 0;
                d.setMgActDCode(line_spilt[index++]);
                d.setMgActDName(line_spilt[index++]);
                d.setMgActDPrdCode(line_spilt[index++]);
                d.setMgActDSecType(line_spilt[index++]);
                d.setMgActDSecCmp(line_spilt[index++]);
                d.setMgActDSecCode(line_spilt[index++]);
                d.setMgActDCostId(line_spilt[index++]);
                d.setMgActDSaleChnl(line_spilt[index++]);
                d.setMgActDStartDate(line_spilt[index++]);
                d.setMgActDEndDate(line_spilt[index++]);
                d.setMgActDBaseAmt(new BigDecimal(line_spilt[index++].trim()));
                d.setMgActDLowAmt(new BigDecimal(line_spilt[index++].trim()));
                d.setMgActDHighAmt(new BigDecimal(line_spilt[index++].trim()));
                d.setMgActDRate(new BigDecimal(line_spilt[index++].trim()));
                d.setMgActDFee(new BigDecimal(line_spilt[index++].trim()));
                detailItems.add(d);
            }
        } catch (IOException ex) {
            Logger.getLogger(MgSetMasterController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public MgSetMaster prepareCreate() {
        selected = new MgSetMaster();
        mgSetDetail = new MgSetDetail();
        start_date = new Date();
        end_date = new Date();
        last_settle_date = new Date();
        initializeEmbeddableKey();
        return selected;
    }

    public void findByMasterId(MgSetMaster m) {
        this.detailItems = (List) m.getMgSetDetailCollection();
    }

    public void addDetail(MgSetMaster m) {
        this.selected = m;
        mgSetDetail = new MgSetDetail();
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
        this.selected.setMgActLastSettleDate(toStringDate(last_settle_date));
        this.selected.setMgActMCode(this.showCode());
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("MgSetMasterCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void createDetail() {
        mgSetDetail.setMgActDStartDate(toStringDate(start_date));
        mgSetDetail.setMgActDEndDate(toStringDate(end_date));
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
        getFacade().edit(selected);
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("MgSetMasterUpdated"));
    }

    public void updateCheckBox() {
        fundId.clear();
        for (String str : selectedInvCorp) {            
            System.out.println("Select Company:" + str);            
            Set s = invCorpFundMapping.keySet();
            Iterator i = s.iterator();
            while (i.hasNext()) {
                String key = (String) i.next();
               
                if (invCorpFundMapping.get(key).equalsIgnoreCase(str)) {                    
                     System.out.println("Select key:"+key);
                    fundId.add(key);
                }
            }

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

    public String toStringDate(Date d) {
        DateTime s = new DateTime(d);
        return s.toString("yyyyMMdd");
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

    public String showCode(){
        MgSetMaster lastObj = mgSetMasterFacade.findByLastIdMgMaster("");
        int seq = 0;
        if(lastObj != null){
            seq = lastObj.getId().intValue();
        }
        return utils.toPlusOneString(seq);
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

}
