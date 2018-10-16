/*
 * File Name:SysMetaMethodService.java
 * Package Name:com.hongguaninfo.hgdf.bud.service.sys
 * Date:2018年01月03日 下午2:16:08
 * Copyright (c) 2016, hongguaninfo.com All Rights Reserved.
 *
 */
package com.hongguaninfo.hgdf.adp.service.sys;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.hongguaninfo.hgdf.adp.core.Constants;
import com.hongguaninfo.hgdf.adp.core.base.BasePage;
import com.hongguaninfo.hgdf.adp.core.exception.BizException;
import com.hongguaninfo.hgdf.adp.core.utils.SessionUtils;
import com.hongguaninfo.hgdf.adp.core.utils.generator.DbIdGenerator;
import com.hongguaninfo.hgdf.adp.core.utils.spring.WebBeanUtil;
import com.hongguaninfo.hgdf.adp.dao.sys.SysMetaMethodDao;
import com.hongguaninfo.hgdf.adp.entity.sys.SysMetaMethod;
import com.hongguaninfo.hgdf.core.utils.StringUtil;
import com.hongguaninfo.hgdf.core.utils.logging.Log;
import com.hongguaninfo.hgdf.core.utils.logging.LogFactory;
import com.hongguaninfo.hgdf.core.utils.page.Page;
import com.hongguaninfo.hgdf.core.utils.props.config.Config;

/**
 * 系统元方法表: sys_meta_method. <br />
 * service interface 层 <br />
 * Date: 2018年01月03日 下午2:16:08 <br />
 * @author  zhxl
 * @since V1.0.0
 */
@Service("sysMetaMethodService")
public class SysMetaMethodService {
    
    private static final Log      LOG = LogFactory.getLog(SysMetaMethodService.class);
    
    @Autowired
    private SysMetaMethodDao      sysMetaMethodDao;
    
    @Autowired
    private DbIdGenerator         dbIdGenerator;
    
    @Autowired
    private SysDatadicItemService sysDatadicItemBiz;
    
    @Config("sysMetaMethod.data.no.init.package")
    private String                noInitPackageNames;
    
    @Config("sysMetaMethod.data.no.init.class")
    private String                noInitClassNames;
    
    /**
     * REMARK
     * 分页查询
     * Show all content and can paging
     * The following id to give priority to key !
     */
    public List<SysMetaMethod> getSysMetaMethodList(BasePage<SysMetaMethod> basePage,
        SysMetaMethod vo, Map<String, Object> map) throws BizException {
        vo.setIsDelete(0);
        basePage.setFilters(vo);
        Page<SysMetaMethod> page = sysMetaMethodDao.pageQuery(basePage);
        List<SysMetaMethod> list = page.getResult();
        for (SysMetaMethod bo : list) {
            bo.setIsValidStr(
                sysDatadicItemBiz.getItemNameByValue("LOGIC_TAG", bo.getIsValid() + ""));
            bo.setLogLevelStr(sysDatadicItemBiz.getItemNameByValue(
                Constants.DICGROUP_SYSMETAMETHOD_LOG_LEVEL, bo.getLogLevel() + ""));
            bo.setLogTypeStr(sysDatadicItemBiz.getItemNameByValue(
                Constants.DICGROUP_SYSMETAMETHOD_LOG_TYPE, bo.getLogType() + ""));
        }
        map.put("rows", list);
        map.put("total", page.getTotalCount());
        return list;
    }
    
    /**
     * 查询列表
     * @param vo
     * @return
     * @throws BizException
     */
    public List<SysMetaMethod> getSysMetaMethodList(SysMetaMethod vo) throws BizException {
        vo.setIsDelete(Constants.ZERO);
        return sysMetaMethodDao.getList(vo);
    }
    
    /**
     * 根据方法签名查询。 
     * （根据类名、方法名、参数类型，精确查找）
     * @param vo
     * @return
     * @throws BizException
     */
    public List<SysMetaMethod> getListByMethodSignature(SysMetaMethod vo) throws BizException {
        vo.setIsDelete(Constants.ZERO);
        return sysMetaMethodDao.getListByMethodSignature(vo);
    }
    
