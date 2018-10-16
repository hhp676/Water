package com.hongguaninfo.hgdf.wadp.service.sys;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.stereotype.Service;

import com.hongguaninfo.hgdf.wadp.core.base.BaseService;
import com.hongguaninfo.hgdf.wadp.core.exception.BizException;
import com.hongguaninfo.hgdf.core.utils.logging.Log;
import com.hongguaninfo.hgdf.core.utils.logging.LogFactory;
import com.hongguaninfo.hgdf.core.utils.props.config.CustomPropertyPlaceholderConfigurer;
import com.hongguaninfo.hgdf.core.utils.system.stat.LoadJvmStat;
import com.hongguaninfo.hgdf.core.utils.system.stat.LoadServerStat;
import com.hongguaninfo.hgdf.core.utils.system.stat.LoadSystemStat;

@Service("sysInfoService")
public class SysInfoService extends BaseService {

    private static final Log LOG = LogFactory.getLog(SysInfoService.class);

    /**
     * @Title: getSystemInfo
     * @Description: 系统信息
     * @param @return
     * @param @throws BizException 设定文件
     * @return String 返回类型
     * @throws
     * @since 1.0.0
     */
    public Map<String, Object> getSystemInfo() throws BizException {
        return LoadSystemStat.getJvmInfo();
    }

    /**
     * @Title: getServerInfo
     * @Description: 获取服务器信息
     * @param @return
     * @param @throws BizException 设定文件
     * @return String 返回类型
     * @throws
     * @since 1.0.0
     */
    public Map<String, Object> getServerInfo() throws BizException {
        return new LoadServerStat().getServerInfo();
    }

    /**
     * @Title: getJvmInfo
     * @Description: 获取JVM信息
     * @param @return
     * @param @throws BizException 设定文件
     * @return Map<String,String> 返回类型
     * @throws
     * @since 1.0.0
     */
    public Map<String, Object> getJvmInfo() throws BizException {
        return LoadJvmStat.getJvmInfo();
    }

    /**
     * @Title: getPropsInfo
     * @Description: 获取系统properties信息
     * @param @return
     * @param @throws BizException 设定文件
     * @return String 返回类型
     * @throws
     * @since 1.0.0
     */
    public Map<String, Object> getPropsInfo() throws BizException {
        return new HashMap<String, Object>(
                (Map) new CustomPropertyPlaceholderConfigurer().getProps());
    }

    public Map<String, Object> getOtherInfo() throws BizException {
        Properties props = System.getProperties();
        /*Set<Object> keySet = props.keySet();
        for (Object object : keySet) {
            String property = props.getProperty(object.toString());
            log.debug(object.toString() + " : " + property);
        }*/
        return new HashMap<String, Object>((Map) props);
    }
}