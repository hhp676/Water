package com.hongguaninfo.hgdf.adp.dao.sys;

import org.springframework.stereotype.Repository;

import com.hongguaninfo.hgdf.adp.core.base.BaseDao;
import com.hongguaninfo.hgdf.adp.entity.sys.SysDatadicGroup;
import com.hongguaninfo.hgdf.adp.mapper.sys.SysDatadicGroupMapper;

/**
 * : SYS_DATADIC_GROUP dao 层
 * 
 * @author zhengyangjun
 */

@SuppressWarnings("unchecked")
@Repository("sysDatadicGroupDao")
public class SysDatadicGroupDao extends
        BaseDao<SysDatadicGroup, SysDatadicGroupMapper, Integer> implements
        SysDatadicGroupMapper {

    @Override
    public String getMapperNamesapce() {
        return SysDatadicGroupMapper.class.getName().toString();
    }

}