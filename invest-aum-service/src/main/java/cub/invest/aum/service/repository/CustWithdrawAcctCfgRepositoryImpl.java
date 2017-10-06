package cub.invest.aum.service.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cub.invest.aum.service.dto.CustWithdrawAcctCfgDto;
import cub.invest.aum.service.entity.CustWithdrawAcctCfg;

@Repository
public class CustWithdrawAcctCfgRepositoryImpl implements CustWithdrawAcctCfgExtRepository{

	@Transactional
	@Override
	public List<CustWithdrawAcctCfg> findAllByUniqueKey(List<CustWithdrawAcctCfgDto> uniquekeyList) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
