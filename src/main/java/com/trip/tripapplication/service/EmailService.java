package com.trip.tripapplication.service;

import com.trip.tripapplication.domain.Mail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;

    public void send(final Mail mail){
        log.info("Prepering email");

        try{
            SimpleMailMessage mailMessage = createEmailMessage(mail);

            javaMailSender.send(mailMessage);
            log.info("Email has been sent to " + mail.getMailTo());
        } catch (MailException mailException){
            log.error("Failed to process email sending to: " + mail.getMailTo());
        }
    }

    private SimpleMailMessage createEmailMessage(final Mail mail) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());
        return mailMessage;
    }
}
