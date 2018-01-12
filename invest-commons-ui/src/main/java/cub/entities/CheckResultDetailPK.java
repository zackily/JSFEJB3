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
public class CheckResultDetailPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "ORDER_NO")
    private String orderNo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "RULE_NO")
    private String ruleNo;

    public CheckResultDetailPK() {
    }

    public CheckResultDetailPK(String orderNo, String ruleNo) {
        this.orderNo = orderNo;
        this.ruleNo = ruleNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getRuleNo() {
        return ruleNo;
    }

    public void setRuleNo(String ruleNo) {
        this.ruleNo = ruleNo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderNo != null ? orderNo.hashCode() : 0);
        hash += (ruleNo != null ? ruleNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CheckResultDetailPK)) {
            return false;
        }
        CheckResultDetailPK other = (CheckResultDetailPK) object;
        if ((this.orderNo == null && other.orderNo != null) || (this.orderNo != null && !this.orderNo.equals(other.orderNo))) {
            return false;
        }
        if ((this.ruleNo == null && other.ruleNo != null) || (this.ruleNo != null && !this.ruleNo.equals(other.ruleNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cub.entities.CheckResultDetailPK[ orderNo=" + orderNo + ", ruleNo=" + ruleNo + " ]";
    }
    
}
