package com.cjteam.xiao.service.impl;

import com.cjteam.xiao.model.Administrator;
import com.cjteam.xiao.repository.AdministratorRepository;
import com.cjteam.xiao.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * Created by ChenLong
 * Date: 13-11-5
 */
@Service
@Transactional
public class AdministratorServiceImpl implements AdministratorService {

    @Override
    public Administrator findByUsername(String username) {
        return administratorRepository.findByUsername(username);
    }

    @Autowired
    AdministratorRepository administratorRepository;
}
