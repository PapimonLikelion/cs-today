package com.newsletter.cstoday.slack.application.event;

import lombok.Getter;

@Getter
public class SlackJoinEvent {

    private final String message;

    public SlackJoinEvent(String message) {
        this.message = message;
    }

    public static SlackJoinEvent ofNewSubscription(Long userId, String email, int mailInterval) {
        return new SlackJoinEvent(
                "### 신규 가입 ###\n" +
                "ID : " + userId + ", Email : " + email + ", 간격 : " + mailInterval + "\n"
        );
    }
}
