/*
 * File Name:SysMetaMethodMapper.java
 * Package Name:com.hongguaninfo.hgdf.bud.mapper.sys
 * Date:2018年01月03日 下午2:16:08
 * Copyright (c) 2016, hongguaninfo.com All Rights Reserved.
 *
 */
package com.hongguaninfo.hgdf.adp.mapper.sys;

import java.util.List;

import com.hongguaninfo.hgdf.adp.core.base.BaseSqlMapper;
import com.hongguaninfo.hgdf.adp.entity.sys.SysMetaMethod;

/**
 * 系统元方法表: sys_meta_method。 <br />
 * mapper 层 <br />
 * Date: 2018年01月03日 下午2:16:08 <br />
 *
 * @author  zhxl
 * @since V1.0.0
 */
public interface SysMetaMethodMapper extends BaseSqlMapper {
    /**
     * 把所有数据设置为不再用。
     */
    void setAllInvalid();
    
    /**
     * 根据方法签名查询。
     * （根据类名、方法名、参数类型，精确查找）
     * @param filter
     * @return
     */
    List<SysMetaMethod> getListByMethodSignature(SysMetaMethod filter);
}
