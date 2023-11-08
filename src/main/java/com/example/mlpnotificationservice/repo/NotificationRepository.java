package com.example.mlpnotificationservice.repo;

import com.example.mlpnotificationservice.model.Notification;
import com.example.mlpnotificationservice.model.NotificationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, UUID> {

    List<Notification> findAllByUserId(UUID userId);

    List<Notification> findAllByUserIdAndAndNotificationType(UUID userId, NotificationType notificationType);
}
