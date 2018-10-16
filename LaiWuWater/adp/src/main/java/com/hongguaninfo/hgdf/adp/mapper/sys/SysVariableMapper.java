package com.hongguaninfo.hgdf.adp.mapper.sys;

import java.util.List;

import com.hongguaninfo.hgdf.adp.core.base.BaseSqlMapper;

/**
 * 系统变量表:SYS_VARIABLE mapper 层
 * 
 * @author:
 */

public interface SysVariableMapper extends BaseSqlMapper {

	List getListByName(String distinctName);

}
