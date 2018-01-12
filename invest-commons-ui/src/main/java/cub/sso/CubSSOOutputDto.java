package cub.sso;

import cub.enums.Roles;
import java.io.Serializable;
//import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ReturnData")
@XmlAccessorType(XmlAccessType.FIELD)
@ManagedBean(name = "userSession")
@SessionScoped
public class CubSSOOutputDto implements Serializable {

    @XmlElement(name = "ReturnCode")
    private String returncode;
    @XmlElement(name = "ReturnMessage")
    private String returnmessage;
    @XmlElement(name = "EmpId")
    private String empId;
    @XmlElement(name = "EmpName")
    private String empname;
    @XmlElement(name = "BranchId")
    private String branchid;
    @XmlElement(name = "BranchName")
    private String branchname;
    @XmlElement(name = "Class")
    private String role;
 
    private String authName;

    public String getReturncode() {
        return returncode;
    }

    public void setReturncode(String returncode) {
        this.returncode = returncode;
    }

    public String getReturnmessage() {
        return returnmessage;
    }

    public void setReturnmessage(String returnmessage) {
        this.returnmessage = returnmessage;
    }

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
