package ru.kinolink.web.webapp.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.kinolink.web.config.ServiceConfig;

@Configuration
@Import(ServiceConfig.class)
public class AppConfig {

}
