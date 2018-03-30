/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cub.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author F123669
 */
@Entity
@Table(name = "UD_METHOD_DETAIL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UdMethodDetail.findAll", query = "SELECT u FROM UdMethodDetail u")
    , @NamedQuery(name = "UdMethodDetail.findByMethodName", query = "SELECT u FROM UdMethodDetail u WHERE u.udMethodDetailPK.methodName = :methodName")
    , @NamedQuery(name = "UdMethodDetail.findBySeqNo", query = "SELECT u FROM UdMethodDetail u WHERE u.udMethodDetailPK.seqNo = :seqNo")
    , @NamedQuery(name = "UdMethodDetail.findByParameterName", query = "SELECT u FROM UdMethodDetail u WHERE u.parameterName = :parameterName")
    , @NamedQuery(name = "UdMethodDetail.findByParameterDesc", query = "SELECT u FROM UdMethodDetail u WHERE u.parameterDesc = :parameterDesc")})
public class UdMethodDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UdMethodDetailPK udMethodDetailPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "PARAMETER_NAME")
    private String parameterName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "PARAMETER_DESC")
    private String parameterDesc;

    public UdMethodDetail() {
    }

    public UdMethodDetail(UdMethodDetailPK udMethodDetailPK) {
        this.udMethodDetailPK = udMethodDetailPK;
    }

    public UdMethodDetail(UdMethodDetailPK udMethodDetailPK, String parameterName, String parameterDesc) {
        this.udMethodDetailPK = udMethodDetailPK;
        this.parameterName = parameterName;
        this.parameterDesc = parameterDesc;
    }

    public UdMethodDetail(String methodName, short seqNo) {
        this.udMethodDetailPK = new UdMethodDetailPK(methodName, seqNo);
    }

    public UdMethodDetailPK getUdMethodDetailPK() {
        return udMethodDetailPK;
    }

    public void setUdMethodDetailPK(UdMethodDetailPK udMethodDetailPK) {
        this.udMethodDetailPK = udMethodDetailPK;
    }

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public String getParameterDesc() {
        return parameterDesc;
    }

    public void setParameterDesc(String parameterDesc) {
        this.parameterDesc = parameterDesc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (udMethodDetailPK != null ? udMethodDetailPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UdMethodDetail)) {
            return false;
        }
        UdMethodDetail other = (UdMethodDetail) object;
        if ((this.udMethodDetailPK == null && other.udMethodDetailPK != null) || (this.udMethodDetailPK != null && !this.udMethodDetailPK.equals(other.udMethodDetailPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cub.entities.UdMethodDetail[ udMethodDetailPK=" + udMethodDetailPK + " ]";
    }
    
}
