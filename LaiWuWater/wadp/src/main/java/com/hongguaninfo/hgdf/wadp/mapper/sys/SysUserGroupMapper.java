package com.hongguaninfo.hgdf.wadp.mapper.sys;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.hongguaninfo.hgdf.wadp.core.base.BaseSqlMapper;
import com.hongguaninfo.hgdf.wadp.entity.sys.SysUserGroup;

/**
 * 系统用户组表:SYS_USER_GROUP mapper 层
 * 
 * @author:
 */

public interface SysUserGroupMapper extends BaseSqlMapper {

    @Select("select SUG.GROUP_ID_ groupId, SUG.DEPART_ID_ departId, SUG.GROUP_NAME_ groupName, SUG.GROUP_CODE_ groupCode, SUG.DESCR_ descr, SUG.FID_ fid, SUG.IS_DELETE_ isDelete, SUG.IS_FINAL_ isFinal, SUG.CRT_TIME_ crtTime, SUG.UPD_TIME_ updTime, SUG.CRT_USERID_ crtUserid, SUG.UPD_USERID_ updUserid ,SD.DEPART_NAME_ departName from SYS_USER_GROUP SUG LEFT JOIN SYS_DEPARTMENT SD ON SUG.DEPART_ID_ = SD.DEPART_ID_ WHERE SUG.GROUP_NAME_ like '%'||#{groupName}||'%' AND SUG.GROUP_CODE_ like '%'||#{groupCode}||'%' AND SUG.IS_DELETE_= 0")
    List<SysUserGroup> getUserGroupByUserNameOrCode(SysUserGroup vo);

}
