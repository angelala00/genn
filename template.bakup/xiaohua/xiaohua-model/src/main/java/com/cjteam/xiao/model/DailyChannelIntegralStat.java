package com.cjteam.xiao.model;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * DailyChannelIntegralStat entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "daily_channel_integral_stat")
public class DailyChannelIntegralStat implements java.io.Serializable {

	// Fields

	private Integer id;
    private String appId;
	private String channel;
	private Integer score;
	private Date date;
	private String userId;

	// Constructors

	/** default constructor */
	public DailyChannelIntegralStat() {
	}

	/** full constructor */
	public DailyChannelIntegralStat(String channel, Integer score) {
		this.channel = channel;
		this.score = score;
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

	@Column(name = "channel", length = 50)
	public String getChannel() {
		return this.channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	@Column(name = "score", precision = 12, scale = 0)
	public Integer getScore() {
		return this.score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	@Column(name = "user_id")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

    @Column(name = "date",length = 19)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
        return "DailyChannelIntegralStat [id=" + id + ", channel=" + channel + ", score=" + score + ",date=" + date + "]";
    }

}