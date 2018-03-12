package cub.common;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import cub.enums.SeqTypeEnum;
import cub.facade.DataScopeMasterFacade;
import cub.facade.UdDataScopeMasterFacade;

@ManagedBean(name = "scopeCodeConverter")
@RequestScoped
public class ScopeCodeConverter implements Converter {
    @EJB
    private DataScopeMasterFacade ejbDataScopeMasterFacade;
    @EJB
    private UdDataScopeMasterFacade ejbUdDataScopeMasterFacade;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String result = "";
        if (null != value) {
            if (value.toString().indexOf(SeqTypeEnum.UDDATA_CODE.getCode()) >= 0) {
                result = ejbUdDataScopeMasterFacade.findScopeNameByCode(value.toString());
            } else if (value.toString().indexOf(SeqTypeEnum.DATA_CODE.getCode()) >= 0) {
                result = ejbDataScopeMasterFacade.findScopeNameByCode(value.toString());
            }
        }
        return result;
    }

}
