package com.hongguaninfo.hgdf.adp.dao.sys;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hongguaninfo.hgdf.adp.core.base.BaseDao;
import com.hongguaninfo.hgdf.adp.entity.sys.SysDatadicItem;
import com.hongguaninfo.hgdf.adp.mapper.sys.SysDatadicItemMapper;
import com.hongguaninfo.hgdf.adp.mapper.sys.SysVariableMapper;

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

	public List getListByGroupIdItemCode(SysDatadicItem sysDatadicItem) {
		 return getMapperByType(SysDatadicItemMapper.class).getListByGroupIdItemCode(sysDatadicItem);
	}

}