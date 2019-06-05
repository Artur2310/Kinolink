package ru.kinolink.web.integration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.kinolink.web.config.ServiceConfig;
import ru.kinolink.web.webapp.config.DatabaseConfig;
import ru.kinolink.web.webapp.config.LocaleConfig;

@Configuration
@EnableAutoConfiguration
@Import({TestDBConfig.class,
        LocaleConfig.class,
        ServiceConfig.class})
//@PropertySource("test-config.properties")
@ComponentScan(basePackageClasses = AdminMovieController.class)
public class TestAppConfig implements WebMvcConfigurer {

}
