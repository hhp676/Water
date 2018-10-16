/*
 * File Name:WaCompanyInfoDao.java
 * Package Name:com.hongguaninfo.hgdf.bud.dao.wa
 * Date:2018年09月10日 下午5:14:26
 * Copyright (c) 2016, hongguaninfo.com All Rights Reserved.
 *
 */
package com.hongguaninfo.hgdf.wa.dao;

import com.hongguaninfo.hgdf.adp.core.base.BaseDao;
import com.hongguaninfo.hgdf.wa.entity.WaCompanyInfo;
import com.hongguaninfo.hgdf.wa.mapper.WaCompanyInfoMapper;
import org.springframework.stereotype.Repository;


/**
 * : wa_company_info. <br />
 * dao 层 <br />
 * Date: 2018年09月10日 下午5:14:26 <br />
 * @author hhp
 * @since V1.0.0
 */
@SuppressWarnings("unchecked")
@Repository("waCompanyInfoDao")
public class WaCompanyInfoDao extends BaseDao<WaCompanyInfo, WaCompanyInfoMapper, Integer> implements
		WaCompanyInfoMapper{

    @Override
    public String getMapperNamesapce() {
        return WaCompanyInfoMapper.class.getName().toString();
    }


    @Override
    public WaCompanyInfo getEntityByCode(WaCompanyInfo entity) {
        return getMapperByType(WaCompanyInfoMapper.class).getEntityByCode(entity);
    }
}
