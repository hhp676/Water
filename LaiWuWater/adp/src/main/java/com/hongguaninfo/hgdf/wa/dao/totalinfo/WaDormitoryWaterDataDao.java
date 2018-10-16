/*
 * File Name:WaDormitoryWaterDataDao.java
 * Package Name:com.hongguaninfo.hgdf.bud.dao.wa
 * Date:2018年09月14日 上午9:23:39
 * Copyright (c) 2016, hongguaninfo.com All Rights Reserved.
 *
 */
package com.hongguaninfo.hgdf.wa.dao.totalinfo;

import com.hongguaninfo.hgdf.adp.core.base.BaseDao;
import com.hongguaninfo.hgdf.wa.entity.totalinfo.WaDormitoryWaterData;
import com.hongguaninfo.hgdf.wa.mapper.totalinfo.WaDormitoryWaterDataMapper;
import org.springframework.stereotype.Repository;


/**
 * : wa_dormitory_water_data. <br />
 * dao 层 <br />
 * Date: 2018年09月14日 上午9:23:39 <br />
 * @author hhp
 * @since V1.0.0
 */
@SuppressWarnings("unchecked")
@Repository("waDormitoryWaterDataDao")
public class WaDormitoryWaterDataDao extends BaseDao<WaDormitoryWaterData, WaDormitoryWaterDataMapper, Integer> implements
		WaDormitoryWaterDataMapper{

    @Override
    public String getMapperNamesapce() {
        return WaDormitoryWaterDataMapper.class.getName().toString();
    }


    @Override
    public WaDormitoryWaterData getDorDataByCompanyId(int companyId) {
        return getMapperByType(WaDormitoryWaterDataMapper.class).getDorDataByCompanyId(companyId);
    }
}
