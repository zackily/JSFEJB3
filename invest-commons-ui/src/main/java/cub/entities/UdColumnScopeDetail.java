/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cub.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    , @NamedQuery(name = "UdColumnScopeDetail.findByUdColumnCode", query = "SELECT u FROM UdColumnScopeDetail u WHERE u.udColumnCode = :udColumnCode")
    , @NamedQuery(name = "UdColumnScopeDetail.findBySeqNo", query = "SELECT u FROM UdColumnScopeDetail u WHERE u.seqNo = :seqNo")
    , @NamedQuery(name = "UdColumnScopeDetail.findByDataCode", query = "SELECT u FROM UdColumnScopeDetail u WHERE u.dataCode = :dataCode")})
public class UdColumnScopeDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "UD_COLUMN_CODE")
    private String udColumnCode;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SEQ_NO")
    private short seqNo;
    @Column(name = "DATA_CODE")
    private Short dataCode;

    public UdColumnScopeDetail() {
    }

    public UdColumnScopeDetail(String udColumnCode) {
        this.udColumnCode = udColumnCode;
    }

    public UdColumnScopeDetail(String udColumnCode, short seqNo) {
        this.udColumnCode = udColumnCode;
        this.seqNo = seqNo;
    }

    public String getUdColumnCode() {
        return udColumnCode;
    }

    public void setUdColumnCode(String udColumnCode) {
        this.udColumnCode = udColumnCode;
    }

    public short getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(short seqNo) {
        this.seqNo = seqNo;
    }

    public Short getDataCode() {
        return dataCode;
    }

    public void setDataCode(Short dataCode) {
        this.dataCode = dataCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (udColumnCode != null ? udColumnCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UdColumnScopeDetail)) {
            return false;
        }
        UdColumnScopeDetail other = (UdColumnScopeDetail) object;
        if ((this.udColumnCode == null && other.udColumnCode != null) || (this.udColumnCode != null && !this.udColumnCode.equals(other.udColumnCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cub.entities.UdColumnScopeDetail[ udColumnCode=" + udColumnCode + " ]";
    }
    
}
