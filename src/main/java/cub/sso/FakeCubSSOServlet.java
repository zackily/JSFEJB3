package cub.sso;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/FakeCheckToken")
public class FakeCubSSOServlet extends HttpServlet {

    private static Logger logger = LoggerFactory.getLogger(FakeCubSSOServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("j_username");
        logger.debug("username : " + username);
        String password = req.getParameter("j_password");
        logger.debug("password : " + password);
        String role = "";
        if (StringUtils.isNotBlank(username)) {
            role = username.equals("gilbert") ? "1" : "2";
            CubSSOOutputDto person = new CubSSOOutputDto();
            person.setBranchid("001");
            person.setBranchname("天下第一行");
            person.setEmpId(username);
            person.setEmpname("不登入測試帳號");
            person.setRole(role);
            // person.setAuthName(Roles.getName(Integer.parseInt(person.getRole())));

            if (person != null) {
                UserSession user = new UserSession();
                user.setUser(person);
                HttpSession session = req.getSession(true);
                UserSession userSession = (UserSession) session.getAttribute("userSession");
                userSession = new UserSession();
                userSession.setUser(person);
                session.setAttribute("userSession", userSession);
                resp.sendRedirect("/invest-commons-ui/faces/rcmm/dataScopeSetting.xhtml");
            }
        } else {
            resp.sendRedirect("/invest-commons-ui/faces/error.xhtml");
        }

    }
}
