package com.newsletter.cstoday.mail.application;

import com.newsletter.cstoday.mail.application.event.NewsLetterMailEvent;
import com.newsletter.cstoday.mail.application.event.WelcomeMailEvent;
import com.newsletter.cstoday.slack.application.SlackService;
import com.newsletter.cstoday.slack.application.event.SlackMailEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

@SpringBootTest
class MailServiceTest {

    @Autowired
    MailService mailService;

    @MockBean
    JavaMailSender mailSender;

    @MockBean
    SlackService slackService;

    MimeMessage mockMimeMessage = new MimeMessage(Session.getInstance(new Properties()));

    @BeforeEach
    void setUp() {
        doNothing().when(mailSender).send(isA(MimeMessage.class));
        doNothing().when(slackService).sendSlackMailMessage(isA(SlackMailEvent.class));
        when(mailSender.createMimeMessage()).thenReturn(mockMimeMessage);
    }

    @DisplayName("웰컴 메일을 보내면, 슬랙에 웰컴 메일 관련 메시지가 간다.")
    @Test
    void sendWelcomeMail() {
        mailService.sendWelcomeMail(new WelcomeMailEvent(1L, "joel610@naver.com"));
        verify(mailSender, Mockito.timeout(1000).times(1)).send(isA(MimeMessage.class));
        verify(slackService, Mockito.timeout(1000).times(1)).sendSlackMailMessage(any());
    }

    @DisplayName("뉴스레터를 보내면, 슬랙에 뉴스레터 전송 메시지가 간다.")
    @Test
    void sendNewsLetter() {
        mailService.sendNewsLetter(new NewsLetterMailEvent(1L, "joel610@naver.com", "text"));
        verify(mailSender, times(1)).send(isA(MimeMessage.class));
        verify(slackService, times(1)).sendSlackMailMessage(any());
    }
}
