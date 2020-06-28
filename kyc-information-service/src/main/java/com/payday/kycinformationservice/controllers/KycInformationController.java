package com.payday.kycinformationservice.controllers;

import com.payday.kycinformationservice.dal.interfaces.KycInformationDAL;
import com.payday.kycinformationservice.dal.repositories.KycInformationRepository;
import com.payday.kycinformationservice.models.KycInformation;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "")
public class KycInformationController {

    private final KycInformationDAL kycInformationDAL;
    private final KycInformationRepository kycInformationRepository;

    public KycInformationController(KycInformationDAL kycInformationDAL, KycInformationRepository kycInformationRepository) {
        this.kycInformationDAL = kycInformationDAL;
        this.kycInformationRepository = kycInformationRepository;
    }

    @Cacheable("kycinformation")
    @RequestMapping(value = "/kycinformation/{nationalId}", method = RequestMethod.GET)
    public KycInformation getKycInformationByNationalId(@PathVariable String nationalId)
    {
        KycInformation kycInformation = kycInformationDAL.getKycInformationByNationalId(nationalId);
        if (kycInformation != null)
        {
            return kycInformation;
        }
        else {
            return null;
        }
    }

    @RequestMapping(value = "/kycinformation/create", method = RequestMethod.POST)
    public KycInformation saveKycInformation(@RequestBody KycInformation kycInformation) {
        return kycInformationDAL.createKycInformation(kycInformation);
    }
}

