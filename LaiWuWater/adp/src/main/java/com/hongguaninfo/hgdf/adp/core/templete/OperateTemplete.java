package com.hongguaninfo.hgdf.adp.core.templete;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.hongguaninfo.hgdf.adp.core.exception.BizException;
import com.hongguaninfo.hgdf.adp.core.utils.ResponseUtils;
import com.hongguaninfo.hgdf.core.utils.exception.BaseException;
import com.hongguaninfo.hgdf.core.utils.logging.Log;
import com.hongguaninfo.hgdf.core.utils.logging.LogFactory;

/**
 * web 层封装抽象类
 * 
 * @author henry
 * 
 */
public abstract class OperateTemplete {

    public static final Log LOG = LogFactory.getLog(OperateTemplete.class);

    protected String str;
    protected Map<String, Object> map = new HashMap<String, Object>();
    protected List<Object> list = new ArrayList<Object>();
    protected List<BindingResult> validResultList = new ArrayList<BindingResult>();

    /**
     * 设置map值
     * 
     * @param key
     * @param value
     */
    public void put(String key, Object value) {
        map.put(key, value);
    }

    /**
     * 输出xml
     * 
     * @param response
     */
    public void operate(HttpServletResponse response) {
        try {
            doSomething();
        } catch (Exception e) {
            LOG.error("operate fail!", e);
            str = returnFail(getExcptionMessage(e));
        }
        ResponseUtils.renderXml(response, str);
    }

    /**
     * 配合@ResponseBody输出json
     * 
     * @return
     */
    public Map<String, Object> operate() {
        try {
            doSomething();
            map.put("success", true);
        } catch (Exception e) {
            LOG.error("operate fail!", e);
            map.put("success", false);
            map.put("data", getExcptionMessage(e));
        }
        return map;
    }

    /**
     * 配合@ResponseBody输出json 只输出结果list
     * 
     * @return
     */
    public List<Object> operateList() {
        try {
            doSomething();
        } catch (Exception e) {
            LOG.error("operateList fail!", e);
        }
        return list;
    }
    
    /**
     * view
     * 
     * @return
     */
    public String operateModel() {
        try {
            doSomething();
        } catch (Exception e) {
            LOG.error("operateModel fail!", e);
        }
        return str;
    }

    /**
     * 输出视图
     * 
     * @param viewName
     * @return
     */
    public ModelAndView operateView(String viewName) {
        try {
            doSomething();
        } catch (Exception e) {
            LOG.error("operateView fail!", e);
            str = returnFail(getExcptionMessage(e));
        }
        return new ModelAndView(viewName, map);
    }

    /**
     * 输出excel
     * 
     * @param viewName
     * @return
     */
    public ModelAndView operateExcel(String viewName) {
        return operateView(viewName);
    }

    /**
     * 输出pdf
     * 
     * @param viewName
     * @return
     */
    public ModelAndView operatePdf(String viewName) {
        return operateView(viewName);
    }

    /**
     * 输出文件
     * 
     * @param viewName
     * @return
     */
    public void operateFile(HttpServletResponse response) {
        try {
            doSomething();
            String fileName = map.get("fileName").toString();
            byte[] data = (byte[]) map.get("fileData");
            ResponseUtils.renderFile(response, fileName, data);
        } catch (Exception e) {
            LOG.error("operateFile fail!", e);
            str = returnFail(getExcptionMessage(e));
        }
    }

    /**
     * 返回成功
     * 
     * @return
     */
    private String returnSuccess() {
        return "ok";
    }

    /**
     * 返回错误信息
     * 
     * @param msg
     * @return
     */
    private String returnFail(String msg) {
        return msg;
    }

    /**
     * @Title: doValidate
     * @Description: 校验接口
     * @since 1.0.0
     */
    protected boolean doValidate() throws BizException {
        if (validResultList != null) {
            List<HashMap<String, String>> errorList = new ArrayList<HashMap<String, String>>();
            for (BindingResult result : validResultList) {
                if (result.hasErrors()) {
                    HashMap<String, String> errorMap = new HashMap<String, String>();
                    errorMap.put("object", result.getObjectName());
                    errorMap.put("field", result.getFieldError().getField());
                    errorMap.put("message", result.getFieldError()
                            .getDefaultMessage());
                    errorList.add(errorMap);
                }
            }
            if (errorList.size() > 0) {
                throw new BizException(JSON.toJSONString(errorList));
            }
        }
        return true;
    }

    /**
     * action层 统一调用方法
     * 
     * @throws BaseException
     */
    protected abstract void doSomething() throws BaseException;
    
    /**
     * 异常信息（当范围message长度大于100时只显示指定内容）
     * @param e
     * @return
     */
    private String getExcptionMessage(Exception e){
        if(String.valueOf(e.getMessage()).length() > 100){
            return "服务器处理异常！";
        }else{            
            return e.getMessage();
        }
    }
    
    
}
