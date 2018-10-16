/*
 * File Name:SysUserPasswordChangeService.java
 * Package Name:com.hongguaninfo.hgdf.bud.service.sys
 * Date:2018年01月03日 下午2:21:54
 * Copyright (c) 2016, hongguaninfo.com All Rights Reserved.
 *
 */
package com.hongguaninfo.hgdf.adp.service.sys;


import java.util.Date;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hongguaninfo.hgdf.adp.core.base.BasePage;
import com.hongguaninfo.hgdf.adp.core.exception.BizException;
import com.hongguaninfo.hgdf.adp.core.utils.SessionUtils;
import com.hongguaninfo.hgdf.adp.core.utils.generator.DbIdGenerator;
import com.hongguaninfo.hgdf.adp.dao.sys.SysUserPasswordChangeDao;
import com.hongguaninfo.hgdf.adp.entity.sys.SysUserPasswordChange;
import com.hongguaninfo.hgdf.core.utils.page.Page;


/**
 * 用户密码变更记录表: sys_user_password_change. <br />
 * service interface 层 <br />
 * Date: 2018年01月03日 下午2:21:54 <br />
 * @author  menghaixiao
 * @since V1.0.0
 */
@Service("sysUserPasswordChangeService")
public class SysUserPasswordChangeService {

	@Autowired
	private SysUserPasswordChangeDao sysUserPasswordChangeDao;

	@Autowired
	private DbIdGenerator dbIdGenerator;
	
	/**
	 * REMARK
	 * 分页查询
	 * Show all content and can paging
	 * The following id to give priority to key !
	 */
	public List<SysUserPasswordChange> getSysUserPasswordChangeList (BasePage<SysUserPasswordChange> basePage,
            SysUserPasswordChange vo, Map<String, Object> map) throws BizException {
		vo.setIsDelete(0);
        basePage.setFilters(vo);
        Page<SysUserPasswordChange> page = sysUserPasswordChangeDao.pageQuery(basePage);
        List<SysUserPasswordChange> list = page.getResult();
        /**for (SysUserPasswordChange bo : list) {
            bo.setIsFinalStr(sysDatadicItemBiz.getItemNameByValue("LOGIC_TAG",
                    bo.getIsFinal() + ""));
        }*/
        map.put("rows", list);
        map.put("total", page.getTotalCount());
        return list;
	}
    
	/**
	 * REMARK
	 * 新增信息
	 * Through the id inquires the out a data
	 * Date need their conversion !
	 * UserName is the current user name !
	 */
	public void addSysUserPasswordChange (SysUserPasswordChange sysUserPasswordChange) throws BizException{
		 
		sysUserPasswordChange.setIsDelete(0);
        sysUserPasswordChange.setIsFinal(0);
        sysUserPasswordChange.setCrtTime(new Date());
        sysUserPasswordChange.setCrtUserId(SessionUtils.getUserId());
        sysUserPasswordChange.setUpdTime(new Date());
        sysUserPasswordChange.setUpdUserId(SessionUtils.getUserId());
		sysUserPasswordChangeDao.save(sysUserPasswordChange);
	}
	
	public void add(Integer userId,Integer changeReason,String oldPwd,String newPwd) throws BizException{
		SysUserPasswordChange entity = new SysUserPasswordChange();
		entity.setAccountId(userId);
		entity.setChangeReason(changeReason);
		entity.setOldLoginPwd(oldPwd);
		entity.setNewLoginPwd(newPwd);
		this.addSysUserPasswordChange(entity);
	}

	/**
	 * REMARK
	 * 修改
	 * update data
	 * Date need their conversion !
	 */
	public void updateSysUserPasswordChange (SysUserPasswordChange sysUserPasswordChange) throws BizException{
 		sysUserPasswordChange.setUpdTime(new Date());
        sysUserPasswordChange.setUpdUserId(SessionUtils.getUserId());
		sysUserPasswordChangeDao.update(sysUserPasswordChange);
	}

	/**
	 * REMARK
	 * 删除
	 * Through the id delete a data
	 */
	public void deleteSysUserPasswordChange(int id) throws BizException{
		sysUserPasswordChangeDao.delete(id);
	}
	
	/**
	 * REMARK
	 * 物理删除
	 * Through the id delete a data logic
	 */
	public void deleteSysUserPasswordChangeLogic(int id) throws BizException{
        SysUserPasswordChange sysUserPasswordChange = new SysUserPasswordChange();
        sysUserPasswordChange.setUpdTime(new Date());
        sysUserPasswordChange.setUpdUserId(SessionUtils.getUserId());
        sysUserPasswordChange.setIsDelete(1);
        sysUserPasswordChangeDao.update(sysUserPasswordChange);
	}	
	

	/**
	 * REMARK
	 * 查询实体信息
	 * Inquires the individual data
	 * Date need their conversion !
	 */
	public SysUserPasswordChange getSysUserPasswordChangeById(int id) throws BizException{
		if (id == 0) {
            return null;
        }
        return sysUserPasswordChangeDao.getById(id);
	}
	
	public boolean checkRecentUsed(Integer accountId, int count, String newPwd){
		SysUserPasswordChange vo = new SysUserPasswordChange();
		vo.setAccountId(accountId);
		vo.setIsDelete(0);
		vo.setCount(count);
		vo.setOrderStr("supc.CRT_TIME_ desc");
		List<SysUserPasswordChange> list = sysUserPasswordChangeDao.getList(vo);
		if(list == null || list.size() < 1){
			return false;
		}
		
		for(SysUserPasswordChange bo : list){
			if(newPwd.equals(bo.getNewLoginPwd())){
				return true;
			}
		}
		return false;
	}
}
