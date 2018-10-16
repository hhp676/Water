package com.hongguaninfo.hgdf.adp.dao.sys;

import org.springframework.stereotype.Repository;

import com.hongguaninfo.hgdf.adp.core.base.BaseDao;
import com.hongguaninfo.hgdf.adp.entity.sys.SysUgroupAuthJoin;
import com.hongguaninfo.hgdf.adp.mapper.sys.SysUgroupAuthJoinMapper;

/**
 * 系统权限用户组关系表:SYS_UGROUP_AUTH_JOIN dao 层
 * 
 * @author:
 */

@SuppressWarnings("unchecked")
@Repository("sysUgroupAuthJoinDao")
public class SysUgroupAuthJoinDao extends
        BaseDao<SysUgroupAuthJoin, SysUgroupAuthJoinMapper, Integer> implements
        SysUgroupAuthJoinMapper {

    @Override
    public String getMapperNamesapce() {
        return SysUgroupAuthJoinMapper.class.getName().toString();
    }

	public void deleteByAuthId(int authId) {
		 getMapperByType(SysUgroupAuthJoinMapper.class).deleteByAuthId(authId);
	}

}