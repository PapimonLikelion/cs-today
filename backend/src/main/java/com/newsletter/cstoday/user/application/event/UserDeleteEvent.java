package com.newsletter.cstoday.user.application.event;

import lombok.Getter;

@Getter
public class UserDeleteEvent {

    private final String email;

    public UserDeleteEvent(String email) {
        this.email = email;
    }
}
