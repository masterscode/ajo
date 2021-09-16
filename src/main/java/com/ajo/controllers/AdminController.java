package com.ajo.controllers;


import com.ajo.models.enums.UserRole;
import com.ajo.payloads.requests.UserRegistrationRequest;
import com.ajo.payloads.response.ApiResponse;
import com.ajo.payloads.response.UserRegistrationResponse;
import com.ajo.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/v1/admin")
public class AdminController {
    private final UserService userService;
    @PostMapping
    public ResponseEntity<ApiResponse<UserRegistrationResponse>> createAdmin(@Valid @RequestBody UserRegistrationRequest registrationRequest){
        return null;
    }

    @PostMapping("/user")
    public @ResponseBody UserRegistrationResponse adminCreateUser(@Valid @RequestBody UserRegistrationRequest registrationRequest){
        return userService.createUser(registrationRequest, UserRole.MEMBER);
    }
}
