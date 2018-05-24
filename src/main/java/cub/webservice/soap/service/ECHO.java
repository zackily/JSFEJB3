
package cub.webservice.soap.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * anonymous complex type �� Java ���O.
 * 
 * <p>
 * �U�C���n���q�|���w�����O���]�t���w�����e.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RequestString" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "requestString" })
@XmlRootElement(name = "ECHO")
public class ECHO {

	@XmlElement(name = "RequestString", required = true)
	protected String requestString;

	/**
	 * ���o requestString �S�ʪ���.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getRequestString() {
		return requestString;
	}

	/**
	 * �]�w requestString �S�ʪ���.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setRequestString(String value) {
		this.requestString = value;
	}

}
