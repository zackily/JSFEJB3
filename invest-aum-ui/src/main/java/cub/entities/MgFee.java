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
@Table(name = "MG_FEE")
@XmlRootElement
public class MgFee implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "TST_ACCT_NO")
    private Character trustacct;
    @Basic(optional = false)
    @NotNull
    @Column(name = "BASE_DATE")
    private Character baseDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CUST_ID")
    private Character custId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FUND_CODE")
    private Character fundId;
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
    @Size(min = 1, max = 20)
    @Column(name = "PORTFOLIO_NO")
    private String portfolioNo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "AUM_NTD_FEE_AMT")
    private BigDecimal aumTwFee;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COST_NTD_FEE_AMT")
    private BigDecimal costTwFee;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "AUM_USD_FEE_AMT")
    private BigDecimal aumUsdFee;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COST_USD_FEE_AMT")
    private BigDecimal costUsdFee;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "AUM_FEE_AMT")
    private BigDecimal aumFee;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COST_FEE_AMT")
    private BigDecimal costFee;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CURRENCY")
    private Character cur;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NTD_EXRATE")
    private BigDecimal exrate;
    
     @Column(name = "USD_EXRATE")
    private BigDecimal usdExrate;
     
    @Basic(optional = false)
    @NotNull
    @Column(name = "CANVASSER")
    private Character canvasser;
    @Basic(optional = false)
    @NotNull
    @Column(name = "BRANCH_CODE")
    private Character branchId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "UPDATE_DTTM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDttm;

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id   
    @NotNull
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MF_SEQ")
    @SequenceGenerator(name = "MF_SEQ", sequenceName = "MG_FEE_SEQ", initialValue = 1, allocationSize = 1)    
    private Long id;
    

    public MgFee() {
    }

    public MgFee(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public BigDecimal getUsdExrate() {
        return usdExrate;
    }

    public void setUsdExrate(BigDecimal usdExrate) {
        this.usdExrate = usdExrate;
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
        if (!(object instanceof MgFee)) {
            return false;
        }
        MgFee other = (MgFee) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cub.entities.MgFee[ id=" + id + " ]";
    }

    public Character getTrustacct() {
        return trustacct;
    }

    public void setTrustacct(Character trustacct) {
        this.trustacct = trustacct;
    }

    public Character getBaseDate() {
        return baseDate;
    }

    public void setBaseDate(Character baseDate) {
        this.baseDate = baseDate;
    }

    public Character getCustId() {
        return custId;
    }

    public void setCustId(Character custId) {
        this.custId = custId;
    }

    public Character getFundId() {
        return fundId;
    }

    public void setFundId(Character fundId) {
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

    public BigDecimal getAumTwFee() {
        return aumTwFee;
    }

    public void setAumTwFee(BigDecimal aumTwFee) {
        this.aumTwFee = aumTwFee;
    }

    public BigDecimal getCostTwFee() {
        return costTwFee;
    }

    public void setCostTwFee(BigDecimal costTwFee) {
        this.costTwFee = costTwFee;
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

    public Character getCur() {
        return cur;
    }

    public void setCur(Character cur) {
        this.cur = cur;
    }

    public BigDecimal getExrate() {
        return exrate;
    }

    public void setExrate(BigDecimal exrate) {
        this.exrate = exrate;
    }

    public Character getCanvasser() {
        return canvasser;
    }

    public void setCanvasser(Character canvasser) {
        this.canvasser = canvasser;
    }

    public Character getBranchId() {
        return branchId;
    }

    public void setBranchId(Character branchId) {
        this.branchId = branchId;
    }

    public Date getUpdateDttm() {
        return updateDttm;
    }

    public void setUpdateDttm(Date updateDttm) {
        this.updateDttm = updateDttm;
    }
    
}
