package com.newsletter.cstoday.mail.application;

import com.newsletter.cstoday.mail.application.event.NewsLetterMailEvent;
import com.newsletter.cstoday.mail.application.event.WelcomeMailEvent;
import com.newsletter.cstoday.slack.application.SlackService;
import com.newsletter.cstoday.slack.application.event.SlackMailEvent;
import com.newsletter.cstoday.user.domain.User;
import com.newsletter.cstoday.user.domain.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
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
import java.util.Optional;
import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

@SpringBootTest
class MailServiceTest {

    @Autowired
    MailService mailService;

    @Autowired
    UserRepository userRepository;

    @MockBean
    JavaMailSender mailSender;

    @MockBean
    SlackService slackService;

    @MockBean
    MailValidationService mailValidationService;

    MimeMessage mockMimeMessage = new MimeMessage(Session.getInstance(new Properties()));

    String validEmail = "joel610@naver.com";
    String invalidEmail = "invalid@invalid.com";

    @BeforeEach
    void setUp() {
        userRepository.save(new User(validEmail, 7));
        userRepository.save(new User(invalidEmail, 7));

        doNothing().when(mailSender).send(isA(MimeMessage.class));
        doNothing().when(slackService).sendSlackMailMessage(isA(SlackMailEvent.class));

        when(mailSender.createMimeMessage()).thenReturn(mockMimeMessage);
        when(mailValidationService.checkValidMail(validEmail)).thenReturn(true);
        when(mailValidationService.checkValidMail(invalidEmail)).thenReturn(false);
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @DisplayName("유효한 이메일이라면 웰컴 메일을 보내고, 슬랙에 웰컴 메일 관련 메시지가 간다.")
    @Test
    void sendWelcomeMail() {
        mailService.sendWelcomeMail(new WelcomeMailEvent(1L, validEmail));
        verify(mailSender, Mockito.timeout(1000).times(1)).send(isA(MimeMessage.class));
        verify(slackService, Mockito.timeout(1000).times(1)).sendSlackMailMessage(any());
    }

    @DisplayName("유효하지 않은 이메일이라면, 메일/슬랙을 보내지 않고, 유저 데이터가 삭제된다")
    @Test
    void dontSendInvalidMail() {
        mailService.sendWelcomeMail(new WelcomeMailEvent(2L, invalidEmail));

        final Optional<User> deletedUser = userRepository.findByEmail(invalidEmail);
        assertThat(deletedUser).isEmpty();

        verify(mailSender, Mockito.timeout(1000).times(0)).send(isA(MimeMessage.class));
        verify(slackService, Mockito.timeout(1000).times(0)).sendSlackMailMessage(any());
    }

    @DisplayName("뉴스레터를 보내면, 슬랙에 뉴스레터 전송 메시지가 간다.")
    @Test
    void sendNewsLetter() {
        mailService.sendNewsLetter(new NewsLetterMailEvent(1L, validEmail, "text"));
        verify(mailSender, times(1)).send(isA(MimeMessage.class));
        verify(slackService, times(1)).sendSlackMailMessage(any());
    }
}
