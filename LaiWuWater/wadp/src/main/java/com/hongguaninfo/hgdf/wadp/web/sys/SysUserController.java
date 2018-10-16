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
import com.hongguaninfo.hgdf.wadp.core.base.BaseController;
import com.hongguaninfo.hgdf.wadp.core.base.BasePage;
import com.hongguaninfo.hgdf.wadp.core.exception.BizException;
import com.hongguaninfo.hgdf.wadp.core.interceptor.RepeatSubmitToken;
import com.hongguaninfo.hgdf.wadp.core.templete.HttpTemplete;
import com.hongguaninfo.hgdf.wadp.core.templete.OperateTemplete;
import com.hongguaninfo.hgdf.wadp.core.utils.SessionUtils;
import com.hongguaninfo.hgdf.wadp.entity.sys.SysUser;
import com.hongguaninfo.hgdf.wadp.service.sys.SysDatadicItemService;
import com.hongguaninfo.hgdf.wadp.service.sys.SysUserAuthJoinService;
import com.hongguaninfo.hgdf.wadp.service.sys.SysUserRoleJoinService;
import com.hongguaninfo.hgdf.wadp.service.sys.SysUserService;
import com.hongguaninfo.hgdf.wadp.service.sys.SysUserUgroupJoinService;
import com.hongguaninfo.hgdf.wadp.shiro.conf.Auths;
import com.hongguaninfo.hgdf.core.utils.exception.BaseException;

@Controller
@RequestMapping("/sys/sysUser")
public class SysUserController extends BaseController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysUserAuthJoinService sysUserAuthJoinService;

    @Autowired
    private SysUserRoleJoinService sysUserRoleJoinService;
    
    @Autowired
    private SysUserUgroupJoinService sysUserUgroupJoinService;

    @Autowired
    private SysDatadicItemService sysDatadicItemService;

    @RequestMapping(value = "/showSysUser")
    @RequiresPermissions(Auths.SYS_USRR)
    public String showSysUser(HttpServletRequest request,
            HttpServletResponse response, Model model) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                str = "sys/sysUser_show";
            }
        };
        return templete.operateModel();
    }

    @RequestMapping(value = "/sysUserDetail/{userId}")
    public String showSysVarDetail(@PathVariable int userId,
            HttpServletRequest request, HttpServletResponse response,
            Model model) throws BizException {
        model.addAttribute("sysUser", sysUserService.getUser(userId));
        model.addAttribute("sexMap",
                sysDatadicItemService.getMapByGroupCode("SEX"));
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                str = "sys/sysUser_detail";
            }
        };
        return templete.operateModel();
    }

    @RequestMapping(value = "/showChangePwd/{userId}")
    public String showChangePwd(@PathVariable int userId,
            HttpServletRequest request, HttpServletResponse response,
            Model model) throws BizException {
        model.addAttribute("userId", userId);
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                str = "sys/sysUser_changePwd";
            }
        };
        return templete.operateModel();
    }

    @RequestMapping("/list")
    @ResponseBody
    @RequiresPermissions(Auths.SYS_USRR)
    public Map getSysRoleList(final SysUser vo, final BasePage basePage,
            HttpServletResponse response, HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
                sysUserService.getSysUserList(basePage, vo, map);
            }
        };
        return templete.operate();
    }

    @RequestMapping("/add")
    @ResponseBody
    @RepeatSubmitToken(removeToken = true)
    @RequiresPermissions(Auths.SYS_USRR_ADD)
    @UserLog(code = "addSysRole", name = "增加用户", remarkClass = SysUser.class)
    public Map addSysRole(@Valid final SysUser vo, BindingResult result,
            HttpServletResponse response, final HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request, result) {
            protected void doSomething() throws BizException {
                if (doValidate()) {
                    sysUserService.insertUser(vo);
                }
            }
        };
        return templete.operate();
    }

    @RequestMapping("/edit")
    @ResponseBody
    @RequiresPermissions(Auths.SYS_USRR_EDIT)
    @UserLog(code = "editSysUser", name = "修改用户", remarkClass = SysUser.class)
    public Map editSysUser(final SysUser vo, HttpServletResponse response,
            final HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
                if (doValidate()) {
                    sysUserService.updateUserWithJoin(vo);
                }

            }
        };
        return templete.operate();
    }

    @RequestMapping("/delete")
    @ResponseBody
    @RequiresPermissions(Auths.SYS_USRR_DELETE)
    @UserLog(code = "deleteSysUser", name = "删除用户", remarkClass = Integer.class)
    public Map deleteSysUser(final Integer id, HttpServletResponse response,
            final HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
                sysUserService.deleteById(id);
            }
        };
        return templete.operate();
    }

    @RequestMapping(value = "/editLoginUser")
    @ResponseBody
    @UserLog(code = "updateLoginUser", name = "更新登录用户信息", remarkClass = SysUser.class)
    public Map updateLoginUser(final SysUser sysUser, HttpServletRequest request) {
        LOG.debug("-------------------------------------------------------》editLoginUser");
        HttpTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                sysUser.setUserId(SessionUtils.getUserId());
                sysUserService.updateUser(sysUser);
            }
        };
        return templete.operate();
    }

    @RequestMapping(value = "/changePwd")
    @ResponseBody
    @UserLog(code = "changePwd", name = "修改密码", remarkClass = String.class)
    public Map changePwd(final String oldPwd, final String newPwd,
            HttpServletRequest request) {
        HttpTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                sysUserService.changePwd(oldPwd, newPwd);
            }
        };
        return templete.operate();
    }

    @RequestMapping(value = "/adminChangePwd")
    @ResponseBody
    @UserLog(code = "adminChangePwd", name = "系统管理员改密", remarkClass = String.class)
    public Map adminChangePwd(final String newPwd, final int userId,
            HttpServletRequest request) {
        HttpTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                sysUserService.adminChangePwd(newPwd, userId);
            }
        };
        return templete.operate();
    }

    @RequestMapping("/authList")
    @ResponseBody
    public Map getSysAuthList(final int userId, final BasePage basePage,
            HttpServletResponse response, HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
                map.put("list", sysUserAuthJoinService.getListByUserId(userId));
            }
        };
        return templete.operate();
    }

    @RequestMapping("/roleList")
    @ResponseBody
    public Map getSysRoleList(final int userId, final BasePage basePage,
            HttpServletResponse response, HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
                map.put("list", sysUserRoleJoinService.getListByUserId(userId));
            }
        };
        return templete.operate();
    }
    
    @RequestMapping("/ugroupList")
    @ResponseBody
    public Map getSysUgroupList(final int userId, final BasePage basePage,
            HttpServletResponse response, HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
                map.put("list", sysUserUgroupJoinService.getListByUserId(userId));
            }
        };
        return templete.operate();
    }
    
    

}