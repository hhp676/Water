package com.hongguaninfo.hgdf.wadp.dao.sys;

import org.springframework.stereotype.Repository;

import com.hongguaninfo.hgdf.wadp.core.base.BaseDao;
import com.hongguaninfo.hgdf.wadp.entity.sys.SysDatadicItem;
import com.hongguaninfo.hgdf.wadp.mapper.sys.SysDatadicItemMapper;

/**
 * : SYS_DATADIC_ITEM dao 层
 * 
 * @author zhengyangjun
 */

@SuppressWarnings("unchecked")
@Repository("sysDatadicItemDao")
public class SysDatadicItemDao extends
        BaseDao<SysDatadicItem, SysDatadicItemMapper, Integer> implements
        SysDatadicItemMapper {

    @Override
    public String getMapperNamesapce() {
        return SysDatadicItemMapper.class.getName().toString();
    }

}