package com.cjteam.xiao.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * DnProduct entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "dn_product", uniqueConstraints = @UniqueConstraint(columnNames = "dn_id"))
public class DnProduct implements java.io.Serializable {

	// Fields

	private Integer id;
	private String dnId;
	private String name;
	private Integer price;
	private String info;
	private Integer type;
	private Set<Product> products = new HashSet<Product>(0);

	// Constructors

	/** default constructor */
	public DnProduct() {
	}

	/** full constructor */
	public DnProduct(String dnId, String name, Integer price, String info,
			Integer type, Set<Product> products) {
		this.dnId = dnId;
		this.name = name;
		this.price = price;
		this.info = info;
		this.type = type;
		this.products = products;
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

	@Column(name = "dn_id",length = 100,nullable = false)
	public String getDnId() {
		return this.dnId;
	}

	public void setDnId(String dnId) {
		this.dnId = dnId;
	}

	@Column(name = "name", length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "price")
	public Integer getPrice() {
		return this.price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	@Column(name = "info", length = 100)
	public String getInfo() {
		return this.info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	@Column(name = "type")
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "dnProduct")
	public Set<Product> getProducts() {
		return this.products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

}