package com.cjteam.xiao.web.controller;

import com.cjteam.xiao.manager.UserLoginManager;
import com.cjteam.xiao.model.Administrator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by ChenLong
 * Date: 13-11-5
 */
@Controller
@RequestMapping("/user/login")
public class LoginController {
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value = "/in")
    public String login(@ModelAttribute Administrator admin, ModelMap modelMap, HttpServletRequest request) {
        Administrator loginUser = null;
//        try {
//            Assert.notNull(admin, "invalid param");
//            Assert.hasText(admin.getUsername(), "invalid param");
//            Assert.hasLength(admin.getPassword(), "invalid param");
//            loginUser = loginManager.login(admin);
//            Assert.notNull(loginUser, "username and password not matched or not exist.");
//            request.getSession().setAttribute(UserLoginManager.USER_SESSION_NAME, loginUser);
//            loginUser.setLastestLoginTime(new Date());
//            loginManager.updateAdmin(loginUser);
//        } catch (Throwable t) {
//            modelMap.put("msg", t.getMessage());
//            return "/login";
//        }
//        log.info(" {} login successfully.", loginUser);

        return "redirect:" + "/admin";
    }

    @RequestMapping(value = "/out")
    public String loginOut(ModelMap modelMap, HttpServletRequest request) {
        Administrator loginUser = null;
        try {
            loginUser = (Administrator) request.getSession().getAttribute(UserLoginManager.USER_SESSION_NAME);
            request.getSession().removeAttribute(UserLoginManager.USER_SESSION_NAME);
        } catch (Throwable t) {
            modelMap.put("msg", t.getMessage());
            return "/login";
        }
        log.info(" {} login out successfully.", loginUser);
        return "redirect:" + request.getContextPath() + "/admin/welcome";
    }

    @RequestMapping(value = "/fail")
    public String loginFail(ModelMap modelMap, HttpServletRequest request) {
        modelMap.addAttribute("msg", "true");
        return "/login";
    }
}
