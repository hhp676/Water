package com.hongguaninfo.hgdf.core.utils.jgroup;

import java.util.Properties;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.distribution.CacheManagerPeerProvider;
import net.sf.ehcache.distribution.jgroups.JGroupsCacheManagerPeerProviderFactory;

import com.hongguaninfo.hgdf.core.utils.props.PropertyUtils;

/**
 * jgroup ehcache cluster init util
 * 
 * @author henry
 * 
 */
public class CustomJGroupsCacheManagerPeerProviderFactory extends JGroupsCacheManagerPeerProviderFactory {

    private static final String RES_NAME = "jgroup";

    public CacheManagerPeerProvider createCachePeerProvider(CacheManager cacheManager, Properties properties) {
        ressetJgroupProps(properties);
        return super.createCachePeerProvider(cacheManager, properties);
    }

    /**
     * 获取属性信息
     * 
     * @return
     */
    private Properties ressetJgroupProps(Properties properties) {
        Properties props = new Properties();
        props = PropertyUtils.getPropertyValues(RES_NAME);

        String tmpString = properties.getProperty("connect");
        tmpString = tmpString.replace("BIND_PORT", props.getProperty("ehcache_bind_port"))
                .replace("BIND_ADDR", props.getProperty("ehcache_bind_addr"))
                .replace("INITIAL_HOSTS", props.getProperty("ehcache_initial_hosts"))
                .replace("MCAST_ADDR", props.getProperty("ehcache_mcast_addr"))
                .replace("MCAST_PORT", props.getProperty("ehcache_mcast_port"));
        properties.setProperty("connect", tmpString);
        return properties;
    }

}
