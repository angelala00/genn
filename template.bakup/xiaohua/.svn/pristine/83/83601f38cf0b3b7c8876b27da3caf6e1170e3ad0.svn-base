package com.cjteam.xiao.web.controller.platform.scoreinterface;

import com.cjteam.xiao.model.Integral;
import com.cjteam.xiao.model.User;
import com.cjteam.xiao.service.AppService;
import com.cjteam.xiao.service.IntegralService;
import com.cjteam.xiao.service.ScoreCollector;
import com.cjteam.xiao.web.vo.ResponseVo;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

/**
 * Created by ChenLong on 2014/4/2.
 */
@Controller
public class ScoreInterfaceController extends ScoreInterfaceBaseController {
    private static final Logger LOG = LoggerFactory.getLogger(ScoreInterfaceController.class);
    private static final Logger LOG_INTEGRAL = LoggerFactory.getLogger(ScoreInterfaceController.class.getName() + ".INTEGRAL");
    private static LinkedList<String> orders = new LinkedList<String>();
    private static final String lockerShare = "SHARE";
    private static final String WANPU = "WANPU";
    private static final String MOPAN = "MOPIAN";
    private static final String XINGYUN = "XINGYUN";
    private static final String GUOMOB = "GUOMENG";
    private static final String CHUKONG = "CHUKONG";
    private static final String ADVIEW = "ADVIEW";
    private static final String ADSQ = "AIDESIQI";
    private static final String YOUMI = "YOUMI";
    private static final String DUOMENG = "DUOMENG";
    private static final String DIANRU = "DIANRU";
    private static final String ADWO= "ADWO";
    private static final String JUPENG= "JUPENG";
    private static final String QUMI= "QUMI";
    private static final String SHOUXIN= "SHOUXIN";

    @RequestMapping("/score-increase")
    public
    @ResponseBody
    ResponseVo platformADActiveIncreaseScore(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
        ResponseVo responseVo = new ResponseVo();
        try {
            String channelCode = request.getParameter(REQUEST_PARAM_NAME_CHANNEL_CODE);
            String appId = request.getParameter("appId");
            Integral integral = new Integral();

            integral.setAppId(appId);
            channelCodeParamMustNotEmpty(integral, channelCode);

            userSpecailKeyMustBeSet(integral, request);

            fillMoreParams(integral, request);

            integral.setIp(integral.getUser().getIp());

            checkProcessStack(appId,choseLocker(channelCode),integral);
            checkSign(integral, request);


            String msg = integralService.addPoints(getAppIdParam(request), String.valueOf(integral.getScore()), integral.getUser().getUserId(), integral.getChannel().getCode(), integral.getOrderId(), integral.getAdId(), integral.getAdName());
            if (StringUtils.equals("OK", msg)) {
                responseVo.setSuccess(Boolean.TRUE);
                responseVo.setMessage("OK");
            } else {
                responseVo.setMessage(msg);
                LOG_INTEGRAL.warn("{}:获得积分数失败：{},user:{},orderId:{},orderTime:{},adName:{},adId:{},channel:{}," +
                                "points:{}",
                        msg,
                        integral.getAppId() + "-" + integral.getUser().getUserId(),
                        integral.getOrderId(), integral.getCreateTime(),
                        integral.getAdName(), integral.getAdId(),
                        integral.getChannel().getCode(), integral.getScore()
                );
                scoreCollector.addScore(appId, integral.getCreateTime(), integral.getScore(), integral.getChannel().getCode(), integral.getOrderId(), integral.getAdId(), integral.getAdName());
            }
        } catch (ScoreInterfaceException scoreInterfaceException) {
            LOG_INTEGRAL.warn("{}:获得积分数失败：{},user:{},orderId:{},orderTime:{},adName:{},adId:{},channel:{}," +
                            "points:{}",
                    scoreInterfaceException.getCauseType().getCode(), scoreInterfaceException.getCauseType().getMeaning(),
                    scoreInterfaceException.getUserSpecialKey(),
                    scoreInterfaceException.getOrderId(), scoreInterfaceException.getOrderTime(),
                    scoreInterfaceException.getAdName(), scoreInterfaceException.getAdId(),
                    scoreInterfaceException.getChannelCode(), scoreInterfaceException.getScore()
            );
            responseVo.setMessage(scoreInterfaceException.getCauseType().getMeaning());
        } catch (Throwable t) {
            LOG.error(t.getLocalizedMessage(), t);
            responseVo.setMessage(t.getLocalizedMessage());
        }
        return responseVo;
    }

