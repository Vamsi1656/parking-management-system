package com.example.notification_service.Notification_Service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private NotificationType type;

    @NotBlank(message = "phone number should not be null")
    @Pattern(regexp ="^(\\+91)?[6-9][0-9]{9}$",message = "Mobile number must be 10 digits")
    private String userPhone;

    @NotBlank(message = "Email must not be blank")
    @Email(message = "Please provide a valid email address")
    private String email;

    private String message;

    private LocalDateTime createdAt;
}
