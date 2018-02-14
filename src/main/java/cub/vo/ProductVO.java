/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cub.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author NT48810
 */
public class ProductVO implements Serializable{
   private String productClass;
   private String productAbroad;
   private List<String> productSeries;
   private List<String> productType;
   private String productName;
   private String productCode;

    public String getProductClass() {
        return productClass;
    }

    public void setProductClass(String productClass) {
        this.productClass = productClass;
    }

    public String getProductAbroad() {
        return productAbroad;
    }

    public void setProductAbroad(String productAbroad) {
        this.productAbroad = productAbroad;
    }

    public List<String> getProductSeries() {
        return productSeries;
    }

    public void setProductSeries(List<String> productSeries) {
        this.productSeries = productSeries;
    }

    public List<String> getProductType() {
        return productType;
    }

    public void setProductType(List<String> productType) {
        this.productType = productType;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

   
}
