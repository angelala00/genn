package com.cjteam.xiao.repository;

import com.cjteam.xiao.model.ExchangeRecord;
import com.cjteam.xiao.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by chenlong on 2014/8/13.
 */
public interface ExchangeRecordRepository extends JpaRepository<ExchangeRecord,Integer> {
    Page<ExchangeRecord>  findByUserUserId(Pageable pageable , String userId ) ;
}
