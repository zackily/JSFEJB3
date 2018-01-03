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
@Table(name = "MG_FEE_ACT_DETAIL_SEC_CLU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MgFeeActDetailSecClu.findAll", query = "SELECT m FROM MgFeeActDetailSecClu m")
    , @NamedQuery(name = "MgFeeActDetailSecClu.findById", query = "SELECT m FROM MgFeeActDetailSecClu m WHERE m.id = :id")
    , @NamedQuery(name = "MgFeeActDetailSecClu.findByActCode", query = "SELECT m FROM MgFeeActDetailSecClu m WHERE m.actCode = :actCode")
    , @NamedQuery(name = "MgFeeActDetailSecClu.findByActSubCode", query = "SELECT m FROM MgFeeActDetailSecClu m WHERE m.actSubCode = :actSubCode")
    , @NamedQuery(name = "MgFeeActDetailSecClu.findByActOptionCode", query = "SELECT m FROM MgFeeActDetailSecClu m WHERE m.actOptionCode = :actOptionCode")
    , @NamedQuery(name = "MgFeeActDetailSecClu.findByActAttrCode", query = "SELECT m FROM MgFeeActDetailSecClu m WHERE m.actAttrCode = :actAttrCode")
    , @NamedQuery(name = "MgFeeActDetailSecClu.findByActCounterparty", query = "SELECT m FROM MgFeeActDetailSecClu m WHERE m.actCounterparty = :actCounterparty")
    , @NamedQuery(name = "MgFeeActDetailSecClu.findByActFundCode", query = "SELECT m FROM MgFeeActDetailSecClu m WHERE m.actFundCode = :actFundCode")
    , @NamedQuery(name = "MgFeeActDetailSecClu.findByActUpdateDttm", query = "SELECT m FROM MgFeeActDetailSecClu m WHERE m.actUpdateDttm = :actUpdateDttm")})
public class MgFeeActDetailSecClu implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ACT_OPTION_CODE")
    private String actOptionCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ACT_ATTR_CODE")
    private String actAttrCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "ACT_COUNTERPARTY")
    private String actCounterparty;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "ACT_FUND_CODE")
    private String actFundCode;
    @Column(name = "ACT_UPDATE_DTTM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date actUpdateDttm;

    public MgFeeActDetailSecClu() {
    }

    public MgFeeActDetailSecClu(BigDecimal id) {
        this.id = id;
    }

    public MgFeeActDetailSecClu(BigDecimal id, String actCode, String actSubCode, String actOptionCode, String actAttrCode, String actCounterparty, String actFundCode) {
        this.id = id;
        this.actCode = actCode;
        this.actSubCode = actSubCode;
        this.actOptionCode = actOptionCode;
        this.actAttrCode = actAttrCode;
        this.actCounterparty = actCounterparty;
        this.actFundCode = actFundCode;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
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

    public String getActOptionCode() {
        return actOptionCode;
    }

    public void setActOptionCode(String actOptionCode) {
        this.actOptionCode = actOptionCode;
    }

    public String getActAttrCode() {
        return actAttrCode;
    }

    public void setActAttrCode(String actAttrCode) {
        this.actAttrCode = actAttrCode;
    }

    public String getActCounterparty() {
        return actCounterparty;
    }

    public void setActCounterparty(String actCounterparty) {
        this.actCounterparty = actCounterparty;
    }

    public String getActFundCode() {
        return actFundCode;
    }

    public void setActFundCode(String actFundCode) {
        this.actFundCode = actFundCode;
    }

    public Date getActUpdateDttm() {
        return actUpdateDttm;
    }

    public void setActUpdateDttm(Date actUpdateDttm) {
        this.actUpdateDttm = actUpdateDttm;
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
        if (!(object instanceof MgFeeActDetailSecClu)) {
            return false;
        }
        MgFeeActDetailSecClu other = (MgFeeActDetailSecClu) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cub.entities.MgFeeActDetailSecClu[ id=" + id + " ]";
    }
    
}
