package com.newsletter.cstoday.slack.application.event;

import lombok.Getter;

@Getter
public class SlackMessageEvent {

    private final String message;

    public SlackMessageEvent(String message) {
        this.message = message;
    }

    public static SlackMessageEvent ofNewSubscription(String email, int mailInterval) {
        return new SlackMessageEvent(email + "-" + mailInterval + "일 간격 신규 가입");
    }
}
