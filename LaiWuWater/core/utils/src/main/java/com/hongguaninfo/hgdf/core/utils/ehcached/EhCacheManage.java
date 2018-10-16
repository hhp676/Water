package com.hongguaninfo.hgdf.core.utils.ehcached;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.store.MemoryStoreEvictionPolicy;

import org.apache.commons.lang.StringUtils;

import com.hongguaninfo.hgdf.core.utils.exception.BaseException;
import com.hongguaninfo.hgdf.core.utils.logging.Log;
import com.hongguaninfo.hgdf.core.utils.logging.LogFactory;

/**
 * @classDescription:ehcache工具类
 */
public class EhCacheManage {
    private static final Log LOG = LogFactory.getLog(EhCacheManage.class);
    private static CacheManager cacheManager = null;

    // ------------------简化---------------------
    /**
     * 初始化缓存管理容器
     */
    public static CacheManager initCacheManager() throws BaseException {
        if (cacheManager == null) {
            cacheManager = CacheManager.getInstance();
        }
        return cacheManager;
    }

    /**
     * 初始化缓存管理容器
     * 
     * @param path
     *            ehcache.xml存放的路徑
     */
    public static CacheManager initCacheManager(String path) throws BaseException {
        if (cacheManager == null) {
            cacheManager = CacheManager.getInstance().create(path);
        }
        return cacheManager;
    }

    /**
     * 根据名字获取cache
     * 
     * @param cacheName
     * @return
     */
    public static Cache getCacheByName(String cacheName) {
        checkCacheManager();
        return cacheManager.getCache(cacheName);
    }

    /**
     * 默認初始化cache為永久
     * 
     * @param cacheName
     * @return
     */
    public static Cache initCache(String cacheName) {
        checkCacheManager();
        if (null == cacheManager.getCache(cacheName)) {
            try {
                initCache(cacheName, 0);
            } catch (BaseException e) {

            }
        }
        return cacheManager.getCache(cacheName);
    }

    /**
     * 添加缓存
     * 
     * @param cacheName
     *            cache名
     * @param key
     *            关键字
     * @param value
     *            值
     */
    public static void put(String cacheName, Object key, Object value) {
        Cache cache = getCacheByName(cacheName);
        Element element = new Element(key, value);
        cache.put(element);
    }

    /**
     * 覆盖缓存
     * 
     * @param cacheName
     *            cache名
     * @param key
     *            关键字
     * @param value
     *            值
     */
    public static void replace(String cacheName, Object key, Object value) {
        Cache cache = getCacheByName(cacheName);
        Element element = new Element(key, value);
        if (cache.get(key) == null) {
            cache.put(element);
        } else {
            Element oldElement = cache.get(key);
            cache.replace(oldElement, element);
        }
    }

    /**
     * 获取cache值
     * 
     * @param cacheName
     *            cache名
     * @param key
     *            关键字
     * @return
     */
    public static Object get(String cacheName, Object key) {
        Cache cache = getCacheByName(cacheName);
        Element element = cache.get(key);
        if (null == element) {
            return null;
        }
        return element.getObjectValue();
    }

    /**
     * 初始化缓存
     * 
     * @param cacheName
     *            缓存名称
     * @param maxElementsInMemory
     *            元素最大数量
     * @param overflowToDisk
     *            是否持久化到硬盘
     * @param eternal
     *            是否会死亡
     * @param timeToLiveSeconds
     *            缓存存活时间
     * @param timeToIdleSeconds
     *            缓存的间隔时间
     * @return缓存
     * @throws BaseException
     *             异常
     */
    public static Cache initCache(String cacheName, int maxElementsInMemory, boolean overflowToDisk, boolean eternal,
            long timeToLiveSeconds, long timeToIdleSeconds) throws BaseException {
        CacheManager singletonManager = CacheManager.create();
        Cache myCache = singletonManager.getCache(cacheName);
        if (myCache != null) {
            CacheConfiguration config = myCache.getCacheConfiguration();
            config.setTimeToLiveSeconds(timeToLiveSeconds);
            config.setMaxElementsInMemory(maxElementsInMemory);
            config.setOverflowToDisk(overflowToDisk);
            config.setEternal(eternal);
            config.setTimeToLiveSeconds(timeToLiveSeconds);
            config.setTimeToIdleSeconds(timeToIdleSeconds);
        }
        if (myCache == null) {
            Cache memoryOnlyCache = new Cache(cacheName, maxElementsInMemory, overflowToDisk, eternal,
                    timeToLiveSeconds, timeToIdleSeconds);
            singletonManager.addCache(memoryOnlyCache);
            myCache = singletonManager.getCache(cacheName);
        }
        return myCache;
    }

