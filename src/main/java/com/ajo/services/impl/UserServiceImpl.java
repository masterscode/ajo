package com.ajo.services.impl;

import com.ajo.models.Member;
import com.ajo.models.Role;
import com.ajo.models.enums.UserRole;
import com.ajo.payloads.requests.UserRegistrationRequest;
import com.ajo.payloads.response.UserRegistrationResponse;
import com.ajo.repositories.AdminRepository;
import com.ajo.repositories.MemberRepository;
import com.ajo.repositories.RoleRepository;
import com.ajo.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final AdminRepository adminRepository;
    private final MemberRepository memberRepository;
    private RoleRepository roleRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }

    public UserRegistrationResponse createUser(UserRegistrationRequest registrationRequest, UserRole userRole){
        Role role = roleRepository.findRoleByUserRole(userRole);

        registrationRequest.setPassword(
                passwordEncoder.encode(registrationRequest.getPassword())
        );

        return userRole.name().equals("USER")
                ? createMember(registrationRequest, role): createAdmin(registrationRequest, role);
    }

    private UserRegistrationResponse createMember(UserRegistrationRequest registrationRequest, Role role){

        Member memberToRegister = modelMapper.map(registrationRequest, Member.class);
        memberToRegister.setRole(role);

        Member registeredMember = memberRepository.save(memberToRegister);

        return modelMapper.map(registeredMember, UserRegistrationResponse.class);

    }
    private UserRegistrationResponse createAdmin(UserRegistrationRequest registrationRequest, Role role){

    }

}
