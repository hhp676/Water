package com.hongguaninfo.hgdf.tss.core.quartz.demo;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hongguaninfo.hgdf.core.utils.logging.Log;
import com.hongguaninfo.hgdf.core.utils.logging.LogFactory;

/**
 * @author henry
 */
@Component
public class SysScanner {

    private static Log log = LogFactory.getLog(SysScanner.class);

    // 被Spring的Quartz MethodInvokingJobDetailFactoryBean反射执行
    public void executeByQuartzLocalJob() {
        execute("quartz local job");
    }

    /**
     * 定时打印系统时间
     */
    private void execute(String by) {
        log.info("System Time is " + new Date() + ", printed by " + by + ".");
    }
}
