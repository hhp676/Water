/*
 * File Name:WaCommFacilitiesWaterDataMapper.java
 * Package Name:com.hongguaninfo.hgdf.bud.mapper.wa
 * Date:2018年09月14日 上午9:23:39
 * Copyright (c) 2016, hongguaninfo.com All Rights Reserved.
 *
 */
package com.hongguaninfo.hgdf.wa.mapper.totalinfo;

import com.hongguaninfo.hgdf.adp.core.base.BaseSqlMapper;
import com.hongguaninfo.hgdf.wa.entity.totalinfo.WaCommFacilitiesWaterData;

/**
 * : wa_comm_facilities_water_data。 <br />
 * mapper 层 <br />
 * Date: 2018年09月14日 上午9:23:39 <br />
 *
 * @author  hhp
 * @since V1.0.0
 */
public interface WaCommFacilitiesWaterDataMapper extends BaseSqlMapper {

    WaCommFacilitiesWaterData getWaCommFacDataByCompanyId(int companyId);

}
