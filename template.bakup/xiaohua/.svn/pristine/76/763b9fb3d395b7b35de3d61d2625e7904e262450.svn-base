package com.cjteam.xiao.web.controller;

import com.cjteam.xiao.model.User;
import com.cjteam.xiao.service.IntegralService;
import com.cjteam.xiao.service.UserService;
import com.cjteam.xiao.service.impl.IntegralSituation;
import com.cjteam.xiao.web.vo.ResponseVo;
import com.cjteam.xiao.web.vo.ResponseVo1;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.LinkedList;

/**
 * @author JiangChi
 */
@Controller
@RequestMapping("/score/interface")
public class ScoreCallBackController {
    private static final Logger LOG_INTEGRAL = LoggerFactory.getLogger(ScoreCallBackController.class.getName() + ".INTEGRAL");
    private static final Logger LOG = LoggerFactory.getLogger(ScoreCallBackController.class);

    @Value("${YOUMI.sec}")
    private String YOUMI;
    @Value("${DUOMENG.sec}")
    private String DUOMENG;
    @Value("${DIANRU.sec}")
    private String DIANRU;
    @Value("${APPID.DIANRU}")
    private String APPID_DIANRU;
    private String YIJIFEN;
    @Value("${MIIDI.sec}")
    private String MIIDI;
    private String AIPU;
    @Value("${ADWO.sec}")
    private String ADWO;
    @Value("${APPID.ADWO}")
    private String APPID_ADWO;

    private static LinkedList<String> orders = new LinkedList<String>();

    private static final String CC_KUOGUO = "KUOGUO";

    @RequestMapping(value = {"/kuguo"})
    public void kuoguo(@RequestParam(required = false) String userId,
                       @RequestParam(required = false) String idfa,
                       @RequestParam(required = false) String cooId,
                       @RequestParam(required = false) String app_id,
                       @RequestParam String price,
                       @RequestParam String points,
                       @RequestParam(required = false) String callbackTime,
                       @RequestParam(required = false) String advertName,
                       @RequestParam String orderId, HttpServletResponse response
    ) {
        try {
            String tip = "OK";
            User user = userService.getOne(app_id, userId);
            if (null == user) {
                user = userService.getByMac(app_id, idfa);
                if (null != user) {
                    userId = user.getUserId();
                }
            }
            String adId = DigestUtils.md5Hex(advertName.getBytes());
            if (checkProcessStack(CC_DIANLE, userId, orderId, adId)) {
                LOG.info("订单正在处理中:{} {},{},{},{}", CC_DIANLE, userId, orderId, advertName);
                tip = "该订单正在处理";
                response.getWriter().write("{success:1}");
                response.getWriter().flush();
                response.getWriter().close();
                return;
            } else {
                tip = integralService.addPoints(app_id, points, userId, CC_KUOGUO, orderId, adId,advertName);
            }
            response.getWriter().write("{success:1}");
            response.getWriter().flush();
            response.getWriter().close();
        } catch (Exception e) {
            LOG.error(e.getLocalizedMessage(), e);
            try {
                response.getWriter().write("{success:0}");
                response.getWriter().flush();
                response.getWriter().close();
            } catch (IOException e1) {
                LOG.error(e.getLocalizedMessage(), e1);
            }
        } finally {
            try {
                response.getWriter().close();
            } catch (IOException e) {
                LOG.error(e.getLocalizedMessage(), e);
            }
        }
        return;
    }

    private static final String CC_DIANLE = "DIANDIAN";

