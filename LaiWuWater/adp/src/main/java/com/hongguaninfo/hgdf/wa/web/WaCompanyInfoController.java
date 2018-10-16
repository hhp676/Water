/*
 * Project Name:hbm-base.
 * File Name:WaCompanyInfoController.java
 * Package Name:com.hongguaninfo.hgdf.bud.web.wa
 * Date:2018年09月10日 下午5:14:26
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
import com.hongguaninfo.hgdf.core.utils.FtpUtil;
import com.hongguaninfo.hgdf.core.utils.Identities;
import com.hongguaninfo.hgdf.core.utils.exception.BaseException;
import com.hongguaninfo.hgdf.core.utils.logging.Log;
import com.hongguaninfo.hgdf.core.utils.logging.LogFactory;
import com.hongguaninfo.hgdf.wa.entity.WaCompanyInfo;
import com.hongguaninfo.hgdf.wa.entity.WaUploadfileData;
import com.hongguaninfo.hgdf.wa.service.WaCompanyInfoService;
import com.hongguaninfo.hgdf.wa.service.WaUploadfileDataService;
import com.hongguaninfo.hgdf.wa.service.totalinfo.WaCommFacilitiesWaterDataService;
import com.hongguaninfo.hgdf.wa.service.totalinfo.WaDormitoryWaterDataService;
import com.hongguaninfo.hgdf.wa.service.totalinfo.WaIndustryWaterDataService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
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
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;


/**
 * WaCompanyInfoController:。 <br />
 * controller 层 <br />
 * Date:2018年09月10日 下午5:14:26
 *
 * @author hhp
 * @since V1.0.0
 */
@Controller
@RequestMapping("/wa/WaCompanyInfo")
public class WaCompanyInfoController {

    private static final Log LOG = LogFactory.getLog(WaCompanyInfoController.class);

    /**
     * Service。
     */
    @Autowired
    private WaCompanyInfoService waCompanyInfoService;
    @Autowired
    private SysDatadicItemService sysDatadicItemService;
    @Autowired
    private WaCommFacilitiesWaterDataService waCommFacilitiesWaterDataService;
    @Autowired
    private WaDormitoryWaterDataService waDormitoryWaterDataService;
    @Autowired
    private WaIndustryWaterDataService waIndustryWaterDataService;
    @Autowired
    private WaUploadfileDataService waUploadfileDataService;

