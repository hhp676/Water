package com.hongguaninfo.hgdf.wadp.dao.sys;

import org.springframework.stereotype.Repository;

import com.hongguaninfo.hgdf.wadp.core.base.BaseDao;
import com.hongguaninfo.hgdf.wadp.core.base.BasePage;
import com.hongguaninfo.hgdf.wadp.entity.sys.SysUserAuthJoin;
import com.hongguaninfo.hgdf.wadp.mapper.sys.SysUserAuthJoinMapper;
import com.hongguaninfo.hgdf.core.utils.page.Page;

/**
 * 用户权限关联表:SYS_USER_AUTH_JOIN dao 层
 * 
 * @author:yuyanlin
 */

@SuppressWarnings("unchecked")
@Repository("sysUserAuthJoinDao")
public class SysUserAuthJoinDao extends
        BaseDao<SysUserAuthJoin, SysUserAuthJoinMapper, Integer> implements
        SysUserAuthJoinMapper {

    @Override
    public String getMapperNamesapce() {
        return SysUserAuthJoinMapper.class.getName().toString();
    }

    public Page pageQueryNotDelete(BasePage pageRequest) {
        return pageQuery(getMapperNamesapce() + ".getListWhereNotDelete",
                getMapperNamesapce() + ".getListCountWhereNotDelete",
                pageRequest);
    }

}