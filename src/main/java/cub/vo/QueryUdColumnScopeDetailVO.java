/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cub.vo;

import java.io.Serializable;

/**
 *
 * @author NT48810
 */
public class QueryUdColumnScopeDetailVO implements Serializable {

    private Short classCode;
    private String tableName;
    private String columnName;

    public Short getClassCode() {
        return classCode;
    }

    public void setClassCode(Short classCode) {
        this.classCode = classCode;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

}
