package ru.kinolink.web.webapp.config;

import org.springframework.context.annotation.Import;
import ru.kinolink.service.config.ServiceConfig;

@Import({DatabaseConfig.class,
        ServiceConfig.class,
        ServerConfig.class,
        LocaleConfig.class})
public class AppConfig {


}
