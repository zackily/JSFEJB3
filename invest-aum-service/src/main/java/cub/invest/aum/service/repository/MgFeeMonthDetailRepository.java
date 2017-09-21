package cub.invest.aum.service.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import cub.invest.aum.service.entity.MgFeeMonthDetail;

@RepositoryRestResource(collectionResourceRel = "mg-fee-month-detail", path = "mg-fee-month-detail")
public interface MgFeeMonthDetailRepository extends PagingAndSortingRepository<MgFeeMonthDetail, Long> {
	
	/**
	 * exclude delete method
	 */
	@Override
	@RestResource(exported = false)
	void delete(Long id);

	/**
	 * exclude delete method
	 */
	@Override
	@RestResource(exported = false)
	void delete(MgFeeMonthDetail entity);
}
