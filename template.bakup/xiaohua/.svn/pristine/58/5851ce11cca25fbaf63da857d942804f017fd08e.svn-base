package com.cjteam.xiao.web.controller;

import com.cjteam.xiao.manager.BroadCastsManager;
import com.cjteam.xiao.model.CrapBet;
import com.cjteam.xiao.model.CrapIssue;
import com.cjteam.xiao.model.User;
import com.cjteam.xiao.service.CrapService;
import com.cjteam.xiao.service.SysConfService;
import com.cjteam.xiao.web.vo.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ChenLong on 14-2-23.
 */
@Controller
@RequestMapping(value = "/crap")
public class CrapController extends BaseController<CrapIssue> {
    private static final Logger LOG = LoggerFactory.getLogger(CrapController.class);

//    @Value("${sys.conf.homepage.recommend.channel.code}")
//    private String sysConfHomePageRecommendChannelCode;

    @RequestMapping(value = "/currentIssue")
    public
    @ResponseBody
    ResponseVo currentCrapIssue(@ModelAttribute User user) {
        CrapVo crapVo = new CrapVo();
        try {
            checkModelAttribute(user);

            String appId = user.getAppId();
            String userId = user.getUserId();
            user = userService.getOne(appId,userId);
            Assert.notNull(user, "user not found for param appId:" + appId + ",userId:" + userId + "");

            crapVo.setUserVo(new UserVo(user));
            
            CrapIssue issue = crapService.getCurrentIssue(appId);
            crapVo.setIssue(new CrapIssueVo(issue));
            crapVo.setSuccess(Boolean.TRUE);
            String recommendChannelCode =
                    (String) sysConfService.getConfigValueByConfigCode(appId, "homepage.recommend.channel-code", new String(""));
            crapVo.setRecommend(StringUtils.isBlank(recommendChannelCode) ? "" : recommendChannelCode);
            crapVo.setBroadcasts(broadCastsManager.fetchNews(appId));
        } catch (Throwable t) {
            crapVo.setMessage(t.getLocalizedMessage());
            LOG.error(t.getLocalizedMessage(),t);
        }
        return crapVo;
    }

    @RequestMapping(value = "/checkCoinsBet")
    public
    @ResponseBody
    ResponseVo checkCoinsBet(@ModelAttribute User user, int coinsBet) {
        ResponseVo responseVo = new ResponseVo();
        try {
            checkModelAttribute(user);
            crapService.assertCoinsBet(user.getAppId(),user.getUserId(), coinsBet);
            responseVo.setSuccess(true);
        } catch (Throwable t) {
            responseVo.setMessage(t.getLocalizedMessage());
            LOG.error(t.getLocalizedMessage());
        }
        return responseVo;
    }

    @RequestMapping(value = "/betBig")
    public
    @ResponseBody
    ResponseVo betBig(@ModelAttribute User user, Integer issueNo, int coinsBet) {
        ResponseVo responseVo = new ResponseVo();
        try {
            checkModelAttribute(user);
            crapService.betBig(user.getAppId(),user.getUserId(), issueNo, coinsBet);
            responseVo.setSuccess(true);
        } catch (Throwable t) {
            responseVo.setMessage(t.getLocalizedMessage());
            LOG.error(t.getLocalizedMessage());
        }
        return responseVo;
    }

    @RequestMapping(value = "/betSmall")
    public
    @ResponseBody
    ResponseVo betSmall(@ModelAttribute User user, Integer issueNo, int coinsBet) {
        ResponseVo responseVo = new ResponseVo();
        try {
            checkModelAttribute(user);
            crapService.betSmall(user.getAppId(),user.getUserId(), issueNo, coinsBet);
            responseVo.setSuccess(true);
        } catch (Throwable t) {
            responseVo.setMessage(t.getLocalizedMessage());
            LOG.error(t.getLocalizedMessage());
        }
        return responseVo;
    }
    @RequestMapping(value = "/issues")
    public
    @ResponseBody
    ResponseVo craps(@RequestParam(value = "appId", required = false) String appId, @RequestParam(value = "issue", required = false) Integer issue,
                     @RequestParam(value = "page", required = false, defaultValue = "0") Integer page) {
        CrapIssuesVo responseVo = new CrapIssuesVo();
        List<CrapIssue> issues = new ArrayList<CrapIssue>();
        try {
            if (null != issue) {
                CrapIssue one = crapService.getIssue(appId,issue);
                issues.add(one);
            } else {
                Pageable pageable = new PageRequest(0, 100, Sort.Direction.DESC, "startTime");// Pages are zero indexed
                Page<CrapIssue> issuesPage = crapService.getAllIssues(appId, pageable);
                if (null != issuesPage) {
                    issues = issuesPage.getContent();
                }
            }
            responseVo.setSuccess(Boolean.TRUE);
            responseVo.append(issues);
        } catch (Throwable t) {
            responseVo.setMessage(t.getLocalizedMessage());
            LOG.error(t.getLocalizedMessage(), t);
        }
        return responseVo;
    }
    @RequestMapping(value = "/history")
    public
    @ResponseBody
    ResponseVo history(@RequestParam(value = "appId", required = false) String appId, @RequestParam(value = "userId", required = true) String userId,
                       @RequestParam(value = "issue", required = false) Integer issue,
                       @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                       @RequestParam(value = "win",required = false,defaultValue ="0") Integer win) {
        CrapBetsVo responseVo = new CrapBetsVo();
        List<CrapBet> bets = null;
        try {
            if (null != issue) {
                bets = crapService.getIssueInfoByUserId(appId, issue, userId);
            } else {
                if(win.equals(0)){
                    Pageable pageable = new PageRequest(0, 100, Sort.Direction.DESC, "createTime");// Pages are zero indexed
                bets = crapService.getMyIssue(appId, userId, pageable);
                }else{
                    bets = crapService.getMyWinIssues(appId, userId);
                }
            }
            responseVo.setSuccess(Boolean.TRUE);
            responseVo.append(bets);
        } catch (Throwable t) {
            responseVo.setMessage(t.getLocalizedMessage());
            LOG.error(t.getLocalizedMessage());
        }
        return responseVo;
    }
    @Autowired
    private BroadCastsManager broadCastsManager;
    @Autowired
    private CrapService crapService;
    @Autowired
    private SysConfService sysConfService;
    @Override
    protected String getPrefixPath() {
        //todo
        return null;
    }
}