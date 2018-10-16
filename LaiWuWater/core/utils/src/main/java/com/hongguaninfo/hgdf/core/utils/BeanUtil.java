package com.hongguaninfo.hgdf.core.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.hongguaninfo.hgdf.core.utils.logging.Log;
import com.hongguaninfo.hgdf.core.utils.logging.LogFactory;

public class BeanUtil implements ApplicationContextAware {

    private Log log = LogFactory.getLog(getClass());

    public static String NAME = "beanLocator";

    private ApplicationContext context;

    private static BeanUtil instance;

    public void setApplicationContext(ApplicationContext context) throws BeansException {
        this.context = context;
    }

    public BeanUtil() {

    }

    public void init() {
        instance = this;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(String beanName) {
        return (T) getContext().getBean(beanName);
    }

    public static ApplicationContext getContext() {
        return instance.context;
    }

}
