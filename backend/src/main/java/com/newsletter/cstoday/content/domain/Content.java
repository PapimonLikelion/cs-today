package com.newsletter.cstoday.content.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "LONGTEXT")
    private String text;

    protected Content() {
    }

    public Content(String text) {
        this.text = text;
    }
}
