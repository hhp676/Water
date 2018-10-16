package com.hongguaninfo.hgdf.adp.mapper.sys;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hongguaninfo.hgdf.adp.core.base.BaseSqlMapper;
import com.hongguaninfo.hgdf.adp.entity.sys.SysConfig;

/**
 * 系统设置表:SYS_CONFIG mapper 层
 * 
 * @author:
 */

public interface SysConfigMapper extends BaseSqlMapper {

    @Update("UPDATE SYS_CONFIG SET CONFIG_VALUE_ = DEFAULT_VALUE_")
    void resetSysConfig();

    @Select("SELECT CONFIG_KEY_ configKey, CONFIG_VALUE_ configValue, CONFIG_DESC_ configDesc, DEFAULT_VALUE_ defaultValue FROM SYS_CONFIG WHERE CONFIG_KEY_=#{configKey}")
    SysConfig getSysConfigByKey(String configKey);
}
