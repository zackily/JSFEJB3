package cub.common;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import cub.facade.RdDataColumnOptionFacade;

@ManagedBean(name = "opValueConverter")
@RequestScoped
public class OpValueConverter implements Converter {
    @EJB
    private RdDataColumnOptionFacade ejbRdDataColumnOptionFacade;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String result = "";
        if (null != value) {
            result = ejbRdDataColumnOptionFacade.getOpNameByCode(value.toString());
        }
        return result;
    }

}
