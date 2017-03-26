package com.cjteam.xiao.service.impl;

import com.cjteam.xiao.model.IpUserLimit;
import com.cjteam.xiao.model.User;
import com.cjteam.xiao.repository.IpUserLimitRepository;
import com.cjteam.xiao.service.IpUsersLimitService;
import com.cjteam.xiao.service.SysConfService;
import com.cjteam.xiao.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ChenLong
 * Date: 13-12-16
 */
@Service
@Transactional
public class IpUsersLimitServiceImpl implements IpUsersLimitService {
    private static final Logger LOG = LoggerFactory.getLogger(IpUsersLimitServiceImpl.class);
    @Value("${sys.conf.ip.user.limit}")
    private String sysconf_ip_user_limit;

    @Override
    public boolean control(String xRealIp, String appId, String userId) {
        LOG.info("param xRealIp is {},app id is {} ,userId is {},client app id is {}", xRealIp, appId, userId, appId);
        if (StringUtils.isBlank(appId)) {
            //todo
            //param is error
        }
        if (StringUtils.isEmpty(xRealIp) || StringUtils.isEmpty(userId))
            return false;

        User user = userService.getOne(appId, userId);
        if (null == user) {
            LOG.info("app {} 's user {} not exist", appId,userId);
            return false;
        }

        if (user.getIsBlack()) {
            LOG.info("app {} ' s user {} is in black list already", appId,userId);
            return false;
        }

        userService.updateLastLoginIp(user, xRealIp);

        IpUserLimit currentUserRecord = ipUserLimitRepository.findByAppIdAndIpAndUser_userId(user.getAppId(), xRealIp, userId);
        if (null == currentUserRecord) {
            LOG.info("new ip accessor ,{}-{}-{}", appId,userId, xRealIp);
            insertNewUserAccessRecord(user.getAppId(), xRealIp, userId);
            currentUserRecord = ipUserLimitRepository.findByAppIdAndIpAndUser_userId(user.getAppId(),xRealIp, userId);
        }

        if (null == currentUserRecord) {
            LOG.info("accessor not recorded");
            return false;
        }
        Integer limit = (Integer) sysConfService.getConfigValueByConfigCode(appId, sysconf_ip_user_limit, new Integer(0));
        if (null == limit || limit < 0) {
            LOG.info("ip user limit not config, or is not a valid numeric");
            return false;
        }

        if (currentUserRecord.getTag() >= limit) {
            LOG.info("app {} 's user {} at ip {} exceed limit {}", appId,userId, xRealIp, sysconf_ip_user_limit);
            userService.blackUser(user.getId());
            return true;
        }
        return false;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, timeout = 100)
    private void insertNewUserAccessRecord(String appId, String xRealIp, String userId) {
        ipUserLimitRepository.insertNewUserAccessRecord(appId,xRealIp, userId);
    }

    @Autowired
    IpUserLimitRepository ipUserLimitRepository;
    @Autowired
    SysConfService sysConfService;
    @Autowired
    UserService userService;
}
