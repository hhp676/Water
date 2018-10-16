/*
 * File Name:WaUploadfileDataMapper.java
 * Package Name:com.hongguaninfo.hgdf.bud.mapper.wa
 * Date:2018年10月12日 下午4:17:20
 * Copyright (c) 2016, hongguaninfo.com All Rights Reserved.
 *
 */
package com.hongguaninfo.hgdf.wa.mapper;

import com.hongguaninfo.hgdf.adp.core.base.BaseSqlMapper;
import com.hongguaninfo.hgdf.wa.entity.WaUploadfileData;

import java.util.List;

/**
 * : wa_uploadfile_data。 <br />
 * mapper 层 <br />
 * Date: 2018年10月12日 下午4:17:20 <br />
 *
 * @author  hhp
 * @since V1.0.0
 */
public interface WaUploadfileDataMapper extends BaseSqlMapper {

    public List<WaUploadfileData> getFileDataList(WaUploadfileData waUploadfileData);
}
