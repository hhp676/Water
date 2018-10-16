package com.hongguaninfo.hgdf.adp.dao.sys;

import org.springframework.stereotype.Repository;

import com.hongguaninfo.hgdf.adp.core.base.BaseDao;
import com.hongguaninfo.hgdf.adp.entity.sys.SysRoleUgroupJoin;
import com.hongguaninfo.hgdf.adp.mapper.sys.SysRoleUgroupJoinMapper;

/**
 * 系统角色用户组关系表:SYS_ROLE_UGROUP_JOIN dao 层
 * 
 * @author:
 */

@SuppressWarnings("unchecked")
@Repository("sysRoleUgroupJoinDao")
public class SysRoleUgroupJoinDao extends
        BaseDao<SysRoleUgroupJoin, SysRoleUgroupJoinMapper, Integer> implements
        SysRoleUgroupJoinMapper {

    @Override
    public String getMapperNamesapce() {
        return SysRoleUgroupJoinMapper.class.getName().toString();
    }

}