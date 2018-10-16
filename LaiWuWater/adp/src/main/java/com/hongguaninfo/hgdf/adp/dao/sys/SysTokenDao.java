package com.hongguaninfo.hgdf.adp.dao.sys;

import org.springframework.stereotype.Repository;

import com.hongguaninfo.hgdf.adp.core.base.BaseDao;
import com.hongguaninfo.hgdf.adp.entity.sys.SysToken;
import com.hongguaninfo.hgdf.adp.entity.sys.SysUser;
import com.hongguaninfo.hgdf.adp.mapper.sys.SysTokenMapper;
import com.hongguaninfo.hgdf.adp.mapper.sys.SysUserMapper;

 /**
 * : sys_token
 * dao 层
 * @author henry
 */

@SuppressWarnings("unchecked")
@Repository("sysTokenDao")
public class SysTokenDao extends BaseDao<SysToken, SysTokenMapper, Integer> implements
		SysTokenMapper{

	@Override
	public String getMapperNamesapce() {
		return SysTokenMapper.class.getName().toString();
	}

    @Override
    public Integer countValidToken(SysToken sysToken) {
        return getMapperByType(SysTokenMapper.class).countValidToken(sysToken);
    }


}