/*
 * File Name:SysMetaMethodDao.java
 * Package Name:com.hongguaninfo.hgdf.bud.dao.sys
 * Date:2018年01月03日 下午2:16:08
 * Copyright (c) 2016, hongguaninfo.com All Rights Reserved.
 *
 */
package com.hongguaninfo.hgdf.adp.dao.sys;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hongguaninfo.hgdf.adp.core.base.BaseDao;
import com.hongguaninfo.hgdf.adp.entity.sys.SysMetaMethod;
import com.hongguaninfo.hgdf.adp.mapper.sys.SysMetaMethodMapper;

/**
 * 系统元方法表: sys_meta_method. <br />
 * dao 层 <br />
 * Date: 2018年01月03日 下午2:16:08 <br />
 * @author zhxl
 * @since V1.0.0
 */
@SuppressWarnings("unchecked")
@Repository("sysMetaMethodDao")
public class SysMetaMethodDao extends BaseDao<SysMetaMethod, SysMetaMethodMapper, Integer> implements
		SysMetaMethodMapper{

    @Override
    public String getMapperNamesapce() {
        return SysMetaMethodMapper.class.getName().toString();
    }

    @Override
    public void setAllInvalid() {
        getMapperByType(SysMetaMethodMapper.class).setAllInvalid();
    }

    @Override
    public List<SysMetaMethod> getListByMethodSignature(SysMetaMethod filter) {
        return getMapperByType(SysMetaMethodMapper.class).getListByMethodSignature(filter);
    }
}