    /**
	 * REMARK
	 * 列表页面。
	 * @param response
	 * @param request
	 */
	@RequestMapping("/showWaCompanyInfo")
	public String showWaCompanyInfo (HttpServletRequest request, HttpServletResponse response, Model model) {

	    OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                str = "wa/waCompanyInfo_show";
            }
        };
        return templete.operateModel();
	}

    /**
     * 信息编辑
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/showWaCompanyInfoEdit")
    public String showWaCompanyInfoEdit (HttpServletRequest request, HttpServletResponse response, Model model) {

        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                str = "wa/waCompanyInfo/edit/waCompanyInfo_edit";
            }
        };
        return templete.operateModel();
    }

    /**
     * 信息录入
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/showWaCompanyInfoInsert")
    public String showWaCompanyInfoInsert (HttpServletRequest request, HttpServletResponse response, Model model) {

        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                str = "wa/waCompanyInfo/insert/waCompanyInfo_insert";
            }
        };
        return templete.operateModel();
    }

    /**
     * 信息查询
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/showWaCompanyInfoSearch")
    public String showWaCompanyInfoSearch (HttpServletRequest request, HttpServletResponse response, Model model) {

        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                str = "wa/waCompanyInfo/search/waCompanyInfo_search";
            }
        };
        return templete.operateModel();
    }

    /**
     * 用水情况统计
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/showWaCompanyInfoWaterTotal")
    public String showWaCompanyInfoWaterTotal (HttpServletRequest request, HttpServletResponse response, Model model) {

        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                str = "wa/waCompanyInfo/waterTotal/waCompanyInfo_waterTotal";
            }
        };
        return templete.operateModel();
    }
    /**
    * 分页数据。
    * @WaCompanyInfo
    * @return 
    */
	@RequestMapping("/list")
    @ResponseBody
    @UserLog(code = "list", name = "查看单位信息操作", remarkClass = WaCompanyInfoController.class)
    public Map getWaCompanyInfoList (final WaCompanyInfo waCompanyInfo , final BasePage pageRequest,
                                     HttpServletResponse response, HttpServletRequest request){
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
                waCompanyInfoService.getWaCompanyInfoList(pageRequest, waCompanyInfo, map);
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
    @UserLog(code = "addWaCompanyInfo", name = "新增单位信息", remarkClass = WaCompanyInfo.class)
    public Map addWaCompanyInfo (@Valid final WaCompanyInfo vo, BindingResult result,
            HttpServletResponse response, final HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request, result) {
            protected void doSomething() throws BizException {
                if (doValidate()) {
                    waCompanyInfoService.addWaCompanyInfo(vo);
                }
            }
        };
        return templete.operate();
    }

	/**
	 * REMARK
	 * 修改
	 */
    @RequestMapping("/update")
    @ResponseBody
    @UserLog(code = "editWaCompanyInfo", name = "修改单位信息", remarkClass = WaCompanyInfo.class)
    public Map editWaCompanyInfo (final WaCompanyInfo vo, HttpServletResponse response,
            final HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
				waCompanyInfoService.updateWaCompanyInfo(vo);    
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
    @UserLog(code = "deleteWaCompanyInfo", name = "删除单位信息操作", remarkClass = Integer.class)
    public Map deleteWaCompanyInfo (final Integer companyId, HttpServletResponse response,
            final HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
            	waCompanyInfoService.deleteWaCompanyInfoLogic(companyId);
            }
        };
        return templete.operate();
    }

	/**
	 * REMARK
	 * 详情信息
	 * Through the id inquires the out a data
	 */
	@RequestMapping(value = "/waCompanyInfoDetail/{companyId}")
    @UserLog(code = "showWaCompanyInfoDetail", name = "查看单位信息详情", remarkClass = WaCompanyInfoController.class)
	public String showWaCompanyInfoDetail (@PathVariable int companyId,
            HttpServletRequest request, HttpServletResponse response,
            Model model) throws BizException {
        model.addAttribute("companyId", companyId);
        model.addAttribute("waCompanyInfo",
         waCompanyInfoService.getWaCompanyInfoById(companyId));
        model.addAttribute("isImportMap",
                sysDatadicItemService.getMapByGroupCode("IS_IMPORT"));
        model.addAttribute("saveWaterTypeMap",
                sysDatadicItemService.getMapByGroupCode("SAVEWATER_TYPE"));
        model.addAttribute("userTypeMap",
                sysDatadicItemService.getMapByGroupCode("USER_TYPE"));
        model.addAttribute("waterTypeMap",
                sysDatadicItemService.getMapByGroupCode("WATER_TYPE"));

        model.addAttribute("uploadFileList", waUploadfileDataService.getFileListByCompanyId(companyId));
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                 str = "wa/waCompanyInfo/waCompanyInfo_detail";
            }
        };
        return templete.operateModel();
    }

    /**
     * REMARK
     * 详情信息
     * Through the id inquires the out a data
     */
    @RequestMapping(value = "/totalInfoDetail/{companyId}")
    public String showTotalInfoDetail (@PathVariable int companyId,
                                           HttpServletRequest request, HttpServletResponse response,
                                           Model model) throws BizException {
        model.addAttribute("companyId", companyId);
        model.addAttribute("commFacData" , waCommFacilitiesWaterDataService.getWaCommFacDataByCompanyId(companyId));
        model.addAttribute("dormitoryWaterData", waDormitoryWaterDataService.getDorDataByCompanyId(companyId));
        model.addAttribute("industryWaterData", waIndustryWaterDataService.getIndustryDataByCompanyId(companyId));
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                str = "wa/waCompanyInfo/waterTotal/waCompanyInfo_totalInfo";
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
                str = "wa/waCompanyInfo/insert/waCompanyInfo_import";
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
    @UserLog(code = "delete", name = "批量导入单位信息操作", remarkClass = WaCompanyInfoController.class)
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
                    tag = waCompanyInfoService.doReadXls(multipartFile.getInputStream());
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
     * 新增统计数据
     */
    @RequestMapping("/addTotalInfo")
    @ResponseBody
    @UserLog(code = "delete", name = "单位信息数据统计", remarkClass = WaCompanyInfoController.class)
    public Map addTotalInfo (@Valid final WaCompanyInfo vo, BindingResult result,
                                 HttpServletResponse response, final HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request, result) {
            protected void doSomething() throws BizException {
                if (doValidate()) {
                    waCompanyInfoService.addWaCompanyInfo(vo);
                }
            }
        };
        return templete.operate();
    }

    /**
     * uploadFile
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/uploadFile/{companyId}")
    @UserLog(code = "uploadFile", name = "上传附件文件", remarkClass = WaCompanyInfoController.class)
    public void uploadFile(@PathVariable String companyId, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map resultMap = new HashMap();
        System.out.println(companyId);
        try { // 1. 创建工厂对象
            FileItemFactory factory = new DiskFileItemFactory();
            // 2. 文件上传核心工具类
            ServletFileUpload upload = new ServletFileUpload(factory);
            // 设置大小限制参数
            upload.setFileSizeMax(10*1024*1024);	// 单个文件大小限制
            upload.setSizeMax(50*1024*1024);		// 总文件大小限制
            upload.setHeaderEncoding("UTF-8");		// 对中文文件编码处理

            // 判断
            if (upload.isMultipartContent(request)) {
                // 3. 把请求数据转换为list集合
                List<FileItem> list = upload.parseRequest(request);
                // 遍历
                for (FileItem item : list){
                    // 判断：普通文本数据
                    if (item.isFormField()){
                        // 获取名称
                        String name = item.getFieldName();
                        // 获取值
                        String value = item.getString();
                        value = new String(value.getBytes("ISO8859-1"), "UTF-8");
                        System.out.println(value);
                    }
                    // 文件表单项
                    else {
                        /******** 文件上传 ***********/
                        // a. 获取文件名称
                        String name = item.getName();
                        // ----处理上传文件名重名问题----
                        // a1. 先得到唯一标记
                        // a2. 拼接文件名

                        // b. 得到上传目录
                        String basePath = "C:\\upload";//request.getServletContext().getRealPath("/upload");
                        // c. 创建要上传的文件对象
                        File fileDir = new File(basePath);
                        fileDir.mkdirs();

                        File file = new File(basePath,name);
                        // d. 上传
                        item.write(file);

                        WaUploadfileData upLoadFileData = new WaUploadfileData();
                        upLoadFileData.setCompanyId(companyId);
                        upLoadFileData.setFileName(file.getName());
                        upLoadFileData.setDirs(file.getPath());
                        upLoadFileData.setIsDelte(0);
                        waUploadfileDataService.addWaUploadfileData(upLoadFileData);
                        resultMap.put("data", file.getName());
                        resultMap.put("dirs", file.getPath());
                        //item.delete();  // 删除组件运行时产生的临时文件
                    }
                }
            }
            resultMap.put("result", "上传成功");
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("result", "上传失败");
        }
        ResponseUtils.renderHtml(response, JSON.toJSONString(resultMap), "encoding:utf-8");
    }


    /**
     * 上传至ftp服务器
     * @param companyId
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/uploadFtpFile/{companyId}")
    @UserLog(code = "uploadFtpFile", name = "上传附件文件", remarkClass = WaCompanyInfoController.class)
    public void uploadFtpFile(@PathVariable String companyId, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map resultMap = new HashMap();
        FileItemFactory factory = new DiskFileItemFactory();
        // 2. 文件上传核心工具类
        ServletFileUpload upload = new ServletFileUpload(factory);
        // 设置大小限制参数
        upload.setFileSizeMax(10*1024*1024);	// 单个文件大小限制
        upload.setSizeMax(50*1024*1024);		// 总文件大小限制
        upload.setHeaderEncoding("UTF-8");		// 对中文文件编码处理
        FileItem item = upload.parseRequest(request).get(0);
        String name = item.getName();
        InputStream itemStream = item.getInputStream();
        String remoteDir = name; //远程文件路径

        try {
            //读取配置文件参数
            Properties pro = new Properties();
            pro.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("env.properties"));
            FtpUtil ftputil = new FtpUtil(
                    pro.getProperty("ftp.hostname"),
                    Integer.parseInt(pro.getProperty("ftp.port")),
                    pro.getProperty("ftp.username"),
                    pro.getProperty("ftp.password"), null);
            //开始ftp操作
            ftputil.login();
            boolean flag = ftputil.upload(itemStream, remoteDir);
            ftputil.closeServer();
            //上传成功
            if (flag){
                WaUploadfileData upLoadFileData = new WaUploadfileData();
                Long fileIdLong = Identities.randomLong();
                int fileId = fileIdLong.intValue();
                upLoadFileData.setFileid(fileId);
                upLoadFileData.setCompanyId(companyId);
                upLoadFileData.setFileName(name);
                upLoadFileData.setDirs(name);
                upLoadFileData.setIsDelte(0);
                waUploadfileDataService.addWaUploadfileData(upLoadFileData);

                resultMap.put("data", name);
                resultMap.put("dirs", name);
                resultMap.put("fileId", fileId);
                resultMap.put("result", "上传成功");
            }

        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("result", "上传失败");
        }
        ResponseUtils.renderHtml(response, JSON.toJSONString(resultMap), "encoding:utf-8");
    }


    /**
     * 下载附件
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/downFtpFile/{fileName}/{downDirs}")
    @UserLog(code = "downFtpFile", name = "下载附件", remarkClass = WaCompanyInfoController.class)
    public void downFtpFile(@PathVariable String fileName, @PathVariable String downDirs, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map resultMap = new HashMap();
        try {
            //读取配置文件参数
            Properties pro = new Properties();
            pro.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("env.properties"));
            FtpUtil ftputil = new FtpUtil(
                    pro.getProperty("ftp.hostname"),
                    Integer.parseInt(pro.getProperty("ftp.port")),
                    pro.getProperty("ftp.username"),
                    pro.getProperty("ftp.password"), null);
            //开始ftp操作
            ftputil.login();

            String remoteDir = "" + fileName;
            String itemStream = downDirs + "\\" + fileName;
            ftputil.download(itemStream, remoteDir);
            resultMap.put("result", "上传成功");
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("result", "上传失败");
        }
        ResponseUtils.renderHtml(response, JSON.toJSONString(resultMap), "encoding:utf-8");
    }

    @RequestMapping("/downLoadFile/{fileName}")
    @UserLog(code = "downLoadFile", name = "downLoadFile", remarkClass = WaCompanyInfoController.class)
    public void downLoadFile(@PathVariable String fileName,HttpServletRequest request,HttpServletResponse response) throws IOException {
        OutputStream os = null;
        Map resultMap = new HashMap();
        try {
            //读取配置文件参数
            Properties pro = new Properties();
            pro.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("env.properties"));
            FtpUtil ftputil = new FtpUtil(
                    pro.getProperty("ftp.hostname"),
                    Integer.parseInt(pro.getProperty("ftp.port")),
                    pro.getProperty("ftp.username"),
                    pro.getProperty("ftp.password"), null);
            //开始ftp操作
            ftputil.login();

            os = response.getOutputStream();
            //弹框保存begin
            response.setContentType("application/vnd.ms-excel; charset=utf-8");
            response.setHeader("Content-Disposition","attachment;filename=" + new String((fileName).getBytes("utf-8"),"iso8859-1"));
            response.setCharacterEncoding("utf-8");
            //弹框保存end
           /* String remoteDir = "" + fileName;
            String itemStream = downDirs + "\\" + fileName;
            File file = new File(fileName);
*/
            byte[] buffer = ftputil.downFileByte(fileName);//根据文件名下载FTP服务器上的文件
            os.write(buffer);
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("result", "上传失败");
        }finally {
            if(os != null) {
                os.close();
            }

        }
        ResponseUtils.renderHtml(response, JSON.toJSONString(resultMap), "encoding:utf-8");
    }


    @RequestMapping("/uploadFileList")
    @ResponseBody
    public Map uploadFileList (final WaUploadfileData waUploadfileData , final BasePage pageRequest,
                                        HttpServletResponse response, HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
                waUploadfileDataService.getWaUploadfileDataList(pageRequest, waUploadfileData, map);
            }
        };
        return templete.operate();
    }

    /**
     * REMARK
     * 删除
     * Through the id delete a data
     */
    @RequestMapping("/delFile")
    @ResponseBody
    @UserLog(code = "delFile", name = "delete WaUploadfileData", remarkClass = Integer.class)
    public Map delFile (final Integer fileId, HttpServletResponse response,
                                       final HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
                waUploadfileDataService.deleteWaUploadfileDataLogic(fileId);
            }
        };
        return templete.operate();
    }

}
