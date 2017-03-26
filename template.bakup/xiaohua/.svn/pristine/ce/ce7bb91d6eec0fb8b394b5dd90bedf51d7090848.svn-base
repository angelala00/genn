package com.cjteam.xiao.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Administrator entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "administrator")
public class Administrator implements java.io.Serializable,UserDetails {

    // Fields

    private Integer id;
    private String name;
    private String username;
    private String email;
    private String password;
    private Date createTime;
    private Date updateTime;
    private Date lastestLoginTime;
    private Boolean isValid = Boolean.TRUE;
    private Integer role = 0;

    // Constructors

    /**
     * default constructor
     */
    public Administrator() {
    }

    /**
     * minimal constructor
     */
    public Administrator(String name, Boolean isValid) {
        this.name = name;
        this.isValid = isValid;
    }

    /**
     * full constructor
     */
    public Administrator(String name, String username, String email,
                         String password, Date createTime, Date updateTime, Boolean isValid) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.createTime = createTime;
        this.updateTime = updateTime;
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

    @Column(name = "name", nullable = false, length = 45)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "username", length = 100)
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "email", length = 100)
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "password", length = 100)
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "create_time", length = 19)
    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "update_time", length = 19)
    public Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Column(name = "is_valid", nullable = false)
    public Boolean getIsValid() {
        return this.isValid;
    }

    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }

    @Column(name = "lastest_login_time", length = 19)
    public Date getLastestLoginTime() {
        return lastestLoginTime;
    }

    public void setLastestLoginTime(Date lastestLoginTime) {
        this.lastestLoginTime = lastestLoginTime;
    }

    @Override
    public String toString() {
        return "Administrator{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                '}';
    }

    @Column(name = "role")
    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    @PrePersist
    public void prePersist() {
        Date now = new Date();
        setCreateTime(now);
        setUpdateTime(now);
    }

    @PreUpdate
    public void preUpdate() {
        setUpdateTime(new Date());
    }

    private List<Role> authorities;
    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;

    @Override
    @Transient
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public void setAuthorities(List<Role> authorities) {
        this.authorities = authorities;
    }

    @Transient
    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    @Transient
    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    @Transient
    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    @Transient
    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}