package cub.common;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.apache.commons.lang.StringUtils;

import cub.facade.TrMasterFacade;

@ManagedBean(name = "trCodeConverter")
@RequestScoped
public class TrCodeConverter implements Converter {
    @EJB
    private TrMasterFacade ejbTrMasterFacade;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String result = "";
        if (null != value && StringUtils.isNotBlank(value.toString())) {
            result = ejbTrMasterFacade.findNameByTrCode(value.toString());
        }
        return result;
    }

}
