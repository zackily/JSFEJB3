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
@Table(name = "MG_FEE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MgFee.findAll", query = "SELECT m FROM MgFee m")
    , @NamedQuery(name = "MgFee.findById", query = "SELECT m FROM MgFee m WHERE m.id = :id")
    , @NamedQuery(name = "MgFee.findByMgFeeTstAcc", query = "SELECT m FROM MgFee m WHERE m.mgFeeTstAcc = :mgFeeTstAcc")
    , @NamedQuery(name = "MgFee.findByMgFeeCustId", query = "SELECT m FROM MgFee m WHERE m.mgFeeCustId = :mgFeeCustId")
    , @NamedQuery(name = "MgFee.findByMgFeeDate", query = "SELECT m FROM MgFee m WHERE m.mgFeeDate = :mgFeeDate")
    , @NamedQuery(name = "MgFee.findByMgFeeFundNo", query = "SELECT m FROM MgFee m WHERE m.mgFeeFundNo = :mgFeeFundNo")
    , @NamedQuery(name = "MgFee.findByMgPortfolioNo", query = "SELECT m FROM MgFee m WHERE m.mgPortfolioNo = :mgPortfolioNo")
    , @NamedQuery(name = "MgFee.findByMgFeeActCode", query = "SELECT m FROM MgFee m WHERE m.mgFeeActCode = :mgFeeActCode")
    , @NamedQuery(name = "MgFee.findByMgFeeAumTwFee", query = "SELECT m FROM MgFee m WHERE m.mgFeeAumTwFee = :mgFeeAumTwFee")
    , @NamedQuery(name = "MgFee.findByMgFeeCostTwFee", query = "SELECT m FROM MgFee m WHERE m.mgFeeCostTwFee = :mgFeeCostTwFee")
    , @NamedQuery(name = "MgFee.findByMgFeeAumFee", query = "SELECT m FROM MgFee m WHERE m.mgFeeAumFee = :mgFeeAumFee")
    , @NamedQuery(name = "MgFee.findByMgFeeCostFee", query = "SELECT m FROM MgFee m WHERE m.mgFeeCostFee = :mgFeeCostFee")
    , @NamedQuery(name = "MgFee.findByMgFeeCur", query = "SELECT m FROM MgFee m WHERE m.mgFeeCur = :mgFeeCur")
    , @NamedQuery(name = "MgFee.findByMgFeeExrate", query = "SELECT m FROM MgFee m WHERE m.mgFeeExrate = :mgFeeExrate")
    , @NamedQuery(name = "MgFee.findByMgFeeCanvasser", query = "SELECT m FROM MgFee m WHERE m.mgFeeCanvasser = :mgFeeCanvasser")
    , @NamedQuery(name = "MgFee.findByMgFeeBranchId", query = "SELECT m FROM MgFee m WHERE m.mgFeeBranchId = :mgFeeBranchId")
    , @NamedQuery(name = "MgFee.findByMgFeeDateTime", query = "SELECT m FROM MgFee m WHERE m.mgFeeDateTime = :mgFeeDateTime")})
