package com.hongguaninfo.hgdf.adp.dao.sys;

import org.springframework.stereotype.Repository;

import com.hongguaninfo.hgdf.adp.core.base.BaseDao;
import com.hongguaninfo.hgdf.adp.entity.sys.SysConfig;
import com.hongguaninfo.hgdf.adp.mapper.sys.SysConfigMapper;

/**
 * 系统设置表:SYS_CONFIG dao 层
 * 
 * @author:
 */

@SuppressWarnings("unchecked")
@Repository("sysConfigDao")
public class SysConfigDao extends BaseDao<SysConfig, SysConfigMapper, Integer>
        implements SysConfigMapper {

    @Override
    public String getMapperNamesapce() {
        return SysConfigMapper.class.getName().toString();
    }

    public void resetSysConfig() {
        getMapperByType(SysConfigMapper.class).resetSysConfig();

    }

    public SysConfig getSysConfigByKey(String configKey) {
        return getMapperByType(SysConfigMapper.class).getSysConfigByKey(
                configKey);
    }
}