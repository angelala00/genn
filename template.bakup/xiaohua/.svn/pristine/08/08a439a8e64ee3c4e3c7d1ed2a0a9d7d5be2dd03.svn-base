package com.cjteam.xiao.model;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Product entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "product", uniqueConstraints = @UniqueConstraint(columnNames = "code"))
public class Product implements java.io.Serializable {

	// Fields

	private Integer id;
	private ProductType productType;
	private DnProduct dnProduct;
	private Integer quantity=1;
	private String code;
	private String name;
    private String pic;
	private Integer price;
	private Integer realPrice;
	private Integer score;
	private String info;
	private Integer valid;
	private Set<Exchange> exchanges = new HashSet<Exchange>(0);

    // Constructors

	/** default constructor */
	public Product() {
	}

	/** minimal constructor */
	public Product(ProductType productType, String code, Integer price,
			Integer realPrice, Integer score) {
		this.productType = productType;
		this.code = code;
		this.price = price;
		this.realPrice = realPrice;
		this.score = score;
	}

	/** full constructor */
	public Product(ProductType productType, DnProduct dnProduct, String code,
			String name, Integer price, Integer realPrice, Integer score,
			String info, Integer valid, Set<Exchange> exchanges) {
		this.productType = productType;
		this.dnProduct = dnProduct;
		this.code = code;
		this.name = name;
		this.price = price;
		this.realPrice = realPrice;
		this.score = score;
		this.info = info;
		this.valid = valid;
		this.exchanges = exchanges;
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
	@JoinColumn(name = "type", nullable = false,referencedColumnName="code")
	public ProductType getProductType() {
		return this.productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dn_id",referencedColumnName="dn_id")
    @NotFound(action = NotFoundAction.IGNORE)
	public DnProduct getDnProduct() {
		return this.dnProduct;
	}

	public void setDnProduct(DnProduct dnProduct) {
		this.dnProduct = dnProduct;
	}

	@Column(name = "code", unique = true, nullable = false, length = 100)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "name", length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "price", nullable = false)
	public Integer getPrice() {
		return this.price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	@Column(name = "real_price", nullable = false)
	public Integer getRealPrice() {
		return this.realPrice;
	}

	public void setRealPrice(Integer realPrice) {
		this.realPrice = realPrice;
	}

	@Column(name = "score", nullable = false)
	public Integer getScore() {
		return this.score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	@Column(name = "info", length = 100)
	public String getInfo() {
		return this.info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	@Column(name = "valid")
	public Integer getValid() {
		return this.valid;
	}

	public void setValid(Integer valid) {
		this.valid = valid;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
	public Set<Exchange> getExchanges() {
		return this.exchanges;
	}

	public void setExchanges(Set<Exchange> exchanges) {
		this.exchanges = exchanges;
	}

    @Column(name = "quantity")
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Column(name = "pic")
    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}