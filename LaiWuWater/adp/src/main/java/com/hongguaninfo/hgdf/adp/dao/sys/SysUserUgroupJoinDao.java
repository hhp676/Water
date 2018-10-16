package com.hongguaninfo.hgdf.adp.dao.sys;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hongguaninfo.hgdf.adp.core.base.BaseDao;
import com.hongguaninfo.hgdf.adp.entity.sys.SysDepartment;
import com.hongguaninfo.hgdf.adp.entity.sys.SysUserUgroupJoin;
import com.hongguaninfo.hgdf.adp.mapper.sys.SysUserUgroupJoinMapper;
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

	@Override
	public List<SysDepartment> getSpecDepartList(String nativeSql) {
		return getMapperByType(SysUserUgroupJoinMapper.class).getSpecDepartList(nativeSql);
	}

	public void deleteByGroupId(int groupId) {
		getMapperByType(SysUserUgroupJoinMapper.class).deleteByGroupId(groupId);
	}


}