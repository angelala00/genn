package com.cjteam.xiao.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * IntegralType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "integral_type", uniqueConstraints = @UniqueConstraint(columnNames = "code"))
public class IntegralType implements java.io.Serializable {
    public static final String INFO_COMPLETELY="person_information_consummate";

    // Fields

	private Integer id;
	private String code;
	private String label;
	private String meaning;
	private Set<Integral> integrals = new HashSet<Integral>(0);

	// Constructors

	/** default constructor */
	public IntegralType() {
	}

	/** minimal constructor */
	public IntegralType(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public IntegralType(Integer id, String code, String label, String meaning,
			Set<Integral> integrals) {
		this.id = id;
		this.code = code;
		this.label = label;
		this.meaning = meaning;
		this.integrals = integrals;
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

	@Column(name = "code", unique = true, length = 20)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "label", length = 45)
	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Column(name = "meaning", length = 100)
	public String getMeaning() {
		return this.meaning;
	}

	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "integralType")
	public Set<Integral> getIntegrals() {
		return this.integrals;
	}

	public void setIntegrals(Set<Integral> integrals) {
		this.integrals = integrals;
	}


	@Override
	public String toString() {
		return "IntegralType [id=" + id + ", code=" + code + ", label=" + label + ", meaning=" + meaning + ", integrals.size()=" + integrals.size() + "]";
	}

}