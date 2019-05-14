package ru.kinolink.web.integration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.kinolink.web.config.ServiceConfig;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackageClasses = {ServiceConfig.class})
@EnableJpaRepositories(basePackageClasses = {ServiceConfig.class})
@Import({TestDBConfig.class,
        ServiceConfig.class})
public class TestAppConfig {

}
