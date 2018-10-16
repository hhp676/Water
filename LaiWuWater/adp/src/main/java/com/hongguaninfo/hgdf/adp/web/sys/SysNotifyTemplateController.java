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
import com.hongguaninfo.hgdf.adp.entity.sys.SysNotifyTemplate;
import com.hongguaninfo.hgdf.adp.service.sys.SysDatadicItemService;
import com.hongguaninfo.hgdf.adp.service.sys.SysNotifyTemplateService;
import com.hongguaninfo.hgdf.adp.shiro.conf.Auths;
import com.hongguaninfo.hgdf.core.utils.exception.BaseException;

@Controller
@RequestMapping("/sys/notifyTemplate")
public class SysNotifyTemplateController {

    @Autowired
    private SysNotifyTemplateService sysNotifyTemplateService;
    
    @Autowired
    private SysDatadicItemService sysDatadicItemService;

    @RequestMapping(value = "/showSysNotifyTemplate")
    @RequiresPermissions(Auths.SYS_NOTIFYTEMPLATE)
    public String showSysNotifyTemplate(HttpServletRequest request,
            HttpServletResponse response, Model model) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                str = "sys/sysNotifyTemplate_show";
            }
        };
        return templete.operateModel();
    }

    @RequestMapping(value = "/sysNotifyTemplateDetail/{tempId}")
    @RequiresPermissions(Auths.SYS_NOTIFYTEMPLATE_VIEW)
    public String showSysNotifyTemplateDetail(@PathVariable int tempId,
            HttpServletRequest request, HttpServletResponse response,
            Model model) throws BizException {
        model.addAttribute("tempId", tempId);
        model.addAttribute("sysNotifyTemplate",sysNotifyTemplateService.getSysNotifyTemplateById(tempId));
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                str = "sys/sysNotifyTemplate_detail";
            }
        };
        return templete.operateModel();
    }

    @RequestMapping("/list")
    @ResponseBody
    @RequiresPermissions(Auths.SYS_NOTIFYTEMPLATE)
    public Map getSysNotifyTemplateList(final SysNotifyTemplate vo, final BasePage pageRequest,
            HttpServletResponse response, HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
            	sysNotifyTemplateService.getSysNotifyTemplateList(pageRequest, vo, map);
            }
        };
        return templete.operate();
    }

    @RequestMapping("/add")
    @ResponseBody
    @RepeatSubmitToken(removeToken = true)
    @RequiresPermissions(Auths.SYS_NOTIFYTEMPLATE_ADD)
    @UserLog(code = "addSysNotifyTemplate", name = "新建通知模板", remarkClass = SysNotifyTemplate.class)
    public Map addSysNotifyTemplate(@Valid final SysNotifyTemplate vo, BindingResult result,
            HttpServletResponse response, final HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request, result) {
            protected void doSomething() throws BizException {
                if (doValidate()) {
                	sysNotifyTemplateService.insertNotifyTemplate(vo);

                }
            }
        };
        return templete.operate();
    }

    @RequestMapping("/edit")
    @ResponseBody
    @RequiresPermissions(Auths.SYS_NOTIFYTEMPLATE_EDIT)
    @UserLog(code = "editSysNotifyTemplate", name = "修改通知模板", remarkClass = SysNotifyTemplate.class)
    public Map editSysNotifyTemplate(final SysNotifyTemplate vo, HttpServletResponse response,
            final HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
            	sysNotifyTemplateService.updateNotifyTemplate(vo);
            }
        };
        return templete.operate();
    }

    @RequestMapping("/delete")
    @ResponseBody
    @RequiresPermissions(Auths.SYS_NOTIFYTEMPLATE_DELETE)
    @UserLog(code = "deleteSysNotifyTemplate", name = "删除通知模板", remarkClass = Integer.class)
    public Map deleteSysNotifyTemplate(final Integer id, HttpServletResponse response,
            final HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
            	sysNotifyTemplateService.deleteById(id);
            }
        };
        return templete.operate();
    }
    

}
