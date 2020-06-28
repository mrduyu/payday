package com.payday.riskanalysisservice.model;

public class KafkaMessage {
    private String message;
    private String emailAddress;

    public KafkaMessage(String message, String emailAddress) {
        this.message = message;
        this.emailAddress = emailAddress;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
