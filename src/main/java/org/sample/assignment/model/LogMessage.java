package org.sample.assignment.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.TextScore;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * Super Type for log messages, messages are stored in a NOSQL DB (mongodb).
 *
 * @author Mohamed Mekkawy
 */
public abstract class LogMessage {

    @Id
    private String id;

    @TextIndexed
    private String message;

    private Date time;

    private String level;

    @TextIndexed
    private String applicationName;

    private String loggerName;

    private List<String> traceback;

    @TextScore
    private Float score;

    public List<String> getTraceback() {
        return traceback;
    }

    public void setTraceback(List<String> traceback) {
        this.traceback = traceback;
    }

    public String getLoggerName() {
        return loggerName;
    }

    public void setLoggerName(String loggerName) {
        this.loggerName = loggerName;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Float getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "LogMessage{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", time=" + time +
                ", level='" + level + '\'' +
                ", applicationName='" + applicationName + '\'' +
                ", loggerName='" + loggerName + '\'' +
                ", traceback=" + traceback +
                ", score=" + score +
                '}';
    }
}
