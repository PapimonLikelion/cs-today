package com.newsletter.cstoday.mail.application;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class MailSenderImpl implements MailSender {

    @Override
    public void sendMail(Long userId, String destination, String subject, String text) {
        try {
            final JavaMailSenderImpl mailSender = MailSenderManager.findMailSender(userId);
            final MimeMessage mimeMessage = mailSender.createMimeMessage();
            final MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            mimeMessageHelper.setFrom(mailSender.getUsername());
            mimeMessageHelper.setTo(destination);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(text, true);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new IllegalStateException("MimeMessage 메일을 만드는 과정에서 오류가 발생했습니다.");
        }
    }
}
