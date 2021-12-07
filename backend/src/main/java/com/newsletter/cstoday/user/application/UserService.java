package com.newsletter.cstoday.user.application;

import com.newsletter.cstoday.mail.application.MailService;
import com.newsletter.cstoday.slack.application.SlackService;
import com.newsletter.cstoday.user.domain.User;
import com.newsletter.cstoday.user.domain.repository.UserRepository;
import com.newsletter.cstoday.user.ui.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final MailService mailService;
    private final SlackService slackService;
    private final UserRepository userRepository;

    public void register(UserDto userDto) {
        final User user = new User(userDto.getEmail(), userDto.getMailInterval());
        userRepository.save(user);
        mailService.sendWelcomeMail(user.getEmail());
        slackService.sendSlackMessage(user.getEmail() + "-" + user.getMailInterval() + "일 간격 신규 가입");
    }
}
