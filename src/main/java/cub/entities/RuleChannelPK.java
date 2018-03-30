/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cub.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author NT48810
 */
@Embeddable
public class RuleChannelPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "RULE_NO")
    private String ruleNo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CHANNEL_CODE")
    private short channelCode;

    public RuleChannelPK() {
    }

    public RuleChannelPK(String ruleNo, short channelCode) {
        this.ruleNo = ruleNo;
        this.channelCode = channelCode;
    }

    public String getRuleNo() {
        return ruleNo;
    }

    public void setRuleNo(String ruleNo) {
        this.ruleNo = ruleNo;
    }

    public short getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(short channelCode) {
        this.channelCode = channelCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ruleNo != null ? ruleNo.hashCode() : 0);
        hash += (int) channelCode;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RuleChannelPK)) {
            return false;
        }
        RuleChannelPK other = (RuleChannelPK) object;
        if ((this.ruleNo == null && other.ruleNo != null) || (this.ruleNo != null && !this.ruleNo.equals(other.ruleNo))) {
            return false;
        }
        if (this.channelCode != other.channelCode) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cub.entities.RuleChannelPK[ ruleNo=" + ruleNo + ", channelCode=" + channelCode + " ]";
    }
    
}
