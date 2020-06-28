package com.payday.mailservice.service;

import com.payday.mailservice.models.MailDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

public class MailService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MailService.class);
    public void sendmail(MailDTO mailDTO) throws AddressException, MessagingException, IOException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("paydaybank1234@gmail.com", "payday1234");
            }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("paydaybank1234@gmail.com", false));

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailDTO.getEmailAddress()));
        msg.setSubject("Your loan request result:");
        msg.setContent("Tutorials point email", "text/html");
        msg.setSentDate(new Date());

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(mailDTO.getMessage(), "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        msg.setContent(multipart);
        Transport.send(msg);
        LOGGER.info("INFO-LOG: Mail service sent an email to: " + mailDTO.getEmailAddress());
    }
}
