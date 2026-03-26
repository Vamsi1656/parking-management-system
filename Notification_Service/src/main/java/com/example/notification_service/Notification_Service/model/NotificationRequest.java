package com.example.notification_service.Notification_Service.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class NotificationRequest {

    private NotificationType type;

    @NotBlank(message = "Phone number should not be blank")
    @Pattern(regexp = "^(\\+91)?[6-9][0-9]{9}$",message = "Mobile number must be 10 digits")
    private String phone;

    @NotBlank(message = "Email must not be blank")
    @Email(message = "Please provide a valid email address")
    private String email;
}