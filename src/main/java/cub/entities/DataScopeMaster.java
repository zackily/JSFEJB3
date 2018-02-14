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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author NT48810
 */
@Entity
@Table(name = "DATA_SCOPE_MASTER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DataScopeMaster.findAll", query = "SELECT d FROM DataScopeMaster d")
    , @NamedQuery(name = "DataScopeMaster.findByScopeCode", query = "SELECT d FROM DataScopeMaster d WHERE d.scopeCode = :scopeCode")
    , @NamedQuery(name = "DataScopeMaster.findByClassCode", query = "SELECT d FROM DataScopeMaster d WHERE d.classCode = :classCode")
    , @NamedQuery(name = "DataScopeMaster.findByScopeName", query = "SELECT d FROM DataScopeMaster d WHERE d.scopeName = :scopeName")
    , @NamedQuery(name = "DataScopeMaster.findByLogUserId", query = "SELECT d FROM DataScopeMaster d WHERE d.logUserId = :logUserId")
    , @NamedQuery(name = "DataScopeMaster.findByLogDttm", query = "SELECT d FROM DataScopeMaster d WHERE d.logDttm = :logDttm")})
public class DataScopeMaster implements Serializable {

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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "SCOPE_NAME")
    private String scopeName;
    @Size(max = 20)
    @Column(name = "LOG_USER_ID")
    private String logUserId;
    @Column(name = "LOG_DTTM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date logDttm;
    @Transient
    private String className;

    public DataScopeMaster() {
    }

    public DataScopeMaster(String scopeCode) {
        this.scopeCode = scopeCode;
    }

    public DataScopeMaster(String scopeCode, short classCode, String scopeName) {
        this.scopeCode = scopeCode;
        this.classCode = classCode;
        this.scopeName = scopeName;
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

    
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
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
        if (!(object instanceof DataScopeMaster)) {
            return false;
        }
        DataScopeMaster other = (DataScopeMaster) object;
        if ((this.scopeCode == null && other.scopeCode != null) || (this.scopeCode != null && !this.scopeCode.equals(other.scopeCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cub.entities.DataScopeMaster[ scopeCode=" + scopeCode + " ]";
    }

}
