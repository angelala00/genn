package com.cjteam.xiao.web;

import com.google.common.collect.ImmutableList;
import com.cjteam.xiao.model.Administrator;
import com.cjteam.xiao.model.Role;
import com.cjteam.xiao.service.AdministratorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ChenLong on 14-3-3.
 */
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final Logger LOG = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    private static final List<Role> allRoles = ImmutableList.of(new Role("payer"), new Role("manager"), new Role("admin"));

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Administrator administrator = administratorService.findByUsername(username);
        if (null == administrator) {
            throw new UsernameNotFoundException(username);
        }
        judgeRole(administrator);
        LOG.info("the use {} has {} roles", administrator, administrator.getAuthorities().size());
        return administrator;
    }

    private void judgeRole(Administrator admin) {
        Integer role = admin.getRole();
        if (null == role)
            return;
        List<Role> authorities = new ArrayList<Role>();
        while (role-- > 0) {
            authorities.add(allRoles.get(role));
        }
        admin.setAuthorities(authorities);
    }

    @Autowired
    private AdministratorService administratorService;
}
