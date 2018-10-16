package com.hongguaninfo.hgdf.adp.dao.sys;

import org.springframework.stereotype.Repository;

import com.hongguaninfo.hgdf.adp.core.base.BaseDao;
import com.hongguaninfo.hgdf.adp.entity.sys.SysNotifyTemplate;
import com.hongguaninfo.hgdf.adp.mapper.sys.SysNotifyTemplateMapper;

 /**
 * : sys_notify_template
 * dao 层
 * @author henry
 */

@SuppressWarnings("unchecked")
@Repository("sysNotifyTemplateDao")
public class SysNotifyTemplateDao extends BaseDao<SysNotifyTemplate, SysNotifyTemplateMapper, Integer> implements
		SysNotifyTemplateMapper{

	@Override
	public String getMapperNamesapce() {
		return SysNotifyTemplateMapper.class.getName().toString();
	}


}