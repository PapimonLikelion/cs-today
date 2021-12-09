package com.newsletter.cstoday.user.ui.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

    @Email(message = "Email should be valid")
    private String email;

    @Min(1)
    @Max(7)
    private Integer mailInterval;

    public UserDto(String email, Integer mailInterval) {
        this.email = email;
        this.mailInterval = mailInterval;
    }
}
