package com.payday.loanservice.dal.interfaces;

import com.payday.loanservice.models.LoanRequest;

import java.util.List;

public interface LoanRequestDAL {
    LoanRequest createLoanRequest(LoanRequest loanRequest);
    List<LoanRequest> getLoanRequestsByNationalId(String nationalId);
    List<LoanRequest> getUnAnalyzedLoanRequests();
    LoanRequest getLoanRequestByLoanId(String loanId);
    LoanRequest updateLoanRequestByLoanId(LoanRequest updatedLoanRequest);
}
