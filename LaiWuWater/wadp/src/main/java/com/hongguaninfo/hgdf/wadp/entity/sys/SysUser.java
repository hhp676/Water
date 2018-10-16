package com.hongguaninfo.hgdf.wadp.entity.sys;

import java.util.Date;
import java.util.List;

import com.hongguaninfo.hgdf.wadp.core.base.BaseEntity;

public class SysUser extends BaseEntity {
    protected Integer userId;
    protected String userName;
    private String engName;
    protected String loginName;
    private String loginPwd;
    private String newPwd;
    private Integer sex;
    private String sexStr;
    private String birthday;
    private Integer FID;
    private String mobile;
    private String telephone;
    private String email;
    private String address;
    private String linkTel;
    private String position;
    private String descr;
    private Integer isDelete;
    private Integer isValid;
    private Integer isLock;
    private Integer isFinal;
    private String isFinalStr;
    private String lastLoginIp;
    private Date lastLoginTime;
    private Date updTime;
    private Date crtTime;
    private String crtTimeStr;
    private Integer updUserid;
    private Integer crtUserid;

    private String plainPassword;

    private List<SysRole> roles;
    private List<SysAuth> auths;
    private String checkedAuthIds;
    private String checkedRoleIds;
    private String checkedUgroupIds;
    private String authIds;
    private String roleIds;

    // REMIND_TYPE_ : 提醒类型(及时提醒\定时提醒\不提醒)
    private String remindType;

    // REMIND_TIME_ : 定时提醒时间（格式如:18:00:00）
    private String remindTime;

    // 用户名模糊查询
    private String blurUserName;

    // 部门名称
    private String deptName;

    // 部门FID
    private Integer dpFid;

    public Integer getUserId() {
        return userId;
    }

    public String getSexStr() {
        return sexStr;
    }
    public String getCheckedUgroupIds() {
		return checkedUgroupIds;
	}

	public void setCheckedUgroupIds(String checkedUgroupIds) {
		this.checkedUgroupIds = checkedUgroupIds;
	}

	public void setSexStr(String sexStr) {
        this.sexStr = sexStr;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getCheckedRoleIds() {
        return checkedRoleIds;
    }

    public void setCheckedRoleIds(String checkedRoleIds) {
        this.checkedRoleIds = checkedRoleIds;
    }

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEngName() {
        return engName;
    }

    public void setEngName(String engName) {
        this.engName = engName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Integer getFID() {
        return FID;
    }

    public void setFID(Integer fid) {
        FID = fid;
    }

    public String getMobile() {
        return mobile;
    }

    public Integer getUpdUserid() {
        return updUserid;
    }

    public void setUpdUserid(Integer updUserid) {
        this.updUserid = updUserid;
    }

    public Integer getCrtUserid() {
        return crtUserid;
    }

    public void setCrtUserid(Integer crtUserid) {
        this.crtUserid = crtUserid;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public String getCheckedAuthIds() {
        return checkedAuthIds;
    }

    public void setCheckedAuthIds(String checkedAuthIds) {
        this.checkedAuthIds = checkedAuthIds;
    }

    public String getAuthIds() {
        return authIds;
    }

    public void setAuthIds(String authIds) {
        this.authIds = authIds;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLinkTel() {
        return linkTel;
    }

    public void setLinkTel(String linkTel) {
        this.linkTel = linkTel;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public Integer getIsLock() {
        return isLock;
    }

    public void setIsLock(Integer isLock) {
        this.isLock = isLock;
    }

    public Integer getIsFinal() {
        return isFinal;
    }

    public void setIsFinal(Integer isFinal) {
        this.isFinal = isFinal;
    }

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getRemindType() {
        return remindType;
    }

    public void setRemindType(String remindType) {
        this.remindType = remindType;
    }

    public String getRemindTime() {
        return remindTime;
    }

    public void setRemindTime(String remindTime) {
        this.remindTime = remindTime;
    }

    public String getBlurUserName() {
        return blurUserName;
    }

    public void setBlurUserName(String blurUserName) {
        this.blurUserName = blurUserName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Date getUpdTime() {
        return updTime;
    }

    public void setUpdTime(Date updTime) {
        this.updTime = updTime;
    }

    public Integer getDpFid() {
        return dpFid;
    }

    public void setDpFid(Integer dpFid) {
        this.dpFid = dpFid;
    }

    public String getPlainPassword() {
        return plainPassword;
    }

    public void setPlainPassword(String plainPassword) {
        this.plainPassword = plainPassword;
    }

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }

    public List<SysAuth> getAuths() {
        return auths;
    }

    public void setAuths(List<SysAuth> auths) {
        this.auths = auths;
    }

    public Date getCrtTime() {
        return crtTime;
    }

    public void setCrtTime(Date crtTime) {
        this.crtTime = crtTime;
    }

    public String getCrtTimeStr() {
        return crtTimeStr;
    }

    public void setCrtTimeStr(String crtTimeStr) {
        this.crtTimeStr = crtTimeStr;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getIsFinalStr() {
        return isFinalStr;
    }

    public void setIsFinalStr(String isFinalStr) {
        this.isFinalStr = isFinalStr;
    }

}