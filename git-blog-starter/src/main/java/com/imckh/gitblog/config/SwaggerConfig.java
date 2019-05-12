package com.imckh.gitblog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Springfox 提供了一个 Docket 对象，让我们可以灵活的配置 Swagger 的各项属性
 * <br/>
 * 注解 @Configuration 是告诉 Spring Boot 需要加载这个配置类，@EnableSwagger2 是启用 Swagger2，如果没加的话自然而然也就看不到后面的验证效果了。
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("git-blog")
                .description("Convert a github repository to a blog")
                .termsOfServiceUrl("https://github.com/imckh/git-blog")
                .version("0.0.1")
                .build();
    }

}