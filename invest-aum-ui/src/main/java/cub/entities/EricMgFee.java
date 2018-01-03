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
@Table(name = "MG_FEE")
@XmlRootElement
public class EricMgFee implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 12)
    @Column(name = "TRUSTACCT")
    private String trustacct;
    @Size(max = 8)
    @Column(name = "BASE_DATE")
    private String baseDate;
    @Size(max = 11)
    @Column(name = "CUST_ID")
    private String custId;
    @Size(max = 8)
    @Column(name = "FUND_ID")
    private String fundId;
    @Size(max = 20)
    @Column(name = "ACT_CODE")
    private String actCode;
    @Size(max = 5)
    @Column(name = "ACT_SUB_CODE")
    private String actSubCode;
    @Size(max = 20)
    @Column(name = "PORTFOLIO_NO")
    private String portfolioNo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "AUM_TWD_FEE")
    private BigDecimal aumTwdFee;
    @Column(name = "COST_TWD_FEE")
    private BigDecimal costTwdFee;
    @Column(name = "AUM_USD_FEE")
    private BigDecimal aumUsdFee;
    @Column(name = "COST_USD_FEE")
    private BigDecimal costUsdFee;
    @Column(name = "AUM_FEE")
    private BigDecimal aumFee;
    @Column(name = "COST_FEE")
    private BigDecimal costFee;
    @Size(max = 3)
    @Column(name = "CURRENCY")
    private String currency;
    @Column(name = "TWD_EXRATE")
    private BigDecimal twdExrate;
    @Column(name = "USD_EXRATE")
    private BigDecimal usdExrate;
    @Size(max = 5)
    @Column(name = "CANVASSER")
    private String canvasser;
    @Size(max = 3)
    @Column(name = "BRANCH_ID")
    private String branchId;
    @Column(name = "UPDATE_DTTM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDttm;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;

    public EricMgFee() {
    }

    public EricMgFee(BigDecimal id) {
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

    public String getFundId() {
        return fundId;
    }

    public void setFundId(String fundId) {
        this.fundId = fundId;
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

    public String getPortfolioNo() {
        return portfolioNo;
    }

    public void setPortfolioNo(String portfolioNo) {
        this.portfolioNo = portfolioNo;
    }

    public BigDecimal getAumTwdFee() {
        return aumTwdFee;
    }

    public void setAumTwdFee(BigDecimal aumTwdFee) {
        this.aumTwdFee = aumTwdFee;
    }

    public BigDecimal getCostTwdFee() {
        return costTwdFee;
    }

    public void setCostTwdFee(BigDecimal costTwdFee) {
        this.costTwdFee = costTwdFee;
    }

    public BigDecimal getAumUsdFee() {
        return aumUsdFee;
    }

    public void setAumUsdFee(BigDecimal aumUsdFee) {
        this.aumUsdFee = aumUsdFee;
    }

    public BigDecimal getCostUsdFee() {
        return costUsdFee;
    }

    public void setCostUsdFee(BigDecimal costUsdFee) {
        this.costUsdFee = costUsdFee;
    }

    public BigDecimal getAumFee() {
        return aumFee;
    }

    public void setAumFee(BigDecimal aumFee) {
        this.aumFee = aumFee;
    }

    public BigDecimal getCostFee() {
        return costFee;
    }

    public void setCostFee(BigDecimal costFee) {
        this.costFee = costFee;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getTwdExrate() {
        return twdExrate;
    }

    public void setTwdExrate(BigDecimal twdExrate) {
        this.twdExrate = twdExrate;
    }

    public BigDecimal getUsdExrate() {
        return usdExrate;
    }

    public void setUsdExrate(BigDecimal usdExrate) {
        this.usdExrate = usdExrate;
    }

    public String getCanvasser() {
        return canvasser;
    }

    public void setCanvasser(String canvasser) {
        this.canvasser = canvasser;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
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
        if (!(object instanceof EricMgFee)) {
            return false;
        }
        EricMgFee other = (EricMgFee) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cub.entities.EricMgFee[ id=" + id + " ]";
    }
    
}
