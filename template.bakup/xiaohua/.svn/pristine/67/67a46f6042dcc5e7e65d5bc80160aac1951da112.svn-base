package com.cjteam.xiao.repository;

import com.cjteam.xiao.model.Withdraw;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository("withdrawRepository")
public interface WithdrawRepository extends PagingAndSortingRepository<Withdraw, Integer> {
    Page<Withdraw> findByAppIdAndUser_NickName(String appId, String user_NickName, Pageable pageable);

    Page<Withdraw> findByAppId(String appId, Pageable pageable);
}
