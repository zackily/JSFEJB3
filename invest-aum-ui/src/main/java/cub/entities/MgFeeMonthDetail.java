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
@Table(name = "MG_FEE_MONTH_DETAIL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MgFeeMonthDetail.findAll", query = "SELECT m FROM MgFeeMonthDetail m")
    , @NamedQuery(name = "MgFeeMonthDetail.findByTrustacct", query = "SELECT m FROM MgFeeMonthDetail m WHERE m.trustacct = :trustacct")
    , @NamedQuery(name = "MgFeeMonthDetail.findByBaseDate", query = "SELECT m FROM MgFeeMonthDetail m WHERE m.baseDate = :baseDate")
    , @NamedQuery(name = "MgFeeMonthDetail.findByCustId", query = "SELECT m FROM MgFeeMonthDetail m WHERE m.custId = :custId")
    , @NamedQuery(name = "MgFeeMonthDetail.findByPortfolioNo", query = "SELECT m FROM MgFeeMonthDetail m WHERE m.portfolioNo = :portfolioNo")
    , @NamedQuery(name = "MgFeeMonthDetail.findByActCode", query = "SELECT m FROM MgFeeMonthDetail m WHERE m.actCode = :actCode")
    , @NamedQuery(name = "MgFeeMonthDetail.findByActSubCode", query = "SELECT m FROM MgFeeMonthDetail m WHERE m.actSubCode = :actSubCode")
    , @NamedQuery(name = "MgFeeMonthDetail.findByFundId", query = "SELECT m FROM MgFeeMonthDetail m WHERE m.fundId = :fundId")
    , @NamedQuery(name = "MgFeeMonthDetail.findByAumFee", query = "SELECT m FROM MgFeeMonthDetail m WHERE m.aumFee = :aumFee")
    , @NamedQuery(name = "MgFeeMonthDetail.findByAumRemainFee", query = "SELECT m FROM MgFeeMonthDetail m WHERE m.aumRemainFee = :aumRemainFee")
    , @NamedQuery(name = "MgFeeMonthDetail.findByCostFee", query = "SELECT m FROM MgFeeMonthDetail m WHERE m.costFee = :costFee")
    , @NamedQuery(name = "MgFeeMonthDetail.findByCostRemainFee", query = "SELECT m FROM MgFeeMonthDetail m WHERE m.costRemainFee = :costRemainFee")
    , @NamedQuery(name = "MgFeeMonthDetail.findByCur", query = "SELECT m FROM MgFeeMonthDetail m WHERE m.cur = :cur")
    , @NamedQuery(name = "MgFeeMonthDetail.findByUpdateDttm", query = "SELECT m FROM MgFeeMonthDetail m WHERE m.updateDttm = :updateDttm")
    , @NamedQuery(name = "MgFeeMonthDetail.findById", query = "SELECT m FROM MgFeeMonthDetail m WHERE m.id = :id")})
public class MgFeeMonthDetail implements Serializable {

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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "ACT_SUB_CODE")
    private String actSubCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "FUND_ID")
    private String fundId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "AUM_FEE")
    private BigDecimal aumFee;
    @Basic(optional = false)
    @NotNull
    @Column(name = "AUM_REMAIN_FEE")
    private BigDecimal aumRemainFee;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COST_FEE")
    private BigDecimal costFee;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COST_REMAIN_FEE")
    private BigDecimal costRemainFee;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "CUR")
    private String cur;
    @Basic(optional = false)
    @NotNull
    @Column(name = "UPDATE_DTTM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDttm;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;

    public MgFeeMonthDetail() {
    }

    public MgFeeMonthDetail(BigDecimal id) {
        this.id = id;
    }

    public MgFeeMonthDetail(BigDecimal id, String trustacct, String baseDate, String custId, String actCode, String actSubCode, String fundId, BigDecimal aumFee, BigDecimal aumRemainFee, BigDecimal costFee, BigDecimal costRemainFee, String cur, Date updateDttm) {
        this.id = id;
        this.trustacct = trustacct;
        this.baseDate = baseDate;
        this.custId = custId;
        this.actCode = actCode;
        this.actSubCode = actSubCode;
        this.fundId = fundId;
        this.aumFee = aumFee;
        this.aumRemainFee = aumRemainFee;
        this.costFee = costFee;
        this.costRemainFee = costRemainFee;
        this.cur = cur;
        this.updateDttm = updateDttm;
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

    public String getActSubCode() {
        return actSubCode;
    }

    public void setActSubCode(String actSubCode) {
        this.actSubCode = actSubCode;
    }

    public String getFundId() {
        return fundId;
    }

    public void setFundId(String fundId) {
        this.fundId = fundId;
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

    public String getCur() {
        return cur;
    }

    public void setCur(String cur) {
        this.cur = cur;
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
        if (!(object instanceof MgFeeMonthDetail)) {
            return false;
        }
        MgFeeMonthDetail other = (MgFeeMonthDetail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cub.entities.MgFeeMonthDetail[ id=" + id + " ]";
    }
    
}
