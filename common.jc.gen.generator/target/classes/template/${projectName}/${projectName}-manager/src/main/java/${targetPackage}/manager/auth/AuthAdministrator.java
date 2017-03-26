package ${targetPackage}.manager.auth;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class AuthAdministrator implements java.io.Serializable, UserDetails {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	public AuthAdministrator() {
	}

	/**
	 * minimal constructor
	 */
	public AuthAdministrator(String name, Boolean isValid) {
		this.name = name;
		this.isValid = isValid;
	}

	/**
	 * full constructor
	 */
	public AuthAdministrator(String name, String username, String email, String password, Date createTime, Date updateTime, Boolean isValid) {
		this.name = name;
		this.username = username;
		this.email = email;
		this.password = password;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.isValid = isValid;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
		this.username = name;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Boolean getIsValid() {
		return this.isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	public Date getLastestLoginTime() {
		return lastestLoginTime;
	}

	public void setLastestLoginTime(Date lastestLoginTime) {
		this.lastestLoginTime = lastestLoginTime;
	}

	@Override
	public String toString() {
		return "AuthAdministrator [id=" + id + ", name=" + name + ", username=" + username + ", email=" + email + ", password=" + password + ", createTime=" + createTime + ", updateTime="
				+ updateTime + ", lastestLoginTime=" + lastestLoginTime + ", isValid=" + isValid + ", role=" + role + ", authorities=" + authorities + ", accountNonExpired=" + accountNonExpired
				+ ", accountNonLocked=" + accountNonLocked + ", credentialsNonExpired=" + credentialsNonExpired + ", enabled=" + enabled + "]";
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	public void prePersist() {
		Date now = new Date();
		setCreateTime(now);
		setUpdateTime(now);
	}

	public void preUpdate() {
		setUpdateTime(new Date());
	}

	private List<AuthRole> authorities;
	private boolean accountNonExpired = true;
	private boolean accountNonLocked = true;
	private boolean credentialsNonExpired = true;
	private boolean enabled = true;

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	public void setAuthorities(List<AuthRole> authorities) {
		this.authorities = authorities;
	}

	public boolean isAccountNonExpired() {
		return this.accountNonExpired;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public boolean isAccountNonLocked() {
		return this.accountNonLocked;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public boolean isCredentialsNonExpired() {
		return this.credentialsNonExpired;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public boolean isEnabled() {
		return this.enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}