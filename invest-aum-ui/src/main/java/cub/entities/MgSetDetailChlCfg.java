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
@Table(name = "MG_SET_DETAIL_CHL_CFG")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MgSetDetailChlCfg.findAll", query = "SELECT m FROM MgSetDetailChlCfg m")
    , @NamedQuery(name = "MgSetDetailChlCfg.findById", query = "SELECT m FROM MgSetDetailChlCfg m WHERE m.id = :id")
    , @NamedQuery(name = "MgSetDetailChlCfg.findByMgActCode", query = "SELECT m FROM MgSetDetailChlCfg m WHERE m.mgActCode = :mgActCode")
    , @NamedQuery(name = "MgSetDetailChlCfg.findByMgActSubCode", query = "SELECT m FROM MgSetDetailChlCfg m WHERE m.mgActSubCode = :mgActSubCode")
    , @NamedQuery(name = "MgSetDetailChlCfg.findByMgActSaleChnlCode", query = "SELECT m FROM MgSetDetailChlCfg m WHERE m.mgActSaleChnlCode = :mgActSaleChnlCode")})
public class MgSetDetailChlCfg implements Serializable {

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
    @Size(max = 1)
    @Column(name = "MG_ACT_SALE_CHNL_CODE")
    private String mgActSaleChnlCode;

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
