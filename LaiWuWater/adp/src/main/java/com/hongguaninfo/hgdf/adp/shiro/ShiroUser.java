package com.hongguaninfo.hgdf.adp.shiro;

import com.google.common.base.Objects;
import com.hongguaninfo.hgdf.adp.entity.sys.SysUser;

/**
 * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息.
 */
public class ShiroUser extends SysUser {

    private String name;

    public ShiroUser(Integer userId, String loginName, String name) {
        this.userId = userId;
        this.loginName = loginName;
        this.name = name;
    }

    public String getLoginName() {
        return loginName;
    }

    /**
     * 本函数输出将作为默认的<shiro:principal/>输出.
     */
    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }
    public void setName(String name){
    	this.name = name;
    }
    /**
     * 重载hashCode,只计算loginName;
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(loginName);
    }

    /**
     * 重载equals,只计算loginName;
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ShiroUser other = (ShiroUser) obj;
        if (loginName == null) {
            if (other.loginName != null) {
                return false;
            }
        } else if (!loginName.equals(other.loginName)) {
            return false;
        }
        return true;
    }
}