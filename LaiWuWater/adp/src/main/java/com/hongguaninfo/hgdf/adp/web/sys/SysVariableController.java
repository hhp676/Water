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
import com.hongguaninfo.hgdf.adp.core.interceptor.RepeatSubmitToken;
import com.hongguaninfo.hgdf.adp.core.templete.HttpTemplete;
import com.hongguaninfo.hgdf.adp.core.templete.OperateTemplete;
import com.hongguaninfo.hgdf.adp.entity.sys.SysVariable;
import com.hongguaninfo.hgdf.adp.service.sys.SysVariableService;
import com.hongguaninfo.hgdf.adp.shiro.conf.Auths;
import com.hongguaninfo.hgdf.core.utils.exception.BaseException;

@Controller
@RequestMapping("/sys/var")
public class SysVariableController {

    @Autowired
    private SysVariableService sysVariableBiz;

    @RequestMapping(value = "/showSysVar")
    @RequiresPermissions(Auths.SYS_VARIABLE)
    public String showSysVar(HttpServletRequest request,
            HttpServletResponse response, Model model) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                str = "sys/sysVar_show";
            }
        };
        return templete.operateModel();
    }

    @RequestMapping(value = "/sysVarDetail/{varId}")
    @RequiresPermissions(Auths.SYS_VARIABLE_VIEW)
    public String showSysVarDetail(@PathVariable int varId,
            HttpServletRequest request, HttpServletResponse response,
            Model model) throws BizException {
        model.addAttribute("varId", varId);
        model.addAttribute("sysVariable",
                sysVariableBiz.getSysVariableByVarId(varId));
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                str = "sys/sysVar_detail";
            }
        };
        return templete.operateModel();
    }

    @RequestMapping("/list")
    @ResponseBody
    @RequiresPermissions(Auths.SYS_VARIABLE)
    public Map getSysVarList(final SysVariable vo, final BasePage pageRequest,
            HttpServletResponse response, HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
                sysVariableBiz.getSysVariableList(pageRequest, vo, map);
            }
        };
        return templete.operate();
    }

    @RequestMapping("/add")
    @ResponseBody
    @RepeatSubmitToken(removeToken = true)
    @RequiresPermissions(Auths.SYS_VARIABLE_ADD)
    @UserLog(code = "addSysVar", name = "新建系统变量", remarkClass = SysVariable.class)
    public Map addSysVar(@Valid final SysVariable vo, BindingResult result,
            HttpServletResponse response, final HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request, result) {
            protected void doSomething() throws BizException {
                if (doValidate()) {
                    sysVariableBiz.insertVariable(vo);

                }
            }
        };
        return templete.operate();
    }

    @RequestMapping("/edit")
    @ResponseBody
    @RequiresPermissions(Auths.SYS_VARIABLE_EDIT)
    @UserLog(code = "editSysvar", name = "修改系统变量", remarkClass = SysVariable.class)
    public Map editSysVar(final SysVariable vo, HttpServletResponse response,
            final HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
                sysVariableBiz.updateVariable(vo);
            }
        };
        return templete.operate();
    }

    @RequestMapping("/delete")
    @ResponseBody
    @RequiresPermissions(Auths.SYS_VARIABLE_DELETE)
    @UserLog(code = "deleteSysvar", name = "删除系统变量", remarkClass = Integer.class)
    public Map deleteSysVar(final Integer id, HttpServletResponse response,
            final HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
                sysVariableBiz.deleteById(id);
            }
        };
        return templete.operate();
    }

}
