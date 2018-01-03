/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cub.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
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
@Table(name = "MG_CUST_ACT_LIST")
@XmlRootElement
public class MgCustActList implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "CUST_ID")
    private String custId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "ACT_CODE")
    private String actCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "ACT_SUB_CODE")
    private String actSubCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "ACT_DATA_DATE")
    private String actDataDt;
    @Size(max = 8)
    @Column(name = "ACT_START_DATE")
    private String actStartDt;
    @Size(max = 8)
    @Column(name = "ACT_END_DATE")
    private String actEndDt;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
   @SequenceGenerator(name = "MCAL_SEQ", sequenceName = "MG_CUST_ACT_LIST_SEQ", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MCAL_SEQ")
    private BigDecimal id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "ACT_STATUS_CODE")
    private String status;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ACT_UPDATE_DTTM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDttm;

    public MgCustActList() {
    }

    public MgCustActList(BigDecimal id) {
        this.id = id;
    }

    public MgCustActList(BigDecimal id, String custId, String actCode, String actSubCode, String actDataDt, String status, Date updateDttm) {
        this.id = id;
        this.custId = custId;
        this.actCode = actCode;
        this.actSubCode = actSubCode;
        this.actDataDt = actDataDt;
        this.status = status;
        this.updateDttm = updateDttm;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getActCode() {
        return actCode;
    }

    public void setActCode(String actCode) {
        this.actCode = actCode;
    }

    public String getActSubCode() {
        return actSubCode;
    }

    public void setActSubCode(String actSubCode) {
        this.actSubCode = actSubCode;
    }

    public String getActDataDt() {
        return actDataDt;
    }

    public void setActDataDt(String actDataDt) {
        this.actDataDt = actDataDt;
    }

    public String getActStartDt() {
        return actStartDt;
    }

    public void setActStartDt(String actStartDt) {
        this.actStartDt = actStartDt;
    }

    public String getActEndDt() {
        return actEndDt;
    }

    public void setActEndDt(String actEndDt) {
        this.actEndDt = actEndDt;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getUpdateDttm() {
        return updateDttm;
    }

    public void setUpdateDttm(Date updateDttm) {
        this.updateDttm = updateDttm;
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
        if (!(object instanceof MgCustActList)) {
            return false;
        }
        MgCustActList other = (MgCustActList) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cub.entities.MgCustActList[ id=" + id + " ]";
    }
    
}
