package com.newsletter.cstoday.slack.application.event;

import lombok.Getter;

@Getter
public class SlackJoinEvent {

    private final String message;

    public SlackJoinEvent(String message) {
        this.message = message;
    }

    public static SlackJoinEvent ofNewSubscription(String email, int mailInterval) {
        return new SlackJoinEvent(email + "-" + mailInterval + "일 간격 신규 가입");
    }
}
