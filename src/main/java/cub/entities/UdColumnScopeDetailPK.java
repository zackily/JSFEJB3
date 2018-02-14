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
public class UdColumnScopeDetailPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "UD_COLUMN_CODE")
    private String udColumnCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "DATA_CODE")
    private String dataCode;

    public UdColumnScopeDetailPK() {
    }

    public UdColumnScopeDetailPK(String udColumnCode, String dataCode) {
        this.udColumnCode = udColumnCode;
        this.dataCode = dataCode;
    }

    public String getUdColumnCode() {
        return udColumnCode;
    }

    public void setUdColumnCode(String udColumnCode) {
        this.udColumnCode = udColumnCode;
    }

    public String getDataCode() {
        return dataCode;
    }

    public void setDataCode(String dataCode) {
        this.dataCode = dataCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (udColumnCode != null ? udColumnCode.hashCode() : 0);
        hash += (dataCode != null ? dataCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UdColumnScopeDetailPK)) {
            return false;
        }
        UdColumnScopeDetailPK other = (UdColumnScopeDetailPK) object;
        if ((this.udColumnCode == null && other.udColumnCode != null) || (this.udColumnCode != null && !this.udColumnCode.equals(other.udColumnCode))) {
            return false;
        }
        if ((this.dataCode == null && other.dataCode != null) || (this.dataCode != null && !this.dataCode.equals(other.dataCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cub.entities.UdColumnScopeDetailPK[ udColumnCode=" + udColumnCode + ", dataCode=" + dataCode + " ]";
    }
    
}
