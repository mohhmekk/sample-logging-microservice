package org.sample.assignment.service;

import org.sample.assignment.model.ApplicationLog;
import org.sample.assignment.model.CustomerLog;
import org.sample.assignment.repository.CustomerLogRepository;
import org.sample.assignment.util.CustomerLogStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

/**
 * Service component That performs business operations on the CustomerLog data model.
 * <p/>
 * Created by Mohamed Mekkawy on 18/03/2015.
 */
@Service
public class CustomerLogService {

    @Autowired
    CustomerLogRepository customerLogRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    /**
     * Retrieves products visited by this customer ordered by most visited one.
     *
     * @param customerId The customer ID.
     * @return List of products and the corresponding number of visits
     */
    public List<CustomerLogStatistics> mostVisitedProducts(String customerId) {
        Aggregation aggregation = newAggregation(CustomerLog.class, match(Criteria.where("customerId").is(customerId)),
                group("productId").count().as("total"),
                project("total").and("productId").previousOperation(),
                sort(Sort.Direction.DESC, "total"));

        AggregationResults<CustomerLogStatistics> groupResults = mongoTemplate.aggregate(aggregation, CustomerLog.class, CustomerLogStatistics.class);

        return groupResults.getMappedResults();

    }

    /**
     * Retrieves the most visited product.
     *
     * @param customerId The customer ID.
     * @return List of products and the corresponding number of visits
     */
    public CustomerLogStatistics mostVisitedProduct(String customerId) {
        return mostVisitedProducts(customerId).get(0);

    }

    /**
     * Deep search in all customer log entries for any match with the passed texts.
     * This uses the full text search of MongoDB.
     *
     * @param text search terms
     * @return matched log records sorted by match score
     */
    public List<CustomerLog> searchForText(String... text){
        TextCriteria criteria = TextCriteria.forDefaultLanguage()
                .matchingAny(text);
        return customerLogRepository.findAllByOrderByScoreDesc(criteria);
    }

    /**
     * Finds all logs related to a specific customer
     *
     * @param customerId
     * @return
     */
    public List<CustomerLog> findByCustomerId(String customerId) {
        return customerLogRepository.findByCustomerId(customerId);
    }

    @Async
    public void saveCustomerLog(CustomerLog customerLog) {
        customerLogRepository.save(customerLog);
    }
}
