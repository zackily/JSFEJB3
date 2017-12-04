/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cub.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
@Table(name = "ERIC_MG_FEE_MONTH")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EricMgFeeMonth.findAll", query = "SELECT e FROM EricMgFeeMonth e")
    , @NamedQuery(name = "EricMgFeeMonth.findByCustId", query = "SELECT e FROM EricMgFeeMonth e WHERE e.custId = :custId")
    , @NamedQuery(name = "EricMgFeeMonth.findByBaseDate", query = "SELECT e FROM EricMgFeeMonth e WHERE e.baseDate = :baseDate")
    , @NamedQuery(name = "EricMgFeeMonth.findByActCode", query = "SELECT e FROM EricMgFeeMonth e WHERE e.actCode = :actCode")
    , @NamedQuery(name = "EricMgFeeMonth.findByActSubCode", query = "SELECT e FROM EricMgFeeMonth e WHERE e.actSubCode = :actSubCode")
    , @NamedQuery(name = "EricMgFeeMonth.findByCurrency", query = "SELECT e FROM EricMgFeeMonth e WHERE e.currency = :currency")
    , @NamedQuery(name = "EricMgFeeMonth.findByAumFee", query = "SELECT e FROM EricMgFeeMonth e WHERE e.aumFee = :aumFee")
    , @NamedQuery(name = "EricMgFeeMonth.findByAumRemainFee", query = "SELECT e FROM EricMgFeeMonth e WHERE e.aumRemainFee = :aumRemainFee")
    , @NamedQuery(name = "EricMgFeeMonth.findByCostFee", query = "SELECT e FROM EricMgFeeMonth e WHERE e.costFee = :costFee")
    , @NamedQuery(name = "EricMgFeeMonth.findByCostRemainFee", query = "SELECT e FROM EricMgFeeMonth e WHERE e.costRemainFee = :costRemainFee")
    , @NamedQuery(name = "EricMgFeeMonth.findBySettleUserId", query = "SELECT e FROM EricMgFeeMonth e WHERE e.settleUserId = :settleUserId")
    , @NamedQuery(name = "EricMgFeeMonth.findBySettleDate", query = "SELECT e FROM EricMgFeeMonth e WHERE e.settleDate = :settleDate")
    , @NamedQuery(name = "EricMgFeeMonth.findByUpdateDttm", query = "SELECT e FROM EricMgFeeMonth e WHERE e.updateDttm = :updateDttm")
    , @NamedQuery(name = "EricMgFeeMonth.findByAumTwFee", query = "SELECT e FROM EricMgFeeMonth e WHERE e.aumTwFee = :aumTwFee")
    , @NamedQuery(name = "EricMgFeeMonth.findByAumUsdFee", query = "SELECT e FROM EricMgFeeMonth e WHERE e.aumUsdFee = :aumUsdFee")
    , @NamedQuery(name = "EricMgFeeMonth.findByCostTwFee", query = "SELECT e FROM EricMgFeeMonth e WHERE e.costTwFee = :costTwFee")
    , @NamedQuery(name = "EricMgFeeMonth.findByCostUsdFee", query = "SELECT e FROM EricMgFeeMonth e WHERE e.costUsdFee = :costUsdFee")
    , @NamedQuery(name = "EricMgFeeMonth.findById", query = "SELECT e FROM EricMgFeeMonth e WHERE e.id = :id")})
public class EricMgFeeMonth implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "CUST_ID")
    private String custId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "BASE_DATE")
    private String baseDate;
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
    @Size(min = 1, max = 3)
    @Column(name = "CURRENCY")
    private String currency;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "AUM_FEE")
    private BigDecimal aumFee;
    @Column(name = "AUM_REMAIN_FEE")
    private BigDecimal aumRemainFee;
    @Column(name = "COST_FEE")
    private BigDecimal costFee;
    @Column(name = "COST_REMAIN_FEE")
    private BigDecimal costRemainFee;
    @Size(max = 5)
    @Column(name = "SETTLE_USER_ID")
    private String settleUserId;
    @Size(max = 8)
    @Column(name = "SETTLE_DATE")
    private String settleDate;
    @Column(name = "UPDATE_DTTM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDttm;
    @Column(name = "AUM_TW_FEE")
    private BigInteger aumTwFee;
    @Column(name = "AUM_USD_FEE")
    private BigInteger aumUsdFee;
    @Column(name = "COST_TW_FEE")
    private BigInteger costTwFee;
    @Column(name = "COST_USD_FEE")
    private BigInteger costUsdFee;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;

    public EricMgFeeMonth() {
    }

    public EricMgFeeMonth(BigDecimal id) {
        this.id = id;
    }

    public EricMgFeeMonth(BigDecimal id, String custId, String baseDate, String actCode, String actSubCode, String currency) {
        this.id = id;
        this.custId = custId;
        this.baseDate = baseDate;
        this.actCode = actCode;
        this.actSubCode = actSubCode;
        this.currency = currency;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getBaseDate() {
        return baseDate;
    }

    public void setBaseDate(String baseDate) {
        this.baseDate = baseDate;
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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
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

    public String getSettleUserId() {
        return settleUserId;
    }

    public void setSettleUserId(String settleUserId) {
        this.settleUserId = settleUserId;
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

    public BigInteger getAumTwFee() {
        return aumTwFee;
    }

    public void setAumTwFee(BigInteger aumTwFee) {
        this.aumTwFee = aumTwFee;
    }

    public BigInteger getAumUsdFee() {
        return aumUsdFee;
    }

    public void setAumUsdFee(BigInteger aumUsdFee) {
        this.aumUsdFee = aumUsdFee;
    }

    public BigInteger getCostTwFee() {
        return costTwFee;
    }

    public void setCostTwFee(BigInteger costTwFee) {
        this.costTwFee = costTwFee;
    }

    public BigInteger getCostUsdFee() {
        return costUsdFee;
    }

    public void setCostUsdFee(BigInteger costUsdFee) {
        this.costUsdFee = costUsdFee;
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
        if (!(object instanceof EricMgFeeMonth)) {
            return false;
        }
        EricMgFeeMonth other = (EricMgFeeMonth) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cub.entities.EricMgFeeMonth[ id=" + id + " ]";
    }
    
}
