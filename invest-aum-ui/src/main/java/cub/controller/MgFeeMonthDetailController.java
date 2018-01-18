package cub.controller;

import cub.entities.MgFeeMonthDetail;
import cub.facade.MgFeeMonthDetailFacade;

import java.io.Serializable;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "mgFeeMonthController")
@ViewScoped
public class MgFeeMonthDetailController implements Serializable {

    @EJB
    private cub.facade.MgFeeMonthDetailFacade ejbFacade;
    private List<MgFeeMonthDetail> items = null;

    public MgFeeMonthDetailController() {
    }

    @PostConstruct
    public void init() {
        Map<String, String> paramMaps = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String baseMonth = paramMaps.get("baseMonth");
        String custId = paramMaps.get("custId");
        items = getFacade().fineByBaseMonth(baseMonth, custId);
    }

    private MgFeeMonthDetailFacade getFacade() {
        return ejbFacade;
    }

    public MgFeeMonthDetailFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(MgFeeMonthDetailFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public List<MgFeeMonthDetail> getItems() {
        return items;
    }

    public void setItems(List<MgFeeMonthDetail> items) {
        this.items = items;
    }

}
