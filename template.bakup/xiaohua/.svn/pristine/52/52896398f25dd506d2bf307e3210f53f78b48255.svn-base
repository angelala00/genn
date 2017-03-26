package com.cjteam.xiao.service;

import com.cjteam.xiao.error.Error;
import com.cjteam.xiao.model.User;
import com.cjteam.xiao.service.impl.QueryCondition;
import com.cjteam.xiao.util.PageBasicInfo;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by ChenLong Date: 13-9-26
 */
public interface UserService {
    User updateUserInfo(String appId, String clientUserId, String telphone, String newpassword, String newNick, String alipayNo, String newqq);
    List<User> getAll() ;
    User create(User user) throws Exception;
    public User getByUniqueUserId(String userId) ;
    User getOne(String appId, String userId);

    boolean rollbackExchangeConsumptionScore(User consumer, Integer scoreConsumption);

    boolean reduceUserScore(User user, Integer scoreConsumption);

    boolean userInfoIsValid(User user);

    Page<User> queryAll(String appId, int pageSize, QueryCondition queryCondition, int page);

    Page<User> getBlackListUsers(String appId, int pageSize, QueryCondition queryCondition, int page);

    User save(User user);

    Boolean blackUser(Integer id);

    Boolean whiteUser(Integer id);

    User updateSeckey(Integer id);

    void updateLastLoginIp(User user, String currentLoginIp);

    Page<User> getTopTotallyScoredUserByPage(String appId, int pageSize, int pageNo);

    User findByOpenUdid(String appId, String device);

    User getById(Integer id);

    int sharkTodayForUser(User user);

    void increaseScore(Integer id, Integer award);

    User getByMac(String appId,String mac);

    void gameAward(User user, Integer score);

    Long getSurplus(String appId, Integer userId);

    boolean consumeScore(Integer userId, int consumeScore);

    void awardScore(Integer id, int newScore);

    void delUser(User user);

    boolean consumeScore(String appId, String userId, int sumCost);

    boolean recordEyeCountIncrease(String appId, String userId, Integer count);

    boolean recordTimeCountIncrease(String appId, String userId, Integer count);

    boolean upgradeUserLevel(String appId, String userId, Integer levelIncrease);
    public Error perfectUserInfo (int id  ,  String nickName ,String qq , String alipayNo , String mobilePhone) ;
    public User getByUserId(String userId) ;
    public Page<User> getSurplusDesc(PageBasicInfo pageBasicInfo) ;

    public void updateLastLoginTime (User user) ;
    public void initialLiveness(User user) ;
}
