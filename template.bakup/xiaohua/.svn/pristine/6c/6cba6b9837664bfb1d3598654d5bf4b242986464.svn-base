package com.cjteam.xiao.repository;

import com.cjteam.xiao.model.IpLocation;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IpLocationRepository extends PagingAndSortingRepository<IpLocation, Long> {
    IpLocation findByIp(String ip);
}
