package com.hongguaninfo.hgdf.adp.dao.sys;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.hongguaninfo.hgdf.adp.core.base.BaseDao;
import com.hongguaninfo.hgdf.adp.entity.sys.SysUserGroup;
import com.hongguaninfo.hgdf.adp.mapper.sys.SysUserGroupMapper;

/**
 * 系统用户组表:SYS_USER_GROUP dao 层
 * 
 * @author:
 */

@SuppressWarnings("unchecked")
@Repository("sysUserGroupDao")
public class SysUserGroupDao extends
        BaseDao<SysUserGroup, SysUserGroupMapper, Integer> implements
        SysUserGroupMapper {

    @Override
    public String getMapperNamesapce() {
        return SysUserGroupMapper.class.getName().toString();
    }

    public List<SysUserGroup> getUserGroupByUserNameOrCode(SysUserGroup vo) {
        return getMapperByType(SysUserGroupMapper.class)
                .getUserGroupByUserNameOrCode(vo);
    }

}