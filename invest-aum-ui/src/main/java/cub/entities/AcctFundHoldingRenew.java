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
@Table(name = "ACCT_FUND_HOLDING_RENEW")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AcctFundHoldingRenew.findAll", query = "SELECT a FROM AcctFundHoldingRenew a")
    , @NamedQuery(name = "AcctFundHoldingRenew.findById", query = "SELECT a FROM AcctFundHoldingRenew a WHERE a.id = :id")
    , @NamedQuery(name = "AcctFundHoldingRenew.findByCustId", query = "SELECT a FROM AcctFundHoldingRenew a WHERE a.custId = :custId")
    , @NamedQuery(name = "AcctFundHoldingRenew.findByBaseDt", query = "SELECT a FROM AcctFundHoldingRenew a WHERE a.baseDt = :baseDt")
    , @NamedQuery(name = "AcctFundHoldingRenew.findByFundId", query = "SELECT a FROM AcctFundHoldingRenew a WHERE a.fundId = :fundId")
    , @NamedQuery(name = "AcctFundHoldingRenew.findByNav", query = "SELECT a FROM AcctFundHoldingRenew a WHERE a.nav = :nav")
    , @NamedQuery(name = "AcctFundHoldingRenew.findByNavDt", query = "SELECT a FROM AcctFundHoldingRenew a WHERE a.navDt = :navDt")
    , @NamedQuery(name = "AcctFundHoldingRenew.findByUnit", query = "SELECT a FROM AcctFundHoldingRenew a WHERE a.unit = :unit")
    , @NamedQuery(name = "AcctFundHoldingRenew.findByTwdValue", query = "SELECT a FROM AcctFundHoldingRenew a WHERE a.twdValue = :twdValue")
    , @NamedQuery(name = "AcctFundHoldingRenew.findByUsdValue", query = "SELECT a FROM AcctFundHoldingRenew a WHERE a.usdValue = :usdValue")
    , @NamedQuery(name = "AcctFundHoldingRenew.findByValue", query = "SELECT a FROM AcctFundHoldingRenew a WHERE a.value = :value")
    , @NamedQuery(name = "AcctFundHoldingRenew.findByCost", query = "SELECT a FROM AcctFundHoldingRenew a WHERE a.cost = :cost")
    , @NamedQuery(name = "AcctFundHoldingRenew.findByPortfolioNo", query = "SELECT a FROM AcctFundHoldingRenew a WHERE a.portfolioNo = :portfolioNo")
    , @NamedQuery(name = "AcctFundHoldingRenew.findByCurrency", query = "SELECT a FROM AcctFundHoldingRenew a WHERE a.currency = :currency")
    , @NamedQuery(name = "AcctFundHoldingRenew.findByTwdExrate", query = "SELECT a FROM AcctFundHoldingRenew a WHERE a.twdExrate = :twdExrate")
    , @NamedQuery(name = "AcctFundHoldingRenew.findByUsdExrate", query = "SELECT a FROM AcctFundHoldingRenew a WHERE a.usdExrate = :usdExrate")
    , @NamedQuery(name = "AcctFundHoldingRenew.findByExrateDt", query = "SELECT a FROM AcctFundHoldingRenew a WHERE a.exrateDt = :exrateDt")
    , @NamedQuery(name = "AcctFundHoldingRenew.findByCanvasser", query = "SELECT a FROM AcctFundHoldingRenew a WHERE a.canvasser = :canvasser")
    , @NamedQuery(name = "AcctFundHoldingRenew.findByBranchId", query = "SELECT a FROM AcctFundHoldingRenew a WHERE a.branchId = :branchId")
    , @NamedQuery(name = "AcctFundHoldingRenew.findByChannel", query = "SELECT a FROM AcctFundHoldingRenew a WHERE a.channel = :channel")
    , @NamedQuery(name = "AcctFundHoldingRenew.findByTflag", query = "SELECT a FROM AcctFundHoldingRenew a WHERE a.tflag = :tflag")
    , @NamedQuery(name = "AcctFundHoldingRenew.findByUpdateDttm", query = "SELECT a FROM AcctFundHoldingRenew a WHERE a.updateDttm = :updateDttm")
    , @NamedQuery(name = "AcctFundHoldingRenew.findByTstAcct", query = "SELECT a FROM AcctFundHoldingRenew a WHERE a.tstAcct = :tstAcct")})
