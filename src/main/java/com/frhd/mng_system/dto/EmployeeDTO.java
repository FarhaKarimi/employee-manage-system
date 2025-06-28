package com.frhd.mng_system.dto;


import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EmployeeDTO(
        @JsonView(Views.Public.class) Long id,
        @JsonView(Views.Public.class) @NotBlank(message = "First name cannot be blank") String firstName,
        @JsonView(Views.Public.class) @NotBlank(message = "Last name cannot be blank") String lastName,
        @JsonView(Views.Detailed.class) @Email(message = "Email must be valid") String email,
        @JsonView(Views.Detailed.class) @NotBlank(message = "Department cannot be blank") String department,
        @JsonView(Views.Admin.class) @NotNull(message = "Salary cannot be null") @Positive(message = "Salary must be positive") Double salary) {
    public interface Views {
        interface Public {}
        interface Detailed extends Public {}
        interface Admin extends Detailed {}
    }
}