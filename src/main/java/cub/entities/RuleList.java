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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author NT48810
 */
@Entity
@Table(name = "RULE_LIST")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RuleList.findAll", query = "SELECT r FROM RuleList r")
    , @NamedQuery(name = "RuleList.findByRuleNo", query = "SELECT r FROM RuleList r WHERE r.ruleNo = :ruleNo")
    , @NamedQuery(name = "RuleList.findByRuleClass", query = "SELECT r FROM RuleList r WHERE r.ruleClass = :ruleClass")
    , @NamedQuery(name = "RuleList.findByRuleChnName", query = "SELECT r FROM RuleList r WHERE r.ruleChnName = :ruleChnName")
    , @NamedQuery(name = "RuleList.findByRuleEngName", query = "SELECT r FROM RuleList r WHERE r.ruleEngName = :ruleEngName")
    , @NamedQuery(name = "RuleList.findByRtnMessage", query = "SELECT r FROM RuleList r WHERE r.rtnMessage = :rtnMessage")
    , @NamedQuery(name = "RuleList.findByCheckTiming", query = "SELECT r FROM RuleList r WHERE r.checkTiming = :checkTiming")
    , @NamedQuery(name = "RuleList.findByClientAggregate", query = "SELECT r FROM RuleList r WHERE r.clientAggregate = :clientAggregate")
    , @NamedQuery(name = "RuleList.findByCheckColumn", query = "SELECT r FROM RuleList r WHERE r.checkColumn = :checkColumn")
    , @NamedQuery(name = "RuleList.findByDividendAggregate", query = "SELECT r FROM RuleList r WHERE r.dividendAggregate = :dividendAggregate")
    , @NamedQuery(name = "RuleList.findByDivisorSource", query = "SELECT r FROM RuleList r WHERE r.divisorSource = :divisorSource")
    , @NamedQuery(name = "RuleList.findByDivisorValue", query = "SELECT r FROM RuleList r WHERE r.divisorValue = :divisorValue")
    , @NamedQuery(name = "RuleList.findByStartDate", query = "SELECT r FROM RuleList r WHERE r.startDate = :startDate")
    , @NamedQuery(name = "RuleList.findByEndDate", query = "SELECT r FROM RuleList r WHERE r.endDate = :endDate")
    , @NamedQuery(name = "RuleList.findByLimitCondition", query = "SELECT r FROM RuleList r WHERE r.limitCondition = :limitCondition")
    , @NamedQuery(name = "RuleList.findByLimitRate", query = "SELECT r FROM RuleList r WHERE r.limitRate = :limitRate")
    , @NamedQuery(name = "RuleList.findByLimitReaction", query = "SELECT r FROM RuleList r WHERE r.limitReaction = :limitReaction")
    , @NamedQuery(name = "RuleList.findByLogUserId", query = "SELECT r FROM RuleList r WHERE r.logUserId = :logUserId")
    , @NamedQuery(name = "RuleList.findByLogDttm", query = "SELECT r FROM RuleList r WHERE r.logDttm = :logDttm")
    , @NamedQuery(name = "RuleList.findByIsLock", query = "SELECT r FROM RuleList r WHERE r.isLock = :isLock")
    , @NamedQuery(name = "RuleList.findByLockUserId", query = "SELECT r FROM RuleList r WHERE r.lockUserId = :lockUserId")
    , @NamedQuery(name = "RuleList.findByLockDttm", query = "SELECT r FROM RuleList r WHERE r.lockDttm = :lockDttm")})
