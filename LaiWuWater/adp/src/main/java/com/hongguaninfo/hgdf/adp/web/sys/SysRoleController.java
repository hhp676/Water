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
import com.hongguaninfo.hgdf.adp.core.base.BasePage;
import com.hongguaninfo.hgdf.adp.core.exception.BizException;
import com.hongguaninfo.hgdf.adp.core.interceptor.RepeatSubmitToken;
import com.hongguaninfo.hgdf.adp.core.templete.HttpTemplete;
import com.hongguaninfo.hgdf.adp.core.templete.OperateTemplete;
import com.hongguaninfo.hgdf.adp.entity.sys.SysRole;
import com.hongguaninfo.hgdf.adp.service.sys.SysRoleAuthJoinService;
import com.hongguaninfo.hgdf.adp.service.sys.SysRoleService;
import com.hongguaninfo.hgdf.adp.shiro.conf.Auths;
import com.hongguaninfo.hgdf.core.utils.exception.BaseException;

@Controller
@RequestMapping("/sys/role")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleBiz;

    @Autowired
    private SysRoleAuthJoinService sysRoleAuthJoinService;

    @RequestMapping(value = "/showSysRole")
    @RequiresPermissions(Auths.SYS_ROLE)
    public String showSysRole(HttpServletRequest request,
            HttpServletResponse response, Model model) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                str = "sys/sysRole_show";
            }
        };
        return templete.operateModel();
    }

    @RequestMapping(value = "/sysRoleDetail/{roleId}")
    @RequiresPermissions(Auths.SYS_ROLE_VIEW)
    public String showSysVarDetail(@PathVariable int roleId,
            HttpServletRequest request, HttpServletResponse response,
            Model model) throws BizException {
        model.addAttribute("roleId", roleId);
        model.addAttribute("sysRole", sysRoleBiz.getSysRoleById(roleId));
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                str = "sys/sysRole_detail";
            }
        };
        return templete.operateModel();
    }

    @RequestMapping("/list")
    @ResponseBody
    @RequiresPermissions(Auths.SYS_ROLE)
    public Map getSysRoleList(final SysRole vo, final BasePage basePage,
            HttpServletResponse response, HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
                sysRoleBiz.getSysRoleList(basePage, vo, map);
            }
        };
        return templete.operate();
    }

    @RequestMapping("/addInfoList")
    @ResponseBody
    public Map addInfoList(final SysRole vo, final BasePage basePage,
            HttpServletResponse response, HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
                sysRoleBiz.getSysRoleList(basePage, vo, map);
            }
        };
        return templete.operate();
    }
    
    /**
	 * 角色对应人员列表 页面
	 * 
	 */
	@RequestMapping(value = "/userByRoleListShow/{roleId}")
	public String getUserShowByRoleId(@PathVariable final Integer roleId,final SysRole sys ,final HttpServletRequest request,HttpServletResponse response, final Model model) throws BizException {
		OperateTemplete templete = new HttpTemplete(request) {
			protected void doSomething() throws BaseException {
				model.addAttribute("roleId", roleId);
				str = "sys/sysUserRoleJoin_show";
			}
		};
		return templete.operateModel();
	}
    /**
	 * 角色对应人员列表  数据
	 */
	@RequestMapping("/userByRoleList/{roleId}")
	@ResponseBody
	public Map getUserListByRoleId(@PathVariable final Integer roleId,final SysRole sys ,final BasePage<SysRole> basePage, HttpServletResponse response,final HttpServletRequest request) {
		OperateTemplete templete = new HttpTemplete(request) {
			protected void doSomething() throws BizException {
				sys.setRoleId(new BigDecimal(roleId));
				sysRoleBiz.getUserRoleJoinList(basePage,sys,map);
			}
		}; 
		return templete.operate();
	}
    @RequestMapping("/add")
    @RequiresPermissions(Auths.SYS_ROLE_ADD)
    @ResponseBody
    @RepeatSubmitToken(removeToken = true)
    @UserLog(code = "addSysRole", name = "增加角色", remarkClass = SysRole.class)
    public Map addSysRole(@Valid final SysRole vo, BindingResult result,
            HttpServletResponse response, final HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request, result) {
            protected void doSomething() throws BizException {
                if (doValidate()) {
                    sysRoleBiz.insertRole(vo);
                }
            }
        };
        return templete.operate();
    }

    @RequestMapping("/edit")
    @RequiresPermissions(Auths.SYS_ROLE_EDIT)
    @ResponseBody
    @UserLog(code = "editSysRole", name = "修改角色", remarkClass = SysRole.class)
    public Map editSysRole(final SysRole vo, HttpServletResponse response,
            final HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
                if (doValidate()) {
                    sysRoleBiz.updateRole(vo);
                }

            }
        };
        return templete.operate();
    }

    @RequestMapping("/delete")
    @RequiresPermissions(Auths.SYS_ROLE_DELETE)
    @ResponseBody
    @UserLog(code = "deleteSysRole", name = "删除角色", remarkClass = Integer.class)
    public Map deleteSysRole(final Integer id, HttpServletResponse response,
            final HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
                sysRoleBiz.deleteById(id);
            }
        };
        return templete.operate();
    }

    @RequestMapping("/authList")
    @ResponseBody
    public Map getSysAuthList(final int roleId, final BasePage basePage,
            HttpServletResponse response, HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
                map.put("list", sysRoleAuthJoinService.getListByRoleId(roleId));
            }
        };
        return templete.operate();
    }

}
