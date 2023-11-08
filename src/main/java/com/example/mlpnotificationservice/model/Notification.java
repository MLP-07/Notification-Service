package com.example.mlpnotificationservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Map;
import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private NotificationType notificationType;

    @Lob
    private String payload;

    private UUID userId;
}
