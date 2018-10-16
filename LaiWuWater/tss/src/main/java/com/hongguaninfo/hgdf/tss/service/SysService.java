package com.hongguaninfo.hgdf.tss.service;

import java.util.Date;

import org.springframework.stereotype.Service;

/**
 * 系统设置表:SYS_CONFIG biz 层
 * 
 * @author:
 */

@Service("sysService")
public class SysService {

    public Date getSysTime() {
       return new Date();
    }
}