package com.vitali.runstats.controller;

import com.vitali.runstats.dto.UserDto;
import com.vitali.runstats.service.api.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public UserDto register(@RequestBody UserDto user) {
        return userService.save(user);
    }
}
