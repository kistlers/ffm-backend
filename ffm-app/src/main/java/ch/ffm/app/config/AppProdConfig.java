package ch.ffm.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("prod")
public class AppProdConfig extends AppBaseConfig {}
