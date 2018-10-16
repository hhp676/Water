/*
 * Project Name:hbm-base.
 * File Name:SysUserStateChangeController.java
 * Package Name:com.hongguaninfo.hgdf.bud.web.sys
 * Date:2018年01月04日 下午3:55:45
 * Copyright (c) 2016, hongguaninfo.com All Rights Reserved.
 *
 */
package com.hongguaninfo.hgdf.adp.web.sys;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hongguaninfo.hgdf.adp.core.aop.log.UserLog;
import com.hongguaninfo.hgdf.adp.core.base.BasePage;
import com.hongguaninfo.hgdf.adp.core.exception.BizException;
import com.hongguaninfo.hgdf.core.utils.exception.BaseException;
import com.hongguaninfo.hgdf.adp.core.interceptor.RepeatSubmitToken;
import com.hongguaninfo.hgdf.adp.core.templete.HttpTemplete;
import com.hongguaninfo.hgdf.adp.core.templete.OperateTemplete;
import com.hongguaninfo.hgdf.adp.entity.sys.SysUserStateChange;
import com.hongguaninfo.hgdf.adp.service.sys.SysDatadicItemService;
import com.hongguaninfo.hgdf.adp.service.sys.SysUserStateChangeService;
import com.hongguaninfo.hgdf.adp.shiro.conf.Auths;
import com.hongguaninfo.hgdf.core.utils.logging.Log;
import com.hongguaninfo.hgdf.core.utils.logging.LogFactory;


/**
 * SysUserStateChangeController:用户状态变更表。 <br />
 * controller 层 <br />
 * Date:2018年01月04日 下午3:55:45
 *
 * @author menghaixiao
 * @since V1.0.0
 */
@Controller
@RequestMapping("/sys/SysUserStateChange")
public class SysUserStateChangeController {

    /**
     *
     */
    private static final Log LOG = LogFactory.getLog(SysUserStateChangeController.class);

    /**
     * Service。
     */
    @Autowired
    private SysUserStateChangeService sysUserStateChangeService;
    
    @Autowired
    private SysDatadicItemService sysDatadicItemService;

    /**
	 * REMARK
	 * 列表页面。
	 * @param pageRequest
	 * @param response
	 * @param request
	 */
	@RequestMapping("/showSysUserStateChange")
	@RequiresPermissions(Auths.SYS_USER_STATE_CHANGE)
	public String showSysUserStateChange (HttpServletRequest request, HttpServletResponse response, final Model model) {
		OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
            	model.addAttribute("userLockStateList", sysDatadicItemService.getListByGroupCode("USER_LOCK_STATE"));
            	model.addAttribute("crtTypeList", sysDatadicItemService.getListByGroupCode("CRT_TYPE"));
            	model.addAttribute("currentList", sysDatadicItemService.getListByGroupCode("CURRENT"));
            	str = "sys/sysUserStateChange_show";
            }
        };
        return templete.operateModel();
	}
    /**
    * 分页数据。
    * @SysUserStateChange
    * @return 
    */
	@RequestMapping("/list")
    @ResponseBody
    @RequiresPermissions(Auths.SYS_USER_STATE_CHANGE)
    public Map getSysUserStateChangeList (final SysUserStateChange sysUserStateChange , final BasePage pageRequest,
            HttpServletResponse response, HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
                sysUserStateChangeService.getSysUserStateChangeList(pageRequest, sysUserStateChange, map);
            }
        };
        return templete.operate();
    }


	/**
	 * REMARK
	 * 详情信息
	 * Through the id inquires the out a data
	 */
	@RequestMapping(value = "/sysUserStateChangeDetail/{id}")
    @RequiresPermissions(Auths.SYS_USER_STATE_CHANGE_VIEW)
	public String showSysUserStateChangeDetail (@PathVariable int id,
            HttpServletRequest request, HttpServletResponse response,
            Model model) throws BizException {
        model.addAttribute("id", id);
        model.addAttribute("sysUserStateChange",sysUserStateChangeService.getSysUserStateChangeById(id));
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                 str = "sys/sysUserStateChange_detail";
            }
        };
        return templete.operateModel();
    }
}
