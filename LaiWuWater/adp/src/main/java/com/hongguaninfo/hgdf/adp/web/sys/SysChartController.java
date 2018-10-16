package com.hongguaninfo.hgdf.adp.web.sys;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hongguaninfo.hgdf.adp.core.exception.BizException;
import com.hongguaninfo.hgdf.adp.core.templete.HttpTemplete;
import com.hongguaninfo.hgdf.adp.core.templete.OperateTemplete;
import com.hongguaninfo.hgdf.adp.service.sys.SysChartService;
import com.hongguaninfo.hgdf.core.utils.exception.BaseException;

/**
 * : 图表 controller 层
 * 
 * @author yuyanlin
 */

@Controller
@RequestMapping("/sys/chart")
public class SysChartController {

	@Autowired
	private SysChartService sysChartService;
	
	
	
	
    @RequestMapping(value = "/sysUser")
	public String showSysUser (HttpServletRequest request, HttpServletResponse response,
            final Model model) throws BizException {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
            	model.addAttribute("maleData", sysChartService.getUserBithMonthCntListStr(1));
            	model.addAttribute("femaleData",sysChartService.getUserBithMonthCntListStr(0));
                str = "sys/chart/sysUser";
            }
        };
        return templete.operateModel();
    }
    
    @RequestMapping(value = "/sysDepart")
	public String showSysDepart (HttpServletRequest request, HttpServletResponse response,
            final Model model) throws BizException {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                str = "sys/chart/sysDepart";
            }
        };
        return templete.operateModel();
    }
    
    
    
 

}
