package ch.ffm.admin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class AdminDevConfig extends AdminBaseConfig {}
