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

    @Column(name = "CHECK_PAGE")
    private long checkPage;

    public RuleOrderPagePK() {
    }

    public RuleOrderPagePK(String ruleNo, long checkPage) {
        super();
        this.ruleNo = ruleNo;
        this.checkPage = checkPage;
    }

    public String getRuleNo() {
        return this.ruleNo;
    }

    public void setRuleNo(String ruleNo) {
        this.ruleNo = ruleNo;
    }

    public long getCheckPage() {
        return this.checkPage;
    }

    public void setCheckPage(long checkPage) {
        this.checkPage = checkPage;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RuleOrderPagePK)) {
            return false;
        }
        RuleOrderPagePK castOther = (RuleOrderPagePK) other;
        return this.ruleNo.equals(castOther.ruleNo)
                && (this.checkPage == castOther.checkPage);
    }

    public int hashCode() {
        final int prime = 31;
        int hash = 17;
        hash = hash * prime + this.ruleNo.hashCode();
        hash = hash * prime + ((int) (this.checkPage ^ (this.checkPage >>> 32)));

        return hash;
    }
}