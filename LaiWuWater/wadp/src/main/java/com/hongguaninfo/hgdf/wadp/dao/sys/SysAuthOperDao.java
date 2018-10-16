package com.hongguaninfo.hgdf.wadp.dao.sys;

import org.springframework.stereotype.Repository;

import com.hongguaninfo.hgdf.wadp.core.base.BaseDao;
import com.hongguaninfo.hgdf.wadp.entity.sys.SysAuthOper;
import com.hongguaninfo.hgdf.wadp.mapper.sys.SysAuthOperMapper;

/**
 * 系统操作权限表:SYS_AUTH_OPER dao 层
 * 
 * @author:
 */

@SuppressWarnings("unchecked")
@Repository("sysAuthOperDao")
public class SysAuthOperDao extends
        BaseDao<SysAuthOper, SysAuthOperMapper, Integer> implements
        SysAuthOperMapper {

    @Override
    public String getMapperNamesapce() {
        return SysAuthOperMapper.class.getName().toString();
    }

}