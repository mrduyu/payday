package com.payday.incomeinformationservice.dal.repositories;

import com.payday.incomeinformationservice.models.IncomeInformation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeInformationRepository extends MongoRepository<IncomeInformation,String> {
}
