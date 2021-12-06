package com.newsletter.cstoday.user.ui.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

    @Email(message = "Email should be valid")
    private String email;
    private int mailInterval;

    public UserDto(String email, int mailInterval) {
        this.email = email;
        this.mailInterval = mailInterval;
    }
}
