package com.newsletter.cstoday.user.application;

import com.newsletter.cstoday.user.application.event.UserDeleteEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserEventHandler {

    private final UserService userService;

    @EventListener
    public void deleteUser(UserDeleteEvent userDeleteEvent) {
        userService.deleteUserByEmail(userDeleteEvent.getEmail());
    }
}
