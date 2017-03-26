package com.cjteam.xiao.web.controller;

import java.util.Map;

import com.cjteam.xiao.model.FrontApp;
import com.cjteam.xiao.service.AppService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cjteam.xiao.service.VersionService;
import com.cjteam.xiao.web.vo.NewVersionResponse;

/**
 * Created by ChenLong Date: 13-10-8
 */
@Controller
@RequestMapping("/version")
public class VersionController {
    private static final Logger LOG = LoggerFactory.getLogger(VersionController.class);

    @RequestMapping(value = {"hasnew"})
    public
    @ResponseBody
    NewVersionResponse hasNewVersion(@RequestParam("appId") String appId) {
        NewVersionResponse response = new NewVersionResponse();
        try {
            FrontApp app = appService.getOne(appId);
            Assert.notNull(app, "client app not found");
            response.setUrl(app.getUpdateUrl());
            response.setVersionNum(app.getVersion());
            response.setPolicy(app.getPolicy());
            response.setSuccess(Boolean.TRUE);
        } catch (Throwable t) {
            LOG.error(t.getLocalizedMessage(), t);
            response.setMessage(t.getLocalizedMessage());

        }
        return response;
    }

    @Autowired
    private AppService appService;
}
