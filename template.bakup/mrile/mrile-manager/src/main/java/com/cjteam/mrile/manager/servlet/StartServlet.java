package com.cjteam.mrile.manager.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.lang3.StringUtils;

public class StartServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		this.getServletContext().setAttribute("StringUtils", new StringUtils());
		this.getServletContext().setAttribute("WebUtil", new WebUtil());
		System.out.println("startservlet inited");
		super.init();
	}

}
