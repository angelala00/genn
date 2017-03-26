package com.cjteam.xiao.web.controller;

import com.cjteam.xiao.model.User;
import com.cjteam.xiao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ChenLong Date: 13-9-26
 */
public abstract class BaseController<T> {
    protected void checkModelAttribute(User user) {
        Assert.notNull(user, "invalid param");
        Assert.notNull(user.getAppId(),"missing appid param");
//        Assert.hasText(user.getMac(), "missing mac param");
//        Assert.hasText(user.getOpenUdid(), "missing openudid param");
//        Assert.hasText(user.getToken(), "missing token param");
        Assert.notNull(user.getUserId(), "missing userId param");
//        Assert.isTrue(userService.userInfoIsValid(user), "params wrong. mac,token and openUdid not matched to userId");
    }


    protected String getViewPath(String suffixPath) {
        return "/admin" + getPrefixPath() + "/" + suffixPath;
    }

    protected String getAppIdParam(HttpServletRequest request) {
        return request.getParameter("appId");
    }

    protected abstract String getPrefixPath();

    @Autowired
    protected UserService userService;
}
