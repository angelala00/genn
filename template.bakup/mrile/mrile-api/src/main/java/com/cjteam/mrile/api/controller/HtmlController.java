package com.cjteam.mrile.api.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cjteam.mrile.service.UserService;

/**
 * 
 * @author Jiangchi
 *
 */
@Controller
@RequestMapping("")
public class HtmlController extends BaseController {

	@Autowired
	private UserService userService;

	@Value("${mrile.host}")
	private String host;

	@RequestMapping("/html/help")
	public @ResponseBody Object help(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter w = response.getWriter();
		w.write("http://" + host + "/help/questions");
		w.flush();
		w.close();
		return null;
	}

	@RequestMapping("/html/aboutUs")
	public @ResponseBody Object aboutUs(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter w = response.getWriter();
		w.write("http://" + host + "/help/about");
		w.flush();
		w.close();
		return null;
	}
}
