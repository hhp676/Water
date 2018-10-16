/*
 * File Name:WaPlanYearWaterDataDao.java
 * Package Name:com.hongguaninfo.hgdf.bud.dao.wa
 * Date:2018年09月11日 下午8:49:44
 * Copyright (c) 2016, hongguaninfo.com All Rights Reserved.
 *
 */
package com.hongguaninfo.hgdf.wa.dao;

import com.hongguaninfo.hgdf.adp.core.base.BaseDao;
import com.hongguaninfo.hgdf.wa.entity.WaPlanYearWaterData;
import com.hongguaninfo.hgdf.wa.mapper.WaPlanYearWaterDataMapper;
import org.springframework.stereotype.Repository;

/**
 * : wa_plan_year_water_data. <br />
 * dao 层 <br />
 * Date: 2018年09月11日 下午8:49:44 <br />
 * @author hhp
 * @since V1.0.0
 */
@SuppressWarnings("unchecked")
@Repository("waPlanYearWaterDataDao")
public class WaPlanYearWaterDataDao extends BaseDao<WaPlanYearWaterData, WaPlanYearWaterDataMapper, Integer> implements
		WaPlanYearWaterDataMapper{

    @Override
    public String getMapperNamesapce() {
        return WaPlanYearWaterDataMapper.class.getName().toString();
    }

    @Override
    public WaPlanYearWaterData getByYearWaterData(WaPlanYearWaterData waterData) {
        return  getMapperByType(WaPlanYearWaterDataMapper.class).getByYearWaterData(waterData);
    }

    @Override
    public void deleteByWaterData(WaPlanYearWaterData waterData) {
        getMapperByType(WaPlanYearWaterDataMapper.class).deleteByWaterData(waterData);

    }


   /* @Override
    public List<WaPlanYearWaterData> getPlanWaterList(WaPlanYearWaterData waterData) {
        return getMapperByType(WaPlanYearWaterDataMapper.class).getPlanWaterList(waterData);
    }*/
}
