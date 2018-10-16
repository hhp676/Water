package com.hongguaninfo.hgdf.adp.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.hongguaninfo.hgdf.adp.core.Constants;
import com.hongguaninfo.hgdf.adp.core.base.BaseController;
import com.hongguaninfo.hgdf.adp.core.exception.BizException;

/**
 * 
 * @ClassName: AllFilesController
 * @Description: 资料上传下载 Controller
 * @author tfl
 * @date 2015年11月12日
 * 
 */
@Controller
@RequestMapping("/allFiles/files")
public class AllFilesController extends BaseController {
    
    
    
    
    /** 文件上传
     * @param vo
     * @param request
     * @param response
     * @throws BizException 
     */
    @RequestMapping("/uploadFile")
//    @UserLog(code = "uploadFile", name = "上传资料")
    public void uploadFile( HttpServletRequest request, 
            HttpServletResponse response) throws BizException {
        
        HttpSession session = request.getSession();  
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;  
        
        MultipartFile multipartFile = multipartRequest.getFile("docFile");
        
        long  size = multipartFile.getSize();
        long maxSzie = 5*1024*1024;
        System.out.println("文件大小size:"+size);
        String result = "";
        String oldName = multipartFile.getOriginalFilename();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss"); 
        String newName =sdf.format(new Date());
        int lastIndexOf = oldName.lastIndexOf('.');
        if (lastIndexOf > -1) {
            newName += oldName.substring(lastIndexOf);
        }
//        String ctxPath=Constants.imagePath;
//        //String path = PropertyUtils.getPropertyValue("env", "middle.imagePath");
//        //String path = "usr/local/upload/image/bobo/";
//        File dirs = new File(ctxPath);
//        
//        //目录不存在且创建目录失败
//        if (!dirs.exists() && !dirs.mkdirs()) {
//            result = "目录不存在且创建失败，请联系管理员！";
//        } else {
//            InputStream inputStream = null;
//            OutputStream outputStream = null;
//            try {
//                
//                if(maxSzie<size){
//                    result = "error";
//                    result = "上传图片大小不能超过5M!";
//                    System.out.println("result:"+result);
//                }else{
//                    System.out.println("图片可正常上传");
//                    inputStream = multipartFile.getInputStream();
//                    outputStream = new FileOutputStream(ctxPath+ newName);
//                    
//                    byte[] dataAry = new byte[1024];
//                    int data;
//                    while ((data = inputStream.read(dataAry)) > -1) {
//                        outputStream.write(dataAry, 0, data);
//                    }
//                    
//                    result = "success";
//                }
//                
//            } catch (IOException e) {
//                LOG.error(e.getMessage());
//            } finally {
//                if (outputStream != null) {
//                    try {
//                        outputStream.close();
//                    } catch (IOException e) {
//                        LOG.error(e.getMessage());
//                    }
//                }
//                if (inputStream != null) {
//                    try {
//                        inputStream.close();
//                    } catch (IOException e) {
//                        LOG.error(e.getMessage());
//                    }
//                }
//            }
//        }
//            
//        Map resultMap = new HashMap();
//        resultMap.put("result", result);
//        resultMap.put("filePath", ctxPath + newName);
//        resultMap.put("picUrl", ctxPath + newName);
//        ResponseUtils.renderHtml(response, JSON.toJSONString(resultMap),  "encoding:utf-8");
       //  ResponseUtils.renderJson(response, resultMap, "encoding:utf-8");;
    }
    
     
    
    
    /**
     * 根据请求图片的物理路径，将图片流写给img标签
     * @param path
     * @param response
     * @param request
     * @throws BizException
     * @throws UnsupportedEncodingException 
     */
    @RequestMapping("/queryImage")
    public void queryImage(String path, HttpServletResponse response,
            final HttpServletRequest request) throws BizException, UnsupportedEncodingException {
    
        
        File file = new File(path);
        if (file.exists()) {
            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                inputStream = new FileInputStream(file);
                outputStream = response.getOutputStream();

                byte[] dataAry = new byte[1024];
                int data;
                while ((data = inputStream.read(dataAry)) > -1) {
                    outputStream.write(dataAry, 0, data);
                }
            
            } catch (IOException e) {
                LOG.error(e.getMessage());
            } finally {
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        LOG.error(e.getMessage());
                    }
                }
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        LOG.error(e.getMessage());
                    }
                }
            }
        }
    }
}
