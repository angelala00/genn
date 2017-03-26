package com.cjteam.xiao.model;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * User entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"app_id", "user_id"}),
        @UniqueConstraint(columnNames = {"app_id", "mac", "open_udid", "token"})})
public class User implements java.io.Serializable {
    static public enum Field{
        surplus ;
    }
    private static final Logger log = LoggerFactory.getLogger(User.class);

    // Fields

    private Integer id;
//    @Version
    private String qq ;
    private Integer liveness;
    private Date lastLoginTime  ;
    @Column(name = "last_login_time")
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    private Long versionId;
    private String appId;
    private String userId;
    private String uniqueUserId;
    private String mac;
    private String openUdid;
    private String ip;
    private String token;
    private String nickName;
    private String password;
    private String mobilePhone;
    private int validTimes = 3;
    private Boolean isBlack = Boolean.FALSE;
    private String dailyExchangeLimit;
    private String versionNo;
    private Long surplus = 0l;
    private Long totalPoints = 0l;
    private Date createTime;
    public static final String fieldCreateTime = "createTime";
    private Date updateTime;
    private String sce;
    private Set<Exchange> exchanges = new HashSet<Exchange>(0);
    private Set<Integral> integrals = new HashSet<Integral>(0);
    private Set<Withdraw> withdraws = new HashSet<Withdraw>(0);

    private String shark;
    private Boolean award = Boolean.FALSE;
    public static final int defaultDailyExchangeMoneyLimit = 20 * 100;//100 = DoubleNineAgent.RATE
    private Long gameScore = 0l;
    private String alipayNo;
    private Integer productEyeCount = 0;
    private Integer productTimeCount = 0;
    private Integer level =0;
    private String deviceUdid;

    public User() {
    }

