package ru.kinolink.test.integration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.kinolink.service.config.ServiceConfig;
import ru.kinolink.web.webapp.config.LocaleConfig;

@Configuration
@EnableAutoConfiguration
@Import({TestDBConfig.class,
        LocaleConfig.class,
        ServiceConfig.class})
@ComponentScan(basePackages = "ru.kinolink.web.webapp.controller")
public class TestAppConfig implements WebMvcConfigurer {

}