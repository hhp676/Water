package com.hongguaninfo.hgdf.adp.dao.sys;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hongguaninfo.hgdf.adp.core.base.BaseDao;
import com.hongguaninfo.hgdf.adp.core.base.BasePage;
import com.hongguaninfo.hgdf.adp.entity.sys.SysUser;
import com.hongguaninfo.hgdf.adp.entity.sys.SysUserRoleJoin;
import com.hongguaninfo.hgdf.adp.mapper.sys.SysUserRoleJoinMapper;
import com.hongguaninfo.hgdf.core.utils.page.Page;

/**
 * 用户角色关联表:SYS_USER_ROLE_JOIN dao 层
 * 
 * @author:yuyanlin
 */

@SuppressWarnings("unchecked")
@Repository("sysUserRoleJoinDao")
public class SysUserRoleJoinDao extends
        BaseDao<SysUserRoleJoin, SysUserRoleJoinMapper, Integer> implements
        SysUserRoleJoinMapper {

    @Override
    public String getMapperNamesapce() {
        return SysUserRoleJoinMapper.class.getName().toString();
    }

    public Page pageQueryNotDelete(BasePage pageRequest) {
        return pageQuery(getMapperNamesapce() + ".getListWhereNotDelete",
                getMapperNamesapce() + ".getListCountWhereNotDelete",
                pageRequest);
    }

	public List<SysUser> getSpecUserList(String nativeSql) {
		return getMapperByType(SysUserRoleJoinMapper.class).getSpecUserList(nativeSql);
	}

	public void deleteByRoleId(int roleId) {
		 getMapperByType(SysUserRoleJoinMapper.class).deleteByRoleId(roleId);
		
	}
	@Override
	public List<SysUser> getUserListByRoleId(Integer roleId) {
		return getMapperByType(SysUserRoleJoinMapper.class).getUserListByRoleId(roleId);
	}
}