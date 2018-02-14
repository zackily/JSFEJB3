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
 * @author F123669
 */
@Embeddable
public class UdMethodDetailPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "METHOD_NAME")
    private String methodName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SEQ_NO")
    private short seqNo;

    public UdMethodDetailPK() {
    }

    public UdMethodDetailPK(String methodName, short seqNo) {
        this.methodName = methodName;
        this.seqNo = seqNo;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
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
        hash += (methodName != null ? methodName.hashCode() : 0);
        hash += (int) seqNo;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UdMethodDetailPK)) {
            return false;
        }
        UdMethodDetailPK other = (UdMethodDetailPK) object;
        if ((this.methodName == null && other.methodName != null) || (this.methodName != null && !this.methodName.equals(other.methodName))) {
            return false;
        }
        if (this.seqNo != other.seqNo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cub.entities.UdMethodDetailPK[ methodName=" + methodName + ", seqNo=" + seqNo + " ]";
    }
    
}
