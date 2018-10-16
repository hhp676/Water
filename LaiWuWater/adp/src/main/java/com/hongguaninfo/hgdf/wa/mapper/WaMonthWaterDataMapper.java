/*
 * File Name:WaMonthWaterDataMapper.java
 * Package Name:com.hongguaninfo.hgdf.bud.mapper.wa
 * Date:2018年09月11日 下午8:49:44
 * Copyright (c) 2016, hongguaninfo.com All Rights Reserved.
 *
 */
package com.hongguaninfo.hgdf.wa.mapper;

import com.hongguaninfo.hgdf.adp.core.base.BaseSqlMapper;
import com.hongguaninfo.hgdf.wa.entity.WaMonthWaterData;

/**
 * : wa_month_water_data。 <br />
 * mapper 层 <br />
 * Date: 2018年09月11日 下午8:49:44 <br />
 *
 * @author  hhp
 * @since V1.0.0
 */
public interface WaMonthWaterDataMapper extends BaseSqlMapper {
    WaMonthWaterData getWaListByEntity(WaMonthWaterData entity);
}
