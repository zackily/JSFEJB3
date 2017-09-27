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
public class MgMasterVO implements Serializable{
   private String mgActMCode;
   private String mgActMName;
   private String mgActMType;
   private String mgActMRateSet;
   private String mgActMSaleChnl;
   private String mgActMChargeObj;

    public String getMgActMCode() {
        return mgActMCode;
    }

    public void setMgActMCode(String mgActMCode) {
        this.mgActMCode = mgActMCode;
    }

    public String getMgActMName() {
        return mgActMName;
    }

    public void setMgActMName(String mgActMName) {
        this.mgActMName = mgActMName;
    }

    public String getMgActMRateSet() {
        return mgActMRateSet;
    }

    public void setMgActMRateSet(String mgActMRateSet) {
        this.mgActMRateSet = mgActMRateSet;
    }

    public String getMgActMSaleChnl() {
        return mgActMSaleChnl;
    }

    public void setMgActMSaleChnl(String mgActMSaleChnl) {
        this.mgActMSaleChnl = mgActMSaleChnl;
    }

    public String getMgActMChargeObj() {
        return mgActMChargeObj;
    }

    public void setMgActMChargeObj(String mgActMChargeObj) {
        this.mgActMChargeObj = mgActMChargeObj;
    }

    public String getMgActMType() {
        return mgActMType;
    }

    public void setMgActMType(String mgActMType) {
        this.mgActMType = mgActMType;
    }
   

}
