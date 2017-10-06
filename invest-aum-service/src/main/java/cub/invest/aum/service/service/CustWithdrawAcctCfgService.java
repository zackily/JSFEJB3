package cub.invest.aum.service.service;

import java.util.List;

import cub.invest.aum.service.dto.CustWithdrawAcctCfgDto;
import cub.invest.aum.service.dto.SvcResult;

/**
 * PayAcct Service interface
 * @author NT80212
 *
 */
public interface CustWithdrawAcctCfgService {
	/**
	 * Update PayAcct by a DTO list
	 * @param dtoList
	 * @return SvcResult
	 */
	public SvcResult updAll(List<CustWithdrawAcctCfgDto> dtoList);
	
	/**
	 * Update PayAcct by a DTO
	 * @param dto
	 * @return SvcResult
	 */
	public SvcResult updOne(CustWithdrawAcctCfgDto dto);
	
	/**
	 * Create PayAcct by a DTO list
	 * @param dtoList
	 * @return SvcResult
	 */
	public SvcResult crtAll(List<CustWithdrawAcctCfgDto> dtoList);
	
	/**
	 * Create PayAcct by a DTO
	 * @param dto
	 * @return SvcResult
	 */
	public SvcResult crtOne(CustWithdrawAcctCfgDto dto);
	
	/**
	 * get PAY_TYPE from WITHDRAW_ACCT
	 * @param withdrawAcct
	 * @return
	 */
	public char getPayTypeFromWithdrawAcct(String withdrawAcct);
}