    private void checkSign(Integral integral, ServletRequest request) throws ScoreInterfaceException {
        if (StringUtils.startsWith(integral.getChannel().getCode(), WANPU)) {
            // sign todo
        } else if (StringUtils.startsWith(integral.getChannel().getCode(), MOPAN)) {
            //mopanSignCheck(integral, request);
        } else if (StringUtils.startsWith(integral.getChannel().getCode(), XINGYUN)) {
            //sign todo
        } else if (StringUtils.startsWith(integral.getChannel().getCode(), GUOMOB)) {
            String sign = request.getParameter("sign");
            //sign todo
        } else if (StringUtils.startsWith(integral.getChannel().getCode(), CHUKONG)) {
            //sign todo
        } else if (StringUtils.startsWith(integral.getChannel().getCode(), ADVIEW)) {
            //sign todo
        } else if (StringUtils.startsWith(integral.getChannel().getCode(), ADSQ)) {
            //sign todo
        } else if (StringUtils.startsWith(integral.getChannel().getCode(), YOUMI)) {
            //sign todo
        } else if (StringUtils.startsWith(integral.getChannel().getCode(), DUOMENG)) {
            //sign todo
        } else if (StringUtils.startsWith(integral.getChannel().getCode(), DIANRU)) {
            //sign todo
        } else if (StringUtils.startsWith(integral.getChannel().getCode(), ADWO)) {
            //sign todo
        } else if (StringUtils.startsWith(integral.getChannel().getCode(), JUPENG)) {
            //sign todo
        } else if (StringUtils.startsWith(integral.getChannel().getCode(), QUMI)) {
            //sign todo
        } else if (StringUtils.startsWith(integral.getChannel().getCode(), SHOUXIN)) {
            //sign todo
        }
    }

    private void fillMoreParams(Integral integral, ServletRequest request) throws ScoreInterfaceException {
        if (StringUtils.startsWith(integral.getChannel().getCode(), WANPU)) {
            wanpuFillMoreParams(integral, request);
        } else if (StringUtils.startsWith(integral.getChannel().getCode(), MOPAN)) {
            mopanFillMoreParams(integral, request);
        } else if (StringUtils.startsWith(integral.getChannel().getCode(), XINGYUN)) {
            xingyunFillMoreParams(integral, request);
        } else if (StringUtils.startsWith(integral.getChannel().getCode(), GUOMOB)) {
            guomobFillMoreParams(integral, request);
        } else if (StringUtils.startsWith(integral.getChannel().getCode(), CHUKONG)) {
            chukongFillMoreParams(integral, request);
        } else if (StringUtils.startsWith(integral.getChannel().getCode(), ADVIEW)) {
            adViewFillMoreParams(integral, request);
        } else if (StringUtils.startsWith(integral.getChannel().getCode(), ADSQ)) {
            adsqFillMoreParams(integral, request);
        } else if (StringUtils.startsWith(integral.getChannel().getCode(), YOUMI)) {
            youmiFillMoreParams(integral, request);
        } else if (StringUtils.startsWith(integral.getChannel().getCode(), DUOMENG)) {
            domobFillMoreParams(integral, request);
        } else if (StringUtils.startsWith(integral.getChannel().getCode(), DIANRU)) {
            dianruFillMoreParams(integral, request);
        } else if (StringUtils.startsWith(integral.getChannel().getCode(), ADWO)) {
            adwoFillMoreParams(integral, request);
        } else if (StringUtils.startsWith(integral.getChannel().getCode(), JUPENG)) {
            jupengFillMoreParams(integral, request);
        } else if (StringUtils.startsWith(integral.getChannel().getCode(), QUMI)) {
            qumiFillMoreParams(integral, request);
        } else if (StringUtils.startsWith(integral.getChannel().getCode(), SHOUXIN)) {
            shouxinFillMoreParams(integral, request);
        }
    }

