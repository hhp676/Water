package com.hongguaninfo.hgdf.adp.web;

import com.google.code.kaptcha.Producer;
import com.hongguaninfo.hgdf.adp.core.Constants;
import com.hongguaninfo.hgdf.adp.core.base.BaseController;
import com.hongguaninfo.hgdf.adp.core.exception.BizException;
import com.hongguaninfo.hgdf.adp.core.interceptor.RepeatSubmitToken;
import com.hongguaninfo.hgdf.adp.core.interceptor.RepeatSubmitUtil;
import com.hongguaninfo.hgdf.adp.core.templete.HttpTemplete;
import com.hongguaninfo.hgdf.adp.core.templete.OperateTemplete;
import com.hongguaninfo.hgdf.adp.core.utils.SessionUtils;
import com.hongguaninfo.hgdf.adp.entity.sys.SysAuth;
import com.hongguaninfo.hgdf.adp.entity.sys.SysAuthModule;
import com.hongguaninfo.hgdf.adp.entity.sys.SysConfig;
import com.hongguaninfo.hgdf.adp.entity.sys.SysToken;
import com.hongguaninfo.hgdf.adp.service.IndexService;
import com.hongguaninfo.hgdf.adp.service.sys.*;
import com.hongguaninfo.hgdf.core.utils.SmsUtil;
import com.hongguaninfo.hgdf.core.utils.encrypt.RSAUtil;
import com.hongguaninfo.hgdf.core.utils.exception.BaseException;
import com.hongguaninfo.hgdf.core.utils.logging.Log;
import com.hongguaninfo.hgdf.core.utils.logging.LogFactory;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.security.interfaces.RSAPublicKey;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/")
public class IndexController extends BaseController {

    public static final Log LOG = LogFactory.getLog(IndexController.class);

    @Autowired
    private Producer captchaProducer;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysConfigService sysConfigService;

    @Autowired
    private SysUserUgroupJoinService sysUserUgroupJoinService;

    @Autowired
    private SysUserRoleJoinService sysUserRoleJoinService;

    @Autowired
    private IndexService indexService;
    @Autowired
    private SysDatadicItemService sysDatadicItemService;
    
    /**
     * @throws BizException 
     * @Title: home
     * @Description: 首页跳转
     * @since 1.0.0
     */
    @RequestMapping(value = "/")
    public String home(HttpServletRequest request, HttpServletResponse response, Model model) throws BizException {
       /* model.addAttribute("loginUserId", SessionUtils.getUserId());
        RSAPublicKey publicKey = RSAUtil.getDefaultPublicKey();
        model.addAttribute("rsa_modulus", new String(Hex.encodeHex(publicKey.getModulus().toByteArray())));
        model.addAttribute("rsa_exponent", new String(Hex.encodeHex(publicKey.getPublicExponent().toByteArray())));
        SysConfig sysConfActiveTimeout = sysConfigService.getSysConfigByKey(Constants.ACTIVE_TIMEOUT);
        model.addAttribute("sysConfActiveTimeout", sysConfActiveTimeout);*/
      //让IE浏览器使用最高文档模式(避免使用杂项模式) yinyanzhen
        response.setHeader("X-UA-Compatible", "IE=Edge");
        
       /* boolean shouldChangePassword = false;
        SysConfig sysConf = sysConfigService.getSysConfigByKey(Constants.UPDPASSWORD_SWITCH);
        if(sysConf != null){
        	if(StringUtils.isNotBlank(sysConf.getConfigValue()) && "0".equals(sysConf.getConfigValue())){
        		SysConfig cycleCon = sysConfigService.getSysConfigByKey(Constants.UPDPASSWORD_CYCLE);
        		if(cycleCon != null){
        			if(StringUtils.isNotBlank(cycleCon.getConfigValue())){
        				String cycleStr = cycleCon.getConfigValue();
                		shouldChangePassword = sysUserService.shouldChangePassword(Integer.parseInt(cycleStr),sysUserService.getPureUser(SessionUtils.getUserId()));
        			}
        		}
        	}
        }
        model.addAttribute("shouldChangePassword",shouldChangePassword);*/
        return "map"; //index
    }

