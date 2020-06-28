package com.emreduyu.kafkaservice.controllers;

import com.emreduyu.kafkaservice.models.MailDTO;
import com.emreduyu.kafkaservice.services.Producer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "")
public class KafkaController {
    private final Producer producer;
    @Autowired
    public KafkaController(Producer producer) {
        this.producer = producer;
    }
    @RequestMapping(value = "/hello/{message}", method = RequestMethod.GET)
    public String greetings(@PathVariable String message){
        return "Hello Emre. You have sent me a message to test my endpoint. Message: "+message+"";
    }
    @RequestMapping(value = "/message/{message}", method = RequestMethod.GET)
    public String sendMessageToKafkaTopic(@PathVariable String message){
        this.producer.sendMessage(message);
        return "Hello. I have sent your message '"+message+"' to Kafka.";
    }
    @RequestMapping(value = "/sendmessage", method = RequestMethod.POST)
    public void sendMessage(@RequestBody String mailDTO) {
        this.producer.sendMessage(mailDTO);
    }
}
