package com.hongguaninfo.hgdf.adp.service.sys;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hongguaninfo.hgdf.adp.core.exception.BizException;
import com.hongguaninfo.hgdf.adp.core.utils.SessionUtils;
import com.hongguaninfo.hgdf.adp.dao.sys.SysUserAuthJoinDao;
import com.hongguaninfo.hgdf.adp.entity.sys.SysUserAuthJoin;

/**
 * 系统角色权限关联表:SYS_ROLE_AUTH_JOIN biz 层
 * 
 * @author:yuyanlin
 */

@Service("sysUserAuthJoinService")
public class SysUserAuthJoinService {

    @Autowired
    private SysUserAuthJoinDao sysUserAuthJoinDao;

    // 通过用户id获取列表
    public List<SysUserAuthJoin> getListByUserId(int userId)
            throws BizException {
        SysUserAuthJoin queryVo = new SysUserAuthJoin();
        queryVo.setUserId(new BigDecimal(userId));
        return sysUserAuthJoinDao.getList(queryVo);
    }

    // 通过用户id获取权限id字符串
    public String getAuthIdsByUserId(int userId) throws BizException {
        List<String> authIdList = new ArrayList<String>();
        List<SysUserAuthJoin> list = getListByUserId(userId);
        for (SysUserAuthJoin bo : list) {
            authIdList.add(bo.getAuthId() + "");
        }
        return StringUtils.join(authIdList, ",");
    }

    // 新增
    public void insertSysUserAuthJoin(int userId, int authId)
            throws BizException {
        SysUserAuthJoin sysUserAuthJoin = new SysUserAuthJoin();
        sysUserAuthJoin.setAuthId(new BigDecimal(authId));
        sysUserAuthJoin.setUserId(new BigDecimal(userId));
        sysUserAuthJoin.setAuthType(1);// 正授权
        sysUserAuthJoin.setIsFinal(0);
        sysUserAuthJoin.setCrtUserid(new BigDecimal(SessionUtils.getUserId()));
        sysUserAuthJoin.setCrtTime(new Date());
        sysUserAuthJoinDao.save(sysUserAuthJoin);

    }

    // 批量新增
    public void insertBatchSysUserAuthJoin(String authIds, int userId)
            throws BizException {
        if (!StringUtils.isEmpty(authIds)) {
            List<SysUserAuthJoin> list = new ArrayList<SysUserAuthJoin>();
            String[] authIdAry = authIds.split(",");
            for (String authId : authIdAry) {
                SysUserAuthJoin sysUserAuthJoin = new SysUserAuthJoin();
                sysUserAuthJoin.setAuthId(new BigDecimal(authId));
                sysUserAuthJoin.setUserId(new BigDecimal(userId));
                sysUserAuthJoin.setIsFinal(0);
                sysUserAuthJoin.setAuthType(1);// 正授权
                sysUserAuthJoin.setCrtUserid(new BigDecimal(SessionUtils
                        .getUserId()));
                sysUserAuthJoin.setCrtTime(new Date());
                list.add(sysUserAuthJoin);
            }

            sysUserAuthJoinDao.saveBatch(list);
        }
    }

    // 通过角色id删除
    public void deleteByUserId(int userId) throws BizException {
        sysUserAuthJoinDao.delete(userId);
    }

	public void deleteByAuthId(int authId) {
		sysUserAuthJoinDao.deleteByAuthId(authId);
		
	}

}