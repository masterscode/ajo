package com.ajo.services.interfaces;


import com.ajo.models.enums.UserRole;
import com.ajo.payloads.requests.UserLoginRequest;
import com.ajo.payloads.requests.UserRegistrationRequest;
import com.ajo.payloads.response.LoginResponse;
import com.ajo.payloads.response.UserRegistrationResponse;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService {
    UserRegistrationResponse createUser(UserRegistrationRequest registrationRequest, UserRole userRole);
    LoginResponse loginUser(UserLoginRequest request);
}