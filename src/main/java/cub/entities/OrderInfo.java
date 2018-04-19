package cub.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the ORDER_INFO database table.
 * 
 */
@Entity
@Table(name="ORDER_INFO")
@NamedQuery(name="OrderInfo.findAll", query="SELECT o FROM OrderInfo o")
public class OrderInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ORDER_NO")
	private String orderNo;

	@Column(name="AMERICAN_DECLARE")
	private String americanDeclare;

	@Column(name="BRANCH_ID")
	private BigDecimal branchId;

	@Column(name="CHANNEL_CODE")
	private BigDecimal channelCode;

	@Column(name="CHECK_TIMING")
	private BigDecimal checkTiming;

	@Column(name="CLIENT_ID")
	private String clientId;

	@Column(name="CLIENT_ID_SWITCH_IN")
	private String clientIdSwitchIn;

	@Column(name="MASTER_ORDER_NO")
	private String masterOrderNo;

	@Column(name="ORDER_AMT")
	private BigDecimal orderAmt;

	@Column(name="ORDER_CUR")
	private String orderCur;

	@Temporal(TemporalType.DATE)
	@Column(name="ORDER_DTTM")
	private Date orderDttm;

	@Column(name="ORDER_PRICE")
	private BigDecimal orderPrice;

	@Column(name="ORDER_QTY")
	private BigDecimal orderQty;

	@Column(name="PRD_CODE")
	private BigDecimal prdCode;

	@Column(name="SEC_CODE")
	private String secCode;

	@Column(name="SEC_CODE_SWITCH_IN")
	private String secCodeSwitchIn;

	@Column(name="TELLER_ID")
	private BigDecimal tellerId;

	@Column(name="TRADE_TYPE")
	private BigDecimal tradeType;

	public OrderInfo() {
	}

	public String getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getAmericanDeclare() {
		return this.americanDeclare;
	}

	public void setAmericanDeclare(String americanDeclare) {
		this.americanDeclare = americanDeclare;
	}

	public BigDecimal getBranchId() {
		return this.branchId;
	}

	public void setBranchId(BigDecimal branchId) {
		this.branchId = branchId;
	}

	public BigDecimal getChannelCode() {
		return this.channelCode;
	}

	public void setChannelCode(BigDecimal channelCode) {
		this.channelCode = channelCode;
	}

	public BigDecimal getCheckTiming() {
		return this.checkTiming;
	}

	public void setCheckTiming(BigDecimal checkTiming) {
		this.checkTiming = checkTiming;
	}

	public String getClientId() {
		return this.clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientIdSwitchIn() {
		return this.clientIdSwitchIn;
	}

	public void setClientIdSwitchIn(String clientIdSwitchIn) {
		this.clientIdSwitchIn = clientIdSwitchIn;
	}

	public String getMasterOrderNo() {
		return this.masterOrderNo;
	}

	public void setMasterOrderNo(String masterOrderNo) {
		this.masterOrderNo = masterOrderNo;
	}

	public BigDecimal getOrderAmt() {
		return this.orderAmt;
	}

	public void setOrderAmt(BigDecimal orderAmt) {
		this.orderAmt = orderAmt;
	}

	public String getOrderCur() {
		return this.orderCur;
	}

	public void setOrderCur(String orderCur) {
		this.orderCur = orderCur;
	}

	public Date getOrderDttm() {
		return this.orderDttm;
	}

	public void setOrderDttm(Date orderDttm) {
		this.orderDttm = orderDttm;
	}

	public BigDecimal getOrderPrice() {
		return this.orderPrice;
	}

	public void setOrderPrice(BigDecimal orderPrice) {
		this.orderPrice = orderPrice;
	}

	public BigDecimal getOrderQty() {
		return this.orderQty;
	}

	public void setOrderQty(BigDecimal orderQty) {
		this.orderQty = orderQty;
	}

	public BigDecimal getPrdCode() {
		return this.prdCode;
	}

	public void setPrdCode(BigDecimal prdCode) {
		this.prdCode = prdCode;
	}

	public String getSecCode() {
		return this.secCode;
	}

	public void setSecCode(String secCode) {
		this.secCode = secCode;
	}

	public String getSecCodeSwitchIn() {
		return this.secCodeSwitchIn;
	}

	public void setSecCodeSwitchIn(String secCodeSwitchIn) {
		this.secCodeSwitchIn = secCodeSwitchIn;
	}

	public BigDecimal getTellerId() {
		return this.tellerId;
	}

	public void setTellerId(BigDecimal tellerId) {
		this.tellerId = tellerId;
	}

	public BigDecimal getTradeType() {
		return this.tradeType;
	}

	public void setTradeType(BigDecimal tradeType) {
		this.tradeType = tradeType;
	}

}