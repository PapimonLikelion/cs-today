package com.newsletter.cstoday.mail.application;

import com.newsletter.cstoday.mail.application.event.WelcomeMailEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String serverMailAddress;

    @Async
    @TransactionalEventListener
    public void sendWelcomeMail(WelcomeMailEvent welcomeMailEvent) {
        try {
            final MimeMessage mimeMessage = mailSender.createMimeMessage();
            final MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            mimeMessageHelper.setFrom(serverMailAddress);
            mimeMessageHelper.setTo(welcomeMailEvent.getEmail());
            mimeMessageHelper.setSubject("오늘의 CS를 구독해주셔서 감사합니다 🎁");
            mimeMessageHelper.setText(WelcomeMail.welcomeMailContent, true);
            mailSender.send(mimeMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
