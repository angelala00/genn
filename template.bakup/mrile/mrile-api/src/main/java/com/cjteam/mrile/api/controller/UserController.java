package com.cjteam.mrile.api.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cjteam.mrile.api.controller.vo.BaseResponseVo;
import com.cjteam.mrile.model.User;
import com.cjteam.mrile.service.UserService;

/**
 * 
 * @author Jiangchi
 *
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	@Autowired
	private UserService userService;

	@RequestMapping("/test")
	public @ResponseBody Object saveUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		BaseResponseVo rv = new BaseResponseVo();
		User user = userService.getOne(13769);
		rv.setContent(user);
		rv.setMessage("success");
		rv.setSuccess(true);
		return rv;
	}
}
