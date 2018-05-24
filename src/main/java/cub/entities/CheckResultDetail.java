package cub.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the CHECK_RESULT_DETAIL database table.
 * 
 */
@Entity
@Table(name="CHECK_RESULT_DETAIL")
@NamedQuery(name="CheckResultDetail.findAll", query="SELECT c FROM CheckResultDetail c")
public class CheckResultDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CheckResultDetailPK id;

	private BigDecimal dividend;

	private BigDecimal divisor;

	@Temporal(TemporalType.DATE)
	@Column(name="LOG_DTTM")
	private Date logDttm;

	private BigDecimal ratio;

	@Column(name="RESULT_CODE")
	private BigDecimal resultCode;

	@Column(name="RTN_MESSAGE")
	private String rtnMessage;

	public CheckResultDetail() {
	}

	public CheckResultDetailPK getId() {
		return this.id;
	}

	public void setId(CheckResultDetailPK id) {
		this.id = id;
	}

	public BigDecimal getDividend() {
		return this.dividend;
	}

	public void setDividend(BigDecimal dividend) {
		this.dividend = dividend;
	}

	public BigDecimal getDivisor() {
		return this.divisor;
	}

	public void setDivisor(BigDecimal divisor) {
		this.divisor = divisor;
	}

	public Date getLogDttm() {
		return this.logDttm;
	}

	public void setLogDttm(Date logDttm) {
		this.logDttm = logDttm;
	}

	public BigDecimal getRatio() {
		return this.ratio;
	}

	public void setRatio(BigDecimal ratio) {
		this.ratio = ratio;
	}

	public BigDecimal getResultCode() {
		return this.resultCode;
	}

	public void setResultCode(BigDecimal resultCode) {
		this.resultCode = resultCode;
	}

	public String getRtnMessage() {
		return this.rtnMessage;
	}

	public void setRtnMessage(String rtnMessage) {
		this.rtnMessage = rtnMessage;
	}

}