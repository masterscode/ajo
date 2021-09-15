package com.ajo.payloads.response;

import com.ajo.models.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRegistrationResponse implements Serializable {

    private String publicId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    private Boolean isAccountNonExpired;
    private Boolean isAccountNonLocked;
    private Boolean isCredentialsNonExpired;
    private Boolean isEnabled;

    private Role role;
}
