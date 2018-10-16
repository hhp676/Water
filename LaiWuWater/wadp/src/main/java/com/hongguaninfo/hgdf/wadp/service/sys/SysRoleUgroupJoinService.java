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
import com.hongguaninfo.hgdf.wadp.dao.sys.SysRoleUgroupJoinDao;
import com.hongguaninfo.hgdf.wadp.entity.sys.SysRoleUgroupJoin;

/**
 * 系统用户角色关联表:SYS_USER_ROLE_JOIN biz 层
 * 
 * @author:yuyanlin
 */

@Service("sysRoleUgroupJoinService")
public class SysRoleUgroupJoinService {

    @Autowired
    private SysRoleUgroupJoinDao sysRoleUgroupJoinDao;

    // 通过用户组id获取列表
    public List<SysRoleUgroupJoin> getListByGroupId(int groupId)
            throws BizException {
        SysRoleUgroupJoin queryVo = new SysRoleUgroupJoin();
        queryVo.setGroupId(new BigDecimal(groupId));
        return sysRoleUgroupJoinDao.getList(queryVo);
    }

    // 通过用户组id获取角色id字符串
    public String getRoleIdsByGroupId(int groupId) throws BizException {
        List<String> roleIdList = new ArrayList<String>();
        List<SysRoleUgroupJoin> list = getListByGroupId(groupId);
        for (SysRoleUgroupJoin bo : list) {
            roleIdList.add(bo.getRoleId() + "");
        }
        return StringUtils.join(roleIdList, ",");
    }

    // 新增
    public void insertSysRoleUgroupJoin(int groupId, int roleId)
            throws BizException {
        SysRoleUgroupJoin sysUgroupRoleJoin = new SysRoleUgroupJoin();
        sysUgroupRoleJoin.setRoleId(new BigDecimal(roleId));
        sysUgroupRoleJoin.setGroupId(new BigDecimal(groupId));
        sysUgroupRoleJoin.setIsFinal(0);
        sysUgroupRoleJoin
                .setCrtUserid(new BigDecimal(SessionUtils.getUserId()));
        sysUgroupRoleJoin.setCrtTime(new Date());
        sysRoleUgroupJoinDao.save(sysUgroupRoleJoin);

    }

    // 批量新增
    public void insertBatchSysUgroupRoleJoin(String roleIds, int groupId)
            throws BizException {
        if (!StringUtils.isEmpty(roleIds)) {
            List<SysRoleUgroupJoin> list = new ArrayList<SysRoleUgroupJoin>();
            String[] roleIdAry = roleIds.split(",");
            for (String roleId : roleIdAry) {
                SysRoleUgroupJoin sysUgroupRoleJoin = new SysRoleUgroupJoin();
                sysUgroupRoleJoin.setRoleId(new BigDecimal(roleId));
                sysUgroupRoleJoin.setGroupId(new BigDecimal(groupId));
                sysUgroupRoleJoin.setIsFinal(0);
                sysUgroupRoleJoin.setCrtUserid(new BigDecimal(SessionUtils
                        .getUserId()));
                sysUgroupRoleJoin.setCrtTime(new Date());
                list.add(sysUgroupRoleJoin);
            }

            sysRoleUgroupJoinDao.saveBatch(list);
        }
    }

    // 通过用户组id删除
    public void deleteByGroupId(int groupId) throws BizException {
        sysRoleUgroupJoinDao.delete(groupId);
    }

}