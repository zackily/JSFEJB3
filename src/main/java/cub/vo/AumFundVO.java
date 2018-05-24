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
public class AumFundVO implements Serializable{
    private Date queryStartDate;
    private Date queryEndDate;
    private String fundHldTstAcc;
    private String fundHldCustNo;
    private String fundHldFundNo;
    private String fundHldTstOrCust;

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

    public String getFundHldTstAcc() {
        return fundHldTstAcc;
    }

    public void setFundHldTstAcc(String fundHldTstAcc) {
        this.fundHldTstAcc = fundHldTstAcc;
    }

    public String getFundHldCustNo() {
        return fundHldCustNo;
    }

    public void setFundHldCustNo(String fundHldCustNo) {
        this.fundHldCustNo = fundHldCustNo;
    }

    public String getFundHldFundNo() {
        return fundHldFundNo;
    }

    public void setFundHldFundNo(String fundHldFundNo) {
        this.fundHldFundNo = fundHldFundNo;
    }

    public String getFundHldTstOrCust() {
        return fundHldTstOrCust;
    }

    public void setFundHldTstOrCust(String fundHldTstOrCust) {
        this.fundHldTstOrCust = fundHldTstOrCust;
    }
    
    

}
