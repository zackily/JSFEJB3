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
@Table(name = "RULE_PRODUCT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RuleProduct.findAll", query = "SELECT r FROM RuleProduct r")
    , @NamedQuery(name = "RuleProduct.findByRuleNo", query = "SELECT r FROM RuleProduct r WHERE r.ruleProductPK.ruleNo = :ruleNo")
    , @NamedQuery(name = "RuleProduct.findByPrdCode", query = "SELECT r FROM RuleProduct r WHERE r.ruleProductPK.prdCode = :prdCode")
    , @NamedQuery(name = "RuleProduct.findByLogUserId", query = "SELECT r FROM RuleProduct r WHERE r.logUserId = :logUserId")
    , @NamedQuery(name = "RuleProduct.findByLogDttm", query = "SELECT r FROM RuleProduct r WHERE r.logDttm = :logDttm")})
public class RuleProduct implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RuleProductPK ruleProductPK;
    @Size(max = 20)
    @Column(name = "LOG_USER_ID")
    private String logUserId;
    @Column(name = "LOG_DTTM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date logDttm;

    public RuleProduct() {
    }

    public RuleProduct(RuleProductPK ruleProductPK) {
        this.ruleProductPK = ruleProductPK;
    }

    public RuleProduct(String ruleNo, short prdCode) {
        this.ruleProductPK = new RuleProductPK(ruleNo, prdCode);
    }

    public RuleProductPK getRuleProductPK() {
        return ruleProductPK;
    }

    public void setRuleProductPK(RuleProductPK ruleProductPK) {
        this.ruleProductPK = ruleProductPK;
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
        hash += (ruleProductPK != null ? ruleProductPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RuleProduct)) {
            return false;
        }
        RuleProduct other = (RuleProduct) object;
        if ((this.ruleProductPK == null && other.ruleProductPK != null) || (this.ruleProductPK != null && !this.ruleProductPK.equals(other.ruleProductPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cub.entities.RuleProduct[ ruleProductPK=" + ruleProductPK + " ]";
    }
    
}
