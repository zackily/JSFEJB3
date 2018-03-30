/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cub.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author NT48810
 */
@Entity
@Table(name = "UD_COLUMN_SCOPE_DETAIL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UdColumnScopeDetail.findAll", query = "SELECT u FROM UdColumnScopeDetail u")
    , @NamedQuery(name = "UdColumnScopeDetail.findByUdColumnCode", query = "SELECT u FROM UdColumnScopeDetail u WHERE u.udColumnScopeDetailPK.udColumnCode = :udColumnCode")
    , @NamedQuery(name = "UdColumnScopeDetail.findByDataCode", query = "SELECT u FROM UdColumnScopeDetail u WHERE u.udColumnScopeDetailPK.dataCode = :dataCode")})
public class UdColumnScopeDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UdColumnScopeDetailPK udColumnScopeDetailPK;

    public UdColumnScopeDetail() {
    }

    public UdColumnScopeDetail(UdColumnScopeDetailPK udColumnScopeDetailPK) {
        this.udColumnScopeDetailPK = udColumnScopeDetailPK;
    }

    public UdColumnScopeDetail(String udColumnCode, String dataCode) {
        this.udColumnScopeDetailPK = new UdColumnScopeDetailPK(udColumnCode, dataCode);
    }

    public UdColumnScopeDetailPK getUdColumnScopeDetailPK() {
        return udColumnScopeDetailPK;
    }

    public void setUdColumnScopeDetailPK(UdColumnScopeDetailPK udColumnScopeDetailPK) {
        this.udColumnScopeDetailPK = udColumnScopeDetailPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (udColumnScopeDetailPK != null ? udColumnScopeDetailPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UdColumnScopeDetail)) {
            return false;
        }
        UdColumnScopeDetail other = (UdColumnScopeDetail) object;
        if ((this.udColumnScopeDetailPK == null && other.udColumnScopeDetailPK != null) || (this.udColumnScopeDetailPK != null && !this.udColumnScopeDetailPK.equals(other.udColumnScopeDetailPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cub.entities.UdColumnScopeDetail[ udColumnScopeDetailPK=" + udColumnScopeDetailPK + " ]";
    }
    
}
