package com.ajo.services.impl;

import com.ajo.payloads.requests.UserRegistrationRequest;
import com.ajo.payloads.response.UserRegistrationResponse;
import com.ajo.repositories.MemberRepository;
import com.ajo.services.interfaces.MemberService;
import com.ajo.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {
    private final UserService userService;
    private final MemberRepository memberRepository;


    @Override
    public UserRegistrationResponse createMember(UserRegistrationRequest registrationRequest) {
        return null;
    }
}
