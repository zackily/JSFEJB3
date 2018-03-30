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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author NT48810
 */
@Entity
@Table(name = "COMMON_CURRENCY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CommonCurrency.findAll", query = "SELECT c FROM CommonCurrency c")
    , @NamedQuery(name = "CommonCurrency.findById", query = "SELECT c FROM CommonCurrency c WHERE c.id = :id")
    , @NamedQuery(name = "CommonCurrency.findByCurrencyCode", query = "SELECT c FROM CommonCurrency c WHERE c.currencyCode = :currencyCode")
    , @NamedQuery(name = "CommonCurrency.findByCurrencyId", query = "SELECT c FROM CommonCurrency c WHERE c.currencyId = :currencyId")
    , @NamedQuery(name = "CommonCurrency.findByCurrencyDesc", query = "SELECT c FROM CommonCurrency c WHERE c.currencyDesc = :currencyDesc")
    , @NamedQuery(name = "CommonCurrency.findByCurrencyStatus", query = "SELECT c FROM CommonCurrency c WHERE c.currencyStatus = :currencyStatus")
    , @NamedQuery(name = "CommonCurrency.findByTradeCode", query = "SELECT c FROM CommonCurrency c WHERE c.tradeCode = :tradeCode")
    , @NamedQuery(name = "CommonCurrency.findByCurrDecimal", query = "SELECT c FROM CommonCurrency c WHERE c.currDecimal = :currDecimal")
    , @NamedQuery(name = "CommonCurrency.findByCrtDate", query = "SELECT c FROM CommonCurrency c WHERE c.crtDate = :crtDate")
    , @NamedQuery(name = "CommonCurrency.findByCrtUserId", query = "SELECT c FROM CommonCurrency c WHERE c.crtUserId = :crtUserId")
    , @NamedQuery(name = "CommonCurrency.findByUpdDate", query = "SELECT c FROM CommonCurrency c WHERE c.updDate = :updDate")
    , @NamedQuery(name = "CommonCurrency.findByUpdUserId", query = "SELECT c FROM CommonCurrency c WHERE c.updUserId = :updUserId")})
public class CommonCurrency implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @Size(max = 20)
    @Column(name = "CURRENCY_CODE")
    private String currencyCode;
    @Size(max = 20)
    @Column(name = "CURRENCY_ID")
    private String currencyId;
    @Size(max = 20)
    @Column(name = "CURRENCY_DESC")
    private String currencyDesc;
    @Size(max = 20)
    @Column(name = "CURRENCY_STATUS")
    private String currencyStatus;
    @Size(max = 20)
    @Column(name = "TRADE_CODE")
    private String tradeCode;
    @Column(name = "CURR_DECIMAL")
    private BigInteger currDecimal;
    @Column(name = "CRT_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date crtDate;
    @Size(max = 20)
    @Column(name = "CRT_USER_ID")
    private String crtUserId;
    @Column(name = "UPD_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updDate;
    @Size(max = 20)
    @Column(name = "UPD_USER_ID")
    private String updUserId;

    public CommonCurrency() {
    }

    public CommonCurrency(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

    public String getCurrencyDesc() {
        return currencyDesc;
    }

    public void setCurrencyDesc(String currencyDesc) {
        this.currencyDesc = currencyDesc;
    }

    public String getCurrencyStatus() {
        return currencyStatus;
    }

    public void setCurrencyStatus(String currencyStatus) {
        this.currencyStatus = currencyStatus;
    }

    public String getTradeCode() {
        return tradeCode;
    }

    public void setTradeCode(String tradeCode) {
        this.tradeCode = tradeCode;
    }

    public BigInteger getCurrDecimal() {
        return currDecimal;
    }

    public void setCurrDecimal(BigInteger currDecimal) {
        this.currDecimal = currDecimal;
    }

    public Date getCrtDate() {
        return crtDate;
    }

    public void setCrtDate(Date crtDate) {
        this.crtDate = crtDate;
    }

    public String getCrtUserId() {
        return crtUserId;
    }

    public void setCrtUserId(String crtUserId) {
        this.crtUserId = crtUserId;
    }

    public Date getUpdDate() {
        return updDate;
    }

    public void setUpdDate(Date updDate) {
        this.updDate = updDate;
    }

    public String getUpdUserId() {
        return updUserId;
    }

    public void setUpdUserId(String updUserId) {
        this.updUserId = updUserId;
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
        if (!(object instanceof CommonCurrency)) {
            return false;
        }
        CommonCurrency other = (CommonCurrency) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cub.entities.CommonCurrency[ id=" + id + " ]";
    }
    
}
