package com.newsletter.cstoday.mail.application;

import com.newsletter.cstoday.mail.application.event.NewsLetterMailEvent;
import com.newsletter.cstoday.mail.application.event.WelcomeMailEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class MailService {

    private static final String WELCOME_MAIL_SUBJECT = "오늘의 CS를 구독해주셔서 감사합니다 🎁";
    private static final String NEWSLETTER_SUBJECT = "오늘의 CS 뉴스레터입니다 🎁";

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String serverMailAddress;

    @Async
    @TransactionalEventListener
    public void sendWelcomeMail(WelcomeMailEvent welcomeMailEvent) {
        final MimeMessage mimeMessage = createMimeMessage(welcomeMailEvent.getEmail(), WELCOME_MAIL_SUBJECT, WelcomeMail.welcomeMailContent);
        mailSender.send(mimeMessage);
    }
    
    @EventListener
    public void sendNewsLetter(NewsLetterMailEvent newsLetterMailEvent) {
        final MimeMessage mimeMessage = createMimeMessage(newsLetterMailEvent.getEmail(), NEWSLETTER_SUBJECT, newsLetterMailEvent.getText());
        mailSender.send(mimeMessage);
    }

    private MimeMessage createMimeMessage(String destination, String subject, String text) {
        try {
            final MimeMessage mimeMessage = mailSender.createMimeMessage();
            final MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            mimeMessageHelper.setFrom(serverMailAddress);
            mimeMessageHelper.setTo(destination);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(text, true);
            return mimeMessage;
        } catch (MessagingException e) {
            throw new IllegalStateException("MimeMessage 메일을 만드는 과정에서 오류가 발생했습니다.");
        }
    }
}
