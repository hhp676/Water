package com.hongguaninfo.hgdf.adp.service.sys;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hongguaninfo.hgdf.adp.core.base.BaseService;
import com.hongguaninfo.hgdf.adp.core.exception.BizException;
import com.hongguaninfo.hgdf.core.utils.logging.Log;
import com.hongguaninfo.hgdf.core.utils.logging.LogFactory;
import com.hongguaninfo.hgdf.core.utils.props.config.CustomPropertyPlaceholderConfigurer;
import com.hongguaninfo.hgdf.core.utils.system.stat.LoadJvmStat;
import com.hongguaninfo.hgdf.core.utils.system.stat.LoadServerStat;
import com.hongguaninfo.hgdf.core.utils.system.stat.LoadSystemStat;

@Service("sysInfoService")
public class SysInfoService extends BaseService {
    
    private static final Log LOG = LogFactory.getLog(SysInfoService.class);
    
    @Autowired
    private SessionDAO       sessionDAO;
    
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
    	  Map<String, Object> map = LoadServerStat.getServerInfo();
        return   map;
    }
    
    /**
     * @Title: getJvmInfo
     * @Description: 获取JVM信息
     * @param @return
     * @param @throws BizException 设定文件
     * @return Map &lt;String,String&gt; 返回类型
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
    
    /**
     * @Title: getSessionInfo
     * @Description: 获取在线用户信息
     * @param @return
     * @param @throws BizException 设定文件
     * @return String 返回类型
     * @throws
     * @since 2.6.0
     */
    public Map<String, Object> getSessionInfo() throws BizException {
        Collection<Session> sessions = sessionDAO.getActiveSessions();
        Map sessionMap = new HashMap<String, Object>();
        sessionMap.put("count", sessions.size());
        sessionMap.put("sessionId", SecurityUtils.getSubject().getSession().toString());
        sessionMap.put("list", sessions);
        return sessionMap;
    }
    
    /**
     * @Title: forceLogout
     * @Description: 强制踢出某用户
     * @param @return
     * @param @throws BizException 设定文件
     * @return String 返回类型
     * @throws
     * @since 2.6.0
     */
    public void forceLogout(String sessionId) throws BizException {
        Session session = sessionDAO.readSession(sessionId);
        if (session != null) {
            session.setTimeout(0);
        }
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
