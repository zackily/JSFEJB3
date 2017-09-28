/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cub.controller.util;

import javax.ejb.Singleton;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author NT48810
 */
@Singleton
public class Utils {
    public String toPlusOneString(int seq){
        return StringUtils.leftPad(Integer.toString(++seq), 5,'0');
    }
}
