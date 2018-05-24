
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
 *         &lt;element name="ECHOReturn" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "echoReturn" })
@XmlRootElement(name = "ECHOResponse")
public class ECHOResponse {

	@XmlElement(name = "ECHOReturn", required = true)
	protected String echoReturn;

	/**
	 * ���o echoReturn �S�ʪ���.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getECHOReturn() {
		return echoReturn;
	}

	/**
	 * �]�w echoReturn �S�ʪ���.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setECHOReturn(String value) {
		this.echoReturn = value;
	}

}
