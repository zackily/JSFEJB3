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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author NT48810
 */
@Entity
@Table(name = "MG_FEE_MONTH")
@XmlRootElement
public class MgFeeMonth implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "CUST_ID")
    private String custId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "BASE_MONTH")
    private String baseMonth;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ACT_CODE")
    private String actCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "CURRENCY")
    private String currency;
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
    @Size(min = 1, max = 20)
    @Column(name = "SETTLE_USER_NO")
    private String settleUserNo;
    @Column(name = "SETTLE_USER_NAME")
    private String settleUserName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "SETTLE_DATE")
    private String settleDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "UPDATE_DTTM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDttm;
    @Transient
    private BigDecimal aumFeePaid;
    
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MFM_SEQ")
    @SequenceGenerator(name = "MFM_SEQ", sequenceName = "MG_FEE_MONTH_SEQ", initialValue = 1, allocationSize = 1)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.id);
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
        final MgFeeMonth other = (MgFeeMonth) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }


    @Override
    public String toString() {
        return "cub.entities.MgFeeMonth[ id=" + id + " ]";
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getBaseMonth() {
        return baseMonth;
    }

    public void setBaseMonth(String baseMonth) {
        this.baseMonth = baseMonth;
    }


    public String getActCode() {
        return actCode;
    }

    public void setActCode(String actCode) {
        this.actCode = actCode;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
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

    public String getSettleUserNo() {
        return settleUserNo;
    }

    public void setSettleUserNo(String settleUserNo) {
        this.settleUserNo = settleUserNo;
    }

    public String getSettleUserName() {
        return settleUserName;
    }

    public void setSettleUserName(String settleUserName) {
        this.settleUserName = settleUserName;
    }

    public String getSettleDate() {
        return settleDate;
    }

    public void setSettleDate(String settleDate) {
        this.settleDate = settleDate;
    }

    public Date getUpdateDttm() {
        return updateDttm;
    }

    public void setUpdateDttm(Date updateDttm) {
        this.updateDttm = updateDttm;
    }

    public BigDecimal getAumFeePaid() {
        return aumFeeAmt.subtract(aumFeeBal);
    }

    public void setAumFeePaid(BigDecimal aumFeePaid) {
        this.aumFeePaid = aumFeePaid;
    }

}
