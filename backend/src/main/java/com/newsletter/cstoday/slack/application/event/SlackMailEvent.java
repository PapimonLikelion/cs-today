package com.newsletter.cstoday.slack.application.event;

import lombok.Getter;

@Getter
public class SlackMailEvent {

    private final String message;

    public SlackMailEvent(String message) {
        this.message = message;
    }

    public static SlackMailEvent ofWelcome(String email) {
        return new SlackMailEvent(email + " -- 웰컴 메일 전송");
    }

    public static SlackMailEvent ofNewsLetter(String email) {
        return new SlackMailEvent(email + " -- 뉴스레터 메일 전송");
    }
}
