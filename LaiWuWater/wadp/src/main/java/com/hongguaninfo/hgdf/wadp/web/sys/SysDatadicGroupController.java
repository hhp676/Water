package com.hongguaninfo.hgdf.wadp.web.sys;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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
import com.hongguaninfo.hgdf.wadp.entity.sys.SysDatadicGroup;
import com.hongguaninfo.hgdf.wadp.service.sys.SysDatadicGroupService;
import com.hongguaninfo.hgdf.core.utils.exception.BaseException;

@Controller
@RequestMapping("/sys/datadicgroup")
public class SysDatadicGroupController {

    @Autowired
    private SysDatadicGroupService sysDatadicGroupBiz;

    @RequestMapping(value = "/showSysDatadicGroup")
    public String showSysDatadicGroup(HttpServletRequest request,
            HttpServletResponse response, Model model) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                str = "sys/sysDatadicGroup_show";
            }
        };
        return templete.operateModel();
    }

    @RequestMapping(value = "/sysDatadicGroupDetail/{groupId}")
    public String showSysDatadicGroupDetail(@PathVariable int groupId,
            HttpServletRequest request, HttpServletResponse response,
            Model model) {
        model.addAttribute("groupId", groupId);
        model.addAttribute("groupObj",
                sysDatadicGroupBiz.getDatadicGroupById(groupId));
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                str = "sys/sysDatadicGroup_detail";
            }
        };
        return templete.operateModel();
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map getSysDatadicGroupList(final SysDatadicGroup vo,
            final BasePage pageRequest, HttpServletResponse response,
            HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
                sysDatadicGroupBiz.getSysDatadicGroupList(pageRequest, vo, map);
            }
        };
        return templete.operate();
    }

    @RequestMapping("/add")
    @ResponseBody
    @RepeatSubmitToken(removeToken = true)
    @UserLog(code = "addSysDatadicGroup", name = "增加字典组", remarkClass = SysDatadicGroup.class)
    public Map addSysDatadicGroup(@Valid final SysDatadicGroup vo,
            BindingResult result, HttpServletResponse response,
            final HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request, result) {
            protected void doSomething() throws BizException {
                sysDatadicGroupBiz.insertDatadicGroup(vo);
            }
        };
        return templete.operate();
    }

    @RequestMapping("/edit")
    @ResponseBody
    @UserLog(code = "editSysDatadicGroup", name = "修改字典组", remarkClass = SysDatadicGroup.class)
    public Map editSysDatadicGroup(final SysDatadicGroup vo,
            HttpServletResponse response, final HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
                sysDatadicGroupBiz.updateDatadicGroup(vo);
            }
        };
        return templete.operate();
    }

    @RequestMapping("/delete")
    @ResponseBody
    @UserLog(code = "deleteSysDatadicGroup", name = "删除字典组", remarkClass = Integer.class)
    public Map deleteSysDatadicGroup(final Integer id,
            HttpServletResponse response, final HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
                sysDatadicGroupBiz.deleteById(id);
            }
        };
        return templete.operate();
    }

}
