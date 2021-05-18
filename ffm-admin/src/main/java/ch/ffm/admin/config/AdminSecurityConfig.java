package ch.ffm.admin.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

@Configuration
@Profile(value = {"prod", "dev"})
@EnableWebSecurity
public class AdminSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(AdminSecurityConfig.class);

    @Value("#{${ws.admin.users}}")
    Map<String, String> adminUsers;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        for (Map.Entry<String, String> adminUser : adminUsers.entrySet()) {
            logger.info("registered login for admin user {}", adminUser.getKey());
            auth.inMemoryAuthentication().withUser(adminUser.getKey()).password(adminUser.getValue()).roles("ADMIN");
        }
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        logger.debug("running configure");
        http.cors();
        http.authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/login", "/v1/api/**").permitAll(); // allow CORS option calls

        http.authorizeRequests().antMatchers("/v1/api/**").hasAnyRole("ADMIN");
        http.authorizeRequests().anyRequest().authenticated();
        http.formLogin().loginProcessingUrl("/login").successHandler(successHandler()).failureHandler(failureHandler()).permitAll();
        http.csrf().disable().exceptionHandling().authenticationEntryPoint(entryPoint403());
        http.logout().logoutUrl("/logout");
        http.logout().logoutSuccessHandler((new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK)));

        http.headers().cacheControl().disable();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(Collections.singletonList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "CREATE", "PUT", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(1000000L);
        configuration.addExposedHeader("Set-Cookie");
        configuration.addExposedHeader("Date");
        configuration.addExposedHeader("Content-Range");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    private AuthenticationSuccessHandler successHandler() {
        return (httpServletRequest, httpServletResponse, authentication) -> {
            httpServletResponse.getWriter().append("OK");
            httpServletResponse.setStatus(200);
        };
    }

    private AuthenticationFailureHandler failureHandler() {
        return (httpServletRequest, httpServletResponse, e) -> {
            httpServletResponse.getWriter().append("Authentication failure");
            httpServletResponse.setStatus(401);
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public Http403ForbiddenEntryPoint entryPoint403() {
        return new Http403ForbiddenEntryPoint();
    }
}
