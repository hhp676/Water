package com.hongguaninfo.hgdf.generator.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hongguaninfo.hgdf.generator.core.Constants;
import com.hongguaninfo.hgdf.generator.core.base.BaseService;
import com.hongguaninfo.hgdf.generator.entity.FreemarkerTemplate;
import com.hongguaninfo.hgdf.generator.entity.GeneratorConfig;
import com.hongguaninfo.hgdf.generator.entity.Table;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 
 * @author:henry
 */
@Service("generatorService")
public class GeneratorService extends BaseService {

    @Autowired
    MetaService metaService;

    /**
    *
    */
    @Autowired
   private Configuration cfg;
    /**
    *
    */
   private Template sqlMapTemplate;
   /**
    *
    */
   private List<FreemarkerTemplate> templates;
   /**
   *
   */
  private Template jspTemplate;

  /**
   *
   */
  private Template jsTemplate;
    /**
     * 获取所有表信息
     * 
     * @return
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws SQLException
     */
    public List<Table> getTables(GeneratorConfig genConfig) throws InstantiationException, IllegalAccessException,
            ClassNotFoundException, SQLException {
        return (List<Table>) metaService.getTables(genConfig);
    }

    /**
     * 初始化。
     * @throws IOException io异常
     */
    @PostConstruct
    public void init() throws IOException {
        sqlMapTemplate = cfg.getTemplate("sqlmap.ftl");

        templates = new ArrayList<>();
        // 生成entity
        FreemarkerTemplate ft1 = new FreemarkerTemplate();
        Template temp1 = cfg.getTemplate("entity.ftl");
        ft1.setTemplate(temp1);
        ft1.setPkg(Constants.ENTITY_PKG);
        templates.add(ft1);

        // mapper
        FreemarkerTemplate ft2 = new FreemarkerTemplate();
        Template temp2 = cfg.getTemplate("mapper.ftl");
        ft2.setTemplate(temp2);
        ft2.setPkg(Constants.MAPPER_PKG);
        ft2.setSuffix(Constants.MAPPER_SUFFIX);
        templates.add(ft2);

        // dao
        FreemarkerTemplate ft3 = new FreemarkerTemplate();
        Template temp3 = cfg.getTemplate("dao.ftl");
        ft3.setTemplate(temp3);
        ft3.setPkg(Constants.DAO_PKG);
        ft3.setSuffix(Constants.DAO_SUFFIX);
        templates.add(ft3);

        // service interface
        FreemarkerTemplate ft4 = new FreemarkerTemplate();
        Template temp4 = cfg.getTemplate("service1.ftl");
        ft4.setTemplate(temp4);
        ft4.setPkg(Constants.SERVICE1_PKG);
        ft4.setSuffix(Constants.SERVICE1_SUFFIX);
        templates.add(ft4);

        // service interface
//        FreemarkerTemplate ft5 = new FreemarkerTemplate();
//        Template temp5 = cfg.getTemplate("service2.ftl");
//        ft5.setTemplate(temp5);
//        ft5.setPkg(Constants.SERVICE2_PKG);
//        ft5.setSuffix(Constants.SERVICE2_SUFFIX);
//        templates.add(ft5);

        // controller
        FreemarkerTemplate ft6 = new FreemarkerTemplate();
        Template temp6 = cfg.getTemplate("controller.ftl");
        ft6.setTemplate(temp6);
        ft6.setPkg(Constants.CONTROLLER_PKG);
        ft6.setSuffix(Constants.CONTROLLER_SUFFIX);
        templates.add(ft6);

        jspTemplate = cfg.getTemplate("jsp.ftl");
        jsTemplate = cfg.getTemplate("js.ftl");
    }
    /**
     * 根据表名生成文件。
     * @param conf 代码生成配置
     * @param choseTables 要生成代码的表
     * @throws IOException 抛出的IO异常
     * @throws TemplateException 抛出的freemarker异常
     */
    public void doGenerator(GeneratorConfig conf, List<Table> choseTables) throws IOException, TemplateException {
        for (Table table : choseTables) {
            LOG.info("Start generating files of table " + table.getTableName() + ".........");
            Map<String, Object> context = buildContext(conf, table);
            // 生成sqlmap
            createSqlmapper(conf, context, sqlMapTemplate);

            // 生成java
            for (FreemarkerTemplate ft : templates) {
                createClass(conf, context, ft);
            }

            // 生成jsp
            createJsp(conf, context, jspTemplate);
            // 生成js
            createJs(conf, context, jsTemplate);
        }
    }
    /**
     * 构建代码生成需要的上下文。
     * @param conf 代码生成配置
     * @param tm   生成代码的表
     * @return 上下文
     */
    private Map<String, Object> buildContext(GeneratorConfig conf, Table tm) {
        Map<String, Object> context = new HashMap<>();
        context.put("meta", tm);
        context.put("pkgName", conf.getPackageName());
        context.put("author", conf.getAuthor());
        context.put("entityFolder", Constants.ENTITY_PKG + "." + tm.getModule());

        context.put("MapperSuffix", Constants.MAPPER_SUFFIX);
        context.put("mapperFolder", Constants.MAPPER_PKG + "." + tm.getModule());

        context.put("DaoSuffix", Constants.DAO_SUFFIX);
        context.put("daoFolder", Constants.DAO_PKG + "." + tm.getModule());

        context.put("ServiceSuffix", Constants.SERVICE1_SUFFIX);
        context.put("serviceFolder", Constants.SERVICE1_PKG + "." + tm.getModule());

        context.put("ServiceImplSuffix", Constants.SERVICE2_SUFFIX);
        context.put("serviceImplFolder", Constants.SERVICE2_PKG + "." + tm.getModule());

        context.put("ControllerSuffix", Constants.CONTROLLER_SUFFIX);
        context.put("controllerFolder", Constants.CONTROLLER_PKG + "." + tm.getModule());

        // 实体表和关系表按照能否逻辑删除来区分
        context.put("baseEntity", tm.isBizTable() ? "BaseEntity" : "BaseEntity");
        context.put("baseService", tm.isBizTable() ? "BizService" : "RelService");
        context.put("pkCol", tm.getPkCols().get(0));

        context.put("hasFk", tm.getFks().size() > 0);

        context.put("currentDate", Constants.DATE_FORMAT.format(new Date()));
        return context;
    }
    
