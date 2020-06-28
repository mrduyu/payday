package com.payday.riskanalysisservice.models;

import java.util.Date;
import java.util.List;

public class LoanRequest {
    private String nationalId;
    private List<IndividualLoanRequest> individualLoanRequests;

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public List<IndividualLoanRequest> getIndividualLoanRequests() {
        return individualLoanRequests;
    }

    public void setIndividualLoanRequests(List<IndividualLoanRequest> individualLoanRequests) {
        this.individualLoanRequests = individualLoanRequests;
    }
}
