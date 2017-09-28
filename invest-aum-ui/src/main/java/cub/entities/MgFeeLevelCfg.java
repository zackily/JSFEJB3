/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cub.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author NT48810
 */
@Entity
@Table(name = "MG_FEE_LEVEL_CFG")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MgFeeLevelCfg.findAll", query = "SELECT m FROM MgFeeLevelCfg m")
    , @NamedQuery(name = "MgFeeLevelCfg.findByActCode", query = "SELECT m FROM MgFeeLevelCfg m WHERE m.actCode = :actCode")
    , @NamedQuery(name = "MgFeeLevelCfg.findByActSubCode", query = "SELECT m FROM MgFeeLevelCfg m WHERE m.actSubCode = :actSubCode")
    , @NamedQuery(name = "MgFeeLevelCfg.findByLowAmt", query = "SELECT m FROM MgFeeLevelCfg m WHERE m.lowAmt = :lowAmt")
    , @NamedQuery(name = "MgFeeLevelCfg.findByHightAmt", query = "SELECT m FROM MgFeeLevelCfg m WHERE m.hightAmt = :hightAmt")
    , @NamedQuery(name = "MgFeeLevelCfg.findByRate", query = "SELECT m FROM MgFeeLevelCfg m WHERE m.rate = :rate")
    , @NamedQuery(name = "MgFeeLevelCfg.findById", query = "SELECT m FROM MgFeeLevelCfg m WHERE m.id = :id")})
public class MgFeeLevelCfg implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ACT_CODE")
    private String actCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "ACT_SUB_CODE")
    private String actSubCode;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "LOW_AMT")
    private BigDecimal lowAmt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "HIGHT_AMT")
    private BigDecimal hightAmt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RATE")
    private BigDecimal rate;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;

    public MgFeeLevelCfg() {
    }

    public MgFeeLevelCfg(BigDecimal id) {
        this.id = id;
    }

    public MgFeeLevelCfg(BigDecimal id, String actCode, String actSubCode, BigDecimal lowAmt, BigDecimal hightAmt, BigDecimal rate) {
        this.id = id;
        this.actCode = actCode;
        this.actSubCode = actSubCode;
        this.lowAmt = lowAmt;
        this.hightAmt = hightAmt;
        this.rate = rate;
    }

    public String getActCode() {
        return actCode;
    }

    public void setActCode(String actCode) {
        this.actCode = actCode;
    }

    public String getActSubCode() {
        return actSubCode;
    }

    public void setActSubCode(String actSubCode) {
        this.actSubCode = actSubCode;
    }

    public BigDecimal getLowAmt() {
        return lowAmt;
    }

    public void setLowAmt(BigDecimal lowAmt) {
        this.lowAmt = lowAmt;
    }

    public BigDecimal getHightAmt() {
        return hightAmt;
    }

    public void setHightAmt(BigDecimal hightAmt) {
        this.hightAmt = hightAmt;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
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
        if (!(object instanceof MgFeeLevelCfg)) {
            return false;
        }
        MgFeeLevelCfg other = (MgFeeLevelCfg) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cub.entities.MgFeeLevelCfg[ id=" + id + " ]";
    }
    
}
