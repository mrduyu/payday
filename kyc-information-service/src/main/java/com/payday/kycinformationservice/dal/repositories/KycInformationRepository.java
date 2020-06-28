package com.payday.kycinformationservice.dal.repositories;

import com.payday.kycinformationservice.models.KycInformation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KycInformationRepository extends MongoRepository<KycInformation,String> {
}

