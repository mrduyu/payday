package com.payday.kycinformationservice.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class KycInformation {
    @Id
    private String id;
    private String nationalId;
    private String emailAddress;
    private String address;
    private String lastEmployerName;
    private Date startDate;
    private String lastEmployer;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLastEmployerName() {
        return lastEmployerName;
    }

    public void setLastEmployerName(String lastEmployerName) {
        this.lastEmployerName = lastEmployerName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getLastEmployer() {
        return lastEmployer;
    }

    public void setLastEmployer(String lastEmployer) {
        this.lastEmployer = lastEmployer;
    }
}
