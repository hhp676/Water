package com.hongguaninfo.hgdf.adp.mapper.sys;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.hongguaninfo.hgdf.adp.core.base.BaseSqlMapper;
import com.hongguaninfo.hgdf.adp.entity.sys.SysDepartment;

/**
 * 组织架构(部门)表:SYS_DEPARTMENT mapper 层
 * 
 * @author:
 */

public interface SysDepartmentMapper extends BaseSqlMapper {

    @Select("select distinct(t1.depart_name_) departName from sys_department t left join sys_department t1 on t.fid_ = t1.depart_id_ where t.fid_ = #{fid}")
    String getFnodeNameByFid(java.math.BigDecimal fid);

    @Select("select SD.DEPART_ID_ departId, SD.DEPART_NAME_ departName, SD.DEPART_FULLNAME_ departFullname, SD.DEPART_CODE_ departCode, SD.ENGNAME_ engname, SD.FID_ fid, SD.IS_DELETE_ isDelete, SD.IS_FINAL_ isFinal, SD.CRT_TIME_ crtTime, SD.UPD_TIME_ updTime, SD.CRT_USERID_ crtUserid, SD.UPD_USERID_ updUserid FROM SYS_DEPARTMENT SD WHERE SD.DEPART_NAME_ LIKE '%'||#{departName}||'%' AND SD.DEPART_CODE_ LIKE '%'||#{departCode}||'%' AND SD.IS_DELETE_ = 0")
    List<SysDepartment> getDepartmentByDepartmentName(SysDepartment vo);
    
    List<SysDepartment> getSpecDepartList(String nativeSql);

	List<SysDepartment> getAllList(SysDepartment dept);

}
