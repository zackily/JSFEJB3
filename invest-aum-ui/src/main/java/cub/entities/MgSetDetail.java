/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cub.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author NT48810
 */
@Entity
@Table(name = "MG_SET_DETAIL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MgSetDetail.findAll", query = "SELECT m FROM MgSetDetail m")
    , @NamedQuery(name = "MgSetDetail.findById", query = "SELECT m FROM MgSetDetail m WHERE m.id = :id")
    , @NamedQuery(name = "MgSetDetail.findByMgActDCode", query = "SELECT m FROM MgSetDetail m WHERE m.mgActDCode = :mgActDCode")
    , @NamedQuery(name = "MgSetDetail.findByMgActDSeq", query = "SELECT m FROM MgSetDetail m WHERE m.mgActDSeq = :mgActDSeq")
    , @NamedQuery(name = "MgSetDetail.findByMgActSetType", query = "SELECT m FROM MgSetDetail m WHERE m.mgActSetType = :mgActSetType")
    , @NamedQuery(name = "MgSetDetail.findByMgActDName", query = "SELECT m FROM MgSetDetail m WHERE m.mgActDName = :mgActDName")
    , @NamedQuery(name = "MgSetDetail.findByMgActDPrdCode", query = "SELECT m FROM MgSetDetail m WHERE m.mgActDPrdCode = :mgActDPrdCode")
    , @NamedQuery(name = "MgSetDetail.findByMgActDSecType", query = "SELECT m FROM MgSetDetail m WHERE m.mgActDSecType = :mgActDSecType")
    , @NamedQuery(name = "MgSetDetail.findByMgActDSecCmp", query = "SELECT m FROM MgSetDetail m WHERE m.mgActDSecCmp = :mgActDSecCmp")
    , @NamedQuery(name = "MgSetDetail.findByMgActDSecCode", query = "SELECT m FROM MgSetDetail m WHERE m.mgActDSecCode = :mgActDSecCode")
    , @NamedQuery(name = "MgSetDetail.findByMgActDCostId", query = "SELECT m FROM MgSetDetail m WHERE m.mgActDCostId = :mgActDCostId")
    , @NamedQuery(name = "MgSetDetail.findByMgActDSaleChnl", query = "SELECT m FROM MgSetDetail m WHERE m.mgActDSaleChnl = :mgActDSaleChnl")
    , @NamedQuery(name = "MgSetDetail.findByMgActDStartDate", query = "SELECT m FROM MgSetDetail m WHERE m.mgActDStartDate = :mgActDStartDate")
    , @NamedQuery(name = "MgSetDetail.findByMgActDEndDate", query = "SELECT m FROM MgSetDetail m WHERE m.mgActDEndDate = :mgActDEndDate")
    , @NamedQuery(name = "MgSetDetail.findByMgActDSetNo", query = "SELECT m FROM MgSetDetail m WHERE m.mgActDSetNo = :mgActDSetNo")
    , @NamedQuery(name = "MgSetDetail.findByMgActDBaseAmt", query = "SELECT m FROM MgSetDetail m WHERE m.mgActDBaseAmt = :mgActDBaseAmt")
    , @NamedQuery(name = "MgSetDetail.findByMgActDLowAmt", query = "SELECT m FROM MgSetDetail m WHERE m.mgActDLowAmt = :mgActDLowAmt")
    , @NamedQuery(name = "MgSetDetail.findByMgActDHighAmt", query = "SELECT m FROM MgSetDetail m WHERE m.mgActDHighAmt = :mgActDHighAmt")
    , @NamedQuery(name = "MgSetDetail.findByMgActDRate", query = "SELECT m FROM MgSetDetail m WHERE m.mgActDRate = :mgActDRate")
    , @NamedQuery(name = "MgSetDetail.findByMgActDFee", query = "SELECT m FROM MgSetDetail m WHERE m.mgActDFee = :mgActDFee")
    , @NamedQuery(name = "MgSetDetail.findByMgActDRemark", query = "SELECT m FROM MgSetDetail m WHERE m.mgActDRemark = :mgActDRemark")})
