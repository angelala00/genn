package com.cjteam.xiao.model;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

/**
 * Withdraw entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "withdraw")
public class Withdraw implements java.io.Serializable {

	// Fields

	private Integer id;
    private String appId;
	private User user;
	private String alipayNo;
	private Float amountMoney;
    private Integer scoreCost;
	private Date createTime;
	private Date checkTime;
	private StateType state;
	private Exchange exchange;

	// Constructors

	/** default constructor */
	public Withdraw() {
	}

	/** minimal constructor */
	public Withdraw(Date createTime, Date checkTime) {
		this.createTime = createTime;
		this.checkTime = checkTime;
	}

    public Withdraw(Exchange exchange) {
        setExchange(exchange);
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
	@JoinColumn(name = "user_id",referencedColumnName="user_id")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
        if (null != user && StringUtils.isBlank(appId)) {
            setAppId(user.getAppId());
        }
    }

	@Column(name = "alipay_no", length = 100)
	public String getAlipayNo() {
		return this.alipayNo;
	}

	public void setAlipayNo(String alipayNo) {
		this.alipayNo = alipayNo;
	}

	@Column(name = "amount_money", precision = 12, scale = 0)
	public Float getAmountMoney() {
		return this.amountMoney;
	}

	public void setAmountMoney(Float amountMoney) {
		this.amountMoney = amountMoney;
	}

	@Column(name = "create_time", nullable = false, length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "check_time", nullable = false, length = 19)
	public Date getCheckTime() {
		return this.checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

    @ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "state", referencedColumnName = "code")
	public StateType getState() {
        return this.state;
    }

	public void setState(StateType state) {
		this.state = state;
	}

    @PrePersist
    public void prePersist(){
        setCreateTime(new Date());
    }

	@Override
	public String toString() {
		return "Withdraw [id=" + id + ", user.getUserId()=" + user.getUserId() + ", alipayNo=" + alipayNo + ", amountMoney=" + amountMoney + ", createTime="
				+ createTime + ", checkTime=" + checkTime + ", state=" + state + "]";
	}

    @Column(name = "score_cost")
    public Integer getScoreCost() {
        return scoreCost;
    }

    public void setScoreCost(Integer scoreCost) {
        this.scoreCost = scoreCost;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exchange")
    @NotFound(action = NotFoundAction.IGNORE)
    public Exchange getExchange() {
        return exchange;
    }

    public void setExchange(Exchange exchange) {
        this.exchange = exchange;
    }

    @Column(name = "app_id")
    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
}