    /**
     * 查询实体信息
     * @param vo
     * @return
     * @throws BizException
     */
    public SysMetaMethod getSysMetaMethod(SysMetaMethod vo) throws BizException {
        List<SysMetaMethod> list = getListByMethodSignature(vo);
        if (list != null && list.size() > 1) {
            throw new BizException("符合条件的方法多于一个！");
        }
        return list == null || list.size() == 0 ? null
            : list.get(0);
    }
    
    /**
     * 新增。
     * （根据类名、方法名、参数类型，校验方法是否存在，存在不予添加）
     * @param sysMetaMethod
     * @throws BizException
     */
    public void addSysMetaMethodNotExist(SysMetaMethod sysMetaMethod) throws BizException {
        if (sysMetaMethod == null) {
            return;
        }
        SysMetaMethod filter = new SysMetaMethod();
        filter.setClassName(sysMetaMethod.getClassName());
        filter.setMethodName(sysMetaMethod.getMethodName());
        filter.setArgsName(sysMetaMethod.getArgsName());
        SysMetaMethod existMethod = getSysMetaMethod(filter);
        if (existMethod != null) {
            throw new BizException("方法已存在，不可重复添加");
        }
        addSysMetaMethod(sysMetaMethod);
    }
    
    /**
     * REMARK
     * 新增信息
     * Through the id inquires the out a data
     * Date need their conversion !
     * UserName is the current user name !
     */
    public void addSysMetaMethod(SysMetaMethod sysMetaMethod) throws BizException {
        
        sysMetaMethod.setIsValid(1);
        sysMetaMethod.setIsDelete(0);
        sysMetaMethod.setIsFinal(0);
        sysMetaMethod.setCrtTime(new Date());
        sysMetaMethod.setCrtUserId(SessionUtils.getUserId().longValue());
        sysMetaMethod.setUpdTime(new Date());
        sysMetaMethod.setUpdUserId(SessionUtils.getUserId().longValue());
        sysMetaMethodDao.save(sysMetaMethod);
    }
    
    /**
     * REMARK
     * 修改
     * update data
     * Date need their conversion !
     */
    public void updateSysMetaMethod(SysMetaMethod sysMetaMethod) throws BizException {
        sysMetaMethod.setUpdTime(new Date());
        sysMetaMethod.setUpdUserId(SessionUtils.getUserId().longValue());
        sysMetaMethodDao.update(sysMetaMethod);
    }
    
    /**
     * REMARK
     * 删除
     * Through the id delete a data
     */
    public void deleteSysMetaMethod(int id) throws BizException {
        sysMetaMethodDao.delete(id);
    }
    
    /**
     * REMARK
     * 物理删除
     * Through the id delete a data logic
     */
    public void deleteSysMetaMethodLogic(int id) throws BizException {
        SysMetaMethod sysMetaMethod = new SysMetaMethod();
        sysMetaMethod.setMetaMethodId((long) id);
        sysMetaMethod.setUpdTime(new Date());
        sysMetaMethod.setUpdUserId(SessionUtils.getUserId().longValue());
        sysMetaMethod.setIsDelete(1);
        sysMetaMethodDao.update(sysMetaMethod);
    }
    
    /**
     * REMARK
     * 查询实体信息
     * Inquires the individual data
     * Date need their conversion !
     */
    public SysMetaMethod getSysMetaMethodById(int id) throws BizException {
        if (id == 0) {
            return null;
        }
        return sysMetaMethodDao.getById(id);
    }
    
