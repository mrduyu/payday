package com.emreduyu.kafkaservice.service;

import com.emreduyu.kafkaservice.models.MailDTO;
import com.emreduyu.kafkaservice.services.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class MailService {
    private final Logger LOGGER = LoggerFactory.getLogger(MailService.class);
    public String SendEmail(MailDTO mailDTO)
    {
        LOGGER.info(String.format("INFO-LOG: Hey! I received your message and consumed it. I redirected your request to mail service."));

        String sendEmailUrl = "http://mailservice:8104/sendmail";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<MailDTO> entity = new HttpEntity<MailDTO>(mailDTO, headers);

        // send request and parse result
        ResponseEntity<String> postResponse = restTemplate
                .exchange(sendEmailUrl, HttpMethod.POST, entity, String.class);
        return postResponse.getBody();
    }
}
