package com.hongguaninfo.hgdf.tss.core.quartz.demo;

import java.util.Date;
import java.util.Map;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.hongguaninfo.hgdf.core.utils.logging.Log;
import com.hongguaninfo.hgdf.core.utils.logging.LogFactory;
import com.hongguaninfo.hgdf.tss.service.SysService;

/**
 * 被Spring的Quartz JobDetailBean定时执行的Job类, 支持持久化到数据库实现Quartz集群.
 * 
 * 因为需要被持久化, 不能有用XXService等不能被持久化的成员变量,
 * 只能在每次调度时从QuartzJobBean注入的applicationContext中动态取出.
 * 
 * @author henry
 */
public class QuartzClusterableJob extends QuartzJobBean {

	private static Log log = LogFactory.getLog(QuartzClusterableJob.class);

	private ApplicationContext applicationContext;

	/**
	 * 从SchedulerFactoryBean注入的applicationContext.
	 */
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	/**
	 * 定时打印当前用户数到日志.
	 */
	@Override
	protected void executeInternal(JobExecutionContext ctx) throws JobExecutionException {
		SysService accountService = applicationContext.getBean(SysService.class);
		Map config = (Map) applicationContext.getBean("timerJobConfig");

		Date date = accountService.getSysTime();
		String nodeName = (String) config.get("nodeName");

		log.info("Server Time is"+date+", printed by quartz cluster job on node "+nodeName+".");
	}
}
