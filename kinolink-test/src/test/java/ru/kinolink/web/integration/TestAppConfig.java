package ru.kinolink.web.integration;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.kinolink.service.config.ServiceConfig;
import ru.kinolink.web.webapp.config.LocaleConfig;
import ru.kinolink.web.webapp.controller.AdminMovieController;

@Configuration
@EnableAutoConfiguration
@Import({TestDBConfig.class,
        LocaleConfig.class,
        ServiceConfig.class})
//@PropertySource("test-config.properties")
@ComponentScan(basePackageClasses = AdminMovieController.class)
public class TestAppConfig implements WebMvcConfigurer {

}
