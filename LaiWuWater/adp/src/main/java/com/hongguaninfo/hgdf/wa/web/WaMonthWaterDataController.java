/*
 * Project Name:hbm-base.
 * File Name:WaMonthWaterDataController.java
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
import com.hongguaninfo.hgdf.adp.core.interceptor.RepeatSubmitToken;
import com.hongguaninfo.hgdf.adp.core.templete.HttpTemplete;
import com.hongguaninfo.hgdf.adp.core.templete.OperateTemplete;
import com.hongguaninfo.hgdf.adp.core.utils.ResponseUtils;
import com.hongguaninfo.hgdf.adp.service.sys.SysDatadicItemService;
import com.hongguaninfo.hgdf.core.utils.exception.BaseException;
import com.hongguaninfo.hgdf.core.utils.logging.Log;
import com.hongguaninfo.hgdf.core.utils.logging.LogFactory;
import com.hongguaninfo.hgdf.wa.entity.WaMonthWaterData;
import com.hongguaninfo.hgdf.wa.service.WaCompanyInfoService;
import com.hongguaninfo.hgdf.wa.service.WaMonthWaterDataService;
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
 * WaMonthWaterDataController:。 <br />
 * controller 层 <br />
 * Date:2018年09月11日 下午8:49:44
 *
 * @author hhp
 * @since V1.0.0
 */
@Controller
@RequestMapping("/wa/WaMonthWaterData")
public class WaMonthWaterDataController {

    private static final Log LOG = LogFactory.getLog(WaMonthWaterDataController.class);

    /**
     * Service。
     */
    @Autowired
    private WaMonthWaterDataService waMonthWaterDataService;
    @Autowired
    private SysDatadicItemService sysDatadicItemService;
    @Autowired
    private WaCompanyInfoService waCompanyInfoService;


