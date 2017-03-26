package com.cjteam.xiao.service.impl;

import com.cjteam.xiao.model.SysConf;
import com.cjteam.xiao.repository.SysConfRepository;
import com.cjteam.xiao.service.SysConfService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by ChenLong on 14-3-8.
 */
@Service
@Transactional(readOnly = true)
public class SysConfServiceImpl implements SysConfService {
    @Override
    public Object getConfigValueByConfigCode(String appId, String configCode, Object valueContainer) {
        Object returnValue = null;
        SysConf conf = sysConfRepository.findByAppIdAndCode(appId,configCode);
        if (null == conf)
            return returnValue;
        String value = conf.getValue();
        if (StringUtils.isBlank(value))
            return returnValue;
        if (valueContainer instanceof Boolean) {
            returnValue = Boolean.valueOf(value);
        } else if (valueContainer instanceof Number) {
            if (StringUtils.isNumeric(value)) {
                returnValue = Integer.valueOf(value);
            }
        } else if (valueContainer instanceof Date) {

        } else {
            returnValue = value;
        }
        return returnValue;
    }

    @Autowired
    SysConfRepository sysConfRepository;
}
