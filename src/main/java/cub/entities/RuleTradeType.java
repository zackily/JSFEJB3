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
@Table(name = "RULE_TRADE_TYPE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RuleTradeType.findAll", query = "SELECT r FROM RuleTradeType r")
    , @NamedQuery(name = "RuleTradeType.findByRuleNo", query = "SELECT r FROM RuleTradeType r WHERE r.ruleTradeTypePK.ruleNo = :ruleNo")
    , @NamedQuery(name = "RuleTradeType.findByTradeType", query = "SELECT r FROM RuleTradeType r WHERE r.ruleTradeTypePK.tradeType = :tradeType")
    , @NamedQuery(name = "RuleTradeType.findByLogUserId", query = "SELECT r FROM RuleTradeType r WHERE r.logUserId = :logUserId")
    , @NamedQuery(name = "RuleTradeType.findByLogDttm", query = "SELECT r FROM RuleTradeType r WHERE r.logDttm = :logDttm")})
public class RuleTradeType implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RuleTradeTypePK ruleTradeTypePK;
    @Size(max = 20)
    @Column(name = "LOG_USER_ID")
    private String logUserId;
    @Column(name = "LOG_DTTM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date logDttm;

    public RuleTradeType() {
    }

    public RuleTradeType(RuleTradeTypePK ruleTradeTypePK) {
        this.ruleTradeTypePK = ruleTradeTypePK;
    }

    public RuleTradeType(String ruleNo, short tradeType) {
        this.ruleTradeTypePK = new RuleTradeTypePK(ruleNo, tradeType);
    }

    public RuleTradeTypePK getRuleTradeTypePK() {
        return ruleTradeTypePK;
    }

    public void setRuleTradeTypePK(RuleTradeTypePK ruleTradeTypePK) {
        this.ruleTradeTypePK = ruleTradeTypePK;
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
        hash += (ruleTradeTypePK != null ? ruleTradeTypePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RuleTradeType)) {
            return false;
        }
        RuleTradeType other = (RuleTradeType) object;
        if ((this.ruleTradeTypePK == null && other.ruleTradeTypePK != null) || (this.ruleTradeTypePK != null && !this.ruleTradeTypePK.equals(other.ruleTradeTypePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cub.entities.RuleTradeType[ ruleTradeTypePK=" + ruleTradeTypePK + " ]";
    }
    
}
