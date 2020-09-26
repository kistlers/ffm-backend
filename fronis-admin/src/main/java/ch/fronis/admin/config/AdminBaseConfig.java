package ch.fronis.admin.config;

import ch.fronis.service.security.LogRequestInterceptor;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;

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

    public abstract DataSource dataSource();

    public abstract Flyway flyway();

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
//        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
//        bean.setOrder(0);
//        return bean;
//    }
}
