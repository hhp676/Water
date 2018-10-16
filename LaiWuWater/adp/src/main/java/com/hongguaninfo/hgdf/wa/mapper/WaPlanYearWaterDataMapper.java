/*
 * File Name:WaPlanYearWaterDataMapper.java
 * Package Name:com.hongguaninfo.hgdf.bud.mapper.wa
 * Date:2018年09月11日 下午8:49:44
 * Copyright (c) 2016, hongguaninfo.com All Rights Reserved.
 *
 */
package com.hongguaninfo.hgdf.wa.mapper;

import com.hongguaninfo.hgdf.adp.core.base.BaseSqlMapper;
import com.hongguaninfo.hgdf.wa.entity.WaPlanYearWaterData;

/**
 * : wa_plan_year_water_data。 <br />
 * mapper 层 <br />
 * Date: 2018年09月11日 下午8:49:44 <br />
 *
 * @author  hhp
 * @since V1.0.0
 */
public interface WaPlanYearWaterDataMapper extends BaseSqlMapper {

//    List<WaPlanYearWaterData> getPlanWaterList(WaPlanYearWaterData waterData);
    WaPlanYearWaterData getByYearWaterData(WaPlanYearWaterData waterData);

    void deleteByWaterData(WaPlanYearWaterData waterData);
}
