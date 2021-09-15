package com.ajo.controllers;


import com.ajo.payloads.requests.UserRegistrationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/member")
@RequiredArgsConstructor
public class MemberController {
    @PostMapping
    public ResponseEntity<UserRegistrationRequest> createUser(@Valid @RequestBody UserRegistrationRequest registrationRequest){

    }
}
