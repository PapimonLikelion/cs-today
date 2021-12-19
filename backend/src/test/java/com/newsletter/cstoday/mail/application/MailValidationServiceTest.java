package com.newsletter.cstoday.mail.application;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MailValidationServiceTest {

    @Autowired
    MailValidationService mailValidationService;

    @Disabled
    @DisplayName("유효한 메일이면 true, 아니면 false를 반환한다.")
    @Test
    void checkValidMail() {
        String validMail = "joel610@naver.com";
        assertThat(mailValidationService.checkValidMail(validMail)).isTrue();

        String invalidMail = "sdf09xjc98j98jd91jclx90jc9j0f!!!j8293jkjijijijsidfjoij@gmail.com";
        assertThat(mailValidationService.checkValidMail(invalidMail)).isFalse();

        String invalidMail2 = "hihihihihchcdic@solsosososo.net";
        assertThat(mailValidationService.checkValidMail(invalidMail2)).isFalse();
    }
}
