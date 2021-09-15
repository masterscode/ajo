package com.ajo.services.impl;

import com.ajo.models.enums.UserRole;
import com.ajo.payloads.requests.UserRegistrationRequest;
import com.ajo.payloads.response.UserRegistrationResponse;
import com.ajo.services.interfaces.AdminService;
import com.ajo.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserService userService;


    @Override
    public UserRegistrationResponse createAdmin(UserRegistrationRequest registrationRequest) {
        return userService.createUser(registrationRequest, UserRole.ADMIN);
    }

    @Override
    public UserRegistrationResponse createMember(UserRegistrationRequest registrationRequest) {
        return userService.createUser(registrationRequest, UserRole.USER);
    }
}
