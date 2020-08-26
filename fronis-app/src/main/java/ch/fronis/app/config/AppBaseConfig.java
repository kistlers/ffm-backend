package ch.fronis.app.config;

import ch.fronis.service.player.JDBCPlayerDataService;
import ch.fronis.service.player.PlayerDataService;
import ch.fronis.service.security.LogRequestInterceptor;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;

@Configuration
@EnableScheduling
public abstract class AppBaseConfig implements SchedulingConfigurer, WebMvcConfigurer {

    @Value("${ws.baseUrl}")
    String baseUrl;

    @Value("${datasource.url}")
    String dataSourceUrl;

    @Value("${datasource.username}")
    String dataSourceUser;

    @Value("${datasource.password}")
    String dataSourcePassword;

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

    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry) {
        interceptorRegistry.addInterceptor(new LogRequestInterceptor());
    }

    @Bean
    public PlayerDataService playerDataService() {
        return new JDBCPlayerDataService(dataSource());
    }
}
