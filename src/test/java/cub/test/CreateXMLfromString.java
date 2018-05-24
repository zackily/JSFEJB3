package cub.test;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class CreateXMLfromString {

	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder(200);
		sb.append("<?xml version=\"1.0\" encoding=\"BIG5\"?>")
				.append("<CUBXML xmlns=\"http://www.cathaybk.com.tw/webservice/FNSCIF0000/\" VERSIONNO=\"0.1\">")
				.append("<MWHEADER>")
				.append("<MSGID>FNSCIF0000</MSGID>")
				.append("<SOURCECHANNEL>OTR-NT-DBC-01</SOURCECHANNEL>")
				.append("<TXNSEQ>090815375377</TXNSEQ>")
				.append("</MWHEADER>")
				.append("<TRANRQ>")
				.append("<BranchId>9999</BranchId>")
				.append("<TellerId>00000</TellerId>")
				.append("<IdNo>Q102535902</IdNo>")
				.append("<IdType>11</IdType>")
				.append("</TRANRQ>")
				.append("</CUBXML>");
		Document doc = convertStringToDocument(sb.toString());
		String tagName = doc.getElementsByTagName("TXNSEQ").item(0).getFirstChild().getNodeValue();
		System.out.println(tagName);
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
