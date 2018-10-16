/*
 * Project Name:hbm-base.
 * File Name:WaSavewaterTypeController.java
 * Package Name:com.hongguaninfo.hgdf.bud.web.wa
 * Date:2018年09月11日 下午8:49:44
 * Copyright (c) 2016, hongguaninfo.com All Rights Reserved.
 *
 */
package com.hongguaninfo.hgdf.wa.web;

import com.hongguaninfo.hgdf.adp.core.aop.log.UserLog;
import com.hongguaninfo.hgdf.adp.core.base.BasePage;
import com.hongguaninfo.hgdf.adp.core.exception.BizException;
import com.hongguaninfo.hgdf.adp.core.interceptor.RepeatSubmitToken;
import com.hongguaninfo.hgdf.adp.core.templete.HttpTemplete;
import com.hongguaninfo.hgdf.adp.core.templete.OperateTemplete;
import com.hongguaninfo.hgdf.core.utils.exception.BaseException;
import com.hongguaninfo.hgdf.core.utils.logging.Log;
import com.hongguaninfo.hgdf.core.utils.logging.LogFactory;
import com.hongguaninfo.hgdf.wa.entity.WaSavewaterType;
import com.hongguaninfo.hgdf.wa.service.WaSavewaterTypeService;
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
 * WaSavewaterTypeController:。 <br />
 * controller 层 <br />
 * Date:2018年09月11日 下午8:49:44
 *
 * @author hhp
 * @since V1.0.0
 */
@Controller
@RequestMapping("/wa/WaSavewaterType")
public class WaSavewaterTypeController {

    /**
     *
     */
    private static final Log LOG = LogFactory.getLog(WaSavewaterTypeController.class);

    /**
     * Service。
     */
    @Autowired
    private WaSavewaterTypeService waSavewaterTypeService;

    /**
	 * REMARK
	 * 列表页面。
	 * @param response
	 * @param request
	 */
	@RequestMapping("/showWaSavewaterType")
	public String showWaSavewaterType (HttpServletRequest request, HttpServletResponse response, Model model) {
		OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                str = "wa/WaSavewaterType/waSavewaterType_show";
            }
        };
        return templete.operateModel();
	}
    /**
    * 分页数据。
    * @WaSavewaterType
    * @return 
    */
	@RequestMapping("/list")
    @ResponseBody
    public Map getWaSavewaterTypeList (final WaSavewaterType waSavewaterType , final BasePage pageRequest,
                                       HttpServletResponse response, HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
                waSavewaterTypeService.getWaSavewaterTypeList(pageRequest, waSavewaterType, map);
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
    @RepeatSubmitToken(removeToken = true)
    @UserLog(code = "addWaSavewaterType", name = "add WaSavewaterType", remarkClass = WaSavewaterType.class)
    public Map addWaSavewaterType (@Valid final WaSavewaterType vo, BindingResult result,
            HttpServletResponse response, final HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request, result) {
            protected void doSomething() throws BizException {
                if (doValidate()) {
                    waSavewaterTypeService.addWaSavewaterType(vo);
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
    @UserLog(code = "editWaSavewaterType", name = "update WaSavewaterType", remarkClass = WaSavewaterType.class)
    public Map editWaSavewaterType (final WaSavewaterType vo, HttpServletResponse response,
            final HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
				waSavewaterTypeService.updateWaSavewaterType(vo);    
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
    @UserLog(code = "deleteWaSavewaterType", name = "delete WaSavewaterType", remarkClass = Integer.class)
    public Map deleteWaSavewaterType (final Integer id, HttpServletResponse response,
            final HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
            	waSavewaterTypeService.deleteWaSavewaterTypeLogic(id);    
            }
        };
        return templete.operate();
    }

	/**
	 * REMARK
	 * 详情信息
	 * Through the id inquires the out a data
	 */
	@RequestMapping(value = "/waSavewaterTypeDetail/{id}")
	public String showWaSavewaterTypeDetail (@PathVariable int id,
            HttpServletRequest request, HttpServletResponse response,
            Model model) throws BizException {
        model.addAttribute("id", id);
        model.addAttribute("waSavewaterType",
         waSavewaterTypeService.getWaSavewaterTypeById(id));
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                 str = "wa/WaSavewaterType/waSavewaterType_detail";
            }
        };
        return templete.operateModel();
    }
}
