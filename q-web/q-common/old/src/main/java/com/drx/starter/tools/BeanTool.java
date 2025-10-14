package com.drx.starter.tools;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class BeanTool implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public static <A> A getBean(Class<A> clazz) {
        return applicationContext.getBean(clazz);
    }

    public static <B> B getBean(String beanName, Class<B> clazz) {
        return applicationContext.getBean(beanName, clazz);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        BeanTool.applicationContext = applicationContext;
    }

}
