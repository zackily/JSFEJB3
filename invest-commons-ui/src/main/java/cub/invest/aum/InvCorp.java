/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cub.invest.aum;

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
 * @author nt48810
 */
@Entity
@Table(name = "INVCOMPANY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InvCorp.findAll", query = "SELECT i FROM InvCorp i")
    , @NamedQuery(name = "InvCorp.findById", query = "SELECT i FROM InvCorp i WHERE i.id = :id")
    , @NamedQuery(name = "InvCorp.findByCorpID", query = "SELECT i FROM InvCorp i WHERE i.corpID = :corpID")
    , @NamedQuery(name = "InvCorp.findByAssocCorpID", query = "SELECT i FROM InvCorp i WHERE i.assocCorpID = :assocCorpID")
    , @NamedQuery(name = "InvCorp.findByCorpCName", query = "SELECT i FROM InvCorp i WHERE i.corpCName = :corpCName")
    , @NamedQuery(name = "InvCorp.findByCorpCShortNM", query = "SELECT i FROM InvCorp i WHERE i.corpCShortNM = :corpCShortNM")
    , @NamedQuery(name = "InvCorp.findByCorpEName", query = "SELECT i FROM InvCorp i WHERE i.corpEName = :corpEName")
    , @NamedQuery(name = "InvCorp.findByCorpEShortNM", query = "SELECT i FROM InvCorp i WHERE i.corpEShortNM = :corpEShortNM")
    , @NamedQuery(name = "InvCorp.findByParCorpEName", query = "SELECT i FROM InvCorp i WHERE i.parCorpEName = :parCorpEName")
    , @NamedQuery(name = "InvCorp.findByParCorpEShortNM", query = "SELECT i FROM InvCorp i WHERE i.parCorpEShortNM = :parCorpEShortNM")})
public class InvCorp implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @Size(max = 4)
    @Column(name = "CORPID")
    private String corpID;
    @Size(max = 5)
    @Column(name = "ASSOCCORPID")
    private String assocCorpID;
    @Size(max = 60)
    @Column(name = "CORPCNAME")
    private String corpCName;
    @Size(max = 60)
    @Column(name = "CORPCSHORTNM")
    private String corpCShortNM;
    @Size(max = 60)
    @Column(name = "CORPENAME")
    private String corpEName;
    @Size(max = 30)
    @Column(name = "CORPESHORTNM")
    private String corpEShortNM;
    @Size(max = 60)
    @Column(name = "PARCORPENAME")
    private String parCorpEName;
    @Size(max = 60)
    @Column(name = "PARCORPESHORTNM")
    private String parCorpEShortNM;

    public InvCorp() {
    }

    public InvCorp(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getCorpID() {
        return corpID;
    }

    public void setCorpID(String corpID) {
        this.corpID = corpID;
    }

    public String getAssocCorpID() {
        return assocCorpID;
    }

    public void setAssocCorpID(String assocCorpID) {
        this.assocCorpID = assocCorpID;
    }

    public String getCorpCName() {
        return corpCName;
    }

    public void setCorpCName(String corpCName) {
        this.corpCName = corpCName;
    }

    public String getCorpCShortNM() {
        return corpCShortNM;
    }

    public void setCorpCShortNM(String corpCShortNM) {
        this.corpCShortNM = corpCShortNM;
    }

    public String getCorpEName() {
        return corpEName;
    }

    public void setCorpEName(String corpEName) {
        this.corpEName = corpEName;
    }

    public String getCorpEShortNM() {
        return corpEShortNM;
    }

    public void setCorpEShortNM(String corpEShortNM) {
        this.corpEShortNM = corpEShortNM;
    }

    public String getParCorpEName() {
        return parCorpEName;
    }

    public void setParCorpEName(String parCorpEName) {
        this.parCorpEName = parCorpEName;
    }

    public String getParCorpEShortNM() {
        return parCorpEShortNM;
    }

    public void setParCorpEShortNM(String parCorpEShortNM) {
        this.parCorpEShortNM = parCorpEShortNM;
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
        if (!(object instanceof InvCorp)) {
            return false;
        }
        InvCorp other = (InvCorp) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cub.invest.aum.InvCorp[ id=" + id + " ]";
    }
    
}
