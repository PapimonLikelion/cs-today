package com.newsletter.cstoday.mail.application;

import com.newsletter.cstoday.mail.application.event.NewsLetterMailEvent;
import com.newsletter.cstoday.mail.application.event.WelcomeMailEvent;
import com.newsletter.cstoday.slack.application.event.SlackMailEvent;
import com.newsletter.cstoday.user.application.event.UserDeleteEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
@RequiredArgsConstructor
public class MailService {

    private final MailValidationService mailValidationService;
    private final MailSenderService mailSenderService;
    private final ApplicationEventPublisher eventPublisher;

    @Async
    @TransactionalEventListener
    public void sendWelcomeMail(WelcomeMailEvent welcomeMailEvent) {
        final boolean mailValid = mailValidationService.checkValidMail(welcomeMailEvent.getEmail());
        if (!mailValid) {
            eventPublisher.publishEvent(new UserDeleteEvent(welcomeMailEvent.getEmail()));
            return;
        }

        mailSenderService.sendWelcomeMail(welcomeMailEvent.getEmail());
        eventPublisher.publishEvent(SlackMailEvent.ofWelcome(welcomeMailEvent.getEmail()));
    }

    @EventListener
    public void sendNewsLetter(NewsLetterMailEvent newsLetterMailEvent) {
        mailSenderService.sendNewsLetter(newsLetterMailEvent.getEmail(), newsLetterMailEvent.getText());
        eventPublisher.publishEvent(SlackMailEvent.ofNewsLetter(newsLetterMailEvent.getUserId(), newsLetterMailEvent.getEmail()));
    }
}
