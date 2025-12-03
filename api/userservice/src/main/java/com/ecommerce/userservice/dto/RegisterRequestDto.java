package com.ecommerce.userservice.dto;

import com.ecommerce.userservice.model.UserRole;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequestDto {
    @NotBlank(message= "Username is required")
    private String username;
    @NotBlank(message= "Email is required")
    @Email(message= "Invalid Email Format")
    private String email;
    @NotBlank(message= "Password is required")
    @Size(message = "Password should be atleast 8 chars long")
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRole role;
}
