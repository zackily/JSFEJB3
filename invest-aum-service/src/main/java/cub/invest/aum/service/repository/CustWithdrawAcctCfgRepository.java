package cub.invest.aum.service.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import cub.invest.aum.service.entity.CustWithdrawAcctCfg;

public interface CustWithdrawAcctCfgRepository extends PagingAndSortingRepository<CustWithdrawAcctCfg, Long>, CustWithdrawAcctCfgExtRepository{

}
