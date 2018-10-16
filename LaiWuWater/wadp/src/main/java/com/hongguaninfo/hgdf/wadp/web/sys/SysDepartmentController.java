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
import com.hongguaninfo.hgdf.wadp.core.utils.SessionUtils;
import com.hongguaninfo.hgdf.wadp.entity.sys.SysDepartment;
import com.hongguaninfo.hgdf.wadp.entity.sys.SysUser;
import com.hongguaninfo.hgdf.wadp.service.sys.SysDepartmentService;
import com.hongguaninfo.hgdf.wadp.shiro.conf.Auths;
import com.hongguaninfo.hgdf.core.utils.exception.BaseException;

@Controller
@RequestMapping("/sys/department")
public class SysDepartmentController {

    @Autowired
    private SysDepartmentService sysDepartmentService;

    @RequestMapping(value = "/showSysDepartment")
    @RequiresPermissions(Auths.SYS_DEPARTMENT)
    public String showSysAuth(HttpServletRequest request,
            HttpServletResponse response, Model model) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                str = "sys/sysDepartment_show";
            }
        };
        return templete.operateModel();
    }

    @RequestMapping(value = "/sysDepartmentDetail/{departId}")
    public String showSysDeptEditDetail(@PathVariable Integer departId,
            HttpServletRequest request, HttpServletResponse response,
            Model model) {
        model.addAttribute("sign", 1);
        model.addAttribute("departId", departId);
        SysDepartment vo = sysDepartmentService.getSysDepartmentById(departId);
        model.addAttribute("departObj",
                sysDepartmentService.getSysDepartmentById(departId));
        model.addAttribute("deptName",
                sysDepartmentService.getFnodeNameByFid(vo.getFid()));
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                str = "sys/sysDepartment_detail";
            }
        };
        return templete.operateModel();
    }

    @RequestMapping(value = "/sysDepartmentAddDetail/{departId}")
    public String showSysDeptAddDetail(@PathVariable Integer departId,
            HttpServletRequest request, HttpServletResponse response,
            Model model) {
        SysDepartment dept = sysDepartmentService
                .getSysDepartmentById(departId);
        model.addAttribute("sign", 0);
        model.addAttribute("departId", departId);
        model.addAttribute("deptName", dept.getDepartName());
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                str = "sys/sysDepartment_detail";
            }
        };
        return templete.operateModel();
    }

    @RequestMapping("/list")
    @ResponseBody
    @RequiresPermissions(Auths.SYS_DEPARTMENT)
    public Map getSysVarList(final SysDepartment vo,
            final BasePage pageRequest, HttpServletResponse response,
            HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
                sysDepartmentService.getDeptList(pageRequest, vo, map);
            }
        };
        return templete.operate();
    }

    @RequestMapping("/add")
    @ResponseBody
    @RepeatSubmitToken(removeToken = true)
    @RequiresPermissions(Auths.SYS_DEPARTMENT_ADD)
    @UserLog(code = "addSysDepartment", name = "增加部门", remarkClass = SysDepartment.class)
    public Map addSysDepartment(@Valid final SysDepartment vo,
            BindingResult result, HttpServletResponse response,
            final HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request, result) {
            protected void doSomething() throws BizException {
                SysUser user = SessionUtils.getUser(request);
                if (doValidate()) {
                    sysDepartmentService.insertDept(vo, user.getUserId());
                }
            }
        };
        return templete.operate();
    }

    @RequestMapping("/edit")
    @ResponseBody
    @RequiresPermissions(Auths.SYS_DEPARTMENT_EDIT)
    @UserLog(code = "editSysDepartment", name = "修改部门", remarkClass = SysDepartment.class)
    public Map editSysDepartment(final SysDepartment vo,
            HttpServletResponse response, final HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
                SysUser user = SessionUtils.getUser(request);
                sysDepartmentService.updateDept(vo, user.getUserId());
            }
        };
        return templete.operate();
    }

    @RequestMapping("/delete")
    @ResponseBody
    @RequiresPermissions(Auths.SYS_DEPARTMENT_DELETE)
    @UserLog(code = "deleteSysDepartment", name = "删除部门", remarkClass = Integer.class)
    public Map deleteSysDepartment(final Integer id,
            HttpServletResponse response, final HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
                SysUser user = SessionUtils.getUser(request);
                sysDepartmentService.deleteDeptById(id, user.getUserId());
            }
        };
        return templete.operate();
    }

}
