package cub.controller;

import java.io.IOException;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import cub.sso.UserSession;

public abstract class AbstractController {

    public AbstractController() {
    }

    public void checkSession(UserSession session) {
        if (null == session.getUser()) {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            try {
                ec.redirect(ec.getRequestContextPath() + "/faces/error.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("emp id = " + session.getUser().getEmpId());
        }
    }

}