    private void userSpecailKeyMustBeSet(Integral integral, ServletRequest request) throws ScoreInterfaceException {
        if (StringUtils.startsWith(integral.getChannel().getCode(), WANPU)) {
            wanpuUserSet(integral, request);
        } else if (StringUtils.startsWith(integral.getChannel().getCode(), MOPAN)) {
            mopanUserSet(integral, request);
        } else if (StringUtils.startsWith(integral.getChannel().getCode(), XINGYUN)) {
            xingyunUserSet(integral, request);
        } else if (StringUtils.startsWith(integral.getChannel().getCode(), GUOMOB)) {
            guomobUserSet(integral, request);
        } else if (StringUtils.startsWith(integral.getChannel().getCode(), CHUKONG)) {
            chukongUserSet(integral, request);
        } else if (StringUtils.startsWith(integral.getChannel().getCode(), ADVIEW)) {
            adViewUserSet(integral, request);
        } else if (StringUtils.startsWith(integral.getChannel().getCode(), ADSQ)) {
            adsqUserSet(integral, request);
        } else if (StringUtils.startsWith(integral.getChannel().getCode(), YOUMI)) {
            youmiUserSet(integral, request);
        } else if (StringUtils.startsWith(integral.getChannel().getCode(), DUOMENG)) {
            domobUserSet(integral, request);
        } else if (StringUtils.startsWith(integral.getChannel().getCode(), DIANRU)) {
            dianruUserSet(integral, request);
        } else if (StringUtils.startsWith(integral.getChannel().getCode(), ADWO)) {
            adwoUserSet(integral, request);
        } else if (StringUtils.startsWith(integral.getChannel().getCode(), JUPENG)) {
            jupengUserSet(integral, request);
        } else if (StringUtils.startsWith(integral.getChannel().getCode(), QUMI)) {
            qumiUserSet(integral, request);
        } else if (StringUtils.startsWith(integral.getChannel().getCode(), SHOUXIN)) {
            shouxinUserSet(integral, request);
        }
    }

    private String choseLocker(String channelCode) {
        if (StringUtils.startsWith(channelCode, WANPU)) {
            return WANPU;
        } else if (StringUtils.startsWith(channelCode, MOPAN)) {
            return MOPAN;
        } else if (StringUtils.startsWith(channelCode, XINGYUN)) {
            return XINGYUN;
        } else if (StringUtils.startsWith(channelCode, GUOMOB)) {
            return GUOMOB;
        } else if (StringUtils.startsWith(channelCode, CHUKONG)) {
            return CHUKONG;
        } else if (StringUtils.startsWith(channelCode, ADVIEW)) {
            return ADVIEW;
        } else if (StringUtils.startsWith(channelCode, ADSQ)) {
            return ADSQ;
        } else if (StringUtils.startsWith(channelCode, YOUMI)) {
            return YOUMI;
        } else if (StringUtils.startsWith(channelCode, DUOMENG)) {
            return DUOMENG;
        } else if (StringUtils.startsWith(channelCode, DIANRU)) {
            return DIANRU;
        } else if (StringUtils.startsWith(channelCode, ADWO)) {
            return ADWO;
        } else if (StringUtils.startsWith(channelCode, JUPENG)) {
            return JUPENG;
        } else if (StringUtils.startsWith(channelCode, QUMI)) {
            return QUMI;
        } else if (StringUtils.startsWith(channelCode, SHOUXIN)) {
            return SHOUXIN;
        } else {
            return lockerShare;
        }
    }

    private void mopanSignCheck(Integral integral, ServletRequest request) throws ScoreInterfaceException {
        StringBuilder sign = new StringBuilder();
        String param0 = request.getParameter("param0");
        sign.append(integral.getAdId()).append(integral.getOrderId()).append(request.getParameter("cash"))
                .append(param0 == null ? "" : param0).append(integral.getChannel().getPlatformSec());
        if (!StringUtils.equals(request.getParameter("sign"),
                DigestUtils.md5Hex(sign.toString()))) {
            throw new ScoreInterfaceException(integral, ScoreInterfaceEnum.SIGN_NOT_MATCH);
        }
    }

    private void shouxinFillMoreParams(Integral integral, ServletRequest request) throws ScoreInterfaceException {
        String pointsStr = request.getParameter("cash");
        Double points = 0d;
        if (StringUtils.isNotBlank(pointsStr) && StringUtils.isNumeric(pointsStr)) {
            points = Double.valueOf(pointsStr);
        }
        integral.setScore(points.intValue());
        integral.setAdId(request.getParameter("id"));
        integral.setAdName(request.getParameter("appName"));
        integral.setOrderId(request.getParameter("trand_no"));
        integral.setCreateTime(new Date());
    }

    private void qumiFillMoreParams(Integral integral, ServletRequest request) throws ScoreInterfaceException {

        String pointsStr = request.getParameter("points");
        Double points = 0d;
        if (StringUtils.isNotBlank(pointsStr) && StringUtils.isNumeric(pointsStr)) {
            points = Double.valueOf(pointsStr);
        }
        integral.setScore(points.intValue());

        integral.setAdId(request.getParameter("ad"));
        integral.setAdName(URLDecoder.decode(request.getParameter("ad")));

        integral.setOrderId(request.getParameter("order"));

        String time = request.getParameter("time");
        if (StringUtils.isNotBlank(time)) {
            try {
                integral.setCreateTime(new Date(Long.valueOf(time)));
            } catch (Throwable e) {
                integral.setCreateTime(new Date());
            }
        }
    }

