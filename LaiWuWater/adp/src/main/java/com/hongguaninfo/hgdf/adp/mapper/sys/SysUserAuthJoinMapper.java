package com.hongguaninfo.hgdf.adp.mapper.sys;

import com.hongguaninfo.hgdf.adp.core.base.BaseSqlMapper;

/**
 * 
 * mapper 层
 * 
 * @author:
 */

public interface SysUserAuthJoinMapper extends BaseSqlMapper {

	void deleteByAuthId(int authId);

}
