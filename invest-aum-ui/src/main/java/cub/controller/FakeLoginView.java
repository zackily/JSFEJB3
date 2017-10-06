/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cub.controller;

import cub.enums.Roles;
import cub.sso.CubSSOOutputDto;
import cub.sso.UserSession;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.event.ActionEvent;
import org.primefaces.context.RequestContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author NT14989
 */
@ManagedBean(name = "fakeLogin")
@ViewScoped
public class FakeLoginView implements Serializable {

    CubSSOOutputDto person;
    String empId;
    String empname;
    String branchid;
    String branchname;
    String role;
    String authName;

    private Map<String, String> roles = new HashMap<String, String>();

    @PostConstruct
    public void init() {
        person = new CubSSOOutputDto();
        empId = "44311";
        empname = "";
        branchid = "116";
        branchname = "";
        role = "1";
        authName = "分行經辦";
    }

    public Map<String, String> getRoles() {
        if (roles.isEmpty()) {
            for (Roles c : Roles.values()) {
                roles.put(c.name(), String.valueOf(c.getValue()));
            }
        }
        return roles;
    }

    public void onRoleChange() {
        if (StringUtils.isNotEmpty(role)) {
            setAuthName(Roles.getName(Integer.valueOf(role)));
        }//end if
    }

    public void login(ActionEvent event) throws IOException {
        person.setEmpId(empId);
        person.setEmpname(empname);
        person.setBranchid(branchid);
        person.setBranchname(branchname);
        person.setRole(role);
        person.setAuthName(authName);

        UserSession userSession = new UserSession();
        userSession.setUser(person);
        //RequestContext context = RequestContext.getCurrentInstance();
        //FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) context.getSession(true);
        session.setAttribute("userSession", userSession);
        //HttpServletResponse resp = (HttpServletResponse) facesContext.getExternalContext().getResponse();
        // resp.sendRedirect("/invest-aum-ui/faces/aum/aumFund/QueryList.xhtml");

        context.redirect(context.getRequestContextPath() + "/faces/aum/aumFund/QueryList.xhtml");
    }

    //=====getter & setter======
    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpname() {
        return empname;
    }

    public void setEmpname(String empname) {
        this.empname = empname;
    }

    public String getBranchid() {
        return branchid;
    }

    public void setBranchid(String branchid) {
        this.branchid = branchid;
    }

    public String getBranchname() {
        return branchname;
    }

    public void setBranchname(String branchname) {
        this.branchname = branchname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }

}