    /**
	 * REMARK
	 * 列表页面。
	 * @param response
	 * @param request
	 */
	@RequestMapping("/showWaMonthWaterData")
	public String showWaMonthWaterData (HttpServletRequest request, HttpServletResponse response, Model model) {
		OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                str = "wa/waterPlanManagement/month/waMonthWaterData_show_plan";
            }
        };
        return templete.operateModel();
	}

    /**
     * REMARK
     * 实际用水量录入页面。
     * @param response
     * @param request
     */
    @RequestMapping("/showActWaterDataInsert")
    public String showActWaterDataInsert (HttpServletRequest request, HttpServletResponse response, Model model) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                str = "wa/waterDataManagement/insert/waActWaterDataInsert_show";
            }
        };
        return templete.operateModel();
    }

    /**
     * REMARK
     * 实际用水量查询页面。
     * @param response
     * @param request
     */
    @RequestMapping("/showActWaterDataSearch")
    public String showActWaterDataSearch (HttpServletRequest request, HttpServletResponse response, Model model) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                str = "wa/waterDataManagement/search/waActWaterDataSearch_show";
            }
        };
        return templete.operateModel();
    }

    /**
     * REMARK
     * 超计划用水管理。
     * @param response
     * @param request
     */
    @RequestMapping("/showOverPlanWater")
    public String showOverPlanWater (HttpServletRequest request, HttpServletResponse response, Model model) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                str = "wa/totalAnalyze/overPlan/waOverPlanWaterData_show";
            }
        };
        return templete.operateModel();
    }


    /**
    * 分页数据。
    * @WaMonthWaterData
    * @return 
    */
	@RequestMapping("/list")
    @ResponseBody
    @UserLog(code = "getWaMonthWaterDataList", name = "查看用水信息", remarkClass = WaMonthWaterDataController.class)
    public Map getWaMonthWaterDataList (final WaMonthWaterData waMonthWaterData , final BasePage pageRequest,
                                        HttpServletResponse response, HttpServletRequest request, Model model) throws BizException {
        model.addAttribute("isImportMap",
                sysDatadicItemService.getMapByGroupCode("IS_IMPORT"));
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
                waMonthWaterDataService.getWaMonthWaterDataList(pageRequest, waMonthWaterData, map);
            }
        };
        return templete.operate();
    }

    /**
     * 分页查询月计划用水数据。
     * @WaMonthWaterData
     * @return
     */
    @RequestMapping("/planList")
    @ResponseBody
    @UserLog(code = "getWaMonthWaterPlanDataList", name = "查看月计划用水信息", remarkClass = WaMonthWaterDataController.class)
    public Map getWaMonthWaterPlanDataList (final WaMonthWaterData waMonthWaterData , final BasePage pageRequest,
                                        HttpServletResponse response, HttpServletRequest request, Model model) throws BizException {
        model.addAttribute("isImportMap",
                sysDatadicItemService.getMapByGroupCode("IS_IMPORT"));
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
                waMonthWaterDataService.getWaMonthWaterPlanDataList(pageRequest, waMonthWaterData, map);
            }
        };
        return templete.operate();
    }

    /**
     * 分页查询月实际用水数据。
     * @WaMonthWaterData
     * @return
     */
    @RequestMapping("/actList")
    @ResponseBody
    @UserLog(code = "getWaMonthWaterActDataList", name = "查看月实际用水信息", remarkClass = WaMonthWaterDataController.class)
    public Map getWaMonthWaterActDataList (final WaMonthWaterData waMonthWaterData , final BasePage pageRequest,
                                            HttpServletResponse response, HttpServletRequest request, Model model) throws BizException {
        model.addAttribute("isImportMap",
                sysDatadicItemService.getMapByGroupCode("IS_IMPORT"));
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
                waMonthWaterDataService.getWaMonthWaterActDataList(pageRequest, waMonthWaterData, map);
            }
        };
        return templete.operate();
    }

    /**
     * REMARK
     *
     * 新增月计划用水
     */
    @RequestMapping("/planAdd")
    @ResponseBody
    @RepeatSubmitToken(removeToken = true)
    @UserLog(code = "addWaMonthPlanWaterData", name = "新增月计划用水", remarkClass = WaMonthWaterDataController.class)
    public Map addWaMonthPlanWaterData (@Valid final WaMonthWaterData vo, BindingResult result,
                                    HttpServletResponse response, final HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request, result) {
            protected void doSomething() throws BizException {
                if (doValidate()) {
                    waMonthWaterDataService.addWaMonthWaterData(vo, "plan");
                }
            }
        };
        return templete.operate();
    }

    /**
     * REMARK
     * 修改月计划用水
     */
    @RequestMapping("/planUpdate")
    @ResponseBody
    @UserLog(code = "editWaMonthPlanWaterData", name = "修改月计划用水", remarkClass = WaMonthWaterDataController.class)
    public Map editWaMonthPlanWaterData (final WaMonthWaterData vo, HttpServletResponse response,
                                     final HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
                waMonthWaterDataService.updateWaMonthWaterData(vo, "plan");
            }
        };
        return templete.operate();
    }

    /**
     * REMARK
     *
     * 新增月实际用水
     */
    @RequestMapping("/actAdd")
    @ResponseBody
    @RepeatSubmitToken(removeToken = true)
    @UserLog(code = "addWaMonthActWaterData", name = "新增月实际用水", remarkClass = WaMonthWaterDataController.class)
    public Map addWaMonthActWaterData (@Valid final WaMonthWaterData vo, BindingResult result,
                                        HttpServletResponse response, final HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request, result) {
            protected void doSomething() throws BizException {
                if (doValidate()) {
                    waMonthWaterDataService.addWaMonthWaterData(vo, "act");
                }
            }
        };
        return templete.operate();
    }

    /**
     * REMARK
     * 修改月计划用水
     */
    @RequestMapping("/actUpdate")
    @ResponseBody
    @UserLog(code = "editWaMonthActWaterData", name = "修改月实际用水", remarkClass = WaMonthWaterDataController.class)
    public Map editWaMonthActWaterData (final WaMonthWaterData vo, HttpServletResponse response,
                                         final HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
                waMonthWaterDataService.updateWaMonthWaterData(vo, "act");
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
    @UserLog(code = "addWaMonthWaterData", name = "新增用水信息", remarkClass = WaMonthWaterDataController.class)
    public Map addWaMonthWaterData (@Valid final WaMonthWaterData vo, BindingResult result,
            HttpServletResponse response, final HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request, result) {
            protected void doSomething() throws BizException {
                if (doValidate()) {
                    waMonthWaterDataService.addWaMonthWaterData(vo, "");
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
    @UserLog(code = "editWaMonthWaterData", name = "update WaMonthWaterData", remarkClass = WaMonthWaterDataController.class)
    public Map editWaMonthWaterData (final WaMonthWaterData vo, HttpServletResponse response,
            final HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
				waMonthWaterDataService.updateWaMonthWaterData(vo, "");
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
    @UserLog(code = "deleteWaMonthWaterData", name = "删除用水信息", remarkClass = WaMonthWaterDataController.class)
    public Map deleteWaMonthWaterData (final Integer monthWaterId, HttpServletResponse response,
            final HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
            	waMonthWaterDataService.deleteWaMonthWaterDataLogic(monthWaterId, "");
            }
        };
        return templete.operate();
    }

    /**
     * REMARK
     * 删除
     * Through the id delete a data
     */
    @RequestMapping("/planDelete")
    @ResponseBody
    @UserLog(code = "deleteWaMonthWaterPlanData", name = "删除月计划用水信息", remarkClass = WaMonthWaterDataController.class)
    public Map deleteWaMonthWaterPlanData (final Integer monthWaterId, HttpServletResponse response,
                                       final HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
                waMonthWaterDataService.deleteWaMonthWaterDataLogic(monthWaterId, "plan");
            }
        };
        return templete.operate();
    }

    /**
     * REMARK
     * 删除
     * Through the id delete a data
     */
    @RequestMapping("/actDelete")
    @ResponseBody
    @UserLog(code = "deleteWaMonthWaterActData", name = "删除月实际用水信息", remarkClass = WaMonthWaterDataController.class)
    public Map deleteWaMonthWaterActData (final Integer monthWaterId, HttpServletResponse response,
                                       final HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
                waMonthWaterDataService.deleteWaMonthWaterDataLogic(monthWaterId, "act");
            }
        };
        return templete.operate();
    }

	/**
	 * REMARK
	 * 详情信息
	 * Through the id inquires the out a data
	 */
	@RequestMapping(value = "/waMonthWaterDataDetail/{monthWaterId}/{viewType}")
    @UserLog(code = "showWaMonthWaterDataDetail", name = "查看用水信息", remarkClass = WaMonthWaterDataController.class)
    public String showWaMonthWaterDataDetail (@PathVariable int monthWaterId, @PathVariable final String viewType,
            HttpServletRequest request, HttpServletResponse response,
            Model model) throws BizException {
        model.addAttribute("monthWaterId", monthWaterId);
        WaMonthWaterData entity = new WaMonthWaterData();

        model.addAttribute("companyData", waCompanyInfoService.getComPanyMap());

        entity.setMonthWaterId(monthWaterId);
        entity.setIsDelte(0);
        WaMonthWaterData tmpEntity =  waMonthWaterDataService.getWaMonthWaterDataById(entity);
        model.addAttribute("waMonthWaterData",  tmpEntity);
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                if ("plan".equals(viewType)){  //月计划用水管理模块
                    str = "wa/waterPlanManagement/month/waMonthWaterData_detail_plan";
                }else if ("actInsert".equals(viewType)){  //月实际用水录入
                    str = "wa/waterDataManagement/insert/waActWaterDataInsert_detail_insert";
                }else if("actSearch".equals(viewType)){
                    str = "wa/waterDataManagement/search/waActWaterDataSearch_detail_search";
                }else if("overPlan".equals(viewType)){
                    str = "wa/totalAnalyze/overPlan/waOverPlanWaterData_detail";
                }
            }
        };
        return templete.operateModel();
    }

    /**
     * REMARK
     * 详情信息
     * Through the id inquires the out a data
     */
    @RequestMapping(value = "/waMonthWaterPlanDataDetail/{monthWaterId}")
    @UserLog(code = "showWaMonthWaterPlanDataDetail", name = "查看月计划用水信息", remarkClass = WaMonthWaterDataController.class)
    public String showWaMonthWaterPlanDataDetail (@PathVariable int monthWaterId,
                                              HttpServletRequest request, HttpServletResponse response,
                                              Model model) throws BizException {
        model.addAttribute("monthWaterId", monthWaterId);
        WaMonthWaterData entity = new WaMonthWaterData();

        model.addAttribute("companyData", waCompanyInfoService.getComPanyMap());

        entity.setMonthWaterId(monthWaterId);
        entity.setIsDelte(0);
        WaMonthWaterData tmpEntity =  waMonthWaterDataService.getWaMonthWaterDataById(entity);
        model.addAttribute("waMonthWaterData",  tmpEntity);
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                str = "wa/waMonthWaterData_detail";
            }
        };
        return templete.operateModel();
    }


    /**
     * REMARK
     * 打印功能
     * Through the id inquires the out a data
     */
    @RequestMapping(value = "/waterDataPrint/{monthWaterId}")
    @UserLog(code = "waterDataPrint", name = "打印用水信息操作", remarkClass = WaMonthWaterDataController.class)
    public String waterDataPrint(@PathVariable int monthWaterId,
                                              HttpServletRequest request, HttpServletResponse response,
                                              Model model) throws BizException {
        model.addAttribute("monthWaterId", monthWaterId);
        WaMonthWaterData entity = new WaMonthWaterData();
        model.addAttribute("companyData", waCompanyInfoService.getComPanyMap());

        entity.setMonthWaterId(monthWaterId);
        entity.setIsDelte(0);
        model.addAttribute("waMonthWaterData",
                waMonthWaterDataService.getWaMonthWaterDataById(entity));
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                str = "wa/waterDataManagement/search/waMonthWaterData_print";
            }
        };
        return templete.operateModel();
    }

    /**
     * 批量导入页面展示
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/showImportExcel")
    public String showImportExcel(HttpServletRequest request,
                                  HttpServletResponse response, Model model) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                str = "wa/waterPlanManagement/month/waMonthWaterData_import";
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
    @UserLog(code = "importExcel", name = "批量导入用水信息", remarkClass = WaMonthWaterDataController.class)
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
                    tag = waMonthWaterDataService.doReadXls(multipartFile.getInputStream());
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

    /**
     * 汇总页面展示
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/showsumExcel")
    public String showsumExcel(HttpServletRequest request,
                                  HttpServletResponse response, Model model) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                str = "wa/waterPlanManagement/month/waMonthWaterData_sumExcel";
            }
        };
        return templete.operateModel();
    }


    @RequestMapping("/getSumExcel")
    @UserLog(code = "getSumExcel", name = "导入用水信息", remarkClass = WaMonthWaterDataController.class)
    public void getSumExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String result = "success";
        boolean tag = true;//doReadXls返回结果的标识
        try {
            MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
            MultipartHttpServletRequest multipartRequest = resolver.resolveMultipart(request);
            List<MultipartFile> multipartFileList = multipartRequest.getFiles("excelFile");
            List<WaMonthWaterData> waterList = new ArrayList<>();
            List<String> errList = new ArrayList<>();

            for (MultipartFile file : multipartFileList) {
                String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
                if (!".xls".equals(suffix)) {
                    errList.add(file.getOriginalFilename()+" 文件格式有误");
                    continue;
                }

                WaMonthWaterData resEntity = waMonthWaterDataService.getTagFile(file);
                if (null != resEntity){ //获取到的非空对象
                    WaMonthWaterData checkData = waMonthWaterDataService.getWaMonthWaterDataById(resEntity);
                    if(null != checkData){ //判断数据库是否存在此对象
                        resEntity.setMonthWaterId(checkData.getMonthWaterId());
                        resEntity.setActMonthWater(checkData.getActMonthWater());
                        //存在情况下更新
                        waMonthWaterDataService.updateWaMonthWaterData(resEntity, "");
                    }else {
                        //不存在情况下新增
                        waMonthWaterDataService.addWaMonthWaterData(resEntity, "");
                    }
                }
            }

            if (errList.size()>0){
                result = errList.toString();
            }

        } catch (Exception ex) {
            result = "导入失败，请检查正确性。";
            ex.printStackTrace();
        }
        Map resultMap = new HashMap();
        resultMap.put("result",result);
        ResponseUtils.renderHtml(response, JSON.toJSONString(resultMap), "encoding:utf-8");
    }

}
