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
 * @author F123669
 */
@Entity
@Table(name = "UD_METHOD_MASTER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UdMethodMaster.findAll", query = "SELECT u FROM UdMethodMaster u")
    , @NamedQuery(name = "UdMethodMaster.findByMethodName", query = "SELECT u FROM UdMethodMaster u WHERE u.methodName = :methodName")
    , @NamedQuery(name = "UdMethodMaster.findByMethodDesc", query = "SELECT u FROM UdMethodMaster u WHERE u.methodDesc = :methodDesc")
    , @NamedQuery(name = "UdMethodMaster.findByRtnType", query = "SELECT u FROM UdMethodMaster u WHERE u.rtnType = :rtnType")})
public class UdMethodMaster implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "METHOD_NAME")
    private String methodName;
    @Size(max = 100)
    @Column(name = "METHOD_DESC")
    private String methodDesc;
    @Column(name = "RTN_TYPE")
    private Short rtnType;

    public UdMethodMaster() {
    }

    public UdMethodMaster(String methodName) {
        this.methodName = methodName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getMethodDesc() {
        return methodDesc;
    }

    public void setMethodDesc(String methodDesc) {
        this.methodDesc = methodDesc;
    }

    public Short getRtnType() {
        return rtnType;
    }

    public void setRtnType(Short rtnType) {
        this.rtnType = rtnType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (methodName != null ? methodName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UdMethodMaster)) {
            return false;
        }
        UdMethodMaster other = (UdMethodMaster) object;
        if ((this.methodName == null && other.methodName != null) || (this.methodName != null && !this.methodName.equals(other.methodName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cub.entities.UdMethodMaster[ methodName=" + methodName + " ]";
    }
    
}
