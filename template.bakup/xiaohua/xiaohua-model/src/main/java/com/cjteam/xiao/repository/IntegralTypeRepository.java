package com.cjteam.xiao.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cjteam.xiao.model.IntegralType;

@Repository
public interface IntegralTypeRepository extends PagingAndSortingRepository<IntegralType, Long> {
	@Query("from IntegralType it where it.code = :code")
	IntegralType findOneByCode(@Param("code") String string);
}
