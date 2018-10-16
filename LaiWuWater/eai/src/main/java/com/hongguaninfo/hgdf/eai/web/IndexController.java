package com.hongguaninfo.hgdf.eai.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hongguaninfo.hgdf.core.utils.logging.Log;
import com.hongguaninfo.hgdf.core.utils.logging.LogFactory;
import com.hongguaninfo.hgdf.eai.core.base.BaseController;

@Controller
@RequestMapping("/")
public class IndexController extends BaseController {

	public Log log = LogFactory.getLog(getClass());

	/**
	 * @Title: home
	 * @Description: 首页跳转
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/")
	public String home(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		return "index";
	}
}