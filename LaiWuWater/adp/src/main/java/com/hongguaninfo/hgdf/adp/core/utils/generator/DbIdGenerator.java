package com.hongguaninfo.hgdf.adp.core.utils.generator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hongguaninfo.hgdf.adp.service.sys.SysConfigService;

@Component("dbIdGenerator")
public class DbIdGenerator {

    @Autowired
    SysConfigService sysConfigService;

    static private int idBlockSize = 100;
    static private long nextId = 0;
    static private long lastId = -1;

    public synchronized Long getNextId() {
        if (lastId < nextId) {
            getNewBlock();
        }
        return (nextId++);
    }

    private synchronized void getNewBlock() {
        IdBlock idBlock = sysConfigService.doGetDbIds(idBlockSize);
        this.nextId = idBlock.getNextId();
        this.lastId = idBlock.getLastId();
    }

    public int getIdBlockSize() {
        return idBlockSize;
    }

    public void setIdBlockSize(int idBlockSize) {
        this.idBlockSize = idBlockSize;
    }
}
