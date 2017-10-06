package cub.invest.aum.service.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(
		indexes = {@Index(name = "idx_cust_id_base_date", columnList = "CUST_ID, BASE_DATE")},
		uniqueConstraints = @UniqueConstraint(columnNames = { "CUST_ID", "BASE_DATE", "ACT_CODE", "ACT_SUB_CODE",
		"CUR" }))
public class MgFeeMonth {
	/**
	 * ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	/**
	 * 客戶戶號
	 */
	@Column(name = "CUST_ID", columnDefinition = "CHAR(11)")
	private String custId;

	/**
	 * 結存月份
	 */
	@Column(name = "BASE_DATE", columnDefinition = "CHAR(6)")
	private String baseDate;

	/**
	 * 管理費活動案代碼
	 */
	@Column(name = "ACT_CODE", columnDefinition = "VARCHAR(20)")
	private String actCode;

	/**
	 * 管理費子活動代號
	 */
	@Column(name = "ACT_SUB_CODE", columnDefinition = "VARCHAR(5)")
	private String actSubCode;

	/**
	 * 幣別
	 */
	@Column(name = "CUR", columnDefinition = "CHAR(3)")
	private String cur;

	/**
	 * AUM 管理費
	 */
	@Column(name = "AUM_FEE", columnDefinition = "NUMBER(19,8)")
	private BigDecimal aumFee;

	/**
	 * AUM 管理費餘額
	 */
	@Column(name = "AUM_REMAIN_FEE", columnDefinition = "NUMBER(19,8)")
	private BigDecimal aumRemainFee;

	/**
	 * COST 管理費
	 */
	@Column(name = "COST_FEE", columnDefinition = "NUMBER(19,8)")
	private BigDecimal costFee;

	/**
	 * COST 管理費餘額
	 */
	@Column(name = "COST_REMIAN_FEE", columnDefinition = "NUMBER(19,8)")
	private BigDecimal costRemainFee;

	/**
	 * 月結確認者
	 */
	@Column(name = "SETTLE_USER_ID", columnDefinition = "CHAR(5)")
	private String settleUserId;

	/**
	 * 月結確認日
	 */
	@Column(name = "SETTLE_DATE", columnDefinition = "CHAR(8)")
	private String settleDate;

	/**
	 * 最後異動時間
	 */
	@Column(name = "DTTM", columnDefinition = "DATETIME")
	private Date dttm;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getBaseDate() {
		return baseDate;
	}

	public void setBaseDate(String baseDate) {
		this.baseDate = baseDate;
	}

	public String getActCode() {
		return actCode;
	}

	public void setActCode(String actCode) {
		this.actCode = actCode;
	}

	public String getActSubCode() {
		return actSubCode;
	}

	public void setActSubCode(String actSubCode) {
		this.actSubCode = actSubCode;
	}

	public String getCur() {
		return cur;
	}

	public void setCur(String cur) {
		this.cur = cur;
	}

	public BigDecimal getAumFee() {
		return aumFee;
	}

	public void setAumFee(BigDecimal aumFee) {
		this.aumFee = aumFee;
	}

	public BigDecimal getAumRemainFee() {
		return aumRemainFee;
	}

	public void setAumRemainFee(BigDecimal aumRemainFee) {
		this.aumRemainFee = aumRemainFee;
	}

	public BigDecimal getCostFee() {
		return costFee;
	}

	public void setCostFee(BigDecimal costFee) {
		this.costFee = costFee;
	}

	public BigDecimal getCostRemainFee() {
		return costRemainFee;
	}

	public void setCostRemainFee(BigDecimal costRemainFee) {
		this.costRemainFee = costRemainFee;
	}

	public String getSettleUserId() {
		return settleUserId;
	}

	public void setSettleUserId(String settleUserId) {
		this.settleUserId = settleUserId;
	}

	public String getSettleDate() {
		return settleDate;
	}

	public void setSettleDate(String settleDate) {
		this.settleDate = settleDate;
	}

	public Date getDttm() {
		return dttm;
	}

	public void setDttm(Date dttm) {
		this.dttm = dttm;
	}

}
