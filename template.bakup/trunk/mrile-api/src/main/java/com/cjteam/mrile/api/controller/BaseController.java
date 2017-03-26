package com.cjteam.mrile.api.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Jiangchi
 * 
 */
public abstract class BaseController {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	protected ServletContext servletContext;

	protected String getAppIdParam(HttpServletRequest request) {
		return request.getParameter("appId");
	}

}
