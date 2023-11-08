package com.example.mlpnotificationservice.controller;

import com.example.mlpnotificationservice.dto.NotificationResponseDto;
import com.example.mlpnotificationservice.model.Notification;
import com.example.mlpnotificationservice.service.NotificationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user/{userId}/notification-history")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;
    private final ObjectMapper mapper;

    @GetMapping
    ResponseEntity<List<NotificationResponseDto>> getNotificationsForUser(@PathVariable("userId") String userId, @RequestParam(value = "type", required = false) String type) {
        List<Notification> notifications;
        if (type == null) {
            notifications = notificationService.getAllNotificationsForUser(userId);
        } else {
            notifications = notificationService.getAllNotificationForUserFilterByType(userId, type);
        }

        List<NotificationResponseDto> responseBody = notifications.stream().map(o -> {
            Map<String, Object> map = null;
            try {
                map = mapper.readValue(o.getPayload(), new TypeReference<>() {
                });
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            return NotificationResponseDto.builder().notificationType(o.getNotificationType()).id(o.getId()).payload(map).build();
        }).collect(Collectors.toList());

        return ResponseEntity.ok(responseBody);
    }
}
