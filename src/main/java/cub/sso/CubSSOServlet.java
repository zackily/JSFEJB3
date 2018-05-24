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

@WebServlet("/CheckToken")
public class CubSSOServlet extends HttpServlet {
	private static final long serialVersionUID = -8685285401859800066L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(">>>>>>>>>>doGet()<<<<<<<<<<<");
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String userid = req.getParameter("UserID");
		String token = req.getParameter("Token");

		CubSSOInputDto dto = new CubSSOInputDto();
		dto.setAppname("W06219");
		dto.setEmpid(userid);
		dto.setIp("88.8.196.62");
		dto.setToken(token);

		StringWriter sw = new StringWriter();
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(CubSSOInputDto.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.marshal(dto, sw);
		} catch (JAXBException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		String xml = sw.toString();

		String url = "https://88.8.194.131/secuservice/SecuAuth.asp";
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
			}

			public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
			}
		} // end of new X509TrustManager()
		}; // end of new TrustManager[]
		//
		SSLContext sc;
		try {
			sc = SSLContext.getInstance("SSL");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		URL u = new URL(url);
		HttpURLConnection conn = null;

		if (0 == "https".compareToIgnoreCase(url.substring(0, 5))) {
			conn = (HttpURLConnection) ((HttpsURLConnection) u.openConnection());
		} else {
			conn = (HttpURLConnection) u.openConnection();
		}

		conn.setRequestMethod("POST");
		conn.setInstanceFollowRedirects(true);
		conn.setUseCaches(false);
		conn.setDefaultUseCaches(false);
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset= UTF-8");
		DataOutputStream output = new DataOutputStream(conn.getOutputStream());
		output.writeBytes(xml);
		output.flush();
		output.close();

		InputStream input = conn.getInputStream();
		byte[] ba = new byte[1024];
		StringBuffer sb = new StringBuffer();
		while (input.read(ba) != -1) {
			sb.append(new String(ba, "UTF-8").trim());
                        System.out.println(sb.toString());
			ba = new byte[1024];
		}
		System.out.println(sb.toString());
		input.close();
		//
		JAXBContext jaxbContext_o;
		try {
			jaxbContext_o = JAXBContext.newInstance(CubSSOOutputDto.class);
			Unmarshaller unmarshaller = jaxbContext_o.createUnmarshaller();

			StringReader reader = new StringReader(sb.toString());
			CubSSOOutputDto person = (CubSSOOutputDto) unmarshaller.unmarshal(reader);
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
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
