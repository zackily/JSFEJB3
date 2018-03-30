/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package cub.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author NT48810
 */
@Entity
@Table(name = "RD_DATA_CLASS")
public class RdDataClass implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CLASS_CODE")
    private Short classCode;
    @Size(max = 20)
    @Column(name = "CLASS_NAME")
    private String className;
    @Size(max = 20)
    @Column(name = "DB_NAME")
    private String dbName;
    @Size(max = 20)
    @Column(name = "LOG_USER_ID")
    private String logUserId;
    @Column(name = "LOG_DTTM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date logDttm;

    public RdDataClass() {
    }

    public RdDataClass(Short classCode) {
        this.classCode = classCode;
    }

    public Short getClassCode() {
        return classCode;
    }

    public void setClassCode(Short classCode) {
        this.classCode = classCode;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
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
        hash += (classCode != null ? classCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RdDataClass)) {
            return false;
        }
        RdDataClass other = (RdDataClass) object;
        if ((this.classCode == null && other.classCode != null)
                || (this.classCode != null && !this.classCode.equals(other.classCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cub.entities.RdDataClass[ classCode=" + classCode + " ]";
    }

}
