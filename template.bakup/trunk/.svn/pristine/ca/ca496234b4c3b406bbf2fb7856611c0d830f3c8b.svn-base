package com.cjteam.mrile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjteam.mrile.dao.IAppClientDao;
import com.cjteam.mrile.model.AppClient;

@Service
public class AppClientService extends BaseService {

	@Autowired
	private IAppClientDao appClientDao;

	public AppClient getOne() {
		return appClientDao.selectOneById("xiaohua");
	}

}
