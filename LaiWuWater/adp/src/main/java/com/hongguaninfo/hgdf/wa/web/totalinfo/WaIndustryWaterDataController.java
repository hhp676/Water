/*
 * Project Name:hbm-base.
 * File Name:WaIndustryWaterDataController.java
 * Package Name:com.hongguaninfo.hgdf.bud.web.wa
 * Date:2018年09月14日 上午9:23:39
 * Copyright (c) 2016, hongguaninfo.com All Rights Reserved.
 *
 */
package com.hongguaninfo.hgdf.wa.web.totalinfo;

import com.hongguaninfo.hgdf.adp.core.aop.log.UserLog;
import com.hongguaninfo.hgdf.adp.core.base.BasePage;
import com.hongguaninfo.hgdf.adp.core.exception.BizException;
import com.hongguaninfo.hgdf.adp.core.templete.HttpTemplete;
import com.hongguaninfo.hgdf.adp.core.templete.OperateTemplete;
import com.hongguaninfo.hgdf.core.utils.exception.BaseException;
import com.hongguaninfo.hgdf.core.utils.logging.Log;
import com.hongguaninfo.hgdf.core.utils.logging.LogFactory;
import com.hongguaninfo.hgdf.wa.entity.totalinfo.WaIndustryWaterData;
import com.hongguaninfo.hgdf.wa.service.totalinfo.WaIndustryWaterDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;


/**
 * WaIndustryWaterDataController:。 <br />
 * controller 层 <br />
 * Date:2018年09月14日 上午9:23:39
 *
 * @author hhp
 * @since V1.0.0
 */
@Controller
@RequestMapping("/wa/WaIndustryWaterData")
public class WaIndustryWaterDataController {

    /**
     *
     */
    private static final Log LOG = LogFactory.getLog(WaIndustryWaterDataController.class);

    /**
     * Service。
     */
    @Autowired
    private WaIndustryWaterDataService waIndustryWaterDataService;

    /**
	 * REMARK
	 * 列表页面。
	 * @param response
	 * @param request
	 */
	@RequestMapping("/showWaIndustryWaterData")
	public String showWaIndustryWaterData (HttpServletRequest request, HttpServletResponse response, Model model) {
		OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                str = "wa/WaIndustryWaterData/waIndustryWaterData_show";
            }
        };
        return templete.operateModel();
	}
    /**
    * 分页数据。
    * @WaIndustryWaterData
    * @return 
    */
	@RequestMapping("/list")
    @ResponseBody
    public Map getWaIndustryWaterDataList (final WaIndustryWaterData waIndustryWaterData , final BasePage pageRequest,
                                           HttpServletResponse response, HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
                waIndustryWaterDataService.getWaIndustryWaterDataList(pageRequest, waIndustryWaterData, map);
            }
        };
        return templete.operate();
    }
    /**
	 * REMARK
	 *
	 * 新增
	 */
    @RequestMapping("/add")
    @ResponseBody
    public Map addWaIndustryWaterData (@Valid final WaIndustryWaterData vo, BindingResult result,
            HttpServletResponse response, final HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request, result) {
            protected void doSomething() throws BizException {
                if (doValidate()) {
                    waIndustryWaterDataService.addWaIndustryWaterData(vo);
                }
            }
        };
        return templete.operate();
    }

	/**
	 * REMARK
	 *
	 * 修改
	 */
    @RequestMapping("/update")
    @ResponseBody
    public Map editWaIndustryWaterData (final WaIndustryWaterData vo, HttpServletResponse response,
            final HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
				waIndustryWaterDataService.updateWaIndustryWaterData(vo);    
            }
        };
        return templete.operate();
    }
    /**
	 * REMARK
	 * 删除
	 * Through the id delete a data
	 */
    @RequestMapping("/delete")
    @ResponseBody
    @UserLog(code = "deleteWaIndustryWaterData", name = "delete WaIndustryWaterData", remarkClass = Integer.class)
    public Map deleteWaIndustryWaterData (final Integer id, HttpServletResponse response,
            final HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
            	waIndustryWaterDataService.deleteWaIndustryWaterDataLogic(id);    
            }
        };
        return templete.operate();
    }

	/**
	 * REMARK
	 * 详情信息
	 * Through the id inquires the out a data
	 */
	@RequestMapping(value = "/waIndustryWaterDataDetail/{id}")
	public String showWaIndustryWaterDataDetail (@PathVariable int id,
            HttpServletRequest request, HttpServletResponse response,
            Model model) throws BizException {
        model.addAttribute("id", id);
        model.addAttribute("waIndustryWaterData",
         waIndustryWaterDataService.getWaIndustryWaterDataById(id));
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                 str = "wa/WaIndustryWaterData/waIndustryWaterData_detail";
            }
        };
        return templete.operateModel();
    }
}
