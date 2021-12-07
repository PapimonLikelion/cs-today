package com.newsletter.cstoday.user.application;

import com.newsletter.cstoday.mail.application.MailService;
import com.newsletter.cstoday.mail.application.event.WelcomeMailEvent;
import com.newsletter.cstoday.slack.application.SlackService;
import com.newsletter.cstoday.slack.application.event.SlackMessageEvent;
import com.newsletter.cstoday.user.domain.User;
import com.newsletter.cstoday.user.domain.repository.UserRepository;
import com.newsletter.cstoday.user.ui.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @MockBean
    private SlackService slackService;

    @MockBean
    private MailService mailService;

    private final String email = "joel610@naver.com";
    private final int mailInterval = 3;

    @BeforeEach
    void setUp() {
        doNothing().when(slackService).sendSlackMessage(isA(SlackMessageEvent.class));
        doNothing().when(mailService).sendWelcomeMail(isA(WelcomeMailEvent.class));
    }

    @DisplayName("이메일과 몇일 주기로 받아볼지를 넘겨주면, 이를 유저로 저장하고, 메일과 슬랙에 알림을 보낸다")
    @Test
    void register() {
        userService.register(new UserDto(email, mailInterval));
        final User user = userRepository.findByEmail(email).get();

        assertThat(user.getEmail()).isEqualTo(email);
        assertThat(user.getMailInterval()).isEqualTo(mailInterval);

        verify(slackService, times(1)).sendSlackMessage(any());
        verify(mailService, times(1)).sendWelcomeMail(any());
    }
}
