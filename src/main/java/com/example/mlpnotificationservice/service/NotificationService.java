package com.example.mlpnotificationservice.service;

import com.example.mlpnotificationservice.dto.NotificationResponseDto;
import com.example.mlpnotificationservice.model.Notification;
import com.example.mlpnotificationservice.model.NotificationType;
import com.example.mlpnotificationservice.model.NotificationTypeConverter;
import com.example.mlpnotificationservice.repo.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public List<Notification> getAllNotificationsForUser(String userId) {
        UUID uuid = UUID.fromString(userId);
        return notificationRepository.findAllByUserId(uuid);
    }

    public List<Notification> getAllNotificationForUserFilterByType(String userId, String type) {
        NotificationType notificationType;
        if (type.equals("sms")) notificationType = NotificationType.SMS;
        else if (type.equals("email")) notificationType = NotificationType.EMAIL;
        else {
            throw new RuntimeException("Invalid type parameter");
        }
        UUID uuid = UUID.fromString(userId);
        return notificationRepository.findAllByUserIdAndAndNotificationType(uuid, notificationType);
    }
}
