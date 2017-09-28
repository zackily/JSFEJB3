/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cub.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
 * @author NT48810
 */
@Entity
@Table(name = "CUST_WITHDRAW_ACCT_CFG")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CustWithdrawAcctCfg.findAll", query = "SELECT c FROM CustWithdrawAcctCfg c")
    , @NamedQuery(name = "CustWithdrawAcctCfg.findByCustId", query = "SELECT c FROM CustWithdrawAcctCfg c WHERE c.custWithdrawAcctCfgPK.custId = :custId")
    , @NamedQuery(name = "CustWithdrawAcctCfg.findByColumn2", query = "SELECT c FROM CustWithdrawAcctCfg c WHERE c.custWithdrawAcctCfgPK.column2 = :column2")
    , @NamedQuery(name = "CustWithdrawAcctCfg.findByColumn3", query = "SELECT c FROM CustWithdrawAcctCfg c WHERE c.custWithdrawAcctCfgPK.column3 = :column3")
    , @NamedQuery(name = "CustWithdrawAcctCfg.findByColumn4", query = "SELECT c FROM CustWithdrawAcctCfg c WHERE c.column4 = :column4")
    , @NamedQuery(name = "CustWithdrawAcctCfg.findByColumn5", query = "SELECT c FROM CustWithdrawAcctCfg c WHERE c.column5 = :column5")})
public class CustWithdrawAcctCfg implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CustWithdrawAcctCfgPK custWithdrawAcctCfgPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "COLUMN4")
    private String column4;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COLUMN5")
    @Temporal(TemporalType.TIMESTAMP)
    private Date column5;

    public CustWithdrawAcctCfg() {
    }

    public CustWithdrawAcctCfg(CustWithdrawAcctCfgPK custWithdrawAcctCfgPK) {
        this.custWithdrawAcctCfgPK = custWithdrawAcctCfgPK;
    }

    public CustWithdrawAcctCfg(CustWithdrawAcctCfgPK custWithdrawAcctCfgPK, String column4, Date column5) {
        this.custWithdrawAcctCfgPK = custWithdrawAcctCfgPK;
        this.column4 = column4;
        this.column5 = column5;
    }

    public CustWithdrawAcctCfg(String custId, String column2, String column3) {
        this.custWithdrawAcctCfgPK = new CustWithdrawAcctCfgPK(custId, column2, column3);
    }

    public CustWithdrawAcctCfgPK getCustWithdrawAcctCfgPK() {
        return custWithdrawAcctCfgPK;
    }

    public void setCustWithdrawAcctCfgPK(CustWithdrawAcctCfgPK custWithdrawAcctCfgPK) {
        this.custWithdrawAcctCfgPK = custWithdrawAcctCfgPK;
    }

    public String getColumn4() {
        return column4;
    }

    public void setColumn4(String column4) {
        this.column4 = column4;
    }

    public Date getColumn5() {
        return column5;
    }

    public void setColumn5(Date column5) {
        this.column5 = column5;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (custWithdrawAcctCfgPK != null ? custWithdrawAcctCfgPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CustWithdrawAcctCfg)) {
            return false;
        }
        CustWithdrawAcctCfg other = (CustWithdrawAcctCfg) object;
        if ((this.custWithdrawAcctCfgPK == null && other.custWithdrawAcctCfgPK != null) || (this.custWithdrawAcctCfgPK != null && !this.custWithdrawAcctCfgPK.equals(other.custWithdrawAcctCfgPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cub.entities.CustWithdrawAcctCfg[ custWithdrawAcctCfgPK=" + custWithdrawAcctCfgPK + " ]";
    }
    
}
