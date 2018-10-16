/*
 * File Name:WaIndustryWaterDataDao.java
 * Package Name:com.hongguaninfo.hgdf.bud.dao.wa
 * Date:2018年09月14日 上午9:23:39
 * Copyright (c) 2016, hongguaninfo.com All Rights Reserved.
 *
 */
package com.hongguaninfo.hgdf.wa.dao.totalinfo;

import com.hongguaninfo.hgdf.adp.core.base.BaseDao;
import com.hongguaninfo.hgdf.wa.entity.totalinfo.WaIndustryWaterData;
import com.hongguaninfo.hgdf.wa.mapper.totalinfo.WaIndustryWaterDataMapper;
import org.springframework.stereotype.Repository;


/**
 * : wa_industry_water_data. <br />
 * dao 层 <br />
 * Date: 2018年09月14日 上午9:23:39 <br />
 * @author hhp
 * @since V1.0.0
 */
@SuppressWarnings("unchecked")
@Repository("waIndustryWaterDataDao")
public class WaIndustryWaterDataDao extends BaseDao<WaIndustryWaterData, WaIndustryWaterDataMapper, Integer> implements
		WaIndustryWaterDataMapper{

    @Override
    public String getMapperNamesapce() {
        return WaIndustryWaterDataMapper.class.getName().toString();
    }


    @Override
    public WaIndustryWaterData getIndustryDataByCompanyId(int companyId) {
        return getMapperByType(WaIndustryWaterDataMapper.class).getIndustryDataByCompanyId(companyId);
    }
}