    @RequestMapping(value = {"/dianle"})
    public void dianle(@RequestParam(required = false) String snuid,
                       @RequestParam(required = false) String device_id,
                       @RequestParam(required = false) String app_id,
                       @RequestParam(required = false) String appid,
                       @RequestParam String currency,
                       @RequestParam(required = false) String app_ratio,
                       @RequestParam(required = false) String time_stamp,
                       @RequestParam(required = false) String ad_name,
                       @RequestParam(required = false) String pack_name,
                       @RequestParam(required = true) String token, HttpServletResponse response
    ) {
        try {
            String tip = "OK";
            String userId = snuid;
            User user = userService.getOne(appid, userId);
            if (null == user) {
                user = userService.getByMac(appid, device_id);
                if (null != user) {
                    userId = user.getUserId();
                }
            }
            String trand_no = userId + "_" + pack_name;
            if (checkProcessStack(CC_DIANLE, userId, trand_no, pack_name)) {
                LOG.info("订单正在处理中:{} {},{},{},{}", CC_DIANLE, userId, trand_no, ad_name);
                tip = "该订单正在处理";
                response.getWriter().write("403");
                response.getWriter().flush();
                response.getWriter().close();
                return;
            }else{
                tip = integralService.addPoints(appid, currency, userId, CC_DIANLE, trand_no, pack_name, ad_name);
            }
            response.getWriter().write("200");
            response.getWriter().flush();
            response.getWriter().close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return;
    }

    private static final String CC_MIIDI = "MIIDI";

    @RequestMapping(value = {"/miidi"})
    public
    @ResponseBody
    ResponseVo1 miidi(@RequestParam(required = true) String id,
                      @RequestParam(required = true) String trand_no,
                      @RequestParam(required = true) String cash,
                      @RequestParam(required = false) String app_id,
                      @RequestParam String imei,
                      @RequestParam(required = true) String appName,
                      @RequestParam(required = true) String bundleId,
                      @RequestParam(required = true) String param0,
                      @RequestParam(required = true) String sign
    ) {
        appName = decodeAdName(appName);
        LOG_INTEGRAL.warn("{}:赚取积分的广告id-id {}, 交易流水号唯一id-trand_no {}, 此次操作赚取的积分数量-cash {}, deviceID-imei {}, " +
                        "手机广告的名称-appName {},手机广告的唯一标识-bundleId {}, 应用通过SDK上传的参数-param0 {}, 签名字符串-sign {}",
                CC_MIIDI, id, trand_no, cash, imei, appName, bundleId, param0, sign
        );
        ResponseVo1 response = new ResponseVo1();
        try {
            StringBuffer tempSign = new StringBuffer();
            tempSign.append(id).append(trand_no).append(cash).append(param0 == null ? "" : param0).append(MIIDI);
            String tip = "OK";
            if (!StringUtils.equals(sign, DigestUtils.md5Hex(tempSign.toString()))) {
                tip = "Sign 不匹配";
                LOG_INTEGRAL.warn("{}:{},userId{},and tradeNo. is {}", IntegralSituation.SIGN_NOT_MATCH, CC_MIIDI, param0, trand_no);
            } else {
                String userId = param0;
                if (org.apache.commons.lang3.StringUtils.isBlank(userId)) {
                    User user = userService.getByMac(app_id, imei);
                    if (null != user) {
                        userId = user.getUserId();
                    }
                }
                if (checkProcessStack(CC_MIIDI, param0, trand_no, bundleId)) {
                    LOG.info("订单正在处理中:{} {},{},{},{}", CC_MIIDI, param0, trand_no, bundleId);
                    tip = "该订单正在处理";
                } else {
                    tip = integralService.addPoints(app_id, cash, userId, CC_MIIDI, trand_no, bundleId, appName);
                }
            }
            response.setStatus(Boolean.TRUE);
            response.setMessage("积分录入成功:" + tip);
            response.setCode(200);
        } catch (Exception e) {
            LOG.error(e.getLocalizedMessage(), e);
            response.setStatus(Boolean.FALSE);
            response.setMessage("积分录入异常");
            response.setCode(201);
        }
        return response;
    }

    /**
     * @param aduid 媒体ID
     * @param uid 用户唯一标识
     * @param aid 媒体类型如果为多用户类型，如网游媒体。需要传用户的账号 可选选项
     * @param point 用户当前积分
     * @param source 渠道来源 Limei
     * @param sign 签名信息
     * @param timestamp 当前产生时间戳
     * @param idfa 广告标示符（IDFA-identifierForAdvertising）
     * @return
     * @throws Exception
     */
    private static final String CC_LIMEI = "LIMEI";

    @RequestMapping(value = {"/limei"})
    public
    @ResponseBody
    ResponseVo1 limei(@RequestParam(required = true) String aduid, @RequestParam(required = true) String uid,
                      @RequestParam(required = false) String app_id,
                      @RequestParam(required = true) String aid, @RequestParam(required = true) String point,
                      @RequestParam(required = true) String source, @RequestParam(required = false) String sign,
                      @RequestParam(required = true) String timestamp, @RequestParam(required = true) String idfa
    ) throws Exception {
        LOG_INTEGRAL.warn("{}:媒体ID-aduid-{}, 用户唯一标识-uid-{}, 媒体类型-aid-{}, 用户当前积分-point-{}, 渠道来源-source-{}, 签名信息-sign-{}, 当前产生时间戳-timestamp-{}, 广告标示符-idfa-{}",
                CC_LIMEI, aduid, uid, aid, point, source, sign, timestamp, idfa);
        ResponseVo1 response = new ResponseVo1();
        try {
            String tip = null;
            if (checkProcessStack(CC_LIMEI, aid, timestamp, aduid)) {
                LOG.info("订单正在处理中:{} {},{},{},{}", CC_LIMEI, aid, timestamp, aduid);
                tip = "该订单正在处理";
            } else {
                tip = integralService.addPoints(app_id, point, aid, CC_LIMEI, timestamp + idfa, null, null);
            }
            response.setStatus(Boolean.TRUE);
            response.setMessage("积分录入成功:" + tip);
            response.setCode(200);
        } catch (Exception e) {
            LOG.error(e.getLocalizedMessage(), e);
            response.setStatus(Boolean.FALSE);
            response.setMessage("积分录入异常");
            response.setCode(201);
        }
        return response;
    }

    /**
     * @param appid
     * 开发者应用 ID:如 kc 电话对应的 ID
     * @param adname
     * 下载应用名称
     * @param adid
     * 下载应用 ID
     * @param device
     * mac （mac无冒号）
     * @param idfa
     * idfa
     * @param point
     * 赠送积分, 整型
     * @param ts
     * 时间戳(时间戳+device/idfa为激活回调唯一标识，可联合做流水号使用,开发者需要通过此字段联合device/
     * idfa来判断回调是否重复)
     * @param sign
     * 数字签名 特殊参数
     * @param keyword
     * 关键字 注：不做为签名条件，参数值由开发者自己协定（SDK实例:NSArray *array = [NSArray
     * arrayWithObjects:@"keyword1", nil]; 接收到的字符串为: “keyword1”）
     * @return 处理成功（服务器返回状态码为200即为回调成功，其他状态码则重复发送）
     * @throws Exception
     */
    private static final String CC_ADWO = "ANWO";

    @RequestMapping(value = {"/adwo"})
    public
    @ResponseBody
    ResponseVo adwo(@RequestParam(required = true) String app_id, @RequestParam(required = true) String adname,
                    @RequestParam(required = true) String appid,
                    @RequestParam(required = true) String adid, @RequestParam(required = true) String device,
                    @RequestParam(required = true) String idfa, @RequestParam(required = true) String point,
                    @RequestParam(required = false) String ts, @RequestParam(required = false) String sign,
                    @RequestParam(required = false) String keyword) throws Exception {
        adname = decodeAdName(adname);
        LOG_INTEGRAL.warn("{}:开发者应用ID-appid-{}, 下载应用名称-adname-{}, 下载应用ID-adid-{}, " +
                        "mac-device-{}, idfa-idfa-{}, 赠送积分整型-point-{}, 时间戳-ts-{}, 数字签名-sign-{}, 关键字-keyword-{}",
                CC_ADWO, appid, adname, adid, device, idfa, point, ts, sign, keyword
        );
        ResponseVo response = new ResponseVo();
        String parames = "adid=" + adid + "adname=" + adname + "appid=" + appid + "device=" + device + "idfa=" + idfa + "point=" + point
                + "ts=" + ts + "key=" + ADWO;
        try {
            String tip = "";
            if (!StringUtils.equals(DigestUtils.md5Hex(parames), sign)) {
                LOG_INTEGRAL.warn("{}: 平台{}的请求sign验证不通过,keyword:{},score:{},adname:{},adid:{},ts:{},idfa:{}",
                        IntegralSituation.SIGN_NOT_MATCH, CC_ADWO, keyword, point, adname, adid, ts, idfa);
                LOG_INTEGRAL.warn("expect:{},actual:{},joinStr:{}", DigestUtils.md5Hex(parames), sign, parames);
//            } else if (StringUtils.isBlank(keyword)) {
//                LOG_INTEGRAL.warn("{}: 平台{}的请求kewword 参数为空,keyword:{},score:{},adname:{},adid:{},ts:{},idfa:{}",
//                        IntegralSituation.SDK_PARAM_ISBLANK, CC_ADWO, keyword, point, adname, adid, ts, idfa);
            } else if (!StringUtils.equals(APPID_ADWO, appid)) {
                LOG_INTEGRAL.warn("{}: 平台{}的请求应用ID不匹配,keyword:{},score:{},adname:{},adid:{},ts:{},idfa:{}",
                        IntegralSituation.APPID_NOT_MACTH, CC_ADWO, keyword, point, adname, adid, ts, idfa);
            } else {
                User user = null;
                if (user == null && org.apache.commons.lang3.StringUtils.isNotBlank(idfa)) {
                    user = userService.getByMac(app_id, idfa);
                }
                if (user == null && org.apache.commons.lang3.StringUtils.isNotBlank(device)) {
                    user = userService.getByMac(app_id, device);
                }
                if (user == null && org.apache.commons.lang3.StringUtils.isNotBlank(keyword)) {
                    user = userService.getOne(app_id, keyword);
                }
                if (user != null) {
                    keyword = user.getUserId();
                }
                if (checkProcessStack(CC_ADWO, user.getUserId(), ts, "")) {
                    LOG.info("订单正在处理中:{} {},{},{},{}", CC_ADWO, user.getUserId(), ts, "");
                    tip = "该订单正在处理";
                } else {
                    tip = integralService.addPoints(app_id, point, keyword, CC_ADWO, ts + idfa);
                }
            }
            response.setSuccess(Boolean.TRUE);
            response.setMessage("积分录入成功:" + tip);
        } catch (Exception e) {
            LOG.error(e.getLocalizedMessage(), e);
            response.setSuccess(Boolean.FALSE);
            response.setMessage("积分录入异常");
            throw new Exception("积分录入异常");// 用这种办法返回HTTP状态为非200
        }
        return response;
    }

    /**
     * @param identifier
     * 必传 回传参数, 可用这个参数定位具体的用户.
     * @param achieve_id
     * 必传 “爱普动力”对成果的唯一标识符
     * @param point
     * 必传 需要返给用户的积分数.
     * @param user
     * 有就传 iOS 用户设备的mac 或者Android 用户设备的imei.
     * @param idfa
     * 有就传 苹果官方的广告唯一标识符
     * @param openudid
     * 有就传 开源的广告唯一标识符
     * @param response
     * @throws java.io.IOException
     */
    private static final String CC_AIPU = "AIPU";

    @RequestMapping(value = {"/appdriver"})
    public void appdriver(@RequestParam(required = true) String identifier, @RequestParam(required = true) String achieve_id,
                          @RequestParam(required = true) String point, @RequestParam(required = false) String user,
                          @RequestParam(required = false) String app_id,
                          @RequestParam(required = false) String idfa, @RequestParam(required = false) String openudid, HttpServletResponse response)
            throws IOException {
        LOG_INTEGRAL.warn("{}:用户标识-identifier- {} , 成果的唯一标识符-achieve_id- {} , " +
                        "积分数-point- {} , mac||imei-user- {} , 苹果官方的广告唯一标识符-idfa- {} , 开源的广告唯一标识符-openudid- {} ",
                CC_AIPU, identifier, achieve_id, point, user, idfa, openudid
        );
        try {
            String tip = null;
            if (checkProcessStack(CC_AIPU, identifier, achieve_id, "")) {
                LOG.info("订单正在处理中:{} {},{},{},{}", CC_AIPU, identifier, achieve_id, "");
                tip = "该订单正在处理";
            } else {
                tip = integralService.addPoints(app_id, point, identifier, CC_AIPU, achieve_id);
            }
            response.getWriter().write("1");
            response.getWriter().flush();
            response.getWriter().close();
            System.out.println("tip:" + tip);
        } catch (Exception e) {
            LOG.error(e.getLocalizedMessage(), e);
            response.getWriter().write("0");
            response.getWriter().flush();
            response.getWriter().close();
        }
    }

    /**
     * @param order
     * Order ID 字符串(16字节)
     * @param app
     * App ID 字符串(16字节)
     * @param ad
     * Ad Name 广告名（50字节）
     * @param adid
     * Ad Identify 广告编号（整形）
     * @param user
     * User ID user 字符串
     * @param device
     * Device ID 字符串
     * @param chn
     * Channel 整型
     * @param price
     * Price 浮点型
     * @param points
     * Points 整型
     * @param time
     * Order Create Time 整形
     * @param sig
     * Sig 字符串(8字节)
     * @param sign
     * Sign 字符串(36字节)
     * @return
     * @throws Exception
     */
    private static final String CC_YOUMI = "YOUMI";

    @RequestMapping(value = {"/youmi"})
    public
    @ResponseBody
    ResponseVo youmi(@RequestParam(required = true) String order, @RequestParam(required = true) String app,
                     @RequestParam(required = false) String app_id,
                     @RequestParam(required = true) String ad, @RequestParam(required = true) String adid,
                     @RequestParam(required = true) String user, @RequestParam(required = true) String device,
                     @RequestParam(required = true) String chn, @RequestParam(required = true) String price,
                     @RequestParam(required = true) String points, @RequestParam(required = true) String time,
                     @RequestParam(required = true) String sig, @RequestParam(required = true) String sign, HttpServletResponse response_r)
            throws Exception {
        LOG_INTEGRAL.warn("{}:Order ID 字符串- order - {}, App ID 字符串 -app - {}, Ad Name 广告名-ad - {}, " +
                        "Ad Identify 广告编号 - adid - {}, User ID user - user - {}, Device ID -d e vice - {}, Channel -chn - {}, " +
                        "Price - price - {}, Points 整型 - points - {}, " +
                        "Order Create Time 整形 - time - {}, Sig 字符串(8字节 ) -sig - {}, Sign 字符串(36字节) - sign - {} ",
                CC_YOUMI, order, app, ad, adid, user, device, chn, price, points, time, sig, sign
        );
        ResponseVo response = new ResponseVo();
        String parames = "ad=" + ad + "adid=" + adid + "app=" + app + "chn=" + chn + "device=" + device + "order=" + order + "points="
                + points + "price=" + price + "sig=" + sig + "time=" + time + "user=" + user + YOUMI;
        try {
            String tip = "OK";

            if (!StringUtils.equals(DigestUtils.md5Hex(parames), sign)) {

                LOG_INTEGRAL.warn("{}: 平台{}的请求sign验证不通过,orderId:{},points:{},appdid:{},Ad Name:{},time:{},user:{}",
                        IntegralSituation.SIGN_NOT_MATCH, CC_YOUMI, order, points, adid, ad, time, user);
                LOG_INTEGRAL.warn("expect:{},actual:{},joinStr:{}", DigestUtils.md5Hex(parames), sign, parames);
            } else if (checkProcessStack(CC_YOUMI, user, order, adid)) {
                LOG.info("订单正在处理中:{} {},{},{},{}", CC_YOUMI, user, order, adid);
                tip = "该订单正在处理";
            } else {
                tip = integralService.addPoints(app_id ,points, user, CC_YOUMI, order, adid, ad);
            }
            response.setSuccess(Boolean.TRUE);
            response.setMessage("积分录入成功:" + tip);
        } catch (Exception e) {
            LOG.error(e.getLocalizedMessage(), e);
            response.setSuccess(Boolean.FALSE);
            response.setMessage("积分录入异常");
            throw new Exception("积分录入异常");// 用这种办法返回HTTP状态为非200
        }
        return response;
    }

    /**
     * @param uuid
     * 易积分的用户id（实际参数为Mac地址）
     * @param userID
     * 合作方的用户id
     * @param score
     * 此次激活给的分
     * @param exchangetime
     * 效果发生时间
     * @param plat
     * 平台类型：0是android ,1是ios
     * @return
     */
    private static final String CC_YIJIFEN = "YIJIFEN";

    @RequestMapping(value = {"/yijifen"})
    public
    @ResponseBody
    ResponseVo yijifen(@RequestParam(required = true) String uuid, @RequestParam(required = true) String userID,
                       @RequestParam(required = true) String score, @RequestParam(required = true) String exchangetime,
                       @RequestParam(required = false) String app_id,
                       @RequestParam(required = true) String plat) {
        LOG_INTEGRAL.warn("{}:易积分的用户id（实际参数为Mac地址） - uuid - {}, 合作方的用户id - userID - {}, " +
                        "此次激活给的分 - score - {}, 效果发生时间 - exchangetime  - {}, 平台类型：0是android ,1是ios - plat - {}",
                CC_YIJIFEN, uuid, userID, score, exchangetime, plat
        );
        ResponseVo response = new ResponseVo();
        try {
            String tip = null;
            if (checkProcessStack(CC_YIJIFEN, userID, exchangetime, null)) {
                LOG.info("订单正在处理中:{} {},{},{},{}", CC_YIJIFEN, userID, exchangetime, null);
                tip = "该订单正在处理";
            } else {
                tip = integralService.addPoints(app_id, score, userID, CC_YIJIFEN, exchangetime);
            }
            response.setSuccess(Boolean.TRUE);
            response.setMessage("积分录入成功:" + tip);
        } catch (Exception e) {
            LOG.error(e.getLocalizedMessage(), e);
            response.setSuccess(Boolean.FALSE);
            response.setMessage("积分录入异常");
        }
        return response;
    }

    /**
     * @param orderid
     * 字符串 订单ID。该值是唯一的，如果开发者接收到相同的订单号，那说明该订单已经存在。
     * @param pubid
     * 字符串 Publisher ID，即开发者的积分墙应用ID
     * @param ad
     * 字符串 广告名称
     * @param adid
     * 整型 广告ID，即offer ID
     * @param user
     * 字符串 UserID iOS平台，若开发者没有在积分墙iOS SDK中绑定userid，或对于没有升级到嵌入了SDK
     * 2.0.3以上版本SDK的用户，则user为经过md5加密的用户设备的带冒号的大写的MAC地址。例子：md5(
     * '24:AB:81:5E:F6:1E')=13A81BB8DCA00BE81794E246C8429C0E
     * android平台，如果开发者没有在积分墙android SDK中绑定userid，则user为设备的imei
     * @param device
     * 字符串 设备号。iOS：设备号优先级为mac地址 > IDFA > OpenUDID 如果mac地址为有效值（iOS
     * 7将获取不到有效的MAC地址），则为大写的原始mac地址；否则如果多盟的SDK能够获取到设备的IDFA（iOS
     * 6才能获取），则为设备的IDFA；如果mac地址无效且获取不到设备的IDFA，则为设备的OpenUDID
     * android：统一返回设备的imei
     * @param channel
     * 整型 渠道号。预留给android的推广，对于iOS来说该值为0
     * @param price
     * 浮点型，保留两位小数 开发者获得的收入
     * @param point
     * 整型 用户可获得的积分
     * @param ts
     * 整型 成功结算的时间戳，精确到秒
     * @param sign
     * 字符串 签名。根据private_key与所有参数共同计算得出。
     * @return
     * @throws Exception
     */
    private static final String CC_DUOMENG = "DUOMENG";

    @RequestMapping(value = {"/domob"})
    public
    @ResponseBody
    ResponseVo domob(@RequestParam(required = true) String orderid, @RequestParam(required = true) String pubid,
                     @RequestParam(required = false) String app_id,
                     @RequestParam(required = true) String ad, @RequestParam(required = true) String adid,
                     @RequestParam(required = true) String user, @RequestParam(required = true) String device,
                     @RequestParam(required = true) String channel, @RequestParam(required = true) String price,
                     @RequestParam(required = true) String point, @RequestParam(required = true) String ts,
                     @RequestParam(required = true) String sign) throws Exception {
        ad = decodeAdName(ad);

        LOG_INTEGRAL.warn("{}:订单ID - orderid - {}, PID,开发者的积分墙应用ID - pubid - {}, 广告名称 - ad - {}," +
                        "广告ID - adid - {}, UserID - user - {}, 设备号 - device - {}, 渠道号 - channel - {}, 开发者获得的收入 - price - {}, " +
                        "用户可获得的积分 - point - {}, 成功结算的时间戳，精确到秒 - ts - {}, 签名 - sign - {}",
                CC_DUOMENG, orderid, pubid, ad, adid, user, device, channel, price, point, ts, sign
        );
        ResponseVo response = new ResponseVo();

        try {
            String tip = "OK";
            String parames = "ad=" + ad + "adid=" + adid + "channel=" + channel + "device=" + device + "orderid=" + orderid + "point=" + point
                    + "price=" + price + "pubid=" + pubid + "ts=" + ts + "user=" + user + DUOMENG;
            if (!StringUtils.equals(DigestUtils.md5Hex(parames), sign)) {
                LOG_INTEGRAL.warn("{}: 平台{}的请求sign验证不通过,orderId:{},points:{},appdid:{},Ad Name:{},time:{},user:{}",
                        IntegralSituation.SIGN_NOT_MATCH, CC_DUOMENG, orderid, point, adid, ad, ts, user);
                LOG_INTEGRAL.warn("expect:{},actual:{},joinStr:{}", DigestUtils.md5Hex(parames), sign, parames);
            } else if (checkProcessStack(CC_DUOMENG, user, orderid, adid)) {
                LOG.info("订单正在处理中:{} {},{},{},{}", CC_DUOMENG, user, orderid, adid);
                tip = "该订单正在处理";
            } else {
                tip = integralService.addPoints(app_id,point, user, CC_DUOMENG, orderid, adid, ad);
            }
            response.setSuccess(Boolean.TRUE);
            response.setMessage("积分录入成功:" + tip);
        } catch (Exception e) {
            LOG.error(e.getLocalizedMessage(), e);
            response.setSuccess(Boolean.FALSE);
            response.setMessage("积分录入异常");
            throw new Exception("积分录入异常");// 用这种办法返回HTTP状态为非200
        }
        return response;
    }

    /**
     * @param hashid
     * :唯一号
     * @param appid
     * :开发者应用ID
     * @param adid
     * :广告ID
     * @param adname
     * :广告名称（urlencode操作）
     * @param userid
     * :开发者设置的用户ID（SDK中设置，可能会有urlencode操作）
     * @param deviceid
     * :唯一标识(MAC)
     * @param source
     * :渠道来源
     * @param point
     * :积分值(用户可以赚取的积分)
     * @param time
     * :当前产生的时间戳
     * @param checksum
     * :签名结果值
     * @return
     */
    private static final String CC_DIANRU = "DIANRU";

    @RequestMapping(value = {"/dianru"})
    public
    @ResponseBody
    ResponseVo dianru(@RequestParam(required = true) String hashid, @RequestParam(required = true) String appid,
                      @RequestParam(required = true) String app_id,
                      @RequestParam(required = true) String adid, @RequestParam(required = true) String adname,
                      @RequestParam(required = true) String userid, @RequestParam(required = true) String deviceid,
                      @RequestParam(required = true) String source, @RequestParam(required = true) String point,
                      @RequestParam(required = true) String time, @RequestParam(required = true) String checksum) {
        adname = decodeAdName(adname);

        ResponseVo response = new ResponseVo();
        LOG_INTEGRAL.warn("{}:唯一号 - hashid - {}, 开发者应用ID - appid - {}, 广告ID - adid - {}, " +
                        "广告名称- adname - {}, 开发者设置的用户ID - userid - {}, 唯一标识(MAC) - deviceid - {}, 渠道来源 - source - {}, " +
                        "积分值 - point - {}, 当前产生的时间戳 - time - {}, 签名结果值 - checksum - {}",
                CC_DIANRU, hashid, appid, adid, adname, userid, deviceid, source, point, time, checksum
        );
        try {
            String tip = "OK";
            String parames = "?hashid=" + hashid + "&appid=" + appid + "&adid=" + adid + "&adname=" + adname + "&userid=" + userid
                    + "&deviceid=" + deviceid + "&source=" + source + "&point=" + point + "&time=" + time + "&appsecret=" + DIANRU;
            if (!StringUtils.equals(DigestUtils.md5Hex(parames), checksum)) {
                LOG_INTEGRAL.warn("{}: 平台{}的请求sign验证不通过,orderId:{},points:{},appdid:{},Ad Name:{},time:{},user:{}",
                        IntegralSituation.SIGN_NOT_MATCH, CC_DIANRU, hashid, point, adid, adname, time, userid);
                LOG_INTEGRAL.warn("expect:{},actual:{},joinStr:{}", DigestUtils.md5Hex(parames), checksum, parames);
            } else if (!StringUtils.equals(APPID_DIANRU, appid)) {
                LOG_INTEGRAL.warn("{}: 平台{}的请求应用ID不匹配,orderId:{},points:{},appdid:{},Ad Name:{},time:{},user:{},appId{}",
                        IntegralSituation.APPID_NOT_MACTH, CC_DIANRU, hashid, point, adid, adname, time, userid, appid);
            } else {
                String userId = userid;
                if (org.apache.commons.lang3.StringUtils.equals(userId, hashid) && org.apache.commons.lang3.StringUtils.isNotBlank(deviceid)) {
                    User user = userService.getByMac(app_id,deviceid);
                    if (null != user) {
                        userId = user.getUserId();
                    }
                }
                if (checkProcessStack(CC_DIANRU, userId, hashid, adid)) {
                    LOG.info("订单正在处理中:{} {},{},{},{}", CC_DIANRU, userId, hashid, adid);
                    tip = "该订单正在处理";
                } else {
                    tip = integralService.addPoints(app_id,point, userId, CC_DIANRU, hashid, adid, adname);
                }
            }
            response.setSuccess(Boolean.TRUE);
            response.setMessage("积分录入成功:" + tip);
        } catch (Exception e) {
            LOG.error(e.getLocalizedMessage(), e);
            response.setSuccess(Boolean.FALSE);
            response.setMessage("积分录入异常");
        }
        return response;
    }

    private String decodeAdName(String adname) {
        adname = StringUtils.trimToEmpty(adname);
        if (StringUtils.isNotBlank(adname)) {
            adname = URLDecoder.decode(adname);
        }
        return adname;
    }

    /**
     * @param orderid
     * @param userid
     * @param point
     * @param time
     * @param checksum
     * @return
     */
    @RequestMapping(value = {"/sstatic"})
    public
    @ResponseBody
    ResponseVo staticChannel(@RequestParam(required = true) String orderid, @RequestParam(required = true) String userid,
                             @RequestParam(required = true) String point, @RequestParam(required = false) String time,
                             @RequestParam(required = false) String app_id,
                             @RequestParam(required = false) String checksum) {
        ResponseVo response = new ResponseVo();
        String channel_meansplatform = "STATIC";
        try {
            String tip = "OK";
            integralService.addPoints(app_id,point, userid, channel_meansplatform, orderid);
            response.setSuccess(Boolean.TRUE);
            response.setMessage("积分录入成功:" + tip);
        } catch (Exception e) {
            LOG.error(e.getLocalizedMessage(), e);
            response.setSuccess(Boolean.FALSE);
            response.setMessage("积分录入异常");
        }
        return response;
    }

    private boolean checkProcessStack(String locker, String userId, String orderId, String adId) {
        StringBuilder sb = new StringBuilder();
        sb.append(userId).append(orderId).append(adId);
        String tmp = sb.toString();
        Boolean result = Boolean.FALSE;
        synchronized (locker) {
            if (orders.contains(tmp)) {
                result = true;
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
    UserService userService;
}