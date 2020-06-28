package com.payday.incomeinformationservice.dal.interfaces;

import com.payday.incomeinformationservice.models.IncomeInformation;

public interface IncomeInformationDAL {
    IncomeInformation getIncomeInformationByNationalId(String nationalId);
    IncomeInformation createIncomeInformation(IncomeInformation incomeInformation);
}

