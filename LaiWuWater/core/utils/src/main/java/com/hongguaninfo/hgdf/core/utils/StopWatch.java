package com.hongguaninfo.hgdf.core.utils;

/**
 * 
 * @ClassName: StopWatch
 * @Description: 秒表计时工具
 * @author henry
 * @date 2014-2-10 下午6:41:51
 * 
 */
public class StopWatch {
    private long startTime;

    public StopWatch() {
        startTime = System.currentTimeMillis();
    }

    public long getMillis() {
        return System.currentTimeMillis() - startTime;
    }

    public void reset() {
        startTime = System.currentTimeMillis();
    }
}