    /**
     * 生成sqlmap文件。
     * @param conf         代码生成配置
     * @param context      上下文
     * @param template     模板
     * @throws IOException  io异常
     * @throws TemplateException  freemarker异常
     */
    private void createSqlmapper(GeneratorConfig conf, Map<String, Object> context, Template template)
            throws IOException, TemplateException {
        Table tm = (Table) context.get("meta");

        String sqlMapFolder = conf.getOutPath() + "\\mybatis";
        prepareFolder(sqlMapFolder);

        String sqlMapFilePath = sqlMapFolder + "\\" + tm.getClassName() + "-sqlmap.xml";
        process(context, template, sqlMapFilePath);
    }
    
    /**
     * 确认对应文件夹已生成。
     * @param folder 文件夹
     */
    private void prepareFolder(String folder) {
        File fd2 = new File(folder);
        //noinspection ResultOfMethodCallIgnored
        fd2.mkdirs();
    }
    /**
     * 文件生成。
     * @param context    上下文
     * @param template   模板
     * @param filePath   生成路径
     * @throws IOException  io异常
     * @throws TemplateException freemarker异常
     */
    private void process(Map<String, Object> context, Template template, String filePath)
            throws IOException, TemplateException {
        try (FileOutputStream out = new FileOutputStream(filePath, false)) {
            StringWriter writer = new StringWriter();
            template.process(context, writer);
            out.write((writer.toString()).getBytes("utf-8"));
            out.flush();
        }
    }
    
