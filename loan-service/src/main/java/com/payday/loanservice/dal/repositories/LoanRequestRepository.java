package com.payday.loanservice.dal.repositories;

import com.payday.loanservice.models.LoanRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRequestRepository extends MongoRepository<LoanRequest,String> {
}
