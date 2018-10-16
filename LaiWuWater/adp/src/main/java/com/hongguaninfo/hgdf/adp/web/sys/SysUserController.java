package com.hongguaninfo.hgdf.adp.web.sys;

import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSON;
import com.hongguaninfo.hgdf.adp.core.Constants;
import com.hongguaninfo.hgdf.adp.core.aop.log.UserLog;
import com.hongguaninfo.hgdf.adp.core.base.BaseController;
import com.hongguaninfo.hgdf.adp.core.base.BasePage;
import com.hongguaninfo.hgdf.adp.core.exception.BizException;
import com.hongguaninfo.hgdf.adp.core.interceptor.RepeatSubmitToken;
import com.hongguaninfo.hgdf.adp.core.templete.HttpTemplete;
import com.hongguaninfo.hgdf.adp.core.templete.OperateTemplete;
import com.hongguaninfo.hgdf.adp.core.utils.ResponseUtils;
import com.hongguaninfo.hgdf.adp.core.utils.SessionUtils;
import com.hongguaninfo.hgdf.adp.entity.sys.SysConfig;
import com.hongguaninfo.hgdf.adp.entity.sys.SysUser;
import com.hongguaninfo.hgdf.adp.entity.sys.SysUserLog;
import com.hongguaninfo.hgdf.adp.service.sys.SysConfigService;
import com.hongguaninfo.hgdf.adp.service.sys.SysDatadicItemService;
import com.hongguaninfo.hgdf.adp.service.sys.SysUserAuthJoinService;
import com.hongguaninfo.hgdf.adp.service.sys.SysUserLogService;
import com.hongguaninfo.hgdf.adp.service.sys.SysUserRoleJoinService;
import com.hongguaninfo.hgdf.adp.service.sys.SysUserService;
import com.hongguaninfo.hgdf.adp.service.sys.SysUserUgroupJoinService;
import com.hongguaninfo.hgdf.adp.shiro.conf.Auths;
import com.hongguaninfo.hgdf.core.utils.encrypt.RSAUtil;
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
    
    @Autowired
    private SysConfigService sysConfigService;
    
    @Autowired
    private SysUserLogService sysUserLogService;
    


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

    @RequestMapping(value = "/sysUserDetail/{userId}/{mode}")
    public String showSysVarDetail(@PathVariable int userId,@PathVariable String mode,
            HttpServletRequest request, HttpServletResponse response,
            Model model) throws BizException {
        model.addAttribute("sysUser", sysUserService.getUserById(userId,mode));
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
    
    @RequestMapping(value = "/showImportExcel")
    public String showImportExcel(HttpServletRequest request,
            HttpServletResponse response, Model model) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                str = "sys/sysUser_import";
            }
        };
        return templete.operateModel();
    }
    
    @RequestMapping("/importExcel")
    @RequiresPermissions(Auths.SYS_USRR_IMPORT)
	public void importExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String result = "";
		boolean tag = true;//doReadXls返回结果的标识
		try {
			MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
			MultipartHttpServletRequest multipartRequest = resolver.resolveMultipart(request);
			MultipartFile multipartFile = multipartRequest.getFile("excelFile");
			String suffix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
			if (".xls".equals(suffix)) {
				if (!multipartFile.isEmpty()) {
					tag = sysUserService.doReadXls(multipartFile.getInputStream());
					if(tag){
						result = "success";
					}else{
						result = "导入失败，请检查导入文件内容格式是否正确。";
					}
					
				}
				
			} else {
				result = "导入失败,请导入xls文件";
			}		
			
		} catch (Exception ex) {
			result = "导入失败，请检查导入Excel的模板是否符合要求、数据的唯一性是否正确。";
			ex.printStackTrace();
		}
		Map resultMap = new HashMap();
		resultMap.put("result",result);
		ResponseUtils.renderHtml(response, JSON.toJSONString(resultMap), "encoding:utf-8");

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
    @UserLog(code = "addUser", name = "增加用户", remarkClass = SysUser.class)
    public Map addUser(@Valid final SysUser vo, BindingResult result,
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
    
    @RequestMapping("/lock")
    @ResponseBody
    @RequiresPermissions(Auths.SYS_USRR_LOCK)
    @UserLog(code = "doLockAccountManual", name = "手动锁定用户", remarkClass = SysUser.class)
    public Map editSysUserLock(@RequestParam final Integer userId, HttpServletResponse response,
            final HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
            	sysUserService.setLock(userId);
            }
        };
        return templete.operate();
    }
    
    @RequestMapping("/unlock")
    @ResponseBody
    @RequiresPermissions(Auths.SYS_USRR_UNLOCK)
    @UserLog(code = "doUnlockAccountManual", name = "手动解锁用户", remarkClass = SysUser.class)
    public Map editSysUserUnLock(@RequestParam final Integer userId, HttpServletResponse response,
            final HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
            	sysUserService.setUnlock(userId);
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
    @UserLog(code = "changePwd", name = "修改密码" )
    public Map changePwd(final String oldPwd, final String newPwd,
            final HttpServletRequest request) {
        HttpTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                String decryptOldPwd = RSAUtil.decryptStringByJs(oldPwd);
                String decryptNewPwd = RSAUtil.decryptStringByJs(newPwd);
                try{
                SysConfig notUsedRecentCon = sysConfigService.getSysConfigByKey(Constants.UPDPASSWORD_NOTUSEDRECENT);
                //启用密码不能与最近几次修改的相同-开关
                if(StringUtils.isNotBlank(notUsedRecentCon.getConfigValue()) && Constants.UPDPASSWORD_NOTUSEDRECENT_USED.equals(notUsedRecentCon.getConfigValue())){
                	SysConfig notUsedCount = sysConfigService.getSysConfigByKey(Constants.UPDPASSWORD_NOTUSEDCOUNT);
                	if(StringUtils.isNotBlank(notUsedCount.getConfigValue())){
                		Integer count = Integer.parseInt(notUsedCount.getConfigValue());
                        sysUserService.changePwd(decryptOldPwd, decryptNewPwd,Constants.PWD_CHANGE_REASON_DEFAULT,count);
                	}
                }else{
                    sysUserService.changePwd(decryptOldPwd, decryptNewPwd,Constants.PWD_CHANGE_REASON_DEFAULT);
                }
                }catch(Exception e){
                	String str = e.getMessage();
            		Map<String,String> messageMap = JSON.parseObject(str, Map.class);
            		if(messageMap.containsKey("field") && messageMap.containsKey("message")){
            			saveUserLog(request,"changePwd "+messageMap.get("field"),messageMap.get("message"),null);
            		}
            		throw new BizException(str);
            	}
            }
        };
        return templete.operate();
    }
    
    @RequestMapping(value = "/changePsatPwd")
    @ResponseBody
    @UserLog(code = "changePsatPwd", name = "修改过期密码" )
    public Map changePastPwd(final String oldPwd, final String newPwd,
            final HttpServletRequest request) {
        HttpTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
            	try{
                String decryptOldPwd = RSAUtil.decryptStringByJs(oldPwd);
                String decryptNewPwd = RSAUtil.decryptStringByJs(newPwd);
                SysConfig notUsedRecentCon = sysConfigService.getSysConfigByKey(Constants.UPDPASSWORD_NOTUSEDRECENT);
                //启用密码不能与最近几次修改的相同-开关
                if(StringUtils.isNotBlank(notUsedRecentCon.getConfigValue()) && Constants.UPDPASSWORD_NOTUSEDRECENT_USED.equals(notUsedRecentCon.getConfigValue())){
                	SysConfig notUsedCount = sysConfigService.getSysConfigByKey(Constants.UPDPASSWORD_NOTUSEDCOUNT);
                	if(StringUtils.isNotBlank(notUsedCount.getConfigValue())){
                		Integer count = Integer.parseInt(notUsedCount.getConfigValue());
                        sysUserService.changePwd(decryptOldPwd, decryptNewPwd,Constants.PWD_CHANGE_REASON_DEFAULT,count);
                	}
                }else{
                    sysUserService.changePwd(decryptOldPwd, decryptNewPwd,Constants.PWD_CHANGE_REASON_DEFAULT);
                }
            	}catch(Exception e){
            		String str = e.getMessage();
            		Map<String,String> messageMap = JSON.parseObject(str, Map.class);
            		if(messageMap.containsKey("field") && messageMap.containsKey("message")){
            			saveUserLog(request,"changePsatPwd "+messageMap.get("field"),messageMap.get("message"),null);
            		}
            		throw new BizException(str);
            	}
            }
        };
        return templete.operate();
    }

    @RequestMapping(value = "/adminChangePwd")
    @ResponseBody
    @UserLog(code = "adminChangePwd", name = "系统管理员改密", remarkClass = String.class)
    @RequiresPermissions(Auths.SYS_USRR_CHANGEPWD)
    public Map adminChangePwd(final String newPwd, final int userId,
            final HttpServletRequest request) {
        HttpTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
            	try{
            		String decryptNewPwd = RSAUtil.decryptStringByJs(newPwd);
            		sysUserService.adminChangePwd(decryptNewPwd, userId);
            	}catch(Exception e){
            		String str = e.getMessage();
            		Map<String,String> messageMap = JSON.parseObject(str, Map.class);
            		if(messageMap.containsKey("field") && messageMap.containsKey("message")){
            			saveUserLog(request,messageMap.get("field"),messageMap.get("message"),null);
            		}
            		throw new BizException(str);
            	}
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
    
    
    private void saveUserLog(ServletRequest request, String code, String name, String remark) {
		SysUserLog sysUserLog = new SysUserLog();
		sysUserLog.setRemark(remark);
		sysUserLog.setOperIP(((HttpServletRequest) request).getRemoteAddr());
		sysUserLog.setCrtTime(new Date());
		sysUserLog.setOperCode(code);
		sysUserLog.setOperName(name);
		sysUserLogService.saveSysUserLog(sysUserLog);
	}
}