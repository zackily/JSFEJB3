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
@Table(name = "MG_FEE_ACT_DETAIL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MgFeeActDetail.findAll", query = "SELECT m FROM MgFeeActDetail m")
    , @NamedQuery(name = "MgFeeActDetail.findByActCode", query = "SELECT m FROM MgFeeActDetail m WHERE m.actCode = :actCode")
    , @NamedQuery(name = "MgFeeActDetail.findByActSubcode", query = "SELECT m FROM MgFeeActDetail m WHERE m.actSubcode = :actSubcode")
    , @NamedQuery(name = "MgFeeActDetail.findByActName", query = "SELECT m FROM MgFeeActDetail m WHERE m.actName = :actName")
    , @NamedQuery(name = "MgFeeActDetail.findByStartDate", query = "SELECT m FROM MgFeeActDetail m WHERE m.startDate = :startDate")
    , @NamedQuery(name = "MgFeeActDetail.findByEndDate", query = "SELECT m FROM MgFeeActDetail m WHERE m.endDate = :endDate")
    , @NamedQuery(name = "MgFeeActDetail.findByChannel", query = "SELECT m FROM MgFeeActDetail m WHERE m.channel = :channel")
    , @NamedQuery(name = "MgFeeActDetail.findBySecType", query = "SELECT m FROM MgFeeActDetail m WHERE m.secType = :secType")
    , @NamedQuery(name = "MgFeeActDetail.findByValueGroupType", query = "SELECT m FROM MgFeeActDetail m WHERE m.valueGroupType = :valueGroupType")
    , @NamedQuery(name = "MgFeeActDetail.findById", query = "SELECT m FROM MgFeeActDetail m WHERE m.id = :id")})
public class MgFeeActDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ACT_CODE")
    private String actCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "ACT_SUBCODE")
    private String actSubcode;
    @Size(max = 100)
    @Column(name = "ACT_NAME")
    private String actName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "START_DATE")
    private String startDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "END_DATE")
    private String endDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CHANNEL")
    private Character channel;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SEC_TYPE")
    private Character secType;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALUE_GROUP_TYPE")
    private Character valueGroupType;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;

    public MgFeeActDetail() {
    }

    public MgFeeActDetail(BigDecimal id) {
        this.id = id;
    }

    public MgFeeActDetail(BigDecimal id, String actCode, String actSubcode, String startDate, String endDate, Character channel, Character secType, Character valueGroupType) {
        this.id = id;
        this.actCode = actCode;
        this.actSubcode = actSubcode;
        this.startDate = startDate;
        this.endDate = endDate;
        this.channel = channel;
        this.secType = secType;
        this.valueGroupType = valueGroupType;
    }

    public String getActCode() {
        return actCode;
    }

    public void setActCode(String actCode) {
        this.actCode = actCode;
    }

    public String getActSubcode() {
        return actSubcode;
    }

    public void setActSubcode(String actSubcode) {
        this.actSubcode = actSubcode;
    }

    public String getActName() {
        return actName;
    }

    public void setActName(String actName) {
        this.actName = actName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Character getChannel() {
        return channel;
    }

    public void setChannel(Character channel) {
        this.channel = channel;
    }

    public Character getSecType() {
        return secType;
    }

    public void setSecType(Character secType) {
        this.secType = secType;
    }

    public Character getValueGroupType() {
        return valueGroupType;
    }

    public void setValueGroupType(Character valueGroupType) {
        this.valueGroupType = valueGroupType;
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
        if (!(object instanceof MgFeeActDetail)) {
            return false;
        }
        MgFeeActDetail other = (MgFeeActDetail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cub.entities.MgFeeActDetail[ id=" + id + " ]";
    }
    
}
