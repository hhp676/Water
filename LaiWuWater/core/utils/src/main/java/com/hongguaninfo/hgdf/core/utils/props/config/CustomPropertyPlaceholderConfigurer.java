package com.hongguaninfo.hgdf.core.utils.props.config;

import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import com.hongguaninfo.hgdf.core.utils.logging.Log;
import com.hongguaninfo.hgdf.core.utils.logging.LogFactory;

/**
 * 自定义属性配置类
 * 
 * @author henry
 * 
 */
public class CustomPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {
    private static Properties props;
    private static Log log = LogFactory.getLog(CustomPropertyPlaceholderConfigurer.class);
    
    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props)
            throws BeansException {
        super.processProperties(beanFactory, props);
        log.debug(String.valueOf(props));
        this.props = props;
    }

    public Properties getProps() {
        return props;
    }

    public Object getProperty(String key) {
        return props.get(key);
    }
}