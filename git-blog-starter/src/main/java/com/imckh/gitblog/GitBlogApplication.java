package com.imckh.gitblog;

import com.imckh.gitblog.util.SpringContextUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@Log4j2
@SpringBootApplication
@ComponentScan(basePackages = {
        "com.imckh.gitblog.config",
        "com.imckh.gitblog.api",
        "com.imckh.gitblog.service",
        "com.imckh.gitblog.util",
        "com.imckh.gitblog.model",
        "com.imckh.gitblog.repo"})
public class GitBlogApplication {
    private static ApplicationContext context = null;

    public static void main(String[] args) {
        context = SpringApplication.run(GitBlogApplication.class, args);

        String activeProfile = SpringContextUtil.getActiveProfile();
        String port = SpringContextUtil.getPort();
    }
}
