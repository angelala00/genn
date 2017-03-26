package com.cjteam.xiao.web.controller;

import com.cjteam.xiao.model.FrontApp;
import com.cjteam.xiao.repository.HelpsRepository;
import com.cjteam.xiao.service.AppService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/page")
public class HelpPageController {
    private static final Logger LOG = LoggerFactory.getLogger(HelpPageController.class);

    @RequestMapping(value = "/help")
    public String info(@RequestParam(value = "appId") String appId, ModelMap modelMap) {
        FrontApp app = null;
        try {
            app = appService.getOne(appId);
            if (app == null) {
                throw new RuntimeException("客户端应用没有注册，系统不识别，请联系后台管理员");
            }
            modelMap.put("helps", helpsRepository.findByAppIdAndValidTrueOrderByOrderDesc(appId));
        } catch (Throwable t) {
            LOG.error(t.getLocalizedMessage());
        }
        return StringUtils.isBlank(app.getHelpPage()) ? "help" : app.getHelpPage();
    }

    @Autowired
    HelpsRepository helpsRepository;
    @Autowired
    AppService appService;
}
