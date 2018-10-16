package com.hongguaninfo.hgdf.adp.web.sys;

import java.math.BigDecimal;
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
import com.hongguaninfo.hgdf.adp.core.exception.BizException;
import com.hongguaninfo.hgdf.adp.core.interceptor.RepeatSubmitToken;
import com.hongguaninfo.hgdf.adp.core.templete.HttpTemplete;
import com.hongguaninfo.hgdf.adp.core.templete.OperateTemplete;
import com.hongguaninfo.hgdf.adp.entity.sys.SysAuth;
import com.hongguaninfo.hgdf.adp.service.sys.SysAuthService;
import com.hongguaninfo.hgdf.adp.service.sys.SysDatadicItemService;
import com.hongguaninfo.hgdf.adp.shiro.conf.Auths;
import com.hongguaninfo.hgdf.core.utils.exception.BaseException;

@Controller
@RequestMapping("/sys/auth")
public class SysAuthController {

    @Autowired
    private SysAuthService sysAuthBiz;

    @Autowired
    private SysDatadicItemService sysDatadicItemService;

    @RequestMapping(value = "/showSysAuth")
    @RequiresPermissions(Auths.SYS_AUTH)
    public String showSysAuth(HttpServletRequest request,
            HttpServletResponse response, Model model) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                str = "sys/sysAuth_show";
            }
        };
        return templete.operateModel();
    }


    @RequestMapping("/list")
    @ResponseBody
    @RequiresPermissions(Auths.SYS_AUTH)
    public Map getSysAuthList(final SysAuth vo, HttpServletResponse response,
            HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
                sysAuthBiz.getSysAuthList(vo, map);
            }
        };
        return templete.operate();
    }
    
    /**
     * 添加其他模块数据时调用
     * @param vo
     * @param response
     * @param request
     * @return
     */
    @RequestMapping("/addInfoList")
    @ResponseBody
    public Map addInfoList(final SysAuth vo, HttpServletResponse response,
            HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
                sysAuthBiz.getSysAuthList(vo, map);
            }
        };
        return templete.operate();
    }
    
    @RequestMapping("/delete")
    @ResponseBody
    @UserLog(code = "deleteSysAuth", name = "删除权限", remarkClass = int.class)
    @RequiresPermissions(Auths.SYS_AUTH_DELETE)
    public Map deleteSysAuth(final int authId, HttpServletResponse response,
            final HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
                sysAuthBiz.deleteByAuthId(authId);
            }
        };
        return templete.operate();
    }

    @RequestMapping(value = "/detail/{authType}/{authId}/{otherId}")
    public String showSysAuthAppDetail(@PathVariable int authId,
            @PathVariable final int authType, @PathVariable int otherId,
            HttpServletRequest request, HttpServletResponse response,
            Model model) throws BizException {
        model.addAttribute("authType", authType);
        SysAuth bo = sysAuthBiz.getSysAuthById(authId);
        if (authId == 0) {
            bo.setFid(new BigDecimal(otherId));
        }
        // 设置父权限名称
        bo.setfName(sysAuthBiz.getSysAuthById(bo.getFid().intValue())
                .getAuthName());
        model.addAttribute("authObj", bo);
        model.addAttribute("logicMap",
                sysDatadicItemService.getMapByGroupCode("LOGIC_TAG"));
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                str = "sys/sysAuth_App_detail";
                if (authType == 1) {
                    str = "sys/sysAuth_Module_detail";
                } else if (authType == 2) {
                    str = "sys/sysAuth_Oper_detail";
                }
            }
        };
        return templete.operateModel();
    }

    @RequestMapping("/add")
    @ResponseBody
    @RepeatSubmitToken(removeToken = true)
    @RequiresPermissions(Auths.SYS_AUTH_ADD)
    @UserLog(code = "addSysAuth", name = "添加权限", remarkClass = SysAuth.class)
    public Map addSysAuth(@Valid final SysAuth vo, BindingResult result,
            HttpServletResponse response, final HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request, result) {
            protected void doSomething() throws BizException {
                if (doValidate()) {
                    sysAuthBiz.insertApp(vo);
                }
            }
        };
        return templete.operate();
    }

    @RequestMapping("/edit")
    @ResponseBody
    @RequiresPermissions(Auths.SYS_AUTH_EDIT)
    @UserLog(code = "editSysAuth", name = "修改权限", remarkClass = SysAuth.class)
    public Map editSysAuth(@Valid final SysAuth vo, BindingResult result,
            HttpServletResponse response, final HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request, result) {
            protected void doSomething() throws BizException {
                if (doValidate()) {
                    sysAuthBiz.updateApp(vo);
                }
            }
        };
        return templete.operate();
    }

}
