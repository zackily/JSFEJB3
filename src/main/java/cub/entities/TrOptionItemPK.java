package cub.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the TR_OPTION_ITEM database table.
 * 
 */
@Embeddable
public class TrOptionItemPK implements Serializable {
    // default serial version id, required for serializable classes.
    private static final long serialVersionUID = 1L;

    @Column(name = "TR_CODE")
    private String trCode;

    @Column(name = "PARAMETER_NAME")
    private String parameterName;

    @Column(name = "ITEM_CODE")
    private String itemCode;

    public TrOptionItemPK() {
    }

    public TrOptionItemPK(String trCode, String parameterName, String itemCode) {
        super();
        this.trCode = trCode;
        this.parameterName = parameterName;
        this.itemCode = itemCode;
    }

    public String getTrCode() {
        return this.trCode;
    }

    public void setTrCode(String trCode) {
        this.trCode = trCode;
    }

    public String getParameterName() {
        return this.parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public String getItemCode() {
        return this.itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TrOptionItemPK)) {
            return false;
        }
        TrOptionItemPK castOther = (TrOptionItemPK) other;
        return this.trCode.equals(castOther.trCode)
                && this.parameterName.equals(castOther.parameterName)
                && this.itemCode.equals(castOther.itemCode);
    }

    public int hashCode() {
        final int prime = 31;
        int hash = 17;
        hash = hash * prime + this.trCode.hashCode();
        hash = hash * prime + this.parameterName.hashCode();
        hash = hash * prime + this.itemCode.hashCode();

        return hash;
    }
}