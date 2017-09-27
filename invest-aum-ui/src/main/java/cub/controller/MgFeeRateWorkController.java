package cub.controller;

import cub.entities.MgFeeRateWork;
import cub.controller.util.JsfUtil;
import cub.controller.util.JsfUtil.PersistAction;
import cub.facade.MgFeeRateWorkFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("mgFeeRateWorkController")
@SessionScoped
public class MgFeeRateWorkController implements Serializable {

    @EJB
    private cub.facade.MgFeeRateWorkFacade ejbFacade;
    private List<MgFeeRateWork> items = null;
    private MgFeeRateWork selected;

    public MgFeeRateWorkController() {
    }

    public MgFeeRateWork getSelected() {
        return selected;
    }

    public void setSelected(MgFeeRateWork selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private MgFeeRateWorkFacade getFacade() {
        return ejbFacade;
    }

    public MgFeeRateWork prepareCreate() {
        selected = new MgFeeRateWork();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("MgFeeRateWorkCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("MgFeeRateWorkUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("MgFeeRateWorkDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<MgFeeRateWork> getItems() {
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

    public MgFeeRateWork getMgFeeRateWork(Long id) {
        return getFacade().find(id);
    }

    public List<MgFeeRateWork> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<MgFeeRateWork> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = MgFeeRateWork.class)
    public static class MgFeeRateWorkControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MgFeeRateWorkController controller = (MgFeeRateWorkController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "mgFeeRateWorkController");
            return controller.getMgFeeRateWork(getKey(value));
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
            if (object instanceof MgFeeRateWork) {
                MgFeeRateWork o = (MgFeeRateWork) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), MgFeeRateWork.class.getName()});
                return null;
            }
        }

    }

}
