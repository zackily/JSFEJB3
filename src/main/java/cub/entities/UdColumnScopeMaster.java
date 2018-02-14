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
 * @author NT48810
 */
@Entity
@Table(name = "UD_COLUMN_SCOPE_MASTER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UdColumnScopeMaster.findAll", query = "SELECT u FROM UdColumnScopeMaster u")
    , @NamedQuery(name = "UdColumnScopeMaster.findByUdColumnCode", query = "SELECT u FROM UdColumnScopeMaster u WHERE u.udColumnCode = :udColumnCode")
    , @NamedQuery(name = "UdColumnScopeMaster.findByUdColumnName", query = "SELECT u FROM UdColumnScopeMaster u WHERE u.udColumnName = :udColumnName")
    , @NamedQuery(name = "UdColumnScopeMaster.findByClassCode", query = "SELECT u FROM UdColumnScopeMaster u WHERE u.classCode = :classCode")
    , @NamedQuery(name = "UdColumnScopeMaster.findByTableName", query = "SELECT u FROM UdColumnScopeMaster u WHERE u.tableName = :tableName")
    , @NamedQuery(name = "UdColumnScopeMaster.findByColumnName", query = "SELECT u FROM UdColumnScopeMaster u WHERE u.columnName = :columnName")
    , @NamedQuery(name = "UdColumnScopeMaster.findByLogUserId", query = "SELECT u FROM UdColumnScopeMaster u WHERE u.logUserId = :logUserId")
    , @NamedQuery(name = "UdColumnScopeMaster.findByLogDttm", query = "SELECT u FROM UdColumnScopeMaster u WHERE u.logDttm = :logDttm")})
public class UdColumnScopeMaster implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "UD_COLUMN_CODE")
    private String udColumnCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "UD_COLUMN_NAME")
    private String udColumnName;
    @Column(name = "CLASS_CODE")
    private Short classCode;
    @Size(max = 50)
    @Column(name = "TABLE_NAME")
    private String tableName;
    @Size(max = 50)
    @Column(name = "COLUMN_NAME")
    private String columnName;
    @Size(max = 20)
    @Column(name = "LOG_USER_ID")
    private String logUserId;
    @Column(name = "LOG_DTTM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date logDttm;

    public UdColumnScopeMaster() {
    }

    public UdColumnScopeMaster(String udColumnCode) {
        this.udColumnCode = udColumnCode;
    }

    public UdColumnScopeMaster(String udColumnCode, String udColumnName) {
        this.udColumnCode = udColumnCode;
        this.udColumnName = udColumnName;
    }

    public String getUdColumnCode() {
        return udColumnCode;
    }

    public void setUdColumnCode(String udColumnCode) {
        this.udColumnCode = udColumnCode;
    }

    public String getUdColumnName() {
        return udColumnName;
    }

    public void setUdColumnName(String udColumnName) {
        this.udColumnName = udColumnName;
    }

    public Short getClassCode() {
        return classCode;
    }

    public void setClassCode(Short classCode) {
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
        hash += (udColumnCode != null ? udColumnCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UdColumnScopeMaster)) {
            return false;
        }
        UdColumnScopeMaster other = (UdColumnScopeMaster) object;
        if ((this.udColumnCode == null && other.udColumnCode != null) || (this.udColumnCode != null && !this.udColumnCode.equals(other.udColumnCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cub.entities.UdColumnScopeMaster[ udColumnCode=" + udColumnCode + " ]";
    }
    
}
