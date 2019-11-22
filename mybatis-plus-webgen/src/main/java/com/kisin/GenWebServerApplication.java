package com.kisin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author: shebin(kisin)
 * @Date: Create in 2019-08-07 17:01
 * @Description:
 */

@EnableSwagger2
@SpringBootApplication
@MapperScan({"com.kisin.*.dao", "com.kisin.*.mapper"})
public class GenWebServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(GenWebServerApplication.class, args);
    }
}
