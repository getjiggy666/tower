package com.telecom.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.ForeignKey;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.userdetails.UserDetails;

/**
 * 实体类 - 用户
 */

@Entity
@JsonIgnoreProperties(value = { "JavassistLazyInitializer",
	"hibernateLazyInitializer", "handler", "authorities", "department",
	"roleSet" })
public class Admin extends BaseEntity implements UserDetails {

    private static final long serialVersionUID = -7519486823153844426L;
    public static final String AUTHEN_CODE_SESSION_ID = "authen_code_session_id";

    private String username;// 用户名
    private String password;// MD5密码
    private String PasswordBase64;// BASE64密码
    private String name;// 姓名
    private String mobile;// 手机号
    private Boolean isAccountEnabled;// 账号是否启用
    private Boolean isAccountLocked;// 账号是否锁定
    private Boolean isAccountExpired;// 账号是否过期
    private Boolean isCredentialsExpired;// 凭证是否过期
    private Integer loginFailureCount;// 连续登录失败的次数
    private Date lockedDate;// 账号锁定日期
    private Date loginDate;// 最后登录日期
    private String loginIp;// 最后登录IP

    private GrantedAuthority[] authorities;// 角色信息
    private Department department;// 部门

    private Set<Role> roleSet = new HashSet<Role>();// 管理角色

    @Column(nullable = false, updatable = false, unique = true)
    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    @Column(nullable = false)
    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    @Column(nullable = true)
    public String getPasswordBase64() {
	return PasswordBase64;
    }

    public void setPasswordBase64(String passwordBase64) {
	PasswordBase64 = passwordBase64;
    }

    @Column(nullable = true)
    public String getMobile() {
	return mobile;
    }

    public void setMobile(String mobile) {
	this.mobile = mobile;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    @ForeignKey(name = "fk_admin_department")
    public Department getDepartment() {
	return department;
    }

    public void setDepartment(Department department) {
	this.department = department;
    }

    @Column(nullable = false)
    public Boolean getIsAccountEnabled() {
	return isAccountEnabled;
    }

    public void setIsAccountEnabled(Boolean isAccountEnabled) {
	this.isAccountEnabled = isAccountEnabled;
    }

    @Column(nullable = false)
    public Boolean getIsAccountLocked() {
	return isAccountLocked;
    }

    public void setIsAccountLocked(Boolean isAccountLocked) {
	this.isAccountLocked = isAccountLocked;
    }

    @Column(nullable = false)
    public Boolean getIsAccountExpired() {
	return isAccountExpired;
    }

    public void setIsAccountExpired(Boolean isAccountExpired) {
	this.isAccountExpired = isAccountExpired;
    }

    @Column(nullable = false)
    public Boolean getIsCredentialsExpired() {
	return isCredentialsExpired;
    }

    public void setIsCredentialsExpired(Boolean isCredentialsExpired) {
	this.isCredentialsExpired = isCredentialsExpired;
    }

    @Column(nullable = false)
    public Integer getLoginFailureCount() {
	return loginFailureCount;
    }

    public void setLoginFailureCount(Integer loginFailureCount) {
	this.loginFailureCount = loginFailureCount;
    }

    public Date getLockedDate() {
	return lockedDate;
    }

    public void setLockedDate(Date lockedDate) {
	this.lockedDate = lockedDate;
    }

    public Date getLoginDate() {
	return loginDate;
    }

    public void setLoginDate(Date loginDate) {
	this.loginDate = loginDate;
    }

    public String getLoginIp() {
	return loginIp;
    }

    public void setLoginIp(String loginIp) {
	this.loginIp = loginIp;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    @OrderBy("name asc")
    @ForeignKey(name = "fk_admin_role_set")
    public Set<Role> getRoleSet() {
	return roleSet;
    }

    public void setRoleSet(Set<Role> roleSet) {
	this.roleSet = roleSet;
    }

    // 保存处理
    @Override
    @Transient
    public void onSave() {
	if (isAccountEnabled == null) {
	    isAccountEnabled = false;
	}
	if (isAccountLocked == null) {
	    isAccountLocked = false;
	}
	if (isAccountExpired == null) {
	    isAccountExpired = false;
	}
	if (isCredentialsExpired == null) {
	    isCredentialsExpired = false;
	}
	if (loginFailureCount == null || loginFailureCount < 0) {
	    loginFailureCount = 0;
	}
    }

    // 更新处理
    @Override
    @Transient
    public void onUpdate() {
	if (isAccountEnabled == null) {
	    isAccountEnabled = false;
	}
	if (isAccountLocked == null) {
	    isAccountLocked = false;
	}
	if (isAccountExpired == null) {
	    isAccountExpired = false;
	}
	if (isCredentialsExpired == null) {
	    isCredentialsExpired = false;
	}
	if (loginFailureCount == null || loginFailureCount < 0) {
	    loginFailureCount = 0;
	}
    }

    @Transient
    public GrantedAuthority[] getAuthorities() {
	return authorities;
    }

    public void setAuthorities(GrantedAuthority[] authorities) {
	this.authorities = authorities;
    }

    @Transient
    public boolean isEnabled() {
	return this.isAccountEnabled;
    }

    @JsonIgnore
    @Transient
    public boolean isAccountNonLocked() {
	return !this.isAccountLocked;
    }

    @JsonIgnore
    @Transient
    public boolean isAccountNonExpired() {
	return !this.isAccountExpired;
    }

    @JsonIgnore
    @Transient
    public boolean isCredentialsNonExpired() {
	return !this.isCredentialsExpired;
    }

}