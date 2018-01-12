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
@Table(name = "ORDER_INFO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrderInfo.findAll", query = "SELECT o FROM OrderInfo o")
    , @NamedQuery(name = "OrderInfo.findByOrderNo", query = "SELECT o FROM OrderInfo o WHERE o.orderNo = :orderNo")
    , @NamedQuery(name = "OrderInfo.findByOrderDttm", query = "SELECT o FROM OrderInfo o WHERE o.orderDttm = :orderDttm")
    , @NamedQuery(name = "OrderInfo.findByPrdCode", query = "SELECT o FROM OrderInfo o WHERE o.prdCode = :prdCode")
    , @NamedQuery(name = "OrderInfo.findByTradeType", query = "SELECT o FROM OrderInfo o WHERE o.tradeType = :tradeType")
    , @NamedQuery(name = "OrderInfo.findByChannelCode", query = "SELECT o FROM OrderInfo o WHERE o.channelCode = :channelCode")
    , @NamedQuery(name = "OrderInfo.findByCheckTiming", query = "SELECT o FROM OrderInfo o WHERE o.checkTiming = :checkTiming")
    , @NamedQuery(name = "OrderInfo.findByClientId", query = "SELECT o FROM OrderInfo o WHERE o.clientId = :clientId")
    , @NamedQuery(name = "OrderInfo.findBySecCode", query = "SELECT o FROM OrderInfo o WHERE o.secCode = :secCode")
    , @NamedQuery(name = "OrderInfo.findByClientIdSwitchIn", query = "SELECT o FROM OrderInfo o WHERE o.clientIdSwitchIn = :clientIdSwitchIn")
    , @NamedQuery(name = "OrderInfo.findBySecCodeSwitchIn", query = "SELECT o FROM OrderInfo o WHERE o.secCodeSwitchIn = :secCodeSwitchIn")
    , @NamedQuery(name = "OrderInfo.findByOrderQty", query = "SELECT o FROM OrderInfo o WHERE o.orderQty = :orderQty")
    , @NamedQuery(name = "OrderInfo.findByOrderPrice", query = "SELECT o FROM OrderInfo o WHERE o.orderPrice = :orderPrice")
    , @NamedQuery(name = "OrderInfo.findByOrderAmt", query = "SELECT o FROM OrderInfo o WHERE o.orderAmt = :orderAmt")})
public class OrderInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "ORDER_NO")
    private String orderNo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ORDER_DTTM")
    private short orderDttm;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRD_CODE")
    private short prdCode;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TRADE_TYPE")
    private short tradeType;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CHANNEL_CODE")
    private short channelCode;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CHECK_TIMING")
    private short checkTiming;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "CLIENT_ID")
    private String clientId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "SEC_CODE")
    private String secCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "CLIENT_ID_SWITCH_IN")
    private String clientIdSwitchIn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "SEC_CODE_SWITCH_IN")
    private String secCodeSwitchIn;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ORDER_QTY")
    private BigDecimal orderQty;
    @Column(name = "ORDER_PRICE")
    private BigDecimal orderPrice;
    @Column(name = "ORDER_AMT")
    private BigDecimal orderAmt;

    public OrderInfo() {
    }

    public OrderInfo(String orderNo) {
        this.orderNo = orderNo;
    }

    public OrderInfo(String orderNo, short orderDttm, short prdCode, short tradeType, short channelCode, short checkTiming, String clientId, String secCode, String clientIdSwitchIn, String secCodeSwitchIn) {
        this.orderNo = orderNo;
        this.orderDttm = orderDttm;
        this.prdCode = prdCode;
        this.tradeType = tradeType;
        this.channelCode = channelCode;
        this.checkTiming = checkTiming;
        this.clientId = clientId;
        this.secCode = secCode;
        this.clientIdSwitchIn = clientIdSwitchIn;
        this.secCodeSwitchIn = secCodeSwitchIn;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public short getOrderDttm() {
        return orderDttm;
    }

    public void setOrderDttm(short orderDttm) {
        this.orderDttm = orderDttm;
    }

    public short getPrdCode() {
        return prdCode;
    }

    public void setPrdCode(short prdCode) {
        this.prdCode = prdCode;
    }

    public short getTradeType() {
        return tradeType;
    }

    public void setTradeType(short tradeType) {
        this.tradeType = tradeType;
    }

    public short getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(short channelCode) {
        this.channelCode = channelCode;
    }

    public short getCheckTiming() {
        return checkTiming;
    }

    public void setCheckTiming(short checkTiming) {
        this.checkTiming = checkTiming;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getSecCode() {
        return secCode;
    }

    public void setSecCode(String secCode) {
        this.secCode = secCode;
    }

    public String getClientIdSwitchIn() {
        return clientIdSwitchIn;
    }

    public void setClientIdSwitchIn(String clientIdSwitchIn) {
        this.clientIdSwitchIn = clientIdSwitchIn;
    }

    public String getSecCodeSwitchIn() {
        return secCodeSwitchIn;
    }

    public void setSecCodeSwitchIn(String secCodeSwitchIn) {
        this.secCodeSwitchIn = secCodeSwitchIn;
    }

    public BigDecimal getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(BigDecimal orderQty) {
        this.orderQty = orderQty;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public BigDecimal getOrderAmt() {
        return orderAmt;
    }

    public void setOrderAmt(BigDecimal orderAmt) {
        this.orderAmt = orderAmt;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderNo != null ? orderNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderInfo)) {
            return false;
        }
        OrderInfo other = (OrderInfo) object;
        if ((this.orderNo == null && other.orderNo != null) || (this.orderNo != null && !this.orderNo.equals(other.orderNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cub.entities.OrderInfo[ orderNo=" + orderNo + " ]";
    }
    
}
