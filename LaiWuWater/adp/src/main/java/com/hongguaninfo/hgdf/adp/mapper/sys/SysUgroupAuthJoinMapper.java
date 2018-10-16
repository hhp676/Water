package com.hongguaninfo.hgdf.adp.mapper.sys;

import com.hongguaninfo.hgdf.adp.core.base.BaseSqlMapper;

/**
 * 系统权限用户组关系表:SYS_UGROUP_AUTH_JOIN mapper 层
 * 
 * @author:
 */

public interface SysUgroupAuthJoinMapper extends BaseSqlMapper {

	void deleteByAuthId(int authId);

}
