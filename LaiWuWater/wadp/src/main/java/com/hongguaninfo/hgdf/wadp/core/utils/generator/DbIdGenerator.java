package com.hongguaninfo.hgdf.wadp.core.utils.generator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hongguaninfo.hgdf.wadp.service.sys.SysConfigService;

@Component("dbIdGenerator")
public class DbIdGenerator {

    @Autowired
    SysConfigService sysConfigService;

    private int idBlockSize = 1;
    private long nextId = 0;
    private long lastId = -1;

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
