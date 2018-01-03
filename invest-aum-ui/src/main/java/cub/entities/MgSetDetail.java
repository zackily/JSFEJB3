/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cub.entities;

import cub.enums.MgSetMasterStatus;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "MG_FEE_ACT_DETAIL")
@XmlRootElement
public class MgSetDetail implements Serializable {

    @Enumerated(EnumType.STRING)
    @Column(name = "ACT_STATUS_CODE")
    private MgSetMasterStatus status;
    @Size(max = 10)
    @Column(name = "ACT_CREATER_NO")
    private String crtEmpId;
    @Size(max = 20)
    @Column(name = "ACT_CREATER_NAME")
    private String crtEmpName;
    @Size(max = 10)
    @Column(name = "ACT_CHECKER_NO")
    private String cfmEmpId;
    @Size(max = 20)
    @Column(name = "ACT_CHECKER_NAME")
    private String cfmEmpName;
    @Column(name = "ACT_CREATE_DTTM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date crtDate;
    @Column(name = "ACT_CHECK_DTTM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cfmDate;

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @NotNull
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MSD_SEQ")
    @SequenceGenerator(name = "MSD_SEQ", sequenceName = "MG_FEE_DETAIL_SEQ", initialValue = 1, allocationSize = 1)
    private Long id;
    @Size(max = 20)
    @Column(name = "ACT_CODE")
    private String mgActDCode;
    @Size(max = 5)
    @Column(name = "ACT_SUB_CODE")
    private String mgActDSeq;
//    @Size(max = 1)
//    @Column(name = "ACT_SEC_TYPE_CODE")
//    private String mgActSetType;
    @Size(max = 100)
    @Column(name = "ACT_SUB_NAME")
    private String mgActDName;
    @Size(max = 20)
    @Column(name = "ACT_SEC_TYPE_CODE")
    private String mgActDPrdCode;
    @Size(max = 1)
    @Column(name = "ACT_GROUP_TYPE_CODE")
    private String mgActDSecType;
//    @Size(max = 30)
//    @Column(name = "MG_ACT_D_SEC_CMP")
//    private String mgActDSecCmp;
//    @Size(max = 50)
//    @Column(name = "MG_ACT_D_SEC_CODE")
//    private String mgActDSecCode; 
    @Column(name = "ACT_CUST_TYPE")
    private String mgActDCostId;
//    @Size(max = 1)
//    @Column(name = "MG_ACT_D_SALE_CHNL")
//    private String mgActDSaleChnl;
    @Size(max = 8)
    @Column(name = "ACT_START_DATE")
    private String mgActDStartDate;
    @Size(max = 8)
    @Column(name = "ACT_END_DATE")
    private String mgActDEndDate;
    
//    @Column(name = "MG_ACT_D_SET_NO")
//    private BigInteger mgActDSetNo;
//    @Column(name = "MG_ACT_D_BASE_AMT")
//    private BigDecimal mgActDBaseAmt;
//    @Column(name = "MG_ACT_D_LOW_AMT")
//    private BigDecimal mgActDLowAmt;
//    @Column(name = "MG_ACT_D_HIGH_AMT")
//    private BigDecimal mgActDHighAmt;
//    @Column(name = "MG_ACT_D_RATE")
//    private BigDecimal mgActDRate;
//    @Column(name = "MG_ACT_D_FEE")
//    private BigDecimal mgActDFee;
    
    @Size(max = 100)
    @Column(name = "ACT_ACCUM_LEVEL_FLAG")
    private String mgActDRemark;
    @JoinColumn(name = "MG_FEE_MASTER_ID", referencedColumnName = "ID")
    @ManyToOne
    private MgSetMaster mgSetMasterId;

    public MgSetDetail() {
    }

    public MgSetDetail(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMgActDCode() {
        return mgActDCode;
    }

    public void setMgActDCode(String mgActDCode) {
        this.mgActDCode = mgActDCode;
    }

    public String getMgActDSeq() {
        return mgActDSeq;
    }

    public void setMgActDSeq(String mgActDSeq) {
        this.mgActDSeq = mgActDSeq;
    }

//    public String getMgActSetType() {
//        return mgActSetType;
//    }
//
//    public void setMgActSetType(String mgActSetType) {
//        this.mgActSetType = mgActSetType;
//    }

    public String getMgActDName() {
        return mgActDName;
    }

    public void setMgActDName(String mgActDName) {
        this.mgActDName = mgActDName;
    }

    public String getMgActDPrdCode() {
        return mgActDPrdCode;
    }

    public void setMgActDPrdCode(String mgActDPrdCode) {
        this.mgActDPrdCode = mgActDPrdCode;
    }

    public String getMgActDSecType() {
        return mgActDSecType;
    }

    public void setMgActDSecType(String mgActDSecType) {
        this.mgActDSecType = mgActDSecType;
    }

    
    public String getMgActDCostId() {
        return mgActDCostId;
    }

    public void setMgActDCostId(String mgActDCostId) {
        this.mgActDCostId = mgActDCostId;
    }

   

    public String getMgActDStartDate() {
        return mgActDStartDate;
    }

    public void setMgActDStartDate(String mgActDStartDate) {
        this.mgActDStartDate = mgActDStartDate;
    }

    public String getMgActDEndDate() {
        return mgActDEndDate;
    }

    public void setMgActDEndDate(String mgActDEndDate) {
        this.mgActDEndDate = mgActDEndDate;
    }
   
    public String getMgActDRemark() {
        return mgActDRemark;
    }

    public void setMgActDRemark(String mgActDRemark) {
        this.mgActDRemark = mgActDRemark;
    }

    public MgSetMaster getMgSetMasterId() {
        return mgSetMasterId;
    }

    public void setMgSetMasterId(MgSetMaster mgSetMasterId) {
        this.mgSetMasterId = mgSetMasterId;
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
        if (!(object instanceof MgSetDetail)) {
            return false;
        }
        MgSetDetail other = (MgSetDetail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cub.entities.MgSetDetail[ id=" + id + " ]";
    }

    public MgSetMasterStatus getStatus() {
        return status;
    }

    public void setStatus(MgSetMasterStatus status) {
        this.status = status;
    }

    public String getCrtEmpId() {
        return crtEmpId;
    }

    public void setCrtEmpId(String crtEmpId) {
        this.crtEmpId = crtEmpId;
    }

    public String getCrtEmpName() {
        return crtEmpName;
    }

    public void setCrtEmpName(String crtEmpName) {
        this.crtEmpName = crtEmpName;
    }

    public String getCfmEmpId() {
        return cfmEmpId;
    }

    public void setCfmEmpId(String cfmEmpId) {
        this.cfmEmpId = cfmEmpId;
    }

    public String getCfmEmpName() {
        return cfmEmpName;
    }

    public void setCfmEmpName(String cfmEmpName) {
        this.cfmEmpName = cfmEmpName;
    }

    public Date getCrtDate() {
        return crtDate;
    }

    public void setCrtDate(Date crtDate) {
        this.crtDate = crtDate;
    }

    public Date getCfmDate() {
        return cfmDate;
    }

    public void setCfmDate(Date cfmDate) {
        this.cfmDate = cfmDate;
    }

}
