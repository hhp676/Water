package com.hongguaninfo.hgdf.core.utils.system.stat;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.FileSystem;
import org.hyperic.sigar.FileSystemUsage;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.NetFlags;
import org.hyperic.sigar.NetInterfaceConfig;
import org.hyperic.sigar.NetInterfaceStat;
import org.hyperic.sigar.OperatingSystem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.SigarNotImplementedException;
import org.hyperic.sigar.Swap;

import com.hongguaninfo.hgdf.core.utils.logging.Log;
import com.hongguaninfo.hgdf.core.utils.logging.LogFactory;

public class SigarImpl {

    private static final Log LOG = LogFactory.getLog(SigarImpl.class);

    // b)CPU的总量（单位：HZ）及CPU的相关信息
    public void getCpuTotal() {
        Sigar sigar = new Sigar();
        CpuInfo[] infos;
        try {
            infos = sigar.getCpuInfoList();
            // 不管是单块CPU还是多CPU都适用
            for (int i = 0; i < infos.length; i++) {
                CpuInfo info = infos[i];
                // CPU的总量MHz
                LOG.debug("mhz=" + info.getMhz());
                // 获得CPU的卖主，如：Intel
                LOG.debug("vendor=" + info.getVendor());
                // 获得CPU的类别，如：Celeron
                LOG.debug("model=" + info.getModel());
                // 缓冲存储器数量
                LOG.debug("cache size=" + info.getCacheSize());
            }
        } catch (SigarException e) {
            LOG.error("getCpuTotal fail !", e);
        }
    }

    // c)CPU的用户使用量、系统使用剩余量、总的剩余量、总的使用占用量等（单位：100%）
    public void testCpuPerc() {
        Sigar sigar = new Sigar();
        // 方式一，主要是针对一块CPU的情况
        CpuPerc cpu;
        try {
            cpu = sigar.getCpuPerc();
            printCpuPerc(cpu);
        } catch (SigarException e) {
            LOG.error("testCpuPerc fail !", e);
        }
        // 方式二，不管是单块CPU还是多CPU都适用
        CpuPerc cpuList[] = null;
        try {
            cpuList = sigar.getCpuPercList();
        } catch (SigarException e) {
            LOG.error("testCpuPerc fail !", e);
            return;
        }
        for (int i = 0; i < cpuList.length; i++) {
            printCpuPerc(cpuList[i]);
        }
    }

    private void printCpuPerc(CpuPerc cpu) {
        // 用户使用率
        LOG.debug("User :" + CpuPerc.format(cpu.getUser()));
        // 系统使用率
        LOG.debug("Sys :" + CpuPerc.format(cpu.getSys()));
        // 当前等待率
        LOG.debug("Wait :" + CpuPerc.format(cpu.getWait()));
        LOG.debug("Nice :" + CpuPerc.format(cpu.getNice()));
        // 当前空闲率
        LOG.debug("Idle :" + CpuPerc.format(cpu.getIdle()));
        // 总的使用率
        LOG.debug("Total :" + CpuPerc.format(cpu.getCombined()));
    }

    // 2.内存资源信息
    public void getPhysicalMemory() {
        // a)物理内存信息
        Sigar sigar = new Sigar();
        Mem mem;
        try {
            mem = sigar.getMem();
            // 内存总量
            LOG.debug("Total = " + mem.getTotal() / 1024L + "K av");
            // 当前内存使用量
            LOG.debug("Used = " + mem.getUsed() / 1024L + "K used");
            // 当前内存剩余量
            LOG.debug("Free = " + mem.getFree() / 1024L + "K free");
            // b)系统页面文件交换区信息
            Swap swap = sigar.getSwap();
            // 交换区总量
            LOG.debug("Total = " + swap.getTotal() / 1024L + "K av");
            // 当前交换区使用量
            LOG.debug("Used = " + swap.getUsed() / 1024L + "K used");
            // 当前交换区剩余量
            LOG.debug("Free = " + swap.getFree() / 1024L + "K free");
        } catch (SigarException e) {
            LOG.error("getPhysicalMemory fail !", e);
        }
    }

    // 3.操作系统信息
    // a)取到当前操作系统的名称：
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

