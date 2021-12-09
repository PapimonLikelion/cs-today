package com.newsletter.cstoday.mail.application.event;

import lombok.Getter;

@Getter
public class NewsLetterMailEvent {

    private final Long userId;
    private final String email;
    private final String text;

    public NewsLetterMailEvent(Long userId, String email, String text) {
        this.userId = userId;
        this.email = email;
        this.text = text;
    }
}
