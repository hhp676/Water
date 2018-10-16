package com.hongguaninfo.hgdf.wadp.web.sys;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hongguaninfo.hgdf.wadp.core.base.BaseController;
import com.hongguaninfo.hgdf.wadp.core.templete.HttpTemplete;
import com.hongguaninfo.hgdf.wadp.core.templete.OperateTemplete;
import com.hongguaninfo.hgdf.wadp.service.sys.SysInfoService;
import com.hongguaninfo.hgdf.core.utils.exception.BaseException;

@Controller
@RequestMapping("/sys/info")
public class SysInfoController extends BaseController {

    @Autowired
    private SysInfoService sysInfoService;

    /**
     * @Title: getJvmInfo
     * @Description: 获取jvm信息
     * @param @param request
     * @param @param response
     * @param @return 设定文件
     * @return Map 返回类型
     * @throws
     * @since 1.0.0
     */
    @RequestMapping(value = "/jvmInfo")
    @ResponseBody
    public Map getJvmInfo(HttpServletRequest request,
            HttpServletResponse response) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                map.put("data", sysInfoService.getJvmInfo());
            }
        };
        return templete.operate();
    }

    /**
     * @Title: getSystemInfo
     * @Description: 获取系统信息
     * @param @param request
     * @param @param response
     * @param @return 设定文件
     * @return Map 返回类型
     * @throws
     * @since 1.0.0
     */
    @RequestMapping(value = "/systemInfo")
    @ResponseBody
    public Map getSystemInfo(HttpServletRequest request,
            HttpServletResponse response) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                map.put("data", sysInfoService.getSystemInfo());
            }
        };
        return templete.operate();
    }

    /**
     * @Title: getServerInfo
     * @Description: 获取服务器信息
     * @param @param request
     * @param @param response
     * @param @return 设定文件
     * @return Map 返回类型
     * @throws
     * @since 1.0.0
     */
    @RequestMapping(value = "/serverInfo")
    @ResponseBody
    public Map getServerInfo(HttpServletRequest request,
            HttpServletResponse response) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                map.put("data", sysInfoService.getServerInfo());
            }
        };
        return templete.operate();
    }

    @RequestMapping(value = "/propsInfo")
    @ResponseBody
    public Map getPropsInfo(HttpServletRequest request,
            HttpServletResponse response) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                map.put("data", sysInfoService.getPropsInfo());
            }
        };
        return templete.operate();
    }

    @RequestMapping(value = "/showSysInfo")
    public String showSysInfo(HttpServletRequest request,
            HttpServletResponse response, Model model) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                /*model.addAttribute(arg0);*/
                str = "sys/sysInfo_show";
            }
        };
        return templete.operateModel();
    }
}