    private void jupengFillMoreParams(Integral integral, ServletRequest request) throws ScoreInterfaceException {
        String pointsStr = request.getParameter("score");
        Double points = 0d;
        if (StringUtils.isNotBlank(pointsStr) && StringUtils.isNumeric(pointsStr)) {
            points = Double.valueOf(pointsStr);
        }
        integral.setScore(points.intValue());
        integral.setAdId(request.getParameter("adid"));
        integral.setAdName(request.getParameter("adShowName"));
        integral.setOrderId(request.getParameter("follow"));
        integral.setCreateTime(new Date());
    }

    private void adwoFillMoreParams(Integral integral, ServletRequest request) throws ScoreInterfaceException {
        String time = request.getParameter("ts");
        if (StringUtils.isNotBlank(time)) {
            try {
                integral.setCreateTime(new Date(Long.valueOf(time)));
            } catch (Throwable e) {
                integral.setCreateTime(new Date());
            }
        }

        String pointsStr = request.getParameter("point");
        Double points = 0d;
        if (StringUtils.isNotBlank(pointsStr) && StringUtils.isNumeric(pointsStr)) {
            points = Double.valueOf(pointsStr);
        }
        integral.setScore(points.intValue());
        integral.setAdId(request.getParameter("adid"));
        integral.setAdName(request.getParameter("adname"));
        integral.setOrderId(integral.getAppId() + integral.getUser().getUserId() + integral.getAdId());
    }

    private void dianruFillMoreParams(Integral integral, ServletRequest request) throws ScoreInterfaceException {
        String time = request.getParameter("time");
        if (StringUtils.isNotBlank(time)) {
            try {
                integral.setCreateTime(new Date(Long.valueOf(time)));
            } catch (Throwable e) {
                integral.setCreateTime(new Date());
            }
        }

        String pointsStr = request.getParameter("point");
        Double points = 0d;
        if (StringUtils.isNotBlank(pointsStr) && StringUtils.isNumeric(pointsStr)) {
            points = Double.valueOf(pointsStr);
        }
        integral.setScore(points.intValue());
        integral.setAdId(request.getParameter("adid"));
        integral.setAdName(request.getParameter("adname"));

        String order = request.getParameter("hashid");

        if (StringUtils.isBlank(order)) {
            throw new ScoreInterfaceException(integral, ScoreInterfaceEnum.PARAM_ERROR_ORDERID);
        }
        integral.setOrderId(order);
    }

    private void domobFillMoreParams(Integral integral, ServletRequest request) throws ScoreInterfaceException {
        String time = request.getParameter("ts");
        if (StringUtils.isNotBlank(time)) {
            try {
                integral.setCreateTime(new Date(Long.valueOf(time)));
            } catch (Throwable e) {
                integral.setCreateTime(new Date());
            }
        }

        String pointsStr = request.getParameter("point");
        Double points = 0d;
        if (StringUtils.isNotBlank(pointsStr) && StringUtils.isNumeric(pointsStr)) {
            points = Double.valueOf(pointsStr);
        }
        integral.setScore(points.intValue());
        integral.setAdId(request.getParameter("adid"));
        integral.setAdName(request.getParameter("ad"));

        String order = request.getParameter("orderid");

        if (StringUtils.isBlank(order)) {
            throw new ScoreInterfaceException(integral, ScoreInterfaceEnum.PARAM_ERROR_ORDERID);
        }
        integral.setOrderId(order);
    }

    private void youmiFillMoreParams(Integral integral, ServletRequest request) throws ScoreInterfaceException {
        String time = request.getParameter("time");
        if (StringUtils.isNotBlank(time)) {
            try {
                integral.setCreateTime(new Date(Long.valueOf(time)));
            } catch (Throwable e) {
                integral.setCreateTime(new Date());
            }
        }

        String pointsStr = request.getParameter("points");
        Double points = 0d;
        if (StringUtils.isNotBlank(pointsStr) && StringUtils.isNumeric(pointsStr)) {
            points = Double.valueOf(pointsStr);
        }
        integral.setScore(points.intValue());
        integral.setAdId(request.getParameter("adid"));
        integral.setAdName(request.getParameter("ad"));

        String order = request.getParameter("order");

        if (StringUtils.isBlank(order)) {
            throw new ScoreInterfaceException(integral, ScoreInterfaceEnum.PARAM_ERROR_ORDERID);
        }
        integral.setOrderId(order);
    }

    SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
    private void adsqFillMoreParams(Integral integral, ServletRequest request) throws ScoreInterfaceException {
        String time = request.getParameter("time");
        if (StringUtils.isNotBlank(time)) {
            try {
                integral.setCreateTime(df.parse(time));
            } catch (ParseException e) {
                integral.setCreateTime(new Date());
            }
        }

        String pointsStr = request.getParameter("points");
        Double points = 0d;
        if (StringUtils.isNotBlank(pointsStr) && StringUtils.isNumeric(pointsStr)) {
            points = Double.valueOf(pointsStr);
        }
        integral.setScore(points.intValue());
        integral.setAdId(request.getParameter("adid"));
        integral.setAdName(request.getParameter("adname"));

        String order = request.getParameter("orderid");

        if (StringUtils.isBlank(order)) {
            throw new ScoreInterfaceException(integral, ScoreInterfaceEnum.PARAM_ERROR_ORDERID);
        }
        integral.setOrderId(order);
    }

    private void adViewFillMoreParams(Integral integral, ServletRequest request) throws ScoreInterfaceException {
        String time = request.getParameter("ti");
        if (StringUtils.isNotBlank(time)) {
            Date orderCreateTime = new Date();
            orderCreateTime.setTime(Long.valueOf(time));
            integral.setCreateTime(orderCreateTime);
        }

        String pointsStr = request.getParameter("points");
        Double points = 0d;
        if (StringUtils.isNotBlank(pointsStr) && StringUtils.isNumeric(pointsStr)) {
            points = Double.valueOf(pointsStr);
        }
        integral.setScore(points.intValue());
        integral.setAdId(request.getParameter("adi"));
        integral.setAdName(request.getParameter("dan"));

        String order = request.getParameter("order");

        if (StringUtils.isBlank(order)) {
            throw new ScoreInterfaceException(integral, ScoreInterfaceEnum.PARAM_ERROR_ORDERID);
        }
        integral.setOrderId(order);
    }

    private void chukongFillMoreParams(Integral integral, ServletRequest request) throws ScoreInterfaceException {
        String time = request.getParameter("time");
        if (StringUtils.isNotBlank(time)) {
            Date orderCreateTime = new Date();
            orderCreateTime.setTime(Long.valueOf(time));
            integral.setCreateTime(orderCreateTime);
        }

        String pointsStr = request.getParameter("coins");
        Double points = 0d;
        if (StringUtils.isNotBlank(pointsStr) && StringUtils.isNumeric(pointsStr)) {
            points = Double.valueOf(pointsStr);
        }
        integral.setScore(points.intValue());
        integral.setAdId(request.getParameter("adid"));
        integral.setAdName(request.getParameter("adtitle"));

        String order = request.getParameter("transactionid");

        if (StringUtils.isBlank(order)) {
            throw new ScoreInterfaceException(integral, ScoreInterfaceEnum.PARAM_ERROR_ORDERID);
        }
        integral.setOrderId(order);
    }

    private void guomobFillMoreParams(Integral integral, ServletRequest request) {
        String app = request.getParameter("app");

        String time = request.getParameter("time");
        if (StringUtils.isNotBlank(time)) {
            Date orderCreateTime = new Date();
            orderCreateTime.setTime(Long.valueOf(time) * 1000);
            integral.setCreateTime(orderCreateTime);
        }

        String pointsStr = request.getParameter("points");
        Double points = 0d;
        if (StringUtils.isNotBlank(pointsStr) && StringUtils.isNumeric(pointsStr)) {
            points = Double.valueOf(pointsStr);
        }
        integral.setScore(points.intValue());
        integral.setAdId(request.getParameter("adsid"));
        integral.setAdName(request.getParameter("ad"));

        String order = request.getParameter("order");
        integral.setOrderId(StringUtils.isBlank(order) ? (integral.getAppId() + (integral.getUser() != null ? integral.getUser().getUniqueUserId() : "") + integral.getAdId()) : order);
    }

    private void wanpuFillMoreParams(Integral integral, ServletRequest request) {
        String pointsStr = request.getParameter("points");
        Double points = 0d;
        if (StringUtils.isNotBlank(pointsStr) && StringUtils.isNumeric(pointsStr)) {
            points = Double.valueOf(pointsStr);
        }
        integral.setScore(points.intValue());
        integral.setAdId(request.getParameter("adv_id"));
        integral.setAdName(request.getParameter("ad_name"));
        Integer status = Integer.valueOf(request.getParameter("status") == null ? "0" : request.getParameter("status"));
        integral.setStatus((status != null && status.intValue() == 1) ? Boolean.TRUE : Boolean.FALSE);
        integral.setOrderId((integral.getAppId() + (integral.getUser() != null ? integral.getUser().getUniqueUserId() : "") + integral.getAdId()));
    }

