package com.hongguaninfo.hgdf.adp.dao.sys;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.hongguaninfo.hgdf.adp.core.base.BaseDao;
import com.hongguaninfo.hgdf.adp.entity.sys.SysDepartment;
import com.hongguaninfo.hgdf.adp.mapper.sys.SysDepartmentMapper;

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

	public List<SysDepartment> getSpecDepartList(String nativeSql) {
		return getMapperByType(SysDepartmentMapper.class).getSpecDepartList(nativeSql);
	}

	public List<SysDepartment> getAllList(SysDepartment dept) {
		return getMapperByType(SysDepartmentMapper.class).getAllList(dept);
	}

}