package cub.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the API_MASTER database table.
 * 
 */
@Entity
@Table(name="API_MASTER")
@NamedQuery(name="ApiMaster.findAll", query="SELECT a FROM ApiMaster a")
public class ApiMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="API_CODE")
	private String apiCode;

	@Column(name="API_DESC")
	private String apiDesc;

	@Column(name="CLASS_CODE")
	private BigDecimal classCode;

	@Temporal(TemporalType.DATE)
	@Column(name="LOG_DTTM")
	private Date logDttm;

	@Column(name="LOG_USER_ID")
	private String logUserId;

	@Column(name="OUTPUT_TR_CODE")
	private String outputTrCode;

	@Column(name="RTN_TYPE")
	private BigDecimal rtnType;

	@Column(name="SYSTEM_CODE")
	private BigDecimal systemCode;

	public ApiMaster() {
	}

	public String getApiCode() {
		return this.apiCode;
	}

	public void setApiCode(String apiCode) {
		this.apiCode = apiCode;
	}

	public String getApiDesc() {
		return this.apiDesc;
	}

	public void setApiDesc(String apiDesc) {
		this.apiDesc = apiDesc;
	}

	public BigDecimal getClassCode() {
		return this.classCode;
	}

	public void setClassCode(BigDecimal classCode) {
		this.classCode = classCode;
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

	public String getOutputTrCode() {
		return this.outputTrCode;
	}

	public void setOutputTrCode(String outputTrCode) {
		this.outputTrCode = outputTrCode;
	}

	public BigDecimal getRtnType() {
		return this.rtnType;
	}

	public void setRtnType(BigDecimal rtnType) {
		this.rtnType = rtnType;
	}

	public BigDecimal getSystemCode() {
		return this.systemCode;
	}

	public void setSystemCode(BigDecimal systemCode) {
		this.systemCode = systemCode;
	}

}