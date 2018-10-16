package com.hongguaninfo.hgdf.wadp.dao.sys;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hongguaninfo.hgdf.wadp.core.base.BaseDao;
import com.hongguaninfo.hgdf.wadp.entity.sys.SysAuth;
import com.hongguaninfo.hgdf.wadp.mapper.sys.SysAuthMapper;

/**
 * 系统权限表:SYS_AUTH dao 层
 * 
 * @author:
 */

@SuppressWarnings("unchecked")
@Repository("sysAuthDao")
public class SysAuthDao extends BaseDao<SysAuth, SysAuthMapper, Integer>
        implements SysAuthMapper {

    @Override
    public String getMapperNamesapce() {
        return SysAuthMapper.class.getName().toString();
    }

    public void updateByFid(SysAuth sysAuth) {
        getMapperByType(SysAuthMapper.class).updateByFid(sysAuth);

    }

    public List<SysAuth> getAuthByAuthNameAndAuthCode(SysAuth sysAuth) {
        return getMapperByType(SysAuthMapper.class)
                .getAuthByAuthNameAndAuthCode(sysAuth);
    }
    
}