    // Property accessors
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "liveness")
    public Integer getLiveness() {
        return this.liveness;
    }

    public void setLiveness(Integer liveness) {
        this.liveness = liveness;
    }

    @Column
    public String getQq() {
        return qq;
    }
    public void setQq(String qq) {
        this.qq = qq;
    }

    @Column(name = "client_user_id", unique = true, length = 100)
    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Column(name = "user_id", unique = true, length = 100)
    public String getUniqueUserId() {
        return uniqueUserId;
    }

    public void setUniqueUserId(String uniqueUserId) {
        this.uniqueUserId = uniqueUserId;
    }

    @Column(name = "mac", nullable = false, length = 100)
    public String getMac() {
        return this.mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    @Column(name = "open_udid", nullable = false, length = 50)
    public String getOpenUdid() {
        return this.openUdid;
    }

    public void setOpenUdid(String openUdid) {
        this.openUdid = openUdid;
    }

    @Column(name = "ip", length = 45)
    public String getIp() {
        return this.ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Column(name = "token")
    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Column(name = "nick_name", length = 45)
    public String getNickName() {
        return this.nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Column(name = "password", length = 30)
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "mobile_phone", length = 20)
    public String getMobilePhone() {
        return this.mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    @Column(name = "is_black", nullable = false)
    public Boolean getIsBlack() {
        return this.isBlack;
    }

    public void setIsBlack(Boolean isBlack) {
        this.isBlack = isBlack;
    }

    @Column(name = "valid_times", nullable = false)
    public int getValidTimes() {
        return validTimes;
    }

    public void setValidTimes(int validTimes) {
        this.validTimes = validTimes;
    }

    @Column(name = "daily_exchange_limit", nullable = true, length = 45)
    public String getDailyExchangeLimit() {
        return dailyExchangeLimit;
    }

    public void setDailyExchangeLimit(String dailyExchangeLimit) {
        if (StringUtils.isBlank(dailyExchangeLimit)) {
            initDailyExchangeLimit();
            return;
        }
        this.dailyExchangeLimit = dailyExchangeLimit;
        log.debug("user {} daily exchange limit is {}", this, this.dailyExchangeLimit);
        try {
            if (!StringUtils.contains(dailyExchangeLimit, ",")) {
                throw new IllegalArgumentException("dailyExchangeLimit filed value is not match special format");
            }
            String[] tmp = getDailyExchangeLimit().split(",");
            pushDailyExchangeLimit(unParseDate(tmp[0]), Integer.valueOf(tmp[1]));
        } catch (Throwable e) {
            log.warn(e.getLocalizedMessage(), e);
        }
    }

    @Column(name = "version_no", length = 20)
    public String getVersionNo() {
        return this.versionNo;
    }

    public void setVersionNo(String versionNo) {
        this.versionNo = versionNo;
    }

    @Column(name = "total_points")
    public Long getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(Long totalPoints) {
        this.totalPoints = totalPoints;
    }

    @Column(name = "surplus")
    public Long getSurplus() {
        return this.surplus;
    }

    public void setSurplus(Long surplus) {
        this.surplus = surplus;
    }

    @Column(name = "create_time", nullable = false, length = 19)
    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "update_time", nullable = false, length = 19)
    public Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,  mappedBy = "user" )
    public Set<Exchange> getExchanges() {
        return this.exchanges;
    }

    public void setExchanges(Set<Exchange> exchanges) {
        this.exchanges = exchanges;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    public Set<Integral> getIntegrals() {
        return this.integrals;
    }

    public void setIntegrals(Set<Integral> integrals) {
        this.integrals = integrals;
    }

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
//    public Set<ZuiquanUserProp> getZuiquanUserProps() {
//        return this.zuiquanUserProps;
//    }
//
//    public void setZuiquanUserProps(Set<ZuiquanUserProp> zuiquanUserProps) {
//        this.zuiquanUserProps = zuiquanUserProps;
//    }



    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    public Set<Withdraw> getWithdraws() {
        return this.withdraws;
    }

    public void setWithdraws(Set<Withdraw> withdraws) {
        this.withdraws = withdraws;
    }

    @PreUpdate
    public void preUpdate() {
        setUpdateTime(new Date());
    }


    private Date deadline;
    private Integer remainingLimit;

    @Transient
    public Date getDeadline() {
        return deadline;
    }

    @Transient
    public Integer getRemainingLimit() {
        return remainingLimit;
    }

    @Transient
    public boolean exchangeInRemainingLimit(Integer cost) {
        Date now = new Date();
        if (now.after(deadline)) {
            //new day,rest limit
            log.debug("new day,rest exchange limit");
            initDailyExchangeLimit();
        }
        if (now.before(deadline) && remainingLimit >= cost) {
            log.info("user {} consume limits {} ,", this, cost);
            remainingLimit = remainingLimit - cost;
            log.info("user {} remaining exchange limit is {} .", this, remainingLimit);
            if (remainingLimit >= 0) {
                setDailyExchangeLimit(parseDate(deadline) + "," + remainingLimit);
                return true;
            }
            remainingLimit += cost;

        }
        return false;
    }

    @PrePersist
    public void prePersist() {
        latestCheck();
        Date now = new Date();
        setCreateTime(now);
        setUpdateTime(now);

        initDailyExchangeLimit();
        initSec();
    }

    private void latestCheck() {
        Assert.isTrue(!StringUtils.equals("(null)", getMac()) && !StringUtils.equals("(null)", getMac()) && !StringUtils.equals("(null)", getMac()), "(null) param found");
    }

    private void initDailyExchangeLimit() {
        Calendar deadline = Calendar.getInstance();
        deadline.add(Calendar.DAY_OF_MONTH, 1);
        setDailyExchangeLimit(parseDate(deadline.getTime()) + "," + defaultDailyExchangeMoneyLimit);
    }

    private void clearTimePart(Calendar deadline) {
        deadline.set(Calendar.HOUR_OF_DAY, 0);
        deadline.set(Calendar.MINUTE, 0);
        deadline.set(Calendar.SECOND, 0);
        deadline.set(Calendar.MILLISECOND, 0);
    }

    private void pushDailyExchangeLimit(Date deadline, int exchangeMoneyLimit) {
        this.deadline = deadline;
        remainingLimit = exchangeMoneyLimit;
        log.debug("deadline is {}", deadline);
        log.debug("remainingLimit is {}", remainingLimit);
    }

    private String parseDate(Date dateTime) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(dateTime);
    }

    private Date unParseDate(String dateTimeStr) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.parse(dateTimeStr);
    }

    @Column(name = "sce", length = 50)
    public String getSce() {
      /*  if (StringUtils.isBlank(sce)) {
            initSec();
        }*/
        return sce;
    }

    public void setSce(String sce) {
        this.sce = sce;
        /*if(StringUtils.isBlank(sce)){
            initSec();
        }*/
    }

    @Column(name = "shark")
    public String getShark() {
        return shark;
    }

    public void setShark(String shark) {
        this.shark = shark;
    }

    @Column(name = "award")
    public Boolean getAward() {
        return award;
    }

    public void setAward(Boolean award) {
        this.award = award;
    }

    private void initSec() {
        setSce("1" + new Date().getTime());
    }

    @Column(name = "app_id")
    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }


    @Transient
    public int getSecIndex() {
        if (StringUtils.isNotBlank(sce) && this.sce.length() > 0) {
            return Integer.valueOf(this.sce.substring(0, 1)).intValue();
        }
        return 1;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + getId() +
                ", appId=" + getAppId() +
                ", userId='" + getUserId() + '\'' +
                ", uniqueUserId='" + getUniqueUserId() + '\'' +
                ", mac='" + getMac() + '\'' +
                ", openUdid='" + getOpenUdid() + '\'' +
                ", ip='" + getIp() + '\'' +
                ", token='" + getToken() + '\'' +
                ", nickName='" + getNickName() + '\'' +
                ", mobilePhone='" + getMobilePhone() + '\'' +
                ", validTimes=" + getValidTimes() +
                ", isBlack=" + getIsBlack() +
                ", dailyExchangeLimit='" + getDailyExchangeLimit() + '\'' +
                ", sce=" + getSce() +
                ", versionNo='" + getVersionNo() + '\'' +
                ", surplus=" + getSurplus() +
                ", gameScore=" + getGameScore() +
                ", createTime=" + getCreateTime() +
                ", updateTime=" + getUpdateTime() +
                '}';
    }

    @Column(name = "version_id")
    public Long getVersionId() {
        return versionId;
    }

    public void setVersionId(Long versionId) {
        this.versionId = versionId;
    }

    public void addSurplus(Integer newScore) {
        setSurplus(getSurplus() + newScore);   //surplus default is o
        setTotalPoints(getTotalPoints() + newScore);//totalPoints default is o
    }

    @Transient
    public String getSpecialKey() {
        StringBuilder sb = new StringBuilder();
        sb.append(getUserId()).append(",")
                .append(getMac()).append(",")
                .append(getToken()).append(",")
                .append(getOpenUdid()).append(",");
        return sb.toString();
    }

    @Column(name = "game_score")
    public Long getGameScore() {
        return gameScore;
    }

    public void setGameScore(Long gameScore) {
        this.gameScore = gameScore;
    }

    @Column(name = "alipay_no")
    public String getAlipayNo() {
        return alipayNo;
    }

    public void setAlipayNo(String alipayNo) {
        this.alipayNo = alipayNo;
    }

    @Column(name = "product_eye_count")
    public Integer getProductEyeCount() {
        return productEyeCount;
    }

    public void setProductEyeCount(Integer productEyeCount) {
        this.productEyeCount = productEyeCount;
    }

    @Column(name = "product_time_count")
    public Integer getProductTimeCount() {
        return productTimeCount;
    }

    public void setProductTimeCount(Integer productTimeCount) {
        this.productTimeCount = productTimeCount;
    }

    @Column(name = "level")
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Column(name = "udid")
         public String getDeviceUdid() {
        return deviceUdid;
    }

    public void setDeviceUdid(String deviceUdid) {
        this.deviceUdid = deviceUdid;
    }
}