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
import com.hongguaninfo.hgdf.wadp.dao.sys.SysUserUgroupJoinDao;
import com.hongguaninfo.hgdf.wadp.entity.sys.SysUserUgroupJoin;

/**
 * 系统用户用户组关联表:SYS_USER_UGROUP_JOIN biz 层
 * 
 * @author:yuyanlin
 */

@Service("sysUserUgroupJoinService")
public class SysUserUgroupJoinService {

    @Autowired
    private SysUserUgroupJoinDao sysUserUgroupJoinDao;

    // 通过用户id获取列表
    public List<SysUserUgroupJoin> getListByUserId(int userId)
            throws BizException {
    	SysUserUgroupJoin queryVo = new SysUserUgroupJoin();
        queryVo.setUserId(new BigDecimal(userId));
        return sysUserUgroupJoinDao.getList(queryVo);
    }

    // 通过用户id获取用户组id字符串
    public String getGroupIdsByUserId(int userId) throws BizException {
        List<String> groupIdList = new ArrayList<String>();
        List<SysUserUgroupJoin> list = getListByUserId(userId);
        for (SysUserUgroupJoin bo : list) {
        	groupIdList.add(bo.getGroupId() + "");
        }
        return StringUtils.join(groupIdList, ",");
    }
    
    // 通过用户id获取用户组名称字符串
    public String getGroupNamesByUserId(int userId) throws BizException {
        List<String> groupNameList = new ArrayList<String>();
        List<SysUserUgroupJoin> list = getListByUserId(userId);
        for (SysUserUgroupJoin bo : list) {
        	groupNameList.add(bo.getGroupName());
        }
        return StringUtils.join(groupNameList, "，");
    }

    // 新增
    public void insertSysUserUgroupJoin(int userId, int groupId)
            throws BizException {
    	SysUserUgroupJoin sysUserUgroupJoin = new SysUserUgroupJoin();
    	sysUserUgroupJoin.setGroupId(new BigDecimal(groupId));
    	sysUserUgroupJoin.setUserId(new BigDecimal(userId));
    	sysUserUgroupJoin.setIsFinal(0);
    	sysUserUgroupJoin.setCrtUserid(new BigDecimal(SessionUtils.getUserId()));
    	sysUserUgroupJoin.setCrtTime(new Date());
    	sysUserUgroupJoinDao.save(sysUserUgroupJoin);

    }

    // 批量新增
    public void insertBatchSysUserUgroupJoin(String groupIds, int userId)
            throws BizException {
        if (!StringUtils.isEmpty(groupIds)) {
            List<SysUserUgroupJoin> list = new ArrayList<SysUserUgroupJoin>();
            String[] groupIdAry = groupIds.split(",");
            for (String groupId : groupIdAry) {
            	SysUserUgroupJoin sysUserUgroupJoin = new SysUserUgroupJoin();
            	sysUserUgroupJoin.setGroupId(new BigDecimal(groupId));
            	sysUserUgroupJoin.setUserId(new BigDecimal(userId));
            	sysUserUgroupJoin.setIsFinal(0);
            	sysUserUgroupJoin.setCrtUserid(new BigDecimal(SessionUtils
                        .getUserId()));
            	sysUserUgroupJoin.setCrtTime(new Date());
                list.add(sysUserUgroupJoin);
            }

            sysUserUgroupJoinDao.saveBatch(list);
        }
    }

    // 通过用户id删除
    public void deleteByUserId(int userId) throws BizException {
    	sysUserUgroupJoinDao.delete(userId);
    }

}