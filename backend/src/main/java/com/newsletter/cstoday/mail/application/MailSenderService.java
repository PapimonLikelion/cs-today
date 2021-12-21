package com.newsletter.cstoday.mail.application;

import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;

@Service
public class MailSenderService {

    private static final String WELCOME_MAIL_SUBJECT = "오늘의 CS를 구독해주셔서 감사합니다 🎁";
    private static final String NEWSLETTER_SUBJECT = "오늘의 CS 뉴스레터입니다 🎁";
    private static final String SERVER_MAIL_NAME = "오늘의 CS";
    private static final String SERVER_MAIL_ADDRESS = "cstoday@cstoday.me";

    private Mailer mailer;
    private File dkimFile;

    @Value("${setting.dkim}")
    private String dkimFilePath;

    @Value("${spring.mail.host}")
    private String mailHost;

    @Value("${spring.mail.port}")
    private Integer mailPort;

    @Value("${spring.mail.username}")
    private String mailUsername;

    @Value("${spring.mail.password}")
    private String mailPassword;

    @PostConstruct
    public void init() {
         mailer = MailerBuilder
                .withSMTPServer(mailHost, mailPort, mailUsername, mailPassword)
                .withTransportStrategy(TransportStrategy.SMTP_TLS)
                .withProperty("mail.smtp.auth", true)
                .withProperty("mail.smtp.timeout", 5000)
                .withProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory")
                .withProperty("mail.smtp.starttls.enable", true)
                .buildMailer();

         dkimFile = new File(dkimFilePath);
    }

    public void sendWelcomeMail(String destination) {
        final Email email = generateEmail(destination, WELCOME_MAIL_SUBJECT, WelcomeMail.welcomeMailContent);
        mailer.sendMail(email);
    }

    public void sendNewsLetter(String destination, String content) {
        final Email email = generateEmail(destination, NEWSLETTER_SUBJECT, content);
        mailer.sendMail(email);
    }

    private Email generateEmail(String destination, String subject, String content) {
        return EmailBuilder.startingBlank()
                .to(destination)
                .from(SERVER_MAIL_NAME, SERVER_MAIL_ADDRESS)
                .withSubject(subject)
                .withHTMLText(content)
                .signWithDomainKey(dkimFile, "cstoday.me", "mail")
                .buildEmail();
    }
}
