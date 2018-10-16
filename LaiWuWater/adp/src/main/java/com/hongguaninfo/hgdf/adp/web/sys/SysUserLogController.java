package com.hongguaninfo.hgdf.adp.web.sys;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hongguaninfo.hgdf.adp.core.base.BasePage;
import com.hongguaninfo.hgdf.adp.core.exception.BizException;
import com.hongguaninfo.hgdf.adp.core.templete.HttpTemplete;
import com.hongguaninfo.hgdf.adp.core.templete.OperateTemplete;
import com.hongguaninfo.hgdf.adp.core.utils.SessionUtils;
import com.hongguaninfo.hgdf.adp.entity.sys.SysUserLog;
import com.hongguaninfo.hgdf.adp.service.sys.SysUserLogService;
import com.hongguaninfo.hgdf.adp.shiro.conf.Auths;
import com.hongguaninfo.hgdf.core.utils.exception.BaseException;

@Controller
@RequestMapping("/sys/userLog")
public class SysUserLogController {

    @Autowired
    private SysUserLogService sysUserLogService;

    @RequestMapping(value = "/showSysUserLog")
    @RequiresPermissions(Auths.SYS_USER_LOG)
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
    @RequiresPermissions(Auths.SYS_USER_LOG)//权限控制
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

    /**
     * 用户登录日志信息列表
     * @param pageRequest
     * @param response
     * @param request
     * @return
     */
    @RequestMapping("/userLogList")
    @ResponseBody
    public Map getUserLogList(final BasePage pageRequest, HttpServletResponse response,
            HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
            	SysUserLog vo  = new SysUserLog();
            	vo.setUserId(SessionUtils.getUserId());
                sysUserLogService.getSysUserLogList(pageRequest, vo, map);
            }
        };
        return templete.operate();
    }
    
    @RequestMapping("/sysUserLogDetail/{logId}")
    @RequiresPermissions(Auths.SYS_USER_LOG_VIEW)//权限控制
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
