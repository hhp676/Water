package com.hongguaninfo.hgdf.wadp.dao.sys;

import org.springframework.stereotype.Repository;

import com.hongguaninfo.hgdf.wadp.core.base.BaseDao;
import com.hongguaninfo.hgdf.wadp.core.base.BasePage;
import com.hongguaninfo.hgdf.wadp.entity.sys.SysVariable;
import com.hongguaninfo.hgdf.wadp.mapper.sys.SysVariableMapper;
import com.hongguaninfo.hgdf.core.utils.page.Page;

/**
 * 系统变量表:SYS_VARIABLE dao 层
 * 
 * @author:
 */

@SuppressWarnings("unchecked")
@Repository("sysVariableDao")
public class SysVariableDao extends
        BaseDao<SysVariable, SysVariableMapper, Integer> implements
        SysVariableMapper {

    @Override
    public String getMapperNamesapce() {
        return SysVariableMapper.class.getName().toString();
    }

    public Page pageQueryNotDelete(BasePage pageRequest) {
        return pageQuery(getMapperNamesapce() + ".getListWhereNotDelete",
                getMapperNamesapce() + ".getListCountWhereNotDelete",
                pageRequest);
    }

}