/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cub.invest.aum;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
@Table(name = "Fund")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fund.findAll", query = "SELECT f FROM Fund f")
    , @NamedQuery(name = "Fund.findById", query = "SELECT f FROM Fund f WHERE f.id = :id")
    , @NamedQuery(name = "Fund.findByFundID", query = "SELECT f FROM Fund f WHERE f.fundID = :fundID")
    , @NamedQuery(name = "Fund.findByAssocFundNo", query = "SELECT f FROM Fund f WHERE f.assocFundNo = :assocFundNo")
    , @NamedQuery(name = "Fund.findByFundCName", query = "SELECT f FROM Fund f WHERE f.fundCName = :fundCName")
    , @NamedQuery(name = "Fund.findByFundCShortNM", query = "SELECT f FROM Fund f WHERE f.fundCShortNM = :fundCShortNM")
    , @NamedQuery(name = "Fund.findByFundEName", query = "SELECT f FROM Fund f WHERE f.fundEName = :fundEName")
    , @NamedQuery(name = "Fund.findByFundEShortNM", query = "SELECT f FROM Fund f WHERE f.fundEShortNM = :fundEShortNM")
    , @NamedQuery(name = "Fund.findByCategoryID", query = "SELECT f FROM Fund f WHERE f.categoryID = :categoryID")
    , @NamedQuery(name = "Fund.findByFundStatus", query = "SELECT f FROM Fund f WHERE f.fundStatus = :fundStatus")
    , @NamedQuery(name = "Fund.findByCorpid", query = "SELECT f FROM Fund f WHERE f.corpid = :corpid")})
public class Fund implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @Size(max = 8)
    @Column(name = "FundID")
    private String fundID;
    @Size(max = 15)
    @Column(name = "AssocFundNo")
    private String assocFundNo;
    @Size(max = 80)
    @Column(name = "FundCName")
    private String fundCName;
    @Size(max = 40)
    @Column(name = "FundCShortNM")
    private String fundCShortNM;
    @Size(max = 100)
    @Column(name = "FundEName")
    private String fundEName;
    @Size(max = 40)
    @Column(name = "FundEShortNM")
    private String fundEShortNM;
    @Column(name = "CategoryID")
    private BigInteger categoryID;
    @Size(max = 2)
    @Column(name = "FundStatus")
    private String fundStatus;
    @Size(max = 4)
    @Column(name = "CORPID")
    private String corpid;

    public Fund() {
    }

    public Fund(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getFundID() {
        return fundID;
    }

    public void setFundID(String fundID) {
        this.fundID = fundID;
    }

    public String getAssocFundNo() {
        return assocFundNo;
    }

    public void setAssocFundNo(String assocFundNo) {
        this.assocFundNo = assocFundNo;
    }

    public String getFundCName() {
        return fundCName;
    }

    public void setFundCName(String fundCName) {
        this.fundCName = fundCName;
    }

    public String getFundCShortNM() {
        return fundCShortNM;
    }

    public void setFundCShortNM(String fundCShortNM) {
        this.fundCShortNM = fundCShortNM;
    }

    public String getFundEName() {
        return fundEName;
    }

    public void setFundEName(String fundEName) {
        this.fundEName = fundEName;
    }

    public String getFundEShortNM() {
        return fundEShortNM;
    }

    public void setFundEShortNM(String fundEShortNM) {
        this.fundEShortNM = fundEShortNM;
    }

    public BigInteger getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(BigInteger categoryID) {
        this.categoryID = categoryID;
    }

    public String getFundStatus() {
        return fundStatus;
    }

    public void setFundStatus(String fundStatus) {
        this.fundStatus = fundStatus;
    }

    public String getCorpid() {
        return corpid;
    }

    public void setCorpid(String corpid) {
        this.corpid = corpid;
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
        if (!(object instanceof Fund)) {
            return false;
        }
        Fund other = (Fund) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cub.invest.aum.Fund[ id=" + id + " ]";
    }
    
}
