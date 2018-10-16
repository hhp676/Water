package com.hongguaninfo.hgdf.core.utils.system.stat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.FileSystem;
import org.hyperic.sigar.FileSystemUsage;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.OperatingSystem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.Swap;

import com.hongguaninfo.hgdf.core.utils.PathUtil;
import com.hongguaninfo.hgdf.core.utils.logging.Log;
import com.hongguaninfo.hgdf.core.utils.logging.LogFactory;

/**
 * 获取所在服务器的相关信息
 * */
public class LoadServerStat {

    private static final Log LOG = LogFactory.getLog(LoadServerStat.class);

    /**
     */
    public LoadServerStat() {
        // 构造函数
    }

    static {
        try {
            loadSigar();
        } catch (IOException e) {
        	LOG.error(e.getMessage());
        }
    }
    
    /**
     * 初始化sigar。
     * @throws IOException
     */
    private static void loadSigar() throws IOException {
        String sigarDllName = getDllNameByOS();
        String jarDllPath = "/sigar/" + sigarDllName;
        String newDllPath = "sigar";
        String newDllName = sigarDllName;

        InputStream inputStream = LoadServerStat.class.getResource(jarDllPath).openStream();
        String destPath = PathUtil.getClasspath() + newDllPath;
        (new File(destPath)).mkdir();
        final File destFile = new File(destPath + File.separatorChar + newDllName);
        if (!destFile.exists()) {
            FileOutputStream outputStream = new FileOutputStream(destFile);
            byte[] array = new byte[8192];
            int bytesRead = -1;
            while ((bytesRead = inputStream.read(array)) != -1) {
                outputStream.write(array, 0, bytesRead);
            }
            outputStream.flush();
            outputStream.close();
        }
        String path = System.getProperty("java.library.path");
        path += File.pathSeparator + destPath;
        System.setProperty("java.library.path", path);
    }
    
    /**
     * //OS版本判断sigar链接库名称。
     *
     * @return
     */
    public static String getDllNameByOS() {
        String OS = System.getProperty("os.name").toLowerCase();
        Properties props = System.getProperties();
        String bits =String.valueOf(props.get("sun.arch.data.model"));
        if (OS.indexOf("win") >= 0){
            if ("32".equals(bits)){
                return "sigar-x86-winnt.dll";
            }else{
                return "sigar-amd64-winnt.dll";
            }
        }else{
            if ("32".equals(bits)){
                return "libsigar-x86-linux.so";
            }else{
                return "libsigar-amd64-linux.so";
            }
        }
    }
    
    public static   Map<String, Object> getServerInfo() {
        Map<String, Object> retMap = new HashMap<String, Object>();
        try {
            retMap.put("cpuTotal", getCpuTotal());
            retMap.put("cpuPerc", getCpuPerc());
            retMap.put("mem", getPhysicalMemory());
            retMap.put("os", getOSInfo());
            // retMap.put("who", getWho());
            retMap.put("fileSystem", getFileSystemInfo());

        } catch (Exception e) {
            return retMap;
        }
        return retMap;
    }

    /**
     * @Title: getCpuCount
     * @Description: 获取CPU数量
     * @param @return
     * @param @throws SigarException 设定文件
     * @return int 返回类型
     * @throws
     * @since 1.0.0
     */
    public static int getCpuCount() throws SigarException {
        Sigar sigar = new Sigar();
        try {
            return sigar.getCpuInfoList().length;
        } finally {
            sigar.close();
        }
    }

    /**
     * @Title: getCpuTotal
     * @Description: 获取CPPU总量信息
     * @param 设定文件
     * @return List 返回类型
     * @throws
     * @since 1.0.0
     */
    private static List<Map<String, Object>> getCpuTotal() throws SigarException {
        Sigar sigar = new Sigar();
        CpuInfo[] infos;
        try {
            List retList = new ArrayList<Map<String, Object>>();
            infos = sigar.getCpuInfoList();
            // 不管是单块CPU还是多CPU都适用
            for (int i = 0; i < infos.length; i++) {
                CpuInfo info = infos[i];
                Map tmpMap = new HashMap<String, Object>();
                // CPU的总量MHz
                tmpMap.put("mhz", info.getMhz());
                // 获得CPU的卖主，如：Intel
                tmpMap.put("vendor", info.getVendor());
                // 获得CPU的类别，如：Celeron
                tmpMap.put("model", info.getModel());
                // 缓冲存储器数量
                tmpMap.put("cache size", info.getCacheSize());
                retList.add(tmpMap);
            }
            return retList;
        } finally {
            sigar.close();
        }
    }

