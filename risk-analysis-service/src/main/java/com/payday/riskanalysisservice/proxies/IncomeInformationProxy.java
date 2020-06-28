package com.payday.riskanalysisservice.proxies;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.function.DoubleBinaryOperator;

public class IncomeInformationProxy {
    private String GetCustomerIncomeInformation(String nationalId)
    {
        String baseUrl = "http://incomeinformationservice:8102/incomeinformation/"+nationalId;
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
    public Double getCustomerMonthlyIncome(String nationalId){
        String incomeInformationJson = GetCustomerIncomeInformation(nationalId);
        JSONObject incomeInformationObject = new JSONObject(incomeInformationJson);
        double monthlyIncome = incomeInformationObject.getInt("monthlyIncome");
        return monthlyIncome;
    }

    private static HttpEntity<?> getHeaders() throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        return new HttpEntity<>(headers);
    }
}
