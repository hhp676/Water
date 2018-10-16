package com.hongguaninfo.hgdf.adp.mapper.sys;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.hongguaninfo.hgdf.adp.core.base.BaseSqlMapper;
import com.hongguaninfo.hgdf.adp.entity.sys.SysAuth;

/**
 * 系统权限表:SYS_AUTH mapper 层
 * 
 * @author:
 */

public interface SysAuthMapper extends BaseSqlMapper {
    void updateByFid(SysAuth sysAuth);

    @Select("select SA.AUTH_ID_ authId, SA.AUTH_TYPE_ authType, SA.AUTH_CODE_ authCode, SA.AUTH_NAME_ authName, SA.AUTH_ENNAME_ authEnname, SA.AUTH_URI_ authUri, SA.AUTH_ICON_ authIcon, SA.ORDER_ID_ orderId, SA.DESCR_ descr,SA.IS_DELETE_ isDelete, SA.IS_FINAL_ isFinal,SA.CRT_TIME_ crtTime, SA.UPD_TIME_ updTime,SA.CRT_USERID_ crtUserid, SA.UPD_USERID_ updUserid,SA.OPER_ID_ operId, SA.FID_ fid,SA.FIDS_ fids,SA.LEVEL_ID_ levelId, SA.IS_VISIBLE_ isVisible from SYS_AUTH SA WHERE SA.IS_DELETE_=0 AND SA.AUTH_NAME_ LIKE '%'||#{authName}||'%' AND SA.AUTH_CODE_ LIKE '%'||#{authCode}||'%' ORDER BY SA.AUTH_TYPE_")
    List<SysAuth> getAuthByAuthNameAndAuthCode(SysAuth sysAuth);
}
