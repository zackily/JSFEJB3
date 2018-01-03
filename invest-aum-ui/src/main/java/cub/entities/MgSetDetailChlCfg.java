/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cub.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author NT48810
 */
@Entity
@Table(name = "MG_FEE_ACT_DETAIL_CHNL_CFG")
@XmlRootElement
public class MgSetDetailChlCfg implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
   @Id
    @NotNull
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MSDCC_SEQ")
    @SequenceGenerator(name = "MSDCC_SEQ", sequenceName = "MG_FEE_DETAIL_CHL_CFG_SEQ", initialValue = 1, allocationSize = 1) 
    private BigDecimal id;
    @Size(max = 20)
    @Column(name = "ACT_CODE")
    private String mgActCode;
    @Size(max = 5)
    @Column(name = "ACT_SUB_CODE")
    private String mgActSubCode;
    @Size(max = 1)
    @Column(name = "ACT_SALE_CHNL_CODE")
    private String mgActSaleChnlCode;
     @Column(name = "ACT_UPDATE_DTTM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date changedate;

    public MgSetDetailChlCfg() {
    }

    public MgSetDetailChlCfg(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getMgActCode() {
        return mgActCode;
    }

    public void setMgActCode(String mgActCode) {
        this.mgActCode = mgActCode;
    }

    public String getMgActSubCode() {
        return mgActSubCode;
    }

    public void setMgActSubCode(String mgActSubCode) {
        this.mgActSubCode = mgActSubCode;
    }

    public String getMgActSaleChnlCode() {
        return mgActSaleChnlCode;
    }

    public void setMgActSaleChnlCode(String mgActSaleChnlCode) {
        this.mgActSaleChnlCode = mgActSaleChnlCode;
    }

    public Date getChangedate() {
        return changedate;
    }

    public void setChangedate(Date changedate) {
        this.changedate = changedate;
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
        if (!(object instanceof MgSetDetailChlCfg)) {
            return false;
        }
        MgSetDetailChlCfg other = (MgSetDetailChlCfg) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cub.entities.MgSetDetailChlCfg[ id=" + id + " ]";
    }
    
}