    /**
     * 初始化元方法数据
     * 实现：扫描所有Controller的方法更新到表中
     * （无则添加，有则不予处理，不匹配的方法置为不再用）
     * @throws BizException
     */
    public void doInitMetaMethod() throws BizException {
        ApplicationContext ac = WebBeanUtil.getContext();
        if (ac == null) {
            throw new BizException("未获取到ApplicationContext");
        }
        
        Map<String, Object> beans = ac
            .getBeansWithAnnotation(org.springframework.stereotype.Controller.class);
        if (beans == null) {
            throw new BizException("未扫描到Controller");
        }
        
        //首先把所有数据设置为不再用，然后逐条更新为在用
        this.setAllInvalid();
        
        for (Map.Entry<String, Object> entry : beans.entrySet()) {
            //获取到实例对象的class信息
            Class<?> clazz = (Class) entry.getValue().getClass().getGenericSuperclass();
            String className = clazz.getName();
            
            //不需要扫描的包、类
            boolean isSkip = containsInNoInitPackages(className, noInitPackageNames)
                || containsInNoInitClazzs(className, noInitClassNames);
            if (isSkip) {
                continue;
            }
            
            //方法
            Method[] methods = clazz.getDeclaredMethods();
            if (methods == null) {
                continue;
            }
            for (Method method : methods) {
                String methodName = method.getName();
                //获取到的方法中有前缀为access$的，对于含$符的方法不予处理
                if (!methodName.contains(Constants.SYSMETAMETHOD_NO_INIT_CONTAIN)) {
                    //方法参数类型名拼接处理
                    Class[] argTypes = method.getParameterTypes();
                    String argTypesName = getArgTypesName(argTypes);
                    
                    addOrUpdateSysMetaMethod(className, methodName, argTypesName);
                }
            }
        }
    }
    
    /**
     * 类是否在不需要扫描的包内
     * @param className 类名
     * @param packageNames 不扫描的包名：..,..
     */
    private static boolean containsInNoInitPackages(String className, String packageNames) {
        if (StringUtil.isEmpty(packageNames)) {
            return false;
        }
        String[] packageNamesArr = StringUtil.split(packageNames,
            Constants.SYSMETAMETHOD_SEPARATOR);
        if (packageNamesArr != null && packageNamesArr.length > 0) {
            for (String packageName : packageNamesArr) {
                String prefix = packageName;
                if (!prefix.endsWith(Constants.PACKAGE_SEPARATOR)) {
                    prefix += Constants.PACKAGE_SEPARATOR;
                }
                if (className.startsWith(prefix)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * 类是否包含在不需要扫描的类内
     * @param className
     * @param classNames 不扫描的类：..,..
     * @return
     */
    private static boolean containsInNoInitClazzs(String className, String classNames) {
        if (StringUtil.isEmpty(classNames)) {
            return false;
        }
        String[] classNamesArr = StringUtil.split(classNames, Constants.SYSMETAMETHOD_SEPARATOR);
        if (classNamesArr != null && classNamesArr.length > 0) {
            for (String name : classNamesArr) {
                if (className.equals(name)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * 更新元方法数据：无则添加，有则置为在用
     * @param className
     * @param methodName
     * @param argsName
     * @throws BizException
     */
    public void addOrUpdateSysMetaMethod(String className, String methodName, String argsName)
        throws BizException {
        SysMetaMethod filter = new SysMetaMethod();
        filter.setClassName(className);
        filter.setMethodName(methodName);
        filter.setArgsName(argsName);
        SysMetaMethod oldMethod = getSysMetaMethod(filter);
        
        SysMetaMethod method = new SysMetaMethod();
        method.setIsValid(Constants.ONE);
        if (oldMethod == null) {
            method.setClassName(className);
            method.setMethodName(methodName);
            method.setArgsName(argsName);
            addSysMetaMethod(method);
        } else {
            method.setMetaMethodId(oldMethod.getMetaMethodId());
            updateSysMetaMethod(method);
        }
    }
    
    /**
     * 把所有数据设置为不再用。
     */
    public void setAllInvalid() {
        sysMetaMethodDao.setAllInvalid();
    }
    
    /**
     * 拼接参数类型名
     * @param argTypes 参数类型数组
     * @return
     */
    public String getArgTypesName(Class[] argTypes) {
        return getArgTypesName(argTypes, Constants.SYSMETAMETHOD_ARGTYPES_NAME_SEPARATOR);
    }
    
    /**
     * 拼接参数类型名
     * @param argTypes  参数类型数组
     * @param separator 分隔符
     * @return
     */
    private String getArgTypesName(Class[] argTypes, String separator) {
        String argTypesName = "";
        if (argTypes != null) {
            StringBuffer buf = new StringBuffer();
            for (Class argType : argTypes) {
                String name = argType.getName();
                buf.append(separator).append(name);
            }
            argTypesName = buf.length() > separator.length() ? buf.substring(separator.length())
                : buf.toString();
        }
        return argTypesName;
    }
    
}
