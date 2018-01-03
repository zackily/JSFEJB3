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
@Table(name = "MG_FEE_ACT_DETAIL_SEC_CFG")
@XmlRootElement
public class MgSetDetailSecCfg implements Serializable {

    @Size(max = 1)
    @Column(name = "ACT_FUND_TYPE")
    private String mgActFundType;
    
    @Size(max = 1)
    @Column(name = "ACT_FUND_KIND")
    private String mgActFundKind;
    
    @Column(name = "ACT_OPTION_CODE")
    private String actOptionCode;
    
    @Column(name = "ACT_ATTR_CODE")
    private String actAttrCode;

     @Column(name = "ACT_UPDATE_DTTM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date changedate;
    
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    @SequenceGenerator(name = "MSDSC_SEQ", sequenceName = "MG_FEE_DETAIL_SEC_CFG_SEQ", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MSDSC_SEQ")
    private BigDecimal id;
    @Size(max = 20)
    @Column(name = "ACT_CODE")
    private String mgActCode;
    @Size(max = 5)
    @Column(name = "ACT_SUB_CODE")
    private String mgActSubCode;
    @Size(max = 30)
    @Column(name = "ACT_COUNTERPARTY")
    private String mgActCounterparty;
    @Size(max = 8)
    @Column(name = "ACT_FUND_CODE")
    private String mgActFundNo;

    public MgSetDetailSecCfg() {
    }

    public MgSetDetailSecCfg(BigDecimal id) {
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

    public String getMgActCounterparty() {
        return mgActCounterparty;
    }

    public void setMgActCounterparty(String mgActCounterparty) {
        this.mgActCounterparty = mgActCounterparty;
    }

    public String getMgActFundNo() {
        return mgActFundNo;
    }

    public void setMgActFundNo(String mgActFundNo) {
        this.mgActFundNo = mgActFundNo;
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
        if (!(object instanceof MgSetDetailSecCfg)) {
            return false;
        }
        MgSetDetailSecCfg other = (MgSetDetailSecCfg) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cub.entities.MgSetDetailSecCfg[ id=" + id + " ]";
    }

    public String getMgActFundType() {
        return mgActFundType;
    }

    public void setMgActFundType(String mgActFundType) {
        this.mgActFundType = mgActFundType;
    }

    public String getMgActFundKind() {
        return mgActFundKind;
    }

    public void setMgActFundKind(String mgActFundKind) {
        this.mgActFundKind = mgActFundKind;
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

    public Date getChangedate() {
        return changedate;
    }

    public void setChangedate(Date changedate) {
        this.changedate = changedate;
    }

}
