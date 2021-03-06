package cub.test;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

import cub.webservice.soap.service.BANCSService;
import cub.webservice.soap.service.BANCSServiceService;

public class WsClientTest {

	public static void main(String[] args) throws SOAPException, IOException {
		BANCSServiceService service = new BANCSServiceService();
		BANCSService bancsService = service.getBANCSService();
		String result = bancsService.xServiceMethod(genXMLContent());
		Document doc = convertStringToDocument(result);
		String tagName = doc.getElementsByTagName("FirstName").item(0).getFirstChild().getNodeValue();
		System.out.println(tagName);
	}

	private static String genXMLContent() {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		String result = "";
		try {
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			// root elements
			Document doc = docBuilder.newDocument();

			Element cubElement = doc.createElement("CUBXML");
			cubElement.setAttribute("xmlns", "http://www.cathaybk.com.tw/webservice/FNSCIF0000/");
			cubElement.setAttribute("VERSIONNO", "0.1");
			doc.appendChild(cubElement);

			Element headerElement = doc.createElement("MWHEADER");
			cubElement.appendChild(headerElement);

			Element msgIdElement = doc.createElement("MSGID");
			msgIdElement.appendChild(doc.createTextNode("FNSCIF0000"));
			headerElement.appendChild(msgIdElement);
			Element channelElement = doc.createElement("SOURCECHANNEL");
			channelElement.appendChild(doc.createTextNode("OTR-NT-DBC-01"));
			headerElement.appendChild(channelElement);
			Element seqElement = doc.createElement("TXNSEQ");
			seqElement.appendChild(doc.createTextNode("090815375377"));
			headerElement.appendChild(seqElement);

			Element transrqElement = doc.createElement("TRANRQ");
			cubElement.appendChild(transrqElement);

			Element bidElement = doc.createElement("BranchId");
			bidElement.appendChild(doc.createTextNode("9999"));
			transrqElement.appendChild(bidElement);
			Element tidElement = doc.createElement("TellerId");
			tidElement.appendChild(doc.createTextNode("00000"));
			transrqElement.appendChild(tidElement);
			Element idnoElement = doc.createElement("IdNo");
			idnoElement.appendChild(doc.createTextNode("Q102535902"));
			transrqElement.appendChild(idnoElement);
			Element idtypeElement = doc.createElement("IdType");
			idtypeElement.appendChild(doc.createTextNode("11"));
			transrqElement.appendChild(idtypeElement);

			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.ENCODING, "BIG5");
			transformer.setOutputProperty(OutputKeys.VERSION, "1.0");
			DOMSource source = new DOMSource(doc);
			StringWriter sw = new StringWriter();
			StreamResult console = new StreamResult(sw);
			transformer.transform(source, console);
			result = sw.toString();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	private static Document convertStringToDocument(String xmlStr) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new InputSource(new StringReader(xmlStr)));
			return doc;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
