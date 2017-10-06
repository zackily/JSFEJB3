package cub.invest.aum.service.entity;

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
@Table(name = "CUST_WITHDRAW_ACCT_CFG",
		indexes = {@Index(name = "idx_unique_key", columnList = "CUST_ID, WITHDRAW_ACCT, CUR"),
				@Index(name = "idx_updateDttm", columnList = "UPDATE_DTTM")},
		uniqueConstraints = @UniqueConstraint(columnNames = { "CUST_ID", "WITHDRAW_ACCT", "CUR" }))
public class CustWithdrawAcctCfg {
	/**
	 * ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	/**
	 * 客戶戶號
	 */
	@Column(name = "CUST_ID", columnDefinition = "VARCHAR(11)")
	private String custId;

	/**
	 * 扣款帳號
	 */
	@Column(name = "WITHDRAW_ACCT", columnDefinition = "VARCHAR(12)")
	private String withdrawAcct;

	/**
	 * 幣別
	 */
	@Column(name = "CUR", columnDefinition = "CHAR(3)")
	private String currency;

	/**
	 * 更新日期
	 */
	@Column(name = "UPDATE_DTTM", columnDefinition = "DATETIME")
	private Date updateDttm;
	
	/**
	 * 作業人員
	 */
	@Column(name = "USER_ID", columnDefinition = "char(5)")
	private String userId;

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

	public String getWithdrawAcct() {
		return withdrawAcct;
	}

	public void setWithdrawAcct(String withdrawAcct) {
		this.withdrawAcct = withdrawAcct;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Date getUpdateDttm() {
		return updateDttm;
	}

	public void setUpdateDttm(Date updateDttm) {
		this.updateDttm = updateDttm;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
