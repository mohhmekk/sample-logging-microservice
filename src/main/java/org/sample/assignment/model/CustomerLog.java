package org.sample.assignment.model;

import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Represents Customer log messages.
 * <p/>
 * Created by Mohamed Mekkawy on 17/03/2015.
 */
@Document
public class CustomerLog extends LogMessage {

    @TextIndexed
    private String customerId;

    private CustomerLogType customerLogType;

    @TextIndexed
    private String productId;

    @TextIndexed(weight = 2)
    private String searchTerm;

    @TextIndexed
    private String currentPage;

    public CustomerLog() {
    }

    public CustomerLog(String appName, String customerId, String message, String productId, String searchTerm, String currentPage, String logType) {
        this.setApplicationName(appName);
        this.setCustomerId(customerId);
        this.setMessage(message);
        this.setProductId(productId);
        this.setSearchTerm(searchTerm);
        this.setCurrentPage(currentPage);
        if (logType != null && !logType.equals("")) {
            this.setCustomerLogType(CustomerLogType.valueOf(logType));
        } else {
            this.setCustomerLogType(CustomerLogType.GENERIC);
        }
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public CustomerLogType getCustomerLogType() {
        return customerLogType;
    }

    public void setCustomerLogType(CustomerLogType customerLogType) {
        this.customerLogType = customerLogType;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    @Override
    public String toString() {
        return "CustomerLog{" +
                "customerId='" + customerId + '\'' +
                ", customerLogType=" + customerLogType +
                ", productId='" + productId + '\'' +
                ", searchTerm='" + searchTerm + '\'' +
                ", currentPage='" + currentPage + '\'' +
                "} " + super.toString();
    }
}
