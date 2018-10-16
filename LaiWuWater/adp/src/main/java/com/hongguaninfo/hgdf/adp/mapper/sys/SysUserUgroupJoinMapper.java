package com.hongguaninfo.hgdf.adp.mapper.sys;

import java.util.List;

import com.hongguaninfo.hgdf.adp.core.base.BaseSqlMapper;
import com.hongguaninfo.hgdf.adp.entity.sys.SysDepartment;

 /**
  *  系统用户与用户组关系表:SYS_USER_UGROUP_JOIN
 * mapper 层
 * @author:
 */

public interface SysUserUgroupJoinMapper extends BaseSqlMapper {
	
	List<SysDepartment> getSpecDepartList(String nativeSql);

	void deleteByGroupId(int groupId);

}

