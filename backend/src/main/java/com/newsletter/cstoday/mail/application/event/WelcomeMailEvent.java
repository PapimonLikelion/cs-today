package com.newsletter.cstoday.mail.application.event;

import lombok.Getter;

@Getter
public class WelcomeMailEvent {

    private final Long userId;
    private final String email;

    public WelcomeMailEvent(Long userId, String email) {
        this.userId = userId;
        this.email = email;
    }
}
