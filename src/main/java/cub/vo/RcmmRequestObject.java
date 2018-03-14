package cub.vo;

import java.math.BigDecimal;
import java.util.Date;

public class RcmmRequestObject {

    private String APID;
    private String orderNo;
    private Date orderDttm;
    private short prdCode;
    private short tradeType;
    private short channelCode;
    private short checkTiming;
    private String clientId;
    private String secCode;
    private String clientIdSwitchIn;
    private String secCodeSwitchIn;
    private BigDecimal orderQty;
    private BigDecimal orderPrice;
    private BigDecimal orderAmt;
    private String orderCur;

    public String getAPID() {
        return APID;
    }

    public void setAPID(String aPID) {
        APID = aPID;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Date getOrderDttm() {
        return orderDttm;
    }

    public void setOrderDttm(Date orderDttm) {
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

    public String getOrderCur() {
        return orderCur;
    }

    public void setOrderCur(String orderCur) {
        this.orderCur = orderCur;
    }

}
