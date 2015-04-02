# Spring Boot Microservice
Sample Logging service that logs messages to a Mongo DB.
Implemented as a microservice with REST endpoints.
Built with Spring Boot and Jersey.
Supports deep search in all of the logged messages using Mongo Full text search http://docs.mongodb.org/manual/core/index-text/ 

## Overview
The Logging service is implemented as a microservice that could be deployed/invoked independent of any client application.
**Spring** and **Spring Boot** are used to implement the service, **Spring Boot** provides very nice features regarding packaging and deployment, it packages the whole service as a runnable jar file with the support of its embedded tomcat server.
**JAX-RS** is used to expose the service endpoints as REST.
**MongoDB** is used to persist log messages, **Log4j** is used as the backbone logging framework.

## Technologies
| Technology    | Purpose |
| :------------ | :-----------: |
| Spring Boot   | Simplify the development, packaging and deployment of spring 						applications by providing fast and ready environment for 						development, Packages the complete solution as a runnable 						jar with an embedded tomcat server. http://projects.spring.io/spring-boot/ |
| JAX-RS   		| Default Java specification for implementing Restful 						services, I have used the default implementation which is 						jersey framework. https://jersey.java.net/|
| MongoDB       | A NOSQL scalable DB based on document data model which 						provides handy scalable data model that fits the need to 						adapt or change the model later as the requirements 						evolve, this perfectly fits the need to add more and more 						data to the logged messages. https://mongolab.com |
| Mongo Full text search | Deep search in the log messages: http://								docs.mongodb.org/manual/core/index-text/ Provides 								text indexes to support text search in the whole 								logged messages.|
| Spring Data      | Provides handy features for data mapping and manipulation 							backed by MongoDB. http://projects.spring.io/spring-							data-mongodb/|
| Log4j      | The backbone logging framework for the service, both internal 					logs and application/customer log messages are logged using 					it.|

## Summary of Functionality
Different logging and analysis services are exposed as REST services:

| Service    | URL | Parameters |
| :------------ | :-----------: | :-----------: |
| Application logging  | http://localhost:8080/log/app | ?applicationName=app1&moduleName=module1&level=info&message=log message&trace=[string array for stack]&logType=SYSTEM_INFO&threadNo=thread123|
| Customer/User logging  | http://localhost:8080/log/customer  | ?applicationName=app1&customerId=customer1&level=info&message=log message&trace=[string array for stack]&logType=VIEW_PRODUCT&productId=mobile&currentPage=home&searchTerm=phones|
All logs for a specific customer  | http://localhost:8080/statistics/allCustomerLogs   | ?customerId=|
 All products visited by a customer order by most visited.  | hhttp://localhost:8080/statistics/mostVisitedProducts  | ?customerId=customer1|
| Search all application logs  | http://localhost:8080/statistics/searchAppLogs  | List of texts to search for:?param1=XXX&param2=YYY|

Note: search services search in all indexed fields in the collection, meaning searching application logs for text “error” will use the mongo text indexes to search within (message, moduleName and applicationName) with the ability of giving high scores for matches in specific fields (ex: CustomerLog.searchTerm)

## Architecture and design

![service architecture](https://raw.githubusercontent.com/mohhmekk/sample-logging-microservice/master/docs/architecture.png)

![Sample sequence diagram](https://raw.githubusercontent.com/mohhmekk/sample-logging-microservice/master/docs/sequence-diagram.png)

## Run application

` mvn install`

` java -jar target/logging-service-1.0-SNAPSHOT.jar`

