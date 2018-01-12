/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cub.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author NT48810
 */
@Entity
@Table(name = "RD_DATA_COLUMN_OPTION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RdDataColumnOption.findAll", query = "SELECT r FROM RdDataColumnOption r")
    , @NamedQuery(name = "RdDataColumnOption.findByClassCode", query = "SELECT r FROM RdDataColumnOption r WHERE r.rdDataColumnOptionPK.classCode = :classCode")
    , @NamedQuery(name = "RdDataColumnOption.findByTableName", query = "SELECT r FROM RdDataColumnOption r WHERE r.rdDataColumnOptionPK.tableName = :tableName")
    , @NamedQuery(name = "RdDataColumnOption.findByColumnName", query = "SELECT r FROM RdDataColumnOption r WHERE r.rdDataColumnOptionPK.columnName = :columnName")
    , @NamedQuery(name = "RdDataColumnOption.findByOptionCode", query = "SELECT r FROM RdDataColumnOption r WHERE r.rdDataColumnOptionPK.optionCode = :optionCode")
    , @NamedQuery(name = "RdDataColumnOption.findByOptionName", query = "SELECT r FROM RdDataColumnOption r WHERE r.optionName = :optionName")
    , @NamedQuery(name = "RdDataColumnOption.findByLogUserId", query = "SELECT r FROM RdDataColumnOption r WHERE r.logUserId = :logUserId")
    , @NamedQuery(name = "RdDataColumnOption.findByLogDttm", query = "SELECT r FROM RdDataColumnOption r WHERE r.logDttm = :logDttm")})
public class RdDataColumnOption implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RdDataColumnOptionPK rdDataColumnOptionPK;
    @Size(max = 20)
    @Column(name = "OPTION_NAME")
    private String optionName;
    @Size(max = 20)
    @Column(name = "LOG_USER_ID")
    private String logUserId;
    @Column(name = "LOG_DTTM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date logDttm;

    public RdDataColumnOption() {
    }

    public RdDataColumnOption(RdDataColumnOptionPK rdDataColumnOptionPK) {
        this.rdDataColumnOptionPK = rdDataColumnOptionPK;
    }

    public RdDataColumnOption(short classCode, String tableName, String columnName, short optionCode) {
        this.rdDataColumnOptionPK = new RdDataColumnOptionPK(classCode, tableName, columnName, optionCode);
    }

    public RdDataColumnOptionPK getRdDataColumnOptionPK() {
        return rdDataColumnOptionPK;
    }

    public void setRdDataColumnOptionPK(RdDataColumnOptionPK rdDataColumnOptionPK) {
        this.rdDataColumnOptionPK = rdDataColumnOptionPK;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
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
        hash += (rdDataColumnOptionPK != null ? rdDataColumnOptionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RdDataColumnOption)) {
            return false;
        }
        RdDataColumnOption other = (RdDataColumnOption) object;
        if ((this.rdDataColumnOptionPK == null && other.rdDataColumnOptionPK != null) || (this.rdDataColumnOptionPK != null && !this.rdDataColumnOptionPK.equals(other.rdDataColumnOptionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cub.entities.RdDataColumnOption[ rdDataColumnOptionPK=" + rdDataColumnOptionPK + " ]";
    }
    
}
