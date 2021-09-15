package com.ajo.services.impl;

import com.ajo.models.Admin;
import com.ajo.models.Member;
import com.ajo.models.Role;
import com.ajo.models.User;
import com.ajo.models.enums.UserRole;
import com.ajo.payloads.requests.UserLoginRequest;
import com.ajo.payloads.requests.UserRegistrationRequest;
import com.ajo.payloads.response.LoginResponse;
import com.ajo.payloads.response.UserRegistrationResponse;
import com.ajo.repositories.AdminRepository;
import com.ajo.repositories.MemberRepository;
import com.ajo.repositories.RoleRepository;
import com.ajo.services.interfaces.UserService;
import com.ajo.utils.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final AdminRepository adminRepository;
    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;

    private User user;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        setUser(s);
        return user;
    }


    @Override
    public LoginResponse loginUser(UserLoginRequest request) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                                request.getEmail(), request.getPassword()
                        ));

//        authentication.
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails loggedInUser = loadUserByUsername(request.getEmail());

        final String jwtToken = jwtUtil.generateToken(loggedInUser);


        LoginResponse response = modelMapper.map(loggedInUser, LoginResponse.class);
        response.setToken(jwtToken);

        return response;
    }

    public UserRegistrationResponse createUser(UserRegistrationRequest registrationRequest, UserRole userRole) {
        Role role = roleRepository.findRoleByUserRole(userRole);

        registrationRequest.setPassword(
                passwordEncoder.encode(registrationRequest.getPassword())
        );

        return userRole.name().equals("USER")
                ? createMember(registrationRequest, role) : createAdmin(registrationRequest, role);
    }

    private UserRegistrationResponse createMember(UserRegistrationRequest registrationRequest, Role role) {
        boolean existsByEmail = memberRepository.existsByEmail(registrationRequest.getEmail());
        if (existsByEmail) throw new EntityExistsException();

        Member memberToRegister = modelMapper.map(registrationRequest, Member.class);
        memberToRegister.setRole(role);

        Member registeredMember = memberRepository.save(memberToRegister);

         UserRegistrationResponse registrationResponse= modelMapper.map(registeredMember, UserRegistrationResponse.class);
         registrationResponse.setRole(
                 registeredMember.getRole().getAuthority()
         );

         return registrationResponse;
    }

    private UserRegistrationResponse createAdmin(UserRegistrationRequest registrationRequest, Role role) {
        boolean existsByEmail = memberRepository.existsByEmail(registrationRequest.getEmail());
        if (existsByEmail) throw new EntityExistsException();

        Admin adminToRegister = modelMapper.map(registrationRequest, Admin.class);
        adminToRegister.setRole(role);

        Admin registeredMember = adminRepository.save(adminToRegister);

        UserRegistrationResponse registrationResponse= modelMapper.map(registeredMember, UserRegistrationResponse.class);
        registrationResponse.setRole(
                registeredMember.getRole().getAuthority()
        );

        return registrationResponse;
    }

    private void setUser(String email){
        memberRepository.findMemberByEmail(email).ifPresentOrElse(member -> this.user = member, ()-> this.user = adminRepository.findByEmail(email).orElseThrow());
    }
}
