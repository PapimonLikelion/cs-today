package com.newsletter.cstoday.mail.application;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Properties;

public enum MailSenderManager {

    FIRST_SENDER(1L, 50L),
    SECOND_SENDER(51L, 100L),
    THIRD_SENDER(101L, 150L),
    FOURTH_SENDER(151L, 200L),
    FIFTH_SENDER(201L, 250L);

    MailSenderManager(Long userIdFrom, Long userIdTo) {
        this.userIdFrom = userIdFrom;
        this.userIdTo = userIdTo;
    }

    private final Long userIdFrom;
    private final Long userIdTo;
    private JavaMailSenderImpl mailSender;

    public static JavaMailSenderImpl findMailSender(Long userId) {
        final MailSenderManager mailSenderManager = Arrays.stream(values())
                .filter(manager -> (manager.userIdFrom <= userId && userId <= manager.userIdTo))
                .findAny()
                .orElseThrow(() -> new IllegalStateException("유저 ID에 대응되는 mailSender가 없습니다!"));
        return mailSenderManager.mailSender;
    }

    @Component
    private static class MailSenderInjector {

        @Value("${mail.user1}")
        private String user1;

        @Value("${mail.pw1}")
        private String pw1;

        @Value("${mail.user2}")
        private String user2;

        @Value("${mail.pw2}")
        private String pw2;

        @Value("${mail.user3}")
        private String user3;

        @Value("${mail.pw3}")
        private String pw3;

        @Value("${mail.user4}")
        private String user4;

        @Value("${mail.pw4}")
        private String pw4;

        @Value("${mail.user5}")
        private String user5;

        @Value("${mail.pw5}")
        private String pw5;

        @PostConstruct
        private void inject() {
            FIRST_SENDER.mailSender = generateMailSender(user1, pw1);
            SECOND_SENDER.mailSender = generateMailSender(user2, pw2);
            THIRD_SENDER.mailSender = generateMailSender(user3, pw3);
            FOURTH_SENDER.mailSender = generateMailSender(user4, pw4);
            FIFTH_SENDER.mailSender = generateMailSender(user5, pw5);
        }

        private JavaMailSenderImpl generateMailSender(String username, String password) {
            final JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
            mailSender.setUsername(username);
            mailSender.setPassword(password);
            mailSender.setHost("smtp.gmail.com");
            mailSender.setPort(587);
            mailSender.setProtocol("smtp");
            mailSender.setDefaultEncoding("UTF-8");

            final Properties properties = new Properties();
            properties.setProperty("mail.smtp.starttls.enable", "true");
            properties.setProperty("mail.smtp.auth", "true");
            properties.setProperty("mail.smtp.timeout", "5000");
            mailSender.setJavaMailProperties(properties);

            return mailSender;
        }
    }
}
