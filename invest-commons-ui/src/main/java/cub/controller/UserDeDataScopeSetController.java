package cub.controller;

import cub.entities.UdColumnScopeMaster;
import cub.enums.SeqTypeEnum;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;

@Named("userDeDataScopeSetController")
@SessionScoped
public class UserDeDataScopeSetController implements Serializable {
    
    @EJB
    private cub.facade.WorkSeqFacade ejbWorkSeqFacade;
    @EJB
    private cub.facade.UdColumnScopeMasterFacade ejbUdColumnScopeMasterFacade;
    
    private List<UdColumnScopeMaster> master;
    
    private UdColumnScopeMaster item;
    
    public UserDeDataScopeSetController() {
    }
    
    @PostConstruct
    public void init() {
        this.master = new ArrayList<UdColumnScopeMaster>();
        this.master = ejbUdColumnScopeMasterFacade.findAll();
        if (this.master.isEmpty()) {
            this.item = new UdColumnScopeMaster();
            this.item.setUdColumnCode(ejbWorkSeqFacade.getWorkSeq(SeqTypeEnum.UDFIELD_CODE.toString()));
            this.master.add(this.item);
        }
    }
    
    public void create(ActionEvent event) {
        ejbUdColumnScopeMasterFacade.create(item);
        ejbWorkSeqFacade.create(entity);
        this.init();
    }
    
    public void update(ActionEvent event) {
        
    }
    
    public void delete(ActionEvent event) {
        
    }
    
    public void search(ActionEvent event) {
        
    }
    
    public void firstRecord(ActionEvent event) {
        
    }
    
    public void prevRecord(ActionEvent event) {
        
    }
    
    public void nextRecord(ActionEvent event) {
        
    }
    
    public void lastRecord(ActionEvent event) {
        
    }
    
    public List<UdColumnScopeMaster> getMaster() {
        return master;
    }
    
    public void setMaster(List<UdColumnScopeMaster> master) {
        this.master = master;
    }
    
    public UdColumnScopeMaster getItem() {
        return item;
    }
    
    public void setItem(UdColumnScopeMaster item) {
        this.item = item;
    }
    
}
