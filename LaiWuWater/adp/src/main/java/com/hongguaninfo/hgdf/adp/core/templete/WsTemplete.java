package com.hongguaninfo.hgdf.adp.core.templete;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hongguaninfo.hgdf.core.utils.exception.BaseException;
import com.hongguaninfo.hgdf.core.utils.logging.Log;
import com.hongguaninfo.hgdf.core.utils.logging.LogFactory;

/**
 * web 层封装类
 *
 * @author henry
 *
 */
public abstract class WsTemplete {
    public static final Log LOG = LogFactory.getLog(OperateTemplete.class);
    protected Map<String, Object> map = new HashMap<>();
    private String in;
    public String param;

    /**
     * 构造函数
     *
     * @param
     */
    public WsTemplete(String in) {
        this.in = in;
        LOG.debug("received json:"+in);
    }

    @SuppressWarnings("rawtypes")
    public String operate() {
        try {
        	 if(StringUtils.isBlank(in)){
        		 map.put("errorData", "false");
             }else{
             	JSONObject objStr = JSONObject.parseObject(in);
                 if(objStr.containsKey("param")){
                     param = objStr.get("param").toString().trim();
                     doSomething();
                 }else{
                     map.put("errorData", "false");
                 }
             }
        } catch (Exception e) {
            map.put("errorData", getExcptionMessage(e));
        }
        return JSON.toJSONString(map);
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
//        if(String.valueOf(e.getMessage()).length() > 1000){
//            return "服务器处理异常！";
//        }else{
            return e.getMessage();
//        }
    }
}
