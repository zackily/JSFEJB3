package cub.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the RULE_ORDER_COLUMN database table.
 * 
 */
@Embeddable
public class RuleOrderColumnPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "RULE_NO")
	private String ruleNo;

	@Column(name = "ORDER_COLUMN")
	private long orderColumn;

	public RuleOrderColumnPK(String ruleNo, long orderColumn) {
		super();
		this.ruleNo = ruleNo;
		this.orderColumn = orderColumn;
	}

	public RuleOrderColumnPK() {
	}

	public String getRuleNo() {
		return this.ruleNo;
	}

	public void setRuleNo(String ruleNo) {
		this.ruleNo = ruleNo;
	}

	public long getOrderColumn() {
		return this.orderColumn;
	}

	public void setOrderColumn(long orderColumn) {
		this.orderColumn = orderColumn;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RuleOrderColumnPK)) {
			return false;
		}
		RuleOrderColumnPK castOther = (RuleOrderColumnPK) other;
		return this.ruleNo.equals(castOther.ruleNo) && (this.orderColumn == castOther.orderColumn);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.ruleNo.hashCode();
		hash = hash * prime + ((int) (this.orderColumn ^ (this.orderColumn >>> 32)));

		return hash;
	}
}