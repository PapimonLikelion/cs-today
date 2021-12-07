package com.newsletter.cstoday.user.application;

import com.newsletter.cstoday.mail.application.event.WelcomeMailEvent;
import com.newsletter.cstoday.slack.application.event.SlackMessageEvent;
import com.newsletter.cstoday.user.domain.User;
import com.newsletter.cstoday.user.domain.repository.UserRepository;
import com.newsletter.cstoday.user.ui.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final ApplicationEventPublisher eventPublisher;
    private final UserRepository userRepository;

    @Transactional
    public void register(UserDto userDto) {
        final User user = new User(userDto.getEmail(), userDto.getMailInterval());
        userRepository.save(user);
        eventPublisher.publishEvent(new WelcomeMailEvent(user.getEmail()));
        eventPublisher.publishEvent(SlackMessageEvent.ofNewSubscription(user.getEmail(), user.getMailInterval()));
    }
}
