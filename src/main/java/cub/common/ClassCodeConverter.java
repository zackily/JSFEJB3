package cub.common;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import cub.facade.RdDataClassFacade;

@ManagedBean(name = "classCodeConverter")
@RequestScoped
public class ClassCodeConverter implements Converter {
    @EJB
    private RdDataClassFacade ejbRdDataClassFacade;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String result = "";
        if (null != value) {
            short s = Short.parseShort(value.toString());
            result = ejbRdDataClassFacade.getClassNameByClassCode(s);
        }
        return value + "." + result;
    }

}
