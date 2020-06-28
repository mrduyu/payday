package com.emreduyu.kafkaservice.services;

import com.emreduyu.kafkaservice.models.MailDTO;
import com.emreduyu.kafkaservice.service.MailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
public class Consumer {

    private final Logger LOGGER = LoggerFactory.getLogger(Producer.class);
    private static final String TOPIC = "Topic1";

    @KafkaListener(topics = TOPIC, groupId = "group_id")
    public String consume(String mailDTOAsJson) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        LOGGER.info("INFO-LOG: Kafka consumed message: " + mailDTOAsJson);
        try
        {
            MailDTO mail = objectMapper.readValue(mailDTOAsJson, MailDTO.class);
            String result = new MailService().SendEmail(mail);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return "FAIL";
        }
    }

    private static HttpEntity<?> getHeaders() throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        return new HttpEntity<>(headers);
    }
}
