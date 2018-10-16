package com.hongguaninfo.hgdf.core.utils.system.stat;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 获取系统信息
 * */
public class LoadSystemStat {

    public static Map<String, Object> getJvmInfo() {
        Map<String, Object> retMap = new HashMap<String, Object>();

        // Jvm
        Runtime runtime = Runtime.getRuntime();
        retMap.put("System date", new Date());
        retMap.put("JVM Vendor", System.getProperty("java.vm.vendor"));
        retMap.put("JVM Name", System.getProperty("java.vm.name"));
        retMap.put("JVM Version", System.getProperty("java.vm.version"));
        retMap.put("Java Version", System.getProperty("java.version"));
        retMap.put("Java Home", System.getProperty("java.home"));
        retMap.put("User Name", System.getProperty("user.name"));
        retMap.put("User TimeZone", System.getProperty("user.timezone"));
        retMap.put("OS", System.getProperty("os.name"));
        retMap.put("System Classpath", System.getProperty("java.class.path"));
        retMap.put("Boot Classpath", System.getProperty("sun.boot.class.path"));
        retMap.put("Library Path", System.getProperty("java.library.path"));

        return retMap;
    }
}