package com.payday.loanservice.dal.implementations;

import com.payday.loanservice.dal.interfaces.LoanRequestDAL;
import com.payday.loanservice.models.LoanRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class LoanRequestDALImpl implements LoanRequestDAL {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public LoanRequest createLoanRequest(LoanRequest loanRequest) {
        return mongoTemplate.save(loanRequest);
    }

    @Override
    public List<LoanRequest> getLoanRequestsByNationalId(String nationalId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("nationalId").is(nationalId));
        List<LoanRequest> loanRequestList = mongoTemplate.find(query, LoanRequest.class);
        return loanRequestList;
    }

    @Override
    public List<LoanRequest> getUnAnalyzedLoanRequests() {
        Query query = new Query();
        query.addCriteria(Criteria.where("isAppliedRiskAnalysis").is(false));
        List<LoanRequest> loanRequestList = mongoTemplate.find(query, LoanRequest.class);
        return loanRequestList;
    }

    @Override
    public LoanRequest getLoanRequestByLoanId(String loanId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(loanId));
        return mongoTemplate.findOne(query, LoanRequest.class);
    }

    @Override
    public LoanRequest updateLoanRequestByLoanId(LoanRequest updatedLoanRequest) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(updatedLoanRequest.getId()));

        LoanRequest loanRequest = mongoTemplate.findOne(query, LoanRequest.class);

        loanRequest.setIsGranted(updatedLoanRequest.getIsGranted());
        loanRequest.setUpdatedDate(new Date());
        loanRequest.setUpdatedUser(updatedLoanRequest.getUpdatedUser());
        loanRequest.setIsAppliedRiskAnalysis(updatedLoanRequest.getIsAppliedRiskAnalysis());
        mongoTemplate.save(loanRequest);

        LoanRequest finalLoanRequest = mongoTemplate.findOne(query, LoanRequest.class);

        return finalLoanRequest;
    }
}