    /**
     * @throws BizException
     * @Title: home
     * @Description: 首页跳转
     * @since 1.0.0
     */
    @RequestMapping(value = "/index")
    public String oldhome(HttpServletRequest request, HttpServletResponse response, Model model) throws BizException {
        model.addAttribute("loginUserId", SessionUtils.getUserId());
        RSAPublicKey publicKey = RSAUtil.getDefaultPublicKey();
        model.addAttribute("rsa_modulus", new String(Hex.encodeHex(publicKey.getModulus().toByteArray())));
        model.addAttribute("rsa_exponent", new String(Hex.encodeHex(publicKey.getPublicExponent().toByteArray())));
        SysConfig sysConfActiveTimeout = sysConfigService.getSysConfigByKey(Constants.ACTIVE_TIMEOUT);
        model.addAttribute("sysConfActiveTimeout", sysConfActiveTimeout);
        //让IE浏览器使用最高文档模式(避免使用杂项模式) yinyanzhen
        response.setHeader("X-UA-Compatible", "IE=Edge");

        boolean shouldChangePassword = false;
        SysConfig sysConf = sysConfigService.getSysConfigByKey(Constants.UPDPASSWORD_SWITCH);
        if(sysConf != null){
            if(StringUtils.isNotBlank(sysConf.getConfigValue()) && "0".equals(sysConf.getConfigValue())){
                SysConfig cycleCon = sysConfigService.getSysConfigByKey(Constants.UPDPASSWORD_CYCLE);
                if(cycleCon != null){
                    if(StringUtils.isNotBlank(cycleCon.getConfigValue())){
                        String cycleStr = cycleCon.getConfigValue();
                        shouldChangePassword = sysUserService.shouldChangePassword(Integer.parseInt(cycleStr),sysUserService.getPureUser(SessionUtils.getUserId()));
                    }
                }
            }
        }
        model.addAttribute("shouldChangePassword",shouldChangePassword);
        return "index"; //index
    }


    /**
     * @Title: home(解决shiro未记住最后页面跳转到NaN问题)
     * @Description: 首页跳转
     * @since 1.0.0
     */
    @RequestMapping(value = "/NaN")
    public String nan(HttpServletRequest request, HttpServletResponse response, Model model) {
        return "redirect:/login";
    }

    /**
     * @throws Exception
     * @Title: login
     * @Description: 登录
     * @since 1.0.0
     */
    @RequestMapping(value = "login")
    public String login(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
        SessionUtils.kickUser();
        SysConfig sysConf = sysConfigService.getSysConfigByKey(Constants.LOGIN_VERIFCODE);
        RSAPublicKey publicKey = RSAUtil.getDefaultPublicKey();
        model.addAttribute("rsa_modulus", new String(Hex.encodeHex(publicKey.getModulus().toByteArray())));
        model.addAttribute("rsa_exponent", new String(Hex.encodeHex(publicKey.getPublicExponent().toByteArray())));
        model.addAttribute("sysConfig", sysConf);
        //让IE浏览器使用最高文档模式(避免使用杂项模式) yinyanzhen
        response.setHeader("X-UA-Compatible", "IE=Edge");
        return "login";
    }

    /**
     * @Title:
     * @Description: 首页内嵌页
     * @since 1.0.0
     */
    @RequestMapping(value = "indexCenter")
    public String indexCenter(HttpServletRequest request, HttpServletResponse response, Model model) {
        return "page/index_center";
    }

    /**
     * @Title:
     * @Description: 首页内嵌页
     * @since 1.0.0
     */
    @RequestMapping(value = "indexMap")
    public String indexMap(HttpServletRequest request, HttpServletResponse response, Model model) {
        return "map";
    }

    /**
     * @Title:
     * @Description: 首页内嵌home页
     * @since 1.0.0
     */
    @RequestMapping(value = "indexHome")
    public String indexHome(HttpServletRequest request, HttpServletResponse response, Model model) {
        return "page/index_home";  //"page/index_home"
    }

    /**
     * @Title:
     * @Description: 加载单位信息管理
     */
    @RequestMapping(value = "indexCompanyCenter")
    public String indexCompanyCenter(HttpServletRequest request, HttpServletResponse response, Model model) {
        model = getCommonModel(model);
        return "wa/home/index_company_center";
    }

    /**
     * @Title:
     * @Description: 加载单位信息管理
     */
    @RequestMapping(value = "indexCompanyHome")
    public String indexCompanyHome(HttpServletRequest request, HttpServletResponse response, Model model) {
        return "wa/home/index_company_home";
    }

    /**
     * @Title:
     * @Description: 加载计划用水管理
     */
    @RequestMapping(value = "indexPlanWaterCenter")
    public String indexPlanWaterCenter(HttpServletRequest request, HttpServletResponse response, Model model) {
        model = getCommonModel(model);
        return "wa/home/index_planWater_center";
    }

