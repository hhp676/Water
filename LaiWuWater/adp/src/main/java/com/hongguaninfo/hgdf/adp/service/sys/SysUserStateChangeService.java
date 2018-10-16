/*
 * File Name:SysUserStateChangeService.java
 * Package Name:com.hongguaninfo.hgdf.bud.service.sys
 * Date:2018年01月04日 下午3:55:45
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

import com.alibaba.fastjson.JSON;
import com.hongguaninfo.hgdf.adp.core.Constants;
import com.hongguaninfo.hgdf.adp.core.base.BasePage;
import com.hongguaninfo.hgdf.adp.core.base.BaseService;
import com.hongguaninfo.hgdf.adp.core.exception.BizException;
import com.hongguaninfo.hgdf.adp.core.utils.SessionUtils;
import com.hongguaninfo.hgdf.adp.core.utils.generator.DbIdGenerator;
import com.hongguaninfo.hgdf.adp.dao.sys.SysUserStateChangeDao;
import com.hongguaninfo.hgdf.adp.entity.sys.SysUserStateChange;
import com.hongguaninfo.hgdf.core.utils.DateUtil;
import com.hongguaninfo.hgdf.core.utils.page.Page;


/**
 * 用户状态变更表: sys_user_state_change. <br />
 * service interface 层 <br />
 * Date: 2018年01月04日 下午3:55:45 <br />
 * @author  menghaixiao
 * @since V1.0.0
 */
@Service("sysUserStateChangeService")
public class SysUserStateChangeService extends BaseService{

	@Autowired
	private SysUserStateChangeDao sysUserStateChangeDao;

	@Autowired
	private DbIdGenerator dbIdGenerator;
	
	@Autowired
	private SysDatadicItemService sysDatadicItemBiz;
	
	/**
	 * REMARK
	 * 分页查询
	 * Show all content and can paging
	 * The following id to give priority to key !
	 */
	public List<SysUserStateChange> getSysUserStateChangeList (BasePage<SysUserStateChange> basePage,
            SysUserStateChange vo, Map<String, Object> map) throws BizException {
		vo.setIsDelete(0);
        basePage.setFilters(vo);
        Page<SysUserStateChange> page = sysUserStateChangeDao.pageQuery(basePage);
        List<SysUserStateChange> list = page.getResult();
        for (SysUserStateChange bo : list) {
            bo.setCrtTimeStr(DateUtil.convertDateTimeToString(bo.getCrtTime()));
            bo.setOriginStateStr(sysDatadicItemBiz.getItemNameByValue("USER_LOCK_STATE", bo.getOriginState()+""));
            bo.setCurrentStateStr(sysDatadicItemBiz.getItemNameByValue("USER_LOCK_STATE", bo.getCurrentState()+""));
            bo.setCrtTypeStr(sysDatadicItemBiz.getItemNameByValue("CRT_TYPE", bo.getCrtType()+""));
            bo.setIsCurrentStr(sysDatadicItemBiz.getItemNameByValue("CURRENT", bo.getIsCurrent()+""));
            bo.setTypeStr(sysDatadicItemBiz.getItemNameByValue("USER_STATE_TYPE", bo.getType()+""));
        }
        map.put("rows", list);
        map.put("total", page.getTotalCount());
        return list;
	}
	
	public List<SysUserStateChange> getList(SysUserStateChange vo){
		return sysUserStateChangeDao.getList(vo);
	}
    
	/**
	 * REMARK
	 * 新增信息
	 * Through the id inquires the out a data
	 * Date need their conversion !
	 * UserName is the current user name !
	 */
	public void addSysUserStateChange (SysUserStateChange sysUserStateChange) throws BizException{
		
		sysUserStateChange.setIsCurrent(Constants.IS_CURRENT);
		sysUserStateChange.setIsDelete(0);
        sysUserStateChange.setIsFinal(0);
        sysUserStateChange.setCrtTime(new Date());
        sysUserStateChange.setCrtUserId(SessionUtils.getUserId());
        sysUserStateChange.setUpdTime(new Date());
        sysUserStateChange.setUpdUserId(SessionUtils.getUserId());
		sysUserStateChangeDao.save(sysUserStateChange);
	}

	/**
	 * REMARK
	 * 修改
	 * update data
	 * Date need their conversion !
	 */
	public void updateSysUserStateChange (SysUserStateChange sysUserStateChange){
 		sysUserStateChange.setUpdTime(new Date());
        sysUserStateChange.setUpdUserId(SessionUtils.getUserId());
		sysUserStateChangeDao.update(sysUserStateChange);
	}

	/**
	 * REMARK
	 * 删除
	 * Through the id delete a data
	 */
	public void deleteSysUserStateChange(int id) throws BizException{
		sysUserStateChangeDao.delete(id);
	}
	
