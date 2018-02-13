/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cub.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author F123669
 */
@Entity
@Table(name = "UD_DATA_SCOPE_MASTER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UdDataScopeMaster.findAll", query = "SELECT u FROM UdDataScopeMaster u")
    , @NamedQuery(name = "UdDataScopeMaster.findByScopeCode", query = "SELECT u FROM UdDataScopeMaster u WHERE u.scopeCode = :scopeCode")
    , @NamedQuery(name = "UdDataScopeMaster.findByClassCode", query = "SELECT u FROM UdDataScopeMaster u WHERE u.classCode = :classCode")
    , @NamedQuery(name = "UdDataScopeMaster.findByScopeName", query = "SELECT u FROM UdDataScopeMaster u WHERE u.scopeName = :scopeName")
    , @NamedQuery(name = "UdDataScopeMaster.findByMethodName", query = "SELECT u FROM UdDataScopeMaster u WHERE u.methodName = :methodName")
    , @NamedQuery(name = "UdDataScopeMaster.findByTableName", query = "SELECT u FROM UdDataScopeMaster u WHERE u.tableName = :tableName")
    , @NamedQuery(name = "UdDataScopeMaster.findByColumnName", query = "SELECT u FROM UdDataScopeMaster u WHERE u.columnName = :columnName")
    , @NamedQuery(name = "UdDataScopeMaster.findByLogUserId", query = "SELECT u FROM UdDataScopeMaster u WHERE u.logUserId = :logUserId")
    , @NamedQuery(name = "UdDataScopeMaster.findByLogDttm", query = "SELECT u FROM UdDataScopeMaster u WHERE u.logDttm = :logDttm")})
public class UdDataScopeMaster implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "SCOPE_CODE")
    private String scopeCode;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CLASS_CODE")
    private short classCode;
    @Size(max = 100)
    @Column(name = "SCOPE_NAME")
    private String scopeName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "METHOD_NAME")
    private String methodName;
    @Size(max = 50)
    @Column(name = "TABLE_NAME")
    private String tableName;
    @Size(max = 50)
    @Column(name = "COLUMN_NAME")
    private String columnName;
    @Size(max = 50)
    @Column(name = "LOG_USER_ID")
    private String logUserId;
    @Column(name = "LOG_DTTM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date logDttm;

    public UdDataScopeMaster() {
    }

    public UdDataScopeMaster(String scopeCode) {
        this.scopeCode = scopeCode;
    }

    public UdDataScopeMaster(String scopeCode, short classCode, String methodName) {
        this.scopeCode = scopeCode;
        this.classCode = classCode;
        this.methodName = methodName;
    }

    public String getScopeCode() {
        return scopeCode;
    }

    public void setScopeCode(String scopeCode) {
        this.scopeCode = scopeCode;
    }

    public short getClassCode() {
        return classCode;
    }

    public void setClassCode(short classCode) {
        this.classCode = classCode;
    }

    public String getScopeName() {
        return scopeName;
    }

    public void setScopeName(String scopeName) {
        this.scopeName = scopeName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
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

    public String getLogUserId() {
        return logUserId;
    }

    public void setLogUserId(String logUserId) {
        this.logUserId = logUserId;
    }

    public Date getLogDttm() {
        return logDttm;
    }

    public void setLogDttm(Date logDttm) {
        this.logDttm = logDttm;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (scopeCode != null ? scopeCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UdDataScopeMaster)) {
            return false;
        }
        UdDataScopeMaster other = (UdDataScopeMaster) object;
        if ((this.scopeCode == null && other.scopeCode != null) || (this.scopeCode != null && !this.scopeCode.equals(other.scopeCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cub.entities.UdDataScopeMaster[ scopeCode=" + scopeCode + " ]";
    }
    
}
