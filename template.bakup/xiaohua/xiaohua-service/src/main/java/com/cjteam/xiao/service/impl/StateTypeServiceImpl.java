package com.cjteam.xiao.service.impl;

import com.cjteam.xiao.model.StateType;
import com.cjteam.xiao.repository.StateTypeRepository;
import com.cjteam.xiao.service.StateTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ChenLong
 * Date: 13-12-12
 */
@Service
@Transactional(readOnly = true)
public class StateTypeServiceImpl implements StateTypeService {
    @Override
    public StateType getStateTypeByCode(String code) {
        return stateTypeRepository.findByCode(code);
    }

    @Autowired
    StateTypeRepository stateTypeRepository;
}
