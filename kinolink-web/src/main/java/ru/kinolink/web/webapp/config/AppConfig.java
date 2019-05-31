package ru.kinolink.web.webapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.kinolink.web.config.ServiceConfig;

@Import({DatabaseConfig.class,
        ServiceConfig.class,
        ServerConfig.class,
        LocaleConfig.class})
public class AppConfig {


}
