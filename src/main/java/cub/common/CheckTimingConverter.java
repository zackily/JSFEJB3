package cub.common;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import cub.facade.RdOptionItemFacade;

@ManagedBean(name = "checkTimingConverter")
@RequestScoped
public class CheckTimingConverter extends AbstractItemNameConverter implements Converter {
    @EJB
    private RdOptionItemFacade ejbRdOptionItemFacade;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return getItemName("4", value, ejbRdOptionItemFacade);
    }

}
