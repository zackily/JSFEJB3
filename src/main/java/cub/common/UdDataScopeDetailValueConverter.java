package cub.common;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@ManagedBean(name = "udDataScopeDetailValueConverter")
@RequestScoped
public class UdDataScopeDetailValueConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String result = "";
        if (null != value) {
            if (value.equals("0")) {
                result = "同下單";
            } else {
                result = value.toString();
            }
        }
        return result;
    }

}
