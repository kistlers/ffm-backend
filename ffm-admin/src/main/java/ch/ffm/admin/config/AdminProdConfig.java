package ch.ffm.admin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

@Configuration
@Profile("prod")
public class AdminProdConfig extends AdminBaseConfig {

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {}
}
