package ru.kinolink.web.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "ru.kinolink.web.repository")
@ComponentScan(basePackages = "ru.kinolink.web")
@EntityScan(basePackages = "ru.kinolink.web.model")
public class ServiceConfig {
}
