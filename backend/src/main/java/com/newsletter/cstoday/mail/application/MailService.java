package com.newsletter.cstoday.mail.application;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String serverMailAddress;

    public void sendWelcomeMail(String email) {
        try {
            final MimeMessage mimeMessage = mailSender.createMimeMessage();
            final MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            mimeMessageHelper.setFrom(serverMailAddress);
            mimeMessageHelper.setTo(email);
            mimeMessageHelper.setSubject("오늘의 CS를 구독해주셔서 감사합니다 🎁");
            mimeMessageHelper.setText(WelcomeMail.welcomeMailContent, true);
            mailSender.send(mimeMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    final InputStream inputStream = new File("C:\\Users\\joel6\\Desktop\\index.html").toURI()
//            .toURL()
//            .openStream();
//    final String mailContents = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
//    IOUtils.closeQuietly(inputStream);
}
