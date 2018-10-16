package com.hongguaninfo.hgdf.wadp.dao.sys;

import org.springframework.stereotype.Repository;

import com.hongguaninfo.hgdf.wadp.core.base.BaseDao;
import com.hongguaninfo.hgdf.wadp.core.base.BasePage;
import com.hongguaninfo.hgdf.wadp.entity.sys.SysUserRoleJoin;
import com.hongguaninfo.hgdf.wadp.mapper.sys.SysUserRoleJoinMapper;
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

}