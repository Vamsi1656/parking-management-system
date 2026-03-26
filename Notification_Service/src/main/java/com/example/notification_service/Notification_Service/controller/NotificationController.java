package com.example.notification_service.Notification_Service.controller;

import com.example.notification_service.Notification_Service.model.NotificationRequest;
import com.example.notification_service.Notification_Service.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    private NotificationService service;

    @PostMapping("/send")
    public String sendNotification(@RequestBody NotificationRequest request){
        service.sendNotification(
                request.getPhone(),
                request.getEmail(),
                request.getType()
        );
        return "Notification send via SMS and Email";
    }
}
