package com.cjteam.xiao.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Channel entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "channel", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Channel implements java.io.Serializable {

    // Fields

    private Integer id;
    private String appId;
    private Integer used = 1;
    private Integer codeInt;
    private String code;
    private ChannelGroup group;
    private String name;
    private String packageName;
    private Integer maxScoreTimes;
    private Integer maxScoreDaily;
    private Integer twiceInternal;
    private Integer dailyTimes;
    private String description;
    private Integer order;
    private String advertisementUrl;
    private String integralUrl;
    private String pic;
    private String type;
    private Boolean valid;
    private String platformSec;
    private Date createTime;
    private Date updateTime;
    private String keys;
    private Set<Integral> integrals = new HashSet<Integral>(0);

    // Constructors

    /**
     * default constructor
     */
    public Channel() {
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

    @ManyToOne
    @JoinColumns({@JoinColumn(name = "app_id", referencedColumnName = "app_id"), @JoinColumn(name = "group_code", referencedColumnName = "code")})
    @NotFound(action = NotFoundAction.IGNORE)
    public ChannelGroup getGroup() {
        return group;
    }

    public void setGroup(ChannelGroup group) {
        this.group = group;
    }

    @Column(name = "code", nullable = false, length = 50)
    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "code_int")
    public Integer getCodeInt() {
        return codeInt;
    }

    public void setCodeInt(Integer codeInt) {
        this.codeInt = codeInt;
    }

    @Column(name = "name", unique = true, nullable = false, length = 100)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "max_score_times")
    public Integer getMaxScoreTimes() {
        return this.maxScoreTimes;
    }

    public void setMaxScoreTimes(Integer maxScoreTimes) {
        this.maxScoreTimes = maxScoreTimes;
    }

    @Column(name = "max_score_daily")
    public Integer getMaxScoreDaily() {
        return this.maxScoreDaily;
    }

    public void setMaxScoreDaily(Integer maxScoreDaily) {
        this.maxScoreDaily = maxScoreDaily;
    }

    @Column(name = "twice_internal")
    public Integer getTwiceInternal() {
        return this.twiceInternal;
    }

    public void setTwiceInternal(Integer twiceInternal) {
        this.twiceInternal = twiceInternal;
    }

    @Column(name = "description")
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "order_")
    public Integer getOrder() {
        return this.order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    @Column(name = "is_valid", nullable = false)
    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    @Column(name = "create_time", length = 19)
    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "update_time", length = 19)
    public Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "channel")
    public Set<Integral> getIntegrals() {
        return this.integrals;
    }

    public void setIntegrals(Set<Integral> integrals) {
        this.integrals = integrals;
    }

    @Column(name = "advertisement_url", length = 200)
    public String getAdvertisementUrl() {
        return advertisementUrl;
    }

    public void setAdvertisementUrl(String advertisementUrl) {
        this.advertisementUrl = advertisementUrl;
    }

    @Column(name = "integral_url", length = 200)
    public String getIntegralUrl() {
        return integralUrl;
    }

    public void setIntegralUrl(String integralUrl) {
        this.integralUrl = integralUrl;
    }

    @Column(name = "type", length = 10)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "pic", length = 100)
    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    @Column(name = "daily_times")
    public Integer getDailyTimes() {
        return dailyTimes;
    }

    public void setDailyTimes(Integer dailyTimes) {
        this.dailyTimes = dailyTimes;
    }

    @Column(name = "platform_sec")
    public String getPlatformSec() {
        return platformSec;
    }

    public void setPlatformSec(String platformSec) {
        this.platformSec = platformSec;
    }

    @Column(name = "app_id", insertable = false, updatable = false)
    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    @Column(name = "used")
    public Integer getUsed() {
        return used;
    }

    public void setUsed(Integer used) {
        this.used = used;
    }

    @Column(name = "package_name")
    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    @Column(name = "keys")
    public String getKeys() {
        return keys;
    }

    public void setKeys(String keys) {
        this.keys = keys;
    }

    @Override
    public String toString() {
        return "Channel [id=" + id + ", code=" + code + ", name=" + name
                + ",type=" + type + ", valid="
                + valid + "]";
    }

    @PrePersist
    public void prePersist() {
        Date now = new Date();
        setCreateTime(now);
        setUpdateTime(now);
    }

    @PreUpdate
    public void preUpdate() {
        setUpdateTime(new Date());
    }
}