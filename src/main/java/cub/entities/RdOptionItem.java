/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package cub.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author NT48810
 */
@Entity
@Table(name = "RD_OPTION_ITEM")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "RdOptionItem.findAll", query = "SELECT r FROM RdOptionItem r"),
        @NamedQuery(name = "RdOptionItem.findByClassCode", query = "SELECT r FROM RdOptionItem r WHERE r.rdOptionItemPK.classCode = :classCode"),
        @NamedQuery(name = "RdOptionItem.findByClassName", query = "SELECT r FROM RdOptionItem r WHERE r.className = :className"),
        @NamedQuery(name = "RdOptionItem.findByItemCode", query = "SELECT r FROM RdOptionItem r WHERE r.rdOptionItemPK.itemCode = :itemCode"),
        @NamedQuery(name = "RdOptionItem.findByItemName", query = "SELECT r FROM RdOptionItem r WHERE r.itemName = :itemName") })
public class RdOptionItem implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RdOptionItemPK rdOptionItemPK;
    @Size(max = 20)
    @Column(name = "CLASS_NAME")
    private String className;
    @Size(max = 50)
    @Column(name = "ITEM_NAME")
    private String itemName;
    @Size(max = 20)
    @Column(name = "COLUMN_NAME")
    private String columnName;

    public RdOptionItem() {
    }

    public RdOptionItem(RdOptionItemPK rdOptionItemPK) {
        this.rdOptionItemPK = rdOptionItemPK;
    }

    public RdOptionItem(short classCode, short itemCode) {
        this.rdOptionItemPK = new RdOptionItemPK(classCode, itemCode);
    }

    public RdOptionItemPK getRdOptionItemPK() {
        return rdOptionItemPK;
    }

    public void setRdOptionItemPK(RdOptionItemPK rdOptionItemPK) {
        this.rdOptionItemPK = rdOptionItemPK;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rdOptionItemPK != null ? rdOptionItemPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RdOptionItem)) {
            return false;
        }
        RdOptionItem other = (RdOptionItem) object;
        if ((this.rdOptionItemPK == null && other.rdOptionItemPK != null)
                || (this.rdOptionItemPK != null && !this.rdOptionItemPK.equals(other.rdOptionItemPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cub.entities.RdOptionItem[ rdOptionItemPK=" + rdOptionItemPK + " ]";
    }

}
