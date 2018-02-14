/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cub.vo;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author NT48810
 */
public class MgDetailVO implements Serializable{
    private String mgActDCode;
    private String mgActDName;
    private Date queryStartDate;
    private Date queryEndDate;
 private String status;
    public String getMgActDCode() {
        return mgActDCode;
    }

    public void setMgActDCode(String mgActDCode) {
        this.mgActDCode = mgActDCode;
    }

    public String getMgActDName() {
        return mgActDName;
    }

    public void setMgActDName(String mgActDName) {
        this.mgActDName = mgActDName;
    }

    public Date getQueryStartDate() {
        return queryStartDate;
    }

    public void setQueryStartDate(Date queryStartDate) {
        this.queryStartDate = queryStartDate;
    }

    public Date getQueryEndDate() {
        return queryEndDate;
    }

    public void setQueryEndDate(Date queryEndDate) {
        this.queryEndDate = queryEndDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
