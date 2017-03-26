package com.cjteam.xiao.web.vo;

/**
 * Created by ChenLong
 * Date: 13-10-8
 */
public class UserResponse extends ResponseVo {
    private UserVo user;

    public UserVo getUser() {
        return user;
    }

    public void setUser(UserVo user) {
        this.user = user;
    }

    @Override
    public int getCount() {
        return user != null ? 1 : 0;
    }
}