    private static List<Map<String, Object>> getCpuPerc() throws SigarException {
        Sigar sigar = new Sigar();
        CpuPerc cpuList[];
        try {
            List retList = new ArrayList<Map<String, Object>>();
            cpuList = sigar.getCpuPercList();
            // 不管是单块CPU还是多CPU都适用
            for (int i = 0; i < cpuList.length; i++) {
                CpuPerc cpu = cpuList[i];
                Map tmpMap = new HashMap<String, Object>();
                // 用户使用率
                tmpMap.put("User", CpuPerc.format(cpu.getUser()));
                // 系统使用率
                tmpMap.put("Sys", CpuPerc.format(cpu.getSys()));
                // 当前等待率
                tmpMap.put("Wait", CpuPerc.format(cpu.getWait()));
                tmpMap.put("Nice", CpuPerc.format(cpu.getNice()));
                // 当前空闲率
                tmpMap.put("Idle", CpuPerc.format(cpu.getIdle()));
                // 总的使用率
                tmpMap.put("Total", CpuPerc.format(cpu.getCombined()));
                retList.add(tmpMap);
            }
            return retList;
        } finally {
            sigar.close();
        }
    }

    /**
     * @Title: getPhysicalMemory
     * @Description: 获取内存资源信息
     * @param 设定文件
     * @return map 返回类型
     * @throws
     * @since 1.0.0
     */
    private static Map<String, Object> getPhysicalMemory() throws SigarException {
        Sigar sigar = new Sigar();
        Mem mem;
        try {
            mem = sigar.getMem();
            Map retMap = new HashMap<String, Object>();
            // 内存总量
            retMap.put("Mem Total", mem.getTotal() / 1024L + "K");
            // 当前内存使用量
            retMap.put("Mem Used", mem.getUsed() / 1024L + "K");
            // 当前内存剩余量
            retMap.put("Mem Free", mem.getFree() / 1024L + "K");

            Swap swap = sigar.getSwap();
            // 交换区总量
            retMap.put("Swap Total", swap.getTotal() / 1024L + "K");
            // 当前交换区使用量
            retMap.put("Swap Used", swap.getUsed() / 1024L + "K");
            // 当前交换区剩余量
            retMap.put("Swap Free", swap.getFree() / 1024L + "K");

            return retMap;
        } finally {
            sigar.close();
        }
    }

    /**
     * @Title: getPlatformName
     * @Description: 操作系统名称
     * @param @return 设定文件
     * @return String 返回类型
     * @throws
     * @since 1.0.0
     */
    public String getPlatformName() {
        String hostname = "";
        try {
            hostname = InetAddress.getLocalHost().getHostName();
        } catch (Exception exc) {
            Sigar sigar = new Sigar();
            try {
                hostname = sigar.getNetInfo().getHostName();
            } catch (SigarException e) {
                hostname = "localhost.unknown";
            } finally {
                sigar.close();
            }
        }
        return hostname;
    }

    /**
     * @Title: testGetOSInfo
     * @Description: 当前操作系统信息
     * @param 设定文件
     * @return void 返回类型
     * @throws
     * @since 1.0.0
     */
    private static Map<String, Object> getOSInfo() {
        OperatingSystem os = OperatingSystem.getInstance();

        Map retMap = new HashMap<String, Object>();
        // 操作系统内核类型
        retMap.put("Arch", os.getArch());
        retMap.put("CpuEndian", os.getCpuEndian());
        retMap.put("DataModel", os.getDataModel());
        // 系统描述
        retMap.put("Description", os.getDescription());
        // 系统机器
        retMap.put("Machine", os.getMachine());
        // 操作系统类型
        retMap.put("Name", os.getName());
        retMap.put("PatchLevel", os.getPatchLevel());
        // 操作系统的卖主
        retMap.put("Vendor", os.getVendor());
        // 卖主名称
        retMap.put("VendorCodeName", os.getVendorCodeName());
        // 操作系统名称
        retMap.put("VendorCodeName", os.getVendorName());
        // 操作系统卖主类型
        retMap.put("VendorVersion", os.getVendorVersion());
        // 操作系统的版本号
        retMap.put("Version", os.getVersion());

        return retMap;
    }

