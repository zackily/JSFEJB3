package cub.invest.aum.service.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import cub.invest.aum.service.entity.MgFeeMonth;

@RepositoryRestResource(collectionResourceRel = "mg-fee-month", path = "mg-fee-month")
public interface MgFeeMonthRepository extends PagingAndSortingRepository<MgFeeMonth, Long> {
	
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
	void delete(MgFeeMonth entity);
}