	/**
	 * REMARK
	 * 物理删除
	 * Through the id delete a data logic
	 */
	public void deleteSysUserStateChangeLogic(int id) throws BizException{
        SysUserStateChange sysUserStateChange = new SysUserStateChange();
        sysUserStateChange.setUpdTime(new Date());
        sysUserStateChange.setUpdUserId(SessionUtils.getUserId());
        sysUserStateChange.setIsDelete(1);
        sysUserStateChangeDao.update(sysUserStateChange);
	}	
	

	/**
	 * REMARK
	 * 查询实体信息
	 * Inquires the individual data
	 * Date need their conversion !
	 */
	public SysUserStateChange getSysUserStateChangeById(int id) throws BizException{
		if (id == 0) {
            return null;
        }
        return sysUserStateChangeDao.getById(id);
	}
	
	public SysUserStateChange getCurrentOne(Integer accountId, String ip, int type){
		SysUserStateChange vo = new SysUserStateChange();
		vo.setIsDelete(0);
		vo.setAccountId(accountId);
		vo.setIp(ip);
		vo.setType(type);
		vo.setOrderStr("susc.CRT_TIME_ desc");
		return getCurrentOne(vo);
	}
	
	public SysUserStateChange getCurrentOne(Integer accountId) {
        return getCurrentOne(accountId, null, Constants.TYPE_ACCOUNT);
    }
	
	/**
     * 获取当前状态
     * @param filter
     * @return
     */
    public SysUserStateChange getCurrentOne(SysUserStateChange vo) {
        List<SysUserStateChange> list = getCurrentList(vo);
        SysUserStateChange currentChange = null;
        if (list != null && list.size() > 0) {
            currentChange = list.get(0);
        }
        return currentChange;
    }
    
    public List<SysUserStateChange> getCurrentList(SysUserStateChange vo) {
        vo.setIsCurrent(1);
        return sysUserStateChangeDao.getList(vo);
    }
    
    public void updateAccountToLock(Integer accountId, Integer originState) throws BizException {
        SysUserStateChange entity = new SysUserStateChange();
        entity.setAccountId(accountId);
        entity.setOriginState(originState);
        entity.setCurrentState(Constants.LOCK);
        entity.setDescr("自动锁定账号");
        this.update(entity, Constants.TYPE_ACCOUNT, Constants.AUTO);
    }
    
    public void update(SysUserStateChange entity, Integer type, Integer crtType) throws BizException{
        entity.setType(type);
        entity.setCrtType(crtType);
        update(entity);
    }
    
    public void update(SysUserStateChange entity) throws BizException{
        
        //查询原状态
        SysUserStateChange filter = new SysUserStateChange();
        filter.setType(entity.getType());//状态类型
        filter.setAccountId(entity.getAccountId());
        filter.setIp(entity.getIp());
        List<SysUserStateChange> oldStates = getCurrentList(filter);
        
        SysUserStateChange oldState = null;
        if (oldStates != null && oldStates.size() > 0) {
            oldState = oldStates.get(0);
            if (oldStates.size() > 1) {
                LOG.debug("符合条件的当前用户状态记录多于一条。过滤条件：" + JSON.toJSONString(filter));
            }
            for (SysUserStateChange state : oldStates) {
                //状态位置0
                state.setIsCurrent(Constants.NOT_CURRENT);
                updateSysUserStateChange(state);
            }
        }
        
        if (entity.getOriginState() == null) {
            if(oldState != null){
                entity.setOriginState(oldState.getCurrentState());
            }else{
                entity.setOriginState(Constants.NOT_CURRENT);
            }
        }
        this.addSysUserStateChange(entity);
    }
    
    public void updateAccountToUnlock(Integer accountId, Integer originState) throws BizException{
        SysUserStateChange entity = new SysUserStateChange();
        entity.setAccountId(accountId);
        entity.setOriginState(originState);
        entity.setCurrentState(Constants.UNLOCK);
        entity.setDescr("系统自动解锁账户");
        this.update(entity, Constants.TYPE_ACCOUNT, Constants.AUTO);
    }
    
    public void updateAccountManualToLock(Integer accountId, Integer originState) throws BizException {
        SysUserStateChange entity = new SysUserStateChange();
        entity.setAccountId(accountId);
        entity.setOriginState(originState);
        entity.setCurrentState(Constants.LOCK);
        entity.setDescr("手动锁定账号");
        this.update(entity, Constants.TYPE_ACCOUNT, Constants.MANUAL);
    }
	
    public void updateAccountManualToUnlock(Integer accountId, Integer originState) throws BizException {
        SysUserStateChange entity = new SysUserStateChange();
        entity.setAccountId(accountId);
        entity.setOriginState(originState);
        entity.setCurrentState(Constants.UNLOCK);
        entity.setDescr("手动解锁账号");
        this.update(entity, Constants.TYPE_ACCOUNT, Constants.MANUAL);
    }
}
