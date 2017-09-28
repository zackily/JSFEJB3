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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "F_SETTLE_STATUS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FSettleStatus.findAll", query = "SELECT f FROM FSettleStatus f")
    , @NamedQuery(name = "FSettleStatus.findByFundId", query = "SELECT f FROM FSettleStatus f WHERE f.fundId = :fundId")
    , @NamedQuery(name = "FSettleStatus.findByLastSettleDate", query = "SELECT f FROM FSettleStatus f WHERE f.lastSettleDate = :lastSettleDate")
    , @NamedQuery(name = "FSettleStatus.findByDttm", query = "SELECT f FROM FSettleStatus f WHERE f.dttm = :dttm")
    , @NamedQuery(name = "FSettleStatus.findById", query = "SELECT f FROM FSettleStatus f WHERE f.id = :id")})
public class FSettleStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "FUND_ID")
    private String fundId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "LAST_SETTLE_DATE")
    private String lastSettleDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DTTM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dttm;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;

    public FSettleStatus() {
    }

    public FSettleStatus(BigDecimal id) {
        this.id = id;
    }

    public FSettleStatus(BigDecimal id, String fundId, String lastSettleDate, Date dttm) {
        this.id = id;
        this.fundId = fundId;
        this.lastSettleDate = lastSettleDate;
        this.dttm = dttm;
    }

    public String getFundId() {
        return fundId;
    }

    public void setFundId(String fundId) {
        this.fundId = fundId;
    }

    public String getLastSettleDate() {
        return lastSettleDate;
    }

    public void setLastSettleDate(String lastSettleDate) {
        this.lastSettleDate = lastSettleDate;
    }

    public Date getDttm() {
        return dttm;
    }

    public void setDttm(Date dttm) {
        this.dttm = dttm;
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
        if (!(object instanceof FSettleStatus)) {
            return false;
        }
        FSettleStatus other = (FSettleStatus) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cub.entities.FSettleStatus[ id=" + id + " ]";
    }
    
}
