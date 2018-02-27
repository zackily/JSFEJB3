/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package cub.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.faces.model.SelectItem;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author NT48810
 */
@Entity
@Table(name = "UD_DATA_SCOPE_DETAIL")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "UdDataScopeDetail.findAll", query = "SELECT u FROM UdDataScopeDetail u"),
        @NamedQuery(name = "UdDataScopeDetail.findByScopeCode", query = "SELECT u FROM UdDataScopeDetail u WHERE u.udDataScopeDetailPK.scopeCode = :scopeCode"),
        @NamedQuery(name = "UdDataScopeDetail.findBySeqNo", query = "SELECT u FROM UdDataScopeDetail u WHERE u.udDataScopeDetailPK.seqNo = :seqNo"),
        @NamedQuery(name = "UdDataScopeDetail.findByParameterName", query = "SELECT u FROM UdDataScopeDetail u WHERE u.parameterName = :parameterName"),
        @NamedQuery(name = "UdDataScopeDetail.findByParameterDesc", query = "SELECT u FROM UdDataScopeDetail u WHERE u.parameterDesc = :parameterDesc"),
        @NamedQuery(name = "UdDataScopeDetail.findByValue", query = "SELECT u FROM UdDataScopeDetail u WHERE u.value = :value"),
        @NamedQuery(name = "UdDataScopeDetail.findByValueDesc", query = "SELECT u FROM UdDataScopeDetail u WHERE u.valueDesc = :valueDesc"),
        @NamedQuery(name = "UdDataScopeDetail.findByUdColumnCode", query = "SELECT u FROM UdDataScopeDetail u WHERE u.udColumnCode = :udColumnCode"),
        @NamedQuery(name = "UdDataScopeDetail.findByUdColumnName", query = "SELECT u FROM UdDataScopeDetail u WHERE u.udColumnName = :udColumnName") })
public class UdDataScopeDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UdDataScopeDetailPK udDataScopeDetailPK;
    @Size(max = 20)
    @Column(name = "PARAMETER_NAME")
    private String parameterName;
    @Size(max = 50)
    @Column(name = "PARAMETER_DESC")
    private String parameterDesc;
    @Size(max = 50)
    @Column(name = "VALUE")
    private String value;
    @Size(max = 50)
    @Column(name = "VALUE_DESC")
    private String valueDesc;
    @Size(max = 6)
    @Column(name = "UD_COLUMN_CODE")
    private String udColumnCode;
    @Size(max = 50)
    @Column(name = "UD_COLUMN_NAME")
    private String udColumnName;
    @Transient
    private List<SelectItem> itemRdOptionItemMenu;

    public UdDataScopeDetail() {
    }

    public UdDataScopeDetail(UdDataScopeDetailPK udDataScopeDetailPK) {
        this.udDataScopeDetailPK = udDataScopeDetailPK;
    }

    public UdDataScopeDetail(String scopeCode, short seqNo) {
        this.udDataScopeDetailPK = new UdDataScopeDetailPK(scopeCode, seqNo);
    }

    public UdDataScopeDetailPK getUdDataScopeDetailPK() {
        return udDataScopeDetailPK;
    }

    public void setUdDataScopeDetailPK(UdDataScopeDetailPK udDataScopeDetailPK) {
        this.udDataScopeDetailPK = udDataScopeDetailPK;
    }

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public String getParameterDesc() {
        return parameterDesc;
    }

    public void setParameterDesc(String parameterDesc) {
        this.parameterDesc = parameterDesc;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValueDesc() {
        return valueDesc;
    }

    public void setValueDesc(String valueDesc) {
        this.valueDesc = valueDesc;
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

    public List<SelectItem> getItemRdOptionItemMenu() {
        return itemRdOptionItemMenu;
    }

    public void setItemRdOptionItemMenu(List<SelectItem> itemRdOptionItemMenu) {
        this.itemRdOptionItemMenu = itemRdOptionItemMenu;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (udDataScopeDetailPK != null ? udDataScopeDetailPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UdDataScopeDetail)) {
            return false;
        }
        UdDataScopeDetail other = (UdDataScopeDetail) object;
        if ((this.udDataScopeDetailPK == null && other.udDataScopeDetailPK != null)
                || (this.udDataScopeDetailPK != null && !this.udDataScopeDetailPK.equals(other.udDataScopeDetailPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cub.entities.UdDataScopeDetail[ udDataScopeDetailPK=" + udDataScopeDetailPK + " ]";
    }

}
