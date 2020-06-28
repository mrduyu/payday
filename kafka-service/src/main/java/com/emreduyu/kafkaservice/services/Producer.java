package com.emreduyu.kafkaservice.services;

import com.emreduyu.kafkaservice.models.MailDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@EnableAutoConfiguration
public class Producer {

    private static final Logger LOGGER = LoggerFactory.getLogger(Producer.class);
    private static final String TOPIC = "Topic1";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String mailDTOAsJson) {
        LOGGER.info("INFO-LOG: Kafka produced message: " + mailDTOAsJson);
        this.kafkaTemplate.send(TOPIC, mailDTOAsJson);
    }
}
