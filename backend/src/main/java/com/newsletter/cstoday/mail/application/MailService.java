package com.newsletter.cstoday.mail.application;

import com.newsletter.cstoday.mail.application.event.NewsLetterMailEvent;
import com.newsletter.cstoday.mail.application.event.WelcomeMailEvent;
import com.newsletter.cstoday.slack.application.event.SlackMailEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
@RequiredArgsConstructor
public class MailService {

    private static final String WELCOME_MAIL_SUBJECT = "오늘의 CS를 구독해주셔서 감사합니다 🎁";
    private static final String NEWSLETTER_SUBJECT = "오늘의 CS 뉴스레터입니다 🎁";

    private final ApplicationEventPublisher eventPublisher;
    private final MailSender mailSender;

    @Async
    @TransactionalEventListener
    public void sendWelcomeMail(WelcomeMailEvent welcomeMailEvent) {
        mailSender.sendMail(welcomeMailEvent.getUserId(), welcomeMailEvent.getEmail(), WELCOME_MAIL_SUBJECT, WelcomeMail.welcomeMailContent);
        eventPublisher.publishEvent(SlackMailEvent.ofWelcome(welcomeMailEvent.getEmail()));
    }

    @EventListener
    public void sendNewsLetter(NewsLetterMailEvent newsLetterMailEvent) {
        mailSender.sendMail(newsLetterMailEvent.getUserId(), newsLetterMailEvent.getEmail(), NEWSLETTER_SUBJECT, newsLetterMailEvent.getText());
        eventPublisher.publishEvent(SlackMailEvent.ofNewsLetter(newsLetterMailEvent.getEmail()));
    }
}
