package com.cjteam.xiao.web.filter;

import com.cjteam.xiao.model.FrontApp;
import com.cjteam.xiao.service.AppService;
import com.cjteam.xiao.service.IpUsersLimitService;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
//import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IpFilter implements Filter {
    private static final Logger LOG = LoggerFactory.getLogger(IpFilter.class);
    private static final Logger ACCESS_LOG = LoggerFactory.getLogger(IpFilter.class + ".ACCESS");

    private List<String> filterUrls =new ArrayList<String>();
    private IpUsersLimitService ipUsersLimitService;
    private AppService appService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String requestUri = httpRequest.getRequestURI();
        ACCESS_LOG.info("{}\t{}", DateTime.now().toString("yyyy-MM-dd HH:mm:ss"), requestUri);
        String appId = httpRequest.getParameter("appId");
        LOG.debug("doFilter for {} of client app {} ", requestUri,appId);

        updateSessionApp(httpRequest,appId);

        if (CollectionUtils.isNotEmpty(filterUrls)) {
            for (String url : filterUrls) {
                if (requestUri.startsWith(url)) {
                    String xRealIp = httpRequest.getHeader("X-Real-IP");
                    String userId = httpRequest.getParameter("userId");
                    ACCESS_LOG.info("app {} 's user {} exceed ip {} 's limit of request uri is {}", appId, userId, xRealIp, requestUri);
                    if (StringUtils.isBlank(userId)) {
//                        httpRequest.getParameterMap().put("ip", new String[]{xRealIp});
                    } else {
                        if (ipUsersLimitService.control(xRealIp,appId,  userId)) {
                            LOG.info("user {} exceed ip {} 's limit ", userId, xRealIp);
                        }
                    }
                }
            }
        }
    	chain.doFilter(request, response);
        return;
    }

    private static final String appInfo = "appinfo";
    private void updateSessionApp(HttpServletRequest httpRequest, String appId) {
        try {
            if (StringUtils.isBlank(appId))
                return;
            FrontApp app = appService.getOne(appId);
            if (app == null)
                return;
            FrontApp currentAppSession = (FrontApp) httpRequest.getSession().getAttribute(appInfo);
            if (currentAppSession == null || !StringUtils.equals(currentAppSession.getId(), app.getId())) {
                httpRequest.getSession().setAttribute(appInfo, app);
                LOG.info("update session app info {}", app.toString());
            }
        } catch (Throwable t) {
            LOG.error(t.getLocalizedMessage());
        }
    }

    @Override
    public void destroy() {
    }

    public List<String> getFilterUrls() {
        return filterUrls;
    }

    public void setFilterUrls(List<String> filterUrls) {
        this.filterUrls = filterUrls;
    }

    public void setIpUsersLimitService(IpUsersLimitService ipUsersLimitService) {
        this.ipUsersLimitService = ipUsersLimitService;
    }

    public void setAppService(AppService appService) {
        this.appService = appService;
    }
}