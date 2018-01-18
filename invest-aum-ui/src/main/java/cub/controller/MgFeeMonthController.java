package cub.controller;

import cub.entities.MgFeeMonth;
import cub.facade.MgFeeMonthFacade;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.event.ActionEvent;
import org.apache.commons.lang.StringUtils;

@ManagedBean(name = "mgFeeMonthController")
@ViewScoped
public class MgFeeMonthController implements Serializable {

    @EJB
    private cub.facade.MgFeeMonthFacade ejbFacade;
    private List<MgFeeMonth> items = null;
    private MgFeeMonth selected;
    private String startMonth;
    private String endMonth;
    private String custId = "A200267857";

    public MgFeeMonthController() {
    }

    @PostConstruct
    public void init() {
        Calendar cal = Calendar.getInstance();
        int endY = cal.get(Calendar.YEAR);
        int endM = cal.get(Calendar.MONTH);
        String m = String.valueOf(endM + 1);
        endMonth = endY + "" + StringUtils.leftPad(m, 2, "0");
        cal.add(Calendar.MONTH, -4);
        int startY = cal.get(Calendar.YEAR);
        String startM = String.valueOf(cal.get(Calendar.MONTH));
        startMonth = startY + "" + StringUtils.leftPad(startM, 2, "0");
        items = getFacade().fineByBaseMonth(this.startMonth, this.endMonth, this.custId);
    }

    public MgFeeMonth getSelected() {
        return selected;
    }

    public void setSelected(MgFeeMonth selected) {
        this.selected = selected;
    }

    public void query(ActionEvent actionEvent) {
        items = getFacade().fineByBaseMonth(this.startMonth, this.endMonth, this.custId);
    }

    private MgFeeMonthFacade getFacade() {
        return ejbFacade;
    }

    public MgFeeMonth getMgFeeMonth(Long id) {
        return getFacade().find(id);
    }

    @FacesConverter(forClass = MgFeeMonth.class)
    public static class MgFeeMonthControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MgFeeMonthController controller = (MgFeeMonthController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "mgFeeMonthController");
            return controller.getMgFeeMonth(getKey(value));
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
            if (object instanceof MgFeeMonth) {
                MgFeeMonth o = (MgFeeMonth) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), MgFeeMonth.class.getName()});
                return null;
            }
        }

    }

    public MgFeeMonthFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(MgFeeMonthFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public List<MgFeeMonth> getItems() {
        return items;
    }

    public void setItems(List<MgFeeMonth> items) {
        this.items = items;
    }

    public String getStartMonth() {
        return startMonth;
    }

    public void setStartMonth(String startMonth) {
        this.startMonth = startMonth;
    }

    public String getEndMonth() {
        return endMonth;
    }

    public void setEndMonth(String endMonth) {
        this.endMonth = endMonth;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

}
