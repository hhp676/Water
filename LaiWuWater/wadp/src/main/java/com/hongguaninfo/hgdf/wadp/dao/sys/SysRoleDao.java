package com.hongguaninfo.hgdf.wadp.dao.sys;

import org.springframework.stereotype.Repository;

import com.hongguaninfo.hgdf.wadp.core.base.BaseDao;
import com.hongguaninfo.hgdf.wadp.entity.sys.SysRole;
import com.hongguaninfo.hgdf.wadp.mapper.sys.SysRoleMapper;

/**
 * 系统角色表:SYS_ROLE dao 层
 * 
 * @author:
 */

@SuppressWarnings("unchecked")
@Repository("sysRoleDao")
public class SysRoleDao extends BaseDao<SysRole, SysRoleMapper, Integer>
        implements SysRoleMapper {

    @Override
    public String getMapperNamesapce() {
        return SysRoleMapper.class.getName().toString();
    }

}