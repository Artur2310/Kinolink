package ru.kinolink.web.webapp.config;

import com.google.common.io.ByteStreams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import ru.kinolink.service.config.ServiceConfig;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

@Import(ServiceConfig.class)
public class DatabaseConfig {

    @Autowired
    Environment environment;

    @Autowired
    DataSource dataSource;

    /**
     * Init schema and data
     */
    @Bean
    public DataSourceInitializer dataSourceInitializer() {

        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
        try {
            if (environment.getProperty("init_data").equals("true")) {
                InputStream inputStream = new URL(environment.getProperty("url_data")).openStream();
                Resource initSchema = new ByteArrayResource(ByteStreams.toByteArray(inputStream));
                resourceDatabasePopulator.addScript(new ClassPathResource("schema.sql"));
                resourceDatabasePopulator.addScript(initSchema);
            }

        } catch (MalformedURLException exeption) {
            exeption.printStackTrace();
        } catch (IOException exeption) {
            exeption.printStackTrace();
        }

        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(dataSource);
        dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
        return dataSourceInitializer;
    }
}
