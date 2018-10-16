/**
* Project Name:hutils
* File Name:PathUtil.java
* Package Name:com.hginfo.hutils
* Date:2016年9月7日上午11:28:46
* Copyright (c) 2016, hongguaninfo.com All Rights Reserved.
*
*/

package com.hongguaninfo.hgdf.core.utils;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * 路径工具类。
* ClassName:PathUtil <br/>
* Date: 2016年9月7日 上午11:28:46 <br/>
* @author licheng
* @version
* @since V1.0.0
* @see
*/
public class PathUtil {
    
    /**
     * 获取当前容器class的绝对路径。
    * @author licheng
    * @return 文件绝对路径
    * @since V1.0.0
     */
    public static String getClasspath() {
        String path = (String
            .valueOf(Thread.currentThread().getContextClassLoader().getResource("")))
                .replaceAll("file:/", "").replaceAll("%20", " ").trim();
        if (path.indexOf(":") != 1) {
            path = File.separator + path;
        }
        return path;
    }
    
    /**
     * 获取一个Class的绝对路径。
    * @author licheng
    * @param clazz Class对象 
    * @return Class的绝对路径 
    * @since V1.0.0
     */
    @SuppressWarnings("rawtypes")
    public static String getPathByClass(Class clazz) {
        String path = null;
        try {
            URI uri = clazz.getResource("").toURI();
            File file = new File(uri);
            path = file.getCanonicalPath();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }
    /*
    public static String PathAddress() {
        String strResult = "";
        
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
            .getRequestAttributes()).getRequest();
        
        StringBuffer strBuf = new StringBuffer();
        
        strBuf.append(request.getScheme() + "://");
        strBuf.append(request.getServerName() + ":");
        strBuf.append(request.getServerPort() + "");
        
        strBuf.append(request.getContextPath() + "/");
        
        strResult = strBuf.toString();// +"ss/";//加入项目的名称
        
        return strResult;
    }*/
    
}
