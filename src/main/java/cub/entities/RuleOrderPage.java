package cub.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the RULE_ORDER_PAGE database table.
 * 
 */
@Entity
@Table(name="RULE_ORDER_PAGE")
@NamedQuery(name="RuleOrderPage.findAll", query="SELECT r FROM RuleOrderPage r")
public class RuleOrderPage implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RuleOrderPagePK id;

	@Temporal(TemporalType.DATE)
	@Column(name="LOG_DTTM")
	private Date logDttm;

	@Column(name="LOG_USER_ID")
	private String logUserId;

	public RuleOrderPage() {
	}

	public RuleOrderPagePK getId() {
		return this.id;
	}

	public void setId(RuleOrderPagePK id) {
		this.id = id;
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