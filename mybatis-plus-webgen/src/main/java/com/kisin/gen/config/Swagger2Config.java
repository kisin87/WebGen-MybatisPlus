package com.kisin.gen.config;

import springfox.documentation.service.Contact;
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
 * @Author: shebin(kisin)
 * @Date: Create in 2019-11-21 17:22
 * @Description:
 */

@Configuration
@EnableSwagger2
public class Swagger2Config {

    /**
     * @Description:swagger2的配置文件，这里可以配置swagger2的一些基本的内容，比如扫描的包等等
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
            .apis(RequestHandlerSelectors.basePackage("com.kisin"))
            .paths(PathSelectors.any()).build();
    }

    /**
     * @Description: 构建 api文档的信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            // 设置页面标题
            .title("api接口文档")
            // 设置联系人
            .contact(new Contact("kisin", "http://www.a.com", "kisin@kisin.kisin"))
            // 描述
            .description("欢迎访问，这里是描述信息")
            // 定义版本号
            .version("1.0").build();
    }
}