    private void mopanFillMoreParams(Integral integral, ServletRequest request) {
        String pointsStr = request.getParameter("cash");
        Double points = 0d;
        if (StringUtils.isNotBlank(pointsStr) && StringUtils.isNumeric(pointsStr)) {
            points = Double.valueOf(pointsStr);
        }
        integral.setScore(points.intValue());
        integral.setOrderId(request.getParameter("trand_no"));
        integral.setAdId(request.getParameter("id"));
    }

    private void xingyunFillMoreParams(Integral integral, ServletRequest request) {
        String pointsStr = request.getParameter("score");
        Double points = 0d;
        if (StringUtils.isNotBlank(pointsStr) && StringUtils.isNumeric(pointsStr)) {
            points = Double.valueOf(pointsStr);
        }
        integral.setScore(points.intValue());
        integral.setOrderId(request.getParameter("trand_no"));
        integral.setAdName(URLDecoder.decode(request.getParameter("appName")));
        integral.setAdId(request.getParameter("adId"));
        if (StringUtils.isBlank(integral.getOrderId())) {
            integral.setOrderId(integral.getAppId() + integral.getUser().getId() + integral.getAdId());
        }

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        String exchangeTime = request.getParameter("exchangetime");
        if (StringUtils.isNotBlank(exchangeTime)) {
            try {
                integral.setCreateTime(df.parse(URLDecoder.decode(exchangeTime)));
            } catch (ParseException e) {
            }
        } else {
            integral.setCreateTime(new Date());
        }
    }

    private void shouxinUserSet(Integral integral, ServletRequest request) throws ScoreInterfaceException {
        String keyParam = request.getParameter("param0");
        User tmpUser = null;
        if (StringUtils.isNotBlank(keyParam)) {
            tmpUser = userService.getOne(integral.getAppId(), keyParam);
            keyParam = null;
        }

        if (null == tmpUser) {
            keyParam = request.getParameter("idfa");
            if (StringUtils.isNotBlank(keyParam)) {
                tmpUser = userService.getByMac(integral.getAppId(), keyParam);
                keyParam = null;
            }
        }

        if (null == tmpUser) {
            keyParam = request.getParameter("mac");
            if (StringUtils.isNotBlank(keyParam)) {
                tmpUser = userService.getByMac(integral.getAppId(), keyParam);
                keyParam = null;
            }
        }

        if (null == tmpUser)
            throw new ScoreInterfaceException(integral, ScoreInterfaceEnum.USER_NOTEXIST);
        integral.setUser(tmpUser);
    }

    private void qumiUserSet(Integral integral, ServletRequest request) throws ScoreInterfaceException {
        String keyParam = request.getParameter("user");
        User tmpUser = null;
        if (StringUtils.isNotBlank(keyParam)) {
            tmpUser = userService.getOne(integral.getAppId(), keyParam);
            keyParam = null;
        }

        if (null == tmpUser) {
            keyParam = request.getParameter("device");
            if (StringUtils.isNotBlank(keyParam)) {
                tmpUser = userService.getByMac(integral.getAppId(), keyParam);
                keyParam = null;
            }
        }

        if (null == tmpUser)
            throw new ScoreInterfaceException(integral, ScoreInterfaceEnum.USER_NOTEXIST);
        integral.setUser(tmpUser);
    }

    private void jupengUserSet(Integral integral, ServletRequest request) throws ScoreInterfaceException {
        String keyParam = request.getParameter("userdef");
        User tmpUser = null;
        if (StringUtils.isNotBlank(keyParam)) {
            tmpUser = userService.getOne(integral.getAppId(), keyParam);
            keyParam = null;
        }

        if (null == tmpUser) {
            keyParam = request.getParameter("udid");
            if (StringUtils.isNotBlank(keyParam)) {
                tmpUser = userService.getByMac(integral.getAppId(), keyParam);
                keyParam = null;
            }
        }

        if (null == tmpUser)
            throw new ScoreInterfaceException(integral, ScoreInterfaceEnum.USER_NOTEXIST);
        integral.setUser(tmpUser);
    }


    private void adwoUserSet(Integral integral, ServletRequest request) throws ScoreInterfaceException {
        String keyParam = request.getParameter("keyword");
        User tmpUser = null;
        if (StringUtils.isNotBlank(keyParam)) {
            tmpUser = userService.getOne(integral.getAppId(), keyParam);
            keyParam = null;
        }

        if (null == tmpUser) {
            keyParam = request.getParameter("idfa");
            if (StringUtils.isNotBlank(keyParam)) {
                tmpUser = userService.getByMac(integral.getAppId(), keyParam);
                keyParam = null;
            }
        }
        if (null == tmpUser) {
            keyParam = request.getParameter("device");
            if (StringUtils.isNotBlank(keyParam)) {
                tmpUser = userService.getByMac(integral.getAppId(), keyParam);
                keyParam = null;
            }
        }

        if (null == tmpUser)
            throw new ScoreInterfaceException(integral, ScoreInterfaceEnum.USER_NOTEXIST);
        integral.setUser(tmpUser);
    }

