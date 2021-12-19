package com.newsletter.cstoday.mail.application;

import com.newsletter.cstoday.mail.ui.dto.MailValidationResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
public class MailValidationService {

    private static final String EMAIL_VERIFICATION_API = "https://emailverification.whoisxmlapi.com/api/v2?";

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${setting.email.api-key}")
    private String apiKey;

    public boolean checkValidMail(String mailAddress) {
        final MailValidationResponse mailValidationResponse = requestVerification(mailAddress);
        final String smtpCheck = mailValidationResponse.getSmtpCheck();
        if (Objects.isNull(smtpCheck)) {
            return false;
        }
        return "true".equals(smtpCheck);
    }

    private MailValidationResponse requestVerification(String mailAddress) {
        final String verificationRequest = EMAIL_VERIFICATION_API + "apiKey=" + apiKey + "&emailAddress=" + mailAddress;
        final MailValidationResponse mailValidationResponse = restTemplate.getForEntity(verificationRequest,
                MailValidationResponse.class).getBody();

        if (Objects.isNull(mailValidationResponse)) {
            throw new IllegalStateException("이메일 검증 API에서 오류가 발생했습니다.");
        }
        return mailValidationResponse;
    }
}
