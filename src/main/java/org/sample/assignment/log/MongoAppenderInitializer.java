package org.sample.assignment.log;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Injects Spring managed MongoLog4jAppender to the service logger.
 *
 * @author Mohamed Mekkawy
 */
@Component
public class MongoAppenderInitializer implements InitializingBean {

    /**
     * The logger service name.
     */
    public final static String LOGGER_SERVICE_NAME = "assignment.service";

    @Autowired
    private MongoLog4jAppender mongoLog4jAppender;

    @Override
    public void afterPropertiesSet() throws Exception {
        Logger.getLogger(LOGGER_SERVICE_NAME).addAppender(mongoLog4jAppender);
    }
}
