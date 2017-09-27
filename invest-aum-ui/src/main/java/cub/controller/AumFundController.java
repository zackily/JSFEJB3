package cub.controller;

import cub.entities.AumFund;
import cub.controller.util.JsfUtil;
import cub.controller.util.JsfUtil.PersistAction;
import cub.facade.AumFundFacade;
import cub.vo.AumFundVO;

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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@ManagedBean(name = "aumFundController")
@ViewScoped
public class AumFundController implements Serializable {

    @EJB
    private cub.facade.AumFundFacade ejbFacade;
    private List<AumFund> items = null;
    private AumFund selected;

    //Query VO
    private AumFundVO queryVO;
    
    
    @PostConstruct
    public void init(){      
         queryVO = new AumFundVO();
    }
    
    public void queryList(){
        items = ejbFacade.findByAumFundVO(queryVO);
    }
//    public AumFundController() {
//    }

    public AumFund getSelected() {
        return selected;
    }

    public void setSelected(AumFund selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private AumFundFacade getFacade() {
        return ejbFacade;
    }

    public AumFund prepareCreate() {
        selected = new AumFund();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("AumFundCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("AumFundUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("AumFundDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<AumFund> getItems() {
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

    public AumFund getAumFund(Long id) {
        return getFacade().find(id);
    }

    public List<AumFund> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<AumFund> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = AumFund.class)
    public static class AumFundControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AumFundController controller = (AumFundController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "aumFundController");
            return controller.getAumFund(getKey(value));
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
            if (object instanceof AumFund) {
                AumFund o = (AumFund) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), AumFund.class.getName()});
                return null;
            }
        }

    }
    public Date toTestDate(){
        return new Date();
    }

    public AumFundVO getQueryVO() {
        return queryVO;
    }

    public void setQueryVO(AumFundVO queryVO) {
        this.queryVO = queryVO;
    }
    
public String toXlsFileName(){
    return Long.toString(System.currentTimeMillis());
}
}
