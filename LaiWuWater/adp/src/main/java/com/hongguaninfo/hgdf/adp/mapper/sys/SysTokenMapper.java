package com.hongguaninfo.hgdf.adp.mapper.sys;

import org.apache.ibatis.annotations.Select;

import com.hongguaninfo.hgdf.adp.core.base.BaseSqlMapper;
import com.hongguaninfo.hgdf.adp.entity.sys.SysToken;

 /**
 * sys_token mapper 层
 * @author henry
 */

public interface SysTokenMapper extends BaseSqlMapper {
    @Select("select count(1) from sys_token where token_id_=#{tokenId} and avoid_login_=1 and user_id_=#{userId} and token_sign_=#{tokenSign} and due_time_>=now()")
    Integer countValidToken(SysToken sysToken);
}

