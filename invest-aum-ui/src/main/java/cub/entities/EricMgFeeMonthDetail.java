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
@Table(name = "MG_FEE_MONTH_DETAIL")
@XmlRootElement
public class EricMgFeeMonthDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 12)
    @Column(name = "TST_ACCT_NO")
    private String trustacct;
    @Size(max = 6)
    @Column(name = "BASE_DATE")
    private String baseDate;
    @Size(max = 11)
    @Column(name = "CUST_ID")
    private String custId;
    @Size(max = 20)
    @Column(name = "PORTFOLIO_NO")
    private String portfolioNo;
    @Size(max = 20)
    @Column(name = "ACT_CODE")
    private String actCode;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "AUM_FEE_AMT")
    private BigDecimal aumFee;
    @Column(name = "AUM_FEE_BAL")
    private BigDecimal aumRemainFee;
    @Column(name = "COST_FEE_AMT")
    private BigDecimal costFee;
    @Column(name = "COST_FEE_BAL")
    private BigDecimal costRemainFee;
    @Size(max = 3)
    @Column(name = "CURRENCY")
    private String currency;
    @Column(name = "UPDATE_DTTM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDttm;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;

    public EricMgFeeMonthDetail() {
    }

    public EricMgFeeMonthDetail(BigDecimal id) {
        this.id = id;
    }

    public String getTrustacct() {
        return trustacct;
    }

    public void setTrustacct(String trustacct) {
        this.trustacct = trustacct;
    }

    public String getBaseDate() {
        return baseDate;
    }

    public void setBaseDate(String baseDate) {
        this.baseDate = baseDate;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getPortfolioNo() {
        return portfolioNo;
    }

    public void setPortfolioNo(String portfolioNo) {
        this.portfolioNo = portfolioNo;
    }

    public String getActCode() {
        return actCode;
    }

    public void setActCode(String actCode) {
        this.actCode = actCode;
    }

    public BigDecimal getAumFee() {
        return aumFee;
    }

    public void setAumFee(BigDecimal aumFee) {
        this.aumFee = aumFee;
    }

    public BigDecimal getAumRemainFee() {
        return aumRemainFee;
    }

    public void setAumRemainFee(BigDecimal aumRemainFee) {
        this.aumRemainFee = aumRemainFee;
    }

    public BigDecimal getCostFee() {
        return costFee;
    }

    public void setCostFee(BigDecimal costFee) {
        this.costFee = costFee;
    }

    public BigDecimal getCostRemainFee() {
        return costRemainFee;
    }

    public void setCostRemainFee(BigDecimal costRemainFee) {
        this.costRemainFee = costRemainFee;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Date getUpdateDttm() {
        return updateDttm;
    }

    public void setUpdateDttm(Date updateDttm) {
        this.updateDttm = updateDttm;
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
        if (!(object instanceof EricMgFeeMonthDetail)) {
            return false;
        }
        EricMgFeeMonthDetail other = (EricMgFeeMonthDetail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cub.entities.EricMgFeeMonthDetail[ id=" + id + " ]";
    }
    
}
