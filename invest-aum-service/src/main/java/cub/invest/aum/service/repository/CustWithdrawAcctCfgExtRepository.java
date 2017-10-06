package cub.invest.aum.service.repository;

import java.util.List;

import cub.invest.aum.service.dto.CustWithdrawAcctCfgDto;
import cub.invest.aum.service.entity.CustWithdrawAcctCfg;

public interface CustWithdrawAcctCfgExtRepository {
	public List<CustWithdrawAcctCfg> findAllByUniqueKey(List<CustWithdrawAcctCfgDto> uniquekeyList);
}
