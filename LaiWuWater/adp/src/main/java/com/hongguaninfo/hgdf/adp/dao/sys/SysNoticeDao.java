package com.hongguaninfo.hgdf.adp.dao.sys;

import org.springframework.stereotype.Repository;

import com.hongguaninfo.hgdf.adp.core.base.BaseDao;
import com.hongguaninfo.hgdf.adp.entity.sys.SysNotice;
import com.hongguaninfo.hgdf.adp.mapper.sys.SysNoticeMapper;

 /**
 * : sys_notice
 * dao 层
 * @author henry
 */

@SuppressWarnings("unchecked")
@Repository("sysNoticeDao")
public class SysNoticeDao extends BaseDao<SysNotice, SysNoticeMapper, Integer> implements
		SysNoticeMapper{

	@Override
	public String getMapperNamesapce() {
		return SysNoticeMapper.class.getName().toString();
	}


}