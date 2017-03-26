package com.cjteam.xiao.web.vo;

import org.apache.commons.collections.CollectionUtils;

import java.util.List;

/**
 * Created by ChenLong Date: 13-10-8
 */
public class UserTop10Response extends ResponseVo {
    private List<UserVo> users;

    public List<UserVo> getUsers() {
        return users;
    }

    public void setUsers(List<UserVo> users) {
        this.users = users;
    }

    @Override
    public int getCount() {
        return CollectionUtils.isEmpty(users) ? 0 : users.size();
    }
}
