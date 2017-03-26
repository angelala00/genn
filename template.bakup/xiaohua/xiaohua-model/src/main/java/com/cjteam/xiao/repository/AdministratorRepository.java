package com.cjteam.xiao.repository;

import com.cjteam.xiao.model.Administrator;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by ChenLong
 * Date: 13-11-5
 */
public interface AdministratorRepository extends PagingAndSortingRepository<Administrator, Integer> {
    @Query("from Administrator a where a.username=:username")
    Administrator queryByUserName(@Param(value = "username") String username);

    Administrator findByUsername(String username);
}
