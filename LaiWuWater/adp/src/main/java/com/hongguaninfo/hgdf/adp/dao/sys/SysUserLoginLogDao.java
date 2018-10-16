/*
 * File Name:SysUserLoginLogDao.java
 * Package Name:com.hongguaninfo.hgdf.bud.dao.sys
 * Date:2018年01月05日 下午3:56:34
 * Copyright (c) 2016, hongguaninfo.com All Rights Reserved.
 *
 */
package com.hongguaninfo.hgdf.adp.dao.sys;

import org.springframework.stereotype.Repository;

import com.hongguaninfo.hgdf.adp.core.base.BaseDao;
import com.hongguaninfo.hgdf.adp.entity.sys.SysUserLoginLog;
import com.hongguaninfo.hgdf.adp.mapper.sys.SysUserLoginLogMapper;

/**
 * 用户登录日志表: sys_user_login_log. <br />
 * dao 层 <br />
 * Date: 2018年01月05日 下午3:56:34 <br />
 * @author menghaixiao
 * @since V1.0.0
 */
@SuppressWarnings("unchecked")
@Repository("sysUserLoginLogDao")
public class SysUserLoginLogDao extends BaseDao<SysUserLoginLog, SysUserLoginLogMapper, Integer> implements
		SysUserLoginLogMapper{

    @Override
    public String getMapperNamesapce() {
        return SysUserLoginLogMapper.class.getName().toString();
    }


}
