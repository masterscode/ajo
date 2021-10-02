package com.ajo.configuration.security;

import com.ajo.utils.JWTUtil;
import com.digicore.http.requests.LoginRequest;
import com.digicore.http.response.LoginResponse;
import com.digicore.models.Account;
import com.digicore.services.intefaces.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//    private @Autowired AccountService accountService;
    private @Autowired JWTUtil jwtUtil;
    private @Autowired ModelMapper modelMapper;



    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        try {
            LoginRequest credentials = modelMapper.map(request.getInputStream(), LoginRequest.class);

            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(credentials.getAccountNumber(), credentials.getAccountPassword(),
                            new ArrayList<>()
                    )
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication auth) throws IOException, ServletException {
        ObjectMapper objectMapper = new ObjectMapper();
        String userName = ((Account) auth.getPrincipal()).getUsername();
        UserDetails account = accountService.loadUserByUsername(userName);
        String token = jwtUtil.generateToken(account);
        LoginResponse loginResponse = new LoginResponse(token, true);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(loginResponse));
        out.flush();
    }
}
