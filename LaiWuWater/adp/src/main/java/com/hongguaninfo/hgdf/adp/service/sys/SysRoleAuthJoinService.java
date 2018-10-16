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
import com.hongguaninfo.hgdf.adp.dao.sys.SysRoleAuthJoinDao;
import com.hongguaninfo.hgdf.adp.entity.sys.SysRoleAuthJoin;

/**
 * 系统角色权限关联表:SYS_ROLE_AUTH_JOIN biz 层
 * 
 * @author:yuyanlin
 */

@Service("sysRoleAuthJoinService")
public class SysRoleAuthJoinService {

    @Autowired
    private SysRoleAuthJoinDao sysRoleAuthJoinDao;

    // 通过角色id获取列表
    public List<SysRoleAuthJoin> getListByRoleId(int roleId)
            throws BizException {
        SysRoleAuthJoin queryVo = new SysRoleAuthJoin();
        queryVo.setRoleId(new BigDecimal(roleId));
        return sysRoleAuthJoinDao.getList(queryVo);
    }

    // 通过角色id获取权限id字符串
    public String getAuthIdsByRoleId(int roleId) throws BizException {
        List<String> authIdList = new ArrayList<String>();
        List<SysRoleAuthJoin> list = getListByRoleId(roleId);
        for (SysRoleAuthJoin bo : list) {
            authIdList.add(bo.getAuthId() + "");
        }
        return StringUtils.join(authIdList, ",");
    }

    // 新增
    public void insertSysRoleAuthJoin(int roleId, int authId)
            throws BizException {
        SysRoleAuthJoin sysRoleAuthJoin = new SysRoleAuthJoin();
        sysRoleAuthJoin.setAuthId(new BigDecimal(authId));
        sysRoleAuthJoin.setRoleId(new BigDecimal(roleId));
        sysRoleAuthJoin.setIsFinal(0);
        sysRoleAuthJoin.setCrtUserid(new BigDecimal(SessionUtils.getUserId()));
        sysRoleAuthJoin.setCrtTime(new Date());
        sysRoleAuthJoinDao.save(sysRoleAuthJoin);

    }

    // 批量新增
    public void insertBatchSysRoleAuthJoin(String authIds, int roleId)
            throws BizException {
        if (!StringUtils.isEmpty(authIds)) {
            List<SysRoleAuthJoin> list = new ArrayList<SysRoleAuthJoin>();
            String[] authIdAry = authIds.split(",");
            for (String authId : authIdAry) {
                SysRoleAuthJoin sysRoleAuthJoin = new SysRoleAuthJoin();
                sysRoleAuthJoin.setAuthId(new BigDecimal(authId));
                sysRoleAuthJoin.setRoleId(new BigDecimal(roleId));
                sysRoleAuthJoin.setIsFinal(0);
                sysRoleAuthJoin.setCrtUserid(new BigDecimal(SessionUtils
                        .getUserId()));
                sysRoleAuthJoin.setCrtTime(new Date());
                list.add(sysRoleAuthJoin);
            }

            sysRoleAuthJoinDao.saveBatch(list);
        }
    }

    // 通过角色id删除
    public void deleteByRoleId(int roleId) throws BizException {
        sysRoleAuthJoinDao.delete(roleId);
    }

	public void deleteByAuthId(int authId) {
		sysRoleAuthJoinDao.deleteByAuthId(authId);
		
	}

}