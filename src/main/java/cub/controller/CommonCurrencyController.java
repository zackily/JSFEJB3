//package cub.controller;
//
//import cub.entities.CommonCurrency;
//import cub.controller.util.JsfUtil;
//import cub.controller.util.JsfUtil.PersistAction;
//import cub.facade.CommonCurrencyFacade;
//
//import java.io.Serializable;
//import java.util.List;
//import java.util.ResourceBundle;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.ejb.EJB;
//import javax.ejb.EJBException;
//import javax.inject.Named;
//import javax.enterprise.context.SessionScoped;
//import javax.faces.component.UIComponent;
//import javax.faces.context.FacesContext;
//import javax.faces.convert.Converter;
//import javax.faces.convert.FacesConverter;
//
//@Named("commonCurrencyController")
//@SessionScoped
//public class CommonCurrencyController implements Serializable {
//
//    @EJB
//    private cub.facade.CommonCurrencyFacade ejbFacade;
//    private List<CommonCurrency> items = null;
//    private CommonCurrency selected;
//
//    public CommonCurrencyController() {
//    }
//
//    public CommonCurrency getSelected() {
//        return selected;
//    }
//
//    public void setSelected(CommonCurrency selected) {
//        this.selected = selected;
//    }
//
//    protected void setEmbeddableKeys() {
//    }
//
//    protected void initializeEmbeddableKey() {
//    }
//
//    private CommonCurrencyFacade getFacade() {
//        return ejbFacade;
//    }
//
//    public CommonCurrency prepareCreate() {
//        selected = new CommonCurrency();
//        initializeEmbeddableKey();
//        return selected;
//    }
//
//    public void create() {
//        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("CommonCurrencyCreated"));
//        if (!JsfUtil.isValidationFailed()) {
//            items = null;    // Invalidate list of items to trigger re-query.
//        }
//    }
//
//    public void update() {
//        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("CommonCurrencyUpdated"));
//    }
//
//    public void destroy() {
//        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("CommonCurrencyDeleted"));
//        if (!JsfUtil.isValidationFailed()) {
//            selected = null; // Remove selection
//            items = null;    // Invalidate list of items to trigger re-query.
//        }
//    }
//
//    public List<CommonCurrency> getItems() {
//        if (items == null) {
//            items = getFacade().findAll();
//        }
//        return items;
//    }
//
//    private void persist(PersistAction persistAction, String successMessage) {
//        if (selected != null) {
//            setEmbeddableKeys();
//            try {
//                if (persistAction != PersistAction.DELETE) {
//                    getFacade().edit(selected);
//                } else {
//                    getFacade().remove(selected);
//                }
//                JsfUtil.addSuccessMessage(successMessage);
//            } catch (EJBException ex) {
//                String msg = "";
//                Throwable cause = ex.getCause();
//                if (cause != null) {
//                    msg = cause.getLocalizedMessage();
//                }
//                if (msg.length() > 0) {
//                    JsfUtil.addErrorMessage(msg);
//                } else {
//                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
//                }
//            } catch (Exception ex) {
//                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
//                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
//            }
//        }
//    }
//
//    public CommonCurrency getCommonCurrency(java.math.BigDecimal id) {
//        return getFacade().find(id);
//    }
//
//    public List<CommonCurrency> getItemsAvailableSelectMany() {
//        return getFacade().findAll();
//    }
//
//    public List<CommonCurrency> getItemsAvailableSelectOne() {
//        return getFacade().findAll();
//    }
//
//    @FacesConverter(forClass = CommonCurrency.class)
//    public static class CommonCurrencyControllerConverter implements Converter {
//
//        @Override
//        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
//            if (value == null || value.length() == 0) {
//                return null;
//            }
//            CommonCurrencyController controller = (CommonCurrencyController) facesContext.getApplication().getELResolver().
//                    getValue(facesContext.getELContext(), null, "commonCurrencyController");
//            return controller.getCommonCurrency(getKey(value));
//        }
//
//        java.math.BigDecimal getKey(String value) {
//            java.math.BigDecimal key;
//            key = new java.math.BigDecimal(value);
//            return key;
//        }
//
//        String getStringKey(java.math.BigDecimal value) {
//            StringBuilder sb = new StringBuilder();
//            sb.append(value);
//            return sb.toString();
//        }
//
//        @Override
//        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
//            if (object == null) {
//                return null;
//            }
//            if (object instanceof CommonCurrency) {
//                CommonCurrency o = (CommonCurrency) object;
//                return getStringKey(o.getId());
//            } else {
//                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), CommonCurrency.class.getName()});
//                return null;
//            }
//        }
//
//    }
//
//}
