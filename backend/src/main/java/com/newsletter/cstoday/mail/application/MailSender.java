package com.newsletter.cstoday.mail.application;

import org.springframework.stereotype.Component;

@Component
public interface MailSender {
    void sendMail(Long userId, String destination, String subject, String text);
}
