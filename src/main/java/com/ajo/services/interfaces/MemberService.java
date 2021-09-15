package com.ajo.services.interfaces;

import com.ajo.payloads.requests.UserRegistrationRequest;
import com.ajo.payloads.response.UserRegistrationResponse;

public interface MemberService {
    UserRegistrationResponse createMember(UserRegistrationRequest registrationRequest);

}
