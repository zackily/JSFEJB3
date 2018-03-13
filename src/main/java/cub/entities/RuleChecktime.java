package cub.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the RULE_CHECKTIME database table.
 * 
 */
@Entity
@Table(name = "RULE_CHECKTIME")
@NamedQuery(name = "RuleChecktime.findAll", query = "SELECT r FROM RuleChecktime r")
public class RuleChecktime implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private RuleChecktimePK ruleChecktimePK;

    @Temporal(TemporalType.DATE)
    @Column(name = "LOG_DTTM")
    private Date logDttm;

    @Column(name = "LOG_USER_ID")
    private String logUserId;

    public RuleChecktime() {
    }

    public RuleChecktimePK getRuleChecktimePK() {
        return ruleChecktimePK;
    }

    public void setRuleChecktimePK(RuleChecktimePK ruleChecktimePK) {
        this.ruleChecktimePK = ruleChecktimePK;
    }

    public Date getLogDttm() {
        return this.logDttm;
    }

    public void setLogDttm(Date logDttm) {
        this.logDttm = logDttm;
    }

    public String getLogUserId() {
        return this.logUserId;
    }

    public void setLogUserId(String logUserId) {
        this.logUserId = logUserId;
    }

}