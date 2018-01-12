/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cub.common;

import cub.enums.SeqTypeEnum;

/**
 *
 * @author NT48810
 */
public class CommonUtils {

    private static CommonUtils instance = new CommonUtils();

    private CommonUtils() {

    }

    public static CommonUtils getInstance() {
        return instance;
    }

}
