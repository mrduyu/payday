package com.payday.riskanalysisservice.proxies;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.payday.riskanalysisservice.models.IndividualLoanRequest;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import javax.management.OperationsException;
import java.io.IOException;
import java.util.*;

public class LoanProxy {
    private String GetPreviousLoansByNationalId(String nationalId) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        String baseUrl = "http://loanservice:8100/loans";
        //build the request
        HttpEntity<String> entity = new HttpEntity<>(nationalId, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(baseUrl, entity, String.class);
        System.out.println(response.getBody());
        return response.getBody();
    }
    private void UpdateLoanRequest(String loanId, Boolean isGranted) throws OperationsException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        String baseUrl = "http://loanservice:8100/loan/update";
        JSONObject loanJsonObject = new JSONObject();
        loanJsonObject.put("id", loanId);
        loanJsonObject.put("isGranted", isGranted);
        loanJsonObject.put("isAppliedRiskAnalysis", true);
        loanJsonObject.put("updatedUser", "RiskAnalysisEngine");
        String JSonRequest = loanJsonObject.toString();
        HttpEntity<String> entity = new HttpEntity<>(JSonRequest, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(baseUrl, entity, String.class);
        if(response.getStatusCode() != HttpStatus.OK)
            throw new OperationsException(response.getBody());
    }
    private String GetUnAnalyzedLoanFromLoanService() {
        String baseUrl = "http://loanservice:8100/unanalyzedloans";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(baseUrl, String.class);
        System.out.println(response.getBody());
        return response.getBody();
    }

    public HashMap<String, List<IndividualLoanRequest>> GetUnAnalyzedLoans(){
        String allUnAnalyzedLoans = GetUnAnalyzedLoanFromLoanService();

        if(allUnAnalyzedLoans == null)
            return null;

        JSONArray loanArr = new JSONArray(allUnAnalyzedLoans);
        HashMap<String, List<IndividualLoanRequest>> nationalIdLoanRequestsDictionary = new HashMap<String, List<IndividualLoanRequest>>();
        for (int i = 0; i < loanArr.length(); i++) {

            IndividualLoanRequest individualLoanRequest = new IndividualLoanRequest();

            String nationalId = loanArr.getJSONObject(i).getString("nationalId");
            String loanId = loanArr.getJSONObject(i).getString("id");
            double requestedAmount = loanArr.getJSONObject(i).getDouble("amount");

            individualLoanRequest.setRequestedAmount(requestedAmount);
            individualLoanRequest.setLoanId(loanId);
            if(nationalIdLoanRequestsDictionary.containsKey(nationalId))
            {
                List<IndividualLoanRequest> currentIndRequests = nationalIdLoanRequestsDictionary.get(nationalId);
                currentIndRequests.add(individualLoanRequest);
                nationalIdLoanRequestsDictionary.put(nationalId,currentIndRequests);
            }
            else
            {
                List<IndividualLoanRequest> individualLoanRequestList = new ArrayList<IndividualLoanRequest>();
                individualLoanRequestList.add(individualLoanRequest);
                nationalIdLoanRequestsDictionary.put(nationalId,individualLoanRequestList);
            }
        }
        return  nationalIdLoanRequestsDictionary;
    }

    public void updateLoanRequestAfterAnalysis(String loanId, boolean isGranted) throws OperationsException {
        UpdateLoanRequest(loanId,isGranted);
    }

    public Double GetPreviousGrantedLoanTotalAmountsByNationalId(String nationalId){
        String allLoansOfSpecificCustomer = GetPreviousLoansByNationalId(nationalId);
        JSONArray loanArr = new JSONArray(allLoansOfSpecificCustomer);
        double totalGrantedLoan = 0.0;
        for (int i = 0; i < loanArr.length(); i++)
        {
            double requestedAmount = loanArr.getJSONObject(i).getDouble("amount");
            boolean isGranted = loanArr.getJSONObject(i).getBoolean("isGranted");

            if(isGranted)
            {
                totalGrantedLoan = totalGrantedLoan + requestedAmount;
            }
        }
        return  totalGrantedLoan;
    }

    private Double getRequestedLoanAmount(String loanInformation, String requestedLoanId) {
        JSONArray loanArr = new JSONArray(loanInformation);
        List<Double> grantedLoanList = new ArrayList<Double>();
        for (int i = 0; i < loanArr.length(); i++) {
            if (loanArr.getJSONObject(i).getString("id").equalsIgnoreCase(requestedLoanId)) {
                return loanArr.getJSONObject(i).getDouble("amount");
            }
        }
        return 0.0;
    }

    /*
    public List<String> getGrantedLoans(String jsonArrayStr, String key) {
        JSONArray jsonArray = new JSONArray(jsonArrayStr);
        return IntStream.range(0, jsonArray.length())
                .mapToObj(index -> ((JSONObject)jsonArray.get(index)).optString(key))
                .collect(Collectors.toList());
    }
    */
    private static HttpEntity<?> getHeaders() throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        return new HttpEntity<>(headers);
    }
}
