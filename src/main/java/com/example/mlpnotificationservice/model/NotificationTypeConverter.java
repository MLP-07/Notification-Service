package com.example.mlpnotificationservice.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class NotificationTypeConverter implements AttributeConverter<NotificationType, Integer> {


    @Override
    public Integer convertToDatabaseColumn(NotificationType notificationType) {
        if (notificationType == null) return null;
        return notificationType.getValue();
    }

    @Override
    public NotificationType convertToEntityAttribute(Integer dbCode) {
        if (dbCode == null) return null;

        return NotificationType.of(dbCode);
    }
}
