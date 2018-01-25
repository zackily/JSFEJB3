/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cub.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author NT48810
 */
@Entity
@Table(name = "DATA_SCOPE_DETAIL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DataScopeDetail.findAll", query = "SELECT d FROM DataScopeDetail d")
    , @NamedQuery(name = "DataScopeDetail.findByScopeCode", query = "SELECT d FROM DataScopeDetail d WHERE d.dataScopeDetailPK.scopeCode = :scopeCode")
    , @NamedQuery(name = "DataScopeDetail.findBySeqNo", query = "SELECT d FROM DataScopeDetail d WHERE d.dataScopeDetailPK.seqNo = :seqNo")
    , @NamedQuery(name = "DataScopeDetail.findByLogic", query = "SELECT d FROM DataScopeDetail d WHERE d.logic = :logic")
    , @NamedQuery(name = "DataScopeDetail.findByLeftBracket", query = "SELECT d FROM DataScopeDetail d WHERE d.leftBracket = :leftBracket")
    , @NamedQuery(name = "DataScopeDetail.findByTableName", query = "SELECT d FROM DataScopeDetail d WHERE d.tableName = :tableName")
    , @NamedQuery(name = "DataScopeDetail.findByColumnName", query = "SELECT d FROM DataScopeDetail d WHERE d.columnName = :columnName")
    , @NamedQuery(name = "DataScopeDetail.findByOpCode", query = "SELECT d FROM DataScopeDetail d WHERE d.opCode = :opCode")
    , @NamedQuery(name = "DataScopeDetail.findByOpValue", query = "SELECT d FROM DataScopeDetail d WHERE d.opValue = :opValue")
    , @NamedQuery(name = "DataScopeDetail.findByRightBracket", query = "SELECT d FROM DataScopeDetail d WHERE d.rightBracket = :rightBracket")})
public class DataScopeDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DataScopeDetailPK dataScopeDetailPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "LOGIC")
    private String logic;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "LEFT_BRACKET")
    private String leftBracket;
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
    @Size(min = 1, max = 5)
    @Column(name = "OP_CODE")
    private String opCode;
    @Size(max = 20)
    @Column(name = "OP_VALUE")
    private String opValue;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "RIGHT_BRACKET")
    private String rightBracket;
    @Transient
    private String classCode;
    @Transient
    private String column;

    public DataScopeDetail() {
    }

    public DataScopeDetail(DataScopeDetailPK dataScopeDetailPK) {
        this.dataScopeDetailPK = dataScopeDetailPK;
    }

    public DataScopeDetail(DataScopeDetailPK dataScopeDetailPK, String logic, String leftBracket, String tableName, String columnName, String opCode, String rightBracket) {
        this.dataScopeDetailPK = dataScopeDetailPK;
        this.logic = logic;
        this.leftBracket = leftBracket;
        this.tableName = tableName;
        this.columnName = columnName;
        this.opCode = opCode;
        this.rightBracket = rightBracket;
    }

    public DataScopeDetail(String scopeCode, short seqNo) {
        this.dataScopeDetailPK = new DataScopeDetailPK(scopeCode, seqNo);
    }

    public DataScopeDetailPK getDataScopeDetailPK() {
        return dataScopeDetailPK;
    }

    public void setDataScopeDetailPK(DataScopeDetailPK dataScopeDetailPK) {
        this.dataScopeDetailPK = dataScopeDetailPK;
    }

    public String getLogic() {
        return logic;
    }

    public void setLogic(String logic) {
        this.logic = logic;
    }

    public String getLeftBracket() {
        return leftBracket;
    }

    public void setLeftBracket(String leftBracket) {
        this.leftBracket = leftBracket;
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

    public String getOpCode() {
        return opCode;
    }

    public void setOpCode(String opCode) {
        this.opCode = opCode;
    }

    public String getOpValue() {
        return opValue;
    }

    public void setOpValue(String opValue) {
        this.opValue = opValue;
    }

    public String getRightBracket() {
        return rightBracket;
    }

    public void setRightBracket(String rightBracket) {
        this.rightBracket = rightBracket;
    }

    @Transient
    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    @Transient
    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dataScopeDetailPK != null ? dataScopeDetailPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DataScopeDetail)) {
            return false;
        }
        DataScopeDetail other = (DataScopeDetail) object;
        if ((this.dataScopeDetailPK == null && other.dataScopeDetailPK != null) || (this.dataScopeDetailPK != null && !this.dataScopeDetailPK.equals(other.dataScopeDetailPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cub.entities.DataScopeDetail[ dataScopeDetailPK=" + dataScopeDetailPK + " ]";
    }

}
