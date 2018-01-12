/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cub.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author NT48810
 */
public enum SeqTypeEnum {

    FUND_CODE("F0"),//基金法規代碼
    GOLD_CODE("G0"),//黃金法規代碼
    DEBT_CODE("B0"),//債券法規代碼
    ETF_CODE("E0"),//ETF法規代碼
    SPEC_CODE("S0"),//特金法規代碼
    COMM_CODE("M0"),//全行共用代碼
    UDFIELD_CODE("CD"),//自定義欄位範圍
    UDDATA_CODE("DF"),//自定義資料範圍
    DATA_CODE("D0");//資料範圍

    private static final Map<String, SeqTypeEnum> lookup = new HashMap<String, SeqTypeEnum>();

    static {
        for (SeqTypeEnum s : EnumSet.allOf(SeqTypeEnum.class)) {
            lookup.put(s.getCode(), s);
        }
    }
    private final String code;

    private SeqTypeEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public static SeqTypeEnum get(String code) {
        return lookup.get(code);
    }
}
