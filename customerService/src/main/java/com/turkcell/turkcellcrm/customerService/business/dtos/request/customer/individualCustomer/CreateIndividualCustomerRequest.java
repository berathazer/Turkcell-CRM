package com.turkcell.turkcellcrm.customerService.business.dtos.request.customer.individualCustomer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateIndividualCustomerRequest {
    @NotNull
    @Pattern(regexp = "^[a-zA-Z]+$", message = "First name must contain only letters")
    @Size(min = 2, max = 30)
    private String firstName;
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Middle name must contain only letters")
    @Size(min = 2, max = 30)
    private String middleName;
    @NotNull
    @Size(min = 2, max = 30)
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Last name must contain only letters")
    private String lastName;
    @NotNull
    private LocalDate birthDate;
    @NotNull
    private int gender;
    @Size(min = 2, max = 30)
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Father name must contain only letters")
    private String fatherName;
    @Size(min = 2, max = 30)
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Mother name must contain only letters")
    private String motherName;
    @NotNull
    @Size(min = 11, max = 11, message = "Nationality ID must be 11.")
    @Pattern(regexp = "\\d+", message = "Nationality ID must contain only digits")
    private String nationalityNumber;
    @NotNull
    @Size(min = 10, max = 30)
    @Email(message = "Email should be valid")
    private String email;
    @NotNull
    @Size(min = 10, max = 10)
    @Pattern(regexp = "\\d+", message = "Mobile Phone number must contain only digits")
    private String mobilePhoneNumber;
}
