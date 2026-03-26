package com.example.notification_service.Notification_Service.service;

import com.example.notification_service.Notification_Service.model.Notification;
import com.example.notification_service.Notification_Service.model.NotificationType;
import com.example.notification_service.Notification_Service.repo.NotificationRepo;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import tools.jackson.databind.deser.jdk.StringArrayDeserializer;

import java.time.LocalDateTime;


@Service
public class NotificationService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private NotificationRepo notificationRepo;

    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.phone.number}")
    private String fromNumber;

    public void sendNotification(String phone, String email, NotificationType type){
        String message=buildMessage(type);

        //EMAIL Logic part
        SimpleMailMessage mail=new SimpleMailMessage();
        String subject="PARKING MANAGEMENT SYSTEM";
        mail.setTo(email);
        mail.setSubject(subject);
        mail.setText(message);
        mailSender.send(mail);

        System.out.println("Email sent to: "+email);

        //SMS Logic part
        Twilio.init(accountSid,authToken);

        Message sms=Message.creator(
                new PhoneNumber(phone),
                new PhoneNumber(fromNumber),
                message
        ).create();
        System.out.println("SMS sent successfully. sid"+sms.getSid());
        Notification notification=new Notification();
        notification.setUserPhone(phone);
        notification.setEmail(email);
        notification.setMessage(message);
        notification.setType(type);
        notification.setCreatedAt(LocalDateTime.now());

        notificationRepo.save(notification);
    }

    private String buildMessage(NotificationType type){
        return switch (type){
            case REGISTRATION -> "WELCOME! REGISTRATION SUCCESSFULLY";
            case PARKING -> "PARKING BOOKED SUCCESSFULLY";
            case EXIT -> "EXIT DONE. PAYMENT SUCCESSFUL";
        };
    }

}
