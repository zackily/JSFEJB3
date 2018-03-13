package cub.common;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.apache.commons.lang.StringUtils;

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
            String[] split = StringUtils.split(value.toString(), "+");
            result = ejbRdDataColumnOptionFacade.getOpNameByCode(split[0], split[1], split[2]);
        }
        return result;
    }

}