    private void dianruUserSet(Integral integral, ServletRequest request) throws ScoreInterfaceException {
        String keyParam = request.getParameter("userid");
        User tmpUser = null;
        if (StringUtils.isNotBlank(keyParam)) {
            tmpUser = userService.getOne(integral.getAppId(), keyParam);
            keyParam = null;
        }

        if (null == tmpUser) {
            keyParam = request.getParameter("deviceid");
            if (StringUtils.isNotBlank(keyParam)) {
                tmpUser = userService.getByMac(integral.getAppId(), keyParam);
                keyParam = null;
            }
        }

        if (null == tmpUser)
            throw new ScoreInterfaceException(integral, ScoreInterfaceEnum.USER_NOTEXIST);
        integral.setUser(tmpUser);
    }

    private void domobUserSet(Integral integral, ServletRequest request) throws ScoreInterfaceException {
        String keyParam = request.getParameter("user");
        User tmpUser = null;
        if (StringUtils.isNotBlank(keyParam)) {
            tmpUser = userService.getOne(integral.getAppId(), keyParam);
            keyParam = null;
        }

        if (null == tmpUser) {
            keyParam = request.getParameter("device");
            if (StringUtils.isNotBlank(keyParam)) {
                tmpUser = userService.getByMac(integral.getAppId(), keyParam);
                keyParam = null;
            }
        }

        if (null == tmpUser)
            throw new ScoreInterfaceException(integral, ScoreInterfaceEnum.USER_NOTEXIST);
        integral.setUser(tmpUser);
    }

    private void youmiUserSet(Integral integral, ServletRequest request) throws ScoreInterfaceException {
        String keyParam = request.getParameter("user");
        User tmpUser = null;
        if (StringUtils.isNotBlank(keyParam)) {
            tmpUser = userService.getOne(integral.getAppId(), keyParam);
            keyParam = null;
        }

        if (null == tmpUser) {
            keyParam = request.getParameter("device");
            if (StringUtils.isNotBlank(keyParam)) {
                tmpUser = userService.getByMac(integral.getAppId(), keyParam);
                keyParam = null;
            }
        }

        if (null == tmpUser)
            throw new ScoreInterfaceException(integral, ScoreInterfaceEnum.USER_NOTEXIST);
        integral.setUser(tmpUser);
    }

    private void adsqUserSet(Integral integral, ServletRequest request) throws ScoreInterfaceException {
        String keyParam = request.getParameter("userid");
        User tmpUser = null;
        if (StringUtils.isNotBlank(keyParam)) {
            tmpUser = userService.getOne(integral.getAppId(), keyParam);
            keyParam = null;
        }

        if (null == tmpUser) {
            keyParam = request.getParameter("deviceid");
            if (StringUtils.isNotBlank(keyParam)) {
                tmpUser = userService.getByMac(integral.getAppId(), keyParam);
                keyParam = null;
            }
        }

        if (null == tmpUser)
            throw new ScoreInterfaceException(integral, ScoreInterfaceEnum.USER_NOTEXIST);
        integral.setUser(tmpUser);
    }

    private void adViewUserSet(Integral integral, ServletRequest request) throws ScoreInterfaceException {
        String keyParam = request.getParameter("userId");
        User tmpUser = null;
        if (StringUtils.isNotBlank(keyParam)) {
            tmpUser = userService.getOne(integral.getAppId(), keyParam);
            keyParam = null;
        }

        if (null == tmpUser) {
            keyParam = request.getParameter("device");
            if (StringUtils.isNotBlank(keyParam)) {
                tmpUser = userService.getByMac(integral.getAppId(), keyParam);
                keyParam = null;
            }
        }

        if (null == tmpUser)
            throw new ScoreInterfaceException(integral, ScoreInterfaceEnum.USER_NOTEXIST);
        integral.setUser(tmpUser);
    }

    private void chukongUserSet(Integral integral, ServletRequest request) throws ScoreInterfaceException {
        String keyParam = request.getParameter("token");
        User tmpUser = null;
        if (StringUtils.isNotBlank(keyParam)) {
            tmpUser = userService.getOne(integral.getAppId(), keyParam);
            keyParam = null;
        }

        if (null == tmpUser) {
            keyParam = request.getParameter("idfa");
            if (StringUtils.isNotBlank(keyParam)) {
                tmpUser = userService.getByMac(integral.getAppId(), keyParam);
                keyParam = null;
            }
        }

        if (null == tmpUser)
            throw new ScoreInterfaceException(integral, ScoreInterfaceEnum.USER_NOTEXIST);
        integral.setUser(tmpUser);
    }

