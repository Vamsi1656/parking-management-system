package com.example.registation_service.Registation_Service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {

    private Long id;
    @NotBlank(message = "UserName is required")
    private String userName;
    @NotBlank(message = "Email is requried")
    @Email(message = "Invalid email format")
    private String email;
    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must at least 6 character")
    private String password;
    @NotBlank(message = "PhoneNumber is required")
    @Pattern(regexp = "^[0-9]{10}$",message = "phone number must be 10 digits")
    private String phoneNumber;
    @NotBlank(message = "State is required")
    private String state;
}
