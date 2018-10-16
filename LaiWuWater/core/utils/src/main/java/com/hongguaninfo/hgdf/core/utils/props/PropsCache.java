package com.hongguaninfo.hgdf.core.utils.props;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.ResourceBundle;

/**
 * 初始化配置文件（指定列表props-cache-list）信息到静态属性中
 * 
 * @author Administrator
 * 
 */
public class PropsCache {

    private static HashMap<String, String> PROPS_CACHE_MAP = new HashMap<String, String>();

    private static String PROPS_CACHE_RES_NAME = "util.props-cache-list";

    static {
        initPropsCache();
    }

    /**
     * 获取信息
     * 
     * @return
     */
    public static String getProp(String propName) {
        return PROPS_CACHE_MAP.get(propName);
    }

    /**
     * 获取所有缓存信息
     * 
     * @return
     */
    public static HashMap<String, String> getAllProps() {
        return PROPS_CACHE_MAP;
    }

    /**
     * 初始化数据
     */
    public static void initPropsCache() {
        PROPS_CACHE_MAP.clear();
        HashMap<String, String> maps = PropertyUtils.getPropertyMap(PROPS_CACHE_RES_NAME);
        for (Entry<String, String> entry : maps.entrySet()) {
            HashMap<String, String> tmpMap = PropertyUtils.getPropertyMap(entry.getKey());
            for (Entry<String, String> tmpEntry : tmpMap.entrySet()) {
                PROPS_CACHE_MAP.put(tmpEntry.getKey(), tmpEntry.getValue());
            }
        }
    }

    /**
     * 刷新
     */
    public static void refreshPropsCache() {
        ResourceBundle.clearCache();
        initPropsCache();
    }

    /**
     * 清理掉缓存
     */
    public static void clearCache() {
        ResourceBundle.clearCache();
    }

    // public static void main(String[] args) {
    // logger.info(String.valueOf(PropsCache.PROPS_CACHE_MAP));
    // }
}
