package org.notification.module.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.SystemProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Date;
import java.util.Properties;

@Service
public class EmailAlertService {

    public void sendAlert(String email) {
        // send an email alert

        try {

            JavaMailSender javaMailSender = getJavaMailSender();
//            javaMailSender.setUsername(email);
//            javaMailSender.setPassword(password);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("nupur.singhal1992@gmail.com");
            message.setTo(email);
            message.setSubject("Hello! This is an alert notification for the logs");

            javaMailSender.send(message);

        } catch (Exception e) {
            System.out.println("Unable to send mail alert to + " + email);
        }

        System.out.println("mail sent successfully");
    }

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(465);

        mailSender.setUsername("nupur.singhal1992@gmail.com");
        mailSender.setPassword("********");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        return mailSender;
    }


}
