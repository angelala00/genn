package com.cjteam.xiao.web.filter;

import com.cjteam.xiao.manager.UserLoginManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {
    private static final Logger log = LoggerFactory.getLogger(LoginFilter.class);

    private String[] excludedUrls;

    @Override
    public void init(FilterConfig config) throws ServletException {
        String excludes = config.getInitParameter("excludedUrls");
        log.debug("excludes=" + excludes);

        if (excludes != null) {
            this.excludedUrls = excludes.split(",");
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String requestUri = httpRequest.getRequestURI();
        log.debug("doFilter for " + requestUri);

        for (String url : excludedUrls) {
            if (requestUri.startsWith(url.trim())) {
                log.debug("exclued");
                chain.doFilter(request, response);
                return;
            }
        }
        HttpSession session = httpRequest.getSession(true);
        if (session.getAttribute(UserLoginManager.USER_SESSION_NAME) == null) {
            log.debug("user should login first. ");
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
        } else {
            chain.doFilter(request, response);
            return;
        }
    }

    @Override
    public void destroy() {
    }

    public String[] getExcludedUrls() {
        return excludedUrls;
    }

    public void setExcludedUrls(String[] excludedUrls) {
        this.excludedUrls = excludedUrls;
    }
}