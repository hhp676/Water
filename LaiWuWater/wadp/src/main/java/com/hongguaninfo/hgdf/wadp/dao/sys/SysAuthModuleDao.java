package com.hongguaninfo.hgdf.wadp.dao.sys;

import org.springframework.stereotype.Repository;

import com.hongguaninfo.hgdf.wadp.core.base.BaseDao;
import com.hongguaninfo.hgdf.wadp.entity.sys.SysAuthModule;
import com.hongguaninfo.hgdf.wadp.mapper.sys.SysAuthModuleMapper;

/**
 * 系统模块权限表:SYS_AUTH_MODULE dao 层
 * 
 * @author:
 */

@SuppressWarnings("unchecked")
@Repository("sysAuthModuleDao")
public class SysAuthModuleDao extends
        BaseDao<SysAuthModule, SysAuthModuleMapper, Integer> implements
        SysAuthModuleMapper {

    @Override
    public String getMapperNamesapce() {
        return SysAuthModuleMapper.class.getName().toString();
    }

}