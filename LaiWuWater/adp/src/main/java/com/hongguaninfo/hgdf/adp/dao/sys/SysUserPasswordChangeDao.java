/*
 * File Name:SysUserPasswordChangeDao.java
 * Package Name:com.hongguaninfo.hgdf.bud.dao.sys
 * Date:2018年01月03日 下午2:21:54
 * Copyright (c) 2016, hongguaninfo.com All Rights Reserved.
 *
 */
package com.hongguaninfo.hgdf.adp.dao.sys;

import org.springframework.stereotype.Repository;

import com.hongguaninfo.hgdf.adp.core.base.BaseDao;
import com.hongguaninfo.hgdf.adp.entity.sys.SysUserPasswordChange;
import com.hongguaninfo.hgdf.adp.mapper.sys.SysUserPasswordChangeMapper;

/**
 * 用户密码变更记录表: sys_user_password_change. <br />
 * dao 层 <br />
 * Date: 2018年01月03日 下午2:21:54 <br />
 * @author menghaixiao
 * @since V1.0.0
 */
@SuppressWarnings("unchecked")
@Repository("sysUserPasswordChangeDao")
public class SysUserPasswordChangeDao extends BaseDao<SysUserPasswordChange, SysUserPasswordChangeMapper, Integer> implements
		SysUserPasswordChangeMapper{

    @Override
    public String getMapperNamesapce() {
        return SysUserPasswordChangeMapper.class.getName().toString();
    }


}
