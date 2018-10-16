package com.hongguaninfo.hgdf.wadp.web.sys;

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

import com.hongguaninfo.hgdf.wadp.core.aop.log.UserLog;
import com.hongguaninfo.hgdf.wadp.core.base.BasePage;
import com.hongguaninfo.hgdf.wadp.core.exception.BizException;
import com.hongguaninfo.hgdf.wadp.core.interceptor.RepeatSubmitToken;
import com.hongguaninfo.hgdf.wadp.core.templete.HttpTemplete;
import com.hongguaninfo.hgdf.wadp.core.templete.OperateTemplete;
import com.hongguaninfo.hgdf.wadp.entity.sys.SysAuthOper;
import com.hongguaninfo.hgdf.wadp.service.sys.SysAuthOperService;
import com.hongguaninfo.hgdf.wadp.shiro.conf.Auths;
import com.hongguaninfo.hgdf.core.utils.exception.BaseException;

@Controller
@RequestMapping("/sys/authoper")
public class SysAuthOperController {

    @Autowired
    private SysAuthOperService sysAuthOperBiz;

    @RequestMapping(value = "/show")
    @RequiresPermissions(Auths.SYS_ROLE)
    public String show(HttpServletRequest request,
            HttpServletResponse response, Model model) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                str = "sys/sysOper_show";
            }
        };
        return templete.operateModel();
    }

    @RequestMapping("/list")
    @ResponseBody
    @RequiresPermissions(Auths.SYS_ROLE)
    public Map getList(final SysAuthOper vo, final BasePage pageRequest,
            HttpServletResponse response, HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
                sysAuthOperBiz.getSysAuthOperList(pageRequest, vo, map);
            }
        };
        return templete.operate();
    }

    @RequestMapping(value = "/sysAuthOperDetail/{operId}")
    public String showSysAuthOperDetail(@PathVariable int operId,
            HttpServletRequest request, HttpServletResponse response,
            Model model) throws BizException {
        model.addAttribute("operId", operId);
        model.addAttribute("authOper",
                sysAuthOperBiz.getSysAuthOperById(operId));
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                str = "sys/sysAuthOper_detail";
            }
        };
        return templete.operateModel();
    }

    @RequestMapping("/add")
    @ResponseBody
    @RepeatSubmitToken(removeToken = true)
    @RequiresPermissions(Auths.SYS_ROLE_ADD)
    @UserLog(code = "addSysAuthOper", name = "增加操作类型", remarkClass = SysAuthOper.class)
    public Map addSysAuthOper(@Valid final SysAuthOper vo,
            BindingResult result, HttpServletResponse response,
            final HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request, result) {
            protected void doSomething() throws BizException {
                if (doValidate()) {
                    sysAuthOperBiz.insertAuthOper(vo);
                }
            }
        };
        return templete.operate();
    }

    @RequestMapping("/edit")
    @ResponseBody
    @RequiresPermissions(Auths.SYS_ROLE_EDIT)
    @UserLog(code = "editSysAuthOper", name = "修改操作类型", remarkClass = SysAuthOper.class)
    public Map editSysAuthOper(@Valid final SysAuthOper vo,
            HttpServletResponse response, final HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
                if (doValidate()) {
                    sysAuthOperBiz.updateAuthOper(vo);
                }

            }
        };
        return templete.operate();
    }

    @RequestMapping("/delete")
    @ResponseBody
    @RequiresPermissions(Auths.SYS_ROLE_DELETE)
    @UserLog(code = "deleteSysAuthOper", name = "删除操作类型", remarkClass = Integer.class)
    public Map deleteSysAuthOper(@Valid final Integer id,
            HttpServletResponse response, final HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
                sysAuthOperBiz.deleteById(id);

            }
        };
        return templete.operate();
    }

}
