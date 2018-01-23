/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cub.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "MG_FEE_MONTH_DETAIL")
@XmlRootElement
public class MgFeeMonthDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "TST_ACC_NO")
    private String tstAccNo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "BASE_DATE")
    private String baseDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "CUST_ID")
    private String custId;
    @Size(max = 20)
    @Column(name = "PORTFOLIO_NO")
    private String portfolioNo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ACT_CODE")
    private String actCode;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "AUM_FEE_AMT")
    private BigDecimal aumFeeAmt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "AUM_FEE_BAL")
    private BigDecimal aumFeeBal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COST_FEE_AMT")
    private BigDecimal costFeeAmt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COST_FEE_BAL")
    private BigDecimal costFeeBal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "CURRENCY")
    private String currency;
    @Basic(optional = false)
    @NotNull
    @Column(name = "UPDATE_DTTM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDttm;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MFD_SEQ")
    @SequenceGenerator(name = "MFD_SEQ", sequenceName = "MG_FEE_DETAIL_SEQ", initialValue = 1, allocationSize = 1)
    private BigDecimal id;

    public MgFeeMonthDetail() {
    }

    public MgFeeMonthDetail(BigDecimal id) {
        this.id = id;
    }

    public String getTstAccNo() {
        return tstAccNo;
    }

    public void setTstAccNo(String tstAccNo) {
        this.tstAccNo = tstAccNo;
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

    public BigDecimal getAumFeeAmt() {
        return aumFeeAmt;
    }

    public void setAumFeeAmt(BigDecimal aumFeeAmt) {
        this.aumFeeAmt = aumFeeAmt;
    }

    public BigDecimal getAumFeeBal() {
        return aumFeeBal;
    }

    public void setAumFeeBal(BigDecimal aumFeeBal) {
        this.aumFeeBal = aumFeeBal;
    }

    public BigDecimal getCostFeeAmt() {
        return costFeeAmt;
    }

    public void setCostFeeAmt(BigDecimal costFeeAmt) {
        this.costFeeAmt = costFeeAmt;
    }

    public BigDecimal getCostFeeBal() {
        return costFeeBal;
    }

    public void setCostFeeBal(BigDecimal costFeeBal) {
        this.costFeeBal = costFeeBal;
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
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MgFeeMonthDetail other = (MgFeeMonthDetail) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }


    @Override
    public String toString() {
        return "cub.entities.MgFeeMonthDetail[ id=" + id + " ]";
    }
    
}
