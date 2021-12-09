package com.newsletter.cstoday.user.application;

import com.newsletter.cstoday.content.domain.Content;
import com.newsletter.cstoday.content.domain.repository.ContentRepository;
import com.newsletter.cstoday.mail.application.event.NewsLetterMailEvent;
import com.newsletter.cstoday.mail.application.event.WelcomeMailEvent;
import com.newsletter.cstoday.slack.application.event.SlackJoinEvent;
import com.newsletter.cstoday.user.domain.User;
import com.newsletter.cstoday.user.domain.repository.UserRepository;
import com.newsletter.cstoday.user.ui.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final ApplicationEventPublisher eventPublisher;
    private final UserRepository userRepository;
    private final ContentRepository contentRepository;

    @Transactional
    public void register(UserDto userDto) {
        final User user = new User(userDto.getEmail(), userDto.getMailInterval());
        userRepository.save(user);
        eventPublisher.publishEvent(new WelcomeMailEvent(user.getId(), user.getEmail()));
        eventPublisher.publishEvent(SlackJoinEvent.ofNewSubscription(user.getId(), user.getEmail(), user.getMailInterval()));
    }

    @Scheduled(cron = "0 0 8 * * ?")
    @Transactional
    public void sendActiveUserNewsLetter() {
        log.info("### Newsletter mail started -- " + LocalDateTime.now().toString());
        final List<User> activeUsers = userRepository.findAllByActive(true);
        for (User user : activeUsers) {
            checkIsMailSendDay(user);
        }
    }

    private void checkIsMailSendDay(User user) {
        if (user.isMailSendDay()) {
            sendNewsLetter(user);
            return;
        }
        user.dayPast();
    }

    public void sendNewsLetter(User user) {
        final Optional<Content> content = contentRepository.findById(user.getContentId());
        if (content.isPresent()) {
            final Content todayContent = content.get();
            eventPublisher.publishEvent(new NewsLetterMailEvent(user.getId(), user.getEmail(), todayContent.getText()));
            user.mailSent();
            return;
        }
        user.finishSubscription();
    }
}
