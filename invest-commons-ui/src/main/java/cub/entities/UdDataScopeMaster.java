/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cub.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author NT48810
 */
@Entity
@Table(name = "UD_DATA_SCOPE_MASTER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UdDataScopeMaster.findAll", query = "SELECT u FROM UdDataScopeMaster u")
    , @NamedQuery(name = "UdDataScopeMaster.findByScopeCode", query = "SELECT u FROM UdDataScopeMaster u WHERE u.scopeCode = :scopeCode")
    , @NamedQuery(name = "UdDataScopeMaster.findByClassCode", query = "SELECT u FROM UdDataScopeMaster u WHERE u.classCode = :classCode")
    , @NamedQuery(name = "UdDataScopeMaster.findByScopeName", query = "SELECT u FROM UdDataScopeMaster u WHERE u.scopeName = :scopeName")
    , @NamedQuery(name = "UdDataScopeMaster.findByProcedureName", query = "SELECT u FROM UdDataScopeMaster u WHERE u.procedureName = :procedureName")
    , @NamedQuery(name = "UdDataScopeMaster.findByValueDesc", query = "SELECT u FROM UdDataScopeMaster u WHERE u.valueDesc = :valueDesc")})
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
    @Column(name = "PROCEDURE_NAME")
    private String procedureName;
    @Size(max = 50)
    @Column(name = "VALUE_DESC")
    private String valueDesc;

    public UdDataScopeMaster() {
    }

    public UdDataScopeMaster(String scopeCode) {
        this.scopeCode = scopeCode;
    }

    public UdDataScopeMaster(String scopeCode, short classCode, String procedureName) {
        this.scopeCode = scopeCode;
        this.classCode = classCode;
        this.procedureName = procedureName;
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

    public String getProcedureName() {
        return procedureName;
    }

    public void setProcedureName(String procedureName) {
        this.procedureName = procedureName;
    }

    public String getValueDesc() {
        return valueDesc;
    }

    public void setValueDesc(String valueDesc) {
        this.valueDesc = valueDesc;
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
