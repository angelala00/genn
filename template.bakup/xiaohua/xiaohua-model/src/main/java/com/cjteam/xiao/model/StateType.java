package com.cjteam.xiao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * StateType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "state_type",  uniqueConstraints = @UniqueConstraint(columnNames = "code"))
public class StateType implements java.io.Serializable {

	// Fields

	private Short id;
	private String code;
	private String meaning;
	private Short order;

	// Constructors

	/** default constructor */
	public StateType() {
	}

	/** minimal constructor */
	public StateType(String code, Short order) {
		this.code = code;
		this.order = order;
	}

	/** full constructor */
	public StateType(String code, String meaning, Short order) {
		this.code = code;
		this.meaning = meaning;
		this.order = order;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Short getId() {
		return this.id;
	}

	public void setId(Short id) {
		this.id = id;
	}

	@Column(name = "code", unique = true, nullable = false, length = 50)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "meaning", length = 50)
	public String getMeaning() {
		return this.meaning;
	}

	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}

	@Column(name = "order", nullable = false)
	public Short getOrder() {
		return this.order;
	}

	public void setOrder(Short order) {
		this.order = order;
	}

}