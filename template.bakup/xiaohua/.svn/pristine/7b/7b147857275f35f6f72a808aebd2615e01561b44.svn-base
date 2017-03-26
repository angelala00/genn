package com.cjteam.xiao.web.controller.admin;

import com.cjteam.xiao.manager.UserLoginManager;
import com.cjteam.xiao.model.Administrator;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
@Controller
@RequestMapping("/admin")
public class AdministratorController {
	private final static String  UPDATE_PASSWORD  = "/admin/updatePassword" ;
	@RequestMapping(value = "/updatePassword")
	public    String updatePassword(){
	        return this.UPDATE_PASSWORD ;  
	}
	@Autowired
	UserLoginManager userLoginManager ;
	@RequestMapping(value = "/subUpdatePassword")
	public String subUpdatePassword(String password , String qrpassword  , HttpServletRequest request  , Map<String,Object> map){
		String tmp  = null ;
		if (password.equals(qrpassword)){
			Administrator administrator =(Administrator)( (SecurityContext) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT")).getAuthentication().getPrincipal();
			administrator.setPassword(password);
			this.userLoginManager.createUser(administrator);
			map.put("msg","修改成功")  ;
		}
		else{
			map.put("msg","确认密码和密码不同");
		}
		return this.UPDATE_PASSWORD ;
	}
}
