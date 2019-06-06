package ru.kinolink.web.integration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.kinolink.web.webapp.config.DatabaseConfig;
import ru.kinolink.web.webapp.config.LocaleConfig;

@Configuration
@EnableAutoConfiguration
@Import({TestDBConfig.class,
        LocaleConfig.class,
        DatabaseConfig.class})
@PropertySource("test-config.properties")
@ComponentScan(basePackages = "ru.kinolink.web.webapp.controller")
public class TestAppConfig implements WebMvcConfigurer {

}