public class RuleList implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "RULE_NO")
    private String ruleNo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RULE_CLASS")
    private Short ruleClass;
    @Size(max = 100)
    @Column(name = "RULE_CHN_NAME")
    private String ruleChnName;
    @Size(max = 100)
    @Column(name = "RULE_ENG_NAME")
    private String ruleEngName;
    @Size(max = 100)
    @Column(name = "RTN_MESSAGE")
    private String rtnMessage;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CHECK_TIMING")
    private Short checkTiming;
    @Column(name = "CLIENT_AGGREGATE")
    private Short clientAggregate;
    @Column(name = "CHECK_COLUMN")
    private Short checkColumn;
    @Column(name = "DIVIDEND_AGGREGATE")
    private Short dividendAggregate;
    @Column(name = "DIVISOR_SOURCE")
    private Short divisorSource;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "DIVISOR_VALUE")
    private BigDecimal divisorValue;
    @Basic(optional = false)
    @NotNull
    @Column(name = "START_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "END_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LIMIT_CONDITION")
    private Short limitCondition;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LIMIT_RATE")
    private BigDecimal limitRate;
    @Column(name = "LIMIT_REACTION")
    private Short limitReaction;
    @Size(max = 20)
    @Column(name = "LOG_USER_ID")
    private String logUserId;
    @Column(name = "LOG_DTTM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date logDttm;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IS_LOCK")
    private Short isLock;
    @Transient
    private boolean lock;
    @Size(max = 20)
    @Column(name = "LOCK_USER_ID")
    private String lockUserId;
    @Column(name = "LOCK_DTTM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lockDttm;

    public RuleList() {
    }

    public RuleList(String ruleNo) {
        this.ruleNo = ruleNo;
    }

    public RuleList(String ruleNo, Short ruleClass, Short checkTiming, Date startDate, Date endDate, Short limitCondition, BigDecimal limitRate, Short isLock) {
        this.ruleNo = ruleNo;
        this.ruleClass = ruleClass;
        this.checkTiming = checkTiming;
        this.startDate = startDate;
        this.endDate = endDate;
        this.limitCondition = limitCondition;
        this.limitRate = limitRate;
        this.isLock = isLock;
    }

    public String getRuleNo() {
        return ruleNo;
    }

    public void setRuleNo(String ruleNo) {
        this.ruleNo = ruleNo;
    }

    public Short getRuleClass() {
        return ruleClass;
    }

    public void setRuleClass(Short ruleClass) {
        this.ruleClass = ruleClass;
    }

    public String getRuleChnName() {
        return ruleChnName;
    }

    public void setRuleChnName(String ruleChnName) {
        this.ruleChnName = ruleChnName;
    }

    public String getRuleEngName() {
        return ruleEngName;
    }

    public void setRuleEngName(String ruleEngName) {
        this.ruleEngName = ruleEngName;
    }

    public String getRtnMessage() {
        return rtnMessage;
    }

    public void setRtnMessage(String rtnMessage) {
        this.rtnMessage = rtnMessage;
    }

    public Short getCheckTiming() {
        return checkTiming;
    }

    public void setCheckTiming(Short checkTiming) {
        this.checkTiming = checkTiming;
    }

    public Short getClientAggregate() {
        return clientAggregate;
    }

    public void setClientAggregate(Short clientAggregate) {
        this.clientAggregate = clientAggregate;
    }

    public Short getCheckColumn() {
        return checkColumn;
    }

    public void setCheckColumn(Short checkColumn) {
        this.checkColumn = checkColumn;
    }

    public Short getDividendAggregate() {
        return dividendAggregate;
    }

    public void setDividendAggregate(Short dividendAggregate) {
        this.dividendAggregate = dividendAggregate;
    }

    public Short getDivisorSource() {
        return divisorSource;
    }

    public void setDivisorSource(Short divisorSource) {
        this.divisorSource = divisorSource;
    }

    public BigDecimal getDivisorValue() {
        return divisorValue;
    }

    public void setDivisorValue(BigDecimal divisorValue) {
        this.divisorValue = divisorValue;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Short getLimitCondition() {
        return limitCondition;
    }

    public void setLimitCondition(Short limitCondition) {
        this.limitCondition = limitCondition;
    }

    public BigDecimal getLimitRate() {
        return limitRate;
    }

    public void setLimitRate(BigDecimal limitRate) {
        this.limitRate = limitRate;
    }

    public Short getLimitReaction() {
        return limitReaction;
    }

    public void setLimitReaction(Short limitReaction) {
        this.limitReaction = limitReaction;
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

    public Short getIsLock() {
        return isLock;
    }

    public void setIsLock(Short isLock) {
        this.isLock = isLock;
    }

    public boolean isLock() {
        return lock;
    }

    public void setLock(boolean lock) {
        this.lock = lock;
    }

    public String getLockUserId() {
        return lockUserId;
    }

    public void setLockUserId(String lockUserId) {
        this.lockUserId = lockUserId;
    }

    public Date getLockDttm() {
        return lockDttm;
    }

    public void setLockDttm(Date lockDttm) {
        this.lockDttm = lockDttm;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ruleNo != null ? ruleNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RuleList)) {
            return false;
        }
        RuleList other = (RuleList) object;
        if ((this.ruleNo == null && other.ruleNo != null) || (this.ruleNo != null && !this.ruleNo.equals(other.ruleNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cub.entities.RuleList[ ruleNo=" + ruleNo + " ]";
    }
    
}
