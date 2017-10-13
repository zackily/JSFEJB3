package cub.controller;

import cub.entities.MgSetDetail;
import cub.controller.util.JsfUtil;
import cub.controller.util.JsfUtil.PersistAction;
import cub.controller.util.Utils;
import cub.entities.MgSetMaster;
import cub.enums.MgSetMasterStatus;
import cub.facade.MgSetDetailFacade;
import cub.facade.MgSetMasterFacade;
import cub.sso.UserSession;
import cub.vo.MgDetailVO;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.joda.time.DateTime;
import org.primefaces.event.FlowEvent;

@Named("mgSetDetailController")
@SessionScoped
public class MgSetDetailController implements Serializable {
    @ManagedProperty("#{userSession}")
    private UserSession userSession;
    
    @EJB
    private cub.facade.MgSetDetailFacade mgSetDetailFacade;
    
    @EJB
    private MgSetMasterFacade mgSetMasterFacade;
    
    private List<MgSetMaster> mgSetMasterList;
    
    private List<MgSetDetail> items = null;
    private MgSetDetail selected;
    private MgSetDetail mgSetDetail;
    
    private List<String> selectedChannel;

    private boolean skip;
    
    //VO
    private MgDetailVO mgDetailVO;
    
    @EJB
    private Utils utils;    
    
   @PostConstruct
    public void init() {
        selected = new MgSetDetail();
        mgDetailVO = new MgDetailVO();
        mgSetDetail = new MgSetDetail();
        mgSetMasterList = mgSetMasterFacade.findByStatusNotInMgMaster(MgSetMasterStatus.DELETE);
    }

     public void prepareUpdate(MgSetDetail item) {
        this.selected = item;       
    }
    
     public String onFlowProcess(FlowEvent event) {
        if (skip) {
            skip = false;   //reset in case user goes back
            return "confirm";
        } else {
            return event.getNewStep();
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

    public MgSetDetail prepareCreate() {
        selected = new MgSetDetail();
        mgDetailVO = new MgDetailVO();
        mgSetDetail = new MgSetDetail();
        mgSetMasterList = mgSetMasterFacade.findByStatusNotInMgMaster(MgSetMasterStatus.DELETE);
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("MgSetDetailCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("MgSetDetailUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("MgSetDetailDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<MgSetDetail> getItems() {
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
    
    public void search() {
        items.clear();
        items= mgSetDetailFacade.findAll();
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

}
