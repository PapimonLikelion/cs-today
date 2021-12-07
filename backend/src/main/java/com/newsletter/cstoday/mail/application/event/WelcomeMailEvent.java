package com.newsletter.cstoday.mail.application.event;

import lombok.Getter;

@Getter
public class WelcomeMailEvent {

    private final String email;

    public WelcomeMailEvent(String email) {
        this.email = email;
    }
}
