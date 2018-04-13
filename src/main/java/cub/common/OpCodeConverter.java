package cub.common;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import cub.facade.RdOptionItemFacade;

@ManagedBean(name = "opCodeConverter")
@RequestScoped
public class OpCodeConverter implements Converter {
    @EJB
    private RdOptionItemFacade ejbRdOptionItemFacade;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String result = "";
        if (null != value) {
            result = ejbRdOptionItemFacade.findNameByItemCode(Short.valueOf(value.toString()));
        }
        return result;
    }

}
