package cub.sso;

//import cub.jsf.controll.UserSession;
//import cub.controller.UserSession;
import cub.enums.Roles;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

@WebServlet("/FakeCheckToken")
public class FakeCubSSOServlet extends HttpServlet {
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(">>>>>>>>>>doGet()<<<<<<<<<<<");
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

			
                        CubSSOOutputDto person = new CubSSOOutputDto();
                        
                        person.setBranchid("001");
                        person.setBranchname("天下第一行");
                        person.setEmpId("99999");
                        person.setEmpname("不登入測試帳號");
                        person.setRole("1");
                        //person.setRoleEnum(Roles.valueOf(person.getRole()));
			person.setAuthName(Roles.getName(Integer.parseInt(person.getRole()))); 
                        
			if (person != null) {
				System.out.println(person.getReturncode());
				UserSession user = new UserSession();
				// user.init();
				user.setUser(person);
				// getServletContext().setAttribute("userSession", session);

				HttpSession session = req.getSession(true);
				// Get the bean from the session
				UserSession userSession = (UserSession) session.getAttribute("userSession");
				// if the bean is not in session instantiate a new bean
				if (userSession == null) {
					userSession = new UserSession();
					userSession.setUser(person);
					// code for anything else you need to do
					// to initialize myBean
					System.out.println("is null");
				}
                                
                                System.out.println(userSession.getUser().getBranchname());
				// rest of servlet code
				// Put the bean back in the session, since you
				// probably either instantiated a new bean,
				// or at least changed the bean in some way
				session.setAttribute("userSession", userSession);

			}

			resp.sendRedirect("/invest-aum-ui/faces/aumfund/activitymgr/masterActivityList.xhtml");
		

	}
}
