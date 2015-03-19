package org.sample.assignment.log;


import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;
import org.sample.assignment.model.ApplicationLog;
import org.sample.assignment.model.CustomerLog;
import org.sample.assignment.service.ApplicationLogService;
import org.sample.assignment.service.CustomerLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sample.assignment.model.LogMessage;
import java.util.Arrays;
import java.util.Calendar;

/**
 * Log4j appender writes log entries into a MongoDB instance.
 * This is the main and only appender for the logging service.
 *
 * @author Mohamed Mekkawy
 */
@Component
public class MongoLog4jAppender extends AppenderSkeleton {


    @Autowired
    private ApplicationLogService applicationLogService;

    @Autowired
    private CustomerLogService customerLogService;

    /*
     * (non-Javadoc)
     * @see org.apache.log4j.AppenderSkeleton#append(org.apache.log4j.spi.LoggingEvent)
     */
    @Override
    protected void append(final LoggingEvent event) {

        Object message = event.getMessage();

        if (!(message instanceof LogMessage)) {
            throw new IllegalArgumentException("Expecting log messages of type {" + LogMessage.class.getSimpleName() + "}, the passed message is of type {" + message.getClass().getSimpleName() + "}");
        }

        LogMessage logMessage = (LogMessage) message;

        logMessage.setLevel(event.getLevel().toString());
        logMessage.setLoggerName(event.getLoggerName());

        //set current time.
        Calendar tstamp = Calendar.getInstance();
        tstamp.setTimeInMillis(event.getTimeStamp());
        logMessage.setTime(tstamp.getTime());

        // Copy traceback info (if there is any) into the document
        String[] traceback = event.getThrowableStrRep();
        if (null != traceback && traceback.length > 0) {
            logMessage.setTraceback(Arrays.asList(traceback));
        }


        if (logMessage instanceof ApplicationLog) {
            applicationLogService.saveApplicationLog((ApplicationLog) logMessage);
        } else if (logMessage instanceof CustomerLog) {
            customerLogService.saveCustomerLog((CustomerLog) logMessage);
        }
    }

    @Override
    public void close() {
        //Nothing needed to be done here.
    }

    /*
         * (non-Javadoc)
         * @see org.apache.log4j.AppenderSkeleton#requiresLayout()
         */
    public boolean requiresLayout() {
        return false;
    }
}