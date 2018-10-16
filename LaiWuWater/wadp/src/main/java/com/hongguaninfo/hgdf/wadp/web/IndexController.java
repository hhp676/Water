package com.hongguaninfo.hgdf.wadp.web;

import java.awt.image.BufferedImage;
import java.security.interfaces.RSAPublicKey;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.code.kaptcha.Producer;
import com.hongguaninfo.hgdf.core.utils.SmsUtil;
import com.hongguaninfo.hgdf.core.utils.encrypt.RSAUtil;
import com.hongguaninfo.hgdf.core.utils.logging.Log;
import com.hongguaninfo.hgdf.core.utils.logging.LogFactory;
import com.hongguaninfo.hgdf.wadp.core.Constants;
import com.hongguaninfo.hgdf.wadp.core.base.BaseController;
import com.hongguaninfo.hgdf.wadp.core.exception.BizException;
import com.hongguaninfo.hgdf.wadp.core.interceptor.RepeatSubmitToken;
import com.hongguaninfo.hgdf.wadp.core.interceptor.RepeatSubmitUtil;
import com.hongguaninfo.hgdf.wadp.core.templete.HttpTemplete;
import com.hongguaninfo.hgdf.wadp.core.templete.OperateTemplete;
import com.hongguaninfo.hgdf.wadp.core.utils.SessionUtils;
import com.hongguaninfo.hgdf.wadp.entity.sys.SysAuth;
import com.hongguaninfo.hgdf.wadp.entity.sys.SysAuthModule;
import com.hongguaninfo.hgdf.wadp.entity.sys.SysConfig;
import com.hongguaninfo.hgdf.wadp.service.sys.SysConfigService;
import com.hongguaninfo.hgdf.wadp.service.sys.SysUserRoleJoinService;
import com.hongguaninfo.hgdf.wadp.service.sys.SysUserService;
import com.hongguaninfo.hgdf.wadp.service.sys.SysUserUgroupJoinService;

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

    /**
     * @Title: home
     * @Description: 首页跳转
     * @since 1.0.0
     */
    @RequestMapping(value = "/")
    public String home(HttpServletRequest request,
            HttpServletResponse response, Model model) {
        List<SysAuth> auths = sysUserService.getUserIndexLeftMenu();
        request.getSession().setAttribute("auths", auths);
        RSAPublicKey publicKey = RSAUtil.getDefaultPublicKey();
        model.addAttribute("rsa_modulus", new String(Hex.encodeHex(publicKey.getModulus().toByteArray())));
        model.addAttribute("rsa_exponent", new String(Hex.encodeHex(publicKey.getPublicExponent().toByteArray())));
        SysConfig sysConfActiveTimeout = sysConfigService.getSysConfigByKey(Constants.ACTIVE_TIMEOUT);
        model.addAttribute("sysConfActiveTimeout", sysConfActiveTimeout);
        return "index";
    }

    /**
     * @throws Exception 
     * @Title: login
     * @Description: 登录
     * @since 1.0.0
     */
    @RequestMapping(value = "login")
    public String login(HttpServletRequest request,
            HttpServletResponse response, Model model) throws Exception {
        SessionUtils.kickUser();
        SysConfig sysConf = sysConfigService.getSysConfigByKey(Constants.LOGIN_VERIFCODE);
        model.addAttribute("sysConfig", sysConf);
        RSAPublicKey publicKey = RSAUtil.getDefaultPublicKey();
        model.addAttribute("rsa_modulus", new String(Hex.encodeHex(publicKey.getModulus().toByteArray())));
        model.addAttribute("rsa_exponent", new String(Hex.encodeHex(publicKey.getPublicExponent().toByteArray())));
        return "login";
    }

    /**
     * @Title:
     * @Description: 首页内嵌页
     * @since 1.0.0
     */
    @RequestMapping(value = "indexCenter")
    public String indexCenter(HttpServletRequest request,
            HttpServletResponse response, Model model) {
        return "page/index_center";
    }

    /**
     * @Title:
     * @Description: 首页内嵌home页
     * @since 1.0.0
     */
    @RequestMapping(value = "indexHome")
    public String indexHome(HttpServletRequest request,
            HttpServletResponse response, Model model) {
        return "page/index_home";
    }

    /**
     * @throws BizException
     * @Title:
     * @Description: 个人中心页
     * @since 1.0.0
     */
    @RequestMapping(value = "personalCenter")
    public String personalCenter(HttpServletRequest request,
            HttpServletResponse response, Model model) throws BizException {
        model.addAttribute("user", sysUserService.getLoginUser());
        List<SysAuth> appAuth = sysUserService.getUserAppAuth();
        String[] appAuthAry = new String[appAuth.size()];
        for (int i=0;i<appAuth.size();i++) {
        	appAuthAry[i] = appAuth.get(i).getAuthName();
        }
        List<SysAuth> moduleAuth = sysUserService.getUserModuleAuth();
        String[] moduleAuthAry = new String[moduleAuth.size()];
        for (int i=0;i<moduleAuth.size();i++) {
        	moduleAuthAry[i] = moduleAuth.get(i).getAuthName();
        }
        List<SysAuth> operAuth = sysUserService.getUserOperAuth();
        String[] operAuthAry = new String[operAuth.size()];
        for (int i=0;i<operAuth.size();i++) {
        	operAuthAry[i] = operAuth.get(i).getAuthName();
        }
        model.addAttribute("appAuth", StringUtils.join(appAuthAry, "，"));
        model.addAttribute("moduleAuth", StringUtils.join(moduleAuthAry, "，"));
        model.addAttribute("operAuth", StringUtils.join(operAuthAry, "，"));
        model.addAttribute("userRoles",sysUserRoleJoinService.getRoleNamesByUserId(SessionUtils.getUserId()));
        model.addAttribute("userUgroups",sysUserUgroupJoinService.getGroupNamesByUserId(SessionUtils.getUserId()));
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
     * 
     * @Title: captchaImage
     * @Description: 验证码图片
     * @since 1.0.0
     */
    @RequestMapping(value = "captchaImage")
    public ModelAndView getKaptchaImage(HttpServletRequest request,
            HttpServletResponse response) {
        HttpSession session = request.getSession();
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control",
                "no-store, no-cache, must-revalidate");
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
    public Map getUserIndexAuthModule(final SysAuthModule vo,
            HttpServletResponse response, final HttpServletRequest request) {
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
    public Map getRandomChar(HttpServletResponse response,
            final HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
                map.put("data", SmsUtil.getDyVfCode());
            }
        };
        return templete.operate();
    }

    
    @RequestMapping("/fm/hello")
    public String sayHello(ModelMap map) {
        System.out.println("say Hello ……");
        map.addAttribute("name", " World!");
        return "/hello.ftl";
    }
}