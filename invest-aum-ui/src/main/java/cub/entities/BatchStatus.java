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
@Table(name = "BATCH_STATUS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BatchStatus.findAll", query = "SELECT b FROM BatchStatus b")
    , @NamedQuery(name = "BatchStatus.findById", query = "SELECT b FROM BatchStatus b WHERE b.id = :id")
    , @NamedQuery(name = "BatchStatus.findByActCode", query = "SELECT b FROM BatchStatus b WHERE b.actCode = :actCode")
    , @NamedQuery(name = "BatchStatus.findByAumSettleDate", query = "SELECT b FROM BatchStatus b WHERE b.aumSettleDate = :aumSettleDate")
    , @NamedQuery(name = "BatchStatus.findByAumSettleExecTime", query = "SELECT b FROM BatchStatus b WHERE b.aumSettleExecTime = :aumSettleExecTime")
    , @NamedQuery(name = "BatchStatus.findByFeeSettleDate", query = "SELECT b FROM BatchStatus b WHERE b.feeSettleDate = :feeSettleDate")
    , @NamedQuery(name = "BatchStatus.findByFeeSettleExecTime", query = "SELECT b FROM BatchStatus b WHERE b.feeSettleExecTime = :feeSettleExecTime")
    , @NamedQuery(name = "BatchStatus.findByFeeMthsettleMonth", query = "SELECT b FROM BatchStatus b WHERE b.feeMthsettleMonth = :feeMthsettleMonth")
    , @NamedQuery(name = "BatchStatus.findByFeeMthsettleExecTime", query = "SELECT b FROM BatchStatus b WHERE b.feeMthsettleExecTime = :feeMthsettleExecTime")
    , @NamedQuery(name = "BatchStatus.findByFeeMthsettleMonthUserId", query = "SELECT b FROM BatchStatus b WHERE b.feeMthsettleMonthUserId = :feeMthsettleMonthUserId")
    , @NamedQuery(name = "BatchStatus.findByFeeChargeDate", query = "SELECT b FROM BatchStatus b WHERE b.feeChargeDate = :feeChargeDate")
    , @NamedQuery(name = "BatchStatus.findByFeeChargeUserId", query = "SELECT b FROM BatchStatus b WHERE b.feeChargeUserId = :feeChargeUserId")})
public class BatchStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @NotNull
    @Column(name = "ID")
    @SequenceGenerator(name = "BS_SEQ", sequenceName = "BATCH_STATUS_SEQ", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BS_SEQ")
    private Long id;
    @Size(max = 10)
    @Column(name = "ACT_CODE")
    private String actCode;
    @Size(max = 8)
    @Column(name = "AUM_SETTLE_DATE")
    private String aumSettleDate;
    @Column(name = "AUM_SETTLE_EXEC_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date aumSettleExecTime;
    @Size(max = 8)
    @Column(name = "FEE_SETTLE_DATE")
    private String feeSettleDate;
    @Column(name = "FEE_SETTLE_EXEC_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date feeSettleExecTime;
    @Size(max = 6)
    @Column(name = "FEE_MTHSETTLE_MONTH")
    private String feeMthsettleMonth;
    @Column(name = "FEE_MTHSETTLE_EXEC_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date feeMthsettleExecTime;
    @Size(max = 5)
    @Column(name = "FEE_MTHSETTLE_MONTH_USER_ID")
    private String feeMthsettleMonthUserId;
    @Size(max = 6)
    @Column(name = "FEE_CHARGE_DATE")
    private String feeChargeDate;
    @Size(max = 5)
    @Column(name = "FEE_CHARGE_USER_ID")
    private String feeChargeUserId;

    public BatchStatus() {
    }

    public BatchStatus(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActCode() {
        return actCode;
    }

    public void setActCode(String actCode) {
        this.actCode = actCode;
    }

    public String getAumSettleDate() {
        return aumSettleDate;
    }

    public void setAumSettleDate(String aumSettleDate) {
        this.aumSettleDate = aumSettleDate;
    }

    public Date getAumSettleExecTime() {
        return aumSettleExecTime;
    }

    public void setAumSettleExecTime(Date aumSettleExecTime) {
        this.aumSettleExecTime = aumSettleExecTime;
    }

    public String getFeeSettleDate() {
        return feeSettleDate;
    }

    public void setFeeSettleDate(String feeSettleDate) {
        this.feeSettleDate = feeSettleDate;
    }

    public Date getFeeSettleExecTime() {
        return feeSettleExecTime;
    }

    public void setFeeSettleExecTime(Date feeSettleExecTime) {
        this.feeSettleExecTime = feeSettleExecTime;
    }

    public String getFeeMthsettleMonth() {
        return feeMthsettleMonth;
    }

    public void setFeeMthsettleMonth(String feeMthsettleMonth) {
        this.feeMthsettleMonth = feeMthsettleMonth;
    }

    public Date getFeeMthsettleExecTime() {
        return feeMthsettleExecTime;
    }

    public void setFeeMthsettleExecTime(Date feeMthsettleExecTime) {
        this.feeMthsettleExecTime = feeMthsettleExecTime;
    }

    public String getFeeMthsettleMonthUserId() {
        return feeMthsettleMonthUserId;
    }

    public void setFeeMthsettleMonthUserId(String feeMthsettleMonthUserId) {
        this.feeMthsettleMonthUserId = feeMthsettleMonthUserId;
    }

    public String getFeeChargeDate() {
        return feeChargeDate;
    }

    public void setFeeChargeDate(String feeChargeDate) {
        this.feeChargeDate = feeChargeDate;
    }

    public String getFeeChargeUserId() {
        return feeChargeUserId;
    }

    public void setFeeChargeUserId(String feeChargeUserId) {
        this.feeChargeUserId = feeChargeUserId;
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
        if (!(object instanceof BatchStatus)) {
            return false;
        }
        BatchStatus other = (BatchStatus) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cub.entities.BatchStatus[ id=" + id + " ]";
    }

}
