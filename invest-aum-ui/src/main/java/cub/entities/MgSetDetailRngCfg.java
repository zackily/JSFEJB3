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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "MG_SET_DETAIL_RNG_CFG")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MgSetDetailRngCfg.findAll", query = "SELECT m FROM MgSetDetailRngCfg m")
    , @NamedQuery(name = "MgSetDetailRngCfg.findById", query = "SELECT m FROM MgSetDetailRngCfg m WHERE m.id = :id")
    , @NamedQuery(name = "MgSetDetailRngCfg.findByMgActCode", query = "SELECT m FROM MgSetDetailRngCfg m WHERE m.mgActCode = :mgActCode")
    , @NamedQuery(name = "MgSetDetailRngCfg.findByMgActSubCode", query = "SELECT m FROM MgSetDetailRngCfg m WHERE m.mgActSubCode = :mgActSubCode")
    , @NamedQuery(name = "MgSetDetailRngCfg.findByMgActLowAmt", query = "SELECT m FROM MgSetDetailRngCfg m WHERE m.mgActLowAmt = :mgActLowAmt")
    , @NamedQuery(name = "MgSetDetailRngCfg.findByMgActHightAmt", query = "SELECT m FROM MgSetDetailRngCfg m WHERE m.mgActHightAmt = :mgActHightAmt")
    , @NamedQuery(name = "MgSetDetailRngCfg.findByMgActRate", query = "SELECT m FROM MgSetDetailRngCfg m WHERE m.mgActRate = :mgActRate")
    , @NamedQuery(name = "MgSetDetailRngCfg.findByChangedate", query = "SELECT m FROM MgSetDetailRngCfg m WHERE m.changedate = :changedate")})
public class MgSetDetailRngCfg implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @Size(max = 20)
    @Column(name = "MG_ACT_CODE")
    private String mgActCode;
    @Size(max = 5)
    @Column(name = "MG_ACT_SUB_CODE")
    private String mgActSubCode;
    @Column(name = "MG_ACT_LOW_AMT")
    private BigDecimal mgActLowAmt;
    @Column(name = "MG_ACT_HIGHT_AMT")
    private BigDecimal mgActHightAmt;
    @Column(name = "MG_ACT_RATE")
    private BigDecimal mgActRate;
    @Column(name = "CHANGEDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date changedate;

    public MgSetDetailRngCfg() {
    }

    public MgSetDetailRngCfg(BigDecimal id) {
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

    public BigDecimal getMgActLowAmt() {
        return mgActLowAmt;
    }

    public void setMgActLowAmt(BigDecimal mgActLowAmt) {
        this.mgActLowAmt = mgActLowAmt;
    }

    public BigDecimal getMgActHightAmt() {
        return mgActHightAmt;
    }

    public void setMgActHightAmt(BigDecimal mgActHightAmt) {
        this.mgActHightAmt = mgActHightAmt;
    }

    public BigDecimal getMgActRate() {
        return mgActRate;
    }

    public void setMgActRate(BigDecimal mgActRate) {
        this.mgActRate = mgActRate;
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
        if (!(object instanceof MgSetDetailRngCfg)) {
            return false;
        }
        MgSetDetailRngCfg other = (MgSetDetailRngCfg) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cub.entities.MgSetDetailRngCfg[ id=" + id + " ]";
    }
    
}
