/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cub.sso;

import cub.enums.Roles;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author NT48810
 */
@ManagedBean(name = "userSession")
@SessionScoped
public class UserSession {
    private CubSSOOutputDto user;

    public CubSSOOutputDto getUser() {
        return user;
    }

    public void setUser(CubSSOOutputDto user) {
        this.user = user;
    }

    public String toRoleName(){
        return Roles.getName(Integer.parseInt(user.getRole()));
    }
            
   
}
