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
@Table(name = "MG_FEE_MONTH_WITHDRAW_LOG")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MgFeeMonthWithdrawLog.findAll", query = "SELECT m FROM MgFeeMonthWithdrawLog m")
    , @NamedQuery(name = "MgFeeMonthWithdrawLog.findByTrustacct", query = "SELECT m FROM MgFeeMonthWithdrawLog m WHERE m.trustacct = :trustacct")
    , @NamedQuery(name = "MgFeeMonthWithdrawLog.findByBaseDate", query = "SELECT m FROM MgFeeMonthWithdrawLog m WHERE m.baseDate = :baseDate")
    , @NamedQuery(name = "MgFeeMonthWithdrawLog.findBySerialNo", query = "SELECT m FROM MgFeeMonthWithdrawLog m WHERE m.serialNo = :serialNo")
    , @NamedQuery(name = "MgFeeMonthWithdrawLog.findByProcessDate", query = "SELECT m FROM MgFeeMonthWithdrawLog m WHERE m.processDate = :processDate")
    , @NamedQuery(name = "MgFeeMonthWithdrawLog.findByCustId", query = "SELECT m FROM MgFeeMonthWithdrawLog m WHERE m.custId = :custId")
    , @NamedQuery(name = "MgFeeMonthWithdrawLog.findByActCode", query = "SELECT m FROM MgFeeMonthWithdrawLog m WHERE m.actCode = :actCode")
    , @NamedQuery(name = "MgFeeMonthWithdrawLog.findByAumFee", query = "SELECT m FROM MgFeeMonthWithdrawLog m WHERE m.aumFee = :aumFee")
    , @NamedQuery(name = "MgFeeMonthWithdrawLog.findByCostFee", query = "SELECT m FROM MgFeeMonthWithdrawLog m WHERE m.costFee = :costFee")
    , @NamedQuery(name = "MgFeeMonthWithdrawLog.findByCur", query = "SELECT m FROM MgFeeMonthWithdrawLog m WHERE m.cur = :cur")
    , @NamedQuery(name = "MgFeeMonthWithdrawLog.findByChargeType", query = "SELECT m FROM MgFeeMonthWithdrawLog m WHERE m.chargeType = :chargeType")
    , @NamedQuery(name = "MgFeeMonthWithdrawLog.findByUserId", query = "SELECT m FROM MgFeeMonthWithdrawLog m WHERE m.userId = :userId")
    , @NamedQuery(name = "MgFeeMonthWithdrawLog.findById", query = "SELECT m FROM MgFeeMonthWithdrawLog m WHERE m.id = :id")})
public class MgFeeMonthWithdrawLog implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "TRUSTACCT")
    private String trustacct;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "BASE_DATE")
    private String baseDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 13)
    @Column(name = "SERIAL_NO")
    private String serialNo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "PROCESS_DATE")
    private String processDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "CUST_ID")
    private String custId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ACT_CODE")
    private String actCode;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "AUM_FEE")
    private BigDecimal aumFee;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COST_FEE")
    private BigDecimal costFee;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "CUR")
    private String cur;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CHARGE_TYPE")
    private Character chargeType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "USER_ID")
    private String userId;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;

    public MgFeeMonthWithdrawLog() {
    }

    public MgFeeMonthWithdrawLog(BigDecimal id) {
        this.id = id;
    }

    public MgFeeMonthWithdrawLog(BigDecimal id, String trustacct, String baseDate, String serialNo, String processDate, String custId, String actCode, String actSubCode, BigDecimal aumFee, BigDecimal costFee, String cur, Character chargeType, String userId) {
        this.id = id;
        this.trustacct = trustacct;
        this.baseDate = baseDate;
        this.serialNo = serialNo;
        this.processDate = processDate;
        this.custId = custId;
        this.actCode = actCode;
        this.aumFee = aumFee;
        this.costFee = costFee;
        this.cur = cur;
        this.chargeType = chargeType;
        this.userId = userId;
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

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getProcessDate() {
        return processDate;
    }

    public void setProcessDate(String processDate) {
        this.processDate = processDate;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
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

    public BigDecimal getCostFee() {
        return costFee;
    }

    public void setCostFee(BigDecimal costFee) {
        this.costFee = costFee;
    }

    public String getCur() {
        return cur;
    }

    public void setCur(String cur) {
        this.cur = cur;
    }

    public Character getChargeType() {
        return chargeType;
    }

    public void setChargeType(Character chargeType) {
        this.chargeType = chargeType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
        if (!(object instanceof MgFeeMonthWithdrawLog)) {
            return false;
        }
        MgFeeMonthWithdrawLog other = (MgFeeMonthWithdrawLog) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cub.entities.MgFeeMonthWithdrawLog[ id=" + id + " ]";
    }
    
}
