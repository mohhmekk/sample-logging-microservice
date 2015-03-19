package org.sample.assignment.service;

import org.apache.log4j.Logger;
import org.sample.assignment.model.ApplicationLog;
import org.sample.assignment.repository.ApplicationLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Mohamed Mekkawy on 18/03/2015.
 */
@Service
public class ApplicationLogService {

    Logger logger = Logger.getLogger(ApplicationLogService.class);

    @Autowired
    private ApplicationLogRepository applicationLogRepository;

    @Async
    public void saveApplicationLog(ApplicationLog applicationLog) {
        applicationLogRepository.save(applicationLog);
    }

    /**
     * Finds all logs for a specific application
     *
     * @param applicationName
     * @return
     */
    public List<ApplicationLog> findAll(String applicationName){
        return applicationLogRepository.findByApplicationName(applicationName);
    }

    /**
     * Deep search in all application log entries for any match with the passed texts.
     * This uses the full text search of MongoDB.
     *
     * @param text search terms
     * @return matched log records sorted by match score
     */
    public List<ApplicationLog> searchForText(String... text){
        logger.debug("searchForText("+text+")");
        TextCriteria criteria = TextCriteria.forDefaultLanguage()
                .matchingAny(text);
        return applicationLogRepository.findAllByOrderByScoreDesc(criteria);
    }
}
