package cub.invest.aum.service.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cub.incest.aum.service.enums.PayType;
import cub.invest.aum.service.dto.CustWithdrawAcctCfgDto;
import cub.invest.aum.service.dto.SvcResult;
import cub.invest.aum.service.entity.CustWithdrawAcctCfg;
import cub.invest.aum.service.repository.CustWithdrawAcctCfgRepository;

@Service
@Transactional
public class CustWithdrawAcctCfgServiceImpl implements CustWithdrawAcctCfgService {

	@Autowired
	CustWithdrawAcctCfgRepository payAcctRepository;

	@Override
	public SvcResult updAll(List<CustWithdrawAcctCfgDto> dtoList) {
		
		// convert dto to entity
		// fill PAY_TYPE & UPDATE_DATE
	
		ArrayList<CustWithdrawAcctCfg> entityList = (ArrayList<CustWithdrawAcctCfg>) payAcctRepository.findAllByUniqueKey(dtoList);
		// TODO  	
			
			
			
//			PayAcct paTmp = new PayAcct();
//			BeanUtils.copyProperties(dto, paTmp);
//			paTmp.setPayType(getPayTypeFromWithdrawAcct(dto.getWithdrawAcct()));
//			paTmp.setUpdateDate(new Date());
//			entityList.add(paTmp);
		
		return null;
	}

	@Override
	public SvcResult updOne(CustWithdrawAcctCfgDto dto) {
		List<CustWithdrawAcctCfgDto> dtoList = new ArrayList<>();
		dtoList.add(dto);
		return updAll(dtoList);
	}

	@Override
	public SvcResult crtAll(List<CustWithdrawAcctCfgDto> dtoList) {
		// convert dto to entity
		// fill PAY_TYPE & UPDATE_DATE
		ArrayList<CustWithdrawAcctCfg> entityList = new ArrayList<CustWithdrawAcctCfg>();
		for (CustWithdrawAcctCfgDto dto : dtoList) {
			CustWithdrawAcctCfg paTmp = new CustWithdrawAcctCfg();
			BeanUtils.copyProperties(dto, paTmp);
			//userId should come from dto
			paTmp.setUpdateDttm(new Date());
			entityList.add(paTmp);
		}

		// call repository & return
		entityList = (ArrayList<CustWithdrawAcctCfg>) payAcctRepository.save(entityList);
		return SvcResult.getSuccessInstance(entityList);

	}

	@Override
	public SvcResult crtOne(CustWithdrawAcctCfgDto dto) {
		List<CustWithdrawAcctCfgDto> dtoList = new ArrayList<>();
		dtoList.add(dto);
		return crtAll(dtoList);
	}

	@Override
	public char getPayTypeFromWithdrawAcct(String withdrawAcct) {
		if (withdrawAcct.startsWith("033") || withdrawAcct.startsWith("833")) {
			return PayType.OBU.getCode(); // return 3
		} else {
			return PayType.DBU.getCode(); // return 1
		}
	}

}
