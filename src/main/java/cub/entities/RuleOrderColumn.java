package cub.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the RULE_ORDER_COLUMN database table.
 * 
 */
@Entity
@Table(name="RULE_ORDER_COLUMN")
@NamedQuery(name="RuleOrderColumn.findAll", query="SELECT r FROM RuleOrderColumn r")
public class RuleOrderColumn implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RuleOrderColumnPK id;

	@Temporal(TemporalType.DATE)
	@Column(name="LOG_DTTM")
	private Date logDttm;

	@Column(name="LOG_USER_ID")
	private String logUserId;

	public RuleOrderColumn() {
	}

	public RuleOrderColumnPK getId() {
		return this.id;
	}

	public void setId(RuleOrderColumnPK id) {
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