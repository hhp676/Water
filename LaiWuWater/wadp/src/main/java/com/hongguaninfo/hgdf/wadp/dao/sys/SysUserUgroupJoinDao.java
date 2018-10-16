package com.hongguaninfo.hgdf.wadp.dao.sys;

import org.springframework.stereotype.Repository;

import com.hongguaninfo.hgdf.wadp.core.base.BaseDao;
import com.hongguaninfo.hgdf.wadp.entity.sys.SysUserUgroupJoin;
import com.hongguaninfo.hgdf.wadp.mapper.sys.SysUserUgroupJoinMapper;
 /**
 *  系统用户与用户组关系表:SYS_USER_UGROUP_JOIN
 * dao 层
 * @author:
 */

@SuppressWarnings("unchecked")
@Repository("sysUserUgroupJoinDao")
public class SysUserUgroupJoinDao extends BaseDao<SysUserUgroupJoin, SysUserUgroupJoinMapper, Integer> implements
		SysUserUgroupJoinMapper{

	@Override
	public String getMapperNamesapce() {
		return SysUserUgroupJoinMapper.class.getName().toString();
	}


}