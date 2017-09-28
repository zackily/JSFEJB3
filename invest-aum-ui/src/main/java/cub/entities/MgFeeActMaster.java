/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cub.entities;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "MG_FEE_ACT_MASTER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MgFeeActMaster.findAll", query = "SELECT m FROM MgFeeActMaster m")
    , @NamedQuery(name = "MgFeeActMaster.findByMgActCode", query = "SELECT m FROM MgFeeActMaster m WHERE m.mgActCode = :mgActCode")
    , @NamedQuery(name = "MgFeeActMaster.findByMgActName", query = "SELECT m FROM MgFeeActMaster m WHERE m.mgActName = :mgActName")
    , @NamedQuery(name = "MgFeeActMaster.findByMgActTypeCode", query = "SELECT m FROM MgFeeActMaster m WHERE m.mgActTypeCode = :mgActTypeCode")
    , @NamedQuery(name = "MgFeeActMaster.findByMgActRemark", query = "SELECT m FROM MgFeeActMaster m WHERE m.mgActRemark = :mgActRemark")
    , @NamedQuery(name = "MgFeeActMaster.findById", query = "SELECT m FROM MgFeeActMaster m WHERE m.id = :id")})
public class MgFeeActMaster implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "MG_ACT_CODE")
    private String mgActCode;
    @Size(max = 50)
    @Column(name = "MG_ACT_NAME")
    private String mgActName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MG_ACT_TYPE_CODE")
    private Character mgActTypeCode;
    @Size(max = 100)
    @Column(name = "MG_ACT_REMARK")
    private String mgActRemark;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;

    public MgFeeActMaster() {
    }

    public MgFeeActMaster(BigDecimal id) {
        this.id = id;
    }

    public MgFeeActMaster(BigDecimal id, String mgActCode, Character mgActTypeCode) {
        this.id = id;
        this.mgActCode = mgActCode;
        this.mgActTypeCode = mgActTypeCode;
    }

    public String getMgActCode() {
        return mgActCode;
    }

    public void setMgActCode(String mgActCode) {
        this.mgActCode = mgActCode;
    }

    public String getMgActName() {
        return mgActName;
    }

    public void setMgActName(String mgActName) {
        this.mgActName = mgActName;
    }

    public Character getMgActTypeCode() {
        return mgActTypeCode;
    }

    public void setMgActTypeCode(Character mgActTypeCode) {
        this.mgActTypeCode = mgActTypeCode;
    }

    public String getMgActRemark() {
        return mgActRemark;
    }

    public void setMgActRemark(String mgActRemark) {
        this.mgActRemark = mgActRemark;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MgFeeActMaster)) {
            return false;
        }
        MgFeeActMaster other = (MgFeeActMaster) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cub.entities.MgFeeActMaster[ id=" + id + " ]";
    }
    
}
