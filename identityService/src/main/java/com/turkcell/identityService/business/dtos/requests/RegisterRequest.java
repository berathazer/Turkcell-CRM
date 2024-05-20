package com.turkcell.identityService.business.dtos.requests;

import com.turkcell.identityService.entitites.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterRequest {

    @NotNull(message = "This field is required")
    @Email(message = "Please enter a valid e-mail address")
    private String email;

    @NotNull(message = "This field is required")
    private String password;

    @NotNull(message = "This field is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthdate;
}
