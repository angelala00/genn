package com.gj.course.web.servlet;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class ParamFilter
 */
public class ParamFilter implements Filter {

    /**
     * Default constructor. 
     */
    public ParamFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here


		if (!(request instanceof HttpServletRequest) || !(response instanceof HttpServletResponse)) {
			throw new ServletException("ParamFilter just supports HTTP requests");
		}
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		Enumeration<String> sss = httpRequest.getParameterNames();
		while (sss.hasMoreElements()) {
			String s = sss.nextElement();
			httpRequest.setAttribute(s, httpRequest.getParameter(s));
		}
		
		System.out.println("current_href" + WebUtil.getCurrentRequestUrl(httpRequest));
		request.setAttribute("current_href", WebUtil.getCurrentRequestUrl(httpRequest));
		request.setAttribute("util", "");
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
