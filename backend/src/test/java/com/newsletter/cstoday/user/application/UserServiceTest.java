package com.newsletter.cstoday.user.application;

import com.newsletter.cstoday.content.domain.Content;
import com.newsletter.cstoday.content.domain.repository.ContentRepository;
import com.newsletter.cstoday.mail.application.MailService;
import com.newsletter.cstoday.mail.application.event.NewsLetterMailEvent;
import com.newsletter.cstoday.mail.application.event.WelcomeMailEvent;
import com.newsletter.cstoday.slack.application.SlackService;
import com.newsletter.cstoday.slack.application.event.SlackJoinEvent;
import com.newsletter.cstoday.user.domain.User;
import com.newsletter.cstoday.user.domain.repository.UserRepository;
import com.newsletter.cstoday.user.ui.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContentRepository contentRepository;

    @MockBean
    private SlackService slackService;

    @MockBean
    private MailService mailService;

    private final String email = "joel610@naver.com";
    private final int mailInterval = 3;

    @BeforeEach
    void setUp() {
        doNothing().when(slackService).sendSlackJoinMessage(isA(SlackJoinEvent.class));
        doNothing().when(mailService).sendWelcomeMail(isA(WelcomeMailEvent.class));
        doNothing().when(mailService).sendNewsLetter(isA(NewsLetterMailEvent.class));
    }

    @DisplayName("이메일과 몇일 주기로 받아볼지를 넘겨주면, 이를 유저로 저장하고, 메일과 슬랙에 알림을 보낸다")
    @Test
    void register() {
        userService.register(new UserDto(email, mailInterval));
        final User user = userRepository.findByEmail(email).get();

        assertThat(user.getEmail()).isEqualTo(email);
        assertThat(user.getMailInterval()).isEqualTo(mailInterval);

        verify(slackService, times(1)).sendSlackJoinMessage(any());
        verify(mailService, times(1)).sendWelcomeMail(any());
    }

    @DisplayName("액티브 유저에게 메일을 보내야 할 날이라면 메일을 보내준다")
    @Test
    @Transactional
    void sendActiveUserNewsLetter() {
        // given
        final User user1 = new User("user1", 1);
        final User user2 = new User("user2", 2);
        final User user3 = new User("user3", 3);
        final User user4 = new User("user4", 4);
        final User user5 = new User("user5", 5);
        userRepository.saveAll(Arrays.asList(user1, user2, user3, user4, user5));

        final Content content1 = new Content("content1");
        final Content content2 = new Content("content2");
        contentRepository.saveAll(Arrays.asList(content1, content2));

        // when
        userService.sendActiveUserNewsLetter();
        userService.sendActiveUserNewsLetter();
        userService.sendActiveUserNewsLetter();

        // then
        assertThat(user1.isActive()).isFalse();
        assertThat(user1.getDatePassedFromLastMail()).isEqualTo(1);
        assertThat(user2.getDatePassedFromLastMail()).isEqualTo(1);
        assertThat(user3.getDatePassedFromLastMail()).isEqualTo(3);
        assertThat(user4.getDatePassedFromLastMail()).isEqualTo(3);
        assertThat(user5.getDatePassedFromLastMail()).isEqualTo(3);
    }
}
