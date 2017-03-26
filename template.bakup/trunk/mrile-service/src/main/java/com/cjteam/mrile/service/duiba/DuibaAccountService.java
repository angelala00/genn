package com.cjteam.mrile.service.duiba;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjteam.mrile.dao.IDuibaAccountDao;
import com.cjteam.mrile.model.DuibaAccount;
import com.cjteam.mrile.service.BaseService;

@Service
public class DuibaAccountService extends BaseService {

	@Autowired
	private IDuibaAccountDao duibaAccountDao;

	public DuibaAccount getOne(int id) {
		return duibaAccountDao.selectOneById(id);
	}
}
