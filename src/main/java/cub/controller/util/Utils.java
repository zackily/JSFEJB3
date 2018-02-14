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
    
//    @EJB
//    private FundFacade fundFacade;
    
    public String toPlusOneString(int seq,int size){
        return StringUtils.leftPad(Integer.toString(++seq), size,'0');
    }
    
//    public List<Fund> findByFundStatus(String status){
//        return fundFacade.findByFundStatus(status);
//    }
}
