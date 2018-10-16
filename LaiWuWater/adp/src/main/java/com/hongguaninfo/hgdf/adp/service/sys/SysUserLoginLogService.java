/*
 * File Name:SysUserLoginLogService.java
 * Package Name:com.hongguaninfo.hgdf.bud.service.sys
 * Date:2018年01月05日 下午3:56:34
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

import com.hongguaninfo.hgdf.adp.core.Constants;
import com.hongguaninfo.hgdf.adp.core.base.BasePage;
import com.hongguaninfo.hgdf.adp.core.base.BaseService;
import com.hongguaninfo.hgdf.adp.core.exception.BizException;
import com.hongguaninfo.hgdf.adp.core.utils.SessionUtils;
import com.hongguaninfo.hgdf.adp.core.utils.generator.DbIdGenerator;
import com.hongguaninfo.hgdf.adp.dao.sys.SysUserDao;
import com.hongguaninfo.hgdf.adp.dao.sys.SysUserLoginLogDao;
import com.hongguaninfo.hgdf.adp.entity.sys.SysUser;
import com.hongguaninfo.hgdf.adp.entity.sys.SysUserLoginLog;
import com.hongguaninfo.hgdf.core.utils.DateUtil;
import com.hongguaninfo.hgdf.core.utils.page.Page;


/**
 * 用户登录日志表: sys_user_login_log. <br />
 * service interface 层 <br />
 * Date: 2018年01月05日 下午3:56:34 <br />
 * @author  menghaixiao
 * @since V1.0.0
 */
@Service("sysUserLoginLogService")
public class SysUserLoginLogService extends BaseService {

	@Autowired
	private SysUserLoginLogDao sysUserLoginLogDao;

	@Autowired
	private DbIdGenerator dbIdGenerator;
	
	@Autowired
	private SysUserDao sysUserDao;
	
	@Autowired
	private SysDatadicItemService sysDatadicItemBiz;
	
	/**
	 * REMARK
	 * 分页查询
	 * Show all content and can paging
	 * The following id to give priority to key !
	 */
	public List<SysUserLoginLog> getSysUserLoginLogList (BasePage<SysUserLoginLog> basePage,
            SysUserLoginLog vo, Map<String, Object> map) throws BizException {
		System.out.println(111);
		basePage.setFilters(vo);
        Page<SysUserLoginLog> page = sysUserLoginLogDao.pageQuery(basePage);
        List<SysUserLoginLog> list = page.getResult();
        for (SysUserLoginLog bo : list) {
        	bo.setCrtTimeStr(DateUtil.convertDateTimeToString(bo.getCrtTime()));
        	bo.setOperResStr(sysDatadicItemBiz.getItemNameByValue("LOGIN_STATE", String.valueOf(bo.getOperRes())));
        }
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
	public void addSysUserLoginLog (SysUserLoginLog sysUserLoginLog){
		 
        sysUserLoginLog.setIsFinal(0);
        sysUserLoginLog.setCrtTime(new Date());
        sysUserLoginLog.setCrtUserId(SessionUtils.getUserId());
		sysUserLoginLogDao.save(sysUserLoginLog);
	}

	/**
	 * REMARK
	 * 修改
	 * update data
	 * Date need their conversion !
	 */
	public void updateSysUserLoginLog (SysUserLoginLog sysUserLoginLog) throws BizException{
		sysUserLoginLogDao.update(sysUserLoginLog);
	}

	/**
	 * REMARK
	 * 删除
	 * Through the id delete a data
	 */
	public void deleteSysUserLoginLog(int id) throws BizException{
		sysUserLoginLogDao.delete(id);
	}
	
	/**
	 * REMARK
	 * 物理删除
	 * Through the id delete a data logic
	 */
	public void deleteSysUserLoginLogLogic(int id) throws BizException{
        SysUserLoginLog sysUserLoginLog = new SysUserLoginLog();
        sysUserLoginLogDao.update(sysUserLoginLog);
	}	
	

	/**
	 * REMARK
	 * 查询实体信息
	 * Inquires the individual data
	 * Date need their conversion !
	 * @throws BizException 
	 */
	public SysUserLoginLog getSysUserLoginLogById(int id) throws BizException{
		if (id == 0) {
            return null;
        }
		
		SysUserLoginLog bo = sysUserLoginLogDao.getById(id);
		if(bo == null){
			return null;
		}
		
    	bo.setOperResStr(sysDatadicItemBiz.getItemNameByValue("LOGIN_STATE", String.valueOf(bo.getOperRes())));
    	bo.setCrtTimeStr(DateUtil.convertDateTimeToString(bo.getCrtTime()));
        return bo;
	}
	
	public List<SysUserLoginLog> getList(SysUserLoginLog vo){
		return sysUserLoginLogDao.getList(vo);
	}
	 
	 //是否包含失败或成功记录
	 public boolean contants(List<SysUserLoginLog> list,Integer isSuccess){
		 if(list == null || list.size() == 0){
			 return false;
		 }
		 
		 for(SysUserLoginLog bo : list){
			 if(bo.getOperRes() == isSuccess){
	    			return true;
	    	 }
		 }
		 return false;
	 }
}
