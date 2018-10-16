package com.hongguaninfo.hgdf.wadp.service.sys;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hongguaninfo.hgdf.wadp.core.exception.BizException;
import com.hongguaninfo.hgdf.wadp.core.utils.SessionUtils;
import com.hongguaninfo.hgdf.wadp.dao.sys.SysUgroupAuthJoinDao;
import com.hongguaninfo.hgdf.wadp.entity.sys.SysUgroupAuthJoin;

/**
 * 系统用户组权限关联表:SYS_UGROUP_AUTH_JOIN biz 层
 * 
 * @author:yuyanlin
 */

@Service("sysUgroupAuthJoinService")
public class SysUgroupAuthJoinService {

    @Autowired
    private SysUgroupAuthJoinDao sysUgroupAuthJoinDao;

    // 通过用户组id获取列表
    public List<SysUgroupAuthJoin> getListByGroupId(int groupId)
            throws BizException {
        SysUgroupAuthJoin queryVo = new SysUgroupAuthJoin();
        queryVo.setGroupId(new BigDecimal(groupId));
        return sysUgroupAuthJoinDao.getList(queryVo);
    }

    // 通过用户组id获取权限id字符串
    public String getAuthIdsByGroupId(int groupId) throws BizException {
        List<String> authIdList = new ArrayList<String>();
        List<SysUgroupAuthJoin> list = getListByGroupId(groupId);
        for (SysUgroupAuthJoin bo : list) {
            authIdList.add(bo.getAuthId() + "");
        }
        return StringUtils.join(authIdList, ",");
    }

    // 新增
    public void insertSysUgroupAuthJoin(int groupId, int authId)
            throws BizException {
        SysUgroupAuthJoin sysUgroupAuthJoin = new SysUgroupAuthJoin();
        sysUgroupAuthJoin.setAuthId(new BigDecimal(authId));
        sysUgroupAuthJoin.setGroupId(new BigDecimal(groupId));
        sysUgroupAuthJoin.setIsFinal(0);
        sysUgroupAuthJoin
                .setCrtUserid(new BigDecimal(SessionUtils.getUserId()));
        sysUgroupAuthJoin.setCrtTime(new Date());
        sysUgroupAuthJoinDao.save(sysUgroupAuthJoin);

    }

    // 批量新增
    public void insertBatchSysUgroupAuthJoin(String authIds, int groupId)
            throws BizException {
        if (!StringUtils.isEmpty(authIds)) {
            List<SysUgroupAuthJoin> list = new ArrayList<SysUgroupAuthJoin>();
            String[] authIdAry = authIds.split(",");
            for (String authId : authIdAry) {
                SysUgroupAuthJoin sysUgroupAuthJoin = new SysUgroupAuthJoin();
                sysUgroupAuthJoin.setAuthId(new BigDecimal(authId));
                sysUgroupAuthJoin.setGroupId(new BigDecimal(groupId));
                sysUgroupAuthJoin.setIsFinal(0);
                sysUgroupAuthJoin.setCrtUserid(new BigDecimal(SessionUtils
                        .getUserId()));
                sysUgroupAuthJoin.setCrtTime(new Date());
                list.add(sysUgroupAuthJoin);
            }

            sysUgroupAuthJoinDao.saveBatch(list);
        }
    }

    // 通过用户组id删除
    public void deleteByGroupId(int groupId) throws BizException {
        sysUgroupAuthJoinDao.delete(groupId);
    }

}