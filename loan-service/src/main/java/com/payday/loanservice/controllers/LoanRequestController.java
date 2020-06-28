package com.payday.loanservice.controllers;

import com.payday.loanservice.dal.interfaces.LoanRequestDAL;
import com.payday.loanservice.dal.repositories.LoanRequestRepository;
import com.payday.loanservice.models.LoanRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "")
public class LoanRequestController {

    private final LoanRequestDAL loanRequestDAL;
    private final LoanRequestRepository loanRequestRepository;

    public LoanRequestController(LoanRequestDAL loanRequestDAL, LoanRequestRepository loanRequestRepository) {
        this.loanRequestDAL = loanRequestDAL;
        this.loanRequestRepository = loanRequestRepository;
    }

    @RequestMapping(value = "/loan/create", method = RequestMethod.POST)
    public LoanRequest saveLoanRequest(@RequestBody LoanRequest loan) {
        return loanRequestDAL.createLoanRequest(loan);
    }

    @RequestMapping(value = "loans", method = RequestMethod.POST)
    public List<LoanRequest> getLoanRequestsByNationalId(@RequestBody String nationalId) {
        List<LoanRequest> loanList = loanRequestDAL.getLoanRequestsByNationalId(nationalId);
        return loanList;
    }

    @RequestMapping(value = "/loans/{loanId}", method = RequestMethod.GET)
    public LoanRequest getLoanRequestByLoanId(@PathVariable String loanId) throws InterruptedException {
        LoanRequest loanRequest = loanRequestDAL.getLoanRequestByLoanId(loanId);
        return loanRequest;
    }

    @RequestMapping(value = "/loan/update", method = RequestMethod.POST)
    public LoanRequest updateLoanRequest(@RequestBody LoanRequest loan) {
        LoanRequest updatedLoan = loanRequestDAL.updateLoanRequestByLoanId(loan);
        return updatedLoan;
    }

    @RequestMapping(value = "/unanalyzedloans", method = RequestMethod.GET)
    public List<LoanRequest> getUnAnalyzedLoans() {
        List<LoanRequest> unAnalyzedLoanRequests = loanRequestDAL.getUnAnalyzedLoanRequests();
        return unAnalyzedLoanRequests;
    }
}

