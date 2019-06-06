package ru.kinolink.web.webapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import ru.kinolink.service.config.ServiceConfig;

import javax.sql.DataSource;

@Import(ServiceConfig.class)
public class DatabaseConfig {

    @Autowired
    Environment environment;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource basicDataSource = new DriverManagerDataSource();
        basicDataSource.setDriverClassName(com.mysql.jdbc.Driver.class.getName());
        basicDataSource.setUsername("root");
        basicDataSource.setPassword("Klarkson2310!");
        basicDataSource.setUrl("jdbc:mysql://localhost:3306/kinolink?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");

        /*try {
            InputStream inputStream = new URL("https://downloader.disk.yandex.ru/disk/1a4398c3d66caca20caaf209561b5be9cb1828687b1e28eccc4170c332dc8ca4/5cf91ec6/8Vx--aj7_7HSb0_BTSbtHiLAKBPjj2VZRMnuB5--6d1huO_bkISLzR-BsYFkNH6ATY2k4jr-hcPMzHkoD1nKKg%3D%3D?uid=0&filename=data.sql&disposition=attachment&hash=b6SgPJL%2BieKbO5SoKUGuvcI3a2FjTV/RI6mx5JabRgGPbdMemoMElXCZv62Z1Ry/q/J6bpmRyOJonT3VoXnDag%3D%3D&limit=0&content_type=text%2Fplain&fsize=28710680&hid=a2d8c24900aaa4de8b9fedfc1003b642&media_type=data&tknv=v2").openStream();
            if (environment.getProperty("init_data").equals("true")) {

                Resource initSchema = new FileUrlResource("C:\\kinolink\\movie.sql");
                //Resource initSchema = new ByteArrayResource(IOUtils.readNBytes(inputStream, inputStream.available()));
                DatabasePopulator databasePopulator = new ResourceDatabasePopulator(initSchema);
                DatabasePopulatorUtils.execute(databasePopulator, basicDataSource);
            }
        }catch (Exception e){

        }*/
        return basicDataSource;
    }

    /**
     * Init schema and data
     */
    @Bean
    public DataSourceInitializer dataSourceInitializer() {

        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
        try{
            //resourceDatabasePopulator.addScript(new ClassPathResource("schema.sql"));
            //resourceDatabasePopulator.addScript(new FileUrlResource("C:\\kinolink\\data.sql"));
    }catch (Exception e){

    }

        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(dataSource());
        dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
        return dataSourceInitializer;
    }
}
