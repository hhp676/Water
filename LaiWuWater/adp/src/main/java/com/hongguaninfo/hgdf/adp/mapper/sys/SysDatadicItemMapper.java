package com.hongguaninfo.hgdf.adp.mapper.sys;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.hongguaninfo.hgdf.adp.core.base.BaseSqlMapper;
import com.hongguaninfo.hgdf.adp.entity.sys.SysDatadicItem;

/**
 * : SYS_DATADIC_ITEM mapper 层
 * 
 * @author zhengyangjun
 */

public interface SysDatadicItemMapper extends BaseSqlMapper {
	List getListByGroupIdItemCode(SysDatadicItem sysDatadicItem);

}
