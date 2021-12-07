package com.newsletter.cstoday.user.domain.repository;

import com.newsletter.cstoday.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @DisplayName("현재 액티브 유저들을 불러올 수 있다.")
    @Test
    void activeUsers() {
        // given
        final User user1 = new User("email1", 1);
        final User user2 = new User("email2", 2);
        final User user3 = new User("email3", 3);
        final User user4 = new User("email4", 4);
        final User user5 = new User("email5", 5);
        final User user6 = new User("email6", 6);
        final User user7 = new User("email7", 7);

        user2.finishSubscription();
        user4.finishSubscription();
        user6.finishSubscription();

        userRepository.saveAll(Arrays.asList(user1, user2, user3, user4, user5, user6, user7));

        // when
        final List<User> activeUsers = userRepository.findAllByActive(true);
        assertThat(activeUsers).hasSize(4);
    }
}