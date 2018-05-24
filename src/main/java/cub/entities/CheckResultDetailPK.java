package cub.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the CHECK_RESULT_DETAIL database table.
 * 
 */
@Embeddable
public class CheckResultDetailPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ORDER_NO")
	private String orderNo;

	@Column(name="RULE_NO")
	private String ruleNo;

	public CheckResultDetailPK(String orderNo, String ruleNo) {
		super();
		this.orderNo = orderNo;
		this.ruleNo = ruleNo;
	}
	public CheckResultDetailPK() {
	}
	public String getOrderNo() {
		return this.orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getRuleNo() {
		return this.ruleNo;
	}
	public void setRuleNo(String ruleNo) {
		this.ruleNo = ruleNo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CheckResultDetailPK)) {
			return false;
		}
		CheckResultDetailPK castOther = (CheckResultDetailPK)other;
		return 
			this.orderNo.equals(castOther.orderNo)
			&& this.ruleNo.equals(castOther.ruleNo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.orderNo.hashCode();
		hash = hash * prime + this.ruleNo.hashCode();
		
		return hash;
	}
}