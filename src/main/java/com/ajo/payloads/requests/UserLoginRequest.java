package com.ajo.payloads.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserLoginRequest {
    @Email(message = "invalid Email format")
    private String email;
    private String password;
}
