package cub.controller;

import java.io.IOException;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cub.sso.UserSession;

public abstract class AbstractController {
    private static Logger logger = LoggerFactory.getLogger(AbstractController.class);

    public AbstractController() {
    }

    public void checkSession(UserSession session) throws IOException {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        if (null == session.getUser()) {
            ec.redirect(ec.getRequestContextPath() + "/faces/error.xhtml");
        } else {
            logger.debug("emp id = " + session.getUser().getEmpId());
        }
    }

    public void closeDialog() {
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('createDialog').hide();");
    }
}
