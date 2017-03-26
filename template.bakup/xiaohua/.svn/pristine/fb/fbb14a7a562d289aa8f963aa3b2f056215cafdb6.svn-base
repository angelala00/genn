package com.cjteam.xiao.duiba.service.impl;

import com.cjteam.xiao.duiba.model.DuibaAccount;
import com.cjteam.xiao.duiba.repository.DuibaAccountRepository;
import com.cjteam.xiao.duiba.service.DuibaAccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by chenlong on 2014/7/31.
 */
@Service
public class DuibaAccountServiceImpl implements DuibaAccountService {
    @Resource
    private DuibaAccountRepository duibaAccountRepository ;
    public DuibaAccount getByAppKey(String appKey){
        return this.duibaAccountRepository.findByAppKey(appKey) ;
    }
    public String getAppIdByAppKey (String appKey){
        DuibaAccount duibaAccount =  this.getByAppKey(appKey) ;
        if (duibaAccount!=null && duibaAccount.getAppDuibaAccountReference()!=null){
            return duibaAccount.getAppDuibaAccountReference().getAppId() ;
        }
        return null ;
    }
}
