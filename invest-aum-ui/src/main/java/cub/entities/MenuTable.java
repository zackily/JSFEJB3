/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cub.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author NT48810
 */
@Entity
@Table(name = "MENU_TABLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MenuTable.findAll", query = "SELECT m FROM MenuTable m")
   })
public class MenuTable implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
   @Id    
    @NotNull
    @Column(name = "ID")    
    @SequenceGenerator(name = "MENU_SEQ", sequenceName = "MENU_SEQ", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MENU_SEQ")
    private BigDecimal id;
    @Size(max = 50)
    @Column(name = "MENU_LABEL_TW")
    private String menuLabelTw;
    @Size(max = 50)
    @Column(name = "MENU_LABEL_EN")
    private String menuLabelEn;
    @Size(max = 50)
    @Column(name = "MEMU_URL")
    private String memuUrl;
    @Size(max = 20)
    @Column(name = "MENU_ICON")
    private String menuIcon;
    @Size(max = 20)
    @Column(name = "IS_ROOT")
    private String isRoot;
    @Size(max = 20)
    @Column(name = "SEQUENCE")
    private String sequence;
    @OneToOne(fetch=FetchType.LAZY)
  @JoinColumn(name="PARENT_ID")   
    private MenuTable parentId;

    public MenuTable() {
    }

    public MenuTable(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getMenuLabelTw() {
        return menuLabelTw;
    }

    public void setMenuLabelTw(String menuLabelTw) {
        this.menuLabelTw = menuLabelTw;
    }

    public String getMenuLabelEn() {
        return menuLabelEn;
    }

    public void setMenuLabelEn(String menuLabelEn) {
        this.menuLabelEn = menuLabelEn;
    }

   

    public String getMemuUrl() {
        return memuUrl;
    }

    public void setMemuUrl(String memuUrl) {
        this.memuUrl = memuUrl;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    public String getIsRoot() {
        return isRoot;
    }

    public void setIsRoot(String isRoot) {
        this.isRoot = isRoot;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public MenuTable getParentId() {
        return parentId;
    }

    public void setParentId(MenuTable parentId) {
        this.parentId = parentId;
    }

 

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MenuTable)) {
            return false;
        }
        MenuTable other = (MenuTable) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cub.entities.MenuTable[ id=" + id + " ]";
    }
    
}
