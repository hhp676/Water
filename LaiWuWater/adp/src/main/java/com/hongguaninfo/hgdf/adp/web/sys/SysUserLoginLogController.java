/*
 * Project Name:hbm-base.
 * File Name:SysUserLoginLogController.java
 * Package Name:com.hongguaninfo.hgdf.bud.web.sys
 * Date:2018年01月05日 下午3:56:34
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
import com.hongguaninfo.hgdf.adp.entity.sys.SysUserLoginLog;
import com.hongguaninfo.hgdf.adp.service.sys.SysUserLoginLogService;
import com.hongguaninfo.hgdf.adp.shiro.conf.Auths;
import com.hongguaninfo.hgdf.core.utils.logging.Log;
import com.hongguaninfo.hgdf.core.utils.logging.LogFactory;


/**
 * SysUserLoginLogController:用户登录日志表。 <br />
 * controller 层 <br />
 * Date:2018年01月05日 下午3:56:34
 *
 * @author menghaixiao
 * @since V1.0.0
 */
@Controller
@RequestMapping("/sys/SysUserLoginLog")
public class SysUserLoginLogController {

    /**
     *
     */
    private static final Log LOG = LogFactory.getLog(SysUserLoginLogController.class);

    /**
     * Service。
     */
    @Autowired
    private SysUserLoginLogService sysUserLoginLogService;

    /**
	 * REMARK
	 * 列表页面。
	 * @param pageRequest
	 * @param response
	 * @param request
	 */
	@RequestMapping("/showSysUserLoginLog")
	@RequiresPermissions(Auths.SYS_USERLOGIN_LOG)
	public String showSysUserLoginLog (HttpServletRequest request, HttpServletResponse response, Model model) {
		OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                str = "sys/sysUserLoginLog_show";
            }
        };
        return templete.operateModel();
	}
    /**
    * 分页数据。
    * @SysUserLoginLog
    * @return 
    */
	@RequestMapping("/list")
    @ResponseBody
    @RequiresPermissions(Auths.SYS_USERLOGIN_LOG_VIEW)
    public Map getSysUserLoginLogList (final SysUserLoginLog sysUserLoginLog , final BasePage pageRequest,
            HttpServletResponse response, HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
                sysUserLoginLogService.getSysUserLoginLogList(pageRequest, sysUserLoginLog, map);
            }
        };
        return templete.operate();
    }
	
	@RequestMapping("/sysUserLoginLogDetail/{logId}")
    @RequiresPermissions(Auths.SYS_USERLOGIN_LOG_VIEW)//权限控制
    public String getSysUserLogDetail(@PathVariable final Integer logId,
            HttpServletResponse response, HttpServletRequest request,
            Model model) throws BizException{
		 model.addAttribute("userLoginLog", sysUserLoginLogService.getSysUserLoginLogById(logId));
		 OperateTemplete templete = new HttpTemplete(request) {
	            protected void doSomething() throws BizException {
	                str = "sys/sysUserLoginLog_detail";
	            }
	        };
	        return templete.operateModel();
	}
}
