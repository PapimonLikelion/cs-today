package com.newsletter.cstoday.user.application;

import com.newsletter.cstoday.mail.application.MailService;
import com.newsletter.cstoday.user.domain.User;
import com.newsletter.cstoday.user.domain.repository.UserRepository;
import com.newsletter.cstoday.user.ui.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final MailService mailService;
    private final UserRepository userRepository;

    public void register(UserDto userDto) {
        final User user = new User(userDto.getEmail(), userDto.getMailInterval());
        userRepository.save(user);
        mailService.sendWelcomeMail(user.getEmail());
    }
}
