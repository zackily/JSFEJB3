/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cub.enums;

/**
 *
 * @author NT48810
 */
public enum MgSetMasterStatus {

    SEND("待覆核"), PROCESSING("處理中"), REJECT("退回"), CONFIRM("已覆核"), CLOSED("結案"),DELETE("刪除");
    
    private String name;

    private MgSetMasterStatus(String name) {
        this.name = name;
    }

    public String getStatus() {
        return name;
    }
}
