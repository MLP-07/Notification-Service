package com.example.mlpnotificationservice.dto;

import com.example.mlpnotificationservice.model.NotificationType;
import lombok.*;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class NotificationResponseDto {
    private UUID id;
    private NotificationType notificationType;
    private Map<String, Object> payload;
}
