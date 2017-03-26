package com.cjteam.xiao.web.controller;

import com.cjteam.xiao.manager.DataFixManager;
import com.cjteam.xiao.model.*;
import com.cjteam.xiao.repository.AboutRepository;
import com.cjteam.xiao.repository.HelpsRepository;
import com.cjteam.xiao.repository.SysMessageRepository;
import com.cjteam.xiao.service.*;
import com.cjteam.xiao.service.doublenine.impl.mobile.TelephoneRegionResponse;
import com.cjteam.xiao.web.vo.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ChenLong
 * Date: 14-2-10
 */
@Controller
@RequestMapping("/sys")
public class SystemController extends BaseController<SysConf> {
    private static final Logger LOG = LoggerFactory.getLogger(SystemController.class);

    @Value("${sys.conf.banner.ad.visible}")
    private String sys_banner_ad_visible;
    @Value("${sys.conf.coinsmall.button.visible}")
    private String sys_coinsmall_button_visible;

    @RequestMapping(value = {"/showCoinsMall"})
    public
    @ResponseBody
    ResponseVo controlCoinsMallButton(@RequestParam(value = "appId", required = false) String appId) {
        ResponseVo response = new ResponseVo();
        try {
            Boolean config = new Boolean(Boolean.FALSE);
            config = (Boolean) sysConfService.getConfigValueByConfigCode(appId, sys_coinsmall_button_visible, config);
            response.setSuccess(config);
        } catch (Throwable t) {
            LOG.error(t.getLocalizedMessage());
        }
        return response;
    }

    @RequestMapping(value = {"/bannerad"})
    public
    @ResponseBody
    BannerADVisibilityResponse controlBannerAD(@RequestParam(value = "appId", required = false) String appId) {
        BannerADVisibilityResponse response = new BannerADVisibilityResponse();
        try {
            Boolean config = new Boolean(Boolean.FALSE);
            config = (Boolean) sysConfService.getConfigValueByConfigCode(appId, sys_banner_ad_visible, config);
            response.setSuccess(config);
        } catch (Throwable t) {
            LOG.error(t.getLocalizedMessage());
        }
        return response;
    }

    @RequestMapping(value = {"/message"})
    public
    @ResponseBody
    ResponseVo message(@RequestParam(value = "appId", required = false) String appId) {
        StringListResponseVo response = new StringListResponseVo();
        try {
            Iterable<SysMessage> messages = sysMessageRepository.findByAppId(appId);

            if (null != messages) {
                for (SysMessage message : messages) {
                    if (org.apache.commons.lang3.StringUtils.isNotBlank(message.getContent())) {
                        response.append(message.getContent());
                    }
                }
            }
            response.setSuccess(Boolean.TRUE);
        } catch (Throwable t) {
            LOG.error(t.getLocalizedMessage());
        }
        return response;
    }

    /**
     * 改 “关于我们”接口，直接返回相关的信息的json串，client app处理显示view
     *
     * @param appId
     * @return
     */
    @RequestMapping(value = {"/about/s"})
    public
    @ResponseBody
    ResponseVo abouts(@RequestParam(value = "appId") String appId) {
        AboutsResponse response = new AboutsResponse();
        try {
            response.append(aboutRepository.findByAppId(appId));
            response.setSuccess(Boolean.TRUE);
        } catch (Throwable t) {
            LOG.error(t.getLocalizedMessage(),t);
        }
        return response;
    }


    @RequestMapping(value = {"/about/"})
    public String about(@RequestParam(value = "appId") String appId) {
        FrontApp app = null;
        try {
            app = appService.getOne(appId);
            if (app == null) {
                throw new RuntimeException("客户端应用没有注册，系统不识别，请联系后台管理员");
            }
        } catch (Throwable t) {
            LOG.error(t.getLocalizedMessage());
        }
        return StringUtils.isBlank(app.getAboutPage()) ? "about" : app.getAboutPage();
    }


    @RequestMapping(value = {"/helps"})
    public
    @ResponseBody
    ResponseVo helps(@RequestParam(value = "appId", required = false) String appId) {
        HelpsVo response = new HelpsVo();
        try {
            List<Helps> messages = helpsRepository.findByAppIdAndValidTrueOrderByOrderDesc(appId);

            if (CollectionUtils.isNotEmpty(messages)) {
                response.setHelpVos(new ArrayList<HelpVo>(messages.size()));
                for (Helps message : messages) {
                    if (org.apache.commons.lang3.StringUtils.isNotBlank(message.getContent())) {
                        response.append(new HelpVo(message));
                    }
                }
            }
            response.setSuccess(Boolean.TRUE);
        } catch (Throwable t) {
            LOG.error(t.getLocalizedMessage());
        }
        return response;
    }

    @RequestMapping(value = {"/cleanAppCache"})
    public
    @ResponseBody
    String cleanAppCache(@RequestParam(value = "appId", required = false) String appId) {
        appService.cleanCache(appId);
        return "OK";
    }

    @RequestMapping(value = {"/appInfo"})
    public
    @ResponseBody
    FrontApp appInfo(@RequestParam(value = "appId", required = false) String appId) {
        return appService.getOne(appId);
    }

