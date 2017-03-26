package com.cjteam.xiao.service.impl;

import com.google.common.collect.ImmutableList;
import com.cjteam.xiao.error.Error;
import com.cjteam.xiao.model.User;
import com.cjteam.xiao.repository.UserRepository;
import com.cjteam.xiao.service.AppService;
import com.cjteam.xiao.service.UserService;
import com.cjteam.xiao.util.PageBasicInfo;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.DurationFieldType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by ChenLong Date: 13-9-27
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    private static final Logger ACCESS_IP = LoggerFactory.getLogger(UserServiceImpl.class + ".IP");
    public static final String privatekey = "WzzLIs(QlaiTf&suO4l-_5CW!7CTAZ";
    private static final List<Integer> sharkPool = ImmutableList.of(3, 4, 5, 6, 7, 8, 9, 10);
    private static final Random sharkRandom = new Random(sharkPool.size() + System.currentTimeMillis());
    public User getByUserId(String userId){
        return this.userRepository.findByUserId(userId) ;
    }
    public User getByUniqueUserId(String userId){
        return this.userRepository.findByUniqueUserId(userId) ;
    }
    @Override
    public User updateUserInfo(String appId, String clientUserId, String telphone, String newpassword, String newNick, String alipayNo, String newqq) {
        User user = userRepository.findByAppIdAndUserId(appId, clientUserId);
        if (user == null) {
            log.info("user {} not exist", clientUserId);
            return null;
        }
        if (StringUtils.isBlank(telphone) && StringUtils.isBlank(newpassword) && StringUtils.isBlank(newNick) && StringUtils.isBlank(alipayNo) && StringUtils.isBlank(newqq)) {
            log.info("nothing change");
            return user;
        }
        if (StringUtils.isNotBlank(telphone)) {
            user.setMobilePhone(telphone);
        }
        if (StringUtils.isNotBlank(newpassword)) {
            user.setPassword(newpassword);
        }
        if (StringUtils.isNotBlank(newNick)) {
            user.setNickName(newNick);
        }
        if (StringUtils.isNotBlank(alipayNo)) {
            user.setAlipayNo(alipayNo);
        }
        if (StringUtils.isNotBlank(newqq)) {
            user.setQq(newqq);
        }

        try {
            if (StringUtils.isNotBlank(user.getMobilePhone()) && StringUtils.isNotBlank(user.getNickName())) {
                if (!user.getAward()) {
                    int awardScore = doSharkScore();
                    user.setSurplus(user.getSurplus() + awardScore);
                    user.setTotalPoints(user.getTotalPoints() + awardScore);
                    user.setAward(Boolean.TRUE);
                    log.info("award user {} {} score for information completely", user.getNickName(), awardScore);
                }
            }
            userRepository.save(user);
        } catch (Exception e) {
            //电话可能重复
            log.info("somthing wrong with update");
            e.printStackTrace();
        }
        return user;
    }
    private String getUserId(User user){
        String token  = user.getToken()==null?"":user.getToken();
        String tmp = user.getMac() + user.getOpenUdid() +token + privatekey ;
        return  DigestUtils.md5Hex(tmp)  ;
    }
    @Override
    public User create(User user) throws Exception {
        String test = user.getMac() + user.getOpenUdid() + user.getToken() + privatekey ;
        user.setUserId(this.getUserId(user));


        User dbUser = getOne(user.getAppId(),user.getUserId());
        if (null != dbUser) {
            log.info("user already exist {}.", user);
            return dbUser;
        } else {
            String token  = user.getToken() ;
            List<User> similarUsers ;
            if (token!=null && token.equals("")) {
                similarUsers = userRepository.queryUserBySimilarInfo(user.getAppId(), user.getMac(),token, user.getOpenUdid());
            }
            else{
                similarUsers = userRepository.queryUserBySimilarInfo(user.getAppId(), user.getMac(), user.getOpenUdid());
            }
            if (CollectionUtils.isNotEmpty(similarUsers)) {
                log.info("found {} similar users for this one {}", similarUsers.size(), user);
                User similarUser = similarUsers.get(0);
                if (appService.onOnlineStatus(similarUser.getAppId())) {
                    similarUser.setIsBlack(Boolean.TRUE);
                }
                similarUser.setMac(user.getMac());
                similarUser.setToken(user.getToken());
                similarUser.setOpenUdid(user.getOpenUdid());
                similarUser.setUserId(user.getUserId());
                similarUser.setIp(user.getIp());
                user = similarUser;
            } else {
                log.info("new user init");
                user.setPassword("123456");
                user.setNickName("" + DateTime.now().getMillisOfDay() + DateTime.now().getDayOfYear());
                user.setUniqueUserId(user.getUserId());  //just set once,use for other table's fk
            }
            return userRepository.save(user);
        }
    }

    @Override
    public boolean rollbackExchangeConsumptionScore(User consumer, Integer scoreConsumption) {
        if (null != consumer)
            return userRepository.rollbackExchangeConsumptionScore(consumer.getId(), Long.valueOf(scoreConsumption)) > 0;
        return false;
    }

    @Override
    public boolean reduceUserScore(User consumer, Integer scoreConsumption) {
        if (null != consumer)
            return userRepository.rollbackExchangeConsumptionScore(consumer.getId(), 0L - scoreConsumption) > 0;
        return false;
    }

    @Override
    public boolean userInfoIsValid(User user) {
        return StringUtils.equals(user.getUserId(), this.getUserId(user));
    }
    @Override
    public User getOne(String appId, String userId) {
        return userRepository.findByAppIdAndUserId(appId, userId);
    }

    @Override
    public Page<User> queryAll(String appId, int size, QueryCondition queryCondition, int page) {
        Pageable pageable = new PageRequest(page, size, Sort.Direction.DESC, User.fieldCreateTime);
        if (!QueryCondition.isValid(queryCondition))
            return userRepository.findByAppId(appId, pageable);
        return userRepository.findByNickNameLikeOrMobilePhoneLike(appId, queryCondition.getQueryName(), queryCondition.getQueryMobile(), pageable);
    }

    @Override
    public Page<User> getBlackListUsers(String appId, int pageSize, QueryCondition queryCondition, int page) {
        Pageable pageable = new PageRequest(page, pageSize, Sort.Direction.DESC, User.fieldCreateTime);
        if (!QueryCondition.isValid(queryCondition))
            return userRepository.findByAppIdAndIsBlackTrue(appId, pageable);
        return blackUserQuerySwither(queryCondition, null, pageable);
    }

    private Page<User> blackUserQuerySwither(QueryCondition queryCondition, String appId, Pageable pageable) {
        if (queryCondition.isIpQuery()) {
            return userRepository.findByAppIdAndIsBlackTrueAndIp(appId, StringUtils.trimToNull(queryCondition.getIp()), pageable);
        } else if (queryCondition.isMacQuery()) {
            return userRepository.findByAppIdAndIsBlackTrueAndMac(appId, StringUtils.trimToNull(queryCondition.getMac()), pageable);
        } else if (queryCondition.isMobileQuery()) {
            return userRepository.findByAppIdAndIsBlackTrueAndMobilePhone(appId, StringUtils.trimToNull(queryCondition.getMobile()), pageable);
        } else if (queryCondition.isNameQuery()) {
            return userRepository.findByAppIdAndIsBlackTrueAndNickName(appId, StringUtils.trimToNull(queryCondition.getName()), pageable);
        } else if (queryCondition.isOpenUidiQuery()) {
            return userRepository.findByAppIdAndIsBlackTrueAndOpenUdid(appId, StringUtils.trimToNull(queryCondition.getOpenUdid()), pageable);
        } else if (queryCondition.isTokenQuery()) {
            return userRepository.findByAppIdAndIsBlackTrueAndToken(appId, StringUtils.trimToNull(queryCondition.getToken()), pageable);
        }
        return null;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Boolean blackUser(Integer id) {
        userRepository.blackUser(id);
        return Boolean.TRUE;
    }

    @Override
    public void updateLastLoginIp(User user, String currentLoginIp) {
        ACCESS_IP.info("user is {},new ip is {}", user.toString(), currentLoginIp);
        if (null == user || StringUtils.equals(user.getIp(), currentLoginIp)) {
            return;
        }
        userRepository.updateLoginIp(user.getId(),currentLoginIp);
    }

    @Override
    public Page<User> getTopTotallyScoredUserByPage(String appId, int pageSize, int pageNo) {
        Pageable pageable = new PageRequest(pageNo, pageSize, Sort.Direction.DESC, "totalPoints");
        return  new PageImpl<User>(userRepository.findSortedScoreUser(pageNo*pageSize,pageSize),pageable,countAllUser());
    }

    private long countAllUser() {
        return userRepository.count();
    }

    @Override
    public User findByOpenUdid(String appId, String openUdid) {
        return userRepository.findByAppIdAndOpenUdid(appId, openUdid);
    }

    @Override
    public User getById(Integer id) {
        return userRepository.findOne(id);
    }

    @Override
    public void increaseScore(Integer id, Integer award) {
        if (award == null || award <= 0)
            return;
        userRepository.awardScore(id, award);
    }

    @Override
    public int sharkTodayForUser(User user) {
        user = userRepository.findByAppIdAndUserId(user.getAppId(), user.getUserId());
        Assert.notNull(user, "用户不存在");
        Date latestSharkDate = null;
        String[] sharkInfo = null;
        if (StringUtils.isNotBlank(user.getShark())) {// today not shark
            sharkInfo = StringUtils.split(user.getShark(), ",");
            latestSharkDate = DateTime.parse(sharkInfo[1]).toDate();
            DateTime duration = new DateTime(latestSharkDate).withFieldAdded(DurationFieldType.days(), 1);
            if (new Date().before(duration.toDate())) {
                throw new RuntimeException("亲，每天只能摇一次哦！");
            }
        }

        int sharkScore = doSharkScore();
        user.setShark(sharkScore + "," + DateTime.now().toString("yyyy-MM-dd"));
        user.setTotalPoints(user.getTotalPoints() + sharkScore);
        user.setSurplus(user.getSurplus() + sharkScore);
        userRepository.save(user);
        return sharkScore;
    }

    private int doSharkScore() {
        return sharkPool.get(Math.abs(sharkRandom.nextInt()) % sharkPool.size());
    }

    @Override
    public Boolean whiteUser(Integer id) {
        User user = userRepository.findOne(id);
        Assert.notNull(user, "user not exist");
        Assert.isTrue(user.getIsBlack(), "user already in white list");
        user.setIsBlack(Boolean.FALSE);
        save(user);
        log.info("user {}  pushed into white list", user);
        return Boolean.TRUE;
    }

    @Override
    @Transactional(propagation = Propagation.NESTED)
    public synchronized User updateSeckey(Integer id) {
        User user = userRepository.findOne(id);
        Assert.notNull(user, "invalid userId param. not exist.");
        user.setSce("" + (user.getSecIndex() + 1) + new Date().getTime());
        return userRepository.save(user);
    }

    @Override
    public User getByMac(String appId,String mac) {
        return userRepository.findOneByMac(appId, mac);
    }

    @Override
    public void gameAward(User user, Integer score) {
        User persistUser = userRepository.findByAppIdAndUserId(user.getAppId(), user.getUserId());
        Assert.notNull(persistUser,"user not found");
        persistUser.setGameScore(persistUser.getGameScore()+score);
        userRepository.save(persistUser);
    }

    @Override
    public Long getSurplus(String appId, Integer userId) {
        Long surplus = userRepository.getSurplus(appId,userId);
        return surplus == null ? 0L : surplus;
    }

    @Override
    public boolean consumeScore(Integer userId, int consumeScore) {
        if (consumeScore <= 0)
            return false;
        int attachedRows = userRepository.consumeScore(userId, consumeScore);
        return attachedRows > 0 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public boolean consumeScore(String appId, String userId, int consumeScore) {
        if (consumeScore <= 0)
            return false;
        int attachedRows = userRepository.consumeScore(appId, userId, consumeScore);
        return attachedRows > 0 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public boolean recordEyeCountIncrease(String appId, String userId, Integer count) {
        int attachedRows = userRepository.recordEye(appId, userId, count);
        return attachedRows > 0 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public boolean recordTimeCountIncrease(String appId, String userId, Integer count) {
        int attachedRows = userRepository.recordTime(appId, userId, count);
        return attachedRows > 0 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public void awardScore(Integer id,int newScore) {
        userRepository.awardScore(id,newScore);
    }

    @Override
    public void delUser(User user) {
        userRepository.delete(user);
    }

    @Override
    public boolean upgradeUserLevel(String appId, String userId, Integer levelIncrease) {
        return userRepository.increaseUserLevel(appId, userId, levelIncrease) > 0;
    }

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AppService appService;

    public UserRepository getUserRepository() {
        return userRepository;
    }

    static public enum UserError  implements Error {
        NOT_USER(1200 , "不存在此用户")
        ;
        private  UserError (int  code  ,String info ){
            this.setCode(code);
            this.setInfo(info);
        }
        private int code ;
        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        private String info ;
    }
    @Transactional
    public Error perfectUserInfo (int id  ,  String nickName ,String qq , String alipayNo , String mobilePhone)   {
        User user = this.getById(id) ;
        Error userError  = null ;
        if (user==null){
            userError = UserError.NOT_USER;
        }
        else{
            user.setNickName(nickName);
            user.setQq(qq);
            user.setAlipayNo(alipayNo);
            user.setMobilePhone(mobilePhone);
            this.save(user) ;
        }
        return userError ;
    }
    public Page<User> getSurplusDesc(PageBasicInfo pageBasicInfo){
        Pageable pageable =  new PageRequest(pageBasicInfo.getPage(), pageBasicInfo.getSize() , Sort.Direction.DESC   , User.Field.surplus.toString());
        return this.userRepository.findAll(pageable) ;
    }
    public List<User> getAll(){
        return  this.userRepository.findAll() ;
    }
    @Transactional
    public void updateLastLoginTime (User user){
        user.setLastLoginTime(new Date());
        this.save(user) ;
    }
    public void initialLiveness(User user){
        user.setLiveness(0) ;
        this.save(user) ;
    }
}
