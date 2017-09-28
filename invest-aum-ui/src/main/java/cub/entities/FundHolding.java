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
@Table(name = "FUND_HOLDING")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FundHolding.findAll", query = "SELECT f FROM FundHolding f")
    , @NamedQuery(name = "FundHolding.findByTrustacct", query = "SELECT f FROM FundHolding f WHERE f.trustacct = :trustacct")
    , @NamedQuery(name = "FundHolding.findByBaseDate", query = "SELECT f FROM FundHolding f WHERE f.baseDate = :baseDate")
    , @NamedQuery(name = "FundHolding.findByCustId", query = "SELECT f FROM FundHolding f WHERE f.custId = :custId")
    , @NamedQuery(name = "FundHolding.findByFundId", query = "SELECT f FROM FundHolding f WHERE f.fundId = :fundId")
    , @NamedQuery(name = "FundHolding.findByFundNav", query = "SELECT f FROM FundHolding f WHERE f.fundNav = :fundNav")
    , @NamedQuery(name = "FundHolding.findByNavDate", query = "SELECT f FROM FundHolding f WHERE f.navDate = :navDate")
    , @NamedQuery(name = "FundHolding.findByUnit", query = "SELECT f FROM FundHolding f WHERE f.unit = :unit")
    , @NamedQuery(name = "FundHolding.findByTwValue", query = "SELECT f FROM FundHolding f WHERE f.twValue = :twValue")
    , @NamedQuery(name = "FundHolding.findByValue", query = "SELECT f FROM FundHolding f WHERE f.value = :value")
    , @NamedQuery(name = "FundHolding.findByCost", query = "SELECT f FROM FundHolding f WHERE f.cost = :cost")
    , @NamedQuery(name = "FundHolding.findByPortfolioNo", query = "SELECT f FROM FundHolding f WHERE f.portfolioNo = :portfolioNo")
    , @NamedQuery(name = "FundHolding.findByCur", query = "SELECT f FROM FundHolding f WHERE f.cur = :cur")
    , @NamedQuery(name = "FundHolding.findByExrate", query = "SELECT f FROM FundHolding f WHERE f.exrate = :exrate")
    , @NamedQuery(name = "FundHolding.findByExrateDate", query = "SELECT f FROM FundHolding f WHERE f.exrateDate = :exrateDate")
    , @NamedQuery(name = "FundHolding.findByBranchId", query = "SELECT f FROM FundHolding f WHERE f.branchId = :branchId")
    , @NamedQuery(name = "FundHolding.findByChannel", query = "SELECT f FROM FundHolding f WHERE f.channel = :channel")
    , @NamedQuery(name = "FundHolding.findByUpdateDttm", query = "SELECT f FROM FundHolding f WHERE f.updateDttm = :updateDttm")
    , @NamedQuery(name = "FundHolding.findById", query = "SELECT f FROM FundHolding f WHERE f.id = :id")})
public class FundHolding implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "TRUSTACCT")
    private String trustacct;
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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "FUND_ID")
    private String fundId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "FUND_NAV")
    private BigDecimal fundNav;
    @Size(max = 8)
    @Column(name = "NAV_DATE")
    private String navDate;
    @Column(name = "UNIT")
    private BigDecimal unit;
    @Column(name = "TW_VALUE")
    private BigDecimal twValue;
    @Column(name = "VALUE")
    private BigDecimal value;
    @Column(name = "COST")
    private BigDecimal cost;
    @Size(max = 20)
    @Column(name = "PORTFOLIO_NO")
    private String portfolioNo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "CUR")
    private String cur;
    @Column(name = "EXRATE")
    private BigDecimal exrate;
    @Size(max = 8)
    @Column(name = "EXRATE_DATE")
    private String exrateDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "BRANCH_ID")
    private String branchId;
    @Size(max = 1)
    @Column(name = "CHANNEL")
    private String channel;
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

    public FundHolding() {
    }

    public FundHolding(BigDecimal id) {
        this.id = id;
    }

    public FundHolding(BigDecimal id, String trustacct, String baseDate, String custId, String fundId, String cur, String branchId, Date updateDttm) {
        this.id = id;
        this.trustacct = trustacct;
        this.baseDate = baseDate;
        this.custId = custId;
        this.fundId = fundId;
        this.cur = cur;
        this.branchId = branchId;
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

    public String getFundId() {
        return fundId;
    }

    public void setFundId(String fundId) {
        this.fundId = fundId;
    }

    public BigDecimal getFundNav() {
        return fundNav;
    }

    public void setFundNav(BigDecimal fundNav) {
        this.fundNav = fundNav;
    }

    public String getNavDate() {
        return navDate;
    }

    public void setNavDate(String navDate) {
        this.navDate = navDate;
    }

    public BigDecimal getUnit() {
        return unit;
    }

    public void setUnit(BigDecimal unit) {
        this.unit = unit;
    }

    public BigDecimal getTwValue() {
        return twValue;
    }

    public void setTwValue(BigDecimal twValue) {
        this.twValue = twValue;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getPortfolioNo() {
        return portfolioNo;
    }

    public void setPortfolioNo(String portfolioNo) {
        this.portfolioNo = portfolioNo;
    }

    public String getCur() {
        return cur;
    }

    public void setCur(String cur) {
        this.cur = cur;
    }

    public BigDecimal getExrate() {
        return exrate;
    }

    public void setExrate(BigDecimal exrate) {
        this.exrate = exrate;
    }

    public String getExrateDate() {
        return exrateDate;
    }

    public void setExrateDate(String exrateDate) {
        this.exrateDate = exrateDate;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
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
        if (!(object instanceof FundHolding)) {
            return false;
        }
        FundHolding other = (FundHolding) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cub.entities.FundHolding[ id=" + id + " ]";
    }
    
}
