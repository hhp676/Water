package com.hongguaninfo.hgdf.wadp.dao.sys;

import org.springframework.stereotype.Repository;

import com.hongguaninfo.hgdf.wadp.core.base.BaseDao;
import com.hongguaninfo.hgdf.wadp.core.base.BasePage;
import com.hongguaninfo.hgdf.wadp.entity.sys.SysRoleAuthJoin;
import com.hongguaninfo.hgdf.wadp.mapper.sys.SysRoleAuthJoinMapper;
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

}