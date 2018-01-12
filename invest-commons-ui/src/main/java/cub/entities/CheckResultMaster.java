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
@Table(name = "CHECK_RESULT_MASTER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CheckResultMaster.findAll", query = "SELECT c FROM CheckResultMaster c")
    , @NamedQuery(name = "CheckResultMaster.findByOrderNo", query = "SELECT c FROM CheckResultMaster c WHERE c.orderNo = :orderNo")
    , @NamedQuery(name = "CheckResultMaster.findByResultCode", query = "SELECT c FROM CheckResultMaster c WHERE c.resultCode = :resultCode")})
public class CheckResultMaster implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "ORDER_NO")
    private String orderNo;
    @Size(max = 4)
    @Column(name = "RESULT_CODE")
    private String resultCode;

    public CheckResultMaster() {
    }

    public CheckResultMaster(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderNo != null ? orderNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CheckResultMaster)) {
            return false;
        }
        CheckResultMaster other = (CheckResultMaster) object;
        if ((this.orderNo == null && other.orderNo != null) || (this.orderNo != null && !this.orderNo.equals(other.orderNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cub.entities.CheckResultMaster[ orderNo=" + orderNo + " ]";
    }
    
}
