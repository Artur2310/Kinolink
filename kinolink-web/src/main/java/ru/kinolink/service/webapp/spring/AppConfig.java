package ru.kinolink.service.webapp.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.kinolink.service.config.ServiceConfig;

@Configuration
@Import(ServiceConfig.class)
public class AppConfig {

}
