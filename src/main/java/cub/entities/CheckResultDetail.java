/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cub.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author NT48810
 */
@Entity
@Table(name = "CHECK_RESULT_DETAIL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CheckResultDetail.findAll", query = "SELECT c FROM CheckResultDetail c")
    , @NamedQuery(name = "CheckResultDetail.findByOrderNo", query = "SELECT c FROM CheckResultDetail c WHERE c.checkResultDetailPK.orderNo = :orderNo")
    , @NamedQuery(name = "CheckResultDetail.findByRuleNo", query = "SELECT c FROM CheckResultDetail c WHERE c.checkResultDetailPK.ruleNo = :ruleNo")
    , @NamedQuery(name = "CheckResultDetail.findByResultCode", query = "SELECT c FROM CheckResultDetail c WHERE c.resultCode = :resultCode")
    , @NamedQuery(name = "CheckResultDetail.findByRatio", query = "SELECT c FROM CheckResultDetail c WHERE c.ratio = :ratio")
    , @NamedQuery(name = "CheckResultDetail.findByDividend", query = "SELECT c FROM CheckResultDetail c WHERE c.dividend = :dividend")
    , @NamedQuery(name = "CheckResultDetail.findByDivisor", query = "SELECT c FROM CheckResultDetail c WHERE c.divisor = :divisor")
    , @NamedQuery(name = "CheckResultDetail.findByReturnValue1", query = "SELECT c FROM CheckResultDetail c WHERE c.returnValue1 = :returnValue1")
    , @NamedQuery(name = "CheckResultDetail.findByReturnValue2", query = "SELECT c FROM CheckResultDetail c WHERE c.returnValue2 = :returnValue2")
    , @NamedQuery(name = "CheckResultDetail.findByLogUserId", query = "SELECT c FROM CheckResultDetail c WHERE c.logUserId = :logUserId")
    , @NamedQuery(name = "CheckResultDetail.findByLogDttm", query = "SELECT c FROM CheckResultDetail c WHERE c.logDttm = :logDttm")})
public class CheckResultDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CheckResultDetailPK checkResultDetailPK;
    @Column(name = "RESULT_CODE")
    private Short resultCode;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "RATIO")
    private BigDecimal ratio;
    @Column(name = "DIVIDEND")
    private BigDecimal dividend;
    @Column(name = "DIVISOR")
    private BigDecimal divisor;
    @Size(max = 100)
    @Column(name = "RETURN_VALUE1")
    private String returnValue1;
    @Size(max = 100)
    @Column(name = "RETURN_VALUE2")
    private String returnValue2;
    @Size(max = 20)
    @Column(name = "LOG_USER_ID")
    private String logUserId;
    @Column(name = "LOG_DTTM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date logDttm;

    public CheckResultDetail() {
    }

    public CheckResultDetail(CheckResultDetailPK checkResultDetailPK) {
        this.checkResultDetailPK = checkResultDetailPK;
    }

    public CheckResultDetail(String orderNo, String ruleNo) {
        this.checkResultDetailPK = new CheckResultDetailPK(orderNo, ruleNo);
    }

    public CheckResultDetailPK getCheckResultDetailPK() {
        return checkResultDetailPK;
    }

    public void setCheckResultDetailPK(CheckResultDetailPK checkResultDetailPK) {
        this.checkResultDetailPK = checkResultDetailPK;
    }

    public Short getResultCode() {
        return resultCode;
    }

    public void setResultCode(Short resultCode) {
        this.resultCode = resultCode;
    }

    public BigDecimal getRatio() {
        return ratio;
    }

    public void setRatio(BigDecimal ratio) {
        this.ratio = ratio;
    }

    public BigDecimal getDividend() {
        return dividend;
    }

    public void setDividend(BigDecimal dividend) {
        this.dividend = dividend;
    }

    public BigDecimal getDivisor() {
        return divisor;
    }

    public void setDivisor(BigDecimal divisor) {
        this.divisor = divisor;
    }

    public String getReturnValue1() {
        return returnValue1;
    }

    public void setReturnValue1(String returnValue1) {
        this.returnValue1 = returnValue1;
    }

    public String getReturnValue2() {
        return returnValue2;
    }

    public void setReturnValue2(String returnValue2) {
        this.returnValue2 = returnValue2;
    }

    public String getLogUserId() {
        return logUserId;
    }

    public void setLogUserId(String logUserId) {
        this.logUserId = logUserId;
    }

    public Date getLogDttm() {
        return logDttm;
    }

    public void setLogDttm(Date logDttm) {
        this.logDttm = logDttm;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (checkResultDetailPK != null ? checkResultDetailPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CheckResultDetail)) {
            return false;
        }
        CheckResultDetail other = (CheckResultDetail) object;
        if ((this.checkResultDetailPK == null && other.checkResultDetailPK != null) || (this.checkResultDetailPK != null && !this.checkResultDetailPK.equals(other.checkResultDetailPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cub.entities.CheckResultDetail[ checkResultDetailPK=" + checkResultDetailPK + " ]";
    }
    
}
