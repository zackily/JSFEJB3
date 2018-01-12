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
import javax.validation.constraints.Size;

/**
 *
 * @author NT48810
 */
@Embeddable
public class RdDataColumnOptionPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "CLASS_CODE")
    private short classCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "TABLE_NAME")
    private String tableName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "COLUMN_NAME")
    private String columnName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "OPTION_CODE")
    private short optionCode;

    public RdDataColumnOptionPK() {
    }

    public RdDataColumnOptionPK(short classCode, String tableName, String columnName, short optionCode) {
        this.classCode = classCode;
        this.tableName = tableName;
        this.columnName = columnName;
        this.optionCode = optionCode;
    }

    public short getClassCode() {
        return classCode;
    }

    public void setClassCode(short classCode) {
        this.classCode = classCode;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public short getOptionCode() {
        return optionCode;
    }

    public void setOptionCode(short optionCode) {
        this.optionCode = optionCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) classCode;
        hash += (tableName != null ? tableName.hashCode() : 0);
        hash += (columnName != null ? columnName.hashCode() : 0);
        hash += (int) optionCode;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RdDataColumnOptionPK)) {
            return false;
        }
        RdDataColumnOptionPK other = (RdDataColumnOptionPK) object;
        if (this.classCode != other.classCode) {
            return false;
        }
        if ((this.tableName == null && other.tableName != null) || (this.tableName != null && !this.tableName.equals(other.tableName))) {
            return false;
        }
        if ((this.columnName == null && other.columnName != null) || (this.columnName != null && !this.columnName.equals(other.columnName))) {
            return false;
        }
        if (this.optionCode != other.optionCode) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cub.entities.RdDataColumnOptionPK[ classCode=" + classCode + ", tableName=" + tableName + ", columnName=" + columnName + ", optionCode=" + optionCode + " ]";
    }
    
}
