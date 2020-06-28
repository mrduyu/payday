package com.payday.mailservice.controller;


import com.payday.mailservice.models.MailDTO;
import com.payday.mailservice.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;

@RestController
@RequestMapping("")
public class EmailController {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailController.class);

    @RequestMapping(value = "/sendmail", method = RequestMethod.POST)
    public String sendMessage(@RequestBody MailDTO mailDTO) throws IOException, MessagingException {
        new MailService().sendmail(mailDTO);
        return "Mail sent.";
    }
}