    // b)取当前操作系统的信息
    public void testGetOSInfo() {
        OperatingSystem os = OperatingSystem.getInstance();
        // 操作系统内核类型如： 386、486、586等x86
        LOG.debug("OS.getArch() = " + os.getArch());
        LOG.debug("OS.getCpuEndian() = " + os.getCpuEndian());
        LOG.debug("OS.getDataModel() = " + os.getDataModel());
        // 系统描述
        LOG.debug("OS.getDescription() = " + os.getDescription());
        LOG.debug("OS.getMachine() = " + os.getMachine());
        // 操作系统类型
        LOG.debug("OS.getName() = " + os.getName());
        LOG.debug("OS.getPatchLevel() = " + os.getPatchLevel());
        // 操作系统的卖主
        LOG.debug("OS.getVendor() = " + os.getVendor());
        // 卖主名称
        LOG.debug("OS.getVendorCodeName() = " + os.getVendorCodeName());
        // 操作系统名称
        LOG.debug("OS.getVendorName() = " + os.getVendorName());
        // 操作系统卖主类型
        LOG.debug("OS.getVendorVersion() = " + os.getVendorVersion());
        // 操作系统的版本号
        LOG.debug("OS.getVersion() = " + os.getVersion());
    }

    // c)取当前系统进程表中的用户信息
    public void testWho() {
        try {
            Sigar sigar = new Sigar();
            org.hyperic.sigar.Who[] who = sigar.getWhoList();
            if (who != null && who.length > 0) {
                for (int i = 0; i < who.length; i++) {
                    LOG.debug("\n~" + String.valueOf(i) + "~");
                    org.hyperic.sigar.Who _who = who[i];
                    LOG.debug("getDevice() = " + _who.getDevice());
                    LOG.debug("getHost() = " + _who.getHost());
                    LOG.debug("getTime() = " + _who.getTime());
                    // 当前系统进程表中的用户名
                    LOG.debug("getUser() = " + _who.getUser());
                }
            }
        } catch (SigarException e) {
            LOG.error("testWho fail !", e);
        }
    }

    // 4.资源信息（主要是硬盘）
    // a)取硬盘已有的分区及其详细信息（通过sigar.getFileSystemList()来获得FileSystem列表对象，然后对其进行编历）：
    public void testFileSystemInfo() throws Exception {
        Sigar sigar = new Sigar();
        FileSystem fslist[] = sigar.getFileSystemList();
        // 当前用户文件夹路径
        for (int i = 0; i < fslist.length; i++) {
            LOG.debug("\n~~~" + i + "~~~");
            FileSystem fs = fslist[i];
            // 分区的盘符名称
            LOG.debug("fs.getDevName() = " + fs.getDevName());
            // 分区的盘符名称
            LOG.debug("fs.getDirName() = " + fs.getDirName());
            LOG.debug("fs.getFlags() = " + fs.getFlags());
            // 文件系统类型，比如 FAT32、NTFS
            LOG.debug("fs.getSysTypeName() = " + fs.getSysTypeName());
            // 文件系统类型名，比如本地硬盘、光驱、网络文件系统等
            LOG.debug("fs.getTypeName() = " + fs.getTypeName());
            // 文件系统类型
            LOG.debug("fs.getType() = " + fs.getType());
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
                LOG.debug(" Total = " + usage.getTotal() + "KB");
                // 文件系统剩余大小
                LOG.debug(" Free = " + usage.getFree() + "KB");
                // 文件系统可用大小
                LOG.debug(" Avail = " + usage.getAvail() + "KB");
                // 文件系统已经使用量
                LOG.debug(" Used = " + usage.getUsed() + "KB");
                double usePercent = usage.getUsePercent() * 100D;
                // 文件系统资源的利用率
                LOG.debug(" Usage = " + usePercent + "%");
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
        }
        return;
    }

    // 5.网络信息
    // a)当前机器的正式域名
    public String getFQDN() {
        Sigar sigar = null;
        try {
            return InetAddress.getLocalHost().getCanonicalHostName();
        } catch (UnknownHostException e) {
            try {
                sigar = new Sigar();
                return sigar.getFQDN();
            } catch (SigarException ex) {
                return null;
            } finally {
                sigar.close();
            }
        }
    }

    // b)取到当前机器的IP地址
    public String getDefaultIpAddress() {
        String address = null;
        try {
            address = InetAddress.getLocalHost().getHostAddress();
            // 没有出现异常而正常当取到的IP时，如果取到的不是网卡循回地址时就返回
            // 否则再通过Sigar工具包中的方法来获取
            if (!NetFlags.LOOPBACK_ADDRESS.equals(address)) {
                return address;
            }
        } catch (UnknownHostException e) {
            // hostname not in DNS or /etc/hosts
        }
        Sigar sigar = new Sigar();
        try {
            address = sigar.getNetInterfaceConfig().getAddress();
        } catch (SigarException e) {
            address = NetFlags.LOOPBACK_ADDRESS;
        } finally {
            sigar.close();
        }
        return address;
    }

