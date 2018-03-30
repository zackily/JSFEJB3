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
public class DataScopeDetailPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "SCOPE_CODE")
    private String scopeCode;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SEQ_NO")
    private short seqNo;

    public DataScopeDetailPK() {
    }

    public DataScopeDetailPK(String scopeCode, short seqNo) {
        this.scopeCode = scopeCode;
        this.seqNo = seqNo;
    }

    public String getScopeCode() {
        return scopeCode;
    }

    public void setScopeCode(String scopeCode) {
        this.scopeCode = scopeCode;
    }

    public short getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(short seqNo) {
        this.seqNo = seqNo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (scopeCode != null ? scopeCode.hashCode() : 0);
        hash += (int) seqNo;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DataScopeDetailPK)) {
            return false;
        }
        DataScopeDetailPK other = (DataScopeDetailPK) object;
        if ((this.scopeCode == null && other.scopeCode != null) || (this.scopeCode != null && !this.scopeCode.equals(other.scopeCode))) {
            return false;
        }
        if (this.seqNo != other.seqNo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cub.entities.DataScopeDetailPK[ scopeCode=" + scopeCode + ", seqNo=" + seqNo + " ]";
    }
    
}
