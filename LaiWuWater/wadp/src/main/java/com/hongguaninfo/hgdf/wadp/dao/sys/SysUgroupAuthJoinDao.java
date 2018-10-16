package com.hongguaninfo.hgdf.wadp.dao.sys;

import org.springframework.stereotype.Repository;

import com.hongguaninfo.hgdf.wadp.core.base.BaseDao;
import com.hongguaninfo.hgdf.wadp.entity.sys.SysUgroupAuthJoin;
import com.hongguaninfo.hgdf.wadp.mapper.sys.SysUgroupAuthJoinMapper;

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

}