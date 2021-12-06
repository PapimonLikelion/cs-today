package com.newsletter.cstoday.user.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UserTest {

    String email = "joel610naver@gmail.com";
    int mailInterval = 3;
    User user;

    @BeforeEach
    void setUp() {
        user = new User(email, mailInterval);
    }

    @DisplayName("유저를 생성할 때, 이메일 주소와 몇 일에 한번 메일을 받아볼 것인지 정보가 필요하다.")
    @Test
    void createUser() {
        assertThat(user.getEmail()).isEqualTo(email);
        assertThat(user.getMailInterval()).isEqualTo(mailInterval);
    }

    @DisplayName("유저 생성 시, 바로 메일을 받을 수 있도록, datePassedFromLastMail은 mailInterval과 동일하다.")
    @Test
    void createUserDatePassed() {
        assertThat(user.getDatePassedFromLastMail()).isEqualTo(mailInterval);
    }

    @DisplayName("유저의 datePassedFromLastMail이 mailInterval와 같다면, checkMailSend가 true이다.")
    @Test
    void checkMailSend() {
        assertThat(user.checkMailSend()).isTrue();
    }

    @DisplayName("유저의 mailSent 메서드는 datePassedFromLastMail을 1로 만든다.")
    @Test
    void mailSent() {
        user.mailSent();
        assertThat(user.getDatePassedFromLastMail()).isEqualTo(1);
    }

    @DisplayName("유저의 mailSent 메서드는 datePassedFromLastMail이 mailInterval과 같을 때만 사용할 수 있다.")
    @Test
    void mailSentError() {
        user.mailSent();
        assertThat(user.getDatePassedFromLastMail()).isNotEqualTo(user.getMailInterval());
        assertThatThrownBy(() -> user.mailSent())
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("mail을 보내지 않으면 dayPast 메서드로 datePassedFromLastMail을 하루 올린다.")
    @Test
    void dayPast() {
        user.mailSent();
        user.dayPast();
        assertThat(user.getDatePassedFromLastMail()).isEqualTo(2);
    }

    @DisplayName("mailInterval 이상인데 dayPast 메서드를 실행할 수는 없다.")
    @Test
    void dayPastLowerThanMailInterval() {
        assertThatThrownBy(() -> user.dayPast())
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("finishSubscription 메서드는 user의 active를 false로 만든다.")
    @Test
    void finishSubscription() {
        assertThat(user.isActive()).isTrue();
        user.finishSubscription();
        assertThat(user.isActive()).isFalse();
    }

    @DisplayName("최초 가입자는 contentId 1 부터 구독을 시작한다.")
    @Test
    void userStartContentIdFrom1() {
        assertThat(user.getContentId()).isOne();
    }

    @DisplayName("mailSent 메서드는 contentId를 1 올린다.")
    @Test
    void mailSentMakeContentIdIncreaseByOne() {
        user.mailSent();
        assertThat(user.getContentId()).isEqualTo(2L);
        
        // 메일 보낸 후 2일이 있어야 다음 메일 전송 가능
        user.dayPast();
        user.dayPast();
        user.mailSent();
        assertThat(user.getContentId()).isEqualTo(3L);
    }
}
