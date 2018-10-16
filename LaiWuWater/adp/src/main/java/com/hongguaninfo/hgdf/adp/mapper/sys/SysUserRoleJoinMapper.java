package com.hongguaninfo.hgdf.adp.mapper.sys;

import com.hongguaninfo.hgdf.adp.core.base.BaseSqlMapper;
import com.hongguaninfo.hgdf.adp.entity.sys.SysUser;

import java.util.List;

/**
 * 
 * mapper 层
 * 
 * @author:
 */

public interface SysUserRoleJoinMapper extends BaseSqlMapper {

	public List<SysUser> getSpecUserList(String nativeSql);

	public void deleteByRoleId(int roleId);

	public List<SysUser> getUserListByRoleId(Integer roleId);

}
