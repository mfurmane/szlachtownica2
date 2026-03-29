package priv.mfurmane.szlachtownica;

import net.sf.log4jdbc.sql.jdbcapi.DataSourceSpy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;


@Configuration
public class AppConfig {
//    @Autowired
//    DataSourceProperties dataSourceProperties;

//    @Bean
//    @Primary
//    public DataSource dataSource(DataSource original) {
//        return new DataSourceSpy(original);
//    }
}