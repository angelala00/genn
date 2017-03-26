package com.cjteam.xiao.web;

import com.cjteam.xiao.manager.UserLoginManager;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by ChenLong on 14-3-5.
 */
public class CustomerPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        return UserLoginManager.packagePassword(rawPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return UserLoginManager.matches(encodedPassword, rawPassword);
    }
}
