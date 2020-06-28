package com.payday.riskanalysisservice.proxies;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

public class KafkaProxy {
    public String sendMessageToKafka(String message, String emailAddress){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        String baseUrl = "http://kafkaservice:8105/sendmessage";
        //build the request
        JSONObject messageObject = new JSONObject();
        messageObject.put("message", message);
        messageObject.put("emailAddress", emailAddress);
        String request = messageObject.toString();
        HttpEntity<String> entity = new HttpEntity<String>(request, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(baseUrl, entity, String.class);
        System.out.println(response.getBody());
        return response.getBody();
    }
}
