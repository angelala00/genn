package com.cjteam.mrile.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/help")
public class HelpController extends BaseController {
	@RequestMapping("questions")
	public String help() {
		return "questions";
	}

	@RequestMapping("about")
	public String AboutUs() {
		return "about";
	}
}
