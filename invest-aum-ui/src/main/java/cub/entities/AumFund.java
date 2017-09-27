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
@Table(name = "AUM_FUND")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AumFund.findAll", query = "SELECT a FROM AumFund a")
    , @NamedQuery(name = "AumFund.findById", query = "SELECT a FROM AumFund a WHERE a.id = :id")
    , @NamedQuery(name = "AumFund.findByFHldTstAcc", query = "SELECT a FROM AumFund a WHERE a.fHldTstAcc = :fHldTstAcc")
    , @NamedQuery(name = "AumFund.findByFHldCustNo", query = "SELECT a FROM AumFund a WHERE a.fHldCustNo = :fHldCustNo")
    , @NamedQuery(name = "AumFund.findByFHldDate", query = "SELECT a FROM AumFund a WHERE a.fHldDate = :fHldDate")
    , @NamedQuery(name = "AumFund.findByFHldFundNo", query = "SELECT a FROM AumFund a WHERE a.fHldFundNo = :fHldFundNo")
    , @NamedQuery(name = "AumFund.findByFHldNav", query = "SELECT a FROM AumFund a WHERE a.fHldNav = :fHldNav")
    , @NamedQuery(name = "AumFund.findByFHldNavDate", query = "SELECT a FROM AumFund a WHERE a.fHldNavDate = :fHldNavDate")
    , @NamedQuery(name = "AumFund.findByFHldUnit", query = "SELECT a FROM AumFund a WHERE a.fHldUnit = :fHldUnit")
    , @NamedQuery(name = "AumFund.findByFHldTwValue", query = "SELECT a FROM AumFund a WHERE a.fHldTwValue = :fHldTwValue")
    , @NamedQuery(name = "AumFund.findByFHldValue", query = "SELECT a FROM AumFund a WHERE a.fHldValue = :fHldValue")
    , @NamedQuery(name = "AumFund.findByFHldCost", query = "SELECT a FROM AumFund a WHERE a.fHldCost = :fHldCost")
    , @NamedQuery(name = "AumFund.findByFHldPortfolioNo", query = "SELECT a FROM AumFund a WHERE a.fHldPortfolioNo = :fHldPortfolioNo")
    , @NamedQuery(name = "AumFund.findByFHldCur", query = "SELECT a FROM AumFund a WHERE a.fHldCur = :fHldCur")
    , @NamedQuery(name = "AumFund.findByFHldExrate", query = "SELECT a FROM AumFund a WHERE a.fHldExrate = :fHldExrate")
    , @NamedQuery(name = "AumFund.findByFHldExrateDate", query = "SELECT a FROM AumFund a WHERE a.fHldExrateDate = :fHldExrateDate")
    , @NamedQuery(name = "AumFund.findByFHldCanvasser", query = "SELECT a FROM AumFund a WHERE a.fHldCanvasser = :fHldCanvasser")
    , @NamedQuery(name = "AumFund.findByFHldBranchId", query = "SELECT a FROM AumFund a WHERE a.fHldBranchId = :fHldBranchId")
    , @NamedQuery(name = "AumFund.findByFHldChannel", query = "SELECT a FROM AumFund a WHERE a.fHldChannel = :fHldChannel")
    , @NamedQuery(name = "AumFund.findByFHldDateTime", query = "SELECT a FROM AumFund a WHERE a.fHldDateTime = :fHldDateTime")})
