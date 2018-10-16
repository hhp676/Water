/*
 * File Name:WaDormitoryWaterDataMapper.java
 * Package Name:com.hongguaninfo.hgdf.bud.mapper.wa
 * Date:2018年09月14日 上午9:23:39
 * Copyright (c) 2016, hongguaninfo.com All Rights Reserved.
 *
 */
package com.hongguaninfo.hgdf.wa.mapper.totalinfo;

import com.hongguaninfo.hgdf.adp.core.base.BaseSqlMapper;
import com.hongguaninfo.hgdf.wa.entity.totalinfo.WaDormitoryWaterData;

/**
 * : wa_dormitory_water_data。 <br />
 * mapper 层 <br />
 * Date: 2018年09月14日 上午9:23:39 <br />
 *
 * @author  hhp
 * @since V1.0.0
 */
public interface WaDormitoryWaterDataMapper extends BaseSqlMapper {

    WaDormitoryWaterData getDorDataByCompanyId(int companyId);
}