    /**
     * @Title:
     * @Description: 加载计划用水管理
     */
    @RequestMapping(value = "indexPlanWaterHome")
    public String indexPlanWaterHome(HttpServletRequest request, HttpServletResponse response, Model model) {
        return "wa/home/index_planWater_home";
    }

    /**
     * @Title:
     * @Description: 加载用水数据管理
     */
    @RequestMapping(value = "indexDataManagementCenter")
    public String indexDataManagementCenter(HttpServletRequest request, HttpServletResponse response, Model model) {
        model = getCommonModel(model);
        return "wa/home/index_dataManagement_center";
    }

    /**
     * @Title:
     * @Description: 加载用水数据管理
     */
    @RequestMapping(value = "indexDataManagementHome")
    public String indexDataManagementHome(HttpServletRequest request, HttpServletResponse response, Model model) {
        return "wa/home/index_dataManagement_home";
    }


    public Model getCommonModel(Model model){
        model.addAttribute("loginUserId", SessionUtils.getUserId());
        RSAPublicKey publicKey = RSAUtil.getDefaultPublicKey();
        model.addAttribute("rsa_modulus", new String(Hex.encodeHex(publicKey.getModulus().toByteArray())));
        model.addAttribute("rsa_exponent", new String(Hex.encodeHex(publicKey.getPublicExponent().toByteArray())));
        SysConfig sysConfActiveTimeout = sysConfigService.getSysConfigByKey(Constants.ACTIVE_TIMEOUT);
        model.addAttribute("sysConfActiveTimeout", sysConfActiveTimeout);
        //让IE浏览器使用最高文档模式(避免使用杂项模式) yinyanzhen
//        response.setHeader("X-UA-Compatible", "IE=Edge");

        boolean shouldChangePassword = false;
        SysConfig sysConf = sysConfigService.getSysConfigByKey(Constants.UPDPASSWORD_SWITCH);
        if(sysConf != null){
            if(StringUtils.isNotBlank(sysConf.getConfigValue()) && "0".equals(sysConf.getConfigValue())){
                SysConfig cycleCon = sysConfigService.getSysConfigByKey(Constants.UPDPASSWORD_CYCLE);
                if(cycleCon != null){
                    if(StringUtils.isNotBlank(cycleCon.getConfigValue())){
                        String cycleStr = cycleCon.getConfigValue();
                        try {
                            shouldChangePassword = sysUserService.shouldChangePassword(Integer.parseInt(cycleStr),sysUserService.getPureUser(SessionUtils.getUserId()));
                        } catch (BizException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        model.addAttribute("shouldChangePassword",shouldChangePassword);

        return model;
    }

    /**
     * @Title:
     * @Description: 首页内嵌Keyboard页
     * @since 1.0.0
     */
    @RequestMapping(value = "indexKeyboard")
    public String indexKeyboard(HttpServletRequest request, HttpServletResponse response, Model model) {
        return "page/index_keyboard";
    }

    /**
     * @throws BizException
     * @Title:
     * @Description: 个人中心页
     * @since 1.0.0
     */
    @RequestMapping(value = "personalCenter")
    public String personalCenter(HttpServletRequest request, HttpServletResponse response, Model model)
            throws BizException {
        model.addAttribute("user", sysUserService.getLoginUser());
        List<SysAuth> appAuth = sysUserService.getUserAppAuth();
        String[] appAuthAry = new String[appAuth.size()];
        for (int i = 0; i < appAuth.size(); i++) {
            appAuthAry[i] = appAuth.get(i).getAuthName();
        }
        List<SysAuth> moduleAuth = sysUserService.getUserModuleAuth();
        String[] moduleAuthAry = new String[moduleAuth.size()];
        for (int i = 0; i < moduleAuth.size(); i++) {
            moduleAuthAry[i] = moduleAuth.get(i).getAuthName();
        }
        List<SysAuth> operAuth = sysUserService.getUserOperAuth();
        String[] operAuthAry = new String[operAuth.size()];
        for (int i = 0; i < operAuth.size(); i++) {
            operAuthAry[i] = operAuth.get(i).getAuthName();
        }
        model.addAttribute("sexMap", sysDatadicItemService.getMapByGroupCode("SEX"));
        model.addAttribute("appAuth", StringUtils.join(appAuthAry, "，"));
        model.addAttribute("moduleAuth", StringUtils.join(moduleAuthAry, "，"));
        model.addAttribute("operAuth", StringUtils.join(operAuthAry, "，"));
        model.addAttribute("userRoles", sysUserRoleJoinService.getRoleNamesByUserId(SessionUtils.getUserId()));
        model.addAttribute("userUgroups", sysUserUgroupJoinService.getGroupNamesByUserId(SessionUtils.getUserId()));
        return "page/personalCenter";
    }

    /**
     * @Title:
     * @Description: 刷新submitToken
     * @since 1.0.0
     */
    @RequestMapping(value = "refSubmitToken")
    @ResponseBody
    @RepeatSubmitToken(saveToken = true)
    public Map refSubmitToken(final HttpServletRequest request, final HttpServletResponse response) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() {
                response.setHeader("Cache-Control", "no-cache");
                response.setHeader("Pragma", "no-cache");
                response.setDateHeader("expires", -1);
                String serverToken = RepeatSubmitUtil.getLastRepeatToken(request);
                map.put("data", serverToken);
            }
        };
        return templete.operate();
    }

    /**
     * @Title:
     * @Description: 删除submitToken
     * @since 1.0.0
     */
    @RequestMapping(value = "removeSubmitToken")
    @ResponseBody
    public Map removeSubmitToken(final HttpServletRequest request, HttpServletResponse response) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
                RepeatSubmitUtil.removeRepeatToken(request);
            }
        };
        return templete.operate();
    }
    

