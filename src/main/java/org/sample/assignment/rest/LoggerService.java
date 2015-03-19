package org.sample.assignment.rest;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.sample.assignment.log.MongoAppenderInitializer;
import org.sample.assignment.model.ApplicationLog;
import org.sample.assignment.model.CustomerLog;
import org.sample.assignment.util.LogMessageFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

/**
 * JAX-RS Logging service, endpoint for client apps to invoke the logging service.
 *
 * @author Mohamed Mekkawy
 */
@Path("/log")
@Component
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LoggerService {

    /**
     * MongoDB logger, logger for remote apps.
     */
    Logger loggerService = Logger.getLogger(MongoAppenderInitializer.LOGGER_SERVICE_NAME);
    /**
     * stdout logger, utility logger for the service logs.
     */
    Logger logger = Logger.getLogger(LoggerService.class);

    /**
     * Log an application log message, message parameters are passed as request query parameters.
     */
    @GET
    @Path("app")
    public void applicationLog(@QueryParam("level") String level, @Context UriInfo uriInfo) {
        logger.info(uriInfo.getQueryParameters());
        ApplicationLog applicationLog = LogMessageFactory.createApplicationLogMessage(uriInfo.getQueryParameters());
        logger.debug("Logging application log message [" + applicationLog + "]");
        loggerService.log(Level.toLevel(level), applicationLog);
    }


    /**
     * Log a customer related log message, message parameters are passed as request query parameters.
     */
    @GET
    @Path("customer")
    public String customerLog(@QueryParam("level") String level, @Context UriInfo uriInfo) {
        CustomerLog customerLog = LogMessageFactory.createCustomerLogMessage(uriInfo.getQueryParameters());
        logger.debug("Logging customer log message [" + customerLog + "]");
        loggerService.log(Level.toLevel(level), customerLog);
        return "ok";
    }

}
