/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cub.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author NT48810
 */
@Embeddable
public class RdOptionItemPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "CLASS_CODE")
    private short classCode;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ITEM_CODE")
    private short itemCode;

    public RdOptionItemPK() {
    }

    public RdOptionItemPK(short classCode, short itemCode) {
        this.classCode = classCode;
        this.itemCode = itemCode;
    }

    public short getClassCode() {
        return classCode;
    }

    public void setClassCode(short classCode) {
        this.classCode = classCode;
    }

    public short getItemCode() {
        return itemCode;
    }

    public void setItemCode(short itemCode) {
        this.itemCode = itemCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) classCode;
        hash += (int) itemCode;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RdOptionItemPK)) {
            return false;
        }
        RdOptionItemPK other = (RdOptionItemPK) object;
        if (this.classCode != other.classCode) {
            return false;
        }
        if (this.itemCode != other.itemCode) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cub.entities.RdOptionItemPK[ classCode=" + classCode + ", itemCode=" + itemCode + " ]";
    }
    
}
