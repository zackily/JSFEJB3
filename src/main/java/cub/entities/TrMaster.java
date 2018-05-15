package cub.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * The persistent class for the TR_MASTER database table.
 * 
 */
@Entity
@Table(name = "TR_MASTER")
@NamedQuery(name = "TrMaster.findAll", query = "SELECT t FROM TrMaster t")
public class TrMaster implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "TR_CODE")
    private String trCode;

    @Column(name = "SYSTEM_CODE")
    private String systemCode;

    @Column(name = "TR_DESC")
    private String trDesc;

    @Transient
    private String systemName;

    @Transient
    private String systemUrl;

    public TrMaster() {
    }

    public String getTrCode() {
        return this.trCode;
    }

    public void setTrCode(String trCode) {
        this.trCode = trCode;
    }

    public String getSystemCode() {
        return this.systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

    public String getTrDesc() {
        return this.trDesc;
    }

    public void setTrDesc(String trDesc) {
        this.trDesc = trDesc;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getSystemUrl() {
        return systemUrl;
    }

    public void setSystemUrl(String systemUrl) {
        this.systemUrl = systemUrl;
    }

}