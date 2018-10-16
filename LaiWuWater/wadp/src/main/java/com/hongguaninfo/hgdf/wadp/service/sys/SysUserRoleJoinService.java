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
import com.hongguaninfo.hgdf.wadp.dao.sys.SysUserRoleJoinDao;
import com.hongguaninfo.hgdf.wadp.entity.sys.SysUserRoleJoin;

/**
 * 系统用户角色关联表:SYS_USER_ROLE_JOIN biz 层
 * 
 * @author:yuyanlin
 */

@Service("sysUserRoleJoinService")
public class SysUserRoleJoinService {

    @Autowired
    private SysUserRoleJoinDao sysUserRoleJoinDao;

    // 通过用户id获取列表
    public List<SysUserRoleJoin> getListByUserId(int userId)
            throws BizException {
        SysUserRoleJoin queryVo = new SysUserRoleJoin();
        queryVo.setUserId(new BigDecimal(userId));
        return sysUserRoleJoinDao.getList(queryVo);
    }

    // 通过用户id获取角色id字符串
    public String getRoleIdsByUserId(int userId) throws BizException {
        List<String> roleIdList = new ArrayList<String>();
        List<SysUserRoleJoin> list = getListByUserId(userId);
        for (SysUserRoleJoin bo : list) {
            roleIdList.add(bo.getRoleId() + "");
        }
        return StringUtils.join(roleIdList, ",");
    }
    
    //用过用户id获取角色名称字符串
    public String getRoleNamesByUserId(int userId) throws BizException {
        List<String> roleNameList = new ArrayList<String>();
        List<SysUserRoleJoin> list = getListByUserId(userId);
        for (SysUserRoleJoin bo : list) {
        	roleNameList.add(bo.getRoleName());
        }
        return StringUtils.join(roleNameList, "，");
    }

    // 新增
    public void insertSysUserRoleJoin(int userId, int roleId)
            throws BizException {
        SysUserRoleJoin sysUserRoleJoin = new SysUserRoleJoin();
        sysUserRoleJoin.setRoleId(new BigDecimal(roleId));
        sysUserRoleJoin.setUserId(new BigDecimal(userId));
        sysUserRoleJoin.setIsFinal(0);
        sysUserRoleJoin.setCrtUserid(new BigDecimal(SessionUtils.getUserId()));
        sysUserRoleJoin.setCrtTime(new Date());
        sysUserRoleJoinDao.save(sysUserRoleJoin);

    }

    // 批量新增
    public void insertBatchSysUserRoleJoin(String roleIds, int userId)
            throws BizException {
        if (!StringUtils.isEmpty(roleIds)) {
            List<SysUserRoleJoin> list = new ArrayList<SysUserRoleJoin>();
            String[] roleIdAry = roleIds.split(",");
            for (String roleId : roleIdAry) {
                SysUserRoleJoin sysUserRoleJoin = new SysUserRoleJoin();
                sysUserRoleJoin.setRoleId(new BigDecimal(roleId));
                sysUserRoleJoin.setUserId(new BigDecimal(userId));
                sysUserRoleJoin.setIsFinal(0);
                sysUserRoleJoin.setCrtUserid(new BigDecimal(SessionUtils
                        .getUserId()));
                sysUserRoleJoin.setCrtTime(new Date());
                list.add(sysUserRoleJoin);
            }

            sysUserRoleJoinDao.saveBatch(list);
        }
    }

    // 通过用户id删除
    public void deleteByUserId(int userId) throws BizException {
        sysUserRoleJoinDao.delete(userId);
    }

}