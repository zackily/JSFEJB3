package cub.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the TR_SYSTEM_URL database table.
 * 
 */
@Entity
@Table(name="TR_SYSTEM_URL")
@NamedQuery(name="TrSystemUrl.findAll", query="SELECT t FROM TrSystemUrl t")
public class TrSystemUrl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="SYSTEM_CODE")
	private String systemCode;

	@Temporal(TemporalType.DATE)
	@Column(name="LOG_DTTM")
	private Date logDttm;

	@Column(name="LOG_USER_ID")
	private String logUserId;

	@Column(name="SYSTEM_NAME")
	private String systemName;

	@Column(name="SYSTEM_URL")
	private String systemUrl;

	public TrSystemUrl() {
	}

	public String getSystemCode() {
		return this.systemCode;
	}

	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
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

	public String getSystemName() {
		return this.systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public String getSystemUrl() {
		return this.systemUrl;
	}

	public void setSystemUrl(String systemUrl) {
		this.systemUrl = systemUrl;
	}

}