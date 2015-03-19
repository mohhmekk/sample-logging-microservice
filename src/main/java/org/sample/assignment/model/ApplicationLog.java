package org.sample.assignment.model;

import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Represents Application log messages.
 * <p/>
 * Created by Mohamed Mekkawy on 17/03/2015.
 */
@Document
public class ApplicationLog extends LogMessage {

    private ApplicationLogType applicationLogType;

    private String threadNo;

    @TextIndexed
    private String moduleName;

    public ApplicationLog() {
    }

    public ApplicationLog(String appName, String moduleName, String threadNo, String message, String logType) {
        this.setApplicationName(appName);
        this.setModuleName(moduleName);
        this.setThreadNo(threadNo);
        this.setMessage(message);
        if (logType != null && !logType.equals("")) {
            this.setApplicationLogType(ApplicationLogType.valueOf(logType));
        } else {
            this.setApplicationLogType(ApplicationLogType.GENERIC);
        }
    }

    public ApplicationLogType getApplicationLogType() {
        return applicationLogType;
    }

    public void setApplicationLogType(ApplicationLogType applicationLogType) {
        this.applicationLogType = applicationLogType;
    }

    public String getThreadNo() {
        return threadNo;
    }

    public void setThreadNo(String threadNo) {
        this.threadNo = threadNo;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    @Override
    public String toString() {
        return "ApplicationLog{" +
                "applicationLogType=" + applicationLogType +
                ", threadNo='" + threadNo + '\'' +
                ", moduleName='" + moduleName + '\'' +
                "} " + super.toString();
    }
}