public class AcctFundHoldingRenew implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @Size(max = 20)
    @Column(name = "CUST_ID")
    private String custId;
    @Size(max = 20)
    @Column(name = "BASE_DT")
    private String baseDt;
    @Size(max = 20)
    @Column(name = "FUND_ID")
    private String fundId;
    @Column(name = "NAV")
    private BigInteger nav;
    @Size(max = 20)
    @Column(name = "NAV_DT")
    private String navDt;
    @Column(name = "UNIT")
    private BigInteger unit;
    @Column(name = "TWD_VALUE")
    private BigInteger twdValue;
    @Column(name = "USD_VALUE")
    private BigInteger usdValue;
    @Column(name = "VALUE")
    private BigInteger value;
    @Column(name = "COST")
    private BigInteger cost;
    @Size(max = 20)
    @Column(name = "PORTFOLIO_NO")
    private String portfolioNo;
    @Size(max = 20)
    @Column(name = "CURRENCY")
    private String currency;
    @Column(name = "TWD_EXRATE")
    private BigInteger twdExrate;
    @Column(name = "USD_EXRATE")
    private BigInteger usdExrate;
    @Size(max = 20)
    @Column(name = "EXRATE_DT")
    private String exrateDt;
    @Size(max = 20)
    @Column(name = "CANVASSER")
    private String canvasser;
    @Size(max = 20)
    @Column(name = "BRANCH_ID")
    private String branchId;
    @Size(max = 20)
    @Column(name = "CHANNEL")
    private String channel;
    @Size(max = 20)
    @Column(name = "TFLAG")
    private String tflag;
    @Column(name = "UPDATE_DTTM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDttm;
    @Size(max = 20)
    @Column(name = "TST_ACCT")
    private String tstAcct;

    public AcctFundHoldingRenew() {
    }

    public AcctFundHoldingRenew(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getBaseDt() {
        return baseDt;
    }

    public void setBaseDt(String baseDt) {
        this.baseDt = baseDt;
    }

    public String getFundId() {
        return fundId;
    }

    public void setFundId(String fundId) {
        this.fundId = fundId;
    }

    public BigInteger getNav() {
        return nav;
    }

    public void setNav(BigInteger nav) {
        this.nav = nav;
    }

    public String getNavDt() {
        return navDt;
    }

    public void setNavDt(String navDt) {
        this.navDt = navDt;
    }

    public BigInteger getUnit() {
        return unit;
    }

    public void setUnit(BigInteger unit) {
        this.unit = unit;
    }

    public BigInteger getTwdValue() {
        return twdValue;
    }

    public void setTwdValue(BigInteger twdValue) {
        this.twdValue = twdValue;
    }

    public BigInteger getUsdValue() {
        return usdValue;
    }

    public void setUsdValue(BigInteger usdValue) {
        this.usdValue = usdValue;
    }

    public BigInteger getValue() {
        return value;
    }

    public void setValue(BigInteger value) {
        this.value = value;
    }

    public BigInteger getCost() {
        return cost;
    }

    public void setCost(BigInteger cost) {
        this.cost = cost;
    }

    public String getPortfolioNo() {
        return portfolioNo;
    }

    public void setPortfolioNo(String portfolioNo) {
        this.portfolioNo = portfolioNo;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigInteger getTwdExrate() {
        return twdExrate;
    }

    public void setTwdExrate(BigInteger twdExrate) {
        this.twdExrate = twdExrate;
    }

    public BigInteger getUsdExrate() {
        return usdExrate;
    }

    public void setUsdExrate(BigInteger usdExrate) {
        this.usdExrate = usdExrate;
    }

    public String getExrateDt() {
        return exrateDt;
    }

    public void setExrateDt(String exrateDt) {
        this.exrateDt = exrateDt;
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

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getTflag() {
        return tflag;
    }

    public void setTflag(String tflag) {
        this.tflag = tflag;
    }

    public Date getUpdateDttm() {
        return updateDttm;
    }

    public void setUpdateDttm(Date updateDttm) {
        this.updateDttm = updateDttm;
    }

    public String getTstAcct() {
        return tstAcct;
    }

    public void setTstAcct(String tstAcct) {
        this.tstAcct = tstAcct;
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
        if (!(object instanceof AcctFundHoldingRenew)) {
            return false;
        }
        AcctFundHoldingRenew other = (AcctFundHoldingRenew) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cub.entities.AcctFundHoldingRenew[ id=" + id + " ]";
    }
    
}