    /**
     * 初始化cache
     * 
     * @param cacheName
     *            cache的名字
     * @param timeToLiveSeconds
     *            有效时间 為0時緩存永恆
     * @return cache 缓存
     * @throws BaseException
     *             异常
     */
    public static Cache initCache(String cacheName, long timeToLiveSeconds) throws BaseException {
        CacheManager myManager = CacheManager.create();
        Cache myCache = myManager.getCache(cacheName);
        if (myCache != null) {
            CacheConfiguration config = myCache.getCacheConfiguration();
            config.setTimeToLiveSeconds(timeToLiveSeconds);
            config.setMaxElementsInMemory(EHCacheConfig.MAXELEMENTSINMEMORY);
            config.setMemoryStoreEvictionPolicy(EHCacheConfig.MEMORYSTOREEVICTIONPOLICY);
            config.setOverflowToDisk(EHCacheConfig.OVERFLOWTODISK);
            config.setEternal(timeToLiveSeconds == 0 ? true : false);
            config.setTimeToLiveSeconds(timeToLiveSeconds);
            config.setTimeToIdleSeconds(EHCacheConfig.TIMETOIDLESECONDS);
            config.setDiskPersistent(EHCacheConfig.DISKPERSISTENT);
            config.setDiskExpiryThreadIntervalSeconds(0);
        }
        if (myManager.getCache(cacheName) == null) {
            myCache = new Cache(new CacheConfiguration(cacheName, EHCacheConfig.MAXELEMENTSINMEMORY)
                    .memoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LFU)
                    .overflowToDisk(EHCacheConfig.OVERFLOWTODISK).eternal(timeToLiveSeconds == 0 ? true : false)
                    .timeToLiveSeconds(timeToLiveSeconds).timeToIdleSeconds(EHCacheConfig.TIMETOIDLESECONDS)
                    .diskPersistent(EHCacheConfig.DISKPERSISTENT).diskExpiryThreadIntervalSeconds(0));
            myManager.addCache(myCache);
        }
        return myCache;
    }

    /**
     * 修改缓存
     * 
     * @param cacheName
     *            缓存名
     * @param timeToLiveSeconds
     *            有效时间
     * @param maxElementsInMemory
     *            最大数量
     * @return真
     * @throws BaseException
     *             异常
     */
    public static boolean modifyCache(String cacheName, long timeToLiveSeconds, int maxElementsInMemory)
            throws BaseException {
        if (StringUtils.isNotBlank(cacheName) && timeToLiveSeconds != 0L && maxElementsInMemory != 0) {
            CacheManager myManager = CacheManager.create();
            Cache myCache = myManager.getCache(cacheName);
            CacheConfiguration config = myCache.getCacheConfiguration();
            config.setTimeToLiveSeconds(timeToLiveSeconds);
            config.setMaxElementsInMemory(maxElementsInMemory);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 请求向ehcache中设置值
     * 
     * @param cacheName
     *            cache名
     * @param key
     *            键
     * @param value
     *            值
     * @return返回真
     * @throws BaseException
     *             异常
     */
    public static boolean setValue(String cacheName, String key, Object value) throws BaseException {
        try {
            EhCacheManage.initCacheManager();
            EhCacheManage.put(cacheName, key, value);
            return true;
        } catch (BaseException e) {
            throw new BaseException("set cache " + cacheName + " failed!!!");
        }
    }

    /**
     * 从ehcache的指定容器中取值
     * 
     * @param key
     *            键
     * @return返回Object类型的值
     * @throws BaseException
     *             异常
     */
    public static Object getValue(String cacheName, String key) throws BaseException {
        try {
            EhCacheManage.initCacheManager();
            return EhCacheManage.get(cacheName, key);
        } catch (BaseException e) {
            throw new BaseException("get cache " + cacheName + " failed!!!");
        }
    }

    /**
     * 删除指定的ehcache容器
     * 
     * @param vesselName
     * @return真
     * @throws BaseException
     *             异常
     */
    public static boolean removeEhcache(String cacheName) throws BaseException {
        try {
            initCacheManager();
            removeCache(cacheName);
            return true;
        } catch (BaseException e) {
            throw new BaseException("remove cache " + cacheName + " failed!!!");
        }
    }

    // ------------------方便调用------------
    /**
     * 释放CacheManage
     * 
     */
    public static void shutdown() {
        cacheManager.shutdown();
    }

    /**
     * 移除cache
     * 
     * @param cacheName
     */
    public static void removeCache(String cacheName) {
        checkCacheManager();
        if (null != getCacheByName(cacheName)) {
            cacheManager.removeCache(cacheName);
        }

    }

    /**
     * 移除cache中的key
     * 
     * @param cacheName
     */
    public static void remove(String cacheName, String key) {
        checkCache(cacheName);
        getCacheByName(cacheName).remove(key);

    }

    /**
     * 移除所有cache
     */
    public static void removeAllCache() {
        checkCacheManager();
        cacheManager.removalAll();
    }

    /**
     * 移除所有Element
     */
    public static void removeAllKey(String cacheName) {
        getCacheByName(cacheName).removeAll();
    }

    /**
     * 获取所有的cache名称
     * 
     * @return
     */
    public static String[] getAllCaches() {
        checkCacheManager();
        return cacheManager.getCacheNames();
    }

    /**
     * 获取Cache所有的Keys
     * 
     * @return
     */
    public static List getKeys(String cacheName) {
        Cache cache = getCacheByName(cacheName);
        return cache.getKeys();
    }

    /**
     * @throws BaseException
     * 
     */
    public static HashMap<String, Object> getCacheValues(String cacheName) throws BaseException {
        HashMap<String, Object> retMap = new HashMap<String, Object>();
        List<String> list = getKeys(cacheName);
        for (String key : list) {
            retMap.put(key, getValue(cacheName, key));
        }
        return retMap;
    }

    /**
     * @throws BaseException
     * 
     */
    public static List<Object> getCacheValuesList(String cacheName) throws BaseException {
        List<Object> retList = new ArrayList<Object>();
        List<String> list = getKeys(cacheName);
        for (String key : list) {
            retList.add(getValue(cacheName, key));
        }
        return retList;
    }

    /**
     * 检测cacheManager
     */
    private static void checkCacheManager() {
        if (null == cacheManager) {
            throw new IllegalArgumentException("调用前请先初始化CacheManager值：EHCacheManage.initCacheManager");
        }

    }

    /**
     * 判断cache是否初始化
     * 
     * @param cacheName
     *            缓存名
     */
    private static void checkCache(String cacheName) {
        Cache cache = cacheManager.getCache(cacheName);
        if (null == cache) {
            throw new IllegalArgumentException("Cache未初始化");
        }
    }

    public void destroy() {
        LOG.info("Shutting down EHCache CacheManager");
        this.cacheManager.shutdown();
    }

}
