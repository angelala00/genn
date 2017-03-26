package com.cjteam.xiao.manager;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.cjteam.xiao.model.Administrator;
import com.cjteam.xiao.model.DoubleNineAgent;
import com.cjteam.xiao.repository.AdministratorRepository;

/**
 * Created by ChenLong
 * Date: 13-11-5
 */
@Component
@Transactional
public class UserLoginManager {
    private static final Logger log = LoggerFactory.getLogger(UserLoginManager.class);

    private static final String userpasswordmd5key = "dm-password-yxu";
    public static final String USER_SESSION_NAME = "user";
    
    public Administrator login(Administrator admin) {
        Administrator existAdmin = adminRepository.queryByUserName(admin.getUsername());
        Assert.notNull(existAdmin, "root user " + admin.getUsername() + " not exist.");
        Assert.isTrue(existAdmin.getIsValid(), "root user " + admin.getUsername() + " was locked.");
        judgePassword(existAdmin.getPassword(), admin.getPassword());
        return existAdmin;
    }

    public static void judgePassword(String expected, String actual) {
        Assert.isTrue(StringUtils.equals(expected, packagePassword(actual)), "password error");
    }

    public static boolean matches(CharSequence expected, CharSequence actual) {
        return StringUtils.equals(expected.toString(), packagePassword(actual));
    }

    public static String packagePassword(CharSequence actual) {
        try {
            String password =  DoubleNineAgent.md5Digest(userpasswordmd5key + actual + userpasswordmd5key);
            return password ;
        } catch (Exception e) {
            log.error(e.getLocalizedMessage(), e);
            throw new RuntimeException("sys error : " + e.getLocalizedMessage(), e);
        }
    }

    public Administrator createUser(Administrator newAdmin) {
        newAdmin.setPassword(packagePassword(newAdmin.getPassword()));
        adminRepository.save(newAdmin);
        return newAdmin;
    }

    public void updateAdmin(Administrator loginUser) {
        adminRepository.save(loginUser);
    }

    @Autowired
    AdministratorRepository adminRepository;
}
