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
@Table(name = "RD_DATA_COLUMN")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RdDataColumn.findAll", query = "SELECT r FROM RdDataColumn r")
    , @NamedQuery(name = "RdDataColumn.findByClassCode", query = "SELECT r FROM RdDataColumn r WHERE r.rdDataColumnPK.classCode = :classCode")
    , @NamedQuery(name = "RdDataColumn.findByTableName", query = "SELECT r FROM RdDataColumn r WHERE r.rdDataColumnPK.tableName = :tableName")
    , @NamedQuery(name = "RdDataColumn.findByColumnName", query = "SELECT r FROM RdDataColumn r WHERE r.rdDataColumnPK.columnName = :columnName")
    , @NamedQuery(name = "RdDataColumn.findByColumnChnName", query = "SELECT r FROM RdDataColumn r WHERE r.columnChnName = :columnChnName")
    , @NamedQuery(name = "RdDataColumn.findByLogUserId", query = "SELECT r FROM RdDataColumn r WHERE r.logUserId = :logUserId")
    , @NamedQuery(name = "RdDataColumn.findByLogDttm", query = "SELECT r FROM RdDataColumn r WHERE r.logDttm = :logDttm")})
public class RdDataColumn implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RdDataColumnPK rdDataColumnPK;
    @Size(max = 50)
    @Column(name = "COLUMN_CHN_NAME")
    private String columnChnName;
    @Size(max = 20)
    @Column(name = "LOG_USER_ID")
    private String logUserId;
    @Column(name = "LOG_DTTM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date logDttm;

    public RdDataColumn() {
    }

    public RdDataColumn(RdDataColumnPK rdDataColumnPK) {
        this.rdDataColumnPK = rdDataColumnPK;
    }

    public RdDataColumn(short classCode, String tableName, String columnName) {
        this.rdDataColumnPK = new RdDataColumnPK(classCode, tableName, columnName);
    }

    public RdDataColumnPK getRdDataColumnPK() {
        return rdDataColumnPK;
    }

    public void setRdDataColumnPK(RdDataColumnPK rdDataColumnPK) {
        this.rdDataColumnPK = rdDataColumnPK;
    }

    public String getColumnChnName() {
        return columnChnName;
    }

    public void setColumnChnName(String columnChnName) {
        this.columnChnName = columnChnName;
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
        hash += (rdDataColumnPK != null ? rdDataColumnPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RdDataColumn)) {
            return false;
        }
        RdDataColumn other = (RdDataColumn) object;
        if ((this.rdDataColumnPK == null && other.rdDataColumnPK != null) || (this.rdDataColumnPK != null && !this.rdDataColumnPK.equals(other.rdDataColumnPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cub.entities.RdDataColumn[ rdDataColumnPK=" + rdDataColumnPK + " ]";
    }
    
}
