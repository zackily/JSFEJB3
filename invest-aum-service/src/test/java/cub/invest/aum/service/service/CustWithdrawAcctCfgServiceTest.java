package cub.invest.aum.service.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cub.incest.aum.service.enums.ChannelType;
import cub.incest.aum.service.enums.CurrencyCode;
import cub.incest.aum.service.enums.PayType;
import cub.invest.aum.service.dto.CustWithdrawAcctCfgDto;
import cub.invest.aum.service.dto.SvcResult;
import cub.invest.aum.service.entity.CustWithdrawAcctCfg;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CustWithdrawAcctCfgServiceTest {
	@Autowired
	CustWithdrawAcctCfgService service;

	@Test
	public void testGetPayTypeFromWithdrawAcct(){
		assertEquals(service.getPayTypeFromWithdrawAcct("833123456"),PayType.OBU.getCode());
		assertEquals(service.getPayTypeFromWithdrawAcct("033123456"),PayType.OBU.getCode());
		assertEquals(service.getPayTypeFromWithdrawAcct("633123456"),PayType.DBU.getCode());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testCrtOne_Happy(){
		CustWithdrawAcctCfgDto dto = new CustWithdrawAcctCfgDto("nt80212","8331XXXXXXX",CurrencyCode.NTD.getCode());

		SvcResult result = service.crtOne(dto);
		List<CustWithdrawAcctCfg> resultList = ((ArrayList<CustWithdrawAcctCfg>)result.getResult());
		assertEquals(result.getCode(), 0);
		assertEquals(resultList.size(), 1);
		assertNotNull(resultList.get(0).getId());
		
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testCrtAll_Happy(){
		List<CustWithdrawAcctCfgDto> dtoList = new ArrayList<>();
		CustWithdrawAcctCfgDto dto1 = new CustWithdrawAcctCfgDto("nt80212","833XXXXXXXX",CurrencyCode.NTD.getCode());
		CustWithdrawAcctCfgDto dto2 = new CustWithdrawAcctCfgDto("nt80212","033XXXXXXXX",CurrencyCode.USD.getCode());
		CustWithdrawAcctCfgDto dto3 = new CustWithdrawAcctCfgDto("nt80212","733XXXXXXXX",CurrencyCode.NTD.getCode());
		dtoList.add(dto1);
		dtoList.add(dto2);
		dtoList.add(dto3);
		SvcResult result = service.crtAll(dtoList);
		List<CustWithdrawAcctCfg> resultList = ((ArrayList<CustWithdrawAcctCfg>)result.getResult());
		assertEquals(result.getCode(), 0);
		assertEquals(resultList.size(), 3);
		assertNotNull(resultList.get(0).getId());
		assertNotNull(resultList.get(1).getId());
		assertNotNull(resultList.get(2).getId());
		
	}
}
