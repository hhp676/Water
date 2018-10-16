package com.hongguaninfo.hgdf.adp.dao.sys;

import org.springframework.stereotype.Repository;

import com.hongguaninfo.hgdf.adp.core.base.BaseDao;
import com.hongguaninfo.hgdf.adp.core.base.BasePage;
import com.hongguaninfo.hgdf.adp.entity.sys.SysRoleAuthJoin;
import com.hongguaninfo.hgdf.adp.mapper.sys.SysRoleAuthJoinMapper;
import com.hongguaninfo.hgdf.core.utils.page.Page;

/**
 * 角色权限关联表:SYS_ROLE_AUTH_JOIN dao 层
 * 
 * @author:yuyanlin
 */

@SuppressWarnings("unchecked")
@Repository("sysRoleAuthJoinDao")
public class SysRoleAuthJoinDao extends
        BaseDao<SysRoleAuthJoin, SysRoleAuthJoinMapper, Integer> implements
        SysRoleAuthJoinMapper {

    @Override
    public String getMapperNamesapce() {
        return SysRoleAuthJoinMapper.class.getName().toString();
    }

    public Page pageQueryNotDelete(BasePage pageRequest) {
        return pageQuery(getMapperNamesapce() + ".getListWhereNotDelete",
                getMapperNamesapce() + ".getListCountWhereNotDelete",
                pageRequest);
    }

	public void deleteByAuthId(int authId) {
		 getMapperByType(SysRoleAuthJoinMapper.class).deleteByAuthId(authId);
		
	}

}