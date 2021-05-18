package ch.ffm.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class AppDevConfig extends AppBaseConfig {}
