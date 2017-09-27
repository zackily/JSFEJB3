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
@Table(name = "MG_FEE_MONTH")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MgFeeMonth.findAll", query = "SELECT m FROM MgFeeMonth m")
    , @NamedQuery(name = "MgFeeMonth.findById", query = "SELECT m FROM MgFeeMonth m WHERE m.id = :id")
    , @NamedQuery(name = "MgFeeMonth.findByMgFeeMonthCustId", query = "SELECT m FROM MgFeeMonth m WHERE m.mgFeeMonthCustId = :mgFeeMonthCustId")
    , @NamedQuery(name = "MgFeeMonth.findByMgFeeMonthDate", query = "SELECT m FROM MgFeeMonth m WHERE m.mgFeeMonthDate = :mgFeeMonthDate")
    , @NamedQuery(name = "MgFeeMonth.findByMgFeeMonthActCode", query = "SELECT m FROM MgFeeMonth m WHERE m.mgFeeMonthActCode = :mgFeeMonthActCode")
    , @NamedQuery(name = "MgFeeMonth.findByMgFeeAumTwFee", query = "SELECT m FROM MgFeeMonth m WHERE m.mgFeeAumTwFee = :mgFeeAumTwFee")
    , @NamedQuery(name = "MgFeeMonth.findByMgFeeCostTwFee", query = "SELECT m FROM MgFeeMonth m WHERE m.mgFeeCostTwFee = :mgFeeCostTwFee")
    , @NamedQuery(name = "MgFeeMonth.findByMgFeeMonthcostTwFee", query = "SELECT m FROM MgFeeMonth m WHERE m.mgFeeMonthcostTwFee = :mgFeeMonthcostTwFee")
    , @NamedQuery(name = "MgFeeMonth.findByMgFeeMonthDateTime", query = "SELECT m FROM MgFeeMonth m WHERE m.mgFeeMonthDateTime = :mgFeeMonthDateTime")
    , @NamedQuery(name = "MgFeeMonth.findByMgFeeMonthMthsettleUserId", query = "SELECT m FROM MgFeeMonth m WHERE m.mgFeeMonthMthsettleUserId = :mgFeeMonthMthsettleUserId")
    , @NamedQuery(name = "MgFeeMonth.findByMgFeeMonthMthsettleDate", query = "SELECT m FROM MgFeeMonth m WHERE m.mgFeeMonthMthsettleDate = :mgFeeMonthMthsettleDate")})
public class MgFeeMonth implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MFM_SEQ")
    @SequenceGenerator(name = "MFM_SEQ", sequenceName = "MG_FEE_MONTH_SEQ" , initialValue = 1, allocationSize = 1)
    private Long id;
    @Size(max = 11)
    @Column(name = "MG_FEE_MONTH_CUST_ID")
    private String mgFeeMonthCustId;
    @Size(max = 8)
    @Column(name = "MG_FEE_MONTH_DATE")
    private String mgFeeMonthDate;
    @Size(max = 10)
    @Column(name = "MG_FEE_MONTH_ACT_CODE")
    private String mgFeeMonthActCode;
    @Column(name = "MG_FEE_AUM_TW_FEE")
    private BigDecimal mgFeeAumTwFee;
    @Column(name = "MG_FEE_COST_TW_FEE")
    private BigDecimal mgFeeCostTwFee;
    @Column(name = "MG_FEE_MONTHCOST_TW_FEE")
    private BigDecimal mgFeeMonthcostTwFee;
    @Column(name = "MG_FEE_MONTH_DATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date mgFeeMonthDateTime;
    @Size(max = 5)
    @Column(name = "MG_FEE_MONTH_MTHSETTLE_USER_ID")
    private String mgFeeMonthMthsettleUserId;
    @Column(name = "MG_FEE_MONTH_MTHSETTLE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date mgFeeMonthMthsettleDate;

    public MgFeeMonth() {
    }

    public MgFeeMonth(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMgFeeMonthCustId() {
        return mgFeeMonthCustId;
    }

    public void setMgFeeMonthCustId(String mgFeeMonthCustId) {
        this.mgFeeMonthCustId = mgFeeMonthCustId;
    }

    public String getMgFeeMonthDate() {
        return mgFeeMonthDate;
    }

    public void setMgFeeMonthDate(String mgFeeMonthDate) {
        this.mgFeeMonthDate = mgFeeMonthDate;
    }

    public String getMgFeeMonthActCode() {
        return mgFeeMonthActCode;
    }

    public void setMgFeeMonthActCode(String mgFeeMonthActCode) {
        this.mgFeeMonthActCode = mgFeeMonthActCode;
    }

    public BigDecimal getMgFeeAumTwFee() {
        return mgFeeAumTwFee;
    }

    public void setMgFeeAumTwFee(BigDecimal mgFeeAumTwFee) {
        this.mgFeeAumTwFee = mgFeeAumTwFee;
    }

    public BigDecimal getMgFeeCostTwFee() {
        return mgFeeCostTwFee;
    }

    public void setMgFeeCostTwFee(BigDecimal mgFeeCostTwFee) {
        this.mgFeeCostTwFee = mgFeeCostTwFee;
    }

    public BigDecimal getMgFeeMonthcostTwFee() {
        return mgFeeMonthcostTwFee;
    }

    public void setMgFeeMonthcostTwFee(BigDecimal mgFeeMonthcostTwFee) {
        this.mgFeeMonthcostTwFee = mgFeeMonthcostTwFee;
    }

    public Date getMgFeeMonthDateTime() {
        return mgFeeMonthDateTime;
    }

    public void setMgFeeMonthDateTime(Date mgFeeMonthDateTime) {
        this.mgFeeMonthDateTime = mgFeeMonthDateTime;
    }

    public String getMgFeeMonthMthsettleUserId() {
        return mgFeeMonthMthsettleUserId;
    }

    public void setMgFeeMonthMthsettleUserId(String mgFeeMonthMthsettleUserId) {
        this.mgFeeMonthMthsettleUserId = mgFeeMonthMthsettleUserId;
    }

    public Date getMgFeeMonthMthsettleDate() {
        return mgFeeMonthMthsettleDate;
    }

    public void setMgFeeMonthMthsettleDate(Date mgFeeMonthMthsettleDate) {
        this.mgFeeMonthMthsettleDate = mgFeeMonthMthsettleDate;
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
        if (!(object instanceof MgFeeMonth)) {
            return false;
        }
        MgFeeMonth other = (MgFeeMonth) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cub.entities.MgFeeMonth[ id=" + id + " ]";
    }
    
}