    private void guomobUserSet(Integral integral, ServletRequest request) throws ScoreInterfaceException {
        String keyParam = request.getParameter("other");
        User tmpUser = null;
        if (StringUtils.isNotBlank(keyParam)) {
            tmpUser = userService.getOne(integral.getAppId(), keyParam);
            keyParam = null;
        }

        if (null == tmpUser) {
            keyParam = request.getParameter("device");
            if (StringUtils.isNotBlank(keyParam)) {
                tmpUser = userService.getByMac(integral.getAppId(), keyParam);
                keyParam = null;
            }
        }

        if (null == tmpUser)
            throw new ScoreInterfaceException(integral, ScoreInterfaceEnum.USER_NOTEXIST);
        integral.setUser(tmpUser);
    }

    private void xingyunUserSet(Integral integral, ServletRequest request) throws ScoreInterfaceException {
        String keyParam = request.getParameter("userID");
        User tmpUser = null;
        if (StringUtils.isNotBlank(keyParam)) {
            tmpUser = userService.getOne(integral.getAppId(), keyParam);
            keyParam = null;
        }

        if (null == tmpUser) {
            keyParam = request.getParameter("idfa");
            if (StringUtils.isNotBlank(keyParam)) {
                tmpUser = userService.getByMac(integral.getAppId(), keyParam);
                keyParam = null;
            }
        }

        if (null == tmpUser)
            throw new ScoreInterfaceException(integral, ScoreInterfaceEnum.USER_NOTEXIST);
        integral.setUser(tmpUser);
    }

    private void mopanUserSet(Integral integral, ServletRequest request) throws ScoreInterfaceException {
        String keyParam = request.getParameter("param0");
        User tmpUser = null;
        if (StringUtils.isNotBlank(keyParam)) {
            tmpUser = userService.getOne(integral.getAppId(), keyParam);
            keyParam = null;
        }
        if (null == tmpUser) {
            keyParam = request.getParameter("imei");
            if (StringUtils.isNotBlank(keyParam)) {
                tmpUser = userService.getByMac(integral.getAppId(), keyParam);
                keyParam = null;
            }
        }

        if (null == tmpUser)
            throw new ScoreInterfaceException(integral, ScoreInterfaceEnum.USER_NOTEXIST);
        integral.setUser(tmpUser);
    }

    private void wanpuUserSet(Integral integral, ServletRequest request) throws ScoreInterfaceException {
        String keyParam = request.getParameter("key");
        User tmpUser = null;
        if (StringUtils.isNotBlank(keyParam)) {
            tmpUser = userService.getOne(integral.getAppId(), keyParam);
            keyParam = null;
        }
        if (null == tmpUser) {
            keyParam = request.getParameter("udid");
            if (StringUtils.isNotBlank(keyParam)) {
                tmpUser = userService.getByMac(integral.getAppId(), keyParam);
                keyParam = null;
            }
        }

        if (null == tmpUser) {
            keyParam = request.getParameter("openudid");
            if (StringUtils.isNotBlank(keyParam)) {
                tmpUser = userService.findByOpenUdid(integral.getAppId(), keyParam);
                keyParam = null;
            }
        }

        if (null == tmpUser)
            throw new ScoreInterfaceException(integral, ScoreInterfaceEnum.USER_NOTEXIST);
        integral.setUser(tmpUser);
    }

    private boolean checkProcessStack(String appId,String locker, Integral integral) throws ScoreInterfaceException {
        if (appService.onAdTestStatus(appId)) {
            return false;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(integral.getAppId()).append(integral.getUser().getId()).append(integral.getOrderId()).append(integral.getAdId());
        String tmp = sb.toString();
        Boolean result = Boolean.FALSE;


        synchronized (locker) {
            if (orders.contains(tmp)) {
                throw new ScoreInterfaceException(integral, ScoreInterfaceEnum.ORDER_ON_PROCESS);
            } else {
                orders.addLast(tmp);
                result = false;
            }
        }
        clearOrders();

        return result;
    }

    private void clearOrders() {
        if (orders.size() >= 800) {
            for (int i = 0; i < 500; i++) {
                orders.removeFirst();
            }
        }
    }

    @Autowired
    private IntegralService integralService;
    @Autowired
    private ScoreCollector scoreCollector;
    @Autowired
    private AppService appService;
}