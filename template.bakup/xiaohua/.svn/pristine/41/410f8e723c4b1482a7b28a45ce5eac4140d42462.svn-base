package com.cjteam.xiao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SysConf entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sys_conf")
public class SysConf implements java.io.Serializable {

	// Fields

	private Integer id;
    private String appId;
	private String name;
	private String code;
	private String value;
	private String desc;

	// Constructors

	/** default constructor */
	public SysConf() {
	}

	/** minimal constructor */
	public SysConf(String name, String code, String value) {
		this.name = name;
		this.code = code;
		this.value = value;
	}

	/** full constructor */
	public SysConf(String name, String code, String value, String desc) {
		this.name = name;
		this.code = code;
		this.value = value;
		this.desc = desc;
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

	@Column(name = "name", nullable = false, length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "code", nullable = false, length = 45)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "value", nullable = false, length = 45)
	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Column(name = "desc", length = 200)
	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

    @Column(name = "app_id")
    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
}