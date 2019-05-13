package com.imckh.gitblog;

import org.apache.http.client.utils.URIBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.awt.*;

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.imckh.gitblog.config",
        "com.imckh.gitblog.controller",
        "com.imckh.gitblog.service",
        "com.imckh.gitblog.repo"} )
public class GitBlogApplication {
    private static ApplicationContext context = null;

    public static void main(String[] args) {
        context = SpringApplication.run(GitBlogApplication.class, args);

    }

    // 获取当前环境
    public static String getActiveProfile() {
        return context.getEnvironment().getActiveProfiles()[0];
    }

    /**
     * 弹出浏览器。
     */
    private static void openBrowser(String scheme, String host, int port, String path) {
        try {
            URIBuilder uriBuilder = new URIBuilder().setScheme(scheme) //
                    .setHost(host) //
                    .setPort(port);
            if (path != null) {
                uriBuilder.setPath(path);
            }
            Desktop.getDesktop().browse(uriBuilder.build());
        } catch (Exception ex) {
        }
    }
}
