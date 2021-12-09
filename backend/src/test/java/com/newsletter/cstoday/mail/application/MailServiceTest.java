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
import org.springframework.mail.javamail.JavaMailSenderImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

@SpringBootTest
class MailServiceTest {

    @Autowired
    MailService mailService;

    @MockBean
    MailSender mailSender;

    @MockBean
    SlackService slackService;

    @BeforeEach
    void setUp() {
        doNothing().when(mailSender).sendMail(isA(Long.class), isA(String.class), isA(String.class), isA(String.class));
        doNothing().when(slackService).sendSlackMailMessage(isA(SlackMailEvent.class));
    }

    @DisplayName("웰컴 메일을 보내면, 슬랙에 웰컴 메일 관련 메시지가 간다.")
    @Test
    void sendWelcomeMail() {
        mailService.sendWelcomeMail(new WelcomeMailEvent(1L, "joel610@naver.com"));
        verify(mailSender, Mockito.timeout(1000).times(1)).sendMail(isA(Long.class), isA(String.class), isA(String.class), isA(String.class));
        verify(slackService, Mockito.timeout(1000).times(1)).sendSlackMailMessage(any());
    }

    @DisplayName("뉴스레터를 보내면, 슬랙에 뉴스레터 전송 메시지가 간다.")
    @Test
    void sendNewsLetter() {
        mailService.sendNewsLetter(new NewsLetterMailEvent(1L, "joel610@naver.com", "text"));
        verify(mailSender, times(1)).sendMail(isA(Long.class), isA(String.class), isA(String.class), isA(String.class));
        verify(slackService, times(1)).sendSlackMailMessage(any());
    }

    @DisplayName("MailSenderManager는 userId에 따라 다른 mailSenderImpl를 반환한다.")
    @Test
    void mailSenderManagerDifferentImpl() {
        final JavaMailSenderImpl mailSender1 = MailSenderManager.findMailSender(25L);
        assertThat(mailSender1.getUsername()).isEqualTo("test1");

        final JavaMailSenderImpl mailSender2 = MailSenderManager.findMailSender(100L);
        assertThat(mailSender2.getUsername()).isEqualTo("test2");

        final JavaMailSenderImpl mailSender3 = MailSenderManager.findMailSender(101L);
        assertThat(mailSender3.getUsername()).isEqualTo("test3");

        final JavaMailSenderImpl mailSender4 = MailSenderManager.findMailSender(176L);
        assertThat(mailSender4.getUsername()).isEqualTo("test4");

        final JavaMailSenderImpl mailSender5 = MailSenderManager.findMailSender(250L);
        assertThat(mailSender5.getUsername()).isEqualTo("test5");
    }

    @DisplayName("MailSenderManager가 지원할 수 없는 userId는 예외를 던진다.")
    @Test
    void mailSenderException() {
        assertThatThrownBy(() -> MailSenderManager.findMailSender(10000000000L))
                .isInstanceOf(IllegalStateException.class);
    }
}
