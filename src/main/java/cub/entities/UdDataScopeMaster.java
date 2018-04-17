package cub.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * The persistent class for the UD_DATA_SCOPE_MASTER database table.
 * 
 */
@Entity
@Table(name = "UD_DATA_SCOPE_MASTER")
@NamedQuery(name = "UdDataScopeMaster.findAll", query = "SELECT u FROM UdDataScopeMaster u")
public class UdDataScopeMaster implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "SCOPE_CODE")
    private String scopeCode;

    @Column(name = "API_CODE")
    private String apiCode;

    @Column(name = "CLASS_CODE")
    private short classCode;

    @Temporal(TemporalType.DATE)
    @Column(name = "LOG_DTTM")
    private Date logDttm;

    @Column(name = "LOG_USER_ID")
    private String logUserId;

    @Column(name = "SCOPE_NAME")
    private String scopeName;
    @Transient
    private String apiName;
    @Transient
    private String trCode;
    @Transient
    private String trDesc;
    @Transient
    private String className;

    public UdDataScopeMaster() {
    }

    public String getScopeCode() {
        return this.scopeCode;
    }

    public void setScopeCode(String scopeCode) {
        this.scopeCode = scopeCode;
    }

    public String getApiCode() {
        return this.apiCode;
    }

    public void setApiCode(String apiCode) {
        this.apiCode = apiCode;
    }

    public short getClassCode() {
        return this.classCode;
    }

    public void setClassCode(short classCode) {
        this.classCode = classCode;
    }

    public Date getLogDttm() {
        return this.logDttm;
    }

    public void setLogDttm(Date logDttm) {
        this.logDttm = logDttm;
    }

    public String getLogUserId() {
        return this.logUserId;
    }

    public void setLogUserId(String logUserId) {
        this.logUserId = logUserId;
    }

    public String getScopeName() {
        return this.scopeName;
    }

    public void setScopeName(String scopeName) {
        this.scopeName = scopeName;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public String getTrCode() {
        return trCode;
    }

    public void setTrCode(String trCode) {
        this.trCode = trCode;
    }

    public String getTrDesc() {
        return trDesc;
    }

    public void setTrDesc(String trDesc) {
        this.trDesc = trDesc;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

}