/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cub.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author NT48810
 */
@Entity
@Table(name = "MG_SET_MASTER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MgSetMaster.findAll", query = "SELECT m FROM MgSetMaster m")
    , @NamedQuery(name = "MgSetMaster.findById", query = "SELECT m FROM MgSetMaster m WHERE m.id = :id")
    , @NamedQuery(name = "MgSetMaster.findByMgActMCode", query = "SELECT m FROM MgSetMaster m WHERE m.mgActMCode = :mgActMCode")
    , @NamedQuery(name = "MgSetMaster.findByMgActMName", query = "SELECT m FROM MgSetMaster m WHERE m.mgActMName = :mgActMName")
    , @NamedQuery(name = "MgSetMaster.findByMgActMType", query = "SELECT m FROM MgSetMaster m WHERE m.mgActMType = :mgActMType")
    , @NamedQuery(name = "MgSetMaster.findByMgActMRateSet", query = "SELECT m FROM MgSetMaster m WHERE m.mgActMRateSet = :mgActMRateSet")
    , @NamedQuery(name = "MgSetMaster.findByMgActMRemark", query = "SELECT m FROM MgSetMaster m WHERE m.mgActMRemark = :mgActMRemark")
    , @NamedQuery(name = "MgSetMaster.findByMgActMSaleChnl", query = "SELECT m FROM MgSetMaster m WHERE m.mgActMSaleChnl = :mgActMSaleChnl")
    , @NamedQuery(name = "MgSetMaster.findByMgActMChargeObj", query = "SELECT m FROM MgSetMaster m WHERE m.mgActMChargeObj = :mgActMChargeObj")
    , @NamedQuery(name = "MgSetMaster.findByMgActLastSettleDate", query = "SELECT m FROM MgSetMaster m WHERE m.mgActLastSettleDate = :mgActLastSettleDate")})
public class MgSetMaster implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id    
    @NotNull
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MSM_SEQ")
    @SequenceGenerator(name = "MSM_SEQ", sequenceName = "MG_SET_MASTER_SEQ", initialValue = 1, allocationSize = 1)
    private Long id;
    @Size(max = 20)
    @Column(name = "MG_ACT_M_CODE")
    private String mgActMCode;
    @Size(max = 50)
    @Column(name = "MG_ACT_M_NAME")
    private String mgActMName;
    @Size(max = 1)
    @Column(name = "MG_ACT_M_TYPE")
    private String mgActMType;
    @Size(max = 1)
    @Column(name = "MG_ACT_M_RATE_SET")
    private String mgActMRateSet;
    @Size(max = 100)
    @Column(name = "MG_ACT_M_REMARK")
    private String mgActMRemark;
    @Size(max = 1)
    @Column(name = "MG_ACT_M_SALE_CHNL")
    private String mgActMSaleChnl;
    @Size(max = 1)
    @Column(name = "MG_ACT_M_CHARGE_OBJ")
    private String mgActMChargeObj;
    @Size(max = 8)
    @Column(name = "MG_ACT_LAST_SETTLE_DATE")
    private String mgActLastSettleDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mgSetMasterId")
    private Collection<MgSetDetail> mgSetDetailCollection;

    public MgSetMaster() {
    }

    public MgSetMaster(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMgActMCode() {
        return mgActMCode;
    }

    public void setMgActMCode(String mgActMCode) {
        this.mgActMCode = mgActMCode;
    }

    public String getMgActMName() {
        return mgActMName;
    }

    public void setMgActMName(String mgActMName) {
        this.mgActMName = mgActMName;
    }

    public String getMgActMType() {
        return mgActMType;
    }

    public void setMgActMType(String mgActMType) {
        this.mgActMType = mgActMType;
    }

    public String getMgActMRateSet() {
        return mgActMRateSet;
    }

    public void setMgActMRateSet(String mgActMRateSet) {
        this.mgActMRateSet = mgActMRateSet;
    }

    public String getMgActMRemark() {
        return mgActMRemark;
    }

    public void setMgActMRemark(String mgActMRemark) {
        this.mgActMRemark = mgActMRemark;
    }

    public String getMgActMSaleChnl() {
        return mgActMSaleChnl;
    }

    public void setMgActMSaleChnl(String mgActMSaleChnl) {
        this.mgActMSaleChnl = mgActMSaleChnl;
    }

    public String getMgActMChargeObj() {
        return mgActMChargeObj;
    }

    public void setMgActMChargeObj(String mgActMChargeObj) {
        this.mgActMChargeObj = mgActMChargeObj;
    }

    public String getMgActLastSettleDate() {
        return mgActLastSettleDate;
    }

    public void setMgActLastSettleDate(String mgActLastSettleDate) {
        this.mgActLastSettleDate = mgActLastSettleDate;
    }

    @XmlTransient
    public Collection<MgSetDetail> getMgSetDetailCollection() {
        return mgSetDetailCollection;
    }

    public void setMgSetDetailCollection(Collection<MgSetDetail> mgSetDetailCollection) {
        this.mgSetDetailCollection = mgSetDetailCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MgSetMaster)) {
            return false;
        }
        MgSetMaster other = (MgSetMaster) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cub.entities.MgSetMaster[ id=" + id + " ]";
    }
    
}
