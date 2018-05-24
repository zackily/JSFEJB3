package cub.test;

import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
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

public class CreateXMLTest {

	public static void main(String[] args) {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
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
			bidElement.appendChild(doc.createTextNode("0019"));
			transrqElement.appendChild(bidElement);
			Element tidElement = doc.createElement("TellerId");
			tidElement.appendChild(doc.createTextNode("00000"));
			transrqElement.appendChild(tidElement);
			Element idnoElement = doc.createElement("IdNo");
			idnoElement.appendChild(doc.createTextNode("K222051683"));
			transrqElement.appendChild(idnoElement);
			Element idtypeElement = doc.createElement("IdType");
			idtypeElement.appendChild(doc.createTextNode("11"));
			transrqElement.appendChild(idtypeElement);

			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource source = new DOMSource(doc);
			StringWriter sw = new StringWriter();
			StreamResult console = new StreamResult(sw);
			transformer.transform(source, console);

			System.out.println(sw);
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
	}

}