    /**
     * @Title: testWho
     * @Description: 当前系统进程表中的用户信息
     * @param 设定文件
     * @return void 返回类型
     * @throws
     * @since 1.0.0
     */
    public List<Map<String, Object>> getWho() throws SigarException {
        Sigar sigar = new Sigar();
        try {
            List retList = new ArrayList<Map<String, Object>>();
            org.hyperic.sigar.Who[] who = sigar.getWhoList();
            if (who != null && who.length > 0) {
                for (int i = 0; i < who.length; i++) {
                    org.hyperic.sigar.Who _who = who[i];
                    Map tmpMap = new HashMap<String, Object>();
                    tmpMap.put("Device", _who.getDevice());
                    tmpMap.put("Host", _who.getHost());
                    tmpMap.put("Time", _who.getTime());
                    tmpMap.put("User", _who.getUser());
                    retList.add(tmpMap);
                }
            }
            return retList;
        } finally {
            sigar.close();
        }
    }

    /**
     * @Title: testFileSystemInfo
     * @Description: 磁盘信息
     * @param @throws Exception 设定文件
     * @return void 返回类型
     * @throws
     * @since 1.0.0
     */
    public static List<Map<String, Object>> getFileSystemInfo() throws Exception {
        Sigar sigar = new Sigar();
        List retList = new ArrayList<Map<String, Object>>();
        FileSystem fslist[] = sigar.getFileSystemList();
        // 当前用户文件夹路径
        String dir = System.getProperty("user.home");
        for (int i = 0; i < fslist.length; i++) {
            LOG.debug("\n~~~~~~~~~~" + i + "~~~~~~~~~~");
            FileSystem fs = fslist[i];
            Map tmpMap = new HashMap<String, Object>();
            // 分区的盘符名称
            tmpMap.put("DevName", fs.getDevName());
            tmpMap.put("DirName", fs.getDirName());
            tmpMap.put("Flags", fs.getFlags());
            // 文件系统类型，比如FAT32、NTFS
            tmpMap.put("SysTypeName", fs.getSysTypeName());
            // 文件系统类型名，比如本地硬盘、光驱、网络文件系统等
            tmpMap.put("TypeName", fs.getTypeName());
            // 文件系统类型

            tmpMap.put("Type", fs.getType());
            FileSystemUsage usage = null;
            try {
                usage = sigar.getFileSystemUsage(fs.getDirName());
            } catch (SigarException e) {
                if (fs.getType() == 2) {
                    throw e;
                }
                continue;
            }
            switch (fs.getType()) {
            case 0: // TYPE_UNKNOWN ：未知
                break;
            case 1: // TYPE_NONE
                break;
            case 2: // TYPE_LOCAL_DISK : 本地硬盘
                // 文件系统总大小
                tmpMap.put("Total", usage.getTotal() + "KB");
                // 文件系统剩余大小
                tmpMap.put("Free", usage.getFree() + "KB");
                // 文件系统可用大小
                tmpMap.put("Avail", usage.getAvail() + "KB");
                // 文件系统已经使用量
                tmpMap.put("Used", usage.getUsed() + "KB");
                double usePercent = usage.getUsePercent() * 100D;
                // 文件系统资源的利用率
                tmpMap.put("Usage", usePercent + "%");
                break;
            case 3:// TYPE_NETWORK ：网络
                break;
            case 4:// TYPE_RAM_DISK ：闪存
                break;
            case 5:// TYPE_CDROM ：光驱
                break;
            case 6:// TYPE_SWAP ：页面交换
                break;
            }
            LOG.debug(" DiskReads = " + usage.getDiskReads());
            LOG.debug(" DiskWrites = " + usage.getDiskWrites());

            retList.add(tmpMap);

        }
        return retList;
    }

    public static void main(String[] args) {

    }

}