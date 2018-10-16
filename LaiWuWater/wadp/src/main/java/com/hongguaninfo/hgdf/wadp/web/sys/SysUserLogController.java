package com.hongguaninfo.hgdf.wadp.web.sys;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hongguaninfo.hgdf.wadp.core.base.BasePage;
import com.hongguaninfo.hgdf.wadp.core.exception.BizException;
import com.hongguaninfo.hgdf.wadp.core.templete.HttpTemplete;
import com.hongguaninfo.hgdf.wadp.core.templete.OperateTemplete;
import com.hongguaninfo.hgdf.wadp.entity.sys.SysUserLog;
import com.hongguaninfo.hgdf.wadp.service.sys.SysUserLogService;
import com.hongguaninfo.hgdf.core.utils.exception.BaseException;

@Controller
@RequestMapping("/sys/userLog")
public class SysUserLogController {

    @Autowired
    private SysUserLogService sysUserLogService;

    @RequestMapping(value = "/showSysUserLog")
    public String showSysUserLog(HttpServletRequest request,
            HttpServletResponse response, Model model) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                /*model.addAttribute(arg0);*/
                str = "sys/sysUserLog_show";
            }
        };
        return templete.operateModel();
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map getSysUserLogList(final SysUserLog vo,
            final BasePage pageRequest, HttpServletResponse response,
            HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
                sysUserLogService.getSysUserLogList(pageRequest, vo, map);
            }
        };
        return templete.operate();
    }

    @RequestMapping("/sysUserLogDetail/{logId}")
    public String getSysUserLogDetail(@PathVariable final Integer logId,
            HttpServletResponse response, HttpServletRequest request,
            Model model) {
        SysUserLog userLog = sysUserLogService.getSysUserLogById(logId);
        model.addAttribute("userLog", userLog);
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
                str = "sys/sysUserLog_detail";
            }
        };
        return templete.operateModel();
    }

}
