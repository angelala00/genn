package com.cjteam.xiao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Advertisement entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "advertisements")
public class Advertisement implements java.io.Serializable {

	// Fields

	private Integer id;
	private Channel channel;
	private String name;
	private String content;
	private String pic;
	private Integer score;
	private Short isValid;
	private String description;

	// Constructors

	/** default constructor */
	public Advertisement() {
	}

	/** minimal constructor */
	public Advertisement(Channel channel, String name, Integer score) {
		this.channel = channel;
		this.name = name;
		this.score = score;
	}

	/** full constructor */
	public Advertisement(Channel channel, String name, String content,
                         String pic, Integer score, Short isValid) {
		this.channel = channel;
		this.name = name;
		this.content = content;
		this.pic = pic;
		this.score = score;
		this.isValid = isValid;
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
	@JoinColumn(name = "channel", nullable = false,referencedColumnName = "code")
	public Channel getChannel() {
		return this.channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	@Column(name = "name", nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "content", length = 200)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "pic", length = 100)
	public String getPic() {
		return this.pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	@Column(name = "score", nullable = false)
	public Integer getScore() {
		return this.score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	@Column(name = "is_valid")
	public Short getIsValid() {
		return this.isValid;
	}

	public void setIsValid(Short isValid) {
		this.isValid = isValid;
	}

    @Column(name = "description", length = 200)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}