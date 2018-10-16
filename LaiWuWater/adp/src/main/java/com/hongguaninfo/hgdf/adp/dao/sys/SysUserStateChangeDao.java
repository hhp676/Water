/*
 * File Name:SysUserStateChangeDao.java
 * Package Name:com.hongguaninfo.hgdf.bud.dao.sys
 * Date:2018年01月04日 下午3:55:45
 * Copyright (c) 2016, hongguaninfo.com All Rights Reserved.
 *
 */
package com.hongguaninfo.hgdf.adp.dao.sys;

import org.springframework.stereotype.Repository;

import com.hongguaninfo.hgdf.adp.core.base.BaseDao;
import com.hongguaninfo.hgdf.adp.entity.sys.SysUserStateChange;
import com.hongguaninfo.hgdf.adp.mapper.sys.SysUserStateChangeMapper;

/**
 * 用户状态变更表: sys_user_state_change. <br />
 * dao 层 <br />
 * Date: 2018年01月04日 下午3:55:45 <br />
 * @author menghaixiao
 * @since V1.0.0
 */
@SuppressWarnings("unchecked")
@Repository("sysUserStateChangeDao")
public class SysUserStateChangeDao extends BaseDao<SysUserStateChange, SysUserStateChangeMapper, Integer> implements
		SysUserStateChangeMapper{

    @Override
    public String getMapperNamesapce() {
        return SysUserStateChangeMapper.class.getName().toString();
    }


}
