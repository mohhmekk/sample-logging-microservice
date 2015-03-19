package org.sample.assignment.rest;

import org.apache.log4j.Logger;
import org.sample.assignment.model.ApplicationLog;
import org.sample.assignment.model.CustomerLog;
import org.sample.assignment.service.ApplicationLogService;
import org.sample.assignment.service.CustomerLogService;
import org.sample.assignment.util.CustomerLogStatistics;
import org.sample.assignment.util.LogMessageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.util.List;

/**
 * JAX-RS Statistics service, prototype for data mining and data analysis operations.
 *
 * Implemented the following services:
 * 1- all log messages for a specific customer.
 * 2- list of most visited products
 * 3- all logs for a specific application.
 * 4- Most visited product for a specific customer.
 * 5- full log search for both Application and customer logs.
 *
 * @author Mohamed Mekkawy
 */
@Path("/statistics")
@Component
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StatisticsService {

    Logger logger = Logger.getLogger(StatisticsService.class);

    @Autowired
    private CustomerLogService customerLogService;

    @Autowired
    private ApplicationLogService applicationLogService;

    /**
     * Retrieves products visited by this customer ordered by most visited one.
     *
     * @param customerId The customer ID.
     * @return List of products and the corresponding number of visits
     */
    @GET
    @Path("mostVisitedProducts")
    public List<CustomerLogStatistics> getMostVisitedProducts(@QueryParam("customerId") String customerId) {
        logger.debug("getMostVisitedProducts(" + customerId + ")");
        return customerLogService.mostVisitedProducts(customerId);
    }

    /**
     * Retrieves most visited product.
     *
     * @param customerId The customer ID.
     * @return Most visited product.
     */
    @GET
    @Path("recommendedProduct")
    public CustomerLogStatistics getMostVisitedProduct(@QueryParam("customerId") String customerId) {
        logger.debug("getMostVisitedProduct(" + customerId + ")");
        return customerLogService.mostVisitedProduct(customerId);
    }

    /**
     * All log messages for a specific customer.
     *
     * @param customerId The customer ID.
     * @return List of CustomerLog for this customer.
     */
    @GET
    @Path("allCustomerLogs")
    public List<CustomerLog> getCustomerLogs(@QueryParam("customerId") String customerId) {
        logger.debug("getCustomerLogs(" + customerId + ")");
        return customerLogService.findByCustomerId(customerId);
    }

    /**
     * All log messages for a specific application.
     *
     */
    @GET
    @Path("allAppLogs")
    public List<ApplicationLog> getAppLogs(@QueryParam("applicationName") String appName) {
        logger.debug("getAppLogs("+appName+")");
        return applicationLogService.findAll(appName);
    }

    /**
     * Search all application logs matching all passed strings in query parameters:
     * /searchAppLogs?param1=XXX&param2=YYY....
     *
     * @return list of matched records sorted by relevance
     */
    @GET
    @Path("searchAppLogs")
    public List<ApplicationLog> searchAppLogs(@Context UriInfo uriInfo) {
        logger.debug("searchAppLogs()");
        String[] texts = LogMessageFactory.getAllValues(uriInfo.getQueryParameters());
        return applicationLogService.searchForText(texts);
    }

    /**
     * Search all customer logs matching all passed strings in query parameters:
     * /searchCustomerLogs?param1=XXX&param2=YYY....
     *
     * @return list of matched records sorted by relevance
     */
    @GET
    @Path("searchCustomerLogs")
    public List<CustomerLog> searchCustomerLogs(@Context UriInfo uriInfo) {
        logger.debug("searchAppLogs()");
        String[] texts = LogMessageFactory.getAllValues(uriInfo.getQueryParameters());
        return customerLogService.searchForText(texts);
    }


}
