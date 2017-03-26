package com.cjteam.xiao.web.controller;

import com.cjteam.xiao.model.User;
import com.cjteam.xiao.service.AppService;
import com.cjteam.xiao.service.UserService;
import com.cjteam.xiao.web.vo.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ChenLong Date: 13-9-26
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController<User> {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
//    @Autowired
//    private ZuiquanUserTaskService zuiquanUserTaskService ;
    @RequestMapping(value = "/info")
    public
    @ResponseBody
    UserResponse info(@RequestParam String appId, @RequestParam String userId) {
        UserResponse response = new UserResponse();
        User user = userService.getOne(appId, userId);
        if (null == user) {
            response.setMessage("user is not exist");
        } else {
//            this.zuiquanUserTaskService.generateUserTask(user);
            this.userService.updateLastLoginTime(user);
            UserVo userv = new UserVo(user);
            response.setUser(userv);
            response.setSuccess(Boolean.TRUE);
        }
        return response;
    }

    private  String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
    @Value("${ios.version}")
    private  String version ;

    @RequestMapping(value = "/init")
    public
    @ResponseBody
    UserResponse init(@ModelAttribute User user, HttpServletRequest request) {

        UserResponse response = new UserResponse();
        try {
            Assert.hasText(user.getMac(), "missing mac param");
            Assert.hasText(user.getOpenUdid(), "missing openudid param");
           // Assert.hasText(user.getToken(), "missing token param");

            UserVo userv = null;

            String deviceUdid = request.getParameter("udidi");
            if (org.apache.commons.lang3.StringUtils.isNotBlank(deviceUdid)) {
                user.setDeviceUdid(deviceUdid);
            }

            if (StringUtils.isNotBlank(user.getUserId())) {
                if (userService.userInfoIsValid(user)) {
                    log.info("init request contains userId param.");
                    User existUser = userService.getOne(user.getAppId(), user.getUserId());
                    if (null != existUser) {
                        userv = new UserVo(existUser);
                    } else {
                        log.warn("get user by param userId ,returns null.");
                        user.setUserId(null);
                    }
                } else {
                    log.info("init user request userId does not match with [mac,token,openUdid]");
                    user.setUserId(null);
                }
            }
            if (null == userv) {
                user.setIp(this.getIpAddr(request));
                if (org.apache.commons.lang3.StringUtils.isBlank(user.getIp()) || org.apache.commons.lang3.StringUtils.equals("error", user.getIp())) {
                    throw new RuntimeException("无法获得您的IP数据,请检查您的网络和操作方式");
                }
                userv = new UserVo(userService.create(user));
            }
            userv.setVersion(this.version);
            response.setUser(userv);
            response.setSuccess(Boolean.TRUE);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage(), e);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "/update")
    public
    @ResponseBody
    UserResponse update(User user, @RequestParam(required = false) String telphone,
                        @RequestParam(required = false) String newpsw,
                        @RequestParam(required = false) String newNick,
                        @RequestParam(required = false) String newqq,
                        @RequestParam(required = false) String alipayNo) {
        UserResponse response = new UserResponse();
        try {
            checkModelAttribute(user);

            User latestUser = userService.updateUserInfo(user.getAppId(), user.getUserId(), telphone, newpsw, newNick, alipayNo,newqq);
            UserVo userv = null;
            if (latestUser != null) {
                userv = new UserVo(latestUser);
                response.setSuccess(Boolean.TRUE);
                response.setUser(userv);
            } else {
                throw new IllegalArgumentException("user is not exist");
            }
        } catch (Throwable t) {
            log.error(t.getLocalizedMessage(), t);
            response.setMessage(t.getMessage());
        }
        return response;
    }

    @Override
    protected String getPrefixPath() {
        //todo
        return null;
    }

    @Autowired
    private UserService userService;
    @Autowired
    private AppService appService;
}
