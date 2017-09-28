/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cub.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author NT48810
 */
@Entity
@Table(name = "MSD_FUND_MAP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MsdFundMap.findAll", query = "SELECT m FROM MsdFundMap m")
    , @NamedQuery(name = "MsdFundMap.findById", query = "SELECT m FROM MsdFundMap m WHERE m.id = :id")
    , @NamedQuery(name = "MsdFundMap.findByMsdId", query = "SELECT m FROM MsdFundMap m WHERE m.msdId = :msdId")
    , @NamedQuery(name = "MsdFundMap.findByFundId", query = "SELECT m FROM MsdFundMap m WHERE m.fundId = :fundId")
    , @NamedQuery(name = "MsdFundMap.findByCrtDate", query = "SELECT m FROM MsdFundMap m WHERE m.crtDate = :crtDate")})
public class MsdFundMap implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @Column(name = "MSD_ID")
    private BigInteger msdId;
    @Column(name = "FUND_ID")
    private BigInteger fundId;
    @Column(name = "CRT_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date crtDate;

    public MsdFundMap() {
    }

    public MsdFundMap(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigInteger getMsdId() {
        return msdId;
    }

    public void setMsdId(BigInteger msdId) {
        this.msdId = msdId;
    }

    public BigInteger getFundId() {
        return fundId;
    }

    public void setFundId(BigInteger fundId) {
        this.fundId = fundId;
    }

    public Date getCrtDate() {
        return crtDate;
    }

    public void setCrtDate(Date crtDate) {
        this.crtDate = crtDate;
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
        if (!(object instanceof MsdFundMap)) {
            return false;
        }
        MsdFundMap other = (MsdFundMap) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cub.entities.MsdFundMap[ id=" + id + " ]";
    }
    
}
