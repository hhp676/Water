package com.hongguaninfo.hgdf.tss;

import java.util.HashMap;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hongguaninfo.hgdf.core.utils.logging.Log;
import com.hongguaninfo.hgdf.core.utils.logging.LogFactory;

/**
 * @author henry
 * @version 创建时间：2014-07-07
 */
public class Run {

    private static final Log log = LogFactory.getLog(Run.class);
    private static HashMap<String, String> contextMap = new HashMap();
    private static String[] jobContextArry;
    
    static {
        contextMap.put("default", "/applicationContext.xml");
    }

    public static void main(String[] args) {
        log.info("-------启动开始------");

        jobContextArry = new String[contextMap.size()];
        ApplicationContext context = new ClassPathXmlApplicationContext((String[]) contextMap.values().toArray(
                jobContextArry));

        log.info("启动结束......");
    }
}
