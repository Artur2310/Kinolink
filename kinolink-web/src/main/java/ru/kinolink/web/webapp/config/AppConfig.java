package ru.kinolink.web.webapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.kinolink.web.webapp.config.DatabaseConfig;
import ru.kinolink.web.webapp.config.ServerConfig;

@Configuration
@Import({DatabaseConfig.class,
        ServerConfig.class})
public class AppConfig {


}
