package com.ajo.payloads.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRegistrationRequest implements Serializable {

    @NotBlank(message = "This field should not be empty")
    private String firstName;

    @NotBlank(message = "This field should not be empty")
    private String lastName;

    @NotBlank(message = "This field should not be empty")
    private String email;

    @NotBlank(message = "This field should not be empty")
    private String password;

    @NotBlank(message = "This field should not be empty")
    private String phoneNumber;
}
