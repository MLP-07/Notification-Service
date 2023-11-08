package com.example.mlpnotificationservice.service;

import com.example.mlpnotificationservice.model.Notification;
import com.example.mlpnotificationservice.model.NotificationType;
import com.example.mlpnotificationservice.repo.NotificationRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class SmsService {

    private final NotificationRepository notificationRepository;
    private final ObjectMapper mapper;

    public void sendSms(String countryCode, String phoneNumber, String messsage, String userId) {
        log.info("Sending message");

        Map<String, String> map = new HashMap<>();
        map.put("phoneNumber", countryCode + "-" + phoneNumber);
        map.put("message", messsage);

        Notification notification;
        try {
            notification = Notification.builder()
                    .notificationType(NotificationType.SMS)
                    .payload(mapper.writeValueAsString(map))
                    .userId(UUID.fromString(userId))
                    .build();
            notificationRepository.save(notification);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        log.info("message sent to " + countryCode + phoneNumber);
        log.info("Message is " + messsage);

    }
}
