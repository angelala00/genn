package com.cjteam.xiao.model;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Exchange entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "exchange")
public class Exchange implements java.io.Serializable {

    // Fields

    private Integer id;
    private String appId;
    private Product product;
    private User user;
    private String consumerAccount;
    private Integer moneyExchange;
    private Integer scoreConsumption;
    private Date createTime;
    private Date processingTime;
    private StateType state;
    private Short status = 1;

    // Constructors

    /**
     * default constructor
     */
    public Exchange() {
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_code", referencedColumnName = "code")
    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", updatable = true)
    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
        if (null != user && StringUtils.isBlank(appId)) {
            setAppId(user.getAppId());
        }
    }

    @Column(name = "consumer_account", nullable = false, length = 10)
    public String getConsumerAccount() {
        return consumerAccount;
    }

    public void setConsumerAccount(String consumerAccount) {
        this.consumerAccount = consumerAccount;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "state", referencedColumnName = "code")
    public StateType getState() {
        return state;
    }

    public void setState(StateType state) {
        this.state = state;
    }


    @Column(name = "money_exchange", nullable = false, length = 100)
    public Integer getMoneyExchange() {
        return this.moneyExchange;
    }

    public void setMoneyExchange(Integer moneyExchange) {
        this.moneyExchange = moneyExchange;
    }

    @Column(name = "score_consumption", nullable = false, precision = 12, scale = 0)
    public Integer getScoreConsumption() {
        return this.scoreConsumption;
    }

    public void setScoreConsumption(Integer scoreConsumption) {
        this.scoreConsumption = scoreConsumption;
    }

    @Column(name = "create_time", nullable = false, length = 19)
    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "processing_time", nullable = false, length = 19)
    public Date getProcessingTime() {
        return this.processingTime;
    }

    public void setProcessingTime(Date processingTime) {
        this.processingTime = processingTime;
    }

    @Column(name = "status")
    public Short getStatus() {
        return this.status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    @Column(name = "app_id")
    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }


    @Override
    public String toString() {
        return "Exchange [id=" + id + ", product.getName()=" + product.getName() + ", user.getUserId()=" + user.getUserId() + ", moneyExchange=" + moneyExchange
                + ", scoreConsumption=" + scoreConsumption + ", createTime=" + createTime + ", processingTime=" + processingTime
                + ", status=" + status + "]";
    }

    @PrePersist
    public void prePersist() {
        setCreateTime(new Date());
    }

    public enum ExchangeType {
        QUICK(0, "快充"),
        SLOW(1, "慢充");
        private Integer code;
        private String label;

        ExchangeType(Integer code, String label) {
            this.code = code;
            this.label = label;
        }

        public Integer getCode() {
            return code;
        }

        public String getLabel() {
            return label;
        }
    }
}