package com.hongguaninfo.hgdf.adp.dao.sys;

import org.springframework.stereotype.Repository;

import com.hongguaninfo.hgdf.adp.core.base.BaseDao;
import com.hongguaninfo.hgdf.adp.entity.sys.SysNotify;
import com.hongguaninfo.hgdf.adp.mapper.sys.SysNotifyMapper;

 /**
 * : sys_notify
 * dao 层
 * @author henry
 */

@SuppressWarnings("unchecked")
@Repository("sysNotifyDao")
public class SysNotifyDao extends BaseDao<SysNotify, SysNotifyMapper, Integer> implements
		SysNotifyMapper{

	@Override
	public String getMapperNamesapce() {
		return SysNotifyMapper.class.getName().toString();
	}


}