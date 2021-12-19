package com.newsletter.cstoday.mail.ui.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
public class MailValidationResponse {
    private String smtpCheck;
    private String dnsCheck;
}
