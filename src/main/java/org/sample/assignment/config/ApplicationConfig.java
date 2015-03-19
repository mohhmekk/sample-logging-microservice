package org.sample.assignment.config;

import com.mongodb.MongoClient;
import org.sample.assignment.model.ApplicationLog;
import org.sample.assignment.model.CustomerLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.repository.init.Jackson2RepositoryPopulatorFactoryBean;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PreDestroy;

/**
 * Spring Configurations.
 */
@Configuration
@ComponentScan("org.sample")
@EnableAutoConfiguration
@PropertySource("classpath:db.properties")
public class ApplicationConfig {

    /**
     * MongoDb configurations are read from properties file db.properties
     */
    @Value("${mongo.host}")
    String mongoHost;
    @Value("${mongo.port}")
    int mongoPort;
    @Value("${mongo.user}")
    String mongoUser;
    @Value("${mongo.pass}")
    String mongoPass;

    @Autowired
    MongoTemplate mongoTemplate;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public
    @Bean
    MongoDbFactory mongoDbFactory() throws Exception {
        return new SimpleMongoDbFactory(new MongoClient(mongoHost, mongoPort), "log", new UserCredentials(mongoUser, mongoPass));
    }

    public
    @Bean
    MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongoDbFactory());

    }

    /**
     * Populate ApplicationLog collection with sample data.
     *
     */
    @Bean
    public Jackson2RepositoryPopulatorFactoryBean repositoryPopulator() {

        Jackson2RepositoryPopulatorFactoryBean factoryBean = new Jackson2RepositoryPopulatorFactoryBean();
        factoryBean.setResources(new Resource[]{new ClassPathResource("app-log.json")});
        return factoryBean;
    }

    /**
     * Clean up after execution by dropping used test db instance.
     *
     * @throws Exception
     */
    @PreDestroy
    void dropTestDB() throws Exception {
        mongoTemplate.dropCollection(ApplicationLog.class);
        mongoTemplate.dropCollection(CustomerLog.class);
    }

}