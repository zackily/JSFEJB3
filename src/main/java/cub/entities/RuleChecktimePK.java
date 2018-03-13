package cub.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the RULE_CHECKTIME database table.
 * 
 */
@Embeddable
public class RuleChecktimePK implements Serializable {
    // default serial version id, required for serializable classes.
    private static final long serialVersionUID = 1L;

    @Column(name = "RULE_NO")
    private String ruleNo;

    @Column(name = "CHECK_TIME")
    private long checkTime;

    public RuleChecktimePK() {
    }

    public RuleChecktimePK(String ruleNo, long checkTime) {
        this.ruleNo = ruleNo;
        this.checkTime = checkTime;
    }

    public String getRuleNo() {
        return this.ruleNo;
    }

    public void setRuleNo(String ruleNo) {
        this.ruleNo = ruleNo;
    }

    public long getCheckTime() {
        return this.checkTime;
    }

    public void setCheckTime(long checkTime) {
        this.checkTime = checkTime;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RuleChecktimePK)) {
            return false;
        }
        RuleChecktimePK castOther = (RuleChecktimePK) other;
        return this.ruleNo.equals(castOther.ruleNo)
                && (this.checkTime == castOther.checkTime);
    }

    public int hashCode() {
        final int prime = 31;
        int hash = 17;
        hash = hash * prime + this.ruleNo.hashCode();
        hash = hash * prime + ((int) (this.checkTime ^ (this.checkTime >>> 32)));

        return hash;
    }
}