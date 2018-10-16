/*
 * File Name:WaCommFacilitiesWaterDataDao.java
 * Package Name:com.hongguaninfo.hgdf.bud.dao.wa
 * Date:2018年09月14日 上午9:23:39
 * Copyright (c) 2016, hongguaninfo.com All Rights Reserved.
 *
 */
package com.hongguaninfo.hgdf.wa.dao.totalinfo;

import com.hongguaninfo.hgdf.adp.core.base.BaseDao;
import com.hongguaninfo.hgdf.wa.entity.totalinfo.WaCommFacilitiesWaterData;
import com.hongguaninfo.hgdf.wa.mapper.totalinfo.WaCommFacilitiesWaterDataMapper;
import org.springframework.stereotype.Repository;

/**
 * : wa_comm_facilities_water_data. <br />
 * dao 层 <br />
 * Date: 2018年09月14日 上午9:23:39 <br />
 * @author hhp
 * @since V1.0.0
 */
@SuppressWarnings("unchecked")
@Repository("waCommFacilitiesWaterDataDao")
public class WaCommFacilitiesWaterDataDao extends BaseDao<WaCommFacilitiesWaterData, WaCommFacilitiesWaterDataMapper, Integer> implements
		WaCommFacilitiesWaterDataMapper{

    @Override
    public String getMapperNamesapce() {
        return WaCommFacilitiesWaterDataMapper.class.getName().toString();
    }


    @Override
    public WaCommFacilitiesWaterData getWaCommFacDataByCompanyId(int companyId) {
       return getMapperByType(WaCommFacilitiesWaterDataMapper.class).getWaCommFacDataByCompanyId(companyId);
    }
}
