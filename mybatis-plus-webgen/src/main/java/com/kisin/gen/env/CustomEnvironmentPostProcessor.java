package com.kisin.gen.env;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * @Author: shebin(kisin)
 * @Date: Create in 2019-08-11 18:09
 * @Description:
 */
public class CustomEnvironmentPostProcessor implements EnvironmentPostProcessor {
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {

        Properties properties = new Properties();

        try {
            properties.load(new InputStreamReader(CustomEnvironmentPostProcessor.class.getClassLoader().getResourceAsStream("web-gen.properties"),"UTF-8"));

            PropertiesPropertySource propertiesPropertySource = new PropertiesPropertySource("my",properties);
            environment.getPropertySources().addLast(propertiesPropertySource);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
