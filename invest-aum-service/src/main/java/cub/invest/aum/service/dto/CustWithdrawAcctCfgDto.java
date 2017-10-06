package cub.invest.aum.service.dto;

import java.util.Date;

/**
 * DTO for PayAcct
 * @author NT80212
 *
 */
public class CustWithdrawAcctCfgDto {
	
	public CustWithdrawAcctCfgDto(){}
	
	public CustWithdrawAcctCfgDto(String custId, String withdrawAcct, String currency){
		this.custId = custId;
		this.withdrawAcct = withdrawAcct;
		this.currency = currency;
	}
	
	/**
	 * ID
	 */
	private long id;
	
	/**
	 * 客戶戶號
	 */
	private String custId;

	/**
	 * 扣款帳號
	 */
	private String withdrawAcct;

	/**
	 * 幣別
	 */
	private String currency;

	/**
	 * 更新日期
	 */
	private Date updateDate;

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

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
