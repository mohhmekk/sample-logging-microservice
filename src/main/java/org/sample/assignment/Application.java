package org.sample.assignment;

/**
 * Spring Boot runner, loads Java based spring configurations and run the embedded tomcat server.
 *
 * @author Mohamed Mekkawy
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}