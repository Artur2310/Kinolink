package ru.kinolink.web.webapp.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.kinolink.web.config.ServiceConfig;

@Import(ServiceConfig.class)
public class DatabaseConfig {
}
