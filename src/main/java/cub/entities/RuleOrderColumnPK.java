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

    @Column(name = "CHECK_COLUMN")
    private long checkColumn;

    public RuleOrderColumnPK() {
    }

    public RuleOrderColumnPK(String ruleNo, long checkColumn) {
        super();
        this.ruleNo = ruleNo;
        this.checkColumn = checkColumn;
    }

    public String getRuleNo() {
        return this.ruleNo;
    }

    public void setRuleNo(String ruleNo) {
        this.ruleNo = ruleNo;
    }

    public long getCheckColumn() {
        return this.checkColumn;
    }

    public void setCheckColumn(long checkColumn) {
        this.checkColumn = checkColumn;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RuleOrderColumnPK)) {
            return false;
        }
        RuleOrderColumnPK castOther = (RuleOrderColumnPK) other;
        return this.ruleNo.equals(castOther.ruleNo)
                && (this.checkColumn == castOther.checkColumn);
    }

    public int hashCode() {
        final int prime = 31;
        int hash = 17;
        hash = hash * prime + this.ruleNo.hashCode();
        hash = hash * prime + ((int) (this.checkColumn ^ (this.checkColumn >>> 32)));

        return hash;
    }
}