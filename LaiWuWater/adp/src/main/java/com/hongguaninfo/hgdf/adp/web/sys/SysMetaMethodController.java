/*
 * Project Name:hbm-base.
 * File Name:SysMetaMethodController.java
 * Package Name:com.hongguaninfo.hgdf.bud.web.sys
 * Date:2018年01月03日 下午2:16:08
 * Copyright (c) 2016, hongguaninfo.com All Rights Reserved.
 *
 */
package com.hongguaninfo.hgdf.adp.web.sys;

import com.hongguaninfo.hgdf.adp.entity.sys.SysMetaMethod;
import com.hongguaninfo.hgdf.adp.service.sys.SysDatadicItemService;
import com.hongguaninfo.hgdf.adp.service.sys.SysMetaMethodService;

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

import com.hongguaninfo.hgdf.adp.core.Constants;
import com.hongguaninfo.hgdf.adp.core.aop.log.UserLog;
import com.hongguaninfo.hgdf.adp.core.base.BasePage;
import com.hongguaninfo.hgdf.adp.core.exception.BizException;
import com.hongguaninfo.hgdf.core.utils.exception.BaseException;
import com.hongguaninfo.hgdf.adp.core.interceptor.RepeatSubmitToken;
import com.hongguaninfo.hgdf.adp.core.templete.HttpTemplete;
import com.hongguaninfo.hgdf.adp.core.templete.OperateTemplete;
import com.hongguaninfo.hgdf.adp.shiro.conf.Auths;
import com.hongguaninfo.hgdf.core.utils.logging.Log;
import com.hongguaninfo.hgdf.core.utils.logging.LogFactory;

/**
 * SysMetaMethodController:系统元方法表。 <br />
 * controller 层 <br />
 * Date:2018年01月03日 下午2:16:08
 *
 * @author zhxl
 * @since V1.0.0
 */
@Controller
@RequestMapping("/sys/sysMetaMethod")
public class SysMetaMethodController {
    
    /**
     *
     */
    private static final Log      LOG = LogFactory.getLog(SysMetaMethodController.class);
    
    /**
     * Service。
     */
    @Autowired
    private SysMetaMethodService  sysMetaMethodService;
    
    /**
     * 字典项 Service。
     */
    @Autowired
    private SysDatadicItemService sysDatadicItemService;
    
    /**
     * REMARK
     * 列表页面。
     * @param pageRequest
     * @param response
     * @param request
     */
    @RequestMapping("/showSysMetaMethod")
    @RequiresPermissions(Auths.SYS_META_METHOD)
    public String showSysMetaMethod(HttpServletRequest request, HttpServletResponse response,
        Model model) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                str = "sys/sysMetaMethod_show";
            }
        };
        return templete.operateModel();
    }
    
    /**
    * 分页数据。
    * @SysMetaMethod
    * @return 
    */
    @RequestMapping("/list")
    @ResponseBody
    @RequiresPermissions(Auths.SYS_META_METHOD)
    public Map getSysMetaMethodList(final SysMetaMethod sysMetaMethod, final BasePage pageRequest,
        HttpServletResponse response, HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
                sysMetaMethodService.getSysMetaMethodList(pageRequest, sysMetaMethod, map);
            }
        };
        return templete.operate();
    }
    
    /**
     * REMARK
     *
     * 新增
     */
    @RequestMapping("/add")
    @ResponseBody
    @RepeatSubmitToken(removeToken = true)
    @RequiresPermissions(Auths.SYS_META_METHOD_ADD)
    @UserLog(code = "addSysMetaMethod", name = "add SysMetaMethod", remarkClass = SysMetaMethod.class)
    public Map addSysMetaMethod(@Valid final SysMetaMethod vo, BindingResult result,
        HttpServletResponse response, final HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request, result) {
            protected void doSomething() throws BizException {
                if (doValidate()) {
                    sysMetaMethodService.addSysMetaMethodNotExist(vo);
                }
            }
        };
        return templete.operate();
    }
    
    /**
     * REMARK
     *
     * 修改
     */
    @RequestMapping("/update")
    @ResponseBody
    @RequiresPermissions(Auths.SYS_META_METHOD_EDIT)
    @UserLog(code = "editSysMetaMethod", name = "update SysMetaMethod", remarkClass = SysMetaMethod.class)
    public Map editSysMetaMethod(final SysMetaMethod vo, HttpServletResponse response,
        final HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
                sysMetaMethodService.updateSysMetaMethod(vo);
            }
        };
        return templete.operate();
    }
    
    /**
     * REMARK
     * 删除
     * Through the id delete a data
     */
    @RequestMapping("/delete")
    @ResponseBody
    @RequiresPermissions(Auths.SYS_META_METHOD_DELETE)
    @UserLog(code = "deleteSysMetaMethod", name = "delete SysMetaMethod", remarkClass = Integer.class)
    public Map deleteSysMetaMethod(final Integer id, HttpServletResponse response,
        final HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
                sysMetaMethodService.deleteSysMetaMethodLogic(id);
            }
        };
        return templete.operate();
    }
    
    /**
     * REMARK
     * 详情信息
     * Through the id inquires the out a data
     */
    @RequestMapping(value = "/sysMetaMethodDetail/{id}")
    @RequiresPermissions(Auths.SYS_META_METHOD_VIEW)
    public String showSysMetaMethodDetail(@PathVariable int id, HttpServletRequest request,
        HttpServletResponse response, Model model) throws BizException {
        model.addAttribute("id", id);
        model.addAttribute("sysMetaMethod", sysMetaMethodService.getSysMetaMethodById(id));
        model.addAttribute("logLevelMap",
            sysDatadicItemService.getMapByGroupCode(Constants.DICGROUP_SYSMETAMETHOD_LOG_LEVEL));
        model.addAttribute("logTypeMap",
            sysDatadicItemService.getMapByGroupCode(Constants.DICGROUP_SYSMETAMETHOD_LOG_TYPE));
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                str = "sys/sysMetaMethod_detail";
            }
        };
        return templete.operateModel();
    }
    
    /**
     * 初始化元方法数据
     * 实现：扫描所有Controller的方法更新到表中，方便配置方法日志信息
     * @param request
     * @param response
     * @return
     * @throws BizException
     */
    @RequestMapping("/init")
    @ResponseBody
    @RequiresPermissions(Auths.SYS_META_METHOD_INIT)
    @UserLog(code = "initSysMetaMethod", name = "init SysMetaMethod", remarkClass = SysMetaMethod.class)
    public Map initSysMetaMethod(HttpServletRequest request, HttpServletResponse response)
        throws BizException {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
                sysMetaMethodService.doInitMetaMethod();
            }
        };
        return templete.operate();
    }
}
