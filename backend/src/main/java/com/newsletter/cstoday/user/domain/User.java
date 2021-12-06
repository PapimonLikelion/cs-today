package com.newsletter.cstoday.user.domain;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private int mailInterval;
    private int datePassedFromLastMail;
    private boolean active;
    private Long contentId;

    protected User() {
    }

    public User(String email, int mailInterval) {
        this.email = email;
        this.mailInterval = mailInterval;
        this.datePassedFromLastMail = mailInterval;
        this.active = true;
        this.contentId = 1L;
    }

    public boolean checkMailSend() {
        return (datePassedFromLastMail == mailInterval);
    }

    public void mailSent() {
        if (datePassedFromLastMail != mailInterval) {
            throw new IllegalArgumentException();
        }
        datePassedFromLastMail = 1;
        contentId++;
    }

    public void dayPast() {
        if (datePassedFromLastMail >= mailInterval) {
            throw new IllegalArgumentException();
        }
        datePassedFromLastMail++;
    }

    public void finishSubscription() {
        active = false;
    }
}
