package com.hongguaninfo.hgdf.wadp.dao.sys;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.hongguaninfo.hgdf.wadp.core.base.BaseDao;
import com.hongguaninfo.hgdf.wadp.entity.sys.SysDepartment;
import com.hongguaninfo.hgdf.wadp.mapper.sys.SysDepartmentMapper;

/**
 * 组织架构(部门)表:SYS_DEPARTMENT dao 层
 * 
 * @author:
 */

@SuppressWarnings("unchecked")
@Repository("sysDepartmentDao")
public class SysDepartmentDao extends
        BaseDao<SysDepartment, SysDepartmentMapper, Integer> implements
        SysDepartmentMapper {

    public String getMapperNamesapce() {
        return SysDepartmentMapper.class.getName().toString();
    }

    public String getFnodeNameByFid(BigDecimal fid) {
        return getMapperByType(SysDepartmentMapper.class)
                .getFnodeNameByFid(fid);
    }

    public List<SysDepartment> getDepartmentByDepartmentName(SysDepartment vo) {
        return getMapperByType(SysDepartmentMapper.class)
                .getDepartmentByDepartmentName(vo);
    }

}