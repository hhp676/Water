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
import com.hongguaninfo.hgdf.adp.entity.sys.SysNotice;
import com.hongguaninfo.hgdf.adp.service.sys.SysDatadicItemService;
import com.hongguaninfo.hgdf.adp.service.sys.SysNoticeService;
import com.hongguaninfo.hgdf.adp.shiro.conf.Auths;
import com.hongguaninfo.hgdf.core.utils.exception.BaseException;

@Controller
@RequestMapping("/sys/notice")
public class SysNoticeController {

    @Autowired
    private SysNoticeService sysNoticeBiz;
    
    @Autowired
    private SysDatadicItemService sysDatadicItemService;

    @RequestMapping(value = "/showSysNotice")
    @RequiresPermissions(Auths.SYS_NOTICE)
    public String showSysNotice(HttpServletRequest request,
            HttpServletResponse response, Model model) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                str = "sys/sysNotice_show";
            }
        };
        return templete.operateModel();
    }

    @RequestMapping(value = "/sysNoticeDetail/{noticeId}")
    @RequiresPermissions(Auths.SYS_NOTICE_VIEW)
    public String showSysNoticeDetail(@PathVariable int noticeId,
            HttpServletRequest request, HttpServletResponse response,
            Model model) throws BizException {
    	 model.addAttribute("logicMap",sysDatadicItemService.getMapByGroupCode("LOGIC_TAG"));
        model.addAttribute("noticeId", noticeId);
        model.addAttribute("sysNotice",
        		sysNoticeBiz.getSysNoticeById(noticeId));
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                str = "sys/sysNotice_detail";
            }
        };
        return templete.operateModel();
    }
    
    @RequestMapping(value = "/indexView/{noticeId}")
    public String showIndexView(@PathVariable int noticeId,
            HttpServletRequest request, HttpServletResponse response,
            Model model) throws BizException {
        model.addAttribute("sysNotice",
        		sysNoticeBiz.getSysNoticeById(noticeId));
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                str = "sys/sysNotice_indexView";
            }
        };
        return templete.operateModel();
    }
    
    

    @RequestMapping("/list")
    @ResponseBody
    @RequiresPermissions(Auths.SYS_NOTICE)
    public Map getSysNoticeList(final SysNotice vo, final BasePage pageRequest,
            HttpServletResponse response, HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
            	sysNoticeBiz.getSysNoticeList(pageRequest, vo, map);
            }
        };
        return templete.operate();
    }

    @RequestMapping("/add")
    @ResponseBody
    @RepeatSubmitToken(removeToken = true)
    @RequiresPermissions(Auths.SYS_NOTICE_ADD)
    @UserLog(code = "addSysNotice", name = "新建系统公告", remarkClass = SysNotice.class)
    public Map addSysNotice(@Valid final SysNotice vo, BindingResult result,
            HttpServletResponse response, final HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request, result) {
            protected void doSomething() throws BizException {
                if (doValidate()) {
                	sysNoticeBiz.insertNotice(vo);

                }
            }
        };
        return templete.operate();
    }

    @RequestMapping("/edit")
    @ResponseBody
    @RequiresPermissions(Auths.SYS_NOTICE_EDIT)
    @UserLog(code = "editSysNotice", name = "修改系统公告", remarkClass = SysNotice.class)
    public Map editSysNotice(final SysNotice vo, HttpServletResponse response,
            final HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
            	sysNoticeBiz.updateNotice(vo);
            }
        };
        return templete.operate();
    }

    @RequestMapping("/delete")
    @ResponseBody
    @RequiresPermissions(Auths.SYS_NOTICE_DELETE)
    @UserLog(code = "deleteSysNotice", name = "删除系统公告", remarkClass = Integer.class)
    public Map deleteSysNotice(final Integer id, HttpServletResponse response,
            final HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
            	sysNoticeBiz.deleteById(id);
            }
        };
        return templete.operate();
    }
    
    @RequestMapping("/publishOrNot")
    @ResponseBody
    @UserLog(code = "publishOrNot", name = "发布或取消发布", remarkClass = Integer.class)
    public Map publishOrNot(final Integer id, HttpServletResponse response,
            final HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
            	sysNoticeBiz.doPublishOrNot(id);
            }
        };
        return templete.operate();
    }
    
    
    @RequestMapping("/indexNoticeList")
    @ResponseBody
    public Map getIndexNoticeList(final HttpServletResponse response, final HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
            	map.put("list", sysNoticeBiz.getIndexNoticeList());
            }
        };
        return templete.operate();
    }

}