    /**
     * 生成java文件。
     * @param conf      生成配置
     * @param context   上下文
     * @param temp       模板
     * @throws IOException io异常
     * @throws TemplateException freemarker异常
     */
    private void createClass(GeneratorConfig conf, Map<String, Object> context, FreemarkerTemplate temp)
            throws IOException, TemplateException {
        Table tm = (Table) context.get("meta");

        String classFolder = conf.getOutPath() + (conf.getPackageName() + "."
                + temp.getPkg() + "." + tm.getModule()).replaceAll("\\.", "\\\\") + "\\";
        prepareFolder(classFolder);

        String classFilePath = classFolder + tm.getClassName() + temp.getSuffix() + ".java";
        process(context, temp.getTemplate(), classFilePath);
    }
    
    /**
     * 生成jsp文件。
     * @param conf         代码生成配置
     * @param context      上下文
     * @param template     模板
     * @throws IOException  io异常
     * @throws TemplateException  freemarker异常
     */
    private void createJsp(GeneratorConfig conf, Map<String, Object> context, Template template)
            throws IOException, TemplateException {
        Table tm = (Table) context.get("meta");

        String jspFolder = conf.getOutPath() + "\\jsp\\" + tm.getModule();
        prepareFolder(jspFolder);

        String jspFilePath = jspFolder + "\\" + tm.getFirstLowerClassName() + "_show.jsp";
        process(context, template, jspFilePath);
    }
    
    /**
     * 生成js文件。
     * @param conf         代码生成配置
     * @param context      上下文
     * @param template     模板
     * @throws IOException  io异常
     * @throws TemplateException  freemarker异常
     */
    private void createJs(GeneratorConfig conf, Map<String, Object> context, Template template)
            throws IOException, TemplateException {
        Table tm = (Table) context.get("meta");

        String jspFolder = conf.getOutPath() + "\\js\\" + tm.getModule();
        prepareFolder(jspFolder);

        String jspFilePath = jspFolder + "\\" + tm.getFirstLowerClassName() + "_show.js";
        process(context, template, jspFilePath);
    }

 //--------------------------------------------------------------------------------------------   
    
