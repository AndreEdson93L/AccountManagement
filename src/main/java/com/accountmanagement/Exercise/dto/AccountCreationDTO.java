package com.accountmanagement.Exercise.dto;

import com.accountmanagement.Exercise.model.Role;
import lombok.Data;

@Data
public class AccountCreationDTO {
    private String email;
    private String password;
    private String nickname;
    private Boolean consent;
    private Role role;
}
