package ch.ffm.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

@Configuration
@Profile("dev")
public class AppDevConfig extends AppBaseConfig {

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {}
}
