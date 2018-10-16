package com.hongguaninfo.hgdf.generator.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hongguaninfo.hgdf.generator.core.base.BaseController;
import com.hongguaninfo.hgdf.generator.entity.GeneratorConfig;
import com.hongguaninfo.hgdf.generator.entity.Table;
import com.hongguaninfo.hgdf.generator.service.GeneratorService;
import com.hongguaninfo.hgdf.generator.service.MetaService;

@Controller
@RequestMapping("/")
public class IndexController extends BaseController {


    @Autowired
    private GeneratorService generatorService;
    /**
    *
    */
    @Autowired
   private MetaService metaService;
    /**
    *
    */
    @Autowired
   private GeneratorConfig defaultConfig;

    /**
     * @Title: home
     * @Description: 首页跳转
     * @since 1.0.0
     */
    @RequestMapping(value = "/")
    public String home(Model model, String message, HttpSession session) {
    	 GeneratorConfig config = (GeneratorConfig) session.getAttribute("config");
         model.addAttribute("config", config == null ? defaultConfig : config);
        model.addAttribute("message", message);
        return "index";
    }

    /**
     * 选择要生成代码的表。
     * @param model    model
     * @param conf     conf
     * @param session   session
     * @return viewId
     * @throws Exception 异常
     */
    @RequestMapping("/chooseTable")
    public String chooseTable(Model model, GeneratorConfig conf, HttpSession session) throws Exception {
        session.setAttribute("config", conf);
        Set<Table> tables = metaService.getTables(conf).getTables();
        session.setAttribute("allTables", tables);
        model.addAttribute("tables", tables);
        return "chooseTable";
    }

    /**
     * 代码生成。
     * @param config        生成配置
     * @param allTables     所有表
     * @param checkAll      选中所有表
     * @param tableName     手动选择的表
     * @param request       request
     * @return  viewId
     * @throws Exception 异常
     */
    @RequestMapping("/gen")
    public ModelAndView generator(boolean checkAll, String[] tableName,  HttpServletRequest request, HttpSession session) throws Exception {

    	Set<Table> allTables =(Set) session.getAttribute("allTables");
    	GeneratorConfig config =(GeneratorConfig) session.getAttribute("config");
        List<Table> choseTables = (List<Table>) (checkAll ? allTables : new ArrayList<>());
        if (!checkAll) {
            Set<String> choseTableNames = new HashSet<>(Arrays.asList(tableName));
            if(choseTableNames !=null && choseTableNames.size()>0){
            	for(String name :choseTableNames ){
            		for(Table table:allTables){
            			if(name.equals(table.getTableName())){
            				choseTables.add(table);
            				break;
            			}
            		}
            	}
            }
        }
        if(choseTables != null && choseTables.size() >0){
        	for(Table table:choseTables){
	            table.setModule(request.getParameter("__module_" + table.getClassName()));
	            table.setBizTable("1".equals(request.getParameter("__base_" + table.getClassName())));
	            table.setTableAlias(request.getParameter("__alias_" + table.getClassName()));
        	}
        }
        generatorService.doGenerator(config, choseTables);
        return new ModelAndView("redirect:/?message=create ok!");
    }
}