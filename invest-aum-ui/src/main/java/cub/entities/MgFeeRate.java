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
@Table(name = "MG_FEE_RATE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MgFeeRate.findAll", query = "SELECT m FROM MgFeeRate m")
    , @NamedQuery(name = "MgFeeRate.findById", query = "SELECT m FROM MgFeeRate m WHERE m.id = :id")
    , @NamedQuery(name = "MgFeeRate.findByActCode", query = "SELECT m FROM MgFeeRate m WHERE m.actCode = :actCode")
    , @NamedQuery(name = "MgFeeRate.findByCtSeq", query = "SELECT m FROM MgFeeRate m WHERE m.ctSeq = :ctSeq")
    , @NamedQuery(name = "MgFeeRate.findByCustId", query = "SELECT m FROM MgFeeRate m WHERE m.custId = :custId")
    , @NamedQuery(name = "MgFeeRate.findByHldDate", query = "SELECT m FROM MgFeeRate m WHERE m.hldDate = :hldDate")
    , @NamedQuery(name = "MgFeeRate.findBySaleChnl", query = "SELECT m FROM MgFeeRate m WHERE m.saleChnl = :saleChnl")
    , @NamedQuery(name = "MgFeeRate.findByActType", query = "SELECT m FROM MgFeeRate m WHERE m.actType = :actType")
    , @NamedQuery(name = "MgFeeRate.findByAum", query = "SELECT m FROM MgFeeRate m WHERE m.aum = :aum")
    , @NamedQuery(name = "MgFeeRate.findByChargeObj", query = "SELECT m FROM MgFeeRate m WHERE m.chargeObj = :chargeObj")
    , @NamedQuery(name = "MgFeeRate.findByActSetType", query = "SELECT m FROM MgFeeRate m WHERE m.actSetType = :actSetType")
    , @NamedQuery(name = "MgFeeRate.findByMgActDSetNo", query = "SELECT m FROM MgFeeRate m WHERE m.mgActDSetNo = :mgActDSetNo")
    , @NamedQuery(name = "MgFeeRate.findByActFee", query = "SELECT m FROM MgFeeRate m WHERE m.actFee = :actFee")
    , @NamedQuery(name = "MgFeeRate.findByAumTwFee", query = "SELECT m FROM MgFeeRate m WHERE m.aumTwFee = :aumTwFee")
    , @NamedQuery(name = "MgFeeRate.findByMgFeeCostTwFee", query = "SELECT m FROM MgFeeRate m WHERE m.mgFeeCostTwFee = :mgFeeCostTwFee")
    , @NamedQuery(name = "MgFeeRate.findByDateTime", query = "SELECT m FROM MgFeeRate m WHERE m.dateTime = :dateTime")})
public class MgFeeRate implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "RATE_DATE")
    private String rateDate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "RATE")
    private BigDecimal rate;

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id   
    @NotNull
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MFR_SEQ")
    @SequenceGenerator(name = "MFR_SEQ", sequenceName = "MG_FEE_RATE_SEQ", initialValue = 1, allocationSize = 1)
    private Long id;
    @Size(max = 10)
    @Column(name = "ACT_CODE")
    private String actCode;
    @Size(max = 5)
    @Column(name = "CT_SEQ")
    private String ctSeq;
    @Size(max = 11)
    @Column(name = "CUST_ID")
    private String custId;
    @Size(max = 8)
    @Column(name = "HLD_DATE")
    private String hldDate;
    @Size(max = 1)
    @Column(name = "SALE_CHNL")
    private String saleChnl;
    @Size(max = 1)
    @Column(name = "ACT_TYPE")
    private String actType;
    @Column(name = "AUM")
    private BigDecimal aum;
    @Size(max = 1)
    @Column(name = "CHARGE_OBJ")
    private String chargeObj;
    @Size(max = 1)
    @Column(name = "ACT_SET_TYPE")
    private String actSetType;
    @Column(name = "MG_ACT_D_SET_NO")
    private BigInteger mgActDSetNo;
    @Column(name = "ACT_FEE")
    private BigDecimal actFee;
    @Column(name = "AUM_TW_FEE")
    private BigDecimal aumTwFee;
    @Column(name = "MG_FEE_COST_TW_FEE")
    private BigDecimal mgFeeCostTwFee;
    @Column(name = "DATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime;

    public MgFeeRate() {
    }

    public MgFeeRate(Long id) {
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

    public String getCtSeq() {
        return ctSeq;
    }

    public void setCtSeq(String ctSeq) {
        this.ctSeq = ctSeq;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getHldDate() {
        return hldDate;
    }

    public void setHldDate(String hldDate) {
        this.hldDate = hldDate;
    }

    public String getSaleChnl() {
        return saleChnl;
    }

    public void setSaleChnl(String saleChnl) {
        this.saleChnl = saleChnl;
    }

    public String getActType() {
        return actType;
    }

    public void setActType(String actType) {
        this.actType = actType;
    }

    public BigDecimal getAum() {
        return aum;
    }

    public void setAum(BigDecimal aum) {
        this.aum = aum;
    }

    public String getChargeObj() {
        return chargeObj;
    }

    public void setChargeObj(String chargeObj) {
        this.chargeObj = chargeObj;
    }

    public String getActSetType() {
        return actSetType;
    }

    public void setActSetType(String actSetType) {
        this.actSetType = actSetType;
    }

    public BigInteger getMgActDSetNo() {
        return mgActDSetNo;
    }

    public void setMgActDSetNo(BigInteger mgActDSetNo) {
        this.mgActDSetNo = mgActDSetNo;
    }

    public BigDecimal getActFee() {
        return actFee;
    }

    public void setActFee(BigDecimal actFee) {
        this.actFee = actFee;
    }

    public BigDecimal getAumTwFee() {
        return aumTwFee;
    }

    public void setAumTwFee(BigDecimal aumTwFee) {
        this.aumTwFee = aumTwFee;
    }

    public BigDecimal getMgFeeCostTwFee() {
        return mgFeeCostTwFee;
    }

    public void setMgFeeCostTwFee(BigDecimal mgFeeCostTwFee) {
        this.mgFeeCostTwFee = mgFeeCostTwFee;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
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
        if (!(object instanceof MgFeeRate)) {
            return false;
        }
        MgFeeRate other = (MgFeeRate) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cub.entities.MgFeeRate[ id=" + id + " ]";
    }

    public String getRateDate() {
        return rateDate;
    }

    public void setRateDate(String rateDate) {
        this.rateDate = rateDate;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
    
}
