package com.cjteam.xiao.xiaohua.model;

// Generated 2014-7-23 15:31:44 by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * XiaohuaSpiderConfig generated by hbm2java
 */
@Entity
@Table(name = "xiaohua_spider_config")
public class XiaohuaSpiderConfig implements java.io.Serializable {

	private Integer id;
	private String url;
	private Integer pageTotal;
	private Integer pageCurrent;
	private Date startTime ; 
	private Date endTime;
	@Column(name = "start_time")
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	@Column(name = "end_time")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	private Set<XiaohuaContent> xiaohuaContents = new HashSet<XiaohuaContent>(0);

	public XiaohuaSpiderConfig() {
	}

	public XiaohuaSpiderConfig(String url, Integer pageTotal,
			Integer pageCurrent, Set<XiaohuaContent> xiaohuaContents) {
		this.url = url;
		this.pageTotal = pageTotal;
		this.pageCurrent = pageCurrent;
		this.xiaohuaContents = xiaohuaContents;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "url")
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "page_total")
	public Integer getPageTotal() {
		return this.pageTotal;
	}

	public void setPageTotal(Integer pageTotal) {
		this.pageTotal = pageTotal;
	}

	@Column(name = "page_current")
	public Integer getPageCurrent() {
		return this.pageCurrent;
	}

	public void setPageCurrent(Integer pageCurrent) {
		this.pageCurrent = pageCurrent;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "xiaohuaSpiderConfig")
	public Set<XiaohuaContent> getXiaohuaContents() {
		return this.xiaohuaContents;
	}

	public void setXiaohuaContents(Set<XiaohuaContent> xiaohuaContents) {
		this.xiaohuaContents = xiaohuaContents;
	}

}
