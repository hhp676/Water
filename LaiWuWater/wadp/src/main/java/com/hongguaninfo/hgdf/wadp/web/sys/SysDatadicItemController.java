package com.hongguaninfo.hgdf.wadp.web.sys;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hongguaninfo.hgdf.wadp.core.aop.log.UserLog;
import com.hongguaninfo.hgdf.wadp.core.base.BasePage;
import com.hongguaninfo.hgdf.wadp.core.exception.BizException;
import com.hongguaninfo.hgdf.wadp.core.interceptor.RepeatSubmitToken;
import com.hongguaninfo.hgdf.wadp.core.templete.HttpTemplete;
import com.hongguaninfo.hgdf.wadp.core.templete.OperateTemplete;
import com.hongguaninfo.hgdf.wadp.entity.sys.SysDatadicItem;
import com.hongguaninfo.hgdf.wadp.service.sys.SysDatadicItemService;
import com.hongguaninfo.hgdf.core.utils.exception.BaseException;

@Controller
@RequestMapping("/sys/datadic/item")
public class SysDatadicItemController {

    @Autowired
    private SysDatadicItemService sysDatadicItemBiz;

    @RequestMapping(value = "/showSysDatadicItem")
    public String showSysDatadicItem(HttpServletRequest request,
            HttpServletResponse response, Model model) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                str = "sys/sysDatadicItem_show";
            }
        };
        return templete.operateModel();
    }

    @RequestMapping(value = "/sysDatadicItemDetail/{itemId}")
    public String showSysDatadicItemDetail(@PathVariable int itemId,
            HttpServletRequest request, HttpServletResponse response,
            Model model) {
        model.addAttribute("itemId", itemId);
        model.addAttribute("itemObj",
                sysDatadicItemBiz.getDatadicItemById(itemId));
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                str = "sys/sysDatadicItem_detail";
            }
        };
        return templete.operateModel();
    }

    @RequestMapping("/list/{groupCode}")
    @ResponseBody
    public Map getSysDatadicItemList(@PathVariable final String groupCode,
            HttpServletResponse response, HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
                Map<String, String> items = sysDatadicItemBiz
                        .getMapByGroupCode(groupCode);
                map.put("items", items);
            }
        };
        return templete.operate();
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map getSysDatadicItemList(final SysDatadicItem vo,
            final BasePage pageRequest, HttpServletResponse response,
            HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
                sysDatadicItemBiz.getSysDatadicList(pageRequest, vo, map);
            }
        };
        return templete.operate();
    }

    @RequestMapping("/add")
    @ResponseBody
    @RepeatSubmitToken(removeToken = true)
    @UserLog(code = "addSysDatadicItem", name = "增加字典项", remarkClass = SysDatadicItem.class)
    public Map addSysDatadicItem(@Valid final SysDatadicItem vo,
            BindingResult result, HttpServletResponse response,
            final HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request, result) {
            protected void doSomething() throws BizException {
                sysDatadicItemBiz.insertDatadicItem(vo);
            }
        };
        return templete.operate();
    }

    @RequestMapping("/edit")
    @ResponseBody
    @UserLog(code = "editSysDatadicItem", name = "修改字典项", remarkClass = SysDatadicItem.class)
    public Map editSysDatadicItem(final SysDatadicItem vo,
            HttpServletResponse response, final HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
                sysDatadicItemBiz.updateDatadicItem(vo);
            }
        };
        return templete.operate();
    }

    @RequestMapping("/delete")
    @ResponseBody
    @UserLog(code = "deleteSysDatadicItem", name = "删除字典项", remarkClass = Integer.class)
    public Map deleteSysDatadicItem(final Integer id,
            HttpServletResponse response, final HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
                sysDatadicItemBiz.deleteById(id);
            }
        };
        return templete.operate();
    }

}