public class AumFund implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id    
    @NotNull
    @Column(name = "ID")    
    @SequenceGenerator(name = "AF_SEQ", sequenceName = "AUM_FUND_SEQ", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AF_SEQ")
    private Long id;
    @Size(max = 12)
    @Column(name = "F_HLD_TST_ACC")
    private String fHldTstAcc;
    @Size(max = 11)
    @Column(name = "F_HLD_CUST_NO")
    private String fHldCustNo;
    @Size(max = 8)
    @Column(name = "F_HLD_DATE")
    private String fHldDate;
    @Size(max = 8)
    @Column(name = "F_HLD_FUND_NO")
    private String fHldFundNo;
    @Column(name = "F_HLD_NAV")
    private BigDecimal fHldNav;
    @Size(max = 8)
    @Column(name = "F_HLD_NAV_DATE")
    private String fHldNavDate;
    @Column(name = "F_HLD_UNIT")
    private BigDecimal fHldUnit;
    @Column(name = "F_HLD_TW_VALUE")
    private BigDecimal fHldTwValue;
    @Column(name = "F_HLD_VALUE")
    private BigDecimal fHldValue;
    @Column(name = "F_HLD_COST")
    private BigDecimal fHldCost;
    @Size(max = 20)
    @Column(name = "F_HLD_PORTFOLIO_NO")
    private String fHldPortfolioNo;
    @Size(max = 3)
    @Column(name = "F_HLD_CUR")
    private String fHldCur;
    @Column(name = "F_HLD_EXRATE")
    private BigDecimal fHldExrate;
    @Size(max = 8)
    @Column(name = "F_HLD_EXRATE_DATE")
    private String fHldExrateDate;
    @Size(max = 5)
    @Column(name = "F_HLD_CANVASSER")
    private String fHldCanvasser;
    @Size(max = 3)
    @Column(name = "F_HLD_BRANCH_ID")
    private String fHldBranchId;
    @Size(max = 1)
    @Column(name = "F_HLD_CHANNEL")
    private String fHldChannel;
    @Column(name = "F_HLD_DATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fHldDateTime;

    public AumFund() {
    }

    public AumFund(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFHldTstAcc() {
        return fHldTstAcc;
    }

    public void setFHldTstAcc(String fHldTstAcc) {
        this.fHldTstAcc = fHldTstAcc;
    }

    public String getFHldCustNo() {
        return fHldCustNo;
    }

    public void setFHldCustNo(String fHldCustNo) {
        this.fHldCustNo = fHldCustNo;
    }

    public String getFHldDate() {
        return fHldDate;
    }

    public void setFHldDate(String fHldDate) {
        this.fHldDate = fHldDate;
    }

    public String getFHldFundNo() {
        return fHldFundNo;
    }

    public void setFHldFundNo(String fHldFundNo) {
        this.fHldFundNo = fHldFundNo;
    }

    public BigDecimal getFHldNav() {
        return fHldNav;
    }

    public void setFHldNav(BigDecimal fHldNav) {
        this.fHldNav = fHldNav;
    }

    public String getFHldNavDate() {
        return fHldNavDate;
    }

    public void setFHldNavDate(String fHldNavDate) {
        this.fHldNavDate = fHldNavDate;
    }

    public BigDecimal getFHldUnit() {
        return fHldUnit;
    }

    public void setFHldUnit(BigDecimal fHldUnit) {
        this.fHldUnit = fHldUnit;
    }

    public BigDecimal getFHldTwValue() {
        return fHldTwValue;
    }

    public void setFHldTwValue(BigDecimal fHldTwValue) {
        this.fHldTwValue = fHldTwValue;
    }

    public BigDecimal getFHldValue() {
        return fHldValue;
    }

    public void setFHldValue(BigDecimal fHldValue) {
        this.fHldValue = fHldValue;
    }

    public BigDecimal getFHldCost() {
        return fHldCost;
    }

    public void setFHldCost(BigDecimal fHldCost) {
        this.fHldCost = fHldCost;
    }

    public String getFHldPortfolioNo() {
        return fHldPortfolioNo;
    }

    public void setFHldPortfolioNo(String fHldPortfolioNo) {
        this.fHldPortfolioNo = fHldPortfolioNo;
    }

    public String getFHldCur() {
        return fHldCur;
    }

    public void setFHldCur(String fHldCur) {
        this.fHldCur = fHldCur;
    }

    public BigDecimal getFHldExrate() {
        return fHldExrate;
    }

    public void setFHldExrate(BigDecimal fHldExrate) {
        this.fHldExrate = fHldExrate;
    }

    public String getFHldExrateDate() {
        return fHldExrateDate;
    }

    public void setFHldExrateDate(String fHldExrateDate) {
        this.fHldExrateDate = fHldExrateDate;
    }

    public String getFHldCanvasser() {
        return fHldCanvasser;
    }

    public void setFHldCanvasser(String fHldCanvasser) {
        this.fHldCanvasser = fHldCanvasser;
    }

    public String getFHldBranchId() {
        return fHldBranchId;
    }

    public void setFHldBranchId(String fHldBranchId) {
        this.fHldBranchId = fHldBranchId;
    }

    public String getFHldChannel() {
        return fHldChannel;
    }

    public void setFHldChannel(String fHldChannel) {
        this.fHldChannel = fHldChannel;
    }

    public Date getFHldDateTime() {
        return fHldDateTime;
    }

    public void setFHldDateTime(Date fHldDateTime) {
        this.fHldDateTime = fHldDateTime;
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
        if (!(object instanceof AumFund)) {
            return false;
        }
        AumFund other = (AumFund) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cub.entities.AumFund[ id=" + id + " ]";
    }

}
