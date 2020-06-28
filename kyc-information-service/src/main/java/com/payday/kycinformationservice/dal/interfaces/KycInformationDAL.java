package com.payday.kycinformationservice.dal.interfaces;

import com.payday.kycinformationservice.models.KycInformation;

public interface KycInformationDAL {
    KycInformation getKycInformationByNationalId(String nationalId);
    KycInformation createKycInformation(KycInformation kycInformation);
}