    /**
     * 
     * @Title: captchaImage
     * @Description: 验证码图片
     * @since 1.0.0
     */
    @RequestMapping(value = "captchaImage")
    public ModelAndView getKaptchaImage(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        String capText = captchaProducer.createText();

        session.setAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY, capText);
        BufferedImage bi = captchaProducer.createImage(capText);
        try {
            ServletOutputStream out = response.getOutputStream();
            ImageIO.write(bi, "jpg", out);
            LOG.debug("*****验证码是: " + capText + "*****");
            try {
                out.flush();
            } finally {
                out.close();
            }
        } catch (Exception e) {
            LOG.error("getKaptchaImage fail !", e);
        }
        return null;
    }

    /**
     * 
     * @Title: getUserIndexAuthModule
     * @Description: 获取用户的首页菜单
     * @param @param vo
     * @param @param response
     * @param @param request
     * @since 1.0.0
     */
    @RequestMapping("/leftMenu")
    @ResponseBody
    public Map getUserIndexAuthModule(final SysAuthModule vo, HttpServletResponse response,
            final HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
                map.put("data", sysUserService.getUserIndexLeftMenu());
            }
        };
        return templete.operate();
    }

    /**
     * 
     * @Title: getRandomNum
     * @Description: 获取随机数字
     * @param @param vo
     * @param @param response
     * @param @param request
     * @since 1.0.0
     */
    @RequestMapping("/getRandomNum")
    @ResponseBody
    public Map getRandomChar(HttpServletResponse response, final HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
                map.put("data", SmsUtil.getDyVfCode());
            }
        };
        return templete.operate();
    }
    
    /**
     * 获取随机密码。
     * @author licheng
     * @param response 
     * @param request 
     * @return 
     * @since V1.0.0
     */
    @RequestMapping("/getRandomChars")
    @ResponseBody
    public Map getRandomChars(HttpServletResponse response, final HttpServletRequest request,final Integer length) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
                int parameter = (length==null)?Constants.SEVEN:length;
                map.put("data", RandomStringUtils.randomAlphanumeric(parameter));
            }
        };
        return templete.operate();
    }

    /**
     * 
     * @Title: showTodoByToken
     * @Description: 根据密钥显示代办
     * @param response
     * @param request
     * @since 1.0.0
     * showTodoByToken?userId=1&tokenId=1&tokenSign=abcdefg&time&toDotaskId=3
     */
    @RequestMapping("/showTodoByToken")
    public String showTodoByToken(final SysToken token,final String toDotaskId, HttpServletRequest request, HttpServletResponse response,
            final Model model) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                indexService.doLoginByToken(token);
                model.addAttribute("toDotaskId", toDotaskId);
                str = "index";
            }
        };
        return templete.operateModel();
       
    }
    
    /**
     * 显示修改个人密码页面
     * @param request
     * @param response
     * @param model
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/showChangePersonalPwd")
    public String showChangePersonalPwd(HttpServletRequest request, HttpServletResponse response,final Model model) throws BizException {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                str = "page/personal_changePwd";
            } 
        };
        return templete.operateModel();
    }

}