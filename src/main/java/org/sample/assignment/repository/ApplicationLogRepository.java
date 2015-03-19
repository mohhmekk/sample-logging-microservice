package org.sample.assignment.repository;

import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.Predicate;
import org.sample.assignment.model.ApplicationLog;
import org.sample.assignment.model.CustomerLog;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by Mohamed Mekkawy on 17/03/2015.
 */
public interface ApplicationLogRepository extends CrudRepository<ApplicationLog, BigInteger>, QueryDslPredicateExecutor<ApplicationLog> {
    @Override
    List<ApplicationLog> findAll();

    @Override
    List<ApplicationLog> findAll(Iterable<BigInteger> bigIntegers);

    @Override
    List<ApplicationLog> findAll(Predicate predicate);

    @Override
    List<ApplicationLog> findAll(Predicate predicate, OrderSpecifier<?>... orders);

    List<ApplicationLog> findByApplicationName(String applicationName);

    List<ApplicationLog> findAllBy(TextCriteria criteria);

    List<ApplicationLog> findAllByOrderByScoreDesc(TextCriteria criteria);

}
