/*
 * Project Name:hbm-base.
 * File Name:${meta.className}${ControllerSuffix}.java
 * Package Name:${pkgName}.${controllerFolder}
 * Date:${currentDate}
 * Copyright (c) 2016, hongguaninfo.com All Rights Reserved.
 *
 */
package ${pkgName}.${controllerFolder};

import ${pkgName}.${entityFolder}.${meta.className};
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hongguaninfo.hgdf.adp.core.aop.log.UserLog;
import com.hongguaninfo.hgdf.adp.core.base.BasePage;
import com.hongguaninfo.hgdf.adp.core.exception.BizException;
import com.hongguaninfo.hgdf.core.utils.exception.BaseException;
import com.hongguaninfo.hgdf.adp.core.interceptor.RepeatSubmitToken;
import com.hongguaninfo.hgdf.adp.core.templete.HttpTemplete;
import com.hongguaninfo.hgdf.adp.core.templete.OperateTemplete;
import com.hongguaninfo.hgdf.adp.shiro.conf.Auths;
import com.hongguaninfo.hgdf.core.utils.logging.LogFactory;


/**
 * ${meta.className}Controller:${meta.tableDesc}。 <br />
 * controller 层 <br />
 * Date:${currentDate}
 *
 * @author ${author}
 * @since V1.0.0
 */
@Controller
@RequestMapping("/${meta.module}/${meta.className}")
public class ${meta.className}${ControllerSuffix} {

    /**
     *
     */
    private static final Log LOG = LogFactory.getLog(${meta.className}${ControllerSuffix}.class);

    /**
     * Service。
     */
    @Autowired
    private ${meta.className}${ServiceSuffix} ${meta.firstLowerClassName}${ServiceSuffix};

    /**
	 * REMARK
	 * 列表页面。
	 * @param pageRequest
	 * @param response
	 * @param request
	 */
	@RequestMapping("/show${meta.className}")
	@RequiresPermissions(Auths.${meta.tableNameUC})
	public String show${meta.className} (HttpServletRequest request, HttpServletResponse response, Model model) {
		OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                str = "${meta.module}/${meta.className}/${meta.firstLowerClassName}_show";
            }
        };
        return templete.operateModel();
	}
    /**
    * 分页数据。
    * @${meta.className}
    * @return 
    */
	@RequestMapping("/list")
    @ResponseBody
    @RequiresPermissions(Auths.${meta.tableNameUC})
    public Map get${meta.className}List (final ${meta.className} ${meta.firstLowerClassName} , final BasePage pageRequest,
            HttpServletResponse response, HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
                ${meta.firstLowerClassName}${ServiceSuffix}.get${meta.className}List(pageRequest, ${meta.firstLowerClassName}, map);
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
    @RequiresPermissions(Auths.${meta.tableNameUC}_ADD)
    @UserLog(code = "add${meta.className}", name = "add ${meta.className}", remarkClass = ${meta.className}.class)
    public Map add${meta.className} (@Valid final ${meta.className} vo, BindingResult result,
            HttpServletResponse response, final HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request, result) {
            protected void doSomething() throws BizException {
                if (doValidate()) {
                    ${meta.firstLowerClassName}${ServiceSuffix}.add${meta.className}(vo);
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
    @RequiresPermissions(Auths.${meta.tableNameUC}_EDIT)
    @UserLog(code = "edit${meta.className}", name = "update ${meta.className}", remarkClass = ${meta.className}.class)
    public Map edit${meta.className} (final ${meta.className} vo, HttpServletResponse response,
            final HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
				${meta.firstLowerClassName}${ServiceSuffix}.update${meta.className}(vo);    
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
    @RequiresPermissions(Auths.${meta.tableNameUC}_DELETE)
    @UserLog(code = "delete${meta.className}", name = "delete ${meta.className}", remarkClass = Integer.class)
    public Map delete${meta.className} (final Integer id, HttpServletResponse response,
            final HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
            	${meta.firstLowerClassName}${ServiceSuffix}.delete${meta.className}Logic(id);    
            }
        };
        return templete.operate();
    }

	/**
	 * REMARK
	 * 详情信息
	 * Through the id inquires the out a data
	 */
	@RequestMapping(value = "/${meta.firstLowerClassName}Detail/{id}")
    @RequiresPermissions(Auths.${meta.tableNameUC}_VIEW)
	public String show${meta.className}Detail (@PathVariable int id,
            HttpServletRequest request, HttpServletResponse response,
            Model model) throws BizException {
        model.addAttribute("id", id);
        model.addAttribute("${meta.firstLowerClassName}",
         ${meta.firstLowerClassName}${ServiceSuffix}.get${meta.className}ById(id));
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                 str = "${meta.module}/${meta.className}/${meta.firstLowerClassName}_detail";
            }
        };
        return templete.operateModel();
    }
}
