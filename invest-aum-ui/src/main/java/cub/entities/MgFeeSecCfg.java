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
@Table(name = "MG_FEE_SEC_CFG")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MgFeeSecCfg.findAll", query = "SELECT m FROM MgFeeSecCfg m")
    , @NamedQuery(name = "MgFeeSecCfg.findByMgActCode", query = "SELECT m FROM MgFeeSecCfg m WHERE m.mgActCode = :mgActCode")
    , @NamedQuery(name = "MgFeeSecCfg.findByMgActSubCode", query = "SELECT m FROM MgFeeSecCfg m WHERE m.mgActSubCode = :mgActSubCode")
    , @NamedQuery(name = "MgFeeSecCfg.findByCorpId", query = "SELECT m FROM MgFeeSecCfg m WHERE m.corpId = :corpId")
    , @NamedQuery(name = "MgFeeSecCfg.findByFundId", query = "SELECT m FROM MgFeeSecCfg m WHERE m.fundId = :fundId")
    , @NamedQuery(name = "MgFeeSecCfg.findById", query = "SELECT m FROM MgFeeSecCfg m WHERE m.id = :id")})
public class MgFeeSecCfg implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "MG_ACT_CODE")
    private String mgActCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "MG_ACT_SUB_CODE")
    private String mgActSubCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "CORP_ID")
    private String corpId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "FUND_ID")
    private String fundId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;

    public MgFeeSecCfg() {
    }

    public MgFeeSecCfg(BigDecimal id) {
        this.id = id;
    }

    public MgFeeSecCfg(BigDecimal id, String mgActCode, String mgActSubCode, String corpId, String fundId) {
        this.id = id;
        this.mgActCode = mgActCode;
        this.mgActSubCode = mgActSubCode;
        this.corpId = corpId;
        this.fundId = fundId;
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

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    public String getFundId() {
        return fundId;
    }

    public void setFundId(String fundId) {
        this.fundId = fundId;
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
        if (!(object instanceof MgFeeSecCfg)) {
            return false;
        }
        MgFeeSecCfg other = (MgFeeSecCfg) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cub.entities.MgFeeSecCfg[ id=" + id + " ]";
    }
    
}
