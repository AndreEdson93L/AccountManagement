package com.accountmanagement.Exercise.dto;

import com.accountmanagement.Exercise.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NonNull;

@Data
public class AccountCreationDTO {

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password should have at least 8 characters")
    private String password;

    private String nickname;

    @NotNull(message = "Consent is required")
    private Boolean consent;

    @NotNull(message = "Role is required")
    private Role role;
}
