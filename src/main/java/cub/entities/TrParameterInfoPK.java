package cub.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the TR_PARAMETER_INFO database table.
 * 
 */
@Embeddable
public class TrParameterInfoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="TR_CODE")
	private String trCode;

	@Column(name="SEQ_NO")
	private long seqNo;

	public TrParameterInfoPK() {
	}
	public String getTrCode() {
		return this.trCode;
	}
	public void setTrCode(String trCode) {
		this.trCode = trCode;
	}
	public long getSeqNo() {
		return this.seqNo;
	}
	public void setSeqNo(long seqNo) {
		this.seqNo = seqNo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TrParameterInfoPK)) {
			return false;
		}
		TrParameterInfoPK castOther = (TrParameterInfoPK)other;
		return 
			this.trCode.equals(castOther.trCode)
			&& (this.seqNo == castOther.seqNo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.trCode.hashCode();
		hash = hash * prime + ((int) (this.seqNo ^ (this.seqNo >>> 32)));
		
		return hash;
	}
}