public class MgSetDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id    
    @NotNull
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MSD_SEQ")
    @SequenceGenerator(name = "MSD_SEQ", sequenceName = "MG_SET_DETAIL_SEQ", initialValue = 1, allocationSize = 1)
    private Long id;
    @Size(max = 20)
    @Column(name = "MG_ACT_D_CODE")
    private String mgActDCode;
    @Size(max = 5)
    @Column(name = "MG_ACT_D_SEQ")
    private String mgActDSeq;
    @Size(max = 1)
    @Column(name = "MG_ACT_SET_TYPE")
    private String mgActSetType;
    @Size(max = 100)
    @Column(name = "MG_ACT_D_NAME")
    private String mgActDName;
    @Size(max = 20)
    @Column(name = "MG_ACT_D_PRD_CODE")
    private String mgActDPrdCode;
    @Size(max = 1)
    @Column(name = "MG_ACT_D_SEC_TYPE")
    private String mgActDSecType;
    @Size(max = 30)
    @Column(name = "MG_ACT_D_SEC_CMP")
    private String mgActDSecCmp;
    @Size(max = 50)
    @Column(name = "MG_ACT_D_SEC_CODE")
    private String mgActDSecCode;
    @Size(max = 11)
    @Column(name = "MG_ACT_D_COST_ID")
    private String mgActDCostId;
    @Size(max = 1)
    @Column(name = "MG_ACT_D_SALE_CHNL")
    private String mgActDSaleChnl;
    @Size(max = 8)
    @Column(name = "MG_ACT_D_START_DATE")
    private String mgActDStartDate;
    @Size(max = 8)
    @Column(name = "MG_ACT_D_END_DATE")
    private String mgActDEndDate;
    @Column(name = "MG_ACT_D_SET_NO")
    private BigInteger mgActDSetNo;
    @Column(name = "MG_ACT_D_BASE_AMT")
    private BigDecimal mgActDBaseAmt;
    @Column(name = "MG_ACT_D_LOW_AMT")
    private BigDecimal mgActDLowAmt;
    @Column(name = "MG_ACT_D_HIGH_AMT")
    private BigDecimal mgActDHighAmt;
    @Column(name = "MG_ACT_D_RATE")
    private BigDecimal mgActDRate;
    @Column(name = "MG_ACT_D_FEE")
    private BigDecimal mgActDFee;
    @Size(max = 100)
    @Column(name = "MG_ACT_D_REMARK")
    private String mgActDRemark;
    @JoinColumn(name = "MG_SET_MASTER_ID", referencedColumnName = "ID")
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

    public String getMgActSetType() {
        return mgActSetType;
    }

    public void setMgActSetType(String mgActSetType) {
        this.mgActSetType = mgActSetType;
    }

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

    public String getMgActDSecCmp() {
        return mgActDSecCmp;
    }

    public void setMgActDSecCmp(String mgActDSecCmp) {
        this.mgActDSecCmp = mgActDSecCmp;
    }

    public String getMgActDSecCode() {
        return mgActDSecCode;
    }

    public void setMgActDSecCode(String mgActDSecCode) {
        this.mgActDSecCode = mgActDSecCode;
    }

    public String getMgActDCostId() {
        return mgActDCostId;
    }

    public void setMgActDCostId(String mgActDCostId) {
        this.mgActDCostId = mgActDCostId;
    }

    public String getMgActDSaleChnl() {
        return mgActDSaleChnl;
    }

    public void setMgActDSaleChnl(String mgActDSaleChnl) {
        this.mgActDSaleChnl = mgActDSaleChnl;
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

    public BigInteger getMgActDSetNo() {
        return mgActDSetNo;
    }

    public void setMgActDSetNo(BigInteger mgActDSetNo) {
        this.mgActDSetNo = mgActDSetNo;
    }

    public BigDecimal getMgActDBaseAmt() {
        return mgActDBaseAmt;
    }

    public void setMgActDBaseAmt(BigDecimal mgActDBaseAmt) {
        this.mgActDBaseAmt = mgActDBaseAmt;
    }

    public BigDecimal getMgActDLowAmt() {
        return mgActDLowAmt;
    }

    public void setMgActDLowAmt(BigDecimal mgActDLowAmt) {
        this.mgActDLowAmt = mgActDLowAmt;
    }

    public BigDecimal getMgActDHighAmt() {
        return mgActDHighAmt;
    }

    public void setMgActDHighAmt(BigDecimal mgActDHighAmt) {
        this.mgActDHighAmt = mgActDHighAmt;
    }

    public BigDecimal getMgActDRate() {
        return mgActDRate;
    }

    public void setMgActDRate(BigDecimal mgActDRate) {
        this.mgActDRate = mgActDRate;
    }

    public BigDecimal getMgActDFee() {
        return mgActDFee;
    }

    public void setMgActDFee(BigDecimal mgActDFee) {
        this.mgActDFee = mgActDFee;
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
    
}
