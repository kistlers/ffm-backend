package ch.fronis.admin.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@Profile("prod")
public class AdminProdConfig extends AdminBaseConfig {

    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        Properties props = new Properties();
        props.put("url", dataSourceUrl);
        props.put("user", dataSourceUser);
        props.put("password", dataSourcePassword);
        props.put("encoding", "utf8");
        props.put("cachePrepStmts", "true");
        props.put("prepStmtCacheSize", "250");
        props.put("prepStmtCacheSqlLimit", "2048");
        props.put("generateSimpleParameterMetadata", true);
        config.setDataSourceProperties(props);
//        config.setDataSourceClassName(null);
        config.setJdbcUrl(dataSourceUrl);
//        config.setDataSourceClassName(MysqlDataSource);
//        config.setDataSourceClassName(dataSourceDriver);
        config.setMaximumPoolSize(Integer.parseInt(dataSourceMaximumPoolSize));
        config.setMaxLifetime(Integer.parseInt(dataSourceMaxLifetime));
        config.setIdleTimeout(Integer.parseInt(dataSourceIdleTimeout));
        config.setConnectionTimeout(Integer.parseInt(dataSourceConnectionTimeout));
        return new HikariDataSource(config);
    }

    @Bean
    @Override
    public Flyway flyway() {
        Flyway flyWay = Flyway.configure().dataSource(dataSource()).locations("classpath:/db/migration/mysql").load();
        flyWay.migrate();
        return flyWay;
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {

    }
}
