package cub.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the RULE_ORDER_PAGE database table.
 * 
 */
@Embeddable
public class RuleOrderPagePK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "RULE_NO")
	private String ruleNo;

	@Column(name = "ORDER_PAGE")
	private long orderPage;

	public RuleOrderPagePK(String ruleNo, long orderPage) {
		super();
		this.ruleNo = ruleNo;
		this.orderPage = orderPage;
	}

	public RuleOrderPagePK() {
	}

	public String getRuleNo() {
		return this.ruleNo;
	}

	public void setRuleNo(String ruleNo) {
		this.ruleNo = ruleNo;
	}

	public long getOrderPage() {
		return this.orderPage;
	}

	public void setOrderPage(long orderPage) {
		this.orderPage = orderPage;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RuleOrderPagePK)) {
			return false;
		}
		RuleOrderPagePK castOther = (RuleOrderPagePK) other;
		return this.ruleNo.equals(castOther.ruleNo) && (this.orderPage == castOther.orderPage);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.ruleNo.hashCode();
		hash = hash * prime + ((int) (this.orderPage ^ (this.orderPage >>> 32)));

		return hash;
	}
}