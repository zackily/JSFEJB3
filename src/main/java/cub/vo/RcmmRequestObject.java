package cub.vo;

import java.math.BigDecimal;
import java.util.Date;

public class RcmmRequestObject {

	private String apid;
	private String orderNo;
	private Date orderDttm;
	private BigDecimal prdCode;
	private BigDecimal tradeType;
	private BigDecimal checkTiming;
	private String clientId;
	private String secCode;
	private String clientIdSwitchIn;
	private String secCodeSwitchIn;
	private BigDecimal orderQty;
	private BigDecimal orderPrice;
	private BigDecimal orderAmt;
	private String orderCurrency;
	private String masterOrderNo;
	private String americanDeclare;
	private BigDecimal branchId;
	private BigDecimal tellerId;
	private BigDecimal orderPage;
	private BigDecimal orderColumn;

	public String getApid() {
		return apid;
	}

	public void setApid(String apid) {
		this.apid = apid;
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

	public BigDecimal getPrdCode() {
		return prdCode;
	}

	public void setPrdCode(BigDecimal prdCode) {
		this.prdCode = prdCode;
	}

	public BigDecimal getTradeType() {
		return tradeType;
	}

	public void setTradeType(BigDecimal tradeType) {
		this.tradeType = tradeType;
	}

	public BigDecimal getCheckTiming() {
		return checkTiming;
	}

	public void setCheckTiming(BigDecimal checkTiming) {
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

	public String getOrderCurrency() {
		return orderCurrency;
	}

	public void setOrderCurrency(String orderCurrency) {
		this.orderCurrency = orderCurrency;
	}

	public String getMasterOrderNo() {
		return masterOrderNo;
	}

	public void setMasterOrderNo(String masterOrderNo) {
		this.masterOrderNo = masterOrderNo;
	}

	public String getAmericanDeclare() {
		return americanDeclare;
	}

	public void setAmericanDeclare(String americanDeclare) {
		this.americanDeclare = americanDeclare;
	}

	public BigDecimal getBranchId() {
		return branchId;
	}

	public void setBranchId(BigDecimal branchId) {
		this.branchId = branchId;
	}

	public BigDecimal getTellerId() {
		return tellerId;
	}

	public void setTellerId(BigDecimal tellerId) {
		this.tellerId = tellerId;
	}

	public BigDecimal getOrderPage() {
		return orderPage;
	}

	public void setOrderPage(BigDecimal orderPage) {
		this.orderPage = orderPage;
	}

	public BigDecimal getOrderColumn() {
		return orderColumn;
	}

	public void setOrderColumn(BigDecimal orderColumn) {
		this.orderColumn = orderColumn;
	}

}
