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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "MG_SET_DETAIL_CUST_ACT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MgSetDetailCustAct.findAll", query = "SELECT m FROM MgSetDetailCustAct m")
    , @NamedQuery(name = "MgSetDetailCustAct.findById", query = "SELECT m FROM MgSetDetailCustAct m WHERE m.id = :id")
    , @NamedQuery(name = "MgSetDetailCustAct.findByMgActCode", query = "SELECT m FROM MgSetDetailCustAct m WHERE m.mgActCode = :mgActCode")
    , @NamedQuery(name = "MgSetDetailCustAct.findByMgActSubCode", query = "SELECT m FROM MgSetDetailCustAct m WHERE m.mgActSubCode = :mgActSubCode")
    , @NamedQuery(name = "MgSetDetailCustAct.findByCustomerId", query = "SELECT m FROM MgSetDetailCustAct m WHERE m.customerId = :customerId")
    , @NamedQuery(name = "MgSetDetailCustAct.findByStatus", query = "SELECT m FROM MgSetDetailCustAct m WHERE m.status = :status")})
public class MgSetDetailCustAct implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
     @SequenceGenerator(name = "MSDCA_SEQ", sequenceName = "MG_SET_DETAIL_CUST_ACT_SEQ", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MSDCA_SEQ")
    private BigDecimal id;
    @Size(max = 20)
    @Column(name = "MG_ACT_CODE")
    private String mgActCode;
    @Size(max = 5)
    @Column(name = "MG_ACT_SUB_CODE")
    private String mgActSubCode;
    @Size(max = 11)
    @Column(name = "CUSTOMER_ID")
    private String customerId;
    @Size(max = 5)
    @Column(name = "STATUS")
    private String status;

    public MgSetDetailCustAct() {
    }

    public MgSetDetailCustAct(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getMgActCode() {
        return mgActCode;
    }

    public void setMgActCode(String mgActCode) {
        this.mgActCode = mgActCode;
    }

    public String getMgActSubCode() {
        return mgActSubCode;
    }

    public void setMgActSubCode(String mgActSubCode) {
        this.mgActSubCode = mgActSubCode;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        if (!(object instanceof MgSetDetailCustAct)) {
            return false;
        }
        MgSetDetailCustAct other = (MgSetDetailCustAct) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cub.entities.MgSetDetailCustAct[ id=" + id + " ]";
    }
    
}
