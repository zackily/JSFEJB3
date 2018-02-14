package cub.sso;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "SendData")
@XmlAccessorType(XmlAccessType.FIELD)
public class CubSSOInputDto implements Serializable {
	
	@XmlElement(name = "Token")
	private String token;
	@XmlElement(name = "AppName")
	private String appname;
	@XmlElement(name = "EmpId")
	private String empid;
	@XmlElement(name = "IP")
	private String ip;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getAppname() {
		return appname;
	}
	public void setAppname(String appname) {
		this.appname = appname;
	}
	public String getEmpid() {
		return empid;
	}
	public void setEmpid(String empid) {
		this.empid = empid;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}

}
