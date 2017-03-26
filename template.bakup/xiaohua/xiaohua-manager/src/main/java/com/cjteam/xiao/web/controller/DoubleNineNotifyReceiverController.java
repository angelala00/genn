package com.cjteam.xiao.web.controller;

import com.cjteam.xiao.service.DoubleNineService;
import com.cjteam.xiao.service.doublenine.model.DNMobileNotify;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.Writer;

/**
 * Created by ChenLong
 * Date: 13-10-29
 */
@Controller
@RequestMapping("/dn")
public class DoubleNineNotifyReceiverController {
    private static final Logger log = LoggerFactory.getLogger(DoubleNineNotifyReceiverController.class);

    @RequestMapping(value = {"/charge-notify"})
    public void mobileNotify(@RequestParam(value = "appId", required = false) String appId, @ModelAttribute DNMobileNotify mobileNotify, HttpServletResponse response) {
        log.info("dn charge notify params :" + mobileNotify.toString());
        try {
            doubleNineService.progressChargeResult(appId,mobileNotify);
            log.debug("charge notify progress successfully");
            Writer writer = response.getWriter();
            writer.write("ok");
            writer.close();
        } catch (Throwable t) {
            log.info("progress charge notify information occur an error.");
            log.error(t.getLocalizedMessage());
        }finally {
            return;
        }
    }

    @Autowired
    DoubleNineService doubleNineService;

}
