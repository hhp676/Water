package com.hongguaninfo.hgdf.adp.web.sys;

import java.util.Date;
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
import com.hongguaninfo.hgdf.adp.entity.sys.SysNotify;
import com.hongguaninfo.hgdf.adp.service.sys.SysDatadicItemService;
import com.hongguaninfo.hgdf.adp.service.sys.SysNotifyService;
import com.hongguaninfo.hgdf.adp.shiro.conf.Auths;
import com.hongguaninfo.hgdf.core.utils.exception.BaseException;

@Controller
@RequestMapping("/sys/notify")
public class SysNotifyController {

    @Autowired
    private SysNotifyService sysNotifyService;
    
    @Autowired
    private SysDatadicItemService sysDatadicItemService;

    @RequestMapping(value = "/showSysNotify")
    @RequiresPermissions(Auths.SYS_NOTIFY)
    public String showSysNotify(HttpServletRequest request,
            HttpServletResponse response, Model model) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                str = "sys/sysNotify_show";
            }
        };
        return templete.operateModel();
    }

    @RequestMapping(value = "/sysNotifyDetail/{notifyId}")
    @RequiresPermissions(Auths.SYS_NOTIFY_VIEW)
    public String showSysNotifyDetail(@PathVariable int notifyId,
            HttpServletRequest request, HttpServletResponse response,
            Model model) throws BizException {
        model.addAttribute("notifyId", notifyId);
        model.addAttribute("sysNotify",sysNotifyService.getSysNotifyById(notifyId));
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                str = "sys/sysNotify_detail";
            }
        };
        return templete.operateModel();
    }
    
    @RequestMapping(value = "/indexView/{notifyId}")
    public String showIndexView(@PathVariable int notifyId,
            HttpServletRequest request, HttpServletResponse response,
            Model model) throws BizException {
        model.addAttribute("sysNotify",
        		sysNotifyService.getSysNotifyById(notifyId));
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                str = "sys/sysNotify_indexView";
            }
        };
        return templete.operateModel();
    }

    @RequestMapping("/list")
    @ResponseBody
    @RequiresPermissions(Auths.SYS_NOTIFY)
    public Map getSysNotifyList(final SysNotify vo, final BasePage pageRequest,
            HttpServletResponse response, HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
            	sysNotifyService.getSysNotifyList(pageRequest, vo, map);
            }
        };
        return templete.operate();
    }

    //************************************************************************************
    
    @RequestMapping("/indexUserNotify")
    @ResponseBody
    public Map getIndexUserNotify(final BasePage pageRequest,
            HttpServletResponse response, HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
            	map.put("userNotify",sysNotifyService.getIndexUserNotify());
            }
        };
        return templete.operate();
    }
    
    @RequestMapping("/readUserNotify/{notifyId}")
    @ResponseBody
    public Map doReadUserNotify(@PathVariable final int notifyId, final BasePage pageRequest,
            HttpServletResponse response, HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
            	SysNotify vo = new SysNotify();
            	vo.setNotifyId(notifyId);
            	vo.setIsRead(1);
            	vo.setReadTime(new Date());
            	sysNotifyService.updateNotify(vo);
            }
        };
        return templete.operate();
    }
   
    /**
     * 任务统计
     */
    @RequestMapping(value = "/myNotify/count")
    @ResponseBody
    public Map countMyNotify(HttpServletResponse response,
            final HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
                sysNotifyService.doCountMyNotify(map);
            }
        };
        return templete.operate();
    }    

}
