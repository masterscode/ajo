package com.ajo.controllers;


import com.ajo.payloads.requests.UserRegistrationRequest;
import com.ajo.payloads.response.UserRegistrationResponse;
import com.ajo.services.interfaces.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/v1/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    @PostMapping
    public ResponseEntity<UserRegistrationResponse> createUser(@Valid @RequestBody UserRegistrationRequest registrationRequest)  {
        return new ResponseEntity<>(
                memberService.createMember(registrationRequest),
                HttpStatus.CREATED
        );
    }
}
