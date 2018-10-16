package com.hongguaninfo.hgdf.wadp.web.sys;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hongguaninfo.hgdf.wadp.core.aop.log.UserLog;
import com.hongguaninfo.hgdf.wadp.core.exception.BizException;
import com.hongguaninfo.hgdf.wadp.core.templete.HttpTemplete;
import com.hongguaninfo.hgdf.wadp.core.templete.OperateTemplete;
import com.hongguaninfo.hgdf.wadp.entity.sys.SysConfig;
import com.hongguaninfo.hgdf.wadp.service.sys.SysConfigService;
import com.hongguaninfo.hgdf.core.utils.exception.BaseException;

@Controller
@RequestMapping("/sys/cfg")
public class SysConfigController {

    @Autowired
    private SysConfigService sysConfigBiz;

    @RequestMapping(value = "/showSysCfg")
    public String showSysCfg(HttpServletRequest request,
            HttpServletResponse response, Model model) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                /*model.addAttribute(arg0);*/
                str = "sys/sysCfg_show";
            }
        };
        return templete.operateModel();
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map getSysCfgList(final SysConfig vo, HttpServletResponse response,
            HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
                map.put("data", sysConfigBiz.getSysCfgList(vo));

            }
        };
        return templete.operate();
    }

    @RequestMapping("/reset")
    @ResponseBody
    @UserLog(code = "resetSysCfgValue", name = "重置系统配置信息")
    public Map resetSysCfgValue(HttpServletResponse response,
            HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {

                sysConfigBiz.resetSysCfgValue();

            }
        };
        return templete.operate();
    }

    @RequestMapping("/batchUpdate")
    @ResponseBody
    @UserLog(code = "updateSysCfgValue", name = "更新系统配置信息", remarkClass = String.class)
    public Map updateSysCfgValue(final String jsonListStr,
            HttpServletResponse response, HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
                sysConfigBiz.doBatchUpdateSysCfg(jsonListStr);

            }
        };
        return templete.operate();
    }

}
