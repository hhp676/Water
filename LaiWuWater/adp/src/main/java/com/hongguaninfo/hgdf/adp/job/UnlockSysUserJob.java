package com.hongguaninfo.hgdf.adp.job;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hongguaninfo.hgdf.adp.job.service.UnlockSysUserService;
import com.hongguaninfo.hgdf.core.utils.logging.Log;
import com.hongguaninfo.hgdf.core.utils.logging.LogFactory;

/**
 * @author menghaixiao
 * 解锁新用户
 */
@Component
public class UnlockSysUserJob {
	
	private static Log log = LogFactory.getLog(UnlockSysUserJob.class);
	
	@Autowired
	private UnlockSysUserService unlockSysUserService;
	
	public void doUnlockSysUser(){
		try {
			unlockSysUserService.doUnlockSysUserList();
		}catch(Exception e){
			log.error("Job UnlockSysUserJob fail! Time is " + new Date() + "!", e);
		}
		log.info("Job UnlockSysUserJob success! Time is " + new Date() + "!");
	}
}
