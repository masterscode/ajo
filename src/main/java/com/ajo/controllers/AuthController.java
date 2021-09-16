package com.ajo.controllers;


import com.ajo.payloads.requests.UserLoginRequest;
import com.ajo.payloads.response.LoginResponse;
import com.ajo.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/auth")
public class AuthController {

    private final UserService userService;

    public @ResponseBody LoginResponse doLogin(@Valid @RequestBody UserLoginRequest loginRequest){
        return userService.loginUser(loginRequest);
    }
}
