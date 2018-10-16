/*
 * File Name:JbzProductInfoDao.java
 * Package Name:com.hongguaninfo.hgdf.adp.dao.jbz
 * Date:2018年01月11日 下午9:09:20
 * Copyright (c) 2016, hongguaninfo.com All Rights Reserved.
 *
 */
package com.hongguaninfo.hgdf.jbz.dao.jbz;

import org.springframework.stereotype.Repository;

import com.hongguaninfo.hgdf.adp.core.base.BaseDao;
import com.hongguaninfo.hgdf.jbz.entity.jbz.JbzProductInfo;
import com.hongguaninfo.hgdf.jbz.mapper.jbz.JbzProductInfoMapper;


/**
 * : jbz_product_info. <br />
 * dao 层 <br />
 * Date: 2018年01月11日 下午9:09:20 <br />
 * @author hhp
 * @since V1.0.0
 */
@SuppressWarnings("unchecked")
@Repository("jbzProductInfoDao")
public class JbzProductInfoDao extends BaseDao<JbzProductInfo, JbzProductInfoMapper, Integer> implements
		JbzProductInfoMapper{

    @Override
    public String getMapperNamesapce() {
        return JbzProductInfoMapper.class.getName();
    }


}
