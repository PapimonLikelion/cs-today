package com.newsletter.cstoday.user.ui;

import com.newsletter.cstoday.user.application.UserService;
import com.newsletter.cstoday.user.ui.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody UserDto userDto) {
        userService.register(userDto);
        return ResponseEntity.ok().build();
    }
}
