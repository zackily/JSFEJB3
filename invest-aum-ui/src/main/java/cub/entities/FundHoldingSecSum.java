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
@Table(name = "FUND_HOLDING_SEC_SUM")
@XmlRootElement
public class FundHoldingSecSum implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "COUNTERPARTY")
    private String corpId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "FUND_CODE")
    private String fundId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SEC_TYPE")
    private Character secType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "BASE_DATE")
    private String baseDate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "AUM_TOTAL_NTD_AMT")
    private BigDecimal aumNtdTot;
    @Column(name = "AUM_TOTAL_USD_AMT")
    private BigDecimal aumUsdTot;
    @Column(name = "AUM_TOTAL_FX_AMT")
    private BigDecimal aumFxTot;
    
    
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;

    public FundHoldingSecSum() {
    }

    public FundHoldingSecSum(BigDecimal id) {
        this.id = id;
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

    public Character getSecType() {
        return secType;
    }

    public void setSecType(Character secType) {
        this.secType = secType;
    }

    public String getBaseDate() {
        return baseDate;
    }

    public void setBaseDate(String baseDate) {
        this.baseDate = baseDate;
    }


    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getAumNtdTot() {
        return aumNtdTot;
    }

    public void setAumNtdTot(BigDecimal aumNtdTot) {
        this.aumNtdTot = aumNtdTot;
    }

    public BigDecimal getAumUsdTot() {
        return aumUsdTot;
    }

    public void setAumUsdTot(BigDecimal aumUsdTot) {
        this.aumUsdTot = aumUsdTot;
    }

    public BigDecimal getAumFxTot() {
        return aumFxTot;
    }

    public void setAumFxTot(BigDecimal aumFxTot) {
        this.aumFxTot = aumFxTot;
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
        if (!(object instanceof FundHoldingSecSum)) {
            return false;
        }
        FundHoldingSecSum other = (FundHoldingSecSum) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cub.entities.FundHoldingSecSum[ id=" + id + " ]";
    }
    
}
