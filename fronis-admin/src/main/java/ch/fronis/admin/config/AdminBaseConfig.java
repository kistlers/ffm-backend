package ch.fronis.admin.config;

import ch.fronis.admin.controller.hibernate.AdminPlayerController;
import ch.fronis.admin.repository.PlayerRepository;
import ch.fronis.model.player.Player;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;

@Configuration
@EnableScheduling
public abstract class AdminBaseConfig implements SchedulingConfigurer, WebMvcConfigurer {

    @Value("${ws.baseUrl}")
    String baseUrl;

    @Value("${ws.authBaseUrl}")
    String authBaseUrl;

    @Value("${datasource.url}")
    String dataSourceUrl;

    @Value("${datasource.username}")
    String dataSourceUser;

    @Value("${datasource.password}")
    String dataSourcePassword;

    @Value("${datasource.driverClassName}")
    String dataSourceDriver;

    @Value("${datasource.maximumPoolSize}")
    String dataSourceMaximumPoolSize;

    @Value("${datasource.maxLifetime}")
    String dataSourceMaxLifetime;

    @Value("${datasource.connectionTimeout}")
    String dataSourceIdleTimeout;

    @Value("${datasource.connectionTimeout}")
    String dataSourceConnectionTimeout;

    public abstract DataSource dataSource();

    public abstract Flyway flyway();

//    @Bean
//    public AdminPlayerController adminPlayerController() {
//        return new AdminPlayerController();
//    }
}