    /**
     * 根据表名生成文件
     * 
     * @param genConfig
     * @param tableName
     * @return
     */
/*    public boolean doGenerator(GeneratorConfig genConfig, String tableName, boolean checkAll) {
        try {
            VelocityEngine engine = new VelocityEngine();
            engine.setProperty(Velocity.RESOURCE_LOADER, "class");
            engine.setProperty("class.resource.loader.class",
                    "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
            engine.init();

            Template template = engine.getTemplate("vm/entity.vm");
            Template tlIbatis = engine.getTemplate("vm/sqlmap.vm");
            Template tlMapper = engine.getTemplate("vm/mapper.vm");
            Template tlDao = engine.getTemplate("vm/dao.vm");
            Template tlBiz = engine.getTemplate("vm/service.vm");
            Template tlAction = engine.getTemplate("vm/controller.vm");

            tlDao.setEncoding("UTF-8");
            tlBiz.setEncoding("UTF-8");
            tlAction.setEncoding("UTF-8");
            template.setEncoding("UTF-8");
            tlIbatis.setEncoding("UTF-8");
            tlMapper.setEncoding("UTF-8");

            String outputPath = genConfig.getOutPath();
            if(checkAll){
                tableName = null;
            }
            List tables = metaService.getTableMetas(genConfig, tableName);
            if (tables.size() > 0) {
                for (int i = 0; i < tables.size(); i++) {
                    Table tm = (Table) tables.get(i);
                    VelocityContext context = new VelocityContext();
                    context.put("meta", tm);
                    StringTokenizer st = new StringTokenizer(tm.getPkgName(), ".");
                    String outJavaPath = outputPath;
                    String outIbatisPath = outputPath + "\\mybatis";
                    while (st.hasMoreTokens()) {
                        String temp = st.nextToken();
                        outJavaPath = outJavaPath + "\\" + temp;
                        File fd = new File(outJavaPath);
                        if (!fd.exists()) {
                            fd.mkdir();
                        }
                        File fd2 = new File(outIbatisPath);
                        if (!fd2.exists()) {
                            fd2.mkdir();
                        }
                    }
                    File fdtmp = new File(outJavaPath + "\\web");
                    if (!fdtmp.exists()) {
                        fdtmp.mkdir();
                    }
                    fdtmp = new File(outJavaPath + "\\service");
                    if (!fdtmp.exists()) {
                        fdtmp.mkdir();
                    }
                    fdtmp = new File(outJavaPath + "\\dao");
                    if (!fdtmp.exists()) {
                        fdtmp.mkdir();
                    }
                    fdtmp = new File(outJavaPath + "\\mapper");
                    if (!fdtmp.exists()) {
                        fdtmp.mkdir();
                    }
                    fdtmp = new File(outJavaPath + "\\entity");
                    if (!fdtmp.exists()) {
                        fdtmp.mkdir();
                    }

                    // 生成entity
                    String outJavaPathEntity = outJavaPath + "\\entity\\" + tm.getClassName() + ".java";
                    StringWriter fw = new StringWriter();
                    template.merge(context, fw);
                    FileOutputStream fdUTF = new FileOutputStream(outJavaPathEntity, false);
                    fdUTF.write((fw.toString()).getBytes("utf-8"));
                    fdUTF.flush();
                    fdUTF.close();

                    // 生成sqlmap
                    String ibatisFileName = outIbatisPath + "\\" + tm.getClassName() + "-sqlmap.xml";
                    // FileWriter fwIbatis = new FileWriter(ibatisFileName,
                    // false);
                    // tlIbatis.merge(context, fwIbatis);
                    // fwIbatis.close();
                    StringWriter fwIbatis = new StringWriter();
                    tlIbatis.merge(context, fwIbatis);
                    fdUTF = new FileOutputStream(ibatisFileName, false);
                    fdUTF.write((fwIbatis.toString()).getBytes("utf-8"));
                    fdUTF.flush();
                    fdUTF.close();

                    // mapper
                    String mapperFileName = outJavaPath + "\\mapper\\" + tm.getClassName() + "Mapper.java";
                    StringWriter fwMapper = new StringWriter();
                    tlMapper.merge(context, fwMapper);
                    fdUTF = new FileOutputStream(mapperFileName, false);
                    fdUTF.write((fwMapper.toString()).getBytes("utf-8"));
                    fdUTF.flush();
                    fdUTF.close();

                    // dao
                    String daoFileName = outJavaPath + "\\dao\\" + tm.getClassName() + "Dao.java";
                    StringWriter fwDao = new StringWriter();
                    tlDao.merge(context, fwDao);
                    fdUTF = new FileOutputStream(daoFileName, false);
                    fdUTF.write((fwDao.toString()).getBytes("utf-8"));
                    fdUTF.flush();
                    fdUTF.close();

                    // biz
                    String bizFileName = outJavaPath + "\\service\\" + tm.getClassName() + "Service.java";
                    StringWriter fwBiz = new StringWriter();
                    tlBiz.merge(context, fwBiz);
                    fdUTF = new FileOutputStream(bizFileName, false);
                    fdUTF.write((fwBiz.toString()).getBytes("utf-8"));
                    fdUTF.flush();
                    fdUTF.close();

                    // action
                    String actionFileName = outJavaPath + "\\web\\" + tm.getClassName() + "Controller.java";
                    StringWriter fwAction = new StringWriter();
                    tlAction.merge(context, fwAction);
                    fdUTF = new FileOutputStream(actionFileName, false);
                    fdUTF.write((fwAction.toString()).getBytes("utf-8"));
                    fdUTF.flush();
                    fdUTF.close();

                }
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            LOG.error("doGenerator fail!", ex);
            return false;
        }
    }*/
}