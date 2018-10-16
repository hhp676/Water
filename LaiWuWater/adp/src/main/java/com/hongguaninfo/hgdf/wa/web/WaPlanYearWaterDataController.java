/*
 * Project Name:hbm-base.
 * File Name:WaPlanYearWaterDataController.java
 * Package Name:com.hongguaninfo.hgdf.bud.web.wa
 * Date:2018年09月11日 下午8:49:44
 * Copyright (c) 2016, hongguaninfo.com All Rights Reserved.
 *
 */
package com.hongguaninfo.hgdf.wa.web;

import com.alibaba.fastjson.JSON;
import com.hongguaninfo.hgdf.adp.core.aop.log.UserLog;
import com.hongguaninfo.hgdf.adp.core.base.BasePage;
import com.hongguaninfo.hgdf.adp.core.exception.BizException;
import com.hongguaninfo.hgdf.adp.core.templete.HttpTemplete;
import com.hongguaninfo.hgdf.adp.core.templete.OperateTemplete;
import com.hongguaninfo.hgdf.adp.core.utils.ResponseUtils;
import com.hongguaninfo.hgdf.core.utils.exception.BaseException;
import com.hongguaninfo.hgdf.core.utils.logging.Log;
import com.hongguaninfo.hgdf.core.utils.logging.LogFactory;
import com.hongguaninfo.hgdf.wa.entity.WaPlanYearWaterData;
import com.hongguaninfo.hgdf.wa.service.WaCompanyInfoService;
import com.hongguaninfo.hgdf.wa.service.WaPlanYearWaterDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * WaPlanYearWaterDataController:。 <br />
 * controller 层 <br />
 * Date:2018年09月11日 下午8:49:44
 *
 * @author hhp
 * @since V1.0.0
 */
@Controller
@RequestMapping("/wa/WaPlanYearWaterData")
public class WaPlanYearWaterDataController {

    /**
     *
     */
    private static final Log LOG = LogFactory.getLog(WaPlanYearWaterDataController.class);

