package com.payday.riskanalysisservice.proxies;

import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class KycInformationProxy {
    private String GetCustomerKYCInformation(String nationalId)
    {
        String baseUrl = "http://kycinformationservice:8101/kycinformation/"+nationalId;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = null;
        try {
            response = restTemplate.exchange(baseUrl,
                    HttpMethod.GET, getHeaders(), String.class);
        } catch (Exception ex) {
            return ex.toString();
        }
        System.out.println(response.getBody());
        return response.getBody();
    }

    public String getKycInformation(String nationalId) throws ParseException {
        String kycInformation = GetCustomerKYCInformation(nationalId);
        return kycInformation;
    }

    private static HttpEntity<?> getHeaders() throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        return new HttpEntity<>(headers);
    }
}
