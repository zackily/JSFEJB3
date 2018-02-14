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
@Table(name = "RULE_DIVIDEND")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RuleDividend.findAll", query = "SELECT r FROM RuleDividend r")
    , @NamedQuery(name = "RuleDividend.findByRuleNo", query = "SELECT r FROM RuleDividend r WHERE r.ruleDividendPK.ruleNo = :ruleNo")
    , @NamedQuery(name = "RuleDividend.findBySeqNo", query = "SELECT r FROM RuleDividend r WHERE r.ruleDividendPK.seqNo = :seqNo")
    , @NamedQuery(name = "RuleDividend.findByOpCode", query = "SELECT r FROM RuleDividend r WHERE r.opCode = :opCode")
    , @NamedQuery(name = "RuleDividend.findByScopeCode", query = "SELECT r FROM RuleDividend r WHERE r.scopeCode = :scopeCode")
    , @NamedQuery(name = "RuleDividend.findByLogUserId", query = "SELECT r FROM RuleDividend r WHERE r.logUserId = :logUserId")
    , @NamedQuery(name = "RuleDividend.findByLogDttm", query = "SELECT r FROM RuleDividend r WHERE r.logDttm = :logDttm")})
public class RuleDividend implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RuleDividendPK ruleDividendPK;
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

    public RuleDividend() {
    }

    public RuleDividend(RuleDividendPK ruleDividendPK) {
        this.ruleDividendPK = ruleDividendPK;
    }

    public RuleDividend(String ruleNo, short seqNo) {
        this.ruleDividendPK = new RuleDividendPK(ruleNo, seqNo);
    }

    public RuleDividendPK getRuleDividendPK() {
        return ruleDividendPK;
    }

    public void setRuleDividendPK(RuleDividendPK ruleDividendPK) {
        this.ruleDividendPK = ruleDividendPK;
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
        hash += (ruleDividendPK != null ? ruleDividendPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RuleDividend)) {
            return false;
        }
        RuleDividend other = (RuleDividend) object;
        if ((this.ruleDividendPK == null && other.ruleDividendPK != null) || (this.ruleDividendPK != null && !this.ruleDividendPK.equals(other.ruleDividendPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cub.entities.RuleDividend[ ruleDividendPK=" + ruleDividendPK + " ]";
    }
    
}
