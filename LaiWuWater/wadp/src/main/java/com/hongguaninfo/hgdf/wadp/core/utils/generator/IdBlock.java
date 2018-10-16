package com.hongguaninfo.hgdf.wadp.core.utils.generator;

public class IdBlock {

    private long nextId;
    private long lastId;

    public IdBlock(long nextId, long lastId) {
        this.nextId = nextId;
        this.lastId = lastId;
    }

    public long getNextId() {
        return nextId;
    }

    public long getLastId() {
        return lastId;
    }
}
