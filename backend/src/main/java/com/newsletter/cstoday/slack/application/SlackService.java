package com.newsletter.cstoday.slack.application;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SlackService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${slack.url}")
    private String slackUrl;

    public void sendSlackMessage(String message) {
        Map<String, String> body = new HashMap<>();
        body.put("text", message);
        restTemplate.postForObject(slackUrl, body, String.class);
    }
}