    @RequestMapping(value = {"/suggestion"})
    public
    @ResponseBody
    ResponseVo suggestion(@RequestParam(value = "appId", required = false) String appId, @RequestParam(value = "userId", required = true) String userId, @RequestParam(value = "suggestion", required = true) String suggestion) {
        ResponseVo response = new ResponseVo();
        try {
            suggestionService.addNew(appId, userId, suggestion);
            response.setSuccess(Boolean.TRUE);
        } catch (Throwable t) {
            LOG.error(t.getLocalizedMessage());
        }
        return response;
    }

    @RequestMapping(value = {"/mobile-region"})
    public
    @ResponseBody
    ResponseVo
    mobileRegionQuery(@RequestParam(value = "appId", required = false) String appId, @RequestParam(value = "mobile", required = true) String mobile) {
        MobileRegionVo regionVo = new MobileRegionVo();
        try {
            TelephoneRegionResponse regionResponse = doubleNineService.queryRegion(appId, mobile);
            if (regionResponse != null) {
                regionVo.setMobile(regionResponse.getMobile());
                regionVo.setProvider(regionResponse.getProvider());
                regionVo.setProvince(regionResponse.getProvince());
                regionVo.setCity(regionResponse.getCity());
                regionVo.setSuccess(Boolean.TRUE);
            }
        } catch (Throwable t) {
            LOG.error(t.getLocalizedMessage(), t);
            regionVo.setMobile(t.getLocalizedMessage());
        }
        return regionVo;
    }

    @RequestMapping(value = {"/initCrapIssue"})
    public
    @ResponseBody
    ResponseVo
    initCrapIssue(@RequestParam(value = "appId", required = false) String appId) {
        ResponseVo responseVo = new ResponseVo();
        try {
            crapService.initHodiernalIssues(appId);
            responseVo.setSuccess(Boolean.TRUE);
        } catch (Throwable t) {
            LOG.error(t.getLocalizedMessage(), t);
            responseVo.setMessage(t.getLocalizedMessage());
        }
        return responseVo;
    }

    @RequestMapping(value = {"/about/qq"})
    public
    @ResponseBody
    ResponseVo aboutInfoQQ(@RequestParam(value = "appId", required = false) String appId) {
        ResponseVo responseVo = new ResponseVo();
        try {
            String qqNumber = (String) sysConfService.getConfigValueByConfigCode(appId, "about-page.info.qq", new String());
            responseVo.setSuccess(Boolean.TRUE);
            responseVo.setMessage(qqNumber);
        } catch (Throwable t) {
            LOG.error(t.getLocalizedMessage(), t);
            responseVo.setMessage(t.getLocalizedMessage());
        }
        return responseVo;
    }

    @RequestMapping(value = {"/datafix"})
    public
    @ResponseBody
    ResponseVo dataFix(HttpServletRequest request) {
        ResponseVo responseVo = new ResponseVo();
        try {
            dataFixManager.doFix(getAppIdParam(request));
            responseVo.setSuccess(Boolean.TRUE);
        } catch (Exception e) {
            LOG.error(e.getLocalizedMessage(), e);
            responseVo.setMessage(e.getLocalizedMessage());
        }
        return responseVo;
    }

    @RequestMapping(value = {"/exchangeMoneyUserLostScore"})
    public
    @ResponseBody
    ResponseVo exchangeMoneyUserLostScore() {
        ResponseVo responseVo = new ResponseVo();
        try {
            scoreCollector.doMoneyExchange();
            responseVo.setSuccess(Boolean.TRUE);
        } catch (Exception e) {
            LOG.error(e.getLocalizedMessage(), e);
            responseVo.setMessage(e.getLocalizedMessage());
        }
        return responseVo;
    }

    @RequestMapping(value = {"/exchangeFunctionController"})
    public
    @ResponseBody
    ExchangeFunctionControllerVo exchangeFunctionController(HttpServletRequest request) {
        ExchangeFunctionControllerVo responseVo = new ExchangeFunctionControllerVo();
        try {
            responseVo.setQq((Integer) sysConfService.getConfigValueByConfigCode(getAppIdParam(request), "exchange_function_controller_qq", new Integer(1)));
            responseVo.setMobile((Integer) sysConfService.getConfigValueByConfigCode(getAppIdParam(request), "exchange_function_controller_mobile", new Integer(1)));
            responseVo.setAlipay((Integer) sysConfService.getConfigValueByConfigCode(getAppIdParam(request), "exchange_function_controller_alipay", new Integer(1)));
            responseVo.setSuccess(Boolean.TRUE);
        } catch (Exception e) {
            LOG.error(e.getLocalizedMessage(), e);
            responseVo.setMessage(e.getLocalizedMessage());
        }
        return responseVo;
    }

    @Override
    protected String getPrefixPath() {
        //todo
        return null;
    }

    @Autowired
    DoubleNineService doubleNineService;
    @Autowired
    DataFixManager dataFixManager;
    @Autowired
    ScoreCollector scoreCollector;
    @Autowired
    SysConfService sysConfService;
    @Autowired
    HelpsRepository helpsRepository;
    @Autowired
    SysMessageRepository sysMessageRepository;
    @Autowired
    SuggestionService suggestionService;
    @Autowired
    CrapService crapService;
    @Autowired
    AppService appService;
    @Autowired
    AboutRepository aboutRepository;
}
