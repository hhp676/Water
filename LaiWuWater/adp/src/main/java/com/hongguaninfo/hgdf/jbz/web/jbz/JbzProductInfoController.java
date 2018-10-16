/*
 * Project Name:hbm-base.
 * File Name:JbzProductInfoController.java
 * Package Name:com.hongguaninfo.hgdf.adp.web.jbz
 * Date:2018年01月11日 下午9:09:20
 * Copyright (c) 2016, hongguaninfo.com All Rights Reserved.
 *
 */
package com.hongguaninfo.hgdf.jbz.web.jbz;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.hongguaninfo.hgdf.adp.core.aop.log.UserLog;
import com.hongguaninfo.hgdf.adp.core.base.BasePage;
import com.hongguaninfo.hgdf.adp.core.exception.BizException;
import com.hongguaninfo.hgdf.adp.core.interceptor.RepeatSubmitToken;
import com.hongguaninfo.hgdf.adp.core.templete.HttpTemplete;
import com.hongguaninfo.hgdf.adp.core.templete.OperateTemplete;
import com.hongguaninfo.hgdf.adp.core.templete.WsTemplete;
import com.hongguaninfo.hgdf.core.utils.exception.BaseException;
import com.hongguaninfo.hgdf.jbz.entity.jbz.JbzProductInfo;
import com.hongguaninfo.hgdf.jbz.service.jbz.JbzProductInfoService;


/**
 * JbzProductInfoController:。 <br />
 * controller 层 <br />
 * Date:2018年01月11日 下午9:09:20
 *
 * @author hhp
 * @since V1.0.0
 */
@Controller
@RequestMapping("/jbz/JbzProductInfo")
public class JbzProductInfoController {

    /**
     *
     */
//    private static final Log LOG = LogFactory.getLog(JbzProductInfoController.class);

    /**
     * Service。
     */
    @Autowired
    private JbzProductInfoService jbzProductInfoService;

    /**
	 * REMARK
	 * 列表页面。
	 * @param pageRequest
	 * @param response
	 * @param request
	 */
	@RequestMapping("/showJbzProductInfo")
	public String showJbzProductInfo (HttpServletRequest request, HttpServletResponse response, Model model) {
		OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                str = "wa/JbzProductInfo/jbzProductInfo_show";
            }
        };
        return templete.operateModel();
	}
    /**
    * 分页数据。
    * @JbzProductInfo
    * @return 
    */
	@RequestMapping("/list")
    @ResponseBody
    public Map getJbzProductInfoList (final JbzProductInfo jbzProductInfo , final BasePage pageRequest,
            HttpServletResponse response, HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
                jbzProductInfoService.getJbzProductInfoList(pageRequest, jbzProductInfo, map);
            }
        };
        return templete.operate();
    }
	
	@RequestMapping("/getInfoList")
    @ResponseBody
    public String getInfoList (@RequestBody String str) throws BizException {
		WsTemplete templete = new WsTemplete(str) {
            protected void doSomething() throws BizException {
            	JbzProductInfo jbzInfo = JSON.parseObject(param, JbzProductInfo.class);
            	
            	List<JbzProductInfo> jbzList = jbzProductInfoService.getInfoList(jbzInfo);
            	map.put("data", jbzList);
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
    @UserLog(code = "addJbzProductInfo", name = "add JbzProductInfo", remarkClass = JbzProductInfo.class)
    public Map addJbzProductInfo (@Valid final JbzProductInfo vo, BindingResult result,
            HttpServletResponse response, final HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request, result) {
            protected void doSomething() throws BizException {
                if (doValidate()) {
                    jbzProductInfoService.addJbzProductInfo(vo);
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
    @UserLog(code = "editJbzProductInfo", name = "update JbzProductInfo", remarkClass = JbzProductInfo.class)
    public Map editJbzProductInfo (final JbzProductInfo vo, HttpServletResponse response,
            final HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
				jbzProductInfoService.updateJbzProductInfo(vo);    
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
    @UserLog(code = "deleteJbzProductInfo", name = "delete JbzProductInfo", remarkClass = Integer.class)
    public Map deleteJbzProductInfo (final Integer id, HttpServletResponse response,
            final HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
            	jbzProductInfoService.deleteJbzProductInfoLogic(id);    
            }
        };
        return templete.operate();
    }

	/**
	 * REMARK
	 * 详情信息
	 * Through the id inquires the out a data
	 */
	@RequestMapping(value = "/jbzProductInfoDetail/{id}")
	public String showJbzProductInfoDetail (@PathVariable int id,
            HttpServletRequest request, HttpServletResponse response,
            Model model) throws BizException {
        model.addAttribute("id", id);
        model.addAttribute("jbzProductInfo",
         jbzProductInfoService.getJbzProductInfoById(id));
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                 str = "wa/JbzProductInfo/jbzProductInfo_detail";
            }
        };
        return templete.operateModel();
    }
}
