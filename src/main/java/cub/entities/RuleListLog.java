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
 * @author F123669
 */
@Entity
@Table(name = "RULE_LIST_LOG")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RuleListLog.findAll", query = "SELECT r FROM RuleListLog r")
    , @NamedQuery(name = "RuleListLog.findByRuleNo", query = "SELECT r FROM RuleListLog r WHERE r.ruleNo = :ruleNo")
    , @NamedQuery(name = "RuleListLog.findByRuleClass", query = "SELECT r FROM RuleListLog r WHERE r.ruleClass = :ruleClass")
    , @NamedQuery(name = "RuleListLog.findByRuleChnName", query = "SELECT r FROM RuleListLog r WHERE r.ruleChnName = :ruleChnName")
    , @NamedQuery(name = "RuleListLog.findByRuleEngName", query = "SELECT r FROM RuleListLog r WHERE r.ruleEngName = :ruleEngName")
    , @NamedQuery(name = "RuleListLog.findByRtnMessage", query = "SELECT r FROM RuleListLog r WHERE r.rtnMessage = :rtnMessage")
    , @NamedQuery(name = "RuleListLog.findByCheckTiming", query = "SELECT r FROM RuleListLog r WHERE r.checkTiming = :checkTiming")
    , @NamedQuery(name = "RuleListLog.findByClientAggregate", query = "SELECT r FROM RuleListLog r WHERE r.clientAggregate = :clientAggregate")
    , @NamedQuery(name = "RuleListLog.findByCheckColumn", query = "SELECT r FROM RuleListLog r WHERE r.checkColumn = :checkColumn")
    , @NamedQuery(name = "RuleListLog.findByDividendAggregate", query = "SELECT r FROM RuleListLog r WHERE r.dividendAggregate = :dividendAggregate")
    , @NamedQuery(name = "RuleListLog.findByDivisorSource", query = "SELECT r FROM RuleListLog r WHERE r.divisorSource = :divisorSource")
    , @NamedQuery(name = "RuleListLog.findByDivisorValue", query = "SELECT r FROM RuleListLog r WHERE r.divisorValue = :divisorValue")
    , @NamedQuery(name = "RuleListLog.findByStartDate", query = "SELECT r FROM RuleListLog r WHERE r.startDate = :startDate")
    , @NamedQuery(name = "RuleListLog.findByEndDate", query = "SELECT r FROM RuleListLog r WHERE r.endDate = :endDate")
    , @NamedQuery(name = "RuleListLog.findByLimitCondition", query = "SELECT r FROM RuleListLog r WHERE r.limitCondition = :limitCondition")
    , @NamedQuery(name = "RuleListLog.findByLimitRate", query = "SELECT r FROM RuleListLog r WHERE r.limitRate = :limitRate")
    , @NamedQuery(name = "RuleListLog.findByLimitReaction", query = "SELECT r FROM RuleListLog r WHERE r.limitReaction = :limitReaction")
    , @NamedQuery(name = "RuleListLog.findByIsLock", query = "SELECT r FROM RuleListLog r WHERE r.isLock = :isLock")
    , @NamedQuery(name = "RuleListLog.findByLockUserId", query = "SELECT r FROM RuleListLog r WHERE r.lockUserId = :lockUserId")
    , @NamedQuery(name = "RuleListLog.findByLockDttm", query = "SELECT r FROM RuleListLog r WHERE r.lockDttm = :lockDttm")
    , @NamedQuery(name = "RuleListLog.findByLogUserId", query = "SELECT r FROM RuleListLog r WHERE r.logUserId = :logUserId")
    , @NamedQuery(name = "RuleListLog.findByLogDttm", query = "SELECT r FROM RuleListLog r WHERE r.logDttm = :logDttm")
    , @NamedQuery(name = "RuleListLog.findByLogType", query = "SELECT r FROM RuleListLog r WHERE r.logType = :logType")
    , @NamedQuery(name = "RuleListLog.findBySeqNo", query = "SELECT r FROM RuleListLog r WHERE r.seqNo = :seqNo")})
public class RuleListLog implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 10)
    @Column(name = "RULE_NO")
    private String ruleNo;
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
    @Column(name = "START_DATE")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(name = "END_DATE")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @Column(name = "LIMIT_CONDITION")
    private Short limitCondition;
    @Column(name = "LIMIT_RATE")
    private BigDecimal limitRate;
    @Column(name = "LIMIT_REACTION")
    private Short limitReaction;
    @Column(name = "IS_LOCK")
    private Short isLock;
    @Size(max = 20)
    @Column(name = "LOCK_USER_ID")
    private String lockUserId;
    @Column(name = "LOCK_DTTM")
    @Temporal(TemporalType.DATE)
    private Date lockDttm;
    @Size(max = 20)
    @Column(name = "LOG_USER_ID")
    private String logUserId;
    @Column(name = "LOG_DTTM")
    @Temporal(TemporalType.DATE)
    private Date logDttm;
    @Column(name = "LOG_TYPE")
    private Short logType;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "SEQ_NO")
    private String seqNo;

    public RuleListLog() {
    }

    public RuleListLog(String seqNo) {
        this.seqNo = seqNo;
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

    public Short getIsLock() {
        return isLock;
    }

    public void setIsLock(Short isLock) {
        this.isLock = isLock;
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

    public Short getLogType() {
        return logType;
    }

    public void setLogType(Short logType) {
        this.logType = logType;
    }

    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (seqNo != null ? seqNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RuleListLog)) {
            return false;
        }
        RuleListLog other = (RuleListLog) object;
        if ((this.seqNo == null && other.seqNo != null) || (this.seqNo != null && !this.seqNo.equals(other.seqNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cub.entities.RuleListLog[ seqNo=" + seqNo + " ]";
    }
    
}
