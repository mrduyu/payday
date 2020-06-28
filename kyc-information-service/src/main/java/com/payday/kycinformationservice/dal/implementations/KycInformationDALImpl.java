package com.payday.kycinformationservice.dal.implementations;

import com.payday.kycinformationservice.dal.interfaces.KycInformationDAL;
import com.payday.kycinformationservice.models.KycInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class KycInformationDALImpl implements KycInformationDAL {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public KycInformation getKycInformationByNationalId(String nationalId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("nationalId").is(nationalId));
        KycInformation kycInformation = mongoTemplate.findOne(query, KycInformation.class);
        return kycInformation;
    }

    @Override
    public KycInformation createKycInformation(KycInformation kycInformation) {
        return mongoTemplate.save(kycInformation);
    }
}

