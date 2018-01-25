/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cub.common;

import cub.enums.SeqTypeEnum;
import javax.ejb.EJB;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author NT48810
 */
public class CommonUtils {

    @EJB
    private cub.facade.WorkSeqFacade ejbWorkSeqFacade;

    private static CommonUtils instance = new CommonUtils();

    private CommonUtils() {

    }

    public static CommonUtils getInstance() {
        return instance;
    }

    /*
    取得自定義欄位範圍代碼
     */
    public String getWorkSeq(String code) {
        String seqType = SeqTypeEnum.valueOf(code).getCode();
        Short no = ejbWorkSeqFacade.getWorkSeqNo(code);
        return seqType + StringUtils.leftPad(String.valueOf(no + 1), 4, "0");
    }
}
