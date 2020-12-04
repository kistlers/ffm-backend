package ch.ffm.admin.config;

import ch.ffm.data.security.LogRequestInterceptor;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.util.Properties;
import javax.sql.DataSource;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableScheduling
public abstract class AdminBaseConfig implements SchedulingConfigurer, WebMvcConfigurer {

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
        config.setJdbcUrl(dataSourceUrl);
        config.setMaximumPoolSize(Integer.parseInt(dataSourceMaximumPoolSize));
        config.setMaxLifetime(Integer.parseInt(dataSourceMaxLifetime));
        config.setIdleTimeout(Integer.parseInt(dataSourceIdleTimeout));
        config.setConnectionTimeout(Integer.parseInt(dataSourceConnectionTimeout));
        return new HikariDataSource(config);
    }

    @Bean
    public Flyway flyway(DataSource dataSource) {
        Flyway flyWay = Flyway.configure().dataSource(dataSource).locations("classpath:/db/migration/mysql").load();
        flyWay.migrate();
        return flyWay;
    }

    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry) {
        interceptorRegistry.addInterceptor(new LogRequestInterceptor());
    }

    //    @Bean
    //    public FilterRegistrationBean<CorsFilter> corsFilter() {
    //        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    //        CorsConfiguration config = new CorsConfiguration();
    //        config.setAllowCredentials(true);
    //        config.addAllowedOrigin("https://r22.internet-box.ch/");
    //        config.addAllowedOrigin("http://localhost:3006");
    //        config.addAllowedHeader("*");
    //        config.addAllowedMethod("*");
    //        config.addExposedHeader("Content-Range");
    //        source.registerCorsConfiguration("/**", config);
    //        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new
    // CorsFilter(source));
    //        bean.setOrder(0);
    //        return bean;
    //    }
}
