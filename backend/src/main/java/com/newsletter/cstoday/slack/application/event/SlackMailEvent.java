package com.newsletter.cstoday.slack.application.event;

import lombok.Getter;

@Getter
public class SlackMailEvent {

    private final String message;

    public SlackMailEvent(String message) {
        this.message = message;
    }

    public static SlackMailEvent ofWelcome(String email) {
        return new SlackMailEvent("웰컴 메일 전송 - " + email);
    }

    public static SlackMailEvent ofNewsLetter(Long userId, String email) {
        return new SlackMailEvent(
                "### 뉴스레터 메일 전송 ###\n" +
                "ID : " + userId + ", Email : " + email + "\n"
        );
    }
}
