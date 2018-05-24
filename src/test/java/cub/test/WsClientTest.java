package cub.test;

import java.io.IOException;

import javax.xml.soap.SOAPException;

import cub.webservice.soap.service.BANCSService;
import cub.webservice.soap.service.BANCSServiceService;

public class WsClientTest {

	public static void main(String[] args) throws SOAPException, IOException {
		BANCSServiceService service = new BANCSServiceService();
		BANCSService bancsService = service.getBANCSService();
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
		String result = bancsService.xServiceMethod(sb.toString());
		System.out.println(result);
	}
}
