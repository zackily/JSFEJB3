/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cub.entities;

import java.io.Serializable;
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
@Table(name = "RULE_DIVISOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RuleDivisor.findAll", query = "SELECT r FROM RuleDivisor r")
    , @NamedQuery(name = "RuleDivisor.findByRuleNo", query = "SELECT r FROM RuleDivisor r WHERE r.ruleDivisorPK.ruleNo = :ruleNo")
    , @NamedQuery(name = "RuleDivisor.findBySeqNo", query = "SELECT r FROM RuleDivisor r WHERE r.ruleDivisorPK.seqNo = :seqNo")
    , @NamedQuery(name = "RuleDivisor.findByOpCode", query = "SELECT r FROM RuleDivisor r WHERE r.opCode = :opCode")
    , @NamedQuery(name = "RuleDivisor.findByScopeCode", query = "SELECT r FROM RuleDivisor r WHERE r.scopeCode = :scopeCode")
    , @NamedQuery(name = "RuleDivisor.findByLogUserId", query = "SELECT r FROM RuleDivisor r WHERE r.logUserId = :logUserId")
    , @NamedQuery(name = "RuleDivisor.findByLogDttm", query = "SELECT r FROM RuleDivisor r WHERE r.logDttm = :logDttm")})
public class RuleDivisor implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RuleDivisorPK ruleDivisorPK;
    @Size(max = 5)
    @Column(name = "OP_CODE")
    private String opCode;
    @Size(max = 10)
    @Column(name = "SCOPE_CODE")
    private String scopeCode;
    @Size(max = 20)
    @Column(name = "LOG_USER_ID")
    private String logUserId;
    @Column(name = "LOG_DTTM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date logDttm;

    public RuleDivisor() {
    }

    public RuleDivisor(RuleDivisorPK ruleDivisorPK) {
        this.ruleDivisorPK = ruleDivisorPK;
    }

    public RuleDivisor(String ruleNo, short seqNo) {
        this.ruleDivisorPK = new RuleDivisorPK(ruleNo, seqNo);
    }

    public RuleDivisorPK getRuleDivisorPK() {
        return ruleDivisorPK;
    }

    public void setRuleDivisorPK(RuleDivisorPK ruleDivisorPK) {
        this.ruleDivisorPK = ruleDivisorPK;
    }

    public String getOpCode() {
        return opCode;
    }

    public void setOpCode(String opCode) {
        this.opCode = opCode;
    }

    public String getScopeCode() {
        return scopeCode;
    }

    public void setScopeCode(String scopeCode) {
        this.scopeCode = scopeCode;
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
        hash += (ruleDivisorPK != null ? ruleDivisorPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RuleDivisor)) {
            return false;
        }
        RuleDivisor other = (RuleDivisor) object;
        if ((this.ruleDivisorPK == null && other.ruleDivisorPK != null) || (this.ruleDivisorPK != null && !this.ruleDivisorPK.equals(other.ruleDivisorPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cub.entities.RuleDivisor[ ruleDivisorPK=" + ruleDivisorPK + " ]";
    }
    
}
