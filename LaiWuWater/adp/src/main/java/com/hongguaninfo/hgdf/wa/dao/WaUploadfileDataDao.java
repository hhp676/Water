/*
 * File Name:WaUploadfileDataDao.java
 * Package Name:com.hongguaninfo.hgdf.bud.dao.wa
 * Date:2018年10月12日 下午4:17:20
 * Copyright (c) 2016, hongguaninfo.com All Rights Reserved.
 *
 */
package com.hongguaninfo.hgdf.wa.dao;

import com.hongguaninfo.hgdf.adp.core.base.BaseDao;
import com.hongguaninfo.hgdf.wa.entity.WaUploadfileData;
import com.hongguaninfo.hgdf.wa.mapper.WaUploadfileDataMapper;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * : wa_uploadfile_data. <br />
 * dao 层 <br />
 * Date: 2018年10月12日 下午4:17:20 <br />
 * @author hhp
 * @since V1.0.0
 */
@SuppressWarnings("unchecked")
@Repository("waUploadfileDataDao")
public class WaUploadfileDataDao extends BaseDao<WaUploadfileData, WaUploadfileDataMapper, Integer> implements
		WaUploadfileDataMapper{

    @Override
    public String getMapperNamesapce() {
        return WaUploadfileDataMapper.class.getName().toString();
    }

    @Override
    public List<WaUploadfileData> getFileDataList(WaUploadfileData waUploadfileData) {
       return getMapperByType(WaUploadfileDataMapper.class).getFileDataList(waUploadfileData);
    }
}
