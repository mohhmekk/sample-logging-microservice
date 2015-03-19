package org.sample.assignment.repository;

import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.Predicate;
import org.sample.assignment.model.CustomerLog;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by Mohamed Mekkawy on 17/03/2015.
 */
public interface CustomerLogRepository extends MongoRepository<CustomerLog, BigInteger>, QueryDslPredicateExecutor<CustomerLog> {
    @Override
    List<CustomerLog> findAll();

    @Override
    List<CustomerLog> findAll(Iterable<BigInteger> bigIntegers);

    @Override
    List<CustomerLog> findAll(Predicate predicate);

    @Override
    List<CustomerLog> findAll(Predicate predicate, OrderSpecifier<?>... orders);


    List<CustomerLog> findAllBy(TextCriteria criteria);

    List<CustomerLog> findAllByOrderByScoreDesc(TextCriteria criteria);


    List<CustomerLog> findByCustomerId(String customerId);
}
