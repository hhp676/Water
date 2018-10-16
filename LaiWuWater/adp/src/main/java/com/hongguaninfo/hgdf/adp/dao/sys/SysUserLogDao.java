package com.hongguaninfo.hgdf.adp.dao.sys;

import org.springframework.stereotype.Repository;

import com.hongguaninfo.hgdf.adp.core.base.BaseDao;
import com.hongguaninfo.hgdf.adp.entity.sys.SysUserLog;
import com.hongguaninfo.hgdf.adp.mapper.sys.SysUserLogMapper;
import com.hongguaninfo.hgdf.adp.mapper.sys.SysUserMapper;

/**
 * @author 查健
 * @Description sysUserLog dao层
 * @Date 2014-5-20
 * */

@SuppressWarnings("unchecked")
@Repository("sysUserLogDao")
public class SysUserLogDao extends BaseDao<SysUserLog, SysUserMapper, Integer>
        implements SysUserLogMapper {
    @Override
    public String getMapperNamesapce() {
        return SysUserLogMapper.class.getName().toString();
    }

}
