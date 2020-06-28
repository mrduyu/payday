package com.payday.incomeinformationservice.dal.implementations;

import com.payday.incomeinformationservice.dal.interfaces.IncomeInformationDAL;
import com.payday.incomeinformationservice.models.IncomeInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class IncomeInformationDALImpl implements IncomeInformationDAL {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public IncomeInformation getIncomeInformationByNationalId(String nationalId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("nationalId").is(nationalId));
        IncomeInformation incomeInformation = mongoTemplate.findOne(query, IncomeInformation.class);
        return incomeInformation;
    }

    @Override
    public IncomeInformation createIncomeInformation(IncomeInformation incomeInformation) {
        return mongoTemplate.save(incomeInformation);
    }
}
