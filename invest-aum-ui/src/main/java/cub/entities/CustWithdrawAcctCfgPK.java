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
public class CustWithdrawAcctCfgPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "CUST_ID")
    private String custId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "COLUMN2")
    private String column2;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "COLUMN3")
    private String column3;

    public CustWithdrawAcctCfgPK() {
    }

    public CustWithdrawAcctCfgPK(String custId, String column2, String column3) {
        this.custId = custId;
        this.column2 = column2;
        this.column3 = column3;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getColumn2() {
        return column2;
    }

    public void setColumn2(String column2) {
        this.column2 = column2;
    }

    public String getColumn3() {
        return column3;
    }

    public void setColumn3(String column3) {
        this.column3 = column3;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (custId != null ? custId.hashCode() : 0);
        hash += (column2 != null ? column2.hashCode() : 0);
        hash += (column3 != null ? column3.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CustWithdrawAcctCfgPK)) {
            return false;
        }
        CustWithdrawAcctCfgPK other = (CustWithdrawAcctCfgPK) object;
        if ((this.custId == null && other.custId != null) || (this.custId != null && !this.custId.equals(other.custId))) {
            return false;
        }
        if ((this.column2 == null && other.column2 != null) || (this.column2 != null && !this.column2.equals(other.column2))) {
            return false;
        }
        if ((this.column3 == null && other.column3 != null) || (this.column3 != null && !this.column3.equals(other.column3))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cub.entities.CustWithdrawAcctCfgPK[ custId=" + custId + ", column2=" + column2 + ", column3=" + column3 + " ]";
    }
    
}
