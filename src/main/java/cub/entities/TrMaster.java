package cub.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TR_MASTER database table.
 * 
 */
@Entity
@Table(name="TR_MASTER")
@NamedQuery(name="TrMaster.findAll", query="SELECT t FROM TrMaster t")
public class TrMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="TR_CODE")
	private String trCode;

	@Column(name="TR_DESC")
	private String trDesc;

	public TrMaster() {
	}

	public String getTrCode() {
		return this.trCode;
	}

	public void setTrCode(String trCode) {
		this.trCode = trCode;
	}

	public String getTrDesc() {
		return this.trDesc;
	}

	public void setTrDesc(String trDesc) {
		this.trDesc = trDesc;
	}

}