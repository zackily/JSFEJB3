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
@Table(name = "RULE_CHANNEL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RuleChannel.findAll", query = "SELECT r FROM RuleChannel r")
    , @NamedQuery(name = "RuleChannel.findByRuleNo", query = "SELECT r FROM RuleChannel r WHERE r.ruleChannelPK.ruleNo = :ruleNo")
    , @NamedQuery(name = "RuleChannel.findByChannel", query = "SELECT r FROM RuleChannel r WHERE r.ruleChannelPK.channelCode = :channelCode")
    , @NamedQuery(name = "RuleChannel.findByLogUserId", query = "SELECT r FROM RuleChannel r WHERE r.logUserId = :logUserId")
    , @NamedQuery(name = "RuleChannel.findByLogDttm", query = "SELECT r FROM RuleChannel r WHERE r.logDttm = :logDttm")})
public class RuleChannel implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RuleChannelPK ruleChannelPK;
    @Size(max = 20)
    @Column(name = "LOG_USER_ID")
    private String logUserId;
    @Column(name = "LOG_DTTM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date logDttm;

    public RuleChannel() {
    }

    public RuleChannel(RuleChannelPK ruleChannelPK) {
        this.ruleChannelPK = ruleChannelPK;
    }

    public RuleChannel(String ruleNo, short channel) {
        this.ruleChannelPK = new RuleChannelPK(ruleNo, channel);
    }

    public RuleChannelPK getRuleChannelPK() {
        return ruleChannelPK;
    }

    public void setRuleChannelPK(RuleChannelPK ruleChannelPK) {
        this.ruleChannelPK = ruleChannelPK;
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
        hash += (ruleChannelPK != null ? ruleChannelPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RuleChannel)) {
            return false;
        }
        RuleChannel other = (RuleChannel) object;
        if ((this.ruleChannelPK == null && other.ruleChannelPK != null) || (this.ruleChannelPK != null && !this.ruleChannelPK.equals(other.ruleChannelPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cub.entities.RuleChannel[ ruleChannelPK=" + ruleChannelPK + " ]";
    }
    
}
