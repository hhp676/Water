package com.hongguaninfo.hgdf.adp.core.exception.web;

import com.hongguaninfo.hgdf.adp.core.utils.ResponseUtils;
import com.hongguaninfo.hgdf.core.utils.logging.Log;
import com.hongguaninfo.hgdf.core.utils.logging.LogFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * User: henry
 * Date: 14-6-17 下午3:38
 * Version: 1.0
 */
@ControllerAdvice
public class DefaultExceptionResolver implements HandlerExceptionResolver {

    private static final Log LOG = LogFactory.getLog(DefaultExceptionResolver.class);


    /**
     * resolveException.
     * @param req req
     * @param resp resp
     * @param object object
     * @param ex ex
     * @return ModelAndView
     */
    public ModelAndView resolveException(HttpServletRequest req, HttpServletResponse resp, Object object, Exception ex) {
        LOG.error("Catch Exception: ", ex);
        Map<String, Object> map = new HashMap<String, Object>();
        StringPrintWriter strintPrintWriter = new StringPrintWriter();
        map.put("errorMsg", strintPrintWriter.getString());
        map.put("data", ex.getMessage());
        ModelAndView errorView = new ModelAndView();
        resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

        /**
         * shiro异常返回无权限。
         */
        if (isUnauthorizedException(ex)) {
            resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
            if (isAjax(req)) {
            	map.put("errorMsg", "访问权限问题，请与管理员联系");
                ResponseUtils.renderJson(resp, map, "encoding:utf-8");
            } else {
                return new ModelAndView("error/403", map);
            }
        } else {
            if (isAjax(req)) {
                ResponseUtils.renderJson(resp, map, "encoding:utf-8");
            } else {
                errorView.setViewName("error/500");
                errorView.addAllObjects(map);
            }
        }
        return errorView;
    }

    /**
     * 是否是权限异常。
     * @param ex ex
     * @return
     */
    private boolean isUnauthorizedException(Exception ex) {
        return (ex instanceof org.apache.shiro.authz.UnauthorizedException);
    }

    /**
     * 是否是AJAX请求。
     * @param request request
     * @return
     */
    private boolean isAjax(HttpServletRequest request) {
        return "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"));
    }

}
