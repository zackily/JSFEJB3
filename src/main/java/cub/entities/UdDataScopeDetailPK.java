package cub.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the UD_DATA_SCOPE_DETAIL database table.
 * 
 */
@Embeddable
public class UdDataScopeDetailPK implements Serializable {
    // default serial version id, required for serializable classes.
    private static final long serialVersionUID = 1L;

    @Column(name = "SCOPE_CODE")
    private String scopeCode;

    @Column(name = "SEQ_NO")
    private long seqNo;

    public UdDataScopeDetailPK() {
    }

    public UdDataScopeDetailPK(String scopeCode, long seqNo) {
        super();
        this.scopeCode = scopeCode;
        this.seqNo = seqNo;
    }

    public String getScopeCode() {
        return this.scopeCode;
    }

    public void setScopeCode(String scopeCode) {
        this.scopeCode = scopeCode;
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
        if (!(other instanceof UdDataScopeDetailPK)) {
            return false;
        }
        UdDataScopeDetailPK castOther = (UdDataScopeDetailPK) other;
        return this.scopeCode.equals(castOther.scopeCode)
                && (this.seqNo == castOther.seqNo);
    }

    public int hashCode() {
        final int prime = 31;
        int hash = 17;
        hash = hash * prime + this.scopeCode.hashCode();
        hash = hash * prime + ((int) (this.seqNo ^ (this.seqNo >>> 32)));

        return hash;
    }
}