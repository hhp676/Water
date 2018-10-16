/*
 * File Name:WaSavewaterTypeDao.java
 * Package Name:com.hongguaninfo.hgdf.bud.dao.wa
 * Date:2018年09月11日 下午8:49:44
 * Copyright (c) 2016, hongguaninfo.com All Rights Reserved.
 *
 */
package com.hongguaninfo.hgdf.wa.dao;

import com.hongguaninfo.hgdf.adp.core.base.BaseDao;
import com.hongguaninfo.hgdf.wa.entity.WaSavewaterType;
import com.hongguaninfo.hgdf.wa.mapper.WaSavewaterTypeMapper;
import org.springframework.stereotype.Repository;


/**
 * : wa_savewater_type. <br />
 * dao 层 <br />
 * Date: 2018年09月11日 下午8:49:44 <br />
 * @author hhp
 * @since V1.0.0
 */
@SuppressWarnings("unchecked")
@Repository("waSavewaterTypeDao")
public class WaSavewaterTypeDao extends BaseDao<WaSavewaterType, WaSavewaterTypeMapper, Integer> implements
		WaSavewaterTypeMapper{

    @Override
    public String getMapperNamesapce() {
        return WaSavewaterTypeMapper.class.getName().toString();
    }


}
