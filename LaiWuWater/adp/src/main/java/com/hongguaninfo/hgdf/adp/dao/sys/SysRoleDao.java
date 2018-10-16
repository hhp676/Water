package com.hongguaninfo.hgdf.adp.dao.sys;

import org.springframework.stereotype.Repository;

import com.hongguaninfo.hgdf.adp.core.base.BaseDao;
import com.hongguaninfo.hgdf.adp.entity.sys.SysRole;
import com.hongguaninfo.hgdf.adp.mapper.sys.SysRoleMapper;

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