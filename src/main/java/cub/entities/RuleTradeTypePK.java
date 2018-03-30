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
public class RuleTradeTypePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "RULE_NO")
    private String ruleNo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TRADE_TYPE")
    private short tradeType;

    public RuleTradeTypePK() {
    }

    public RuleTradeTypePK(String ruleNo, short tradeType) {
        this.ruleNo = ruleNo;
        this.tradeType = tradeType;
    }

    public String getRuleNo() {
        return ruleNo;
    }

    public void setRuleNo(String ruleNo) {
        this.ruleNo = ruleNo;
    }

    public short getTradeType() {
        return tradeType;
    }

    public void setTradeType(short tradeType) {
        this.tradeType = tradeType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ruleNo != null ? ruleNo.hashCode() : 0);
        hash += (int) tradeType;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RuleTradeTypePK)) {
            return false;
        }
        RuleTradeTypePK other = (RuleTradeTypePK) object;
        if ((this.ruleNo == null && other.ruleNo != null) || (this.ruleNo != null && !this.ruleNo.equals(other.ruleNo))) {
            return false;
        }
        if (this.tradeType != other.tradeType) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cub.entities.RuleTradeTypePK[ ruleNo=" + ruleNo + ", tradeType=" + tradeType + " ]";
    }
    
}
