package com.cjteam.mrile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cjteam.mrile.dao.IExchangeRecordDao;
import com.cjteam.mrile.dao.IUserDao;
import com.cjteam.mrile.model.ExchangeRecord;
import com.cjteam.mrile.model.User;

@Service
public class ExchangeRecordService extends BaseService {

	@Autowired
	private IExchangeRecordDao exchangeRecordDao;
	
	@Autowired
	private IUserDao userDao;

	public Page<ExchangeRecord> getListByUserId(Pageable pageableObj,
			String userId) {
		User user = userDao.selectOneByClientUserId(userId);
		return exchangeRecordDao.findByUserId(pageableObj, user.getUserId());
	}

}