    /**
     * Service。
     */
    @Autowired
    private WaPlanYearWaterDataService waPlanYearWaterDataService;
    @Autowired
    private WaCompanyInfoService waCompanyInfoService;
    /**
	 * REMARK
	 * 列表页面。
	 * @param response
	 * @param request
	 */
	@RequestMapping("/showWaPlanYearWaterData")
	public String showWaPlanYearWaterData (HttpServletRequest request, HttpServletResponse response, Model model) {
		OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                str = "wa/waterPlanManagement/year/waPlanYearWaterData_show";
            }
        };
        return templete.operateModel();
	}
    /**
    * 分页数据。
    * @WaPlanYearWaterData
    * @return 
    */
	@RequestMapping("/list")
    @ResponseBody
    public Map getWaPlanYearWaterDataList (final WaPlanYearWaterData waPlanYearWaterData , final BasePage pageRequest,
                                           HttpServletResponse response, HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
                waPlanYearWaterDataService.getWaPlanYearWaterDataList(pageRequest, waPlanYearWaterData, map);
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
    public Map addWaPlanYearWaterData (@Valid final WaPlanYearWaterData vo, BindingResult result,
            HttpServletResponse response, final HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request, result) {
            protected void doSomething() throws BizException {
                if (doValidate()) {
                    waPlanYearWaterDataService.addWaPlanYearWaterData(vo);
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
    @UserLog(code = "update", name = "修改计划年用水信息", remarkClass = WaPlanYearWaterDataController.class)
    public Map editWaPlanYearWaterData (final WaPlanYearWaterData vo, HttpServletResponse response,
            final HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
				waPlanYearWaterDataService.updateWaPlanYearWaterData(vo);    
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
    @UserLog(code = "delete", name = "删除年计划用水信息", remarkClass = WaPlanYearWaterDataController.class)
    public Map deleteWaPlanYearWaterData (final Integer planWaterId, HttpServletResponse response,
            final HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
            	waPlanYearWaterDataService.deleteWaPlanYearWaterDataLogic(planWaterId);
            }
        };
        return templete.operate();
    }

	/**
	 * REMARK
	 * 详情信息
	 * Through the id inquires the out a data
	 */
	@RequestMapping(value = "/waPlanYearWaterDataDetail/{id}")
    @UserLog(code = "delete", name = "查看年用水信息详情", remarkClass = WaPlanYearWaterDataController.class)
	public String showWaPlanYearWaterDataDetail (@PathVariable int id,
            HttpServletRequest request, HttpServletResponse response,
            Model model) throws BizException {
        model.addAttribute("planWaterId", id);
        model.addAttribute("waPlanYearWaterData",
         waPlanYearWaterDataService.getWaPlanYearWaterDataById(id));
        model.addAttribute("companyData", waCompanyInfoService.getComPanyMap());

        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                 str = "wa/waterPlanManagement/year/waPlanYearWaterData_detail";
            }
        };
        return templete.operateModel();
    }

    /**
     * REMARK
     * 详情信息
     * Through the id inquires the out a data
     */
    @RequestMapping(value = "/showCompute/{id}")
    public String showCompute (@PathVariable int id,
                                                 HttpServletRequest request, HttpServletResponse response,
                                                 Model model) throws BizException {
        WaPlanYearWaterData entity = waPlanYearWaterDataService.getWaPlanYearWaterDataById(id);
        String companyId = entity.getCompanyId();
        String cmpanyName = entity.getCompanyName();
        model.addAttribute("companyName", cmpanyName);
        model.addAttribute("companyId", companyId);
        WaPlanYearWaterData waterData = new WaPlanYearWaterData();
        waterData.setPlanWaterId(id);
        waterData.setIsDelte(0);
        waterData.setCompanyId(companyId);
        model.addAttribute("waterDataList",
                waPlanYearWaterDataService.getPlanWaterList(waterData));

        model.addAttribute("newYearData", waPlanYearWaterDataService.getNextYear());
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                str = "wa/waterPlanManagement/year/waPlanYearWaterData_compute";
            }
        };
        return templete.operateModel();
    }



    /**
     * 计算
     */
    @RequestMapping("/compute")
    @ResponseBody
    @UserLog(code = "delete", name = "计算下一年用水信息", remarkClass = WaPlanYearWaterDataController.class)
    public Map compute (final String companyId, HttpServletResponse response,
                                        final HttpServletRequest request) {

        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
                WaPlanYearWaterData yearWater = new WaPlanYearWaterData();
                yearWater.setIsDelte(0);
                yearWater.setCompanyId(companyId);
                List<WaPlanYearWaterData> yearList = new ArrayList<>();
                yearList = waPlanYearWaterDataService.getYearWaterList(waPlanYearWaterDataService.getPlanWaterList(yearWater));

                Long avgData = 0L;
                if(yearList.size()>0){
                    Long sumData = 0L;
                    for (WaPlanYearWaterData wa: yearList){
                        sumData += Long.parseLong(wa.getPlanYearAvgWater());
                    }
                    avgData = sumData/yearList.size();
                }
                map.put("result", avgData);

            }
        };
        return templete.operate();
    }



    @RequestMapping("/savePlanWater")
    @ResponseBody
    public Map savePlanWater (final WaPlanYearWaterData vo, HttpServletResponse response,
                        final HttpServletRequest request) {

        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
                WaPlanYearWaterData tmpData = new WaPlanYearWaterData();
                tmpData.setCompanyId(vo.getCompanyId());
                tmpData.setPlanYear(vo.getPlanYear());
                List<WaPlanYearWaterData> tmpList = waPlanYearWaterDataService.getPlanWaterList(tmpData);
                if (tmpList.size()>0){
                    vo.setPlanWaterId(tmpList.get(0).getPlanWaterId());

                    waPlanYearWaterDataService.updateWaPlanYearWaterData(vo);
                }else {
                    waPlanYearWaterDataService.addWaPlanYearWaterData(vo);
                }

            }
        };
        return templete.operate();
    }

    /**
     * REMARK
     * 详情信息
     * Through the id inquires the out a data
     */
    @RequestMapping(value = "/print/{companyId}/{companyName}/{companyCode}/{planYear}/{planYearAvgWater}")
    public String showPrint (@PathVariable String companyId,@PathVariable String companyName,@PathVariable String companyCode,@PathVariable String planYear, @PathVariable String planYearAvgWater,
                               HttpServletRequest request, HttpServletResponse response,
                               Model model) throws BizException {

       WaPlanYearWaterData yearData = new WaPlanYearWaterData();
       yearData.setCompanyId(companyId);
       yearData.setCompanyName(companyName);
       yearData.setCompanyCode(companyCode);
       yearData.setPlanYear(planYear);
        yearData.setPlanYearAvgWater(planYearAvgWater);
        model.addAttribute("yearData", yearData);
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                str = "wa/waterPlanManagement/year/waPlanYearWaterData_print";
            }
        };
        return templete.operateModel();
    }

    @RequestMapping(value = "/showImportExcel")
    public String showImportExcel(HttpServletRequest request,
                                  HttpServletResponse response, Model model) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                str = "wa/waterPlanManagement/year/waPlanYearWaterData_import";
            }
        };
        return templete.operateModel();
    }

    /**
     * excel批量导入数据
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/importExcel")
    @UserLog(code = "delete", name = "批量导入年用水信息", remarkClass = WaPlanYearWaterDataController.class)
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
                    tag = waPlanYearWaterDataService.doReadXls(multipartFile.getInputStream());
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

}
