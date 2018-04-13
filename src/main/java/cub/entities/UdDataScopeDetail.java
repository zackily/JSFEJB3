package cub.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.model.SelectItem;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * The persistent class for the UD_DATA_SCOPE_DETAIL database table.
 * 
 */
@Entity
@Table(name = "UD_DATA_SCOPE_DETAIL")
@NamedQuery(name = "UdDataScopeDetail.findAll", query = "SELECT u FROM UdDataScopeDetail u")
public class UdDataScopeDetail implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private UdDataScopeDetailPK id;

    @Column(name = "LEFT_BRACKET")
    private String leftBracket;

    @Temporal(TemporalType.DATE)
    @Column(name = "LOG_DTTM")
    private Date logDttm;

    @Column(name = "LOG_USER_ID")
    private String logUserId;

    private String logic;

    @Column(name = "OP_CODE")
    private String opCode;

    @Column(name = "OP_VALUE")
    private String opValue;

    @Column(name = "PARAMETER_DESC")
    private String parameterDesc;

    @Column(name = "PARAMETER_NAME")
    private String parameterName;

    @Column(name = "RIGHT_BRACKET")
    private String rightBracket;
    @Transient
    private String opValueDesc;
    @Transient
    private List<SelectItem> paraMenu;
    @Transient
    private List<SelectItem> opCodeMenu;
    @Transient
    private String paraId;
    @Transient
    private List<SelectItem> opValueMenu;

    public UdDataScopeDetail() {
    }

    public UdDataScopeDetailPK getId() {
        return this.id;
    }

    public void setId(UdDataScopeDetailPK id) {
        this.id = id;
    }

    public String getLeftBracket() {
        return this.leftBracket;
    }

    public void setLeftBracket(String leftBracket) {
        this.leftBracket = leftBracket;
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

    public String getLogic() {
        return this.logic;
    }

    public void setLogic(String logic) {
        this.logic = logic;
    }

    public String getOpCode() {
        return this.opCode;
    }

    public void setOpCode(String opCode) {
        this.opCode = opCode;
    }

    public String getOpValue() {
        return this.opValue;
    }

    public void setOpValue(String opValue) {
        this.opValue = opValue;
    }

    public String getParameterDesc() {
        return this.parameterDesc;
    }

    public void setParameterDesc(String parameterDesc) {
        this.parameterDesc = parameterDesc;
    }

    public String getParameterName() {
        return this.parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public String getRightBracket() {
        return this.rightBracket;
    }

    public void setRightBracket(String rightBracket) {
        this.rightBracket = rightBracket;
    }

    public String getOpValueDesc() {
        return opValueDesc;
    }

    public void setOpValueDesc(String opValueDesc) {
        this.opValueDesc = opValueDesc;
    }

    public List<SelectItem> getParaMenu() {
        return paraMenu;
    }

    public void setParaMenu(List<SelectItem> paraMenu) {
        this.paraMenu = paraMenu;
    }

    public List<SelectItem> getOpCodeMenu() {
        return opCodeMenu;
    }

    public void setOpCodeMenu(List<SelectItem> opCodeMenu) {
        this.opCodeMenu = opCodeMenu;
    }

    public String getParaId() {
        return paraId;
    }

    public void setParaId(String paraId) {
        this.paraId = paraId;
    }

    public List<SelectItem> getOpValueMenu() {
        return opValueMenu;
    }

    public void setOpValueMenu(List<SelectItem> opValueMenu) {
        this.opValueMenu = opValueMenu;
    }

}