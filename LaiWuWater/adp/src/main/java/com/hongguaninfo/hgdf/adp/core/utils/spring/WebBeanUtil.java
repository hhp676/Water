package com.hongguaninfo.hgdf.adp.core.utils.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class WebBeanUtil implements ApplicationContextAware {

    private ApplicationContext context;

    private static WebBeanUtil instance;

    public void setApplicationContext(ApplicationContext context) throws BeansException {
        this.context = context;
    }

    public WebBeanUtil() {

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
