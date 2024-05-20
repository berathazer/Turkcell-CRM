package com.turkcell.identityService.business.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class LoginRequest {
    @NotNull(message = "This field is required")
    @Email(message = "Please enter a valid e-mail address")
    private String email;

    @NotNull(message = "This field is required")
    private String password;
}
