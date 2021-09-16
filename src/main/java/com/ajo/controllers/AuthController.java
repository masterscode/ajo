package com.ajo.controllers;


import com.ajo.payloads.requests.UserLoginRequest;
import com.ajo.payloads.response.LoginResponse;
import com.ajo.services.interfaces.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
public class AuthController {

    private final UserService userService;

    @PostMapping("/login")
    @ApiOperation(value="use this endpoint for admin and member login", response = LoginResponse.class, httpMethod = "POST")
    public @ResponseBody LoginResponse doLogin(@Valid @RequestBody UserLoginRequest loginRequest){
        return userService.loginUser(loginRequest);
    }
}