    // c)取到当前机器的MAC地址
    public String getMAC() {
        Sigar sigar = null;
        try {
            sigar = new Sigar();
            String[] ifaces = sigar.getNetInterfaceList();
            String hwaddr = null;
            for (int i = 0; i < ifaces.length; i++) {
                NetInterfaceConfig cfg = sigar.getNetInterfaceConfig(ifaces[i]);
                if (NetFlags.LOOPBACK_ADDRESS.equals(cfg.getAddress()) || (cfg.getFlags() & NetFlags.IFF_LOOPBACK) != 0
                        || NetFlags.NULL_HWADDR.equals(cfg.getHwaddr())) {
                    continue;
                }
                /*
                 * 如果存在多张网卡包括虚拟机的网卡，默认只取第一张网卡的MAC地址，如果要返回所有的网卡（包括物理的和虚拟的）
                 * 则可以修改方法的返回类型为数组或Collection ，通过在for循环里取到的多个MAC地址。
                 */
                hwaddr = cfg.getHwaddr();
                break;
            }
            return hwaddr != null ? hwaddr : null;
        } catch (Exception e) {
            return null;
        } finally {
            if (sigar != null) {
                sigar.close();
            }
        }
    }

    // d)获取网络流量等信息
    public void testNetIfList() throws Exception {
        Sigar sigar = new Sigar();
        String ifNames[] = sigar.getNetInterfaceList();
        for (int i = 0; i < ifNames.length; i++) {
            String name = ifNames[i];
            NetInterfaceConfig ifconfig = sigar.getNetInterfaceConfig(name);
            // 网络设备名
            print("\nname = " + name);
            // IP地址
            print("Address = " + ifconfig.getAddress());
            // 子网掩码
            print("Netmask = " + ifconfig.getNetmask());
            if ((ifconfig.getFlags() & 1L) <= 0L) {
                print("!IFF_UP...skipping getNetInterfaceStat");
                continue;
            }
            try {
                NetInterfaceStat ifstat = sigar.getNetInterfaceStat(name);
                // 接收的总包裹数
                print("RxPackets = " + ifstat.getRxPackets());
                // 发送的总包裹数
                print("TxPackets = " + ifstat.getTxPackets());
                // 接收到的总字节数
                print("RxBytes = " + ifstat.getRxBytes());
                // 发送的总字节数
                print("TxBytes = " + ifstat.getTxBytes());
                // 接收到的错误包数
                print("RxErrors = " + ifstat.getRxErrors());
                // 发送数据包时的错误数
                print("TxErrors = " + ifstat.getTxErrors());
                // 接收时丢弃的包数
                print("RxDropped = " + ifstat.getRxDropped());
                // 发送时丢弃的包数
                print("TxDropped = " + ifstat.getTxDropped());
            } catch (SigarNotImplementedException e) {
            } catch (SigarException e) {
                print(e.getMessage());
            }
        }
    }

    void print(String msg) {
        LOG.debug(msg);
    }

    // 一些其他的信息
    public void getEthernetInfo() {
        Sigar sigar = null;
        try {
            sigar = new Sigar();
            String[] ifaces = sigar.getNetInterfaceList();
            for (int i = 0; i < ifaces.length; i++) {
                NetInterfaceConfig cfg = sigar.getNetInterfaceConfig(ifaces[i]);
                if (NetFlags.LOOPBACK_ADDRESS.equals(cfg.getAddress()) || (cfg.getFlags() & NetFlags.IFF_LOOPBACK) != 0
                        || NetFlags.NULL_HWADDR.equals(cfg.getHwaddr())) {
                    continue;
                }
                // IP地址
                LOG.debug("cfg.getAddress() = " + cfg.getAddress());
                // 网关广播地址
                LOG.debug("cfg.getBroadcast() = " + cfg.getBroadcast());
                // 网卡MAC地址
                LOG.debug("cfg.getHwaddr() = " + cfg.getHwaddr());
                // 子网掩码
                LOG.debug("cfg.getNetmask() = " + cfg.getNetmask());
                // 网卡描述信息
                LOG.debug("cfg.getDescription() = " + cfg.getDescription());
                LOG.debug("cfg.getType() = " + cfg.getType());
                LOG.debug("cfg.getDestination() = " + cfg.getDestination());
                LOG.debug("cfg.getFlags() = " + cfg.getFlags());
                LOG.debug("cfg.getMetric() = " + cfg.getMetric());
                LOG.debug("cfg.getMtu() = " + cfg.getMtu());
                LOG.debug("cfg.getName() = " + cfg.getName());
            }
        } catch (Exception e) {
            LOG.debug("Error while creating GUID" + e);
        } finally {
            if (sigar != null) {
                sigar.close();
            }
        }
    }
}
