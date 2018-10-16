/*
 * File Name:WaMonthWaterDataDao.java
 * Package Name:com.hongguaninfo.hgdf.bud.dao.wa
 * Date:2018年09月11日 下午8:49:44
 * Copyright (c) 2016, hongguaninfo.com All Rights Reserved.
 *
 */
package com.hongguaninfo.hgdf.wa.dao;

import com.hongguaninfo.hgdf.adp.core.base.BaseDao;
import com.hongguaninfo.hgdf.adp.core.base.BasePage;
import com.hongguaninfo.hgdf.core.utils.page.Page;
import com.hongguaninfo.hgdf.wa.entity.WaMonthWaterData;
import com.hongguaninfo.hgdf.wa.mapper.WaMonthWaterDataMapper;
import org.springframework.stereotype.Repository;


/**
 * : wa_month_water_data. <br />
 * dao 层 <br />
 * Date: 2018年09月11日 下午8:49:44 <br />
 * @author hhp
 * @since V1.0.0
 */
@SuppressWarnings("unchecked")
@Repository("waMonthWaterDataDao")
public class WaMonthWaterDataDao extends BaseDao<WaMonthWaterData, WaMonthWaterDataMapper, Integer> implements
		WaMonthWaterDataMapper{

    @Override
    public String getMapperNamesapce() {
        return WaMonthWaterDataMapper.class.getName().toString();
    }

    public Page pagePlanDataQuery(BasePage pageRequest) {
        return pageQuery(getMapperNamesapce() + ".getPlanList",
                getMapperNamesapce() + ".getPlanListCount", pageRequest);
    }

    public Page pageActDataQuery(BasePage pageRequest) {
        return pageQuery(getMapperNamesapce() + ".getActList",
                getMapperNamesapce() + ".getActListCount", pageRequest);
    }

    @Override
    public WaMonthWaterData getWaListByEntity(WaMonthWaterData entity) {
        return getMapperByType(WaMonthWaterDataMapper.class).getWaListByEntity(entity);
    }
}
