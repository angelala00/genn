package com.cjteam.mrile.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cjteam.mrile.model.ExchangeRecord;

public interface IExchangeRecordDao extends IBaseDao<ExchangeRecord, Integer> {

	Page<ExchangeRecord> findByUserId(Pageable pageableObj, String userId);

}