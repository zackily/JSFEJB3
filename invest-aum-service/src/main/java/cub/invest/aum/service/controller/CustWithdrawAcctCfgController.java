package cub.invest.aum.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cub.invest.aum.service.dto.CustWithdrawAcctCfgDto;
import cub.invest.aum.service.dto.SvcResult;
import cub.invest.aum.service.service.CustWithdrawAcctCfgService;

@RestController
@RequestMapping("/payAcct")
public class CustWithdrawAcctCfgController {
	
	@Autowired
	private CustWithdrawAcctCfgService payAcctService;

	@RequestMapping(method = RequestMethod.POST, 
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public boolean crtAll(List<CustWithdrawAcctCfgDto> padList) {
		SvcResult result = payAcctService.crtAll(padList);
		return (result.getCode() == 0);
	}
}
