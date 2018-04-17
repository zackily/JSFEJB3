package cub.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * The persistent class for the TR_PARAMETER_INFO database table.
 * 
 */
@Entity
@Table(name = "TR_PARAMETER_INFO")
@NamedQuery(name = "TrParameterInfo.findAll", query = "SELECT t FROM TrParameterInfo t")
public class TrParameterInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private TrParameterInfoPK id;

    @Temporal(TemporalType.DATE)
    @Column(name = "LOG_DTTM")
    private Date logDttm;

    @Column(name = "LOG_USER_ID")
    private String logUserId;

    private String memo;

    @Column(name = "PARAMETER_DATA_DEC_DIGIT")
    private BigDecimal parameterDataDecDigit;

    @Column(name = "PARAMETER_DATA_TOTAL_DIGIT")
    private BigDecimal parameterDataTotalDigit;

    @Column(name = "PARAMETER_DATA_TYPE")
    private String parameterDataType;

    @Column(name = "PARAMETER_DESC")
    private String parameterDesc;

    @Column(name = "PARAMETER_NAME")
    private String parameterName;

    public TrParameterInfo() {
    }

    public TrParameterInfoPK getId() {
        return this.id;
    }

    public void setId(TrParameterInfoPK id) {
        this.id = id;
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

    public String getMemo() {
        return this.memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public BigDecimal getParameterDataDecDigit() {
        return this.parameterDataDecDigit;
    }

    public void setParameterDataDecDigit(BigDecimal parameterDataDecDigit) {
        this.parameterDataDecDigit = parameterDataDecDigit;
    }

    public BigDecimal getParameterDataTotalDigit() {
        return this.parameterDataTotalDigit;
    }

    public void setParameterDataTotalDigit(BigDecimal parameterDataTotalDigit) {
        this.parameterDataTotalDigit = parameterDataTotalDigit;
    }

    public String getParameterDataType() {
        return this.parameterDataType;
    }

    public void setParameterDataType(String parameterDataType) {
        this.parameterDataType = parameterDataType;
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

}