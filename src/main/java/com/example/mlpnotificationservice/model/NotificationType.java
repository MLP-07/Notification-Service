package com.example.mlpnotificationservice.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.management.Notification;
import java.util.stream.Stream;

@Getter
public enum NotificationType {
    SMS(1),
    EMAIL(2);

    private final int value;

    private NotificationType(int value) {
        this.value = value;
    }

    public static NotificationType of(int value) {
        return Stream.of(NotificationType.values())
                .filter(p -> p.getValue() == value)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
