package com.payday.riskanalysisservice.models;

import java.util.Date;

public class IndividualLoanRequest {
    private String loanId;
    private Double requestedAmount;
    private Boolean isAnalyzed = false;
    private Boolean isGranted = false;
    private Date updatedDate = new Date();
    private String updatedUser = "RiskEngine";

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public Double getRequestedAmount() {
        return requestedAmount;
    }

    public void setRequestedAmount(Double requestedAmount) {
        this.requestedAmount = requestedAmount;
    }

    public Boolean getIsAnalyzed() {
        return isAnalyzed;
    }

    public void setIsAnalyzed(Boolean analyzed) {
        this.isAnalyzed = isAnalyzed;
    }

    public Boolean getGranted() {
        return isGranted;
    }

    public void setGranted(Boolean granted) {
        isGranted = granted;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public String getUpdatedUser() {
        return updatedUser;
    }
}
