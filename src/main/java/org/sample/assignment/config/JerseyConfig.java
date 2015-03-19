package org.sample.assignment.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.sample.assignment.rest.LoggerService;
import org.sample.assignment.rest.StatisticsService;
import org.springframework.stereotype.Component;

/**
 * Jersey Configurations.
 * <p/>
 * Created by Mohamed Mekkawy on 18/03/2015.
 */
@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(LoggerService.class);
        register(StatisticsService.class);
    }
}
