package com.newsletter.cstoday.mail.application.event;

import lombok.Getter;

@Getter
public class NewsLetterMailEvent {

    private final String email;
    private final String text;

    public NewsLetterMailEvent(String email, String text) {
        this.email = email;
        this.text = text;
    }
}