public class MgFee implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id   
    @NotNull
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MF_SEQ")
    @SequenceGenerator(name = "MF_SEQ", sequenceName = "MG_FEE_SEQ", initialValue = 1, allocationSize = 1)    
    private Long id;
    @Size(max = 12)
    @Column(name = "MG_FEE_TST_ACC")
    private String mgFeeTstAcc;
    @Size(max = 11)
    @Column(name = "MG_FEE_CUST_ID")
    private String mgFeeCustId;
    @Size(max = 8)
    @Column(name = "MG_FEE_DATE")
    private String mgFeeDate;
    @Size(max = 8)
    @Column(name = "MG_FEE_FUND_NO")
    private String mgFeeFundNo;
    @Size(max = 20)
    @Column(name = "MG_PORTFOLIO_NO")
    private String mgPortfolioNo;
    @Size(max = 10)
    @Column(name = "MG_FEE_ACT_CODE")
    private String mgFeeActCode;
    @Column(name = "MG_FEE_AUM_TW_FEE")
    private BigDecimal mgFeeAumTwFee;
    @Column(name = "MG_FEE_COST_TW_FEE")
    private BigDecimal mgFeeCostTwFee;
    @Column(name = "MG_FEE_AUM_FEE")
    private BigDecimal mgFeeAumFee;
    @Column(name = "MG_FEE_COST_FEE")
    private BigDecimal mgFeeCostFee;
    @Size(max = 3)
    @Column(name = "MG_FEE_CUR")
    private String mgFeeCur;
    @Column(name = "MG_FEE_EXRATE")
    private BigDecimal mgFeeExrate;
    @Size(max = 5)
    @Column(name = "MG_FEE_CANVASSER")
    private String mgFeeCanvasser;
    @Size(max = 3)
    @Column(name = "MG_FEE_BRANCH_ID")
    private String mgFeeBranchId;
    @Column(name = "MG_FEE_DATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date mgFeeDateTime;

    public MgFee() {
    }

    public MgFee(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMgFeeTstAcc() {
        return mgFeeTstAcc;
    }

    public void setMgFeeTstAcc(String mgFeeTstAcc) {
        this.mgFeeTstAcc = mgFeeTstAcc;
    }

    public String getMgFeeCustId() {
        return mgFeeCustId;
    }

    public void setMgFeeCustId(String mgFeeCustId) {
        this.mgFeeCustId = mgFeeCustId;
    }

    public String getMgFeeDate() {
        return mgFeeDate;
    }

    public void setMgFeeDate(String mgFeeDate) {
        this.mgFeeDate = mgFeeDate;
    }

    public String getMgFeeFundNo() {
        return mgFeeFundNo;
    }

    public void setMgFeeFundNo(String mgFeeFundNo) {
        this.mgFeeFundNo = mgFeeFundNo;
    }

    public String getMgPortfolioNo() {
        return mgPortfolioNo;
    }

    public void setMgPortfolioNo(String mgPortfolioNo) {
        this.mgPortfolioNo = mgPortfolioNo;
    }

    public String getMgFeeActCode() {
        return mgFeeActCode;
    }

    public void setMgFeeActCode(String mgFeeActCode) {
        this.mgFeeActCode = mgFeeActCode;
    }

    public BigDecimal getMgFeeAumTwFee() {
        return mgFeeAumTwFee;
    }

    public void setMgFeeAumTwFee(BigDecimal mgFeeAumTwFee) {
        this.mgFeeAumTwFee = mgFeeAumTwFee;
    }

    public BigDecimal getMgFeeCostTwFee() {
        return mgFeeCostTwFee;
    }

    public void setMgFeeCostTwFee(BigDecimal mgFeeCostTwFee) {
        this.mgFeeCostTwFee = mgFeeCostTwFee;
    }

    public BigDecimal getMgFeeAumFee() {
        return mgFeeAumFee;
    }

    public void setMgFeeAumFee(BigDecimal mgFeeAumFee) {
        this.mgFeeAumFee = mgFeeAumFee;
    }

    public BigDecimal getMgFeeCostFee() {
        return mgFeeCostFee;
    }

    public void setMgFeeCostFee(BigDecimal mgFeeCostFee) {
        this.mgFeeCostFee = mgFeeCostFee;
    }

    public String getMgFeeCur() {
        return mgFeeCur;
    }

    public void setMgFeeCur(String mgFeeCur) {
        this.mgFeeCur = mgFeeCur;
    }

    public BigDecimal getMgFeeExrate() {
        return mgFeeExrate;
    }

    public void setMgFeeExrate(BigDecimal mgFeeExrate) {
        this.mgFeeExrate = mgFeeExrate;
    }

    public String getMgFeeCanvasser() {
        return mgFeeCanvasser;
    }

    public void setMgFeeCanvasser(String mgFeeCanvasser) {
        this.mgFeeCanvasser = mgFeeCanvasser;
    }

    public String getMgFeeBranchId() {
        return mgFeeBranchId;
    }

    public void setMgFeeBranchId(String mgFeeBranchId) {
        this.mgFeeBranchId = mgFeeBranchId;
    }

    public Date getMgFeeDateTime() {
        return mgFeeDateTime;
    }

    public void setMgFeeDateTime(Date mgFeeDateTime) {
        this.mgFeeDateTime = mgFeeDateTime;
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
        if (!(object instanceof MgFee)) {
            return false;
        }
        MgFee other = (MgFee) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cub.entities.MgFee[ id=" + id + " ]";
    }
    
}
