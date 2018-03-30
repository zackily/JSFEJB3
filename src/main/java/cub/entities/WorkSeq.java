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
@Table(name = "WORK_SEQ")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "WorkSeq.findAll", query = "SELECT w FROM WorkSeq w")
    , @NamedQuery(name = "WorkSeq.findBySeqType", query = "SELECT w FROM WorkSeq w WHERE w.seqType = :seqType")
    , @NamedQuery(name = "WorkSeq.findBySeqNo", query = "SELECT w FROM WorkSeq w WHERE w.seqNo = :seqNo")})
public class WorkSeq implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "SEQ_TYPE")
    private String seqType;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SEQ_NO")
    private short seqNo;

    public WorkSeq() {
    }

    public WorkSeq(String seqType) {
        this.seqType = seqType;
    }

    public WorkSeq(String seqType, short seqNo) {
        this.seqType = seqType;
        this.seqNo = seqNo;
    }

    public String getSeqType() {
        return seqType;
    }

    public void setSeqType(String seqType) {
        this.seqType = seqType;
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
        hash += (seqType != null ? seqType.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WorkSeq)) {
            return false;
        }
        WorkSeq other = (WorkSeq) object;
        if ((this.seqType == null && other.seqType != null) || (this.seqType != null && !this.seqType.equals(other.seqType))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cub.entities.WorkSeq[ seqType=" + seqType + " ]";
    }
    
}
