package com.hongguaninfo.hgdf.generator.core.exception.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.hongguaninfo.hgdf.core.utils.logging.Log;
import com.hongguaninfo.hgdf.core.utils.logging.LogFactory;

/**
 * User: henry
 * Date: 14-6-17 下午3:38
 * Version: 1.0
 */
@ControllerAdvice
public class DefaultExceptionResolver implements HandlerExceptionResolver {

    private static final Log LOG = LogFactory.getLog(DefaultExceptionResolver.class);
    

    public ModelAndView resolveException(HttpServletRequest req, HttpServletResponse resp, Object object, Exception ex) {
        LOG.error("Catch Exception: ",ex);
        Map<String,Object> map = new HashMap<String,Object>();  
        StringPrintWriter strintPrintWriter = new StringPrintWriter();  
        map.put("errorMsg", strintPrintWriter.getString());
        
        return new ModelAndView("error/500", map);
    }

}
