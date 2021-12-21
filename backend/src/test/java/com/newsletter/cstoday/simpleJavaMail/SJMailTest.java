package com.newsletter.cstoday.simpleJavaMail;

import com.newsletter.cstoday.mail.application.WelcomeMail;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;

import java.io.File;

class SJMailTest {

    File dkim = new File("C:\\Users\\joel6\\Desktop\\오늘의 CS\\dkim.der");

    @Disabled
    @Test
    void test() {
        final Email email = EmailBuilder.startingBlank()
                .to("cs.today.100@gmail.com")
                .from("오늘의 CS", "cstoday@cstoday.me")
                .withSubject("testing!")
                .withHTMLText(WelcomeMail.welcomeMailContent)
                .signWithDomainKey(dkim, "cstoday.me", "mail")
                .buildEmail();

        final Mailer mailer = MailerBuilder
                .withSMTPServer("smtp.a.net", 465, "a", "b")
                .withTransportStrategy(TransportStrategy.SMTP_TLS)
                .withProperty("mail.smtp.auth", true)
                .withProperty("mail.smtp.timeout", 5000)
                .withProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory")
                .withProperty("mail.smtp.starttls.enable", true)
                .withDebugLogging(true)
                .buildMailer();

        mailer.sendMail(email);
    }
}
