package com.imckh.gitblog.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * <p>Title: SpringContextUtil </p>
 * <p>Description: </p>
 *
 * @author Cui Kaihui
 * @date 2019/5/13 21:36
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {
    private static final String PROD = "prod";
    private static final String DEV = "dev";
    private static final String TEST = "test";

    private static Environment environment;
    private static ApplicationContext context = null;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        context = applicationContext;
        environment = context.getEnvironment();
    }


    // 传入线程中
    public static <T> T getBean(String beanName) {
        return (T) context.getBean(beanName);
    }


    /**
     * 国际化使用
     * @param key
     * @return
     */
    public static String getMessage(String key) {
        return context.getMessage(key, null, Locale.getDefault());
    }


    /**
     * 获取当前环境(spring.profile.active)
     * @return 当前环境
     */
    public static String getActiveProfile() {
        return environment.getActiveProfiles()[0];
    }

    /**
     * 获取当前端口(spring.profile.active)
     * @return
     */
    public static String getPort() {
        return environment.getProperty("server.port");
    }

    public static String[] getActiveProfiles() {
        return environment.getActiveProfiles();
    }
}