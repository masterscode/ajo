package com.ajo.payloads.response;

import com.ajo.models.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginResponse {
    private String publicId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String token;

    private Boolean isAccountNonExpired;
    private Boolean isAccountNonLocked;
    private Boolean isCredentialsNonExpired;
    private Boolean isEnabled;

    private String role;
}
