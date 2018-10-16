package com.hongguaninfo.hgdf.core.utils.system.stat;

import java.lang.management.ClassLoadingMXBean;
import java.lang.management.CompilationMXBean;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.RuntimeMXBean;
import java.lang.management.ThreadMXBean;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hongguaninfo.hgdf.core.utils.logging.Log;
import com.hongguaninfo.hgdf.core.utils.logging.LogFactory;

/**
 * 获取所在操作系统的信息，JVM的相关信息
 * */
public class LoadJvmStat {

    private static final Log LOG = LogFactory.getLog(LoadJvmStat.class);

    private static final int COV_INT = 1024;

    public static Map<String, Object> getJvmInfo() {
        Map<String, Object> retMap = new HashMap<String, Object>();

        // Jvm
        Runtime runtime = Runtime.getRuntime();
        retMap.put("Total Memory", runtime.totalMemory());
        retMap.put("Free Memory", runtime.freeMemory());
        retMap.put("Max Memory", runtime.maxMemory());

        // Heap
        MemoryMXBean mm = (MemoryMXBean) ManagementFactory.getMemoryMXBean();
        retMap.put("Heap", mm.getHeapMemoryUsage());
        retMap.put("Non Heap", mm.getNonHeapMemoryUsage());

        // Operating System
        OperatingSystemMXBean osm = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        retMap.put("Operating System Arch", osm.getArch());
        retMap.put("Operating System Processors", osm.getAvailableProcessors());
        retMap.put("Operating System Name", osm.getName());
        retMap.put("Operating System Version", osm.getVersion());

        // Thread
        ThreadMXBean tm = (ThreadMXBean) ManagementFactory.getThreadMXBean();
        retMap.put("Thread Total Count", tm.getThreadCount());
        retMap.put("Thread Peak Count", tm.getPeakThreadCount());
        retMap.put("Thread Deamon Count", tm.getDaemonThreadCount());

        // ClassLoad
        ClassLoadingMXBean clm = (ClassLoadingMXBean) ManagementFactory.getClassLoadingMXBean();
        retMap.put("Class Total Count", clm.getTotalLoadedClassCount());
        retMap.put("Class Loaded Count", clm.getLoadedClassCount());
        retMap.put("Class unLoaded Count", clm.getUnloadedClassCount());

        // runtime
        RuntimeMXBean rmb = (RuntimeMXBean) ManagementFactory.getRuntimeMXBean();
        retMap.put("ClassPath", rmb.getClassPath());
        retMap.put("LibraryPath", rmb.getLibraryPath());
        retMap.put("VmVersion", rmb.getVmVersion());
        retMap.put("StartTime", rmb.getStartTime());

        return retMap;
    }

    /**
     * @param 直接通过jdk来获取系统相关状态
     * 
     */
    public static void main(String[] args) {

        LOG.debug("=======================通过java来获取相关系统状态============================ ");
        int i = (int) Runtime.getRuntime().totalMemory() / COV_INT;// Java
                                                                   // 虚拟机中的内存总量,以字节为单位
        LOG.debug("总的内存量 i is " + i);
        int j = (int) Runtime.getRuntime().freeMemory() / COV_INT;// Java
                                                                  // 虚拟机中的空闲内存量
        LOG.debug("空闲内存量 j is " + j);
        LOG.debug("最大内存量 is " + Runtime.getRuntime().maxMemory() / COV_INT);

        LOG.debug("=======================OperatingSystemMXBean============================ ");
        OperatingSystemMXBean osm = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        // 获取操作系统相关信息
        LOG.debug("osm.getArch() " + osm.getArch());
        LOG.debug("osm.getAvailableProcessors() " + osm.getAvailableProcessors());
        LOG.debug("osm.getName() " + osm.getName());
        LOG.debug("osm.getVersion() " + osm.getVersion());
        // 获取整个虚拟机内存使用情况
        LOG.debug("=======================MemoryMXBean============================ ");
        MemoryMXBean mm = (MemoryMXBean) ManagementFactory.getMemoryMXBean();
        LOG.debug("getHeapMemoryUsage " + mm.getHeapMemoryUsage());
        LOG.debug("getNonHeapMemoryUsage " + mm.getNonHeapMemoryUsage());
        // 获取各个线程的各种状态，CPU 占用情况，以及整个系统中的线程状况
        LOG.debug("=======================ThreadMXBean============================ ");
        ThreadMXBean tm = (ThreadMXBean) ManagementFactory.getThreadMXBean();
        LOG.debug("getThreadCount " + tm.getThreadCount());
        LOG.debug("getPeakThreadCount " + tm.getPeakThreadCount());
        LOG.debug("getCurrentThreadCpuTime " + tm.getCurrentThreadCpuTime());
        LOG.debug("getDaemonThreadCount " + tm.getDaemonThreadCount());
        LOG.debug("getCurrentThreadUserTime " + tm.getCurrentThreadUserTime());

        // 当前编译器情况
        LOG.debug("=======================CompilationMXBean============================ ");
        CompilationMXBean gm = (CompilationMXBean) ManagementFactory.getCompilationMXBean();
        LOG.debug("getName " + gm.getName());
        LOG.debug("getTotalCompilationTime " + gm.getTotalCompilationTime());

        // 获取多个内存池的使用情况
        LOG.debug("=======================MemoryPoolMXBean============================ ");
        List<MemoryPoolMXBean> mpmList = ManagementFactory.getMemoryPoolMXBeans();
        for (MemoryPoolMXBean mpm : mpmList) {
            LOG.debug("getUsage " + mpm.getUsage());
            LOG.debug("getMemoryManagerNames " + mpm.getMemoryManagerNames().toString());
        }
        // 获取GC的次数以及花费时间之类的信息
        LOG.debug("=======================MemoryPoolMXBean============================ ");
        List<GarbageCollectorMXBean> gcmList = ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean gcm : gcmList) {
            LOG.debug("getName " + gcm.getName());
            LOG.debug("getMemoryPoolNames " + gcm.getMemoryPoolNames());
        }
        // 获取运行时信息
        LOG.debug("=======================RuntimeMXBean============================ ");
        RuntimeMXBean rmb = (RuntimeMXBean) ManagementFactory.getRuntimeMXBean();
        LOG.debug("getClassPath " + rmb.getClassPath());
        LOG.debug("getLibraryPath " + rmb.getLibraryPath());
        LOG.debug("getVmVersion " + rmb.getVmVersion());
    }

}