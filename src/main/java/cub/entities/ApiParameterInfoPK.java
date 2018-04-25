package cub.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the API_PARAMETER_INFO database table.
 * 
 */
@Embeddable
public class ApiParameterInfoPK implements Serializable {
    // default serial version id, required for serializable classes.
    private static final long serialVersionUID = 1L;

    @Column(name = "API_CODE")
    private String apiCode;

    @Column(name = "SEQ_NO")
    private long seqNo;

    public ApiParameterInfoPK() {
    }

    public ApiParameterInfoPK(String apiCode, long seqNo) {
        super();
        this.apiCode = apiCode;
        this.seqNo = seqNo;
    }

    public String getApiCode() {
        return this.apiCode;
    }

    public void setApiCode(String apiCode) {
        this.apiCode = apiCode;
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
        if (!(other instanceof ApiParameterInfoPK)) {
            return false;
        }
        ApiParameterInfoPK castOther = (ApiParameterInfoPK) other;
        return this.apiCode.equals(castOther.apiCode)
                && (this.seqNo == castOther.seqNo);
    }

    public int hashCode() {
        final int prime = 31;
        int hash = 17;
        hash = hash * prime + this.apiCode.hashCode();
        hash = hash * prime + ((int) (this.seqNo ^ (this.seqNo >>> 32)));

        return hash;
    }
}