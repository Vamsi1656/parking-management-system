package com.example.notification_service.Notification_Service.repo;

import com.example.notification_service.Notification_Service.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepo extends JpaRepository<Notification,Long> {
}
