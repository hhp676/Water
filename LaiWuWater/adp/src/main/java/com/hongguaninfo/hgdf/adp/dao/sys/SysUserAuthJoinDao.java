package com.hongguaninfo.hgdf.adp.dao.sys;

import org.springframework.stereotype.Repository;

import com.hongguaninfo.hgdf.adp.core.base.BaseDao;
import com.hongguaninfo.hgdf.adp.core.base.BasePage;
import com.hongguaninfo.hgdf.adp.entity.sys.SysUserAuthJoin;
import com.hongguaninfo.hgdf.adp.mapper.sys.SysUserAuthJoinMapper;
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

	public void deleteByAuthId(int authId) {
		getMapperByType(SysUserAuthJoinMapper.class).deleteByAuthId(authId);
	}

}