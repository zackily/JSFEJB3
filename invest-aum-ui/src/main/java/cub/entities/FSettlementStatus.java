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
@Table(name = "F_SETTLEMENT_STATUS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FSettlementStatus.findAll", query = "SELECT f FROM FSettlementStatus f")
    , @NamedQuery(name = "FSettlementStatus.findById", query = "SELECT f FROM FSettlementStatus f WHERE f.id = :id")
    , @NamedQuery(name = "FSettlementStatus.findByFundNo", query = "SELECT f FROM FSettlementStatus f WHERE f.fundNo = :fundNo")
    , @NamedQuery(name = "FSettlementStatus.findByLastAumSettleDate", query = "SELECT f FROM FSettlementStatus f WHERE f.lastAumSettleDate = :lastAumSettleDate")
    , @NamedQuery(name = "FSettlementStatus.findByDateTime", query = "SELECT f FROM FSettlementStatus f WHERE f.dateTime = :dateTime")})
public class FSettlementStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "F_S_S_SEQ")
    @SequenceGenerator(name = "F_S_S_SEQ", sequenceName = "F_S_S_SEQ")
    private Long id;
    @Size(max = 8)
    @Column(name = "FUND_NO")
    private String fundNo;
    @Size(max = 8)
    @Column(name = "LAST_AUM_SETTLE_DATE")
    private String lastAumSettleDate;
    @Column(name = "DATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime;

    public FSettlementStatus() {
    }

    public FSettlementStatus(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFundNo() {
        return fundNo;
    }

    public void setFundNo(String fundNo) {
        this.fundNo = fundNo;
    }

    public String getLastAumSettleDate() {
        return lastAumSettleDate;
    }

    public void setLastAumSettleDate(String lastAumSettleDate) {
        this.lastAumSettleDate = lastAumSettleDate;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
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
        if (!(object instanceof FSettlementStatus)) {
            return false;
        }
        FSettlementStatus other = (FSettlementStatus) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cub.entities.FSettlementStatus[ id=" + id + " ]";
    }

}
