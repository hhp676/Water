/*
 * File Name:WaCompanyInfoMapper.java
 * Package Name:com.hongguaninfo.hgdf.bud.mapper.wa
 * Date:2018年09月10日 下午5:14:26
 * Copyright (c) 2016, hongguaninfo.com All Rights Reserved.
 *
 */
package com.hongguaninfo.hgdf.wa.mapper;

import com.hongguaninfo.hgdf.adp.core.base.BaseSqlMapper;
import com.hongguaninfo.hgdf.wa.entity.WaCompanyInfo;

/**
 * : wa_company_info。 <br />
 * mapper 层 <br />
 * Date: 2018年09月10日 下午5:14:26 <br />
 *
 * @author  hhp
 * @since V1.0.0
 */
public interface WaCompanyInfoMapper extends BaseSqlMapper {

    WaCompanyInfo getEntityByCode(WaCompanyInfo entity);
}
