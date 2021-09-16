package com.ajo.controllers;


import com.ajo.payloads.requests.UserRegistrationRequest;
import com.ajo.payloads.response.ApiResponse;
import com.ajo.payloads.response.UserRegistrationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/v1/admin")
public class AdminController {
    @PostMapping
    public ResponseEntity<ApiResponse<UserRegistrationResponse>> createAdmin(@Valid @RequestBody UserRegistrationRequest registrationRequest){
        return null;
    }
}
