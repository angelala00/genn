package com.gj.course.manager.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController extends BaseController {

	@RequestMapping("/login")
	public String login(){
		return "login";
	}
	@RequestMapping("/index")
	public String index(){
		return "index";
	}
	@RequestMapping({"","/","/main"})
	public String main(){
		return "main";
	}
}
