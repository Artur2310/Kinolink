package ru.kinolink.web.webapp.config;

import org.springframework.context.annotation.Import;
import ru.kinolink.service.config.ServiceConfig;

@Import(ServiceConfig.class)
public class DatabaseConfig {
}
