package com.ecommerce.userservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DeleteRequestDto {
    @NotBlank(message= "Cannot be blank")
    @Email(message = "Invalid Email")
    private